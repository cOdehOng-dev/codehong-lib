package com.codehong.library.widget.button.icon

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongIconButtonOption(
    override val type: HongWidgetType = HongWidgetType.BUTTON_ICON
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var shadow = HongShadowInfo()


    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var border: HongBorderInfo = HongBorderInfo(
        width = 1,
        color = HongColor.GRAY_40.hex
    )
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var useShapeCircle: Boolean = true
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    var buttonType: HongButtonIconType = HongButtonIconType.SIZE_28
    var iconColorHex: String = HongColor.BLACK_100.hex
    var state: HongClickState = HongClickState.ENABLE
    var iconResId: Int = 0
}