package com.codehong.lib.sample.label.input

import com.codehong.lib.sample.label.HongLabelPlayground
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.textfield.HongTextFieldPlayground
import com.codehong.library.widget.label.input.HongLabelInputBuilder
import com.codehong.library.widget.label.input.HongLabelInputOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongLabelInputPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongLabelInputOption> {

    companion object {
        val DEFAULT_PREVIEW_OPTION = HongLabelInputBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .padding(
                HongSpacingInfo(
                    top = 10f,
                    bottom = 10f,
                    left = 16f,
                    right = 16f
                )
            )
            .label("레이블")
            .description("레이블 설명하는 테스트이에요.")
            .textFieldOption(
                HongTextFieldBuilder()
                    .copy(HongLabelInputOption.DEFAULT_TEXT_FIELD)
                    .margin(
                        HongSpacingInfo(
                            top = 10f
                        )
                    )
                    .placeholderTextOption(
                        HongTextBuilder()
                            .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                            .applyOption()
                    )
                    .inputTextOption(
                        HongTextBuilder()
                            .copy(HongTextFieldOption.DEFAULT_INPUT)
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .typography(HongTypo.BODY_14)
                            .color(HongColor.BLACK_100)
                            .applyOption()
                    )
                    .applyOption()
            )
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongLabelInputOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.LABEL_INPUT

    fun preview() {
        executePreview()

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "Label 옵션"
        )
        HongLabelPlayground(activity)
            .injectPreview(
                injectOption = previewOption.labelOption,
                includeCommonOption = true
            ) {
                this.previewOption = HongLabelInputBuilder()
                    .copy(previewOption)
                    .labelOption(it)
                    .applyOption()
                executePreview()
            }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "TextField 옵션"
        )
        HongTextFieldPlayground(activity)
            .injectPreview(
                injectOption = previewOption.textFieldOption,
                includeCommonOption = true
            ) {
                this.previewOption = HongLabelInputBuilder()
                    .copy(previewOption)
                    .textFieldOption(it)
                    .applyOption()
                executePreview()
            }
    }

}