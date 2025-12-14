package com.codehong.lib.sample.tab.flow

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.playground.preview.HorizontalOptionView
import com.codehong.library.widget.extensions.toFigureInt
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

        if (includeCommonOption) {
            commonPreviewOption(
                margin = inject.margin,
                padding = inject.padding,
                useWidth = false,
                useHeight = false,
                selectMargin = { selectMargin ->
                    inject = HongTabFlowBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTabFlowBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "row 최대 갯수",
            description = "row 값이 없을 경우 1로 설정해요",
            input = inject.maxRowCount.toString(),
            useOnlyNumber = true
        ) {
            inject = HongTabFlowBuilder()
                .copy(inject)
                .maxRowCount(
                    if (it.toFigureInt() == 0) 1 else it.toFigureInt()
                )
                .applyOption()
            callback.invoke(inject)
        }

        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.labelInputPreview(
                activity,
                label = "tab 좌우 간의 간격",
                input = inject.rowSpacing.toString(),
                useOnlyNumber = true
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .rowSpacing(it.toFigureInt())
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.labelInputPreview(
                activity,
                label = "tab 상하 간의 간격",
                input = inject.betweenTabSpacing.toString(),
                useOnlyNumber = true
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .betweenTabSpacing(it.toFigureInt())
                    .applyOption()
                callback.invoke(inject)
            }
        ).also {
            activity.addOptionView(it)
        }

        PlaygroundManager.addViewRadiusOption(
            activity = activity,
            label = "tab radius",
            radius = inject.tabRadius,
        ) { selectRadius ->
            inject = HongTabFlowBuilder()
                .copy(inject)
                .tabRadius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "활성화 탭 ",
                colorHex = inject.selectBackgroundColorHex,
                useTopPadding = true
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .selectBackgroundColor(it)
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "비활성화 탭 ",
                colorHex = inject.unselectTabBackgroundColorHex,
                useTopPadding = true
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .unselectTabBackgroundColor(it)
                    .applyOption()
                callback.invoke(inject)
            }
        ).also {
            activity.addOptionView(it)
        }

        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.selectedBorder,
            label = "활성화 탭 테두리",
            despWidth = "활성화 탭 테두리를 설정해요.",
            despColor = "활성화 탭 테두리 색상을 설정해요.",
        ) {
            inject = HongTabFlowBuilder()
                .copy(inject)
                .selectedBorder(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.unselectedBorder,
            label = "비활성화 탭 테두리",
            despWidth = "비활성화 탭 테두리를 설정해요.",
            despColor = "비활성화 탭 테두리 색상을 설정해요.",
        ) {
            inject = HongTabFlowBuilder()
                .copy(inject)
                .unselectedBorder(it)
                .applyOption()
            callback.invoke(inject)
        }

        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "활성화 탭 텍스트 ",
                colorHex = inject.selectTextColorHex,
                useTopPadding = true
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .selectTextColor(it)
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "비활성화 탭 텍스트 ",
                colorHex = inject.unselectTextColorHex,
                useTopPadding = true
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .unselectTextColor(it)
                    .applyOption()
                callback.invoke(inject)
            }
        ).also {
            activity.addOptionView(it)
        }

        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.getSelectTypoOptionView(
                activity,
                typo = inject.selectTextTypo,
                label = "활성화 탭 텍스트 typo",
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .selectTextTypo(it)
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.getSelectTypoOptionView(
                activity,
                typo = inject.unselectTextTypo,
                label = "비활성화 탭 텍스트 typo",
            ) {
                inject = HongTabFlowBuilder()
                    .copy(inject)
                    .unselectTextTypo(it)
                    .applyOption()
                callback.invoke(inject)
            },
        )
    }

}