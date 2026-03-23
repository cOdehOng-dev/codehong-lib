package com.codehong.library.widget.button.text

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

data class HongButtonTextOption(
    override val type: HongWidgetType = HongWidgetType.BUTTON_TEXT,
) : HongWidgetCommonOption {

    companion object {
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

    var state: HongState = HongState.ENABLED
}