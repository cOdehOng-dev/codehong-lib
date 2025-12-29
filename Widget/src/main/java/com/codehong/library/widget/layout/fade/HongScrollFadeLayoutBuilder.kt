package com.codehong.library.widget.layout.fade

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo

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

    fun headerContent(headerContent: @Composable (Boolean, Float) -> Unit) = apply {
        option.headerContent = headerContent
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

    fun copy(inject: HongScrollFadeLayoutOption?): HongScrollFadeLayoutBuilder {
        if (inject == null) return HongScrollFadeLayoutBuilder()
        return HongScrollFadeLayoutBuilder()
            .backgroundColor(inject.backgroundColorHex)
            .mainContentHeightDp(inject.mainContentHeightDp)
            .headerContent(inject.headerContent)
            .mainContent(inject.mainContent)
            .subContentList(inject.subContentList)
            .bottomContent(inject.bottomContent)
    }
}