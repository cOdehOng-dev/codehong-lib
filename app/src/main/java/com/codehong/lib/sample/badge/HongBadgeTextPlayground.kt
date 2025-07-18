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

        injectPreview(
            injectOption = previewOption,
            includeCommonOption = true
        ) {
            previewOption = it
            executePreview()
        }
    }

    fun injectPreview(
        injectOption: HongBadgeTextOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongBadgeTextOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "뱃지 옵션",
            labelTypo = if (label.isNotEmpty()) HongTypo.BODY_15_B else null
        )

        /** common */
        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                padding = inject.padding,
                selectWidth = { selectWidth ->
                    inject = HongBadgeTextBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongBadgeTextBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongBadgeTextBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongBadgeTextBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** radius */
        PlaygroundManager.addRadiusOptionPreview(
            activity = activity,
            radius = inject.radius,
        ) { selectRadius ->
            inject = HongBadgeTextBuilder()
                .copy(inject)
                .radius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background ",
            colorHex = inject.backgroundColorHex,
        ) { selectColor ->
            inject = HongBadgeTextBuilder()
                .copy(inject)
                .backgroundColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }

        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.border,
            despWidth = "badge 테두리를 설정해요.",
            despColor = "badge 테두리 색상을 설정해요.",
        ) { selectBorder ->
            inject = HongBadgeTextBuilder()
                .copy(inject)
                .border(selectBorder)
                .applyOption()
            callback.invoke(inject)
        }

        /** shadow */
        PlaygroundManager.addShadowOptionPreview(
            activity = activity,
            shadow = inject.shadow
        ) { selectShadow ->
            inject = HongBadgeTextBuilder()
                .copy(inject)
                .shadow(selectShadow)
                .applyOption()
            callback.invoke(inject)
        }

        // region 버튼 텍스트
        HongTextPlayground(activity).injectPreview(
            injectOption = inject.textOption,
            includeCommonOption = true,
            label = "텍스트 옵션",
            labelTypo = if (label.isNotEmpty()) HongTypo.BODY_15_B else null
        ) {
            inject = HongBadgeTextBuilder()
                .copy(inject)
                .textOption(it)
                .applyOption()
            callback.invoke(inject)
        }

    }
}