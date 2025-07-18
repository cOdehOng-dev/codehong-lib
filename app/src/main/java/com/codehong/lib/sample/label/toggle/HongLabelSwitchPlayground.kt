package com.codehong.lib.sample.label.toggle

import com.codehong.lib.sample.label.HongLabelPlayground
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.toggleswitch.HongSwitchPlayground
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchBuilder
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder

class HongLabelSwitchPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongLabelSwitchOption> {

    companion object {
        val DEFAULT_PREVIEW_OPTION = HongLabelSwitchBuilder()
            .padding(
                HongSpacingInfo(
                    top = 20f,
                    bottom = 20f,
                    left = 16f,
                    right = 16f
                )
            )
            .label("테스트")
            .description("테스트 입니다요")
            .switchOption(
                HongSwitchBuilder()
                    .width(55)
                    .height(30)
                    .onColor(HongColor.MAIN_ORANGE_100)
                    .offColor(HongColor.GRAY_20)
                    .cursorSize(25)
                    .cursorHorizontalMargin(3)
                    .cursorColor(HongColor.WHITE_100)
                    .initialState(true)
                    .switchClick { _, isEnable -> }
                    .applyOption()
            )
            .applyOption()
    }


    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongLabelSwitchOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.LABEL_SWITCH

    fun preview() {
        executePreview()

        HongLabelPlayground(activity)
            .injectPreview(
                injectOption = previewOption.labelOption,
                includeCommonOption = true,
                label = "Label 옵션"
            ) {
                this.previewOption = HongLabelSwitchBuilder()
                    .copy(previewOption)
                    .labelOption(it)
                    .applyOption()
                executePreview()
            }

        HongSwitchPlayground(activity)
            .injectPreview(
                injectOption = previewOption.switchOption,
                includeCommonOption = true,
                label = "Switch 옵션"
            ) {
                this.previewOption = HongLabelSwitchBuilder()
                    .copy(previewOption)
                    .switchOption(it)
                    .applyOption()
                executePreview()
            }
    }
}