package com.codehong.lib.sample.textfield.number

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption
import com.codehong.library.widget.textfield.number.HongNumberTextFieldBuilder
import com.codehong.library.widget.textfield.number.HongNumberTextFieldOption

class HongNumberTextFieldPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongNumberTextFieldOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongNumberTextFieldBuilder()
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
            .placeholderTextOption(
                HongTextBuilder()
                    .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                    .text("값을 입력해주세요.")
                    .applyOption()
            )
            .input("53600")
            .cursorColor(HongColor.MAIN_ORANGE_100.hex)
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongNumberTextFieldOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.NUMBER_TEXT_FIELD

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
        injectOption: HongNumberTextFieldOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongNumberTextFieldOption) -> Unit
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
                    inject = HongNumberTextFieldBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongNumberTextFieldBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongNumberTextFieldBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongNumberTextFieldBuilder()
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
            inject = HongNumberTextFieldBuilder()
                .copy(inject)
                .backgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** placeholder */
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = inject.placeholderTextOption,
                includeCommonOption = false,
                label = "placeholder 설정",
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
                useLineBreak = false,
                useMaxLine = false,
                useOverflow = false,
            ) {
                inject = HongNumberTextFieldBuilder()
                    .copy(inject)
                    .placeholder(null)
                    .placeholderTextOption(it)
                    .applyOption()
                callback.invoke(inject)
            }

        /** input */
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = inject.inputTextOption,
                includeCommonOption = false,
                label = "입력 설정",
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
                useLineBreak = false,
                useMaxLine = false,
                useOverflow = false,
                useText = false
            ) {
                inject = HongNumberTextFieldBuilder()
                    .copy(inject)
                    .inputTextOption(it)
                    .applyOption()
                callback.invoke(inject)
            }

        /** cursor 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.cursorColor,
            label = "cursor "
        ) {
            inject = HongNumberTextFieldBuilder()
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
            inject = HongNumberTextFieldBuilder()
                .copy(inject)
                .clearImageOption(
                    if (isEnable) {
                        HongTextFieldOption.DEFAULT_CLEAR_IMAGE
                    } else {
                        null
                    }
                )
                .applyOption()
            callback.invoke(inject)
        }
    }
}