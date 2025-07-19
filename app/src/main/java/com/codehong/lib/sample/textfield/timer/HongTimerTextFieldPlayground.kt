package com.codehong.lib.sample.textfield.timer

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.textfield.timer.HongTimerTextFieldBuilder
import com.codehong.library.widget.textfield.timer.HongTimerTextFieldOption

class HongTimerTextFieldPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTimerTextFieldOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTimerTextFieldBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f,
                    bottom = 20f
                )
            )
            .backgroundColor(HongColor.WHITE_100.hex)
            .placeholder("[지우기 버튼] 값을 입력해주세요.")
            .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
            .cursorColor(HongColor.MAIN_ORANGE_100.hex)
            .onTextChanged { trackingText -> }
            .clearImageOption(
                HongImageBuilder()
                    .copy(HongTimerTextFieldOption.DEFAULT_CLEAR_IMAGE)
                    .width(16)
                    .height(16)
                    .applyOption()
            )
            .underlineFinishColor(HongColor.RED_100)
            .onFinish {}
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTimerTextFieldOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TIMER_TEXT_FIELD

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
        injectOption: HongTimerTextFieldOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTimerTextFieldOption) -> Unit
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
                    inject = HongTimerTextFieldBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTimerTextFieldBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTimerTextFieldBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTimerTextFieldBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** countDownTextOption */
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = inject.countDownTextOption,
                includeCommonOption = false,
                label = "타이머 텍스트 설정",
                description = "text에 00:00 형식으로 입력해주세요.",
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
                useLineBreak = false,
                useMaxLine = false,
                useOverflow = false,
            ) {
                inject = HongTimerTextFieldBuilder()
                    .copy(inject)
                    .countDownTextOption(it)
                    .applyOption()
            }

        /** underline 활성화 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.underlineFocusColorHex,
            label = "underline 활성화 "
        ) {
            inject = HongTimerTextFieldBuilder()
                .copy(inject)
                .underlineFocusColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** underline 비활성화 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.underlineOutFocusColorHex,
            label = "underline 비활성화 "
        ) {
            inject = HongTimerTextFieldBuilder()
                .copy(inject)
                .underlineOutFocusColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** underline 높이 */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.underlineHeight.toFigureString(),
            label = "underline 높이",
            useOnlyNumber = true
        ) {
            inject = HongTimerTextFieldBuilder()
                .copy(inject)
                .underlineHeight(it.toFigureInt())
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
                inject = HongTimerTextFieldBuilder()
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
            ) {
                inject = HongTimerTextFieldBuilder()
                    .copy(inject)
                    .inputTextOption(it)
                    .applyOption()
                callback.invoke(inject)
            }

        /** cursor 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.cursorColorHex,
            label = "cursor "
        ) {
            inject = HongTimerTextFieldBuilder()
                .copy(inject)
                .cursorColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** background 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.backgroundColorHex,
            label = "background "
        ) {
            inject = HongTimerTextFieldBuilder()
                .copy(inject)
                .backgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** underline finish 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.underlineFinishColorHex,
            label = "underline 타이머 완료 "
        ) {
            inject = HongTimerTextFieldBuilder()
                .copy(inject)
                .underlineFinishColor(it)
                .applyOption()
            callback.invoke(inject)
        }
    }


}