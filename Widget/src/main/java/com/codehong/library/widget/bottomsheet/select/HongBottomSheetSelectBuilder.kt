package com.codehong.library.widget.bottomsheet.select

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongBottomSheetSelectBuilder : HongWidgetCommonBuilder<HongBottomSheetSelectOption, HongBottomSheetSelectBuilder> {

    override val builder: HongBottomSheetSelectBuilder = this
    override val option: HongBottomSheetSelectOption = HongBottomSheetSelectOption()


    fun topRadius(radius: Int) = apply {
        option.topRadius = radius
    }

    fun dimColor(color: String) = apply {
        option.dimColorHex = color
    }

    fun dimColor(color: HongColor) = apply {
        option.dimColorHex = color.hex
    }

    fun dragHandleColor(color: HongColor) = apply {
        option.dragHandleColorHex = color.hex
    }
    fun dragHandleColor(color: String) = apply {
        option.dragHandleColorHex = color
    }

    fun title(title: String) = apply {
        option.title = title
    }

    fun titleTypo(typo: HongTypo) = apply {
        option.titleTypo = typo
    }

    fun titleColor(color: HongColor) = apply {
        option.titleColorHex = color.hex
    }
    fun titleColor(color: String) = apply {
        option.titleColorHex = color
    }

    fun selectionBackgroundColor(color: HongColor) = apply {
        option.selectionBackgroundColorHex = color.hex
    }
    fun selectionBackgroundColor(color: String) = apply {
        option.selectionBackgroundColorHex = color
    }

    fun selectionRadius(radius: HongRadiusInfo) = apply {
        option.selectionRadius = radius
    }

    fun selectionSelectorBorder(border: HongBorderInfo) = apply {
        option.selectionSelectorBorder = border
    }

    fun selectionTitleTypo(typo: HongTypo) = apply {
        option.selectionTitleTypo = typo
    }

    fun selectionTitleColor(color: HongColor) = apply {
        option.selectionTitleColorHex = color.hex
    }

    fun selectionTitleColor(color: String) = apply {
        option.selectionTitleColorHex = color
    }

    fun selectionSubtitleTypo(typo: HongTypo) = apply {
        option.selectionSubtitleTypo = typo
    }
    fun selectionSubtitleColor(color: HongColor) = apply {
        option.selectionSubtitleColorHex = color.hex
    }
    fun selectionSubtitleColor(color: String) = apply {
        option.selectionSubtitleColorHex = color
    }

    fun initialSelection(initialOption: Pair<String, String>) = apply {
        option.initialSelection = initialOption
    }

    fun selectionList(optionList: List<Pair<String, String>>) = apply {
        option.selectionList = optionList
    }

    fun selectSelectionCallback(callback: (Pair<String, String>) -> Unit) = apply {
        option.selectSelectionCallback = callback
    }

    fun onChangeVisibleState(callback: (Boolean) -> Unit) = apply {
        option.onChangeVisibleState = callback
    }

    fun copy(inject: HongBottomSheetSelectOption?): HongBottomSheetSelectBuilder {
        if (inject == null) return HongBottomSheetSelectBuilder()

        return HongBottomSheetSelectBuilder()
            .topRadius(inject.topRadius)
            .dimColor(inject.dimColorHex)
            .dragHandleColor(inject.dragHandleColorHex)
            .title(inject.title)
            .titleTypo(inject.titleTypo)
            .titleColor(inject.titleColorHex)
            .selectionBackgroundColor(inject.selectionBackgroundColorHex)
            .selectionRadius(inject.selectionRadius)
            .selectionSelectorBorder(inject.selectionSelectorBorder)
            .selectionTitleTypo(inject.selectionTitleTypo)
            .selectionTitleColor(inject.selectionTitleColorHex)
            .selectionSubtitleTypo(inject.selectionSubtitleTypo)
            .selectionSubtitleColor(inject.selectionSubtitleColorHex)
            .initialSelection(inject.initialSelection)
            .selectionList(inject.selectionList)
            .selectSelectionCallback(inject.selectSelectionCallback)
            .onChangeVisibleState(inject.onChangeVisibleState)
    }
}