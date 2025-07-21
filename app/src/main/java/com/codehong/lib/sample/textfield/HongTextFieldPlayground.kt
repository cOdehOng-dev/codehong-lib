package com.codehong.lib.sample.textfield

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongTextFieldPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextFieldOption> {
    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextFieldBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(44)
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f,
                    top = 12f,
                    bottom = 12f
                )
            )
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f,
                    bottom = 20f
                )
            )
            .backgroundColor(HongColor.BLACK_5.hex)
            .radius(
                HongRadiusInfo(
                    topLeft = 50,
                    topRight = 50,
                    bottomLeft = 50,
                    bottomRight = 50
                )
            )
            .placeholder("[지우기 버튼] 값을 입력해주세요.")
            .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
            .onTextChanged { trackingText -> }
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextFieldOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_FILED


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
        injectOption: HongTextFieldOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextFieldOption) -> Unit
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
                    inject = HongTextFieldBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextFieldBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextFieldBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTextFieldBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }


        PlaygroundManager.addRadiusOptionPreview(
            activity = activity,
            radius = inject.radius,
        ) { selectRadius ->
            inject = HongTextFieldBuilder()
                .copy(inject)
                .radius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        /** background 컬러 */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.backgroundColorHex,
            label = "background"
        ) {
            inject = HongTextFieldBuilder()
                .copy(inject)
                .backgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** border 컬러 */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.border,
            despWidth = "테두리를 설정해요.",
            despColor = "테두리 color를 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            inject = HongTextFieldBuilder()
                .copy(inject)
                .border(selectBorder)
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
                inject = HongTextFieldBuilder()
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
                inject = HongTextFieldBuilder()
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
            inject = HongTextFieldBuilder()
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
            inject = HongTextFieldBuilder()
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
            inject = HongTextFieldBuilder()
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
            inject = HongTextFieldBuilder()
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