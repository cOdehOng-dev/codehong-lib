package com.codehong.lib.sample.graph

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.playground.preview.HorizontalOptionView
import com.codehong.library.widget.extensions.toFigureFloat
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.graph.HongGraphBuilder
import com.codehong.library.widget.graph.HongGraphOption
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.graph.GraphPoint

class HongGraphBarPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongGraphOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongGraphBuilder()
            .graphPointList(
                listOf(
                    GraphPoint("Jan", 20.0),
                    GraphPoint("Feb", 45.0),
                    GraphPoint("Mar", 30.0),
                    GraphPoint("Apr", 60.0),
                    GraphPoint("May", 50.0),
                    GraphPoint("Jun", 80.0)
                )
            )
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongGraphOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.GRAPH_BAR


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
        injectOption: HongGraphOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongGraphOption) -> Unit
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
                    inject = HongGraphBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongGraphBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "점선 ",
                colorHex = inject.dotLineColorHex,
                useTopPadding = true
            ) {
                inject = HongGraphBuilder()
                    .copy(inject)
                    .dotLineColor(it)
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.labelInputPreview(
                activity,
                input = inject.dotLineWidth.toFigureString(),
                label = "점선 width",
                useOnlyNumber = true,
                useTopPadding = true
            ) {
                inject = HongGraphBuilder()
                    .copy(inject)
                    .dotLineWidth(it.toFigureFloat())
                    .applyOption()
                callback.invoke(inject)
            }
        ).also {
            activity.addOptionView(it)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "그래프 ",
            colorHex = inject.graphColorHex
        ) {
            inject = HongGraphBuilder()
                .copy(inject)
                .graphColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "수평 선 ",
                colorHex = inject.dividerColorHex,
                useTopPadding = true
            ) {
                inject = HongGraphBuilder()
                    .copy(inject)
                    .dividerColor(it)
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.labelInputPreview(
                activity,
                input = inject.dividerWidth.toFigureString(),
                label = "수평 선 width",
                useOnlyNumber = true,
                useTopPadding = true
            ) {
                inject = HongGraphBuilder()
                    .copy(inject)
                    .dividerWidth(it.toFigureInt())
                    .applyOption()
                callback.invoke(inject)
            }
        ).also {
            activity.addOptionView(it)
        }


        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.selectColorOptionView(
                activity,
                label = "그래프 텍스트 ",
                colorHex = inject.labelColorHex,
                useTopPadding = true
            ) {
                inject = HongGraphBuilder()
                    .copy(inject)
                    .labelColor(it)
                    .applyOption()
                callback.invoke(inject)
            },
            rightOptionView = PlaygroundManager.getSelectTypoOptionView(
                activity,
                typo = inject.labelTypo,
                label = "그래프 텍스트 typo",
            ) {
                inject = HongGraphBuilder()
                    .copy(inject)
                    .labelTypo(it)
                    .applyOption()
                callback.invoke(inject)
            },
        )
    }
}