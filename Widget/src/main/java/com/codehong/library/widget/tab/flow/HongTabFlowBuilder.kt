package com.codehong.library.widget.tab.flow

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongTabFlowBuilder : HongWidgetCommonBuilder<HongTabFlowOption, HongTabFlowBuilder> {

    override val builder: HongTabFlowBuilder = this
    override val option: HongTabFlowOption = HongTabFlowOption()

    fun tabList(tabList: List<String>) = apply {
        option.tabList = tabList
    }

    fun initialSelectedIndex(initialSelectedIndex: Int) = apply {
        option.initialSelectedIndex = initialSelectedIndex
    }

    fun maxRowCount(maxRowCount: Int) = apply {
        option.maxRowCount = maxRowCount
    }



    fun betweenTabSpacing(betweenTabSpacing: Int) = apply {
        option.betweenTabSpacing = betweenTabSpacing
    }

    fun rowSpacing(rowSpacing: Int) = apply {
        option.rowSpacing = rowSpacing
    }

    fun tabRadius(tabRadius: HongRadiusInfo) = apply {
        option.tabRadius = tabRadius
    }

    fun selectBackgroundColor(selectBackgroundColor: HongColor) = apply {
        option.selectBackgroundColorHex = selectBackgroundColor.hex
    }
    fun selectBackgroundColor(selectBackgroundColorHex: String) = apply {
        option.selectBackgroundColorHex = selectBackgroundColorHex
    }

    fun unselectTabBackgroundColor(unselectTabBackgroundColor: HongColor) = apply {
        option.unselectTabBackgroundColorHex = unselectTabBackgroundColor.hex
    }
    fun unselectTabBackgroundColorHex(unselectTabBackgroundColorHex: String) = apply {
        option.unselectTabBackgroundColorHex = unselectTabBackgroundColorHex
    }

    fun selectedBorder(selectedBorder: HongBorderInfo) = apply {
        option.selectedBorder = selectedBorder
    }

    fun unselectedBorder(unselectedBorder: HongBorderInfo) = apply {
        option.unselectedBorder = unselectedBorder
    }

    fun selectTextColor(selectTextColor: HongColor) = apply {
        option.selectTextColorHex = selectTextColor.hex
    }
    fun selectTextColor(selectTextColorHex: String) = apply {
        option.selectTextColorHex = selectTextColorHex
    }

    fun unselectTextColor(unselectTextColor: HongColor) = apply {
        option.unselectTextColorHex = unselectTextColor.hex
    }
    fun unselectTextColor(unselectTextColorHex: String) = apply {
        option.unselectTextColorHex = unselectTextColorHex
    }

    fun selectTextTypo(selectTextTypo: HongTypo) = apply {
        option.selectTextTypo = selectTextTypo
    }

    fun unselectTextTypo(unselectTextTypo: HongTypo) = apply {
        option.unselectTextTypo = unselectTextTypo
    }

    fun onSelect(onSelect: (Int) -> Unit) = apply {
        option.onSelect = onSelect
    }

    fun copy(inject: HongTabFlowOption?): HongTabFlowBuilder {
        if (inject == null) return HongTabFlowBuilder()

        return HongTabFlowBuilder()
            .tabList(inject.tabList)
            .initialSelectedIndex(inject.initialSelectedIndex)
            .maxRowCount(inject.maxRowCount)
            .betweenTabSpacing(inject.betweenTabSpacing)
            .rowSpacing(inject.rowSpacing)
            .tabRadius(inject.tabRadius)
            .selectBackgroundColor(inject.selectBackgroundColorHex)
            .unselectTabBackgroundColorHex(inject.unselectTabBackgroundColorHex)
            .selectedBorder(inject.selectedBorder)
            .unselectedBorder(inject.unselectedBorder)
            .selectTextColor(inject.selectTextColorHex)
            .unselectTextColor(inject.unselectTextColorHex)
            .selectTextTypo(inject.selectTextTypo)
            .unselectTextTypo(inject.unselectTextTypo)
            .onSelect(inject.onSelect ?: {})
    }

}