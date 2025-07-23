package com.codehong.lib.sample.text.updown

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.extensions.toFigureStringCoverZero
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.text.updown.HongTextUpDownBuilder
import com.codehong.library.widget.text.updown.HongTextUpDownOption

class HongTextUpDownPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextUpDownOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextUpDownBuilder()
            .amount(7)
            .unit("장")
            .spaceButtonAndDisplay(8)
            .gap(1)
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextUpDownOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_UP_DOWN

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
        injectOption: HongTextUpDownOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextUpDownOption) -> Unit
    ) {

        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label
            )
        }

        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                usePadding = false,
                selectWidth = { selectWidth ->
                    inject = HongTextUpDownBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextUpDownBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextUpDownBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
            )
        }

        /** amount */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.amount.toFigureStringCoverZero(),
            label = "초기 값",
            description = "초기 값은 0이에요.",
            useOnlyNumber = true
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .amount(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }


        /** unit */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.unit,
            label = "단위",
            description = "단위를 설정해요.",
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .unit(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** displayTypo */
        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.displayTypo,
            label = "텍스트 typo 설정"
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .displayTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** displayColor */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.displayColorHex,
            label = "텍스트 ",
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .displayColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** gap */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.gap.toFigureString(),
            label = "변화 폭",
            description = "플러스, 마이너스 버튼을 눌렀을 때 변화하는 폭을 설정해요.",
            useOnlyNumber = true
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .gap(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** useDecimal */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity,
            label = "decimal 사용 여부",
            switchState = inject.useDecimal
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .useDecimal(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** buttonSize */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.buttonSize.toFigureString(),
            label = "버튼 사이즈",
            description = "플러스, 마이너스 버튼 사이즈를 설정해요.",
            useOnlyNumber = true
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .buttonSize(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** spaceButtonAndDisplay */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.spaceButtonAndDisplay.toFigureString(),
            label = "버튼과 텍스트 간격 설정",
            useOnlyNumber = true
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .spaceButtonAndDisplay(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** broderColor */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.borderColorHex,
            label = "border ",
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .broderColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** iconColor */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.iconColorHex,
            label = "아이콘 ",
        ) {
            inject = HongTextUpDownBuilder()
                .copy(inject)
                .iconColor(it)
                .applyOption()
            callback.invoke(inject)
        }
    }

}