package com.codehong.library.widget.button.text2

import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

data class HongTextButtonOption2(
    override val type: HongWidgetType = HongWidgetType.BUTTON_TEXT,
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_TEXT_TYPO = HongTypo.BODY_16_B
        val DEFAULT_TEXT_COLOR = HongColor.WHITE_100.hex

        const val DEFAULT_USE_SHAPE_CIRCLE = false

        val DEFAULT_TEXT_OPTION = HongTextBuilder()
            .typography(DEFAULT_TEXT_TYPO)
            .color(DEFAULT_TEXT_COLOR)
            .applyOption()

        val DEFAULT_DISABLE_TEXT_OPTION = HongTextBuilder()
            .typography(HongTypo.BODY_15_B)
            .color(HongColor.WHITE_60.hex)
            .applyOption()

        val DEFAULT_DISABLE_BACKGROUND_COLOR = HongColor.GRAY_70
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()

    var text: String = ""
    var textTypo: HongTypo = HongTypo.BODY_16_B
    var textColorHex: String = HongColor.WHITE_100.hex

//    var textOption = DEFAULT_TEXT_OPTION

    var state: HongState = HongState.ENABLED



}