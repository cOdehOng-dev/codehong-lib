package com.codehong.lib.sample.button.icon

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.debugtool.log.TimberUtil
import com.codehong.library.widget.button.icon.HongButtonIconBuilder
import com.codehong.library.widget.button.icon.HongButtonIconOption
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.R as WidgetR

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
            .iconResId(WidgetR.drawable.honglib_ic_check)
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
        label: String? = null,
        callback: (HongButtonIconOption) -> Unit
    ) {
        var inject = injectOption

        if (!label.isNullOrEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        if (includeCommonOption) {
            commonPreviewOption(
                height = inject.height,
                padding = inject.padding,
                margin = inject.margin,
                useWidth = false,
                selectHeight = { selectHeight ->
                    inject = HongButtonIconBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongButtonIconBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongButtonIconBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        PlaygroundManager.addOptionTitleView(
            activity,
            label = "아이콘 버튼 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        // 버튼 타입 (사이즈)
        PlaygroundManager.addViewSelectOption(
            activity = activity,
            initialText = inject.buttonType.key,
            label = "버튼 타입",
            useDirectCallback = true,
            selectList = HongButtonIconType.entries.map { it.key },
            selectedPosition = HongButtonIconType.entries.indexOf(inject.buttonType)
        ) { selectType, _ ->
            val buttonType = HongButtonIconType.entries.firstOrNull { it.key == selectType }
                ?: HongButtonIconType.SIZE_56
            inject = HongButtonIconBuilder()
                .copy(inject)
                .buttonType(buttonType)
                .applyOption()
            callback.invoke(inject)
        }

        // 버튼 상태
        PlaygroundManager.addViewSelectOption(
            activity = activity,
            initialText = inject.state.key,
            label = "버튼 상태",
            useDirectCallback = true,
            selectList = HongClickState.entries.map { it.key },
            selectedPosition = HongClickState.entries.indexOf(inject.state)
        ) { selectState, _ ->
            val state = HongClickState.entries.firstOrNull { it.key == selectState }
                ?: HongClickState.ENABLE
            inject = HongButtonIconBuilder()
                .copy(inject)
                .state(state)
                .applyOption()
            callback.invoke(inject)
        }

        // 아이콘 리소스
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "아이콘 리소스",
            drawableResId = inject.iconResId.takeIf { it != 0 }
        ) { selectResId ->
            TimberUtil.d("test here selectResId = $selectResId")
            inject = HongButtonIconBuilder()
                .copy(inject)
                .iconResId(selectResId ?: 0)
                .applyOption()
            callback.invoke(inject)
        }

        // 아이콘 색상
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "아이콘 ",
            colorHex = inject.iconColorHex
        ) { selectColor ->
            inject = HongButtonIconBuilder()
                .copy(inject)
                .iconColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }

        // 배경색
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background ",
            colorHex = inject.backgroundColorHex
        ) { selectColor ->
            inject = HongButtonIconBuilder()
                .copy(inject)
                .backgroundColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }

        // 원형 버튼 사용 여부
        PlaygroundManager.addUseShapeCircleOptionPreview(
            activity = activity,
            useShapeCircle = inject.useShapeCircle
        ) { selectUseShapeCircle ->
            inject = HongButtonIconBuilder()
                .copy(inject)
                .useShapeCircle(selectUseShapeCircle)
                .applyOption()
            callback.invoke(inject)
        }

        // radius
        PlaygroundManager.addViewRadiusOption(
            activity = activity,
            radius = inject.radius
        ) { selectRadius ->
            inject = HongButtonIconBuilder()
                .copy(inject)
                .radius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        // border
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.border,
            despWidth = "버튼 테두리를 설정해요.",
            despColor = "버튼 테두리 색상을 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            inject = HongButtonIconBuilder()
                .copy(inject)
                .border(selectBorder)
                .applyOption()
            callback.invoke(inject)
        }
    }
}