package com.codehong.library.widget.text

import androidx.compose.ui.text.font.FontWeight
import com.codehong.library.widget.HongSpacingInfo
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.isBold
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size

class HongTextOption : HongWidgetCommonOption {

    companion object {
        val DEFAULT_LABEL_COLOR = HongColor.BLACK_100
        val DEFAULT_TYPOGRAPHY = HongTypo.BODY_14
        val DEFAULT_OVER_FLOW = HongTextOverflow.ELLIPSIS
    }

    override var isValidComponent: Boolean = true

    override val type: HongWidgetType = HongWidgetType.TEXT
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)

    override var click: (HongWidgetCommonOption) -> Unit = {}

    var text: String? = null
    var colorHex: String? = null
    var typography: HongTypo? = null
    var textSize: Int? = null
    var fontType: HongFont? = null

    var align: HongTextAlign = HongTextAlign.LEFT
    var maxLines: Int = Int.MAX_VALUE
    var overflow: HongTextOverflow = DEFAULT_OVER_FLOW
    var lineBreak: HongTextLineBreak = HongTextLineBreak.DEFAULT

    var isEnableCancelLine: Boolean = false
    var isEnableUnderLine: Boolean = false

    var spanTextsProperty: List<HongTextOption>? = null

    var isTextRequired: Boolean = false

    internal val lineHeight: Int?
        get() = typography?.lineHeight()

    internal val fontWeight: FontWeight
        get() = fontType?.weight
            ?: DEFAULT_TYPOGRAPHY.fontWeight()

    internal val size: Int?
        get() = typography?.size() ?: textSize

    fun injectOption(option: HongTextOption?) {
        if (option == null) return

        if (this.colorHex.isNullOrEmpty()) {
            if (!option.colorHex.isNullOrEmpty()) {
                this.colorHex = option.colorHex
            } else {
                this.colorHex = DEFAULT_LABEL_COLOR.hex
            }
        }

        if (this.typography == null && option.typography != null) {
            this.typography = option.typography
        } else {
            if (this.textSize == null && option.textSize != null) {
                this.textSize = option.textSize
            }

            if (this.fontType == null && option.fontType != null) {
                this.fontType = option.fontType
            }
        }
    }

    fun hasMargin(): Boolean = (
            margin.left > 0f ||
                    margin.top > 0f ||
                    margin.right > 0f ||
                    margin.bottom > 0f
            )

    class Builder {
        private val option = HongTextOption()

        fun width(width: Int?) = apply {
            width?.let { option.width = it }
        }

        fun height(height: Int?) = apply {
            height?.let { option.height = it }
        }

        fun margin(margin: HongSpacingInfo) = apply {
            option.margin = margin
        }

        fun padding(padding: HongSpacingInfo) = apply {
            option.padding = padding
        }

        fun onClick(onClick: (HongTextOption) -> Unit) = apply {
            option.click = {
                if (it is HongTextOption) {
                    onClick(it)
                }
            }
        }

        fun text(text: String?) = apply {
            option.text = text
        }

        // 컬러
        fun color(hex: String?) = apply {
            hex?.takeIf { it.isNotEmpty() }?.let {
                option.colorHex = it
            }
        }

        fun color(hongColor: HongColor) = apply {
            option.colorHex = hongColor.hex
        }

        /** 텍스트 타이포그라피 */
        fun typography(typography: HongTypo?) = apply {
            option.typography = typography
            option.textSize = typography?.size()
            option.fontType =
                if (typography?.isBold() == true) {
                    HongFont.PRETENDARD_700
                } else {
                    HongFont.PRETENDARD_400
                }
        }

        fun size(dp: Int?) = apply {
            option.textSize = dp
        }

        fun fontType(textFontType: HongFont?) = apply {
            option.fontType = textFontType
        }

        /**
         * 텍스트 정렬
         * 좌측 / 우측 / 가운데
         * width가 WRAP_CONTENT인 경우에는 의도한 대로 적용되지 않으므로 주의!
         */
        fun textAlign(textAlign: HongTextAlign) = apply {
            option.align = textAlign
        }

        fun maxLines(maxLines: Int) = apply {
            option.maxLines = maxLines
        }

        fun overflow(overflow: HongTextOverflow?) = apply {
            option.overflow = overflow ?: DEFAULT_OVER_FLOW
        }

        fun lineBreak(lineBreak: HongTextLineBreak) = apply {
            option.lineBreak = lineBreak
        }

        fun isEnableCancelLine(isEnable: Boolean) = apply {
            option.isEnableCancelLine = isEnable
        }

        fun isEnableUnderLine(isEnable: Boolean) = apply {
            option.isEnableUnderLine = isEnable
        }

        fun spanTextsInfo(spanTextsInfo: List<HongTextOption>?) = apply {
            option.spanTextsProperty = spanTextsInfo
        }

        fun isTextRequired(isRequired: Boolean) = apply {
            option.isTextRequired = isRequired
        }

        fun build(): HongTextOption {
            if (option.text.isNullOrEmpty() && option.isTextRequired) {
                option.isValidComponent = false
            }

            return option
        }

        fun copy(inject: HongTextOption): Builder {
            return Builder()
                .width(inject.width)
                .height(inject.height)
                .text(inject.text)
                .color(inject.colorHex)
                .typography(inject.typography)
                .size(inject.textSize)
                .fontType(inject.fontType)
                .textAlign(inject.align)
                .maxLines(inject.maxLines)
                .overflow(inject.overflow)
                .lineBreak(inject.lineBreak)
                .isEnableCancelLine(inject.isEnableCancelLine)
                .isEnableUnderLine(inject.isEnableUnderLine)
                .spanTextsInfo(inject.spanTextsProperty)
                .margin(inject.margin)
                .padding(inject.padding)
                .isTextRequired(inject.isTextRequired)
                .onClick(inject.click)
        }
    }
}

fun HongTextOption.builder(): HongTextOption.Builder {
    return HongTextOption.Builder().copy(this)
}
