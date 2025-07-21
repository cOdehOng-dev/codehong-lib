package com.codehong.library.widget.textfield.number

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.R
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontType
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder

data class HongNumberTextFieldOption(
    override val type: HongWidgetType = HongWidgetType.NUMBER_TEXT_FIELD
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_PLACEHOLDER_TYPO = HongTypo.BODY_16
        val DEFAULT_PLACEHOLDER_FONT = DEFAULT_PLACEHOLDER_TYPO.fontType().font

        val DEFAULT_INPUT_TYPO = HongTypo.BODY_16_B
        val DEFAULT_INPUT_FONT = DEFAULT_INPUT_TYPO.fontType().font
        val DEFAULT_INPUT_SIZE = DEFAULT_INPUT_TYPO.size()
        val DEFAULT_INPUT_COLOR = HongColor.BLACK_100.hex

        val DEFAULT_KEYBOARD_OPTION = Pair(
            HongKeyboardType.NUMBER,
            HongKeyboardActionType.DONE
        )

        val DEFAULT_PLACEHOLDER = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(DEFAULT_PLACEHOLDER_TYPO)
            .color(HongColor.BLACK_30.hex)
            .applyOption()

        val DEFAULT_INPUT = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(DEFAULT_INPUT_TYPO)
            .color(DEFAULT_INPUT_COLOR)
            .applyOption()

        val DEFAULT_CLEAR_IMAGE = HongImageBuilder()
            .width(20)
            .height(20)
            .margin(
                HongSpacingInfo(
                    left = 8f
                )
            )
            .drawableResId(R.drawable.honglib_ic_20_close)
            .applyOption()
    }


    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null


    override var backgroundColorHex: String = HongColor.BLACK_5.hex

    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    var placeholderTextOption = DEFAULT_PLACEHOLDER
    var inputTextOption = DEFAULT_INPUT
    var clearImageOption: HongImageOption? = null

    var input: String? = null
    var placeholder: String? = null

    var cursorColor: String = HongColor.MAIN_ORANGE_100.hex
    var useHideKeyboard: Boolean = true

    var keyboardOption = DEFAULT_KEYBOARD_OPTION

    var onTextChanged: (String) -> Unit = {}

}