package com.codehong.library.widget.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundCornerShape
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornerShape

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


fun Modifier.hongWidth(
    width: Int
): Modifier {
    return if (width > 0) {
        Modifier
            .then(this)
            .width(width.dp)
    } else {
        if (width == HongLayoutParam.MATCH_PARENT.value) {
            Modifier
                .then(this)
                .fillMaxWidth()
        } else {
            Modifier
                .then(this)
                .wrapContentWidth()
        }
    }
}

fun Modifier.hongHeight(
    height: Int
): Modifier {
    return if (height > 0) {
        Modifier
            .then(this)
            .height(height.dp)
    } else {
        if (height == HongLayoutParam.MATCH_PARENT.value) {
            Modifier
                .then(this)
                .fillMaxHeight()
        } else {
            Modifier
                .then(this)
                .wrapContentHeight()
        }
    }
}

fun Modifier.hongSpacing(
    padding: HongSpacingInfo
): Modifier {
    return Modifier
        .then(this)
        .padding(
            start = padding.left.dp,
            top = padding.top.dp,
            end = padding.right.dp,
            bottom = padding.bottom.dp
        )
}

// region shadow
fun Modifier.dropShadow(
    radius: HongRadiusInfo = HongRadiusInfo(),
    shadow: HongShadowInfo = HongShadowInfo(),
    useShapeCircle: Boolean = false
) = this.drawBehind {
    val shadowSize =
        Size(size.width + shadow.spread.dp.toPx(), size.height + shadow.spread.dp.toPx())
    // CircleShape 사용 여부에 따라 Outline 결정
    val shadowOutline = if (useShapeCircle) {
        // 정사각형 기준으로 원형 outline 생성
        val diameter = minOf(shadowSize.width, shadowSize.height)
        val circleSize = Size(diameter, diameter)
        CircleShape.createOutline(circleSize, layoutDirection, this)
    } else {
        radius.toRoundedCornerShape().createOutline(shadowSize, layoutDirection, this)
    }

    val paint = Paint().apply {
        this.color = shadow.color.toColor()
    }

    if (shadow.blur.dp.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(shadow.blur.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(shadow.offsetX.dp.toPx(), shadow.offsetY.dp.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}

fun Modifier.dropShadow(
    shape: Shape,
    shadowInfo: HongShadowInfo = HongShadowInfo(),
) = this.drawBehind {
    val shadowSize =
        Size(size.width + shadowInfo.spread.dp.toPx(), size.height + shadowInfo.spread.dp.toPx())
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

    val paint = Paint().apply {
        this.color = shadowInfo.color.toColor()
    }

    if (shadowInfo.blur.dp.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(shadowInfo.blur.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(shadowInfo.offsetX.dp.toPx(), shadowInfo.offsetY.dp.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}
// endregion shadow


fun Modifier.hongBackground(
    color: HongColor = HongColor.TRANSPARENT,
    border: HongBorderInfo = HongBorderInfo(),
    shadow: HongShadowInfo = HongShadowInfo(),
    radius: HongRadiusInfo = HongRadiusInfo(),
    useShapeCircle: Boolean = false,
): Modifier {
    return hongBackground(
        color = color.hex,
        border = border,
        shadow = shadow,
        radius = radius,
        useShapeCircle = useShapeCircle
    )
}

fun Modifier.hongBackground(
    color: String = HongColor.TRANSPARENT.hex,
    border: HongBorderInfo = HongBorderInfo(),
    shadow: HongShadowInfo = HongShadowInfo(),
    radius: HongRadiusInfo = HongRadiusInfo(),
    useShapeCircle: Boolean = false,
): Modifier {
    if (shadow.color == HongColor.TRANSPARENT.hex) {
        if (border.width == 0) {
            return Modifier
                .then(this)
                .clip(
                    shape = radius.toRoundCornerShape(useShapeCircle)
                )
                .background(
                    color = color.toColor(),
                    shape = radius.toRoundCornerShape(useShapeCircle)
                )
        }

        return Modifier
            .then(this)
            .border(
                border = BorderStroke(
                    border.width.dp,
                    border.color.toColor()
                ),
                shape = radius.toRoundCornerShape(useShapeCircle)
            )
            .clip(
                shape = radius.toRoundCornerShape(useShapeCircle)
            )
            .background(
                color = color.toColor(),
                shape = radius.toRoundCornerShape(useShapeCircle)
            )
    }

    if (border.width == 0) {
        return Modifier
            .then(this)
            .dropShadow(
                shadow = shadow,
                radius = radius,
                useShapeCircle = useShapeCircle
            )
            .clip(
                shape = radius.toRoundCornerShape(useShapeCircle)
            )
            .background(
                color = color.toColor(),
                shape = radius.toRoundCornerShape(useShapeCircle)
            )
    }

    return Modifier
        .then(this)
        .dropShadow(
            shadow = shadow,
            radius = radius,
            useShapeCircle = useShapeCircle
        )
        .border(
            border = BorderStroke(
                border.width.dp,
                border.color.toColor()
            ),
            shape = radius.toRoundCornerShape(useShapeCircle)
        )
        .clip(
            shape = radius.toRoundCornerShape(useShapeCircle)
        )
        .background(
            color = color.toColor(),
            shape = radius.toRoundCornerShape(useShapeCircle)
        )
}

fun Modifier.shimmerEffect() = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "ShimmerEffectAnimation"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFECF0F1),
                Color(0xFFBDC3C7),
                Color(0xFFECF0F1),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

