package com.codehong.lib.sample.text.badge

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.badge.HongTextBadgeBuilder
import com.codehong.library.widget.text.badge.HongTextBadgeOption

class HongTextBadgePlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextBadgeOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextBadgeBuilder()
            .padding(
                HongSpacingInfo(
                    top = 1.5f,
                    bottom = 1.5f,
                    left = 4f,
                    right = 4f
                )
            )
            .text("모두 보라보라해해에에에")
            .textColor(HongColor.PURPLE_100)
            .textTypo(HongTypo.CONTENTS_12_B)
            .backgroundColor(HongColor.WHITE_100.hex)
            .border(
                HongBorderInfo(
                    width = 1,
                    color = HongColor.PURPLE_30.hex,
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
    override var previewOption: HongTextBadgeOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_BADGE

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
        injectOption: HongTextBadgeOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextBadgeOption) -> Unit
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
                    inject = HongTextBadgeBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextBadgeBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextBadgeBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextBadgeBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** radius */
        PlaygroundManager.addViewRadiusOption(
            activity = activity,
            radius = inject.radius,
        ) { selectRadius ->
            inject = HongTextBadgeBuilder()
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
            inject = HongTextBadgeBuilder()
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
            inject = HongTextBadgeBuilder()
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
            inject = HongTextBadgeBuilder()
                .copy(inject)
                .shadow(selectShadow)
                .applyOption()
            callback.invoke(inject)
        }

        /** text */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.text,
            label = "텍스트",
        ) {
            inject = HongTextBadgeBuilder()
                .copy(inject)
                .text(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** text color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.textColorHex,
            label = "텍스트 ",
        ) {
            inject = HongTextBadgeBuilder()
                .copy(inject)
                .textColor(it)
                .applyOption()
            callback.invoke(inject)
        }
        /** typo */
        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.textTypography,
            label = "텍스트 typo 설정"
        ) {
            inject = HongTextBadgeBuilder()
                .copy(inject)
                .textTypo(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}