package com.codehong.lib.sample.label

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

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
            .labelTextOption(
                HongTextBuilder()
                    .copy(HongLabelOption.DEFAULT_LABEL_OPTION)
                    .text("text align")
                    .applyOption()
            )
            .descriptionTextOption(
                HongTextBuilder()
                    .copy(HongLabelOption.DEFAULT_DESCRIPTION_OPTION)
                    .text("width가 MATCH_PARENT인 경우, textAlign이 적용되지 않습니다.")
                    .applyOption()
            )
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

        HongTextPlayground(activity).injectPreview(
            injectOption = inject.labelTextOption,
            includeCommonOption = true,
            label = "Label",
            labelTypo = HongTypo.BODY_16
        ) {
            inject = HongLabelBuilder()
                .copy(inject)
                .labelTextOption(it)
                .applyOption()
            callback.invoke(inject)
        }

        HongTextPlayground(activity).injectPreview(
            injectOption = inject.descriptionTextOption,
            includeCommonOption = true,
            label = "Description",
            labelTypo = HongTypo.BODY_16
        ) {
            inject = HongLabelBuilder()
                .copy(inject)
                .descriptionTextOption(it)
                .applyOption()
            callback.invoke(inject)
        }
    }

}