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

}
