package com.codehong.lib.sample.toggleswitch

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchOption

class HongSwitchPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongSwitchOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongSwitchBuilder()
            .width(55)
            .height(30)
            .onColor(HongColor.MAIN_ORANGE_100)
            .offColor(HongColor.GRAY_20)
            .cursorSize(25)
            .cursorHorizontalMargin(3)
            .cursorColor(HongColor.WHITE_100)
            .initialState(false)
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongSwitchOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.SWITCH


    fun preview() {
        executePreview()

        injectPreview(
            injectOption = previewOption,
            includeCommonOption = true,
        ) {
            previewOption = it
            executePreview()
        }
    }

    fun injectPreview(
        injectOption: HongSwitchOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongSwitchOption) -> Unit
    ) {
        var inject = injectOption
        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        /** common */
        if (includeCommonOption) {
            commonPreviewOption(
                selectWidth = { selectWidth ->
                    inject = HongSwitchBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongSwitchBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongSwitchBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongSwitchBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.border,
            despWidth = "스위치의 테두리를 설정해요.",
            despColor = "스위치의 테두리 color를 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            inject = HongSwitchBuilder()
                .copy(inject)
                .border(selectBorder)
                .applyOption()
            callback.invoke(inject)
        }

        /** on color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.onColorHex,
            label = "스위치 켜짐 ",
            description = "스위치가 켜졌을 때의 색상을 설정해요.",
            useTopPadding = true
        ) {
            inject = HongSwitchBuilder()
                .copy(inject)
                .onColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** off color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.offColorHex,
            label = "스위치 꺼짐 ",
            description = "스위치가 꺼졌을 때의 색상을 설정해요.",
            useTopPadding = true
        ) {
            inject = HongSwitchBuilder()
                .copy(inject)
                .offColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** cursor size */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "커서 사이즈",
            description = "스위치의 커서 width, height를 동일하게 설정해요.",
            input = inject.cursorSize.toFigureString(),
            useOnlyNumber = true
        ) {
            inject = HongSwitchBuilder()
                .copy(inject)
                .cursorSize(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** cursor horizontal margin */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "커서 사이드 margin",
            description = "커서의 좌우 margin을 설정해요.",
            input = inject.cursorHorizontalMargin.toString(),
            useOnlyNumber = true
        ) {
            inject = HongSwitchBuilder()
                .copy(inject)
                .cursorHorizontalMargin(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** cursor color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            colorHex = inject.cursorColorHex,
            label = "커서 ",
            description = "스위치의 커서 color를 설정해요.",
            useTopPadding = true
        ) {
            inject = HongSwitchBuilder()
                .copy(inject)
                .cursorColor(it)
                .applyOption()
            callback.invoke(inject)
        }
    }

}