package com.codehong.lib.sample.textfield.underline

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldBuilder
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldOption

class HongUnderlineTextFieldPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongUnderlineTextFieldOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongUnderlineTextFieldBuilder()
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
            .onTextChanged { trackingText ->
            }
            .clearImageOption(
                HongUnderlineTextFieldOption.DEFAULT_CLEAR_IMAGE
            )
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongUnderlineTextFieldOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.UNDERLINE_TEXT_FIELD

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
        injectOption: HongUnderlineTextFieldOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongUnderlineTextFieldOption) -> Unit
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
                    inject = HongUnderlineTextFieldBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongUnderlineTextFieldBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongUnderlineTextFieldBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongUnderlineTextFieldBuilder()
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
            inject = HongUnderlineTextFieldBuilder()
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
            inject = HongUnderlineTextFieldBuilder()
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
            inject = HongUnderlineTextFieldBuilder()
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
                inject = HongUnderlineTextFieldBuilder()
                    .copy(inject)
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
                inject = HongUnderlineTextFieldBuilder()
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
            inject = HongUnderlineTextFieldBuilder()
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
            inject = HongUnderlineTextFieldBuilder()
                .copy(inject)
                .backgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** keyboardType */
        val keyboardTypeList = HongKeyboardType.entries.map { it.name }
        val initialKeyboardType = inject.keyboardOption.first
        PlaygroundManager.addSelectOptionView(
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
            inject = HongUnderlineTextFieldBuilder()
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
        PlaygroundManager.addSelectOptionView(
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
            inject = HongUnderlineTextFieldBuilder()
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