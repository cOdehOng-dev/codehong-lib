package com.codehong.library.widget.text

import android.content.Context
import android.graphics.Paint
import android.text.SpannableString
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontType
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.util.dpToFloatPx
import com.codehong.library.widget.util.dpToPx
import com.codehong.library.widget.util.lineBreakSyllable
import com.codehong.library.widget.util.parseColor
import com.codehong.library.widget.util.setLayout
import com.codehong.library.widget.util.setTextFont
import com.codehong.library.widget.util.setTextSize
import com.codehong.library.widget.util.setTextSpan

class HongTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var textView = HongCustomText(context)

    private var lineBreakType: HongTextLineBreak = HongTextLineBreak.DEFAULT
        set(value) {
            field = value
            textView.lineBreakType = value
        }

    fun set(
        option: HongTextOption
    ): HongTextView {
        if (this.childCount > 0) {
            this.removeAllViews()
        }

        this.setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }

        textView.setLayout(
            option.width,
            option.height
        )
        this.addView(textView)
        textView.setPadding(
            context.dpToPx(option.padding.left),
            context.dpToPx(option.padding.top),
            context.dpToPx(option.padding.right),
            context.dpToPx(option.padding.bottom)
        )

        if (!option.isValidComponent) {
            this.visibility = View.GONE
            return this
        }


        setColor(
            (option.colorHex ?: HongTextOption.DEFAULT_LABEL_COLOR.hex).parseColor()
        )
        setTypography(
            option.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size(),
            option.fontType ?: HongTextOption.DEFAULT_TYPOGRAPHY.fontType(),
            option.lineHeight ?: HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
        )
        textView.maxLines = option.maxLines
        textView.textAlignment = option.align.view

        this.gravity = option.align.gravity
        textView.gravity = option.align.gravity

        if (option.isEnableCancelLine) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        if (option.isEnableUnderLine) {
            textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
        }

        this.lineBreakType = (option.lineBreak)

        option.overflow.truncateAt?.let {
            textView.ellipsize = it
        }

        textView.text = option.text

        option.spanTextsProperty?.let {
            val spannableText = SpannableString(
                if (lineBreakType == HongTextLineBreak.SYLLABLE) {
                    option.text.lineBreakSyllable()
                } else {
                    option.text
                }
            )

            it.forEach { spanProperty ->
                spanProperty.injectOption(option)
                spannableText.setTextSpan(
                    context,
                    spanProperty,
                    lineBreakType
                )
            }

            textView.text = spannableText
        }

        return this

    }

    fun setColor(@ColorInt colorInt: Int) {
        setColor(
            textView,
            colorInt
        )
    }
    private fun setColor(
        textView: HongCustomText,
        @ColorInt colorInt: Int
    ) {
        textView.setTextColor(colorInt)
    }

    fun setTypography(
        typography: HongTypo = HongTextOption.DEFAULT_TYPOGRAPHY
    ) {
        setTypography(
            this.textView,
            typography.size(),
            typography.fontType(),
            typography.lineHeight()
        )
    }

    private fun setTypography(
        size: Int = HongTextOption.DEFAULT_TYPOGRAPHY.size(),
        fontType: HongFont = HongTextOption.DEFAULT_TYPOGRAPHY.fontType(),
        lineHeight: Int = HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
    ) {
        setTypography(
            this.textView,
            size,
            fontType,
            lineHeight
        )
    }
    private fun setTypography(
        textView: HongCustomText,
        size: Int = HongTextOption.DEFAULT_TYPOGRAPHY.size(),
        fontType: HongFont = HongTextOption.DEFAULT_TYPOGRAPHY.fontType(),
        lineHeight: Int = HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
    ) {
        textView.setTextSize(size, HongTextOption.DEFAULT_TYPOGRAPHY.size())
        textView.setTextFont(fontType, HongTextOption.DEFAULT_TYPOGRAPHY.fontType())

        // 폰트가 가지고 있는 lineHeight 값 추출
        val fontLineHeight = textView.paint.getFontMetrics(textView.paint.fontMetrics)

        // 디자이너가 요청한 lineHeight 값
        val figmaLineHeight = context.dpToFloatPx(lineHeight)

        textView.setLineSpacing(0f, figmaLineHeight / fontLineHeight)
        textView.includeFontPadding = true
    }
}

internal class HongCustomText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var lineBreakType: HongTextLineBreak = HongTextLineBreak.DEFAULT

    override fun setText(text: CharSequence?, type: BufferType?) {
        if (lineBreakType == HongTextLineBreak.SYLLABLE && text !is SpannableString) {
            if (text?.toString()?.contains("\uff07") == true) {
                super.setText(text, type)
            } else {
                super.setText(text.lineBreakSyllable(), type)
            }
        } else {
            super.setText(text, type)
        }
    }
}
