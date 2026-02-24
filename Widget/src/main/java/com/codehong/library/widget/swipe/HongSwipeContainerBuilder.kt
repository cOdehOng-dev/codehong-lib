package com.codehong.library.widget.swipe

import androidx.compose.runtime.Composable
import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongSwipeContainerBuilder :
    HongWidgetCommonBuilder<HongSwipeContainerOption, HongSwipeContainerBuilder> {

    override val builder: HongSwipeContainerBuilder = this
    override val option: HongSwipeContainerOption = HongSwipeContainerOption()

    fun buttonColor(buttonColor: String) = apply {
        option.buttonColorHex = buttonColor
    }

    fun buttonColor(buttonColor: HongColor) = apply {
        option.buttonColorHex = buttonColor.hex
    }

    fun buttonText(buttonText: String) = apply {
        option.buttonText = buttonText
    }

    fun buttonTextColor(buttonTextColorHex: String) = apply {
        option.buttonTextColorHex = buttonTextColorHex
    }

    fun buttonTextColor(buttonTextColor: HongColor) = apply {
        option.buttonTextColorHex = buttonTextColor.hex
    }

    fun buttonTextTypo(buttonTextTypo: HongTypo) = apply {
        option.buttonTextTypo = buttonTextTypo
    }

    fun onClickButton(onClickButton: () -> Unit) = apply {
        option.onClickButton = onClickButton
    }

    fun content(content: @Composable () -> Unit) = apply {
        option.content = content
    }

    fun copy(inject: HongSwipeContainerOption?) : HongSwipeContainerBuilder {
        if (inject == null) return HongSwipeContainerBuilder()

        return HongSwipeContainerBuilder()
            .buttonColor(inject.buttonColorHex)
            .buttonText(inject.buttonText)
            .buttonTextColor(inject.buttonTextColorHex)
            .buttonTextTypo(inject.buttonTextTypo)
            .onClickButton(inject.onClickButton)
            .content(inject.content)
            .onClick(inject.click)
    }
}