package com.codehong.library.widget.util.picker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.OverScroller
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.parseColor
import kotlin.math.abs
import kotlin.math.min

class PickerItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val topAndBottomFadingEdgeStrength = 0.9f
    private val snapScrollDuration = 300
    private val selectorMaxFlingVelocityAdjustment = 4
    private val defaultTextSize = context.dpToPx(15f)

    private var selectorItemCount: Int = 9
    private var mSelectorVisibleItemCount: Int
    private var minIndex = Integer.MIN_VALUE
    private var maxIndex = Integer.MAX_VALUE
    private var mMaxValidIndex: Int? = null
    private var mMinValidIndex: Int? = null

    private var mRawPickerMiddleItemIndex: Int
    private var mRawPickerVisibleItemMiddleIndex: Int
    private var mSelectorItemIndices: ArrayList<Int>
    private var mSelectorItemValidStatus: ArrayList<Boolean>
    private var mCurSelectedItemIndex = 0
    private var mWrapSelectorRawPickerPreferred: Boolean

    private val textPaint = Paint()
    private val selectedTextColor = HongColor.BLACK_100.parseColor()
    private val unSelectedTextColor = HongColor.BLACK_100.parseColor()

    private val overScroller = OverScroller(context, DecelerateInterpolator(2.5f))
    private var velocityTracker: VelocityTracker? = null
    private val mTouchSlop: Int
    private val mMaximumVelocity: Int
    private val mMinimumVelocity: Int
    private var mLastY: Float = 0f
    private var mIsDragging: Boolean = false
    private var mCurrentFirstItemOffset: Int = 0
    private var mInitialFirstItemOffset = Int.MIN_VALUE
    private var mTextGapHeight: Int = 0
    private var mItemHeight: Int = 0
    private var mTextHeight: Int = 0
    private var mPreviousScrollerY: Int = 0
    private var mOnValueChangeListener: OnValueChangeListener? = null
    private var mOnScrollListener: OnScrollListener? = null
    private var mAdapter: PickerItemAdapter? = null
    private val selectedTextScale = 0.3f

    interface OnValueChangeListener {
        fun onValueChange(picker: PickerItem, oldVal: String, newVal: String)
    }

    interface OnScrollListener {
        fun onScrollStateChange(view: PickerItem, scrollState: Int)
    }

    companion object {
        const val SCROLL_STATE_IDLE = 0
        const val SCROLL_STATE_TOUCH_SCROLL = 1
        const val SCROLL_STATE_FLING = 2
    }

    /**
     * The current scroll state of the number picker.
     */
    private var scrollState = SCROLL_STATE_IDLE

    init {
        mRawPickerMiddleItemIndex = (selectorItemCount - 1) / 2
        mSelectorVisibleItemCount = selectorItemCount - 2
        mRawPickerVisibleItemMiddleIndex = (mSelectorVisibleItemCount - 1) / 2
        mSelectorItemIndices = ArrayList(selectorItemCount)
        mSelectorItemValidStatus = ArrayList(selectorItemCount)

        mWrapSelectorRawPickerPreferred = false

        val configuration = ViewConfiguration.get(context)
        mTouchSlop = configuration.scaledTouchSlop
        mMaximumVelocity = configuration.scaledMaximumFlingVelocity / selectorMaxFlingVelocityAdjustment
        mMinimumVelocity = configuration.scaledMinimumFlingVelocity


        textPaint.run {
            isAntiAlias = true
            textSize = defaultTextSize.toFloat()
            textAlign = Paint.Align.CENTER
            style = Paint.Style.FILL_AND_STROKE
            typeface = ResourcesCompat.getFont(context, R.font.pretendard_400)
        }

        initializeSelectorRawPickerIndices()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (changed) {
            // need to do all this when we know our size
            initializeSelectorRawPicker()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Try greedily to fit the max width and height.
        var layoutParams: ViewGroup.LayoutParams? = layoutParams
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        var width = calculateSize(suggestedMinimumWidth, layoutParams.width, widthMeasureSpec)

        width += paddingLeft + paddingRight

        setMeasuredDimension(width, context.dpToPx(244f))
    }

    override fun getSuggestedMinimumWidth(): Int {
        var suggested = super.getSuggestedMinimumHeight()
        if (mSelectorVisibleItemCount > 0) {
            suggested = Math.max(suggested, computeMaximumWidth())
        }
        return context.dpToPx(20f) + suggested + context.dpToPx(20f)
    }

    override fun getSuggestedMinimumHeight(): Int {
        var suggested = super.getSuggestedMinimumWidth()
        if (mSelectorVisibleItemCount > 0) {
            val fontMetricsInt = textPaint.fontMetricsInt
            val height = fontMetricsInt.descent - fontMetricsInt.ascent
            suggested = Math.max(suggested, height * mSelectorVisibleItemCount)
        }
        return suggested
    }

    private fun computeMaximumWidth(): Int {
        textPaint.textSize = defaultTextSize * 1.3f
        if (mAdapter != null) {
            return if (mAdapter!!.getTextWithMaximumLength().isNotEmpty()) {
                val suggestedWith =
                    textPaint.measureText(mAdapter!!.getTextWithMaximumLength()).toInt()
                textPaint.textSize = defaultTextSize * 1.0f
                suggestedWith
            } else {
                val suggestedWith = textPaint.measureText("0000").toInt()
                textPaint.textSize = defaultTextSize * 1.0f
                suggestedWith
            }
        }
        val widthForMinIndex = textPaint.measureText(minIndex.toString()).toInt()
        val widthForMaxIndex = textPaint.measureText(maxIndex.toString()).toInt()
        textPaint.textSize = defaultTextSize * 1.0f
        return if (widthForMinIndex > widthForMaxIndex) {
            widthForMinIndex
        } else {
            widthForMaxIndex
        }
    }

    private fun calculateSize(suggestedSize: Int, paramSize: Int, measureSpec: Int): Int {
        var result = 0
        val size = MeasureSpec.getSize(measureSpec)
        val mode = MeasureSpec.getMode(measureSpec)

        when (MeasureSpec.getMode(mode)) {
            MeasureSpec.AT_MOST ->
                result = when (paramSize) {
                    ViewGroup.LayoutParams.WRAP_CONTENT -> min(suggestedSize, size)
                    ViewGroup.LayoutParams.MATCH_PARENT -> size
                    else -> min(paramSize, size)
                }

            MeasureSpec.EXACTLY -> result = size
            MeasureSpec.UNSPECIFIED ->
                result =
                    if (paramSize == ViewGroup.LayoutParams.WRAP_CONTENT || paramSize == ViewGroup.LayoutParams.MATCH_PARENT) {
                        suggestedSize
                    } else {
                        paramSize
                    }
        }

        return result
    }

    private fun initializeSelectorRawPicker() {
        mItemHeight = getItemHeight()
        mTextHeight = computeTextHeight()
        mTextGapHeight = getGapHeight()

        val visibleMiddleItemPos =
            mItemHeight * mRawPickerVisibleItemMiddleIndex + (mItemHeight + mTextHeight) / 2
        mInitialFirstItemOffset = visibleMiddleItemPos - mItemHeight * mRawPickerMiddleItemIndex
        mCurrentFirstItemOffset = mInitialFirstItemOffset
    }

    private fun initializeSelectorRawPickerIndices() {
        mSelectorItemIndices.clear()
        mSelectorItemValidStatus.clear()

        mCurSelectedItemIndex = if (mMinValidIndex == null || mMinValidIndex!! < minIndex) {
            if (minIndex <= 0) {
                0
            } else {
                minIndex
            }
        } else {
            if (mMinValidIndex!! <= 0) {
                0
            } else {
                mMinValidIndex!!
            }
        }

        for (i in 0 until selectorItemCount) {
            var selectorIndex = mCurSelectedItemIndex + (i - mRawPickerMiddleItemIndex)
            if (mWrapSelectorRawPickerPreferred) {
                selectorIndex = getWrappedSelectorIndex(selectorIndex)
            }
            mSelectorItemIndices.add(selectorIndex)
            mSelectorItemValidStatus.add(isValidPosition(selectorIndex))
        }
    }

    override fun getBottomFadingEdgeStrength(): Float = topAndBottomFadingEdgeStrength

    override fun getTopFadingEdgeStrength(): Float = topAndBottomFadingEdgeStrength

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawVertical(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        onTouchEventVertical(event)
        return true
    }

    private fun onTouchEventVertical(event: MotionEvent) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain()
        }

        velocityTracker?.addMovement(event)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (!overScroller.isFinished) {
                    overScroller.forceFinished(true)
                }

                mLastY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                var deltaY = event.y - mLastY
                if (!mIsDragging && abs(deltaY) > mTouchSlop) {
                    parent?.requestDisallowInterceptTouchEvent(true)

                    if (deltaY > 0) {
                        deltaY -= mTouchSlop
                    } else {
                        deltaY += mTouchSlop
                    }
                    onScrollStateChange(SCROLL_STATE_TOUCH_SCROLL)
                    mIsDragging = true
                }

                if (mIsDragging) {
                    scrollBy(0, deltaY.toInt())
                    invalidate()
                    mLastY = event.y
                }
            }

            MotionEvent.ACTION_UP -> {
                if (mIsDragging) {
                    mIsDragging = false
                    parent?.requestDisallowInterceptTouchEvent(false)

                    velocityTracker?.computeCurrentVelocity(1000, mMaximumVelocity.toFloat())
                    val velocity = velocityTracker?.yVelocity?.toInt()

                    if (abs(velocity!!) > mMinimumVelocity) {
                        mPreviousScrollerY = 0
                        overScroller.fling(
                            scrollX, scrollY, 0, velocity, 0, 0, Integer.MIN_VALUE,
                            Integer.MAX_VALUE, 0, (getItemHeight() * 0.7).toInt()
                        )
                        invalidateOnAnimation()
                        onScrollStateChange(SCROLL_STATE_FLING)
                    }
                    recyclerVelocityTracker()
                } else {
                    val y = event.y.toInt()
                    handlerClickVertical(y)
                }
            }

            MotionEvent.ACTION_CANCEL -> {
                if (mIsDragging) {
                    mIsDragging = false
                }
                recyclerVelocityTracker()
            }
        }
    }

    private fun handlerClickVertical(y: Int) {
        val selectorIndexOffset = y / mItemHeight - mRawPickerVisibleItemMiddleIndex
        changeValueBySteps(selectorIndexOffset)
    }

    override fun scrollBy(x: Int, y: Int) {
        if (y == 0) {
            return
        }

        val gap = mTextGapHeight

        if (!mWrapSelectorRawPickerPreferred && y > 0
            && (
                    mSelectorItemIndices[mRawPickerMiddleItemIndex] <= minIndex
                            || (mMinValidIndex != null && mSelectorItemIndices[mRawPickerMiddleItemIndex] <= mMinValidIndex!!)
                    )
        ) {
            if (mCurrentFirstItemOffset + y - mInitialFirstItemOffset < gap / 2) {
                mCurrentFirstItemOffset += y
            } else {
                mCurrentFirstItemOffset = mInitialFirstItemOffset + (gap / 2)
                if (!overScroller!!.isFinished && !mIsDragging) {
                    overScroller!!.abortAnimation()
                }
            }
            return
        }

        if (!mWrapSelectorRawPickerPreferred && y < 0
            && (
                    mSelectorItemIndices[mRawPickerMiddleItemIndex] >= maxIndex
                            || (mMaxValidIndex != null && mSelectorItemIndices[mRawPickerMiddleItemIndex] >= mMaxValidIndex!!)
                    )
        ) {
            if (mCurrentFirstItemOffset + y - mInitialFirstItemOffset > -(gap / 2)) {
                mCurrentFirstItemOffset += y
            } else {
                mCurrentFirstItemOffset = mInitialFirstItemOffset - (gap / 2)
                if (!overScroller!!.isFinished && !mIsDragging) {
                    overScroller!!.abortAnimation()
                }
            }
            return
        }

        mCurrentFirstItemOffset += y

        while (mCurrentFirstItemOffset - mInitialFirstItemOffset < -gap) {
            mCurrentFirstItemOffset += mItemHeight
            increaseSelectorsIndex()
            if (!mWrapSelectorRawPickerPreferred
                && (
                        mSelectorItemIndices[mRawPickerMiddleItemIndex] >= maxIndex
                                || (mMaxValidIndex != null && mSelectorItemIndices[mRawPickerMiddleItemIndex] >= mMaxValidIndex!!)
                        )
            ) {
                mCurrentFirstItemOffset = mInitialFirstItemOffset
            }
        }

        while (mCurrentFirstItemOffset - mInitialFirstItemOffset > gap) {
            mCurrentFirstItemOffset -= mItemHeight
            decreaseSelectorsIndex()
            if (!mWrapSelectorRawPickerPreferred
                && (
                        mSelectorItemIndices[mRawPickerMiddleItemIndex] <= minIndex
                                || (mMinValidIndex != null && mSelectorItemIndices[mRawPickerMiddleItemIndex] <= mMinValidIndex!!)
                        )
            ) {
                mCurrentFirstItemOffset = mInitialFirstItemOffset
            }
        }
        onSelectionChanged(mSelectorItemIndices[mRawPickerMiddleItemIndex])
    }

    override fun computeScroll() {
        super.computeScroll()
        if (overScroller.computeScrollOffset()) {
            val x = overScroller.currX
            val y = overScroller.currY

            if (mPreviousScrollerY == 0) {
                mPreviousScrollerY = overScroller.startY
            }
            scrollBy(x, y - mPreviousScrollerY)
            mPreviousScrollerY = y
            invalidate()
        } else {
            if (!mIsDragging) {
                // align item
                adjustItemVertical()
            }
        }
    }

    private fun adjustItemVertical() {
        mPreviousScrollerY = 0
        var deltaY = mInitialFirstItemOffset - mCurrentFirstItemOffset

        if (Math.abs(deltaY) > mItemHeight / 2) {
            deltaY += if (deltaY > 0) {
                -mItemHeight
            } else {
                mItemHeight
            }
        }

        if (deltaY != 0) {
            overScroller.startScroll(scrollX, scrollY, 0, deltaY, 800)
            invalidateOnAnimation()
        }

        onScrollStateChange(SCROLL_STATE_IDLE)
    }

    private fun recyclerVelocityTracker() {
        velocityTracker?.recycle()
        velocityTracker = null
    }

    private fun onScrollStateChange(scrollState: Int) {
        if (this.scrollState == scrollState) {
            return
        }
        this.scrollState = scrollState
        mOnScrollListener?.onScrollStateChange(this, scrollState)
    }

    private fun getItemHeight(): Int {
        return height / (selectorItemCount - 2)
    }

    private fun getGapHeight(): Int {
        return getItemHeight() - computeTextHeight()
    }

    private fun computeTextHeight(): Int {
        val metricsInt = textPaint.fontMetricsInt
        return abs(metricsInt.bottom + metricsInt.top)
    }

    private fun invalidateOnAnimation() {
        postInvalidateOnAnimation()
    }

    private fun drawVertical(canvas: Canvas) {
        if (mSelectorItemIndices.size == 0) {
            return
        }
        val itemHeight = getItemHeight()

        val x = when (textPaint.textAlign) {
            Paint.Align.LEFT -> paddingLeft.toFloat()
            Paint.Align.CENTER -> ((right - left) / 2).toFloat()
            Paint.Align.RIGHT -> (right - left).toFloat() - paddingRight.toFloat()
            else -> ((right - left) / 2).toFloat()
        }

        var y = mCurrentFirstItemOffset.toFloat()

        var i = 0

        val topIndexDiffToMid = mRawPickerVisibleItemMiddleIndex
        val bottomIndexDiffToMid = mSelectorVisibleItemCount - mRawPickerVisibleItemMiddleIndex - 1
        val maxIndexDiffToMid = Math.max(topIndexDiffToMid, bottomIndexDiffToMid)

        while (i < mSelectorItemIndices.size) {
            var scale = 1f

            val offsetToMiddle =
                Math.abs(
                    y - (mInitialFirstItemOffset + mRawPickerMiddleItemIndex * itemHeight).toFloat()
                )

            if (maxIndexDiffToMid != 0) {
                scale =
                    selectedTextScale * (itemHeight * maxIndexDiffToMid - offsetToMiddle) / (itemHeight * maxIndexDiffToMid) + 1
            }

            if (mSelectorItemValidStatus[i]) {
                if (offsetToMiddle < mItemHeight / 2) {
                    textPaint.color = selectedTextColor
                } else {
                    textPaint.color = unSelectedTextColor
                }
            } else {
                textPaint.color = ContextCompat.getColor(context, R.color.honglib_color_000000)
            }

            canvas.save()
            canvas.scale(scale, scale, x, y)
            canvas.drawText(getValue(mSelectorItemIndices[i]), x, y, textPaint)
            canvas.restore()

            y += itemHeight
            i++
        }
    }

    private fun getPosition(value: String): Int = when {
        mAdapter != null -> {
            validatePosition(mAdapter!!.getPosition(value))
        }

        else -> try {
            val position = value.toInt()
            validatePosition(position)
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun increaseSelectorsIndex() {
        for (i in 0 until (mSelectorItemIndices.size - 1)) {
            mSelectorItemIndices[i] = mSelectorItemIndices[i + 1]
            mSelectorItemValidStatus[i] = mSelectorItemValidStatus[i + 1]
        }
        var nextScrollSelectorIndex = mSelectorItemIndices[mSelectorItemIndices.size - 2] + 1
        if (mWrapSelectorRawPickerPreferred && nextScrollSelectorIndex > maxIndex) {
            nextScrollSelectorIndex = minIndex
        }
        mSelectorItemIndices[mSelectorItemIndices.size - 1] = nextScrollSelectorIndex
        mSelectorItemValidStatus[mSelectorItemIndices.size - 1] =
            isValidPosition(nextScrollSelectorIndex)
    }

    private fun decreaseSelectorsIndex() {
        for (i in mSelectorItemIndices.size - 1 downTo 1) {
            mSelectorItemIndices[i] = mSelectorItemIndices[i - 1]
            mSelectorItemValidStatus[i] = mSelectorItemValidStatus[i - 1]
        }
        var nextScrollSelectorIndex = mSelectorItemIndices[1] - 1
        if (mWrapSelectorRawPickerPreferred && nextScrollSelectorIndex < minIndex) {
            nextScrollSelectorIndex = maxIndex
        }
        mSelectorItemIndices[0] = nextScrollSelectorIndex
        mSelectorItemValidStatus[0] = isValidPosition(nextScrollSelectorIndex)
    }

    private fun changeValueBySteps(steps: Int) {
        mPreviousScrollerY = 0
        overScroller?.startScroll(0, 0, 0, -mItemHeight * steps, snapScrollDuration)
        invalidate()
    }

    private fun onSelectionChanged(current: Int) {
        val previous = mCurSelectedItemIndex
        mCurSelectedItemIndex = current
        if (previous != current) {
            notifyChange(previous, current)
            mOnValueChangeListener?.onValueChange(this, getValue(previous), getValue(current))
        }
    }

    private fun getWrappedSelectorIndex(selectorIndex: Int): Int {
        if (selectorIndex > maxIndex) {
            return minIndex + (selectorIndex - maxIndex) % (maxIndex - minIndex + 1) - 1
        } else if (selectorIndex < minIndex) {
            return maxIndex - (minIndex - selectorIndex) % (maxIndex - minIndex + 1) + 1
        }
        return selectorIndex
    }

    private fun notifyChange(previous: Int, current: Int) {
        mOnValueChangeListener?.onValueChange(this, getValue(previous), getValue(current))
    }

    private fun validatePosition(position: Int): Int {
        return if (!mWrapSelectorRawPickerPreferred) {
            when {
                mMaxValidIndex == null && position > maxIndex -> maxIndex
                mMaxValidIndex != null && position > mMaxValidIndex!! -> mMaxValidIndex!!
                mMinValidIndex == null && position < minIndex -> minIndex
                mMinValidIndex != null && position < mMinValidIndex!! -> mMinValidIndex!!
                else -> position
            }
        } else {
            getWrappedSelectorIndex(position)
        }
    }

    fun scrollTo(position: Int) {
        if (mCurSelectedItemIndex == position) {
            return
        }

        mCurSelectedItemIndex = position
        mSelectorItemIndices.clear()
        for (i in 0 until selectorItemCount) {
            var selectorIndex = mCurSelectedItemIndex + (i - mRawPickerMiddleItemIndex)
            if (mWrapSelectorRawPickerPreferred) {
                selectorIndex = getWrappedSelectorIndex(selectorIndex)
            }
            mSelectorItemIndices.add(selectorIndex)
        }

        invalidate()
    }

    fun setOnValueChangedListener(onValueChangeListener: OnValueChangeListener) {
        mOnValueChangeListener = onValueChangeListener
    }

    fun setOnScrollListener(onScrollListener: OnScrollListener) {
        mOnScrollListener = onScrollListener
    }

    fun smoothScrollTo(position: Int) {
        val realPosition = validatePosition(position)
        changeValueBySteps(realPosition - mCurSelectedItemIndex)
    }

    fun smoothScrollToValue(value: String) {
        smoothScrollTo(getPosition(value))
    }

    fun scrollToValue(value: String) {
        scrollTo(getPosition(value))
    }

    /**
     * Set user define adapter
     *
     * @adapter user define adapter
     * @indexRangeBasedOnAdapterSize specific if the picker's min~max range is based on adapter's size
     */
    fun setAdapter(adapter: PickerItemAdapter?, indexRangeBasedOnAdapterSize: Boolean = true) {
        mAdapter = adapter
        if (mAdapter == null) {
            initializeSelectorRawPickerIndices()
            invalidate()
            return
        }

        if (adapter!!.getSize() != -1 && indexRangeBasedOnAdapterSize) {
            maxIndex = adapter.getSize() - 1
            minIndex = 0
        }

        mMaxValidIndex = adapter.getMaxValidIndex()
        mMinValidIndex = adapter.getMinValidIndex()

        initializeSelectorRawPickerIndices()
        invalidate()

        mAdapter?.picker = this
    }

    fun setWrapSelectorRawPicker(wrap: Boolean) {
        mWrapSelectorRawPickerPreferred = wrap
        invalidate()
    }

    fun getWrapSelectorRawPicker(): Boolean {
        return mWrapSelectorRawPickerPreferred
    }

    fun setRawPickerItemCount(count: Int) {
        selectorItemCount = count + 2
        mRawPickerMiddleItemIndex = (selectorItemCount - 1) / 2
        mSelectorVisibleItemCount = selectorItemCount - 2
        mRawPickerVisibleItemMiddleIndex = (mSelectorVisibleItemCount - 1) / 2
        mSelectorItemIndices = ArrayList(selectorItemCount)
        mSelectorItemValidStatus = ArrayList(selectorItemCount)
        reset()
        invalidate()
    }

    fun getValue(position: Int): String = when {
        mAdapter != null -> mAdapter!!.getValue(position)
        else -> if (!mWrapSelectorRawPickerPreferred) {
            when {
                position > maxIndex -> ""
                position < minIndex -> ""
                else -> position.toString()
            }
        } else {
            getWrappedSelectorIndex(position).toString()
        }
    }

    fun setValue(value: String) {
        scrollToValue(value)
    }

    fun setMaxValue(max: Int) {
        maxIndex = max
    }

    fun getMaxValue(): String {
        return if (mAdapter != null) {
            mAdapter!!.getValue(maxIndex)
        } else {
            maxIndex.toString()
        }
    }

    fun setMinValue(min: Int) {
        minIndex = min
    }

    fun setMinValidValue(minValid: Int?) {
        mMinValidIndex = minValid
    }

    fun setMaxValidValue(maxValid: Int?) {
        mMaxValidIndex = maxValid
    }

    fun getMinValue(): String {
        return if (mAdapter != null) {
            mAdapter!!.getValue(minIndex)
        } else {
            minIndex.toString()
        }
    }

    fun reset() {
        initializeSelectorRawPickerIndices()
        initializeSelectorRawPicker()
        invalidate()
    }

    fun getCurrentItem(): String {
        return getValue(mCurSelectedItemIndex)
    }

    fun isValidPosition(position: Int): Boolean {
        return when {
            mMinValidIndex != null && position < mMinValidIndex!! -> false
            mMaxValidIndex != null && position > mMaxValidIndex!! -> false
            else -> true
        }
    }

    abstract class PickerItemAdapter {

        abstract fun getValue(position: Int): String

        abstract fun getPosition(vale: String): Int

        abstract fun getTextWithMaximumLength(): String

        open fun getSize(): Int = -1

        open fun getMinValidIndex(): Int? {
            return null
        }

        open fun getMaxValidIndex(): Int? {
            return null
        }

        var picker: PickerItem? = null

        fun notifyDataSetChanged() {
            picker?.setAdapter(this)
            picker?.requestLayout()
        }
    }
}
