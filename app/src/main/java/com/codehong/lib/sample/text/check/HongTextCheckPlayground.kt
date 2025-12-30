package com.codehong.lib.sample.text.check

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.check.HongTextCheckBuilder
import com.codehong.library.widget.text.check.HongTextCheckOption

class HongTextCheckPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextCheckOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextCheckBuilder()
            .checkSize(30)
            .arrowSize(20)
            .text("휴대폰/카드 본인확인 서비스")
            .textOption(
                HongTextBuilder()
                    .copy(HongTextCheckOption.DEFAULT_TEXT_OPTION)
                    .typography(HongTypo.BODY_15)
                    .color(HongColor.GRAY_70)
                    .applyOption()
            )
            .checkState(true)
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextCheckOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_CHECK

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
        injectOption: HongTextCheckOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextCheckOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                usePadding = false,
                selectWidth = { selectWidth ->
                    inject = HongTextCheckBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextCheckBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextCheckBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
            )
        }

        /** checkSize */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            label = "체크 아이콘 사이즈",
            description = "width, height를 동일하게 설정해요.",
            input = inject.checkSize.toFigureString(),
            useOnlyNumber = true,
        ) {
            inject = HongTextCheckBuilder()
                .copy(inject)
                .checkSize(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** arrowSize */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            label = "우측 arrow 아이콘 사이즈",
            description = "width, height를 동일하게 설정해요.",
            input = inject.arrowSize.toFigureString(),
            useOnlyNumber = true,
        ) {
            inject = HongTextCheckBuilder()
                .copy(inject)
                .checkSize(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** textOption */
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = inject.textOption,
                includeCommonOption = false,
                label = "텍스트 설정",
                description = "텍스트 컬러와 arrow 컬러가 동일하게 설정돼요.",
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
                useLineBreak = false,
                useMaxLine = false,
                useOverflow = false,
            ) {
                inject = HongTextCheckBuilder()
                    .copy(inject)
                    .text(it.text)
                    .textOption(it)
                    .applyOption()
                callback.invoke(inject)
            }

        /** checkColor */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.checkColor,
            label = "체크된 아이콘 ",
            description = "체크 아이콘의 색상을 설정해요."
        ) {
            inject = HongTextCheckBuilder()
                .copy(inject)
                .checkColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** uncheckColor */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.uncheckColor,
            label = "체크 해제 아이콘 ",
            description = "체크 해제 아이콘의 색상을 설정해요."
        ) {
            inject = HongTextCheckBuilder()
                .copy(inject)
                .uncheckColor(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}