package com.codehong.library.widget.draganddrop

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongGridDragAndDropOption(
    override val type: HongWidgetType = HongWidgetType.GRID_DRAG_AND_DROP

) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    override var border: HongBorderInfo = HongBorderInfo()
    override var shadow = HongShadowInfo()

    override var useShapeCircle: Boolean = false

    var onItemClick: () -> Unit = {}
    var onBackClick: () -> Unit = {}

    var itemList: List<Any> = emptyList()
}