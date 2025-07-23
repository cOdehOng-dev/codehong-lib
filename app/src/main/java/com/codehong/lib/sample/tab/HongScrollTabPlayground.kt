package com.codehong.lib.sample.tab

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.tab.HongScrollTabBuilder
import com.codehong.library.widget.tab.HongScrollTabOption

class HongScrollTabPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongScrollTabOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongScrollTabBuilder()
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f
                )
            )
            .tabList(
                listOf(
                    "뮤지컬",
                    "콘서트",
                    "스포츠",
                    "전시/행사",
                    "클래식/무용",
                    "아동/가족",
                    "연극",
                    "레저/캠핑"
                )
            )
            .tabTitleList(
                listOf(
                    "뮤지컬",
                    "콘서트",
                    "스포츠",
                    "전시/행사",
                    "클래식/무용",
                    "아동/가족",
                    "연극",
                    "레저/캠핑"
                )
            )
            .selectBackgroundColor(HongColor.MAIN_ORANGE_100.hex)
            .radius(
                HongRadiusInfo(
                    topLeft = 100,
                    topRight = 100,
                    bottomLeft = 100,
                    bottomRight = 100
                )
            )
            .tabTextHorizontalPadding(12)
            .tabBetweenPadding(8)
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongScrollTabOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.SCROLL_TAB

    fun preview() {
        executePreview()

        /**************************************************************
         * 활성 탭
         **************************************************************/
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = previewOption.selectTabTextOption,
                includeCommonOption = false,
                label = "활성 탭 텍스트 설정",
                useText = false,
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
            ) {
                previewOption = HongScrollTabBuilder()
                    .copy(previewOption)
                    .selectTabTextOption(it)
                    .applyOption()
                executePreview()
            }

        /**************************************************************
         * 비활성 탭
         **************************************************************/
        HongTextPlayground(activity)
            .injectPreview(
                injectOption = previewOption.unselectTabTextOption,
                includeCommonOption = false,
                label = "비활성 탭 텍스트 설정",
                useText = false,
                useAlign = false,
                useCancelLine = false,
                useUnderline = false,
            ) {
                previewOption = HongScrollTabBuilder()
                    .copy(previewOption)
                    .unselectTabTextOption(it)
                    .applyOption()
                executePreview()
            }

        /**************************************************************
         * tabTextHorizontalPadding
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = previewOption.tabTextHorizontalPadding.toFigureString(),
            label = "탭 텍스트 좌우 간격",
            useOnlyNumber = true
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .tabTextHorizontalPadding(it.toFigureInt())
                .applyOption()
            executePreview()
        }


        /**************************************************************
         * tabTextVerticalPadding
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = previewOption.tabTextVerticalPadding.toFigureString(),
            label = "탭 텍스트 상하 간격",
            useOnlyNumber = true
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .tabTextVerticalPadding(it.toFigureInt())
                .applyOption()
            executePreview()
        }

        /**************************************************************
         * borderWidth
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = previewOption.borderWidth.toFigureString(),
            label = "border width",
            description = "탭 테두리 두께를 설정해요.",
            useOnlyNumber = true,
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .borderWidth(it.toFigureInt())
                .applyOption()
            executePreview()
        }


        /**************************************************************
         * selectBorderColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.selectBorderColor,
            label = "활성 탭 테두리 "
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .selectBorderColor(it)
                .applyOption()
            executePreview()
        }
        /**************************************************************
         * unselectBorderColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.unselectBorderColor,
            label = "비활성 탭 테두리 "
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .unselectBorderColor(it)
                .applyOption()
            executePreview()
        }


        /**************************************************************
         * selectBackgroundColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.selectBackgroundColor,
            label = "활성 탭 배경 "
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .selectBackgroundColor(it)
                .applyOption()
            executePreview()
        }

        /**************************************************************
         * unselectBackgroundColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = previewOption.unselectBackgroundColor,
            label = "비활성 탭 배경 "
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .unselectBackgroundColor(it)
                .applyOption()
            executePreview()
        }


        /**************************************************************
         * tabBetweenPadding
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = previewOption.tabBetweenPadding.toFigureString(),
            label = "탭 간격 설정",
            useOnlyNumber = true
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .tabBetweenPadding(it.toFigureInt())
                .applyOption()
            executePreview()
        }

        /**************************************************************
         * radius
         **************************************************************/
        PlaygroundManager.addRadiusOptionPreview(
            activity,
            radius = previewOption.radius,
            label = "탭 ",
            description = "탭의 모서리를 원형으로 처리할 경우 100을 입력하세요."
        ) {
            previewOption = HongScrollTabBuilder()
                .copy(previewOption)
                .radius(it)
                .applyOption()
            executePreview()
        }
    }
}