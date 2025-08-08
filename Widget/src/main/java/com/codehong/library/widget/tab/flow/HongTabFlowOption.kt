package com.codehong.library.widget.tab.flow

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTabFlowOption(
    override val type: HongWidgetType = HongWidgetType.TAB_FLOW
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()



    var tabList: List<String> = emptyList()
    var initialSelectedIndex: Int = 0
    var maxRowCount: Int = 3


    var betweenTabSpacing: Int = 10
    var rowSpacing: Int = 10

    var tabRadius = HongRadiusInfo(
        topLeft = 18,
        topRight = 18,
        bottomLeft = 18,
        bottomRight = 18
    )

    var selectBackgroundColorHex = HongColor.WHITE_100.hex
    var unselectTabBackgroundColorHex = HongColor.WHITE_100.hex

    var selectedBorder = HongBorderInfo(
        width = 2,
        color = HongColor.BLACK_80.hex
    )
    var unselectedBorder = HongBorderInfo(
        width = 1,
        color = HongColor.GRAY_30.hex
    )



    var selectTextColorHex = HongColor.BLACK_100.hex
    var unselectTextColorHex = HongColor.BLACK_100.hex

    var selectTextTypo = HongTypo.BODY_16_B
    var unselectTextTypo = HongTypo.BODY_16

    var onSelect: ((Int) -> Unit)? = null
}