package com.codehong.library.widget

import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor

interface HongWidgetCommonOption {

    @Deprecated("")
    var isValidComponent: Boolean

    val type: HongWidgetType
    var width: Int
    var height: Int
    var margin: HongSpacingInfo
    var padding: HongSpacingInfo

    // TODO HONG ? nullable 처리해서 구조 수정
    var backgroundColor: HongColor
    var backgroundColorHex: String
    var click: ((HongWidgetCommonOption) -> Unit)?
}
