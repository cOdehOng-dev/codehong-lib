package com.codehong.library.widget

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.util.getColor

/**
 * 클릭 ripple 이벤트 삭제
 */
inline fun Modifier.disableRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

fun Modifier.inpkBorder(
    borderWidth: Int,
    borderColor: HongComposeColor,
    backgroundColor: HongComposeColor = HongComposeColor(),
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0
): Modifier = composed {
    this
        .border(
            border = BorderStroke(
                borderWidth.dp,
                borderColor.getColor()
            ),
            shape = RoundedCornerShape(
                topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
            )
        )
        .roundBackground(
            color = backgroundColor,
            allRadius = allRadius,
            topRadius = topRadius,
            bottomRadius = bottomRadius,
            topStartRadius = topStartRadius,
            topEndRadius = topEndRadius,
            bottomStartRadius = bottomStartRadius,
            bottomEndRadius = bottomEndRadius
        )
}

fun Modifier.roundBackground(
    color: HongComposeColor,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0
): Modifier = composed {
    this.background(
        color = color.getColor(),
        shape = RoundedCornerShape(
            topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
            topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
            bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
            bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
        )
    )
}

fun Modifier.circleBackground(
    color: HongComposeColor
): Modifier = composed {
    this.background(
        color = color.getColor(),
        shape = CircleShape
    )
}

// region shadow
fun Modifier.dropShadow(
    color: Color = Color.Black.copy(0.25f),
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    blur: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.drawBehind {
    val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
    val shadowOutline = RoundedCornerShape(
        topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
        topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
        bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
        bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
    ).createOutline(shadowSize, layoutDirection, this)

    val paint = Paint().apply {
        this.color = color
    }

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}

fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    blur: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.drawBehind {
    val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

    val paint = Paint().apply {
        this.color = color
    }

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}

@Composable
fun Modifier.inpkBorderShadow(
    borderColor: HongComposeColor,
    borderWidth: Int,
    backgroundColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_transparent
    ),
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    shadowColor: Color = Color.Black.copy(0.25f),
    blur: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    spread: Dp = 0.dp
): Modifier {
    return this
        .dropShadow(
            shadowColor,
            allRadius,
            topRadius,
            bottomRadius,
            topStartRadius,
            topEndRadius,
            bottomStartRadius,
            bottomEndRadius,
            blur,
            offsetY,
            offsetX,
            spread
        )
        .border(
            border = BorderStroke(
                borderWidth.dp,
                borderColor.getColor()
            ),
            shape = RoundedCornerShape(
                topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
            )
        )
        .roundBackground(
            color = backgroundColor,
            allRadius = allRadius,
            topRadius = topRadius,
            bottomRadius = bottomRadius,
            topStartRadius = topStartRadius,
            topEndRadius = topEndRadius,
            bottomStartRadius = bottomStartRadius,
            bottomEndRadius = bottomEndRadius
        )
}
// endregion shadow
