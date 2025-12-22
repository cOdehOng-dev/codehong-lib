package com.codehong.library.widget.draganddrop

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongSpacingInfo

class HongGridDragAndDropBuilder : HongWidgetCommonBuilder<HongGridDragAndDropOption, HongGridDragAndDropBuilder> {

    override val builder: HongGridDragAndDropBuilder = this
    override val option: HongGridDragAndDropOption = HongGridDragAndDropOption()


    fun onItemClick(onItemClick: () -> Unit) = apply {
        option.onItemClick = onItemClick
    }

    fun onBackClick(onBackClick: () -> Unit) = apply {
        option.onBackClick = onBackClick
    }

    fun itemList(itemList: List<Any>) = apply {
        option.itemList = itemList
    }

    fun inboundColorHex(colorHex: String) = apply {
        option.inboundColorHex = colorHex
    }

    fun gridColumns(columns: Int) = apply {
        option.gridColumns = columns
    }

    fun gridHorizontalSpacing(spacing: Int) = apply {
        option.gridHorizontalSpacing = spacing
    }

    fun gridVerticalSpacing(spacing: Int) = apply {
        option.gridVerticalSpacing = spacing
    }

    fun gridContentPadding(padding: HongSpacingInfo) = apply {
        option.gridContentPadding = padding
    }

    fun copy(inject: HongGridDragAndDropOption?): HongGridDragAndDropBuilder {
        if (inject == null) return HongGridDragAndDropBuilder()

        return HongGridDragAndDropBuilder()
            .padding(inject.padding)
            .backgroundColor(inject.backgroundColorHex)
            .itemList(inject.itemList)
            .inboundColorHex(inject.inboundColorHex)
            .onItemClick(inject.onItemClick)
            .onBackClick(inject.onBackClick)
            .gridColumns(inject.gridColumns)
            .gridHorizontalSpacing(inject.gridHorizontalSpacing)
            .gridVerticalSpacing(inject.gridVerticalSpacing)
            .gridContentPadding(inject.gridContentPadding)
    }
}