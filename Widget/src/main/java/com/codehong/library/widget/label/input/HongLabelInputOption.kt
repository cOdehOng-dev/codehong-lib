package com.codehong.library.widget.label.input

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongLabelInputOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_INPUT
)  : HongWidgetCommonOption {

    companion object {
        val DEFAULT_KEYBOARD_OPTION = Pair(
            HongKeyboardType.TEXT,
            HongKeyboardActionType.DONE
        )
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var useShapeCircle: Boolean = false
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var label: String? = null
    var labelColorHex: String = HongColor.BLACK_100.hex
    var labelTypo: HongTypo = HongTypo.BODY_15_B

    var description: String? = null
    var descriptionColorHex: String = HongColor.BLACK_60.hex
    var descriptionTypo: HongTypo = HongTypo.CONTENTS_10


    var placeholder: String? = null
    var placeholderTypo: HongTypo = HongTypo.BODY_16
    var placeholderColorHex: String = HongColor.BLACK_30.hex

    var input: String? = null
    var inputTypo: HongTypo = HongTypo.BODY_14
    var inputColorHex: String = HongColor.BLACK_100.hex

    var clearIconRes: Int? = R.drawable.honglib_ic_20_circle_close_fill
    var clearIconSize: Int = 18
    var clearIconScaleType: HongScaleType = HongScaleType.CENTER_CROP
    var clearIconMargin: HongSpacingInfo = HongSpacingInfo(left = 8f)

    var keyboardOption = DEFAULT_KEYBOARD_OPTION

    var onTextChanged: (String) -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLabelInputOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (radius != other.radius) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (label != other.label) return false
        if (labelColorHex != other.labelColorHex) return false
        if (labelTypo != other.labelTypo) return false
        if (description != other.description) return false
        if (descriptionColorHex != other.descriptionColorHex) return false
        if (descriptionTypo != other.descriptionTypo) return false
        if (placeholder != other.placeholder) return false
        if (placeholderTypo != other.placeholderTypo) return false
        if (placeholderColorHex != other.placeholderColorHex) return false
        if (input != other.input) return false
        if (inputTypo != other.inputTypo) return false
        if (inputColorHex != other.inputColorHex) return false
        if (clearIconRes != other.clearIconRes) return false
        if (clearIconSize != other.clearIconSize) return false
        if (clearIconScaleType != other.clearIconScaleType) return false
        if (clearIconMargin != other.clearIconMargin) return false
        if (keyboardOption != other.keyboardOption) return false
        if (onTextChanged != other.onTextChanged) return false

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
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + labelColorHex.hashCode()
        result = 31 * result + labelTypo.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + descriptionColorHex.hashCode()
        result = 31 * result + descriptionTypo.hashCode()
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + placeholderTypo.hashCode()
        result = 31 * result + placeholderColorHex.hashCode()
        result = 31 * result + (input?.hashCode() ?: 0)
        result = 31 * result + inputTypo.hashCode()
        result = 31 * result + inputColorHex.hashCode()
        result = 31 * result + (clearIconRes ?: 0)
        result = 31 * result + clearIconSize
        result = 31 * result + clearIconScaleType.hashCode()
        result = 31 * result + clearIconMargin.hashCode()
        result = 31 * result + keyboardOption.hashCode()
        result = 31 * result + onTextChanged.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongLabelInputOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "radius=$radius, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "label=$label, " +
                "labelColorHex='$labelColorHex', " +
                "labelTypo=$labelTypo, " +
                "description=$description, " +
                "descriptionColorHex='$descriptionColorHex', " +
                "descriptionTypo=$descriptionTypo, " +
                "placeholder=$placeholder, " +
                "placeholderTypo=$placeholderTypo, " +
                "placeholderColorHex='$placeholderColorHex', " +
                "input=$input, " +
                "inputTypo=$inputTypo, " +
                "inputColorHex='$inputColorHex', " +
                "clearIconRes=$clearIconRes, " +
                "clearIconSize=$clearIconSize, " +
                "clearIconScaleType=$clearIconScaleType, " +
                "clearIconMargin=$clearIconMargin, " +
                "keyboardOption=$keyboardOption, " +
                "onTextChanged=$onTextChanged" +
                ")"
    }


}
