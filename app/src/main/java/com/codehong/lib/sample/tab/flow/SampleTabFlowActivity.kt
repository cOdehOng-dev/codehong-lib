package com.codehong.lib.sample.tab.flow

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.tab.flow.HongTabFlowBuilder
import com.codehong.library.widget.tab.flow.HongTabFlowCompose
import com.codehong.library.widget.tab.flow.HongTabFlowView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleTabFlowActivity : BaseSampleMixActivity() {


    private val tabList = listOf(
        "거리", "시간", "스피드",
        "칼로리", "걸음수",
    )
    private val option1 = HongTabFlowBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .tabList(tabList)
        .initialSelectedIndex(0)
        .maxRowCount(3)
        .betweenTabSpacing(10)
        .rowSpacing(10)
        .onSelect {
            Log.d("TAG", "Selected index: $it")
        }
        .applyOption()

    override fun optionViewList(): List<View> {
        return listOf(
            HongTabFlowView(this).set(option1),
        )
    }

    @Composable
    override fun InitCompose() {
        HongTabFlowCompose(
            option = HongTabFlowBuilder()
                .copy(option1)
                .applyOption(),
        )
    }
}