package com.codehong.lib.sample.tab

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.tab.HongScrollTabBuilder
import com.codehong.library.widget.tab.HongScrollTabCompose
import com.codehong.library.widget.tab.HongScrollTabView
import com.codehong.library.widget.util.HongToastUtil

class SampleScrollTabActivity : BaseSampleMixActivity() {

    private val option1 = HongScrollTabBuilder()
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
                topLeft = 100,
                topRight = 100,
                bottomLeft = 100,
                bottomRight = 100
            )
        )
        .onTabClick { index, item ->
            HongToastUtil.showToast(this, "선택된 탭: $index, $item")
        }
        .tabTextHorizontalPadding(12)
        .tabBetweenPadding(6)
        .applyOption()

    private val option2 = HongScrollTabBuilder()
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
        .onTabClick { index, item ->
            HongToastUtil.showToast(this, "선택된 탭: $index, $item")
        }
        .tabTextHorizontalPadding(12)
        .tabBetweenPadding(8)
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongScrollTabView(this@SampleScrollTabActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongScrollTabCompose(it)
        }
    }
}