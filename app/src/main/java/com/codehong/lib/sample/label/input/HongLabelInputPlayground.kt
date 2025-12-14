package com.codehong.lib.sample.label.input

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.label.input.HongLabelInputBuilder
import com.codehong.library.widget.label.input.HongLabelInputOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo

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
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongLabelInputOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.LABEL_INPUT

    fun preview() {
        executePreview()

        injectPreview(
            injectOption = previewOption,
        ) {
            previewOption = it
            executePreview()
        }
    }

    fun injectPreview(
        injectOption: HongLabelInputOption,
        label: String = "",
        callback: (HongLabelInputOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "label 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.label,
            label = "label 텍스트",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .label(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.labelColorHex,
            label = "label ",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .labelColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.labelTypo,
            label = "label 폰트",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .labelTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "description 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.label,
            label = "description 텍스트",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .description(it)
                .applyOption()
            callback.invoke(inject)
        }


        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.labelColorHex,
            label = "description ",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .descriptionColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.labelTypo,
            label = "description 폰트",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .descriptionTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "TextField 옵션"
        )

        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "placeholder",
            input = inject.placeholder,
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .placeholder(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            label = "placeholder typo",
            typo = inject.placeholderTypo,
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .placeholderTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "placeholder ",
            colorHex = inject.placeholderColorHex
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .placeholderColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** input */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "입력 값",
            input = inject.input,
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .input(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.inputTypo,
            label = "입력 값 typo",
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .inputTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "입력 값 ",
            colorHex = inject.inputColorHex
        ) {
            inject = HongLabelInputBuilder()
                .copy(inject)
                .inputColor(it)
                .applyOption()
            callback.invoke(inject)
        }
    }

}