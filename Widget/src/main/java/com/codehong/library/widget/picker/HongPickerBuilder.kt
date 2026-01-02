package com.codehong.library.widget.picker

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongPickerBuilder : HongWidgetCommonBuilder<HongPickerOption, HongPickerBuilder> {

    override val builder: HongPickerBuilder = this
    override val option: HongPickerOption = HongPickerOption()

    fun title(title: String) = apply {
        option.title = title
    }
    fun titleColor(titleColor: HongColor) = apply {
        option.titleColorHex = titleColor.hex
    }
    fun titleColor(titleColorHex: String) = apply {
        option.titleColorHex = titleColorHex
    }
    fun buttonText(buttonText: String) = apply {
        option.buttonText = buttonText
    }
    fun initialFirstOption(initialFirstOption: Int) = apply {
        option.initialFirstOption = initialFirstOption
    }
    fun firstOptionList(firstOptionList: List<String>) = apply {
        option.firstOptionList = firstOptionList
    }

    fun initialSecondOption(initialSecondOption: Int) = apply {
        option.initialSecondOption = initialSecondOption
    }
    fun secondOptionList(secondOptionList: List<String>?) = apply {
        option.secondOptionList = secondOptionList
    }

    fun useDimClickClose(useDimClickClose: Boolean) = apply {
        option.useDimClickCLose = useDimClickClose
    }

    fun selectorColor(selectorColor: HongColor) = apply {
        option.selectorColorHex = selectorColor.hex
    }
    fun selectorColor(selectorColorHex: String) = apply {
        option.selectorColorHex = selectorColorHex
    }

    fun radius(
        topLeft: Int,
        topRight: Int,
    ) = apply {
        option.radius = HongRadiusInfo(
            topLeft = topLeft,
            topRight = topRight
        )
    }

    fun onDismiss(onDismiss: () -> Unit) = apply {
        option.onDismiss = onDismiss
    }

    fun onConfirm(
        onConfirm: ((selectedFirstOption: Pair<Int, String>, selectedSecondOption: Pair<Int, String?>) -> Unit)?
    ) = apply {
        option.onConfirm = onConfirm
    }

    fun onDirectSelect(
        onDirectSelect: ((selectedFirstOption: Pair<Int, String>, selectedSecondOption: Pair<Int, String?>) -> Unit)?
    ) = apply {
        option.onDirectSelect = onDirectSelect
    }


    fun copy(inject: HongPickerOption?): HongPickerBuilder {
        if (inject == null) return HongPickerBuilder()

        return HongPickerBuilder()
            .backgroundColor(inject.backgroundColorHex)
            .radius(inject.radius.topLeft, inject.radius.topRight)
            .title(inject.title)
            .titleColor(inject.titleColorHex)
            .buttonText(inject.buttonText)
            .initialFirstOption(inject.initialFirstOption)
            .firstOptionList(inject.firstOptionList)
            .initialSecondOption(inject.initialSecondOption)
            .secondOptionList(inject.secondOptionList)
            .useDimClickClose(inject.useDimClickCLose)
            .selectorColor(inject.selectorColorHex)
            .onDismiss(inject.onDismiss)
            .onConfirm(inject.onConfirm)
            .onDirectSelect(inject.onDirectSelect)
    }

}