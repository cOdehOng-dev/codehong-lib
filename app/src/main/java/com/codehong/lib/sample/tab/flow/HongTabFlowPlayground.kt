package com.codehong.lib.sample.tab.flow

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.tab.flow.HongTabFlowBuilder
import com.codehong.library.widget.tab.flow.HongTabFlowOption

class HongTabFlowPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTabFlowOption> {
    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTabFlowBuilder()
            .margin(
                HongSpacingInfo(
                    top = 20f
                )
            )
            .tabList(
                listOf(
                    "거리", "시간", "스피드",
                    "칼로리", "걸음수",
                )
            )
            .maxRowCount(3)
            .betweenTabSpacing(10)
            .rowSpacing(10)
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTabFlowOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TAB_FLOW

    fun preview() {
        executePreview()

        injectPreview(
            injectOption = previewOption,
            includeCommonOption = true
        ) {
            previewOption = it
            executePreview()
        }
    }

    fun injectPreview(
        injectOption: HongTabFlowOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTabFlowOption) -> Unit
    ) {

        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label
            )
        }
    }
}