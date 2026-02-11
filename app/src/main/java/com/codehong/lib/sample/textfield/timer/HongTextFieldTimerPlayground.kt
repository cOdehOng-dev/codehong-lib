package com.codehong.lib.sample.textfield.timer

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerBuilder
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerOption

class HongTextFieldTimerPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextFieldTimerOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextFieldTimerBuilder()
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
                    .copy(HongTextFieldTimerOption.DEFAULT_CLEAR_IMAGE)
                    .width(16)
                    .height(16)
                    .applyOption()
            )
            .underlineFinishColor(HongColor.RED_100)
            .onFinish {}
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextFieldTimerOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_FIELD_TIMER

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
        injectOption: HongTextFieldTimerOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextFieldTimerOption) -> Unit
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
                    inject = HongTextFieldTimerBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextFieldTimerBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextFieldTimerBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextFieldTimerBuilder()
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
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
                useLineBreak = false,
                useMaxLine = false,
                useOverflow = false,
                useText = false,
            ) {
                inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
                .copy(inject)
                .underlineHeight(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** placeholder */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "placeholder",
            input = inject.placeholder,
        ) {
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
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
            inject = HongTextFieldTimerBuilder()
                .copy(inject)
                .underlineFinishColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** keyboardType */
        val keyboardTypeList = HongKeyboardType.entries.map { it.name }
        val initialKeyboardType = inject.keyboardOption.first
        PlaygroundManager.addViewSelectOption(
            activity,
            initialText = initialKeyboardType.name,
            label = "키보드 타입 설정",
            description = "키보드 타입을 설정해요.",
            useDirectCallback = true,
            selectList = keyboardTypeList,
            selectedPosition = keyboardTypeList.indexOf(initialKeyboardType.name)
        ) { selectKeyboardType, _ ->
            val keyboardType = HongKeyboardType.entries.firstOrNull {
                it.name == selectKeyboardType
            } ?: HongKeyboardType.TEXT
            inject = HongTextFieldTimerBuilder()
                .copy(inject)
                .keyboardOption(
                    Pair(
                        keyboardType,
                        inject.keyboardOption.second
                    )
                )
                .applyOption()
            callback.invoke(inject)
        }

        /** keyboardActionType */
        val keyboardActionTypeList = HongKeyboardActionType.entries.map { it.name }
        val initialKeyboardActionType = inject.keyboardOption.second
        PlaygroundManager.addViewSelectOption(
            activity,
            initialText = initialKeyboardActionType.name,
            label = "키보드 액션 타입 설정",
            description = "키보드 액션 타입을 설정해요.",
            useDirectCallback = true,
            selectList = keyboardActionTypeList,
            selectedPosition = keyboardActionTypeList.indexOf(initialKeyboardActionType.name)
        ) { selectKeyboardActionType, _ ->
            val keyboardActionType = HongKeyboardActionType.entries.firstOrNull {
                it.name == selectKeyboardActionType
            } ?: HongKeyboardActionType.DONE
            inject = HongTextFieldTimerBuilder()
                .copy(inject)
                .keyboardOption(
                    Pair(
                        inject.keyboardOption.first,
                        keyboardActionType
                    )
                )
                .applyOption()
            callback.invoke(inject)
        }
    }


}