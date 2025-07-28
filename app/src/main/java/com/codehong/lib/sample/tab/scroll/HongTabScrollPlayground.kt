package com.codehong.lib.sample.tab.scroll

import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.tab.scroll.HongTabScrollBuilder
import com.codehong.library.widget.tab.scroll.HongTabScrollOption

class HongTabScrollPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongTabScrollOption> {

    companion object {
        private val DEFAULT_PREVIEW_OPTION = HongTabScrollBuilder()
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
            .tabTextList(
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
    override var previewOption: HongTabScrollOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.TAB_SCROLL

    fun preview() {
        executePreview()

        injectPreview(
            injectOption = previewOption
        ) {
            previewOption = it
            executePreview()
        }
    }


    fun injectPreview(
        injectOption: HongTabScrollOption,
        label: String = "",
        callback: (HongTabScrollOption) -> Unit
    ) {
        var inject = injectOption

        if (label.isNotEmpty()) {
            PlaygroundManager.addOptionTitleView(
                activity,
                label = label
            )
        }

        /**************************************************************
         * 활성 탭
         **************************************************************/
        PlaygroundManager.addOptionTitleView(
            activity,
            label = "활성 탭 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        /**************************************************************
         * selectTabTextTypo
         **************************************************************/
        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.selectTabTextTypo,
            label = "활성 탭 텍스트 폰트",
            description = "활성 탭의 텍스트 폰트를 설정해요."
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .selectTabTextTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * selectTabTextColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.selectTabTextColorHex,
            label = "활성 탭 텍스트 "
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .selectTabTextColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * selectBackgroundColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.selectBackgroundColorHex,
            label = "활성 탭 베경 "
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .selectBackgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * selectBorder
         **************************************************************/
        PlaygroundManager.addBorderOptionPreview(
            activity,
            border = HongBorderInfo(
                width = inject.selectBorderWidth,
                color = inject.selectBorderColorHex
            )
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .selectBorderColor(it.color)
                .selectBorderWidth(it.width)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * 비활성 탭
         **************************************************************/
        PlaygroundManager.addOptionTitleView(
            activity,
            label = "비활성 탭 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        /**************************************************************
         * unselectTabTextTypo
         **************************************************************/
        PlaygroundManager.addSelectTypoOptionView(
            activity,
            typo = inject.unselectTabTextTypo,
            label = "비활성 탭 텍스트 폰트",
            description = "비활성 탭의 텍스트 폰트를 설정해요."
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .unselectTabTextTypo(it)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * unselectTabTextColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.unselectTabTextColorHex,
            label = "비활성 탭 텍스트 "
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .unselectTabTextColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * unselectBackgroundColor
         **************************************************************/
        PlaygroundManager.addColorOptionPreview(
            activity,
            colorHex = inject.unselectBackgroundColorHex,
            label = "비활성 탭 베경 "
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .unselectBackgroundColor(it)
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * unselectBorder
         **************************************************************/
        PlaygroundManager.addBorderOptionPreview(
            activity,
            border = HongBorderInfo(
                width = inject.unselectBorderWidth,
                color = inject.unselectBorderColorHex
            )
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .unselectBorderColor(it.color)
                .unselectBorderWidth(it.width)
                .applyOption()
            callback.invoke(inject)
        }



        PlaygroundManager.addOptionTitleView(
            activity,
            label = "탭 간격 설정",
            labelTypo = HongTypo.BODY_16_B
        )

        /**************************************************************
         * tabTextHorizontalPadding
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.tabTextHorizontalPadding.toFigureString(),
            label = "탭 텍스트 좌우 간격",
            useOnlyNumber = true
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .tabTextHorizontalPadding(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }


        /**************************************************************
         * tabTextVerticalPadding
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.tabTextVerticalPadding.toFigureString(),
            label = "탭 텍스트 상하 간격",
            useOnlyNumber = true
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .tabTextVerticalPadding(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
        }

        /**************************************************************
         * tabBetweenPadding
         **************************************************************/
        PlaygroundManager.addLabelInputOptionPreview(
            activity,
            input = inject.tabBetweenPadding.toFigureString(),
            label = "탭 간격 설정",
            useOnlyNumber = true
        ) {
            inject = HongTabScrollBuilder()
                .copy(inject)
                .tabBetweenPadding(it.toFigureInt())
                .applyOption()
            callback.invoke(inject)
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
            inject = HongTabScrollBuilder()
                .copy(inject)
                .radius(it)
                .applyOption()
            callback.invoke(inject)
        }
    }
}