package com.codehong.lib.sample.tab.segment

import android.util.Log
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.tab.segment.HongTabSegmentBuilder
import com.codehong.library.widget.tab.segment.HongTabSegmentOption

class HongTabSegmentPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTabSegmentOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTabSegmentBuilder()
            .margin(
                HongSpacingInfo(
                    left = 16f,
                    top = 16f,
                    right = 16f,
                    bottom = 16f
                )
            )
            .padding(
                HongSpacingInfo(
                    left = 4f,
                    top = 4f,
                    right = 4f,
                    bottom = 4f
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 24,
                    topRight = 24,
                    bottomLeft = 24,
                    bottomRight = 24
                )
            )
            .backgroundColor(HongColor.GRAY_10)
            .tabTextList(listOf("추천", "계좌", "연락처"))
            .onTabClick {
                Log.d("TAG", "selected tab index: $it")
            }
            .applyOption()
    }
    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongTabSegmentOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TAB_SEGMENT

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
        injectOption: HongTabSegmentOption,
        includeCommonOption: Boolean = false,
        label: String = "",
        callback: (HongTabSegmentOption) -> Unit
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
                selectMargin = { selectMargin ->
                    inject = HongTabSegmentBuilder()
                        .copy(inject)
                        .margin(selectMargin)
                        .applyOption()
                    callback.invoke(inject)
                },
                selectPadding = { selectPadding ->
                    inject = HongTabSegmentBuilder()
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
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .radius(selectRadius)
                .applyOption()
            callback.invoke(inject)
        }

        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background ",
            colorHex = inject.backgroundColorHex
        ) { selectColor ->
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .backgroundColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }

        /** indicatorWidth */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.tabWidth.toFigureString(),
            label = "탭 width",
            useOnlyNumber = true
        ) {
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .tabWidth(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }


        /** tabHeight */
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.tabHeight.toFigureString(),
            label = "탭 height",
            useOnlyNumber = true
        ) {
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .tabHeight(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /** indicatorColor */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "인디케이터 ",
            colorHex = inject.backgroundColorHex
        ) { selectColor ->
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .indicatorColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }


        /** selectTextColor */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "인디케이터 텍스트 ",
            colorHex = inject.selectTextColorHex
        ) { selectColor ->
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .selectTextColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }


        /** unselectTabTextColor */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "기본 텍스트 ",
            colorHex = inject.unselectTabColorHex
        ) { selectColor ->
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .unselectTabTextColor(selectColor)
                .applyOption()
            callback.invoke(inject)
        }

        /** selectTypo */
        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.selectTypo,
            label = "인디케이터 typo"
        ) {
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .selectTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        /** unselectTypo */
        PlaygroundManager.addViewSelectTypoOption(
            activity,
            typo = inject.unselectTypo,
            label = "기본 텍스트 typo"
        ) {
            inject = HongTabSegmentBuilder()
                .copy(inject)
                .unselectTypo(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}