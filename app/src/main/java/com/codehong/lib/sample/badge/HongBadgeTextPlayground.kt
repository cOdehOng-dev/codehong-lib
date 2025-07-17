package com.codehong.lib.sample.badge

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.badge.HongBadgeTextBuilder
import com.codehong.library.widget.badge.HongBadgeTextOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class HongBadgeTextPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongBadgeTextOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongBadgeTextBuilder()
            .padding(
                HongSpacingInfo(
                    top = 1.5f,
                    bottom = 1.5f,
                    left = 4f,
                    right = 4f
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("모두 보라보라해해에에에")
                    .color("#8e43e7")
                    .typography(HongTypo.CONTENTS_12_B)
                    .applyOption()
            )
            .backgroundColor(HongColor.WHITE_100.hex)
            .border(
                HongBorderInfo(
                    width = 1,
                    color = "#dfb4fc",
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 4,
                    topRight = 4,
                    bottomLeft = 4,
                    bottomRight = 4
                )
            )
            .applyOption()
    }


    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongBadgeTextOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.BADGE_TEXT

    fun preview() {
        executePreview()

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "뱃지 옵션"
        )

        /** common */
        commonPreviewOption(
            selectWidth = { selectWidth ->
                this.previewOption = HongBadgeTextBuilder()
                    .copy(previewOption)
                    .width(selectWidth)
                    .applyOption()
                executePreview()
            },
            selectHeight = { selectHeight ->
                this.previewOption = HongBadgeTextBuilder()
                    .copy(previewOption)
                    .height(selectHeight)
                    .applyOption()
                executePreview()
            },
            selectMargin = { selectMargin ->
                this.previewOption = HongBadgeTextBuilder()
                    .copy(previewOption)
                    .margin(selectMargin)
                    .applyOption()
                executePreview()
            },
            selectPadding = { selectPadding ->
                this.previewOption = HongBadgeTextBuilder()
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
            this.previewOption = HongBadgeTextBuilder()
                .copy(previewOption)
                .radius(selectRadius)
                .applyOption()
            executePreview()
        }

        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background",
            colorHex = previewOption.backgroundColorHex,
            useTopPadding = true
        ) { selectColor ->
            this.previewOption = HongBadgeTextBuilder()
                .copy(previewOption)
                .backgroundColor(selectColor)
                .applyOption()
            executePreview()
        }

        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = previewOption.border,
            despWidth = "badge 테두리를 설정해요.",
            despColor = "badge 테두리 색상을 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            this.previewOption = HongBadgeTextBuilder()
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
            this.previewOption = HongBadgeTextBuilder()
                .copy(previewOption)
                .shadow(selectShadow)
                .applyOption()
            executePreview()
        }

        // region 버튼 텍스트
        HongTextPlayground(activity).injectPreview(
            injectOption = previewOption.textOption,
            includeCommonOption = true,
            label = "텍스트 옵션"
        ) {
            this.previewOption = HongBadgeTextBuilder()
                .copy(previewOption)
                .textOption(it)
                .applyOption()
            executePreview()
        }

    }

}