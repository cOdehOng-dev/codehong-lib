package com.codehong.lib.sample.checkbox

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.checkbox.HongCheckboxOption
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongState.Companion.isEnabled
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongCheckboxPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongCheckboxOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongCheckboxBuilder()
            .size(24)
            .margin(
                HongSpacingInfo(
                    left = 10f,
                    right = 10f,
                    top = 10f,
                    bottom = 10f
                )
            )
            .backgroundColor(HongColor.WHITE_100)
            .checkedColor(HongColor.MAIN_ORANGE_100)
            .checkmarkColor(HongColor.WHITE_100)
            .border(
                HongBorderInfo(
                    width = 2,
                    color = HongColor.GRAY_20.hex
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 4,
                    topRight = 4,
                    bottomLeft = 4,
                    bottomRight = 4
                )
            )
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongCheckboxOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.CHECKBOX

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
        injectOption: HongCheckboxOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongCheckboxOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        /** size */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            label = "체크박스 사이즈",
            description = "width, height를 동일하게 설정해요.",
            input = inject.size.toFigureString(),
            useOnlyNumber = true,
        ) {
            inject = HongCheckboxBuilder()
                .copy(inject)
                .size(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** common */
        if (includeCommonOption) {
            commonPreviewOption(
                margin = inject.margin,
                useWidth = false,
                useHeight = false,
                usePadding = false,
                selectMargin = { selectMargin ->
                    inject = HongCheckboxBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.backgroundColorHex,
            label = "background ",
            description = "체크박스의 배경색을 설정해요."
        ) {
            inject = HongCheckboxBuilder()
                .copy(inject)
                .backgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** checked color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.checkedColorHex,
            label = "체크활성화 background ",
            description = "체크박스가 활성화 되었을 때의 배경색을 설정해요."
        ) {
            inject = HongCheckboxBuilder()
                .copy(inject)
                .checkedColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** checkmark color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.checkmarkColorHex,
            label = "체크 아이콘 ",
            description = "체크박스의 체크 아이콘 color를 설정해요."
        ) {
            inject = HongCheckboxBuilder()
                .copy(inject)
                .checkmarkColor(it)
                .applyOption()
            callback.invoke(inject)
        }


        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.border,
            despWidth = "체크박스의 테두리를 설정해요.",
            despColor = "체크박스의 테두리 color를 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            inject = HongCheckboxBuilder()
                .copy(inject)
                .border(selectBorder)
                .applyOption()
            callback.invoke(inject)
        }

        /** radius */
        PlaygroundManager.addRadiusOptionPreview(
            activity = activity,
            radius = inject.radius,
            description = "체크박스의 모서리 둥글기를 설정해요.",
            useTopPadding = true
        ) { selectRadius ->
            inject = HongCheckboxBuilder()
                .copy(inject)
                .radius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        /** enable */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity = activity,
            label = "체크박스 활성 상태 설정",
            description = "체크박스의 활성 상태를 설정해요.",
            switchState = inject.enableState.isEnabled(),
        ) { isChecked ->
            inject = HongCheckboxBuilder()
                .copy(inject)
                .enableState(if (isChecked) HongState.ENABLED else HongState.DISABLED)
                .applyOption()
            callback.invoke(inject)
        }

        /** useShapeCircle */
        PlaygroundManager.addUseShapeCircleOptionPreview(
            activity = activity,
            useShapeCircle = inject.useShapeCircle,
        ) { useShapeCircle ->
            inject = HongCheckboxBuilder()
                .copy(inject)
                .useShapeCircle(useShapeCircle)
                .applyOption()
            callback.invoke(inject)
        }
    }
}