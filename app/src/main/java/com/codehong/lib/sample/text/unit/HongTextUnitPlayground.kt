package com.codehong.lib.sample.text.unit

import android.util.Log
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.text.unit.HongTextUnitBuilder
import com.codehong.library.widget.text.unit.HongTextUnitOption

class HongTextUnitPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTextUnitOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTextUnitBuilder()
            .margin(
                HongSpacingInfo(
                    left = 20f,
                    right = 10f,
                    bottom = 10f
                )
            )
            .text("1000")
            .unitText("장")
            .useNumberDecimal(true)
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTextUnitOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TEXT_UNIT

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
        injectOption: HongTextUnitOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTextUnitOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label
            )
        }

        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                usePadding = false,
                selectWidth = { selectWidth ->
                    inject = HongTextUnitBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongTextUnitBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongTextUnitBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
            )
        }

        /** textOption */
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = inject.textOption,
                includeCommonOption = false,
                label = "텍스트 옵션",
                description = "UnitText가 동일한 옵션을 사용해요.",
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
                useLineBreak = false,
                useMaxLine = false,
                useOverflow = false,
            ) {
                inject = HongTextUnitBuilder()
                    .copy(inject)
                    .text(it.text)
                    .textOption(it)
                    .applyOption()
                callback.invoke(inject)
            }

        /** unitText */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            label = "단위 텍스트",
            description = "단위 텍스트를 입력해 주세요.",
            input = inject.unitText,
        ) {
            inject = HongTextUnitBuilder()
                .copy(inject)
                .unitText(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** useNumberDecimal */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity,
            label = "Decimal 사용",
            switchState = inject.useNumberDecimal,
        ) {
            inject = HongTextUnitBuilder()
                .copy(inject)
                .useNumberDecimal(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}