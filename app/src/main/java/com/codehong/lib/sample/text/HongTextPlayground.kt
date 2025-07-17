package com.codehong.lib.sample.text

import android.util.Log
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextAlign.Companion.toHongTextAlign
import com.codehong.library.widget.rule.HongTextAlign.Companion.toHongTextAlignToAlias
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.HongTextLineBreak.Companion.toHongTextLineBreak
import com.codehong.library.widget.rule.HongTextLineBreak.Companion.toHongTextLineBreakToAlias
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.HongTextOverflow.Companion.toHongTextOverFlow
import com.codehong.library.widget.rule.HongTextOverflow.Companion.toHongTextOverflowToAlias
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongTextPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextOption> {
    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextBuilder()
            .text("최애의 아이돌 에스파 윈터와 전국 빵집 털었습니다 | 빵순이 브이로그")
            .width(150)
            .typography(HongTypo.BODY_16_B)
            .color(HongColor.BLACK_100)
            .isEnableUnderLine(true)
            .lineBreak(HongTextLineBreak.DEFAULT)
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT

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
        injectOption: HongTextOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextOption) -> Unit
    ) {
        var inject = injectOption

        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "${label}text",
            input = inject.text,
        ) { text ->
            inject = HongTextBuilder()
                .copy(inject)
                .text(text)
                .applyOption()
            callback.invoke(inject)
        }

        if (includeCommonOption) {
            commonPreviewOption(
                defWidth = inject.width,
                defHeight = inject.height,
                defMargin = inject.margin,
                defPadding = inject.padding,
                selectWidth = { selectWidth ->
                    inject = HongTextBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** 텍스트 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "${label}text",
            colorHex = inject.colorHex
        ) { selectColor ->
            inject = HongTextBuilder()
                .copy(inject)
                .color(selectColor)
                .applyOption()
            callback.invoke(inject)
        }

        /** 텍스트 타이포그라피 */
        val initialTypography = PlaygroundManager.typographyList.firstOrNull {
            it == inject.typography
        } ?: HongTypo.BODY_14
        PlaygroundManager.addSelectOptionView(
            activity = activity,
            initialText = initialTypography.styleName,
            selectList = PlaygroundManager.typographyNameList,
            selectedPosition = PlaygroundManager.typographyList.indexOf(initialTypography),
            label = "typo 설정",
            description = "헤더 제목의 타이포그라피를 설정해요.",
            useDirectCallback = true,
        ) { selectTypography, index ->
            val typography = PlaygroundManager.typographyList.firstOrNull { it.styleName == selectTypography }
                ?: HongTypo.BODY_16_B
            Log.e("TAG", "옵션 typography = $typography, index = $index")
            inject = HongTextBuilder()
                .copy(inject)
                .typography(typography)
                .applyOption()
            callback.invoke(inject)
        }

        /**
         * 텍스트 정렬
         * 좌측 / 우측 / 가운데
         * width가 WRAP_CONTENT인 경우에는 의도한 대로 적용되지 않으므로 주의!
         */
        val textAlignList = HongTextAlign.entries.map { it.alias }
        val initialTextAlign = inject.align.toHongTextAlignToAlias()
        PlaygroundManager.addSelectOptionView(
            activity = activity,
            initialText = initialTextAlign,
            label = "$label 텍스트 정렬",
            description = "width가 WRAP_CONTENT인 경우에는 의도한 대로 적용되지 않으므로 주의!",
            useDirectCallback = true,
            selectList = textAlignList,
            selectedPosition = textAlignList.indexOf(initialTextAlign),
        ) { selectTextAlign, index ->
            val textAlign = selectTextAlign.toHongTextAlign()
            Log.e("TAG", "옵션 textAlign = $selectTextAlign, index = $index")
            inject = HongTextBuilder()
                .copy(inject)
                .textAlign(textAlign)
                .applyOption()
            callback.invoke(inject)
        }


        /** 최대 라인 수 */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "${label}maxLine",
            input = if (inject.maxLines == Int.MAX_VALUE) "" else inject.maxLines.toString(),
            useOnlyNumber = true
        ) { maxLines ->
            inject = HongTextBuilder()
                .copy(inject)
                .maxLines(
                    if (maxLines.isEmpty()) {
                        Int.MAX_VALUE
                    } else {
                        maxLines.toInt()
                    }
                )
                .applyOption()
            callback.invoke(inject)
        }

        /**
         * 화면에 표현할 수 있는 최대 글자수를 넘어간 경우 처리 방법
         * 자름 (Clip) / ... 처리 (Ellipsis)
         */
        val overflowList = HongTextOverflow.entries.map { it.alias }
        val initialOverflow = inject.overflow.toHongTextOverflowToAlias()
        PlaygroundManager.addSelectOptionView(
            activity = activity,
            initialText = initialOverflow,
            label = "${label}글 잘림 처리",
            description = "자름(Clip) / ...처리 (Ellipsis) / 보이기 (Visible)",
            useDirectCallback = true,
            selectList = overflowList,
            selectedPosition = overflowList.indexOf(initialOverflow),
        ) { selectOverflow, _ ->
            inject = HongTextBuilder()
                .copy(inject)
                .overflow(selectOverflow.toHongTextOverFlow())
                .applyOption()
            callback.invoke(inject)
        }

        /**
         * 줄바꿈 타입
         */
        val lineBreakList = HongTextLineBreak.entries.map { it.alias }.toList()
        val initialLineBreak = inject.lineBreak.toHongTextLineBreakToAlias()

        PlaygroundManager.addSelectOptionView(
            activity = activity,
            initialText = initialLineBreak,
            label = "${label}lineBreak",
            description = "시스템에 따름 / 음절 단위로 줄바꿈 / 단어 단위로 줄바꿈",
            useDirectCallback = true,
            selectList = lineBreakList,
            selectedPosition = lineBreakList.indexOf(initialLineBreak),
        ) { selectLineBreak, _ ->
            inject = HongTextBuilder()
                .copy(inject)
                .lineBreak(selectLineBreak.toHongTextLineBreak())
                .applyOption()
            callback.invoke(inject)
        }

        /** 취소선 여부 */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity = activity,
            label = "${label}취소선 여부",
            switchState = inject.isEnableCancelLine
        ) { isEnable ->
            inject = HongTextBuilder()
                .copy(inject)
                .isEnableCancelLine(isEnable)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addLabelSwitchOptionPreview(
            activity = activity,
            label = "${label}밑줄 여부",
            switchState = inject.isEnableUnderLine
        ) { isEnable ->
            inject = HongTextBuilder()
                .copy(inject)
                .isEnableUnderLine(isEnable)
                .applyOption()
            callback.invoke(inject)
        }
    }

}