package com.codehong.library.widget

import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

interface HongWidgetCommonOption {
    val type: HongWidgetType
    var isValidComponent: Boolean
    var width: Int
    var height: Int
    var margin: HongSpacingInfo
    var padding: HongSpacingInfo
    var click: ((HongWidgetCommonOption) -> Unit)?

    var backgroundColor: HongColor
    var backgroundColorHex: String

    var radius: HongRadiusInfo
    var border: HongBorderInfo
    var shadow: HongShadowInfo
    var useShapeCircle: Boolean
}
