package com.codehong.lib.sample.textfield

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongTextFieldPlayground constructor(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextFieldOption> {
    companion object {
        val DEFAULT_PREVIEW_OPTION = HongTextFieldBuilder()
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
            .placeholderTextOption(
                HongTextBuilder()
                    .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                    .text("값을 입력해주세요.")
                    .applyOption()
            )
            .inputTextOption(
                HongTextBuilder()
                    .copy(HongTextFieldOption.DEFAULT_INPUT)
                    .applyOption()
            )
            .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
            .onTextChanged { trackingText ->
            }
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
            commonPreviewOption2(
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

        // region placeholder
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            input = inject.placeholderTextOption.text,
            label = "placeholder text",
            description = "placeholder를 설정해요.",
        ) { text ->
            inject = HongTextFieldBuilder()
                .copy(inject)
                .placeholderTextOption(
                    HongTextBuilder()
                        .copy(inject.placeholderTextOption)
                        .text(text)
                        .applyOption()
                )
                .applyOption()
            callback.invoke(inject)
        }
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.placeholderTextOption.colorHex,
            label = "placeholder "
        ) { selectColorHex ->
            inject = HongTextFieldBuilder()
                .copy(inject)
                .placeholderTextOption(
                    HongTextBuilder()
                        .copy(inject.placeholderTextOption)
                        .color(selectColorHex)
                        .applyOption()
                )
                .applyOption()
            callback.invoke(inject)
        }
        // endregion placeholder

        val initialTypography = PlaygroundManager.typographyList.firstOrNull {
            it == previewOption.placeholderTextOption.typography
        } ?: HongTypo.BODY_14

        PlaygroundManager.addSelectOptionView(
            activity = activity,
            label = "typo 설정",
            initialText = initialTypography.styleName,
            selectList = PlaygroundManager.typographyNameList,
            selectedPosition = PlaygroundManager.typographyList.indexOf(initialTypography),
            useDirectCallback = true,
        ) { selectTypography, index ->
            val typography = PlaygroundManager.typographyList.firstOrNull { it.styleName == selectTypography }
                ?: HongTypo.BODY_16_B
            inject = HongTextFieldBuilder()
                .copy(inject)
                .placeholderTextOption(
                    HongTextBuilder()
                        .copy(inject.placeholderTextOption)
                        .typography(typography)
                        .applyOption()
                )
                .applyOption()
            callback.invoke(inject)
        }

    }




}