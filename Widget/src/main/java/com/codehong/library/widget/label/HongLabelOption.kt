package com.codehong.library.widget.label

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongLabelOption(
    override val type: HongWidgetType = HongWidgetType.LABEL
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    var label: String? = null
    var labelColorHex: String = HongColor.BLACK_100.hex
    var labelTypo: HongTypo = HongTypo.BODY_15_B

    var description: String? = null
    var descriptionColorHex: String = HongColor.BLACK_60.hex
    var descriptionTypo: HongTypo = HongTypo.CONTENTS_10


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLabelOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (label != other.label) return false
        if (labelColorHex != other.labelColorHex) return false
        if (labelTypo != other.labelTypo) return false
        if (description != other.description) return false
        if (descriptionColorHex != other.descriptionColorHex) return false
        if (descriptionTypo != other.descriptionTypo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + labelColorHex.hashCode()
        result = 31 * result + labelTypo.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + descriptionColorHex.hashCode()
        result = 31 * result + descriptionTypo.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongLabelOption2(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "label=$label, " +
                "labelColorHex='$labelColorHex', " +
                "labelTypo=$labelTypo, " +
                "description=$description, " +
                "descriptionColorHex='$descriptionColorHex', " +
                "descriptionTypo=$descriptionTypo" +
                ")"
    }


}