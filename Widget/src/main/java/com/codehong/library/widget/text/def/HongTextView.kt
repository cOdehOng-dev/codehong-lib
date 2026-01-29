package com.codehong.library.widget.text.def

import android.content.Context
import android.graphics.Paint
import android.text.SpannableString
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.codehong.library.widget.extensions.dpToFloatPx
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.lineBreakSyllable
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.extensions.setTextFont
import com.codehong.library.widget.extensions.setTextSize
import com.codehong.library.widget.extensions.setTextSpan
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontType
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size
import java.text.DecimalFormat

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

    fun set(option: HongTextOption): HongTextView {
        prepareView()
        setupLayout(option)

        if (!option.isValidComponent) {
            this.visibility = View.GONE
            return this
        }

        applyTextStyle(option)
        applyTextContent(option)

        return this
    }

    private fun prepareView() {
        if (childCount > 0) {
            removeAllViews()
        }
    }

    private fun setupLayout(option: HongTextOption) {
        setLayout(option.width, option.height)?.apply {
            leftMargin = context.dpToPx(option.margin.left)
            topMargin = context.dpToPx(option.margin.top)
            rightMargin = context.dpToPx(option.margin.right)
            bottomMargin = context.dpToPx(option.margin.bottom)
        }

        textView.setLayout(option.width, option.height)
        addView(textView)

        textView.setPadding(
            context.dpToPx(option.padding.left),
            context.dpToPx(option.padding.top),
            context.dpToPx(option.padding.right),
            context.dpToPx(option.padding.bottom)
        )
    }

    private fun applyTextStyle(option: HongTextOption) {
        setColor((option.colorHex ?: HongTextOption.DEFAULT_LABEL_COLOR.hex).parseColor())
        setTypography(
            option.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size(),
            option.fontType ?: HongTextOption.DEFAULT_TYPOGRAPHY.fontType(),
            option.lineHeight ?: HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
        )

        textView.maxLines = option.maxLines
        textView.textAlignment = option.align.view
        gravity = option.align.gravity
        textView.gravity = option.align.gravity

        updatePaintFlag(Paint.STRIKE_THRU_TEXT_FLAG, option.isEnableCancelLine)
        updatePaintFlag(Paint.UNDERLINE_TEXT_FLAG, option.isEnableUnderLine)

        lineBreakType = option.lineBreak
        option.overflow.truncateAt?.let { textView.ellipsize = it }
    }

    private fun updatePaintFlag(flag: Int, enable: Boolean) {
        textView.paintFlags = if (enable) {
            textView.paintFlags or flag
        } else {
            textView.paintFlags and flag.inv()
        }
    }

    private fun applyTextContent(option: HongTextOption) {
        val resultText = formatNumberIfNeeded(option)
        textView.text = resultText

        option.spanTextBuilderList?.let { builders ->
            textView.text = createSpannableText(resultText, builders, option)
        }
    }

    private fun formatNumberIfNeeded(option: HongTextOption): String? {
        if (!option.useNumberDecimal) {
            return option.text
        }

        return option.text?.let { text ->
            val clean = text.replace(",", "").trim()
            when {
                clean.toLongOrNull() != null -> DecimalFormat("#,###").format(clean.toLong())
                clean.toDoubleOrNull() != null -> DecimalFormat("#,##0.##").format(clean.toDouble())
                else -> text
            }
        }
    }

    private fun createSpannableText(
        text: String?,
        builders: List<HongTextBuilder>,
        option: HongTextOption
    ): SpannableString {
        val processedText = if (lineBreakType == HongTextLineBreak.SYLLABLE) {
            text.lineBreakSyllable()
        } else {
            text
        }

        return SpannableString(processedText).apply {
            builders.forEach { builder ->
                builder.injectOption(option)
                setTextSpan(context, builder, lineBreakType)
            }
        }
    }

    fun setColor(@ColorInt colorInt: Int) {
        textView.setTextColor(colorInt)
    }

    fun setTypography(typography: HongTypo = HongTextOption.DEFAULT_TYPOGRAPHY) {
        setTypography(
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
        textView.setTextSize(size, HongTextOption.DEFAULT_TYPOGRAPHY.size())
        textView.setTextFont(fontType, HongTextOption.DEFAULT_TYPOGRAPHY.fontType())

        val fontLineHeight = textView.paint.getFontMetrics(textView.paint.fontMetrics)
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
