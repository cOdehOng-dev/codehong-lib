package com.codehong.library.widget.graph.bar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.Consts.GRAPH_CANVAS_HORIZONTAL_PADDING_DP
import com.codehong.library.widget.extensions.drawGridLines
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.graph.HongGraphBuilder
import com.codehong.library.widget.graph.HongGraphOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongGraphBarCompose(
    option: HongGraphOption
) {
    if (option.graphPointList.isEmpty()) return

    val containerOption = HongGraphBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(option.padding)
        .applyOption()

    HongWidgetContainer(containerOption) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(option.graphHeight.dp)
            ) {
                val pointListSize = option.graphPointList.size
                val maxDistance = option.graphPointList.maxOfOrNull { it.point } ?: 1.0

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val paddingPx = GRAPH_CANVAS_HORIZONTAL_PADDING_DP.dp.toPx()
                    val innerWidth = size.width - paddingPx * 2

                    drawGridLines(option.dotLineColorHex, option.dotLineWidth)

                    val barWidth = innerWidth / (pointListSize * 1.5f)
                    option.graphPointList.forEachIndexed { index, dataPoint ->
                        val sectionWidth = innerWidth / pointListSize
                        val xStart = paddingPx + sectionWidth * index
                        val x = xStart + (sectionWidth - barWidth) / 2
                        val barHeight = (dataPoint.point / maxDistance).toFloat() * size.height
                        drawRect(
                            color = option.graphColorHex.toColor(),
                            topLeft = Offset(x, size.height - barHeight),
                            size = Size(barWidth, barHeight)
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = option.dividerWidth.dp,
                color = option.dividerColorHex.toColor()
            )

            Row(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .fillMaxWidth()
                    .padding(horizontal = GRAPH_CANVAS_HORIZONTAL_PADDING_DP.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                option.graphPointList.forEach { point ->
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .text(point.label)
                                .typography(option.labelTypo)
                                .color(option.labelColorHex)
                                .textAlign(HongTextAlign.CENTER)
                                .applyOption()
                        )
                    }
                }
            }
        }
    }
}
