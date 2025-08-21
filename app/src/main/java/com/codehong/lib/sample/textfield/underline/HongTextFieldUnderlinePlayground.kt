package com.codehong.lib.sample.textfield.underline

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineBuilder
import com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineOption

class HongTextFieldUnderlinePlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextFieldUnderlineOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextFieldUnderlineBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f
                )
            )
            .backgroundColor(HongColor.WHITE_100.hex)
            .placeholder("[지우기 버튼] 값을 입력해주세요.")
            .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
            .cursorColor(HongColor.MAIN_ORANGE_100.hex)
            .onTextChanged { trackingText -> }
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextFieldUnderlineOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_FIELD_UNDERLINE

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
        injectOption: HongTextFieldUnderlineOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextFieldUnderlineOption) -> Unit
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
                    inject = HongTextFieldUnderlineBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextFieldUnderlineBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextFieldUnderlineBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextFieldUnderlineBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }
        /** underline 활성화 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.underlineFocusColor,
            label = "underline 활성화 "
        ) {
            inject = HongTextFieldUnderlineBuilder()
                .copy(inject)
                .underlineFocusColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** underline 비활성화 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.underlineOutFocusColor,
            label = "underline 비활성화 "
        ) {
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
                .copy(inject)
                .backgroundColor(it)
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
            inject = HongTextFieldUnderlineBuilder()
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
            inject = HongTextFieldUnderlineBuilder()
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