package com.codehong.lib.sample.textfield.number

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.textfield.number.HongTextFieldNumberBuilder
import com.codehong.library.widget.textfield.number.HongTextFieldNumberOption

class HongTextFieldNumberPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextFieldNumberOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextFieldNumberBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f,
                    bottom = 20f
                )
            )
            .backgroundColor(HongColor.WHITE_100)
            .placeholder("값을 입력해주세요.")
            .input("53600")
            .cursorColor(HongColor.MAIN_ORANGE_100.hex)
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextFieldNumberOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_FIELD_NUMBER

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
        injectOption: HongTextFieldNumberOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextFieldNumberOption) -> Unit
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
                padding = inject.padding,
                selectWidth = { selectWidth ->
                    inject = HongTextFieldNumberBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextFieldNumberBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextFieldNumberBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextFieldNumberBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** background 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.backgroundColorHex,
            label = "background"
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .backgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** placeholder */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "placeholder",
            input = inject.placeholder,
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .placeholder(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            label = "placeholder typo",
            typo = inject.placeholderTypo,
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .placeholderTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "placeholder ",
            colorHex = inject.placeholderColorHex
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .placeholderColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** input */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "입력 값",
            input = inject.input,
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .input(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.inputTypo,
            label = "입력 값 typo",
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .inputTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "입력 값 ",
            colorHex = inject.inputColorHex
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .inputColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** cursor 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.cursorColorHex,
            label = "cursor "
        ) {
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .cursorColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** clearImageOption */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity,
            label = "삭제 버튼 표시",
            description = "아이콘은 고정되어 있어요.",
            switchState = false
        ) { isEnable ->
            inject = HongTextFieldNumberBuilder()
                .copy(inject)
                .clearIconRes(
                    if (isEnable) {
                        R.drawable.honglib_ic_20_circle_close_fill
                    } else {
                        null
                    }
                )
                .applyOption()
            callback.invoke(inject)
        }
    }
}