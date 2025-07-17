package com.codehong.lib.sample.image

import android.util.Log
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

class HongImagePlayground constructor(
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
            .imageUrl("https://images.unsplash.com/photo-1735832489994-96adfc4db2dd?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
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

        /** url image */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            label = "이미지 URL",
            input = previewOption.imageUrl,
        ) { inputText ->
            previewOption = HongImageBuilder()
                .copy(previewOption)
                .drawableResId(null)
                .imageUrl(inputText)
                .applyOption()
            executePreview()
        }

        /** local image */
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "로컬 이미지",
            drawableResId = previewOption.drawableResId
        ) { resId ->
            previewOption = HongImageBuilder()
                .copy(previewOption)
                .imageUrl(null)
                .drawableResId(resId)
                .applyOption()
            executePreview()
        }

        /** common */
        commonPreviewOption(
            selectWidth = { selectWidth ->
                this.previewOption = HongImageBuilder()
                    .copy(previewOption)
                    .width(selectWidth)
                    .applyOption()
                executePreview()
            },
            selectHeight = { selectHeight ->
                this.previewOption = HongImageBuilder()
                    .copy(previewOption)
                    .height(selectHeight)
                    .applyOption()
                executePreview()
            },
            selectMargin = { selectMargin ->
                this.previewOption = HongImageBuilder()
                    .copy(previewOption)
                    .margin(selectMargin)
                    .applyOption()
                executePreview()
            },
            selectPadding = { selectPadding ->
                this.previewOption = HongImageBuilder()
                    .copy(previewOption)
                    .padding(selectPadding)
                    .applyOption()
                executePreview()
            }
        )

        /** radius */
        PlaygroundManager.addRadiusOptionPreview(
            activity = activity,
            radius = previewOption.radius,
        ) { selectRadius ->
            this.previewOption = HongImageBuilder()
                .copy(previewOption)
                .radius(selectRadius)
                .applyOption()
            executePreview()
        }

        /** border */
        PlaygroundManager.addBorderOptionPreview(
            activity = activity,
            border = previewOption.border,
            despWidth = "image의 테두리를 설정해요.",
            despColor = "image의 테두리 color를 설정해요.",
            useTopPadding = true
        ) { selectBorder ->
            this.previewOption = HongImageBuilder()
                .copy(previewOption)
                .border(selectBorder)
                .applyOption()
            executePreview()
        }

        /** shadow */
        PlaygroundManager.addShadowOptionPreview(
            activity = activity,
            shadow = previewOption.shadow
        ) { selectShadow ->
            this.previewOption = HongImageBuilder()
                .copy(previewOption)
                .shadow(selectShadow)
                .applyOption()
            executePreview()
        }

        /** scale type */
        val scaleTypeList = HongScaleType.entries.toList()
        val scaleNameList = scaleTypeList.map { it.value }
        val initial = scaleTypeList
            .firstOrNull { it == previewOption.scaleType }
            ?: HongScaleType.FIT_START
        PlaygroundManager.addSelectOptionView(
            activity = activity,
            initialText = initial.value,
            label = "scale type",
            description = "이미지의 스케일 타입을 설정해요.",
            selectList = scaleNameList,
            selectedPosition = scaleNameList.indexOf(initial.value),
            useDirectCallback = true,
        ) { selectScaleType, index ->
            val scaleTypeName = scaleNameList.firstOrNull { it == selectScaleType }
            Log.e("TAG", "옵션 scaleTypeName = $scaleTypeName, index = $index")
            previewOption = HongImageBuilder()
                .copy(previewOption)
                .scaleType(scaleTypeName.toHongScaleType())
                .applyOption()
            executePreview()
        }

        /** placeholder */
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "placeholder",
            drawableResId = previewOption.drawableResId
        ) { resId ->
            previewOption = HongImageBuilder()
                .copy(previewOption)
                .placeholder(resId)
                .applyOption()
            executePreview()
        }

        /** error */
        PlaygroundManager.addLocalResourceOptionPreview(
            activity = activity,
            label = "error",
            drawableResId = previewOption.drawableResId
        ) { resId ->
            previewOption = HongImageBuilder()
                .copy(previewOption)
                .error(resId)
                .applyOption()
            executePreview()
        }

        /** useShapeCircle */
        PlaygroundManager.addUseShapeCircleOptionPreview(
            activity = activity,
            useShapeCircle = previewOption.useShapeCircle,
        ) { useShapeCircle ->
            this.previewOption = HongImageBuilder()
                .copy(previewOption)
                .useShapeCircle(useShapeCircle)
                .applyOption()
            executePreview()
        }

        /** background */
        PlaygroundManager.addColorOptionPreview(
            activity,
            label = "background ",
            colorHex = previewOption.backgroundColorHex
        ) { selectColorHex ->
            this.previewOption = HongImageBuilder()
                .copy(previewOption)
                .backgroundColor(selectColorHex)
                .applyOption()
            executePreview()
        }
    }
}