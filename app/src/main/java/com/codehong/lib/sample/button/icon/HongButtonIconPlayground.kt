package com.codehong.lib.sample.button.icon

import com.codehong.lib.sample.R
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.button.icon.HongButtonIconBuilder
import com.codehong.library.widget.button.icon.HongButtonIconOption
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.button.HongButtonIconType

class HongButtonIconPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongButtonIconOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongButtonIconBuilder()
            .margin(
                HongSpacingInfo(
                    left = 20f
                )
            )
            .buttonType(HongButtonIconType.SIZE_56)
            .iconResId(R.drawable.ic_close)
            .state(HongClickState.ENABLE)
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongButtonIconOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.BUTTON_ICON


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
        injectOption: HongButtonIconOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongButtonIconOption) -> Unit
    ) {

        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label
            )
        }


    }
}