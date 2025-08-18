package com.codehong.lib.sample.graph

import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.graph.HongGraphBuilder
import com.codehong.library.widget.graph.line.HongGraphLine
import com.codehong.library.widget.rule.graph.GraphPoint

class SampleGraphLineActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        val samplePointList = listOf(
            GraphPoint("Jan", 20.0),
            GraphPoint("Feb", 45.0),
            GraphPoint("Mar", 30.0),
            GraphPoint("Apr", 60.0),
            GraphPoint("May", 50.0),
            GraphPoint("Jun", 80.0)
        )
        HongGraphLine(
            option = HongGraphBuilder()
                .graphPointList(samplePointList)
                .applyOption()
        )
    }
}