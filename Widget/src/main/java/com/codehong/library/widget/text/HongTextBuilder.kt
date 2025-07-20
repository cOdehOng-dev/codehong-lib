package com.codehong.library.widget.text

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.isBold
import com.codehong.library.widget.rule.typo.size

class HongTextBuilder : HongWidgetCommonBuilder<HongTextOption, HongTextBuilder> {

    override val builder: HongTextBuilder = this
    override val option: HongTextOption = HongTextOption()

    fun injectOption(inject: HongTextOption?) {
        if (inject == null) return

        if (option.colorHex.isNullOrEmpty()) {
            if (!inject.colorHex.isNullOrEmpty()) {
                option.colorHex = inject.colorHex
            } else {
                option.colorHex = HongTextOption.DEFAULT_LABEL_COLOR.hex
            }
        }

        if (option.typography == null && inject.typography != null) {
            option.typography = inject.typography
        } else {
            if (option.textSize == null && inject.textSize != null) {
                option.textSize = inject.textSize
            }

            if (option.fontType == null && inject.fontType != null) {
                option.fontType = inject.fontType
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
        option.fontType = if (typography?.isBold() == true) {
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
        option.overflow = overflow ?: HongTextOption.DEFAULT_OVER_FLOW
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

    fun spanTextBuilderList(spanTextBuilderList: List<HongTextBuilder>?) = apply {
        option.spanTextBuilderList = spanTextBuilderList
    }

    fun isTextRequired(isRequired: Boolean) = apply {
        option.isTextRequired = isRequired
    }

    fun copy(inject: HongTextOption): HongTextBuilder {
        return HongTextBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
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
            .spanTextBuilderList(inject.spanTextBuilderList)
            .isTextRequired(inject.isTextRequired)
    }
}
