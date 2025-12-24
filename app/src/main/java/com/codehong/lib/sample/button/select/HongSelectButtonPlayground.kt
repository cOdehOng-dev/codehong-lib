package com.codehong.lib.sample.button.select

import android.util.Log
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.button.select.HongButtonSelectBuilder
import com.codehong.library.widget.button.select.HongButtonSelectOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo

class HongSelectButtonPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongButtonSelectOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongButtonSelectBuilder()
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
    override var previewOption: HongButtonSelectOption = DEFAULT_PREVIEW_OPTION
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
        injectOption: HongButtonSelectOption,
        includeCommonOption: Boolean = false,
        label: String? = null,
        callback: (HongButtonSelectOption) -> Unit
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
                    inject = HongButtonSelectBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongButtonSelectBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongButtonSelectBuilder()
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
            inject = HongButtonSelectBuilder()
                .copy(inject)
                .negativeText(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.negativeTextTypo,
            label = "버튼 텍스트 폰트",
        ) {
            inject = HongButtonSelectBuilder()
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
            inject = HongButtonSelectBuilder()
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
            inject = HongButtonSelectBuilder()
                .copy(inject)
                .positiveText(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.positiveTextTypo,
            label = "버튼 텍스트 폰트",
        ) {
            inject = HongButtonSelectBuilder()
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
            inject = HongButtonSelectBuilder()
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