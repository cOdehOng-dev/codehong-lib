package com.codehong.library.widget.text.check

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongText
import com.codehong.library.widget.language.image

class HongCheckTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var option = HongCheckTextOption()
        private set

    private var checkMark = AppCompatImageView(context)
    private var isChecked: Boolean = false

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        addView(checkMark)
    }

    fun set(
        option: HongCheckTextOption
    ): HongCheckTextView {
        this.option = option
        this.isChecked = option.checkState

        setLayout(
            width = option.width,
            height = option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
        hongBackground(
            backgroundColor = option.backgroundColorHex,
        )

        setOnClickListener {
            option.click?.invoke(option)
        }

        with(checkMark) {
            val size = context.dpToPx(option.checkSize)
            layoutParams = LayoutParams(size, size).apply {
                marginEnd = context.dpToPx(8)
            }
            setImageResource(R.drawable.honglib_ic_check)
            setOnClickListener {
                isChecked = !isChecked
                option.onCheck?.invoke(isChecked)
                update()
            }
        }
        update()

        hongText {
            set(option.textOption)
        }

        image {
            val size = context.dpToPx(option.arrowSize)
            layoutParams = LayoutParams(size, size)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(R.drawable.honglib_ic_arrow_right)
            setColorFilter(option.textOption.colorHex.parseColor())
        }

        return this

    }

    private fun update() {
        if (isChecked) {
            checkMark.setColorFilter(option.checkColor.parseColor())
        } else {
            checkMark.setColorFilter(option.uncheckColor.parseColor())
        }
    }
}