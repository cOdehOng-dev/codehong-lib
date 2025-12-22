package com.codehong.library.widget.draganddrop

import com.codehong.library.widget.HongWidgetCommonBuilder

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

    fun copy(inject: HongGridDragAndDropOption?): HongGridDragAndDropBuilder {
        if (inject == null) return HongGridDragAndDropBuilder()

        return HongGridDragAndDropBuilder()
            .padding(inject.padding)
            .backgroundColor(inject.backgroundColorHex)
            .itemList(inject.itemList)
            .onItemClick(inject.onItemClick)
            .onBackClick(inject.onBackClick)
    }
}