package com.codehong.library.widget.layout.fade

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongScrollFadeLayoutBuilder :
    HongWidgetCommonBuilder<HongScrollFadeLayoutOption, HongScrollFadeLayoutBuilder> {

    override val builder: HongScrollFadeLayoutBuilder = this
    override val option: HongScrollFadeLayoutOption = HongScrollFadeLayoutOption()

    override fun padding(padding: HongSpacingInfo) = apply {
        option.padding = HongSpacingInfo()
    }

    override fun margin(margin: HongSpacingInfo) = apply {
        option.margin = HongSpacingInfo()
    }

    override fun height(height: Int?): HongScrollFadeLayoutBuilder = apply {
        option.height = HongLayoutParam.MATCH_PARENT.value
    }

    override fun width(width: Int?): HongScrollFadeLayoutBuilder = apply {
        option.width = HongLayoutParam.MATCH_PARENT.value
    }

    override fun onClick(onClick: ((HongScrollFadeLayoutOption) -> Unit)?) = apply {
        option.click = null
    }


    fun mainContentHeightDp(heightDp: Int) = apply {
        option.mainContentHeightDp = heightDp
    }


    fun mainContent(mainContent: @Composable () -> Unit) = apply {
        option.mainContent = mainContent
    }

    fun subContentList(subContentList: (LazyListScope) -> Unit) = apply {
        option.subContentList = subContentList
    }

    fun bottomContent(bottomContent: @Composable () -> Unit) = apply {
        option.bottomContent = bottomContent
    }

    fun leftIconInfo(info: Any?) = apply {
        option.leftIconInfo = info
    }

    fun leftIconColor(color: Pair<HongColor, HongColor>?) = apply {
        val injectColor = color ?: Pair(HongColor.WHITE_100, HongColor.BLACK_100)
        leftIconColorHex(injectColor.first.hex to injectColor.second.hex)
    }

    fun leftIconColorHex(color: Pair<String, String>) = apply {
        option.leftIconColorHex = color
    }

    fun leftIconClick(onClick: () -> Unit) = apply {
        option.leftIconClick = onClick
    }

    fun rightIconInfo(info: Any?) = apply {
        option.rightIconInfo = info
    }

    fun rightIconColor(color: Pair<HongColor, HongColor>?) = apply {
        val injectColor = color ?: Pair(HongColor.WHITE_100, HongColor.BLACK_100)
        rightIconColorHex(injectColor.first.hex to injectColor.second.hex)
    }

    fun rightIconColorHex(color: Pair<String, String>) = apply {
        option.rightIconColorHex = color
    }

    fun rightIconClick(onClick: () -> Unit) = apply {
        option.rightIconClick = onClick
    }

    fun titleText(title: String) = apply {
        option.titleText = title
    }

    fun titleTypo(typo: HongTypo) = apply {
        option.titleTypo = typo
    }

    fun useTitleOverFlow(use: Boolean) = apply {
        option.useTitleOverFlow = use
    }

    fun titleColor(color: Pair<HongColor, HongColor>?) = apply {
        val injectColor = color ?: Pair(HongColor.WHITE_100, HongColor.BLACK_100)
        titleColorHex(injectColor.first.hex to injectColor.second.hex)
    }

    fun titleColorHex(color: Pair<String, String>) = apply {
        option.titleColorHex = color
    }

    fun headerBackgroundColor(color: HongColor?) = apply {
        val injectColor = color?.hex ?: HongScrollFadeLayoutOption.DEFAULT_HEADER_BACKGROUND_COLOR
        headerBackgroundColorHex(injectColor)
    }
    fun headerBackgroundColorHex(color: String) = apply {
        option.headerBackgroundColorHex = color
    }


    fun copy(inject: HongScrollFadeLayoutOption?): HongScrollFadeLayoutBuilder {
        if (inject == null) return HongScrollFadeLayoutBuilder()

        return HongScrollFadeLayoutBuilder()
            .backgroundColor(inject.backgroundColorHex)
            .mainContentHeightDp(inject.mainContentHeightDp)
            .mainContent(inject.mainContent)
            .subContentList(inject.subContentList)
            .bottomContent(inject.bottomContent)
            .leftIconInfo(inject.leftIconInfo)
            .leftIconColorHex(inject.leftIconColorHex)
            .leftIconClick(inject.leftIconClick)
            .rightIconInfo(inject.rightIconInfo)
            .rightIconColorHex(inject.rightIconColorHex)
            .rightIconClick(inject.rightIconClick)
            .titleText(inject.titleText)
            .titleTypo(inject.titleTypo)
            .useTitleOverFlow(inject.useTitleOverFlow)
            .titleColorHex(inject.titleColorHex)
            .headerBackgroundColorHex(inject.headerBackgroundColorHex)
    }
}