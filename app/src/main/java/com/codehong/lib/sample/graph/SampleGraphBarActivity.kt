package com.codehong.lib.sample.graph

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.graph.HongGraphBuilder
import com.codehong.library.widget.graph.bar.HongGraphBarCompose
import com.codehong.library.widget.graph.bar.HongGraphBarView
import com.codehong.library.widget.rule.graph.GraphPoint

class SampleGraphBarActivity : BaseSampleMixActivity() {

    private val option = HongGraphBuilder()
        .graphPointList(
            listOf(
                GraphPoint("Jan", 20.0),
                GraphPoint("Feb", 45.0),
                GraphPoint("Mar", 30.0),
                GraphPoint("Apr", 60.0),
                GraphPoint("May", 50.0),
                GraphPoint("Jun", 80.0)
            )
        )
        .applyOption()

    override fun optionViewList(): List<View> {
        return listOf(
            HongGraphBarView(this).set(option)
        )
    }

    @Composable
    override fun InitCompose() {
        HongGraphBarCompose(option)
    }
}