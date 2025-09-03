package com.codehong.library.widget.bottomsheet.swipe

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor

class HongBottomSheetSwipeBuilder :
    HongWidgetCommonBuilder<HongBottomSheetSwipeOption, HongBottomSheetSwipeBuilder> {

    override val builder: HongBottomSheetSwipeBuilder = this
    override val option: HongBottomSheetSwipeOption = HongBottomSheetSwipeOption()


    override fun width(width: Int?) = apply {
        option.width = HongLayoutParam.MATCH_PARENT.value
    }
    override fun height(height: Int?) = apply {
        option.height = HongLayoutParam.MATCH_PARENT.value
    }

    override fun margin(margin: HongSpacingInfo) = apply {
        option.margin = HongSpacingInfo()
    }

    override fun padding(padding: HongSpacingInfo) = apply {
        option.padding = HongSpacingInfo()
    }

    fun bottomSheetMaxHeight(maxHeight: Float) = apply {
        option.bottomSheetMaxHeight = maxHeight
    }
    fun bottomSheetMinHeight(minHeight: Float) = apply {
        option.bottomSheetMinHeight = minHeight
    }

    fun bottomSheetBackgroundColor(color: HongColor) = apply {
        option.bottomSheetBackgroundColorHex = color.hex
    }
    fun bottomSheetBackgroundColor(colorHex: String) = apply {
        option.bottomSheetBackgroundColorHex = colorHex
    }

    fun bottomSheetTopRadius(radius: Int) = apply {
        option.bottomSheetTopRadius = radius
    }

    fun closeIconColor(color: HongColor) = apply {
        option.closeIconColorHex = color.hex
    }
    fun closeIconColor(colorHex: String) = apply {
        option.closeIconColorHex = colorHex
    }

    fun onClose(onClose: () -> Unit) = apply {
        option.onCloseClick = onClose
    }

    fun content(content: @Composable BoxScope.() -> Unit) = apply {
        option.content = content
    }

    fun bottomSheetContent(content: @Composable ColumnScope.() -> Unit) = apply {
        option.bottomSheetContent = content
    }

    fun copy(inject: HongBottomSheetSwipeOption?): HongBottomSheetSwipeBuilder {
        if (inject == null) return HongBottomSheetSwipeBuilder()
        return HongBottomSheetSwipeBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)
            .bottomSheetMaxHeight(inject.bottomSheetMaxHeight)
            .bottomSheetMinHeight(inject.bottomSheetMinHeight)
            .bottomSheetBackgroundColor(inject.bottomSheetBackgroundColorHex)
            .bottomSheetTopRadius(inject.bottomSheetTopRadius)
            .closeIconColor(inject.closeIconColorHex)
            .onClose(inject.onCloseClick)
            .content(inject.content)
            .bottomSheetContent(inject.bottomSheetContent)
    }
}