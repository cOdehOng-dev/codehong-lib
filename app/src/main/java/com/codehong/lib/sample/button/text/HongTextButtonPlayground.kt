package com.codehong.lib.sample.button.text

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongTextButtonPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongButtonTextOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongButtonTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .padding(
                HongSpacingInfo(
                    top = 15f,
                    bottom = 15f
                )
            )
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f,
                    bottom = 10f
                )
            )
            .text("검색하기")
            .textTypo(HongTypo.BODY_15_B)
            .textColor(HongColor.WHITE_100)
            .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
            .radius(
                HongRadiusInfo(
                    topLeft = 12,
                    topRight = 12,
                    bottomLeft = 12,
                    bottomRight = 12
                )
            )
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongButtonTextOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.BUTTON_TEXT

    fun preview() {
        executePreview()

        injectPreview(
            injectOption = previewOption,
            includeCommonOption = true,
        ) {
            this.previewOption = it
            executePreview()
        }
    }
    
    fun injectPreview(
        injectOption: HongButtonTextOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        labelTypo: HongTypo? = null,
        description: String = "",
        descriptionTypo: HongTypo? = null,
        useRadius: Boolean = true,
        useShadow: Boolean = true,
        useBackgroundColor: Boolean = true,
        useBorder: Boolean = true,
        callback: (HongButtonTextOption) -> Unit
    ) {
        var inject = injectOption
        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label,
                labelTypo = labelTypo,
                description = description,
                descriptionTypo = descriptionTypo
            )
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "버튼 옵션",
            labelTypo = if (label.isNotEmpty()) HongTypo.BODY_15_B else null
        )

        if (includeCommonOption) {
            /** common */
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                usePadding = false,
                selectWidth = { selectWidth ->
                    inject = HongButtonTextBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongButtonTextBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongButtonTextBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        if (useRadius) {
            /** radius */
            PlaygroundManager.addRadiusOptionPreview(
                activity = activity,
                radius = inject.radius,
            ) { selectRadius ->
                inject = HongButtonTextBuilder()
                    .copy(inject)
                    .radius(selectRadius)
                    .applyOption()
                callback.invoke(inject)
            }

        }

        if (useBackgroundColor) {
            /** background color */
            PlaygroundManager.addColorOptionPreview(
                activity = activity,
                label = "background ",
                colorHex = inject.backgroundColorHex
            ) { selectColor ->
                inject = HongButtonTextBuilder()
                    .copy(inject)
                    .backgroundColor(selectColor)
                    .applyOption()
                callback.invoke(inject)
            }
        }

        if (useBorder) {
            /** border */
            PlaygroundManager.addBorderOptionPreview(
                activity = activity,
                border = inject.border,
                despWidth = "버튼 테두리를 설정해요.",
                despColor = "버튼 테두리 색상을 설정해요.",
                useTopPadding = true
            ) { selectBorder ->
                inject = HongButtonTextBuilder()
                    .copy(inject)
                    .border(selectBorder)
                    .applyOption()
                callback.invoke(inject)
            }
        }

        if (useShadow) {
            /** shadow */
            PlaygroundManager.addShadowOptionPreview(
                activity = activity,
                shadow = inject.shadow
            ) { selectShadow ->
                inject = HongButtonTextBuilder()
                    .copy(inject)
                    .shadow(selectShadow)
                    .applyOption()
                callback.invoke(inject)
            }
        }

        // region 버튼 텍스트
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.text,
            label = "버튼 텍스트",
        ) {
            inject = HongButtonTextBuilder()
                .copy(inject)
                .text(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.textTypo,
            label = "버튼 텍스트 폰트",
        ) {
            inject = HongButtonTextBuilder()
                .copy(inject)
                .textTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "버튼 텍스트 ",
            colorHex = inject.textColorHex
        ) {
            inject = HongButtonTextBuilder()
                .copy(inject)
                .textColor(it)
                .applyOption()
            callback.invoke(inject)
        }

//        HongTextPlayground(activity).injectPreview(
//            inject.textOption,
//            includeCommonOption = false,
//            label = "텍스트 옵션",
//            labelTypo = if (label.isNotEmpty()) HongTypo.BODY_15_B else null,
//            useUnderline = false,
//            useCancelLine = false,
//            useAlign = false
//        ) {
//            inject = HongTextButtonBuilder2()
//                .copy(inject)
//                .textOption(it)
//                .applyOption()
//            callback.invoke(inject)
//        }
        // endregion

        
    }
}