package com.codehong.library.widget

import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

interface HongWidgetAdvanceOption : HongWidgetCommonOption {
    override val type: HongWidgetType
    override var isValidComponent: Boolean
    override var width: Int
    override var height: Int
    override var margin: HongSpacingInfo
    override var padding: HongSpacingInfo
    override var click: ((HongWidgetCommonOption) -> Unit)?

    override var backgroundColor: HongColor
    override var backgroundColorHex: String

    var radius: HongRadiusInfo
    var border: HongBorderInfo
    var shadow: HongShadowInfo
    var useShapeCircle: Boolean
}
