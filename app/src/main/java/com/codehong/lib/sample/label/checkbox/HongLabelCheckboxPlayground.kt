package com.codehong.lib.sample.label.checkbox

import com.codehong.lib.sample.checkbox.HongCheckboxPlayground
import com.codehong.lib.sample.label.HongLabelPlayground
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxBuilder
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxOption
import com.codehong.library.widget.rule.HongWidgetType

class HongLabelCheckboxPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongLabelCheckboxOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongLabelCheckboxBuilder()
            .label("테스트")
            .description("테스트 입니다요")
            .checkboxSize(24)
            .checkState(true)
            .applyOption()
    }


    override val activity: PlaygroundActivity = playgroundActivity
    override val widgetType = HongWidgetType.LABEL_CHECKBOX
    override var previewOption: HongLabelCheckboxOption = DEFAULT_PREVIEW_OPTION

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
        injectOption: HongLabelCheckboxOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongLabelCheckboxOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                padding = inject.padding,
                selectWidth = { selectWidth ->
                    inject = HongLabelCheckboxBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongLabelCheckboxBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongLabelCheckboxBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongLabelCheckboxBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        HongCheckboxPlayground(activity)
            .injectPreview(
                injectOption = inject.checkboxOption,
                includeCommonOption = true,
                label = "체크박스 설정"
            ) {
                inject = HongLabelCheckboxBuilder()
                    .copy(inject)
                    .checkboxOption(it)
                    .applyOption()
                callback.invoke(inject)
            }

        HongLabelPlayground(activity)
            .injectPreview(
                injectOption = inject.labelOption,
                includeCommonOption = true,
                label = "라벨 설정"
            ) {
                inject = HongLabelCheckboxBuilder()
                    .copy(inject)
                    .label(null)
                    .description(null)
                    .labelOption(it)
                    .applyOption()
                callback.invoke(inject)
            }
    }
}