package com.codehong.lib.sample.label.checkbox

import com.codehong.lib.sample.checkbox.HongCheckboxPlayground
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxBuilder
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxOption
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo

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

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "label 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.label,
            label = "label 텍스트",
        ) {
            inject = HongLabelCheckboxBuilder()
                .copy(inject)
                .label(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.labelColorHex,
            label = "label ",
        ) {
            inject = HongLabelCheckboxBuilder()
                .copy(inject)
                .labelColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.labelTypo,
            label = "label 폰트",
        ) {
            inject = HongLabelCheckboxBuilder()
                .copy(inject)
                .labelTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "description 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.label,
            label = "description 텍스트",
        ) {
            inject = HongLabelCheckboxBuilder()
                .copy(inject)
                .description(it)
                .applyOption()
            callback.invoke(inject)
        }


        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.labelColorHex,
            label = "description ",
        ) {
            inject = HongLabelCheckboxBuilder()
                .copy(inject)
                .descriptionColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.labelTypo,
            label = "description 폰트",
        ) {
            inject = HongLabelCheckboxBuilder()
                .copy(inject)
                .descriptionTypo(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}