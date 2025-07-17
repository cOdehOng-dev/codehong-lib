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

        /** size */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            label = "체크박스 사이즈",
            description = "width, height를 동일하게 설정해요.",
            input = previewOption.size.toFigureString(),
            useOnlyNumber = true,
        ) {
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .size(it.toFigureInt())
                .applyOption()
            executePreview()
        }

        /** common */
        commonPreviewOption(
            useWidth = false,
            useHeight = false,
            usePadding = false,
            selectMargin = { selectMargin ->
                this.previewOption = HongCheckboxBuilder()
                    .copy(previewOption)
                    .margin(selectMargin)
                    .applyOption()
                executePreview()
            }
        )


        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.backgroundColorHex,
            label = "background ",
            description = "체크박스의 배경색을 설정해요."
        ) {
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .backgroundColor(it)
                .applyOption()
            executePreview()
        }


        /** checked color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.checkedColorHex,
            label = "체크활성화 background ",
            description = "체크박스가 활성화 되었을 때의 배경색을 설정해요."
        ) {
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .checkedColor(it)
                .applyOption()
            executePreview()
        }


        /** checkmark color */
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.checkmarkColorHex,
            label = "체크 아이콘 ",
            description = "체크박스의 체크 아이콘 color를 설정해요."
        ) {
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .checkmarkColor(it)
                .applyOption()
            executePreview()
        }


        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = previewOption.border,
            despWidth = "체크박스의 테두리를 설정해요.",
            despColor = "체크박스의 테두리 color를 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .border(selectBorder)
                .applyOption()
            executePreview()
        }

        /** radius */
        PlaygroundManager.addRadiusOptionPreview(
            activity = activity,
            radius = previewOption.radius,
            description = "체크박스의 모서리 둥글기를 설정해요.",
            useTopPadding = true
        ) { selectRadius ->
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .radius(selectRadius)
                .applyOption()
            executePreview()
        }

        /** enable */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity = activity,
            label = "체크박스 선택 상태 설정",
            description = "체크박스의 선택 상태를 설정해요.",
            switchState = previewOption.isEnabled,
        ) { isChecked ->
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .enabled(isChecked)
                .applyOption()
            executePreview()
        }

        /** useShapeCircle */
        PlaygroundManager.addUseShapeCircleOptionPreview(
            activity = activity,
            useShapeCircle = previewOption.useShapeCircle,
        ) { useShapeCircle ->
            this.previewOption = HongCheckboxBuilder()
                .copy(previewOption)
                .useShapeCircle(useShapeCircle)
                .applyOption()
            executePreview()
        }
    }
}