package com.codehong.lib.sample.tab

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.tab.HongScrollTab
import com.codehong.library.widget.tab.HongScrollTabBuilder
import com.codehong.library.widget.tab.HongScrollTabView

class SampleScrollTabActivity : BaseSampleMixActivity() {

    override fun optionViewList(): List<View> {
        val option1 = HongScrollTabBuilder()
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f
                )
            )
            .margin(
                HongSpacingInfo(
                    bottom = 20f
                )
            )
            .tabList(
                listOf(
                    "패키지",
                    "국내숙소",
                    "해외숙소",
                    "투어·티켓"
                )
            )
            .tabTitleList(
                listOf(
                    "패키지",
                    "국내숙소",
                    "해외숙소",
                    "투어·티켓"
                )
            )
            .selectBackgroundColor(HongColor.BLACK_100.hex)
            .radius(
                HongRadiusInfo(
                    all = 100
                )
            )
            .tabTextHorizontalPadding(12)
            .tabBetweenPadding(6)
            .applyOption()

        val option2 = HongScrollTabBuilder()
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
                    all = 100
                )
            )
            .tabTextHorizontalPadding(12)
            .tabBetweenPadding(8)
            .applyOption()

        return listOf(
            HongScrollTabView(this).set(
                option = option1,
                onTabClick = { index, item ->
                    // Handle tab click
                }
            ),
            HongScrollTabView(this).set(
                option = option2,
                onTabClick = { index, item ->
                    // Handle tab click
                }
            )
        )
    }

    @Composable
    override fun InitCompose() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(HongColor.WHITE_100.colorResId))
        ) {
            HongScrollTab(
                option = HongScrollTabBuilder()
                    .padding(
                        HongSpacingInfo(
                            left = 16f,
                            right = 16f
                        )
                    )
                    .margin(
                        HongSpacingInfo(
                            bottom = 20f
                        )
                    )
                    .tabList(
                        listOf(
                            "패키지",
                            "국내숙소",
                            "해외숙소",
                            "투어·티켓"
                        )
                    )
                    .tabTitleList(
                        listOf(
                            "패키지",
                            "국내숙소",
                            "해외숙소",
                            "투어·티켓"
                        )
                    )
                    .selectBackgroundColor(HongColor.BLACK_100.hex)
                    .radius(
                        HongRadiusInfo(
                            all = 100
                        )
                    )
                    .tabTextHorizontalPadding(12)
                    .tabBetweenPadding(6)
                    .applyOption(),
            ) { i, item ->
                (item as? String)?.let {

                }
            }

            HongScrollTab(
                option = HongScrollTabBuilder()
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
                            all = 100
                        )
                    )
                    .tabTextHorizontalPadding(12)
                    .tabBetweenPadding(8)
                    .applyOption(),
            ) { i, item ->
                (item as? String)?.let {

                }
            }
        }
    }
}