package com.codehong.lib.sample.button.text

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class HongTextButtonPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextButtonOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextButtonBuilder()
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
            .textOption(
                HongTextBuilder()
                    .text("검색하기")
                    .size(15)
                    .fontType(HongFont.PRETENDARD_700)
                    .color(HongColor.WHITE_100)
                    .maxLines(1)
                    .applyOption()
            )
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
    override var previewOption: HongTextButtonOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_BUTTON

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
        injectOption: HongTextButtonOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        labelTypo: HongTypo? = null,
        description: String = "",
        descriptionTypo: HongTypo? = null,
        useRadius: Boolean = true,
        useShadow: Boolean = true,
        useBackgroundColor: Boolean = true,
        useBorder: Boolean = true,
        callback: (HongTextButtonOption) -> Unit
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
                    inject = HongTextButtonBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextButtonBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextButtonBuilder()
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
                inject = HongTextButtonBuilder()
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
                inject = HongTextButtonBuilder()
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
                inject = HongTextButtonBuilder()
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
                inject = HongTextButtonBuilder()
                    .copy(inject)
                    .shadow(selectShadow)
                    .applyOption()
                callback.invoke(inject)
            }
        }

        // region 버튼 텍스트
        HongTextPlayground(activity).injectPreview(
            inject.textOption,
            includeCommonOption = false,
            label = "텍스트 옵션",
            labelTypo = if (label.isNotEmpty()) HongTypo.BODY_15_B else null,
            useUnderline = false,
            useCancelLine = false,
            useAlign = false
        ) {
            inject = HongTextButtonBuilder()
                .copy(inject)
                .textOption(it)
                .applyOption()
            callback.invoke(inject)
        }
        // endregion

        
    }
}