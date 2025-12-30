package com.codehong.library.widget.graph.line

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.graph.HongGraphBuilder
import com.codehong.library.widget.graph.HongGraphOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongGraphLineCompose(
    option: HongGraphOption,
) {
    if (option.graphPointList.isEmpty()) return

    val containerOption = HongGraphBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(option.padding)
        .applyOption()

    HongWidgetContainer(containerOption) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(option.graphHeight.dp)
            ) {
                val totalPoints = option.graphPointList.size
                val maxDistance = option.graphPointList.maxOfOrNull { it.point } ?: 1.0

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height
                    val paddingPx = 24.dp.toPx()
                    val innerWidth = width - paddingPx * 2
                    val gridLines = 4
                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)

                    for (i in 1..gridLines) {
                        val y = height - (height / gridLines) * i
                        drawLine(
                            color = option.dotLineColorHex.toColor(),
                            start = Offset(0f, y),
                            end = Offset(width, y),
                            strokeWidth = option.dotLineWidth.dp.toPx(),
                            pathEffect = pathEffect
                        )
                    }

                    val path = Path()
                    option.graphPointList.forEachIndexed { index, dataPoint ->
                        val x = paddingPx + (innerWidth / (totalPoints - 1)) * index
                        val y = height - (dataPoint.point / maxDistance).toFloat() * height

                        if (index == 0) {
                            path.moveTo(x, y)
                        } else {
                            path.lineTo(x, y)
                        }
                    }

                    // 그래프 선
                    drawPath(
                        path = path,
                        color = option.graphColorHex.toColor(),
                        style = Stroke(
                            width = option.graphLineWidth.dp.toPx(),
                            cap = StrokeCap.Round,
                            pathEffect = PathEffect.cornerPathEffect(40f)
                        )
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = option.dividerWidth.dp,
                color = option.dividerColorHex.toColor()
            )

            Row(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                option.graphPointList.forEach { point ->
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
