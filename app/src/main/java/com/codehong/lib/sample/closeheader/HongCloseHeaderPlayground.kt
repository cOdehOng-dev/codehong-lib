package com.codehong.lib.sample.closeheader

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.header.HongHeaderCloseBuilder
import com.codehong.library.widget.header.HongHeaderCloseOption
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongCloseHeaderPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongHeaderCloseOption> {

    companion object {
        val DEFAULT_PREVIEW_OPTION = HongHeaderCloseBuilder()
            .title("헤더 제목")
            .titleTypo(HongTypo.BODY_16_B)
            .titleColor(HongColor.BLACK_100.hex)
            .close {}
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongHeaderCloseOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.HEADER_CLOSE

    fun preview() {
        executePreview()

        /** 배경색 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background",
            colorHex = previewOption.backgroundColorHex
        ) { colorHex ->
            previewOption = HongHeaderCloseBuilder()
                .copy(previewOption)
                .backgroundColor(colorHex)
                .applyOption()
            executePreview()
        }

        /** 헤더 제목 */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            input = previewOption.title,
            label = "헤더 제목"
        ) { text ->
            previewOption = HongHeaderCloseBuilder()
                .copy(previewOption)
                .title(text)
                .applyOption()
            executePreview()
        }


        /** 텍스트 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "text ",
            colorHex = previewOption.titleColorHex
        ) { selectHongColor ->
            this.previewOption = HongHeaderCloseBuilder()
                .copy(previewOption)
                .titleColor(selectHongColor)
                .applyOption()
            executePreview()
        }

        /** 텍스트 타이포그라피 */
        val initialTypography = PlaygroundManager.typographyList
            .firstOrNull { it == previewOption.titleTypo } ?: HongTypo.BODY_14_B
        PlaygroundManager.addViewSelectOption(
            activity = activity,
            initialText = initialTypography.styleName,
            selectList = PlaygroundManager.typoNameList,
            selectedPosition = PlaygroundManager.typographyList.indexOf(initialTypography),
            label = "typo 설정",
            description = "헤더 제목의 타이포그라피를 설정해요.",
            useDirectCallback = true,
        ) { selectTypography, i ->
            val typography = PlaygroundManager.typographyList
                .firstOrNull { it.styleName == selectTypography }
                ?: HongTypo.BODY_16_B
            previewOption = HongHeaderCloseBuilder()
                .copy(previewOption)
                .titleTypo(typography)
                .applyOption()
            executePreview()
        }
    }
}