package com.codehong.library.widget

import com.codehong.library.widget.rule.HongWidgetType

interface HongWidgetCommonOption {
    val type: HongWidgetType
    var isValidComponent: Boolean
    var width: Int
    var height: Int
    var margin: HongSpacingInfo
    var padding: HongSpacingInfo
    var click: (HongWidgetCommonOption) -> Unit
}
