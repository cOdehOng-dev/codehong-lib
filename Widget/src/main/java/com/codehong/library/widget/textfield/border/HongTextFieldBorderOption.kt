package com.codehong.library.widget.textfield.border

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTextFieldBorderOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_FIELD_BORDER
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var useShapeCircle: Boolean = false
    override var shadow: HongShadowInfo = HongShadowInfo()

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()


    var inputRadius = HongRadiusInfo(
        topLeft = 12,
        topRight = 12,
        bottomLeft = 12,
        bottomRight = 12
    )

    // border color
    var enableBorderColorHex: String = HongColor.GRAY_20.hex
    var focusedBorderColorHex: String = HongColor.BLACK_80.hex

    // background
    var inputBackgroundColorHex: String = HongColor.WHITE_100.hex

    var label: String = ""
    var labelColoHex: String = HongColor.BLACK_100.hex
    var labelTypo: HongTypo = HongTypo.CONTENTS_12

    var initialInput: String = ""
    var inputTextColorHex: String = HongColor.BLACK_100.hex

    var placeholder: String = ""
    var placeholderColorHex: String = HongColor.GRAY_50.hex
    var placeholderTypo: HongTypo = HongTypo.BODY_16

    var helperText: String = ""
    var helperTextTypo: HongTypo = HongTypo.CONTENTS_10

    var isRequired = false

    var state: HongInputState = HongInputState.ENABLE


    var suffix: String = ""
    var suffixTypo = HongTypo.BODY_16

    var useClearButton = true

    var useNumberKeypad: Boolean = false

    var onChangeInput: (String) -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextFieldBorderOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (inputRadius != other.inputRadius) return false
        if (enableBorderColorHex != other.enableBorderColorHex) return false
        if (focusedBorderColorHex != other.focusedBorderColorHex) return false
        if (inputBackgroundColorHex != other.inputBackgroundColorHex) return false
        if (label != other.label) return false
        if (labelColoHex != other.labelColoHex) return false
        if (labelTypo != other.labelTypo) return false
        if (initialInput != other.initialInput) return false
        if (inputTextColorHex != other.inputTextColorHex) return false
        if (placeholder != other.placeholder) return false
        if (placeholderColorHex != other.placeholderColorHex) return false
        if (placeholderTypo != other.placeholderTypo) return false
        if (helperText != other.helperText) return false
        if (helperTextTypo != other.helperTextTypo) return false
        if (isRequired != other.isRequired) return false
        if (state != other.state) return false
        if (suffix != other.suffix) return false
        if (suffixTypo != other.suffixTypo) return false
        if (useClearButton != other.useClearButton) return false
        if (useNumberKeypad != other.useNumberKeypad) return false
        if (onChangeInput != other.onChangeInput) return false

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
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + inputRadius.hashCode()
        result = 31 * result + enableBorderColorHex.hashCode()
        result = 31 * result + focusedBorderColorHex.hashCode()
        result = 31 * result + inputBackgroundColorHex.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + labelColoHex.hashCode()
        result = 31 * result + labelTypo.hashCode()
        result = 31 * result + initialInput.hashCode()
        result = 31 * result + inputTextColorHex.hashCode()
        result = 31 * result + placeholder.hashCode()
        result = 31 * result + placeholderColorHex.hashCode()
        result = 31 * result + placeholderTypo.hashCode()
        result = 31 * result + helperText.hashCode()
        result = 31 * result + helperTextTypo.hashCode()
        result = 31 * result + isRequired.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + suffix.hashCode()
        result = 31 * result + suffixTypo.hashCode()
        result = 31 * result + useClearButton.hashCode()
        result = 31 * result + useNumberKeypad.hashCode()
        result = 31 * result + onChangeInput.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongTextFieldBorderOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "radius=$radius, " +
                "border=$border, " +
                "inputRadius=$inputRadius, " +
                "enableBorderColorHex='$enableBorderColorHex', " +
                "focusedBorderColorHex='$focusedBorderColorHex', " +
                "inputBackgroundColorHex='$inputBackgroundColorHex', " +
                "label='$label', " +
                "labelColoHex='$labelColoHex', " +
                "labelTypo=$labelTypo, " +
                "initialInput='$initialInput', " +
                "inputTextColorHex='$inputTextColorHex', " +
                "placeholder='$placeholder', " +
                "placeholderColorHex='$placeholderColorHex', " +
                "placeholderTypo=$placeholderTypo, " +
                "helperText='$helperText', " +
                "helperTextTypo=$helperTextTypo, " +
                "isRequired=$isRequired, " +
                "state=$state, " +
                "suffix='$suffix', " +
                "suffixTypo=$suffixTypo, " +
                "useClearButton=$useClearButton, " +
                "useNumberKeypad=$useNumberKeypad, " +
                "onChangeInput=$onChangeInput" +
                ")"
    }


}
