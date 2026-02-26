package com.codehong.library.widget.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.Consts.GRAPH_DASH_OFF
import com.codehong.library.widget.Consts.GRAPH_DASH_ON
import com.codehong.library.widget.Consts.GRAPH_GRID_COUNT

internal fun DrawScope.drawGridLines(
    colorHex: String,
    strokeWidthDp: Float
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(GRAPH_DASH_ON, GRAPH_DASH_OFF), 0f)
    for (i in 1..GRAPH_GRID_COUNT) {
        val y = size.height - (size.height / GRAPH_GRID_COUNT) * i
        drawLine(
            color = colorHex.toColor(),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidthDp.dp.toPx(),
            pathEffect = pathEffect
        )
    }
}
