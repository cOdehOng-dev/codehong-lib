package com.codehong.library.widget.graph

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.graph.GraphPoint
import com.codehong.library.widget.rule.typo.HongTypo

class HongGraphBuilder : HongWidgetCommonBuilder<HongGraphOption, HongGraphBuilder> {

    override val builder: HongGraphBuilder = this
    override val option: HongGraphOption = HongGraphOption()

    fun graphHeight(graphHeight: Int) = apply {
        option.graphHeight = graphHeight
    }

    fun graphPointList(graphPointList: List<GraphPoint>) = apply {
        option.graphPointList = graphPointList
    }

    fun dotLineColor(dotLineColor: HongColor) = apply {
        option.dotLineColorHex = dotLineColor.hex
    }

    fun dotLineColor(dotLineColorHex: String) = apply {
        option.dotLineColorHex = dotLineColorHex
    }

    fun dotLineWidth(dotLineWidth: Float) = apply {
        option.dotLineWidth = dotLineWidth
    }

    fun graphColor(graphColor: HongColor) = apply {
        option.graphColorHex = graphColor.hex
    }

    fun graphColor(graphColorHex: String) = apply {
        option.graphColorHex = graphColorHex
    }

    fun graphLineWidth(graphLineWidth: Int) = apply {
        option.graphLineWidth = graphLineWidth
    }

    fun dividerColor(dividerColor: HongColor) = apply {
        option.dividerColorHex = dividerColor.hex
    }

    fun dividerColor(dividerColorHex: String) = apply {
        option.dividerColorHex = dividerColorHex
    }

    fun dividerWidth(dividerWidth: Int) = apply {
        option.dividerWidth = dividerWidth
    }

    fun labelColor(labelColor: HongColor) = apply {
        option.labelColorHex = labelColor.hex
    }

    fun labelColor(labelColorHex: String) = apply {
        option.labelColorHex = labelColorHex
    }

    fun labelTypo(labelTypo: HongTypo) = apply {
        option.labelTypo = labelTypo
    }

    fun copy(inject: HongGraphOption?): HongGraphBuilder {
        if (inject == null) return HongGraphBuilder()
        return HongGraphBuilder()
            .width(inject.width)
            .padding(inject.padding)
            .backgroundColor(inject.backgroundColorHex)
            .graphHeight(inject.graphHeight)
            .graphLineWidth(inject.graphLineWidth)
            .graphPointList(inject.graphPointList)
            .dotLineColor(inject.dotLineColorHex)
            .dotLineWidth(inject.dotLineWidth)
            .graphColor(inject.graphColorHex)
            .dividerColor(inject.dividerColorHex)
            .dividerWidth(inject.dividerWidth)
            .labelColor(inject.labelColorHex)
            .labelTypo(inject.labelTypo)

    }
}