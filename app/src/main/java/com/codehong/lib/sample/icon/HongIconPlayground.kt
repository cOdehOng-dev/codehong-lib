package com.codehong.lib.sample.icon

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.R
import com.codehong.library.widget.icon.HongIconBuilder
import com.codehong.library.widget.icon.HongIconOption
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.icon.HongIconType
import com.codehong.library.widget.rule.icon.HongIconType.Companion.toIconType

class HongIconPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongIconOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION =
            HongIconBuilder()
                .iconResId(R.drawable.honglib_ic_alarm)
                .iconType(HongIconType.H28)
                .iconColor(HongColor.MAIN_ORANGE_100)
                .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongIconOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.ICON


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
        injectOption: HongIconOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongIconOption) -> Unit
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
                margin = inject.margin,
                padding = inject.padding,
                useWidth = false,
                useHeight = false,
                selectPadding = { selectPadding ->
                    inject = HongIconBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongIconBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
            )
        }

        val iconTypeList = HongIconType.entries.map { it.key }.toList()
        val initialIconTypeKey = inject.iconType.key
        PlaygroundManager.addViewSelectOption(
            activity = activity,
            initialText = initialIconTypeKey,
            label = "아이콘 타입",
            description = "아이콘 타입을 설정해요",
            useDirectCallback = true,
            selectList = iconTypeList,
            selectedPosition = iconTypeList.indexOf(initialIconTypeKey),
        ) { selectLineBreak, _ ->
            inject = HongIconBuilder()
                .copy(inject)
                .iconType(selectLineBreak.toIconType())
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "아이콘 ",
            colorHex = injectOption.iconColorHex
        ) { selectColor ->
            inject = HongIconBuilder()
                .copy(inject)
                .iconColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }
    }
}