package com.codehong.lib.sample.textfield.border

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.textfield.border.HongTextFieldBorderBuilder
import com.codehong.library.widget.textfield.border.HongTextFieldBorderOption

class HongTextFieldBorderPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextFieldBorderOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextFieldBorderBuilder()
            .padding(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f
                )
            )
            .label("닉네임")
            .state(HongInputState.ENABLE)
            .isRequired(true)
            .placeholder("닉네임을 입력하세요.")
            .helperText("헬퍼텍스트가 노출되는 영역입니다.")
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextFieldBorderOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_FIELD_BORDER

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
        injectOption: HongTextFieldBorderOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextFieldBorderOption) -> Unit
    ) {

    }
}