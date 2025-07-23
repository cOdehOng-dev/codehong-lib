package com.codehong.library.widget.textfield.timer

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
import com.codehong.library.widget.text.HongTextOption

data class HongTimerTextFieldOption(
    override val type: HongWidgetType = HongWidgetType.TIMER_TEXT_FIELD
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_USE_HIDE_KEYBOARD = true

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
            .typography(HongTypo.BODY_16)
            .color(HongColor.BLACK_30)
            .applyOption()

        val DEFAULT_INPUT = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(HongTypo.BODY_16_B)
            .color(HongColor.BLACK_100)
            .applyOption()

        val DEFAULT_CLEAR_IMAGE = HongImageBuilder()
            .width(20)
            .height(20)
            .margin(
                HongSpacingInfo(
                    left = 8f,
                    right = 5f
                )
            )
            .drawableResId(R.drawable.honglib_ic_close)
            .applyOption()

        val DEFAULT_COUNT_DOWN_TEXT = HongTextBuilder()
            .width(HongLayoutParam.WRAP_CONTENT.value)
            .height(HongLayoutParam.WRAP_CONTENT.value)
            .typography(HongTypo.BODY_14)
            .color(HongColor.GRAY_50.hex)
            .applyOption()
    }


    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    var clearImageOption: HongImageOption? = null

    var input: String? = null
    var placeholder: String? = null
    var placeholderTextOption = DEFAULT_PLACEHOLDER
    var inputTextOption = DEFAULT_INPUT

    var cursorColor: HongColor = HongColor.MAIN_ORANGE_100
    var cursorColorHex: String = HongColor.MAIN_ORANGE_100.hex
    var useHideKeyboard: Boolean = DEFAULT_USE_HIDE_KEYBOARD

    var keyboardOption = DEFAULT_KEYBOARD_OPTION

    var onTextChanged: (String) -> Unit = {}

    var underlineFocusColor: HongColor = HongColor.MAIN_ORANGE_100
    var underlineFocusColorHex: String = HongColor.MAIN_ORANGE_100.hex

    var underlineOutFocusColor: HongColor = HongColor.GRAY_20
    var underlineOutFocusColorHex: String = HongColor.GRAY_20.hex

    var underlineFinishColor: HongColor? = null
    var underlineFinishColorHex: String? = null

    var underlineHeight = 2

    var countDownTextOption: HongTextOption = DEFAULT_COUNT_DOWN_TEXT
    var min: Int = 0
    var sec: Int = 0

    var onFinish: (() -> Unit)? = null



}