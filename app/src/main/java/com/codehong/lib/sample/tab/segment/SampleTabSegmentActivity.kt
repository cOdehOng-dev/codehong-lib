package com.codehong.lib.sample.tab.segment

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.tab.segment.HongTabSegmentBuilder
import com.codehong.library.widget.tab.segment.HongTabSegmentCompose
import com.codehong.library.widget.tab.segment.HongTabSegmentView

class SampleTabSegmentActivity : BaseSampleMixActivity() {

    private val option = HongTabSegmentBuilder()
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

    private val option2 = HongTabSegmentBuilder()
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
        .indicatorColor(HongColor.MAIN_ORANGE_100)
        .backgroundColor(HongColor.GRAY_10)
        .tabTextList(listOf("추천", "계좌"))
        .onTabClick {
            Log.d("TAG", "selected tab index: $it")
        }
        .applyOption()
    
    private val optionList get() = listOf(
        option,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(
                    HongTabSegmentView(this@SampleTabSegmentActivity).set(it)
                )
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTabSegmentCompose(it)
        }

    }
}