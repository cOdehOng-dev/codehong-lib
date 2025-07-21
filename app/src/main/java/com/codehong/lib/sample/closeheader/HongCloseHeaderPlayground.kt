package com.codehong.lib.sample.closeheader

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.header.HongCloseHeaderBuilder
import com.codehong.library.widget.header.HongCloseHeaderOption
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class HongCloseHeaderPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongCloseHeaderOption> {

    companion object {
        val DEFAULT_PREVIEW_OPTION = HongCloseHeaderBuilder()
            .headerTitleTextOption(
                HongTextBuilder()
                    .text("헤더 제목")
                    .typography(HongTypo.BODY_16_B)
                    .color(HongColor.BLACK_100.hex)
                    .applyOption()
            )
            .close {

            }
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongCloseHeaderOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.CLOSE_HEADER

    fun preview() {
        executePreview()

        /** 배경색 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background",
            colorHex = previewOption.backgroundColorHex
        ) { colorHex ->
            previewOption = HongCloseHeaderBuilder()
                .copy(previewOption)
                .backgroundColor(colorHex)
                .applyOption()
            executePreview()
        }

        /** 헤더 제목 */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            input = previewOption.headerTitleTextOption.text,
            label = "헤더 제목"
        ) { text ->
            previewOption = HongCloseHeaderBuilder()
                .copy(previewOption)
                .headerTitleTextOption(
                    HongTextBuilder()
                        .copy(previewOption.headerTitleTextOption)
                        .text(text)
                        .applyOption()
                )
                .applyOption()
            executePreview()
        }


        /** 텍스트 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "text ",
            colorHex = previewOption.headerTitleTextOption.colorHex
        ) { selectHongColor ->
            this.previewOption = HongCloseHeaderBuilder()
                .copy(previewOption)
                .headerTitleTextOption(
                    HongTextBuilder()
                        .copy(previewOption.headerTitleTextOption)
                        .color(selectHongColor)
                        .applyOption()
                )
                .applyOption()
            executePreview()
        }

        /** 텍스트 타이포그라피 */
        val initialTypography = PlaygroundManager.typographyList.firstOrNull {
            it == previewOption.headerTitleTextOption.typography
        } ?: HongTypo.BODY_14_B
        PlaygroundManager.addSelectOptionView(
            activity = activity,
            initialText = initialTypography.styleName,
            selectList = PlaygroundManager.typographyNameList,
            selectedPosition = PlaygroundManager.typographyList.indexOf(initialTypography),
            label = "typo 설정",
            description = "헤더 제목의 타이포그라피를 설정해요.",
            useDirectCallback = true,
        ) { selectTypography, i ->
            val typography = PlaygroundManager.typographyList
                .firstOrNull { it.styleName == selectTypography }
                ?: HongTypo.BODY_16_B
            previewOption = HongCloseHeaderBuilder()
                .copy(previewOption)
                .headerTitleTextOption(
                    HongTextBuilder()
                        .copy(previewOption.headerTitleTextOption)
                        .typography(typography)
                        .applyOption()
                )
                .applyOption()
            executePreview()
        }
    }
}