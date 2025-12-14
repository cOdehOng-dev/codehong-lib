package com.codehong.lib.sample.image

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongScaleType.Companion.toHongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongImagePlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongImageOption> {
    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongImageBuilder()
            .width(200)
            .height(200)
            .margin(
                HongSpacingInfo(
                    left = 20f
                )
            )
            .imageInfo("https://images.unsplash.com/photo-1735832489994-96adfc4db2dd?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
            .radius(
                HongRadiusInfo(
                    topLeft = 12,
                    topRight = 12,
                    bottomLeft = 12,
                    bottomRight = 12
                )
            )
            .shadow(
                HongShadowInfo(
                    color = HongColor.MAIN_ORANGE_100.hex,
                    blur = 24f,
                    offsetY = 0f,
                    offsetX = 2f,
                    spread = 0f,
                )
            )
            .scaleType(HongScaleType.CENTER_CROP)
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override val widgetType = HongWidgetType.IMAGE
    override var previewOption: HongImageOption = DEFAULT_PREVIEW_OPTION

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
        injectOption: HongImageOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongImageOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        /** url image */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "이미지 URL",
            input = inject.imageInfo as? String,
        ) { inputText ->
            inject = HongImageBuilder()
                .copy(inject)
                .imageInfo(null)
                .imageInfo(inputText)
                .applyOption()
            callback.invoke(inject)
        }

        /** local image */
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "로컬 이미지",
            drawableResId = inject.imageInfo as? Int
        ) { resId ->
            inject = HongImageBuilder()
                .copy(inject)
                .imageInfo(null)
                .imageInfo(resId)
                .applyOption()
            callback.invoke(inject)
        }

        /** common */
        if (includeCommonOption) {
            commonPreviewOption(
                width = inject.width,
                height = inject.height,
                margin = inject.margin,
                padding = inject.padding,
                selectWidth = { selectWidth ->
                    inject = HongImageBuilder()
                        .copy(inject)
                        .width(selectWidth)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectHeight = { selectHeight ->
                    inject = HongImageBuilder()
                        .copy(inject)
                        .height(selectHeight)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectMargin = { selectMargin ->
                    inject = HongImageBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongImageBuilder()
                        .copy(inject)
                        .padding(selectPadding)
                        .applyOption()
                    callback.invoke(inject)
                }
            )
        }

        /** radius */
        PlaygroundManager.addViewRadiusOption(
            activity = activity,
            radius = inject.radius,
        ) { selectRadius ->
            inject = HongImageBuilder()
                .copy(inject)
                .radius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = inject.border,
            despWidth = "image의 테두리를 설정해요.",
            despColor = "image의 테두리 color를 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            inject = HongImageBuilder()
                .copy(inject)
                .border(selectBorder)
                .applyOption()
            callback.invoke(inject)
        }

        /** shadow */
        PlaygroundManager.addShadowOptionPreview(
            activity = activity,
            shadow = inject.shadow
        ) { selectShadow ->
            inject = HongImageBuilder()
                .copy(inject)
                .shadow(selectShadow)
                .applyOption()
            callback.invoke(inject)
        }

        /** scale type */
        val scaleTypeList = HongScaleType.entries.toList()
        val scaleNameList = scaleTypeList.map { it.value }
        val initial = scaleTypeList
            .firstOrNull { it == inject.scaleType }
            ?: HongScaleType.FIT_START
        PlaygroundManager.addViewSelectOption(
            activity = activity,
            initialText = initial.value,
            label = "scale type",
            description = "이미지의 스케일 타입을 설정해요.",
            selectList = scaleNameList,
            selectedPosition = scaleNameList.indexOf(initial.value),
            useDirectCallback = true,
        ) { selectScaleType, index ->
            val scaleTypeName = scaleNameList.firstOrNull { it == selectScaleType }
            inject = HongImageBuilder()
                .copy(inject)
                .scaleType(scaleTypeName.toHongScaleType())
                .applyOption()
            callback.invoke(inject)
        }

        /** placeholder */
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "placeholder",
            drawableResId = inject.imageInfo as? Int
        ) { resId ->
            inject = HongImageBuilder()
                .copy(inject)
                .placeholder(resId)
                .applyOption()
            callback.invoke(inject)
        }

        /** error */
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "error",
            drawableResId = inject.imageInfo as? Int
        ) { resId ->
            inject = HongImageBuilder()
                .copy(inject)
                .error(resId)
                .applyOption()
            callback.invoke(inject)
        }

        /** useShapeCircle */
        PlaygroundManager.addUseShapeCircleOptionPreview(
            activity = activity,
            useShapeCircle = inject.useShapeCircle,
        ) { useShapeCircle ->
            inject = HongImageBuilder()
                .copy(inject)
                .useShapeCircle(useShapeCircle)
                .applyOption()
            callback.invoke(inject)
        }

        /** background */
        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "background ",
            colorHex = inject.backgroundColorHex
        ) { selectColorHex ->
            inject = HongImageBuilder()
                .copy(inject)
                .backgroundColor(selectColorHex)
                .applyOption()
            callback.invoke(inject)
        }

    }

}