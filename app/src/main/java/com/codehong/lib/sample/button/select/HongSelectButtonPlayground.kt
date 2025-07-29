package com.codehong.lib.sample.button.select

import android.util.Log
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.button.select.HongSelectButtonBuilder
import com.codehong.library.widget.button.select.HongSelectButtonOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo

class HongSelectButtonPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongSelectButtonOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongSelectButtonBuilder()
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f,
                )
            )
            .onNegativeClick {
                Log.d("TAG", "취소 버튼!!")
            }
            .onPositiveClick {
                Log.d("TAG", "확인 버튼!!")
            }
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongSelectButtonOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.BUTTON_SELECT

    fun preview() {
        executePreview()

        injectPreview(
            injectOption = previewOption,
            includeCommonOption = true,
        ) {
            previewOption = it
            executePreview()
        }
    }

    fun injectPreview(
        injectOption: HongSelectButtonOption,
        includeCommonOption: Boolean = false,
        label: String? = null,
        callback: (HongSelectButtonOption) -> Unit
    ) {
        var inject = injectOption
        if (!label.isNullOrEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        if (includeCommonOption) {
            commonPreviewOption(
                height = inject.height,
                padding = inject.padding,
                margin = inject.margin,
                useWidth = false,
                selectHeight = { selectHeight ->
                    inject = HongSelectButtonBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongSelectButtonBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongSelectButtonBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }


        PlaygroundManager.addOptionTitleView(
            activity,
            label = "취소 버튼 설정",
            labelTypo = HongTypo.BODY_16_B
        )
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.negativeText,
            label = "버튼 텍스트",
        ) {
            inject = HongSelectButtonBuilder()
                .copy(inject)
                .negativeText(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.negativeTextTypo,
            label = "버튼 텍스트 폰트",
        ) {
            inject = HongSelectButtonBuilder()
                .copy(inject)
                .negativeTextTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "버튼 텍스트 ",
            colorHex = inject.negativeTextColorHex
        ) {
            inject = HongSelectButtonBuilder()
                .copy(inject)
                .negativeTextColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        PlaygroundManager.addOptionTitleView(
            activity,
            label = "확인 버튼 설정",
            labelTypo = HongTypo.BODY_16_B
        )
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.positiveText,
            label = "버튼 텍스트",
        ) {
            inject = HongSelectButtonBuilder()
                .copy(inject)
                .positiveText(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.positiveTextTypo,
            label = "버튼 텍스트 폰트",
        ) {
            inject = HongSelectButtonBuilder()
                .copy(inject)
                .positiveTextTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "버튼 텍스트 ",
            colorHex = inject.positiveTextColorHex
        ) {
            inject = HongSelectButtonBuilder()
                .copy(inject)
                .positiveTextColor(it)
                .applyOption()
            callback.invoke(inject)
        }

//        HongTextButtonPlayground(activity)
//            .injectPreview(
//                injectOption = previewOption.negativeTextButtonOption,
//                includeCommonOption = false,
//                label = "취소 버튼 옵션",
//                useShadow = false
//            ) {
//                inject = HongSelectButtonBuilder()
//                    .copy(inject)
//                    .negativeTextButtonOption(it)
//                    .applyOption()
//                callback.invoke(inject)
//            }
//
//        HongTextButtonPlayground(activity)
//            .injectPreview(
//                injectOption = previewOption.positiveTextButtonOption,
//                includeCommonOption = false,
//                label = "확인 버튼 옵션",
//                useShadow = false
//            ) {
//                inject = HongSelectButtonBuilder()
//                    .copy(inject)
//                    .positiveTextButtonOption(it)
//                    .applyOption()
//                callback.invoke(inject)
//            }
    }
}