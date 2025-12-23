package com.codehong.library.widget.text.label

import androidx.compose.ui.text.font.FontWeight
import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size

data class HongTextOption(
    override val type: HongWidgetType = HongWidgetType.TEXT
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_LABEL_COLOR = HongColor.BLACK_100
        val DEFAULT_TYPOGRAPHY = HongTypo.BODY_14
        val DEFAULT_OVER_FLOW = HongTextOverflow.ELLIPSIS
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)

    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

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

    var spanTextBuilderList: List<HongTextBuilder>? = null

    var isTextRequired: Boolean = false

    var useNumberDecimal = false


    internal val lineHeight: Int?
        get() = typography?.lineHeight()

    internal val fontWeight: FontWeight
        get() = fontType?.weight
            ?: DEFAULT_TYPOGRAPHY.fontWeight()

    internal val size: Int?
        get() = typography?.size() ?: textSize


    fun hasMargin(): Boolean = (
            margin.left > 0f ||
                    margin.top > 0f ||
                    margin.right > 0f ||
                    margin.bottom > 0f
            )


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (text != other.text) return false
        if (colorHex != other.colorHex) return false
        if (typography != other.typography) return false
        if (textSize != other.textSize) return false
        if (fontType != other.fontType) return false
        if (align != other.align) return false
        if (maxLines != other.maxLines) return false
        if (overflow != other.overflow) return false
        if (lineBreak != other.lineBreak) return false
        if (isEnableCancelLine != other.isEnableCancelLine) return false
        if (isEnableUnderLine != other.isEnableUnderLine) return false
        if (spanTextBuilderList != other.spanTextBuilderList) return false
        if (isTextRequired != other.isTextRequired) return false
        if (useNumberDecimal != other.useNumberDecimal) return false
        if (backgroundColorHex != other.backgroundColorHex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (colorHex?.hashCode() ?: 0)
        result = 31 * result + (typography?.hashCode() ?: 0)
        result = 31 * result + (textSize ?: 0)
        result = 31 * result + (fontType?.hashCode() ?: 0)
        result = 31 * result + align.hashCode()
        result = 31 * result + maxLines
        result = 31 * result + overflow.hashCode()
        result = 31 * result + lineBreak.hashCode()
        result = 31 * result + isEnableCancelLine.hashCode()
        result = 31 * result + isEnableUnderLine.hashCode()
        result = 31 * result + (spanTextBuilderList?.hashCode() ?: 0)
        result = 31 * result + isTextRequired.hashCode()
        result = 31 * result + useNumberDecimal.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongTextOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "text=$text, " +
                "colorHex=$colorHex, " +
                "typography=$typography, " +
                "textSize=$textSize, " +
                "fontType=$fontType, " +
                "align=$align, " +
                "maxLines=$maxLines, " +
                "overflow=$overflow, " +
                "lineBreak=$lineBreak, " +
                "isEnableCancelLine=$isEnableCancelLine, " +
                "isEnableUnderLine=$isEnableUnderLine, " +
                "spanTextBuilderList=$spanTextBuilderList, " +
                "isTextRequired=$isTextRequired, " +
                "useNumberDecimal=$useNumberDecimal, " +
                "backgroundColorHex='$backgroundColorHex'" +
                ")"
    }


}
