package com.codehong.library.widget.graph

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.graph.GraphPoint
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongGraphOption(
    override val type: HongWidgetType = HongWidgetType.GRAPH
): HongWidgetCommonOption {
    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var shadow = HongShadowInfo()
    override var useShapeCircle: Boolean = false

    override var padding: HongSpacingInfo = HongSpacingInfo(left = 20f, right = 20f, top = 20f)
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var graphHeight = 198

    var graphPointList: List<GraphPoint> = emptyList()

    var dotLineColorHex = HongColor.GRAY_30.hex
    var dotLineWidth = 1.5f

    var graphColorHex = HongColor.MAIN_ORANGE_100.hex
    var graphLineWidth = 7

    var dividerColorHex = HongColor.GRAY_20.hex
    var dividerWidth = 1

    var labelColorHex = HongColor.BLACK_100.hex
    var labelTypo = HongTypo.CONTENTS_10
}
