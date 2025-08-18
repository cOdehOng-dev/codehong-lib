package com.codehong.lib.sample.label

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo

class HongLabelPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongLabelOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongLabelBuilder()
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    bottom = 20f
                )
            )
            .label("레이블")
            .description("레이블 설명하는 테스트입니다.")
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override val widgetType = HongWidgetType.LABEL
    override var previewOption: HongLabelOption = DEFAULT_PREVIEW_OPTION

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
        injectOption: HongLabelOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongLabelOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        /** common */
        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                padding = inject.padding,
                selectWidth = { selectWidth ->
                    inject = HongLabelBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongLabelBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongLabelBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongLabelBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
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
            inject = HongLabelBuilder()
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
            inject = HongLabelBuilder()
                .copy(inject)
                .labelColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.labelTypo,
            label = "label 폰트",
        ) {
            inject = HongLabelBuilder()
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
            inject = HongLabelBuilder()
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
            inject = HongLabelBuilder()
                .copy(inject)
                .descriptionColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.labelTypo,
            label = "description 폰트",
        ) {
            inject = HongLabelBuilder()
                .copy(inject)
                .descriptionTypo(it)
                .applyOption()
            callback.invoke(inject)
        }
    }

}