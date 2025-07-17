package com.codehong.lib.sample.button

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.button.HongTextButtonBuilder
import com.codehong.library.widget.button.HongTextButtonOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
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

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "버튼 옵션"
        )

        /** common */
        commonPreviewOption(
            selectWidth = { selectWidth ->
                this.previewOption = HongTextButtonBuilder()
                    .copy(previewOption)
                    .width(selectWidth)
                    .applyOption()
                executePreview()
            },
            selectHeight = { selectHeight ->
                this.previewOption = HongTextButtonBuilder()
                    .copy(previewOption)
                    .height(selectHeight)
                    .applyOption()
                executePreview()
            },
            selectMargin = { selectMargin ->
                this.previewOption = HongTextButtonBuilder()
                    .copy(previewOption)
                    .margin(selectMargin)
                    .applyOption()
                executePreview()
            },
            selectPadding = { selectPadding ->
                this.previewOption = HongTextButtonBuilder()
                    .copy(previewOption)
                    .padding(selectPadding)
                    .applyOption()
                executePreview()
            }
        )

        /** radius */
        PlaygroundManager.addRadiusOptionPreview(
            activity = activity,
            radius = previewOption.radius,
        ) { selectRadius ->
            this.previewOption = HongTextButtonBuilder()
                .copy(previewOption)
                .radius(selectRadius)
                .applyOption()
            executePreview()
        }

        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background",
            colorHex = previewOption.backgroundColorHex
        ) { selectColor ->
            this.previewOption = HongTextButtonBuilder()
                .copy(previewOption)
                .backgroundColor(selectColor)
                .applyOption()
            executePreview()
        }

        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = previewOption.border,
            despWidth = "버튼 테두리를 설정해요.",
            despColor = "버튼 테두리 색상을 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            this.previewOption = HongTextButtonBuilder()
                .copy(previewOption)
                .border(selectBorder)
                .applyOption()
            executePreview()
        }

        /** shadow */
        PlaygroundManager.addShadowOptionPreview(
            activity = activity,
            shadow = previewOption.shadow
        ) { selectShadow ->
            this.previewOption = HongTextButtonBuilder()
                .copy(previewOption)
                .shadow(selectShadow)
                .applyOption()
            executePreview()
        }

        // region 버튼 텍스트
        HongTextPlayground(activity).injectPreview(
            previewOption.textOption,
            includeCommonOption = false,
            label = "텍스트 옵션"
        ) {
            this.previewOption = HongTextButtonBuilder()
                .copy(previewOption)
                .textOption(it)
                .applyOption()
            executePreview()
        }
        // endregion
    }
}