package com.codehong.lib.sample.label.toggle

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.toggleswitch.HongSwitchPlayground
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchBuilder
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder

class HongLabelSwitchPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongLabelSwitchOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongLabelSwitchBuilder()
            .padding(
                HongSpacingInfo(
                    top = 20f,
                    bottom = 20f,
                    left = 16f,
                    right = 16f
                )
            )
            .label("테스트")
            .description("테스트 입니다요")
            .switchOption(
                HongSwitchBuilder()
                    .width(55)
                    .height(30)
                    .onColor(HongColor.MAIN_ORANGE_100)
                    .offColor(HongColor.GRAY_20)
                    .cursorSize(25)
                    .cursorHorizontalMargin(3)
                    .cursorColor(HongColor.WHITE_100)
                    .initialState(true)
                    .switchClick { _, isEnable -> }
                    .applyOption()
            )
            .applyOption()
    }


    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongLabelSwitchOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.LABEL_SWITCH

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
        injectOption: HongLabelSwitchOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongLabelSwitchOption) -> Unit
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
                margin = inject.margin,
                padding = inject.padding,
                useWidth = false,
                useHeight = false,
                selectWidth = { selectWidth ->
                    inject = HongLabelSwitchBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongLabelSwitchBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongLabelSwitchBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongLabelSwitchBuilder()
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
            inject = HongLabelSwitchBuilder()
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
            inject = HongLabelSwitchBuilder()
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
            inject = HongLabelSwitchBuilder()
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
            inject = HongLabelSwitchBuilder()
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
            inject = HongLabelSwitchBuilder()
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
            inject = HongLabelSwitchBuilder()
                .copy(inject)
                .descriptionTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        HongSwitchPlayground(activity)
            .injectPreview(
                injectOption = inject.switchOption,
                includeCommonOption = true,
                label = "Switch 옵션"
            ) {
                inject = HongLabelSwitchBuilder()
                    .copy(inject)
                    .switchOption(it)
                    .applyOption()
                callback.invoke(inject)
            }
    }
}