package com.codehong.lib.sample.text.count

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.rule.HongCountType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.text.count.HongTextCountBuilder
import com.codehong.library.widget.text.count.HongTextCountOption

class HongTextCountPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextCountOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextCountBuilder()
            .padding(
                HongSpacingInfo(
                    top = 20f,
                    left = 20f
                )
            )
            .startCount(0.0)
            .maxCount(5.2)
            .amount(1.2)
            .unitText("킬로미터")
            .countType(HongCountType.DOUBLE)
            .onCountChange {

            }
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextCountOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_COUNT

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
        injectOption: HongTextCountOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextCountOption) -> Unit
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
                    inject = HongTextCountBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextCountBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "카운트 텍스트 ",
            colorHex = inject.countColorHex
        ) {
            inject = HongTextCountBuilder()
                .copy(inject)
                .countColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.countTypo,
            label = "카운트 typo",
        ) {
            inject = HongTextCountBuilder()
                .copy(inject)
                .countTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.unitText,
            label = "단위 텍스트",
        ) {
            inject = HongTextCountBuilder()
                .copy(inject)
                .unitText(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "단위 텍스트 ",
            colorHex = inject.unitColorHex
        ) {
            inject = HongTextCountBuilder()
                .copy(inject)
                .unitColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.unitTypo,
            label = "단위 typo",
        ) {
            inject = HongTextCountBuilder()
                .copy(inject)
                .unitTypo(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}