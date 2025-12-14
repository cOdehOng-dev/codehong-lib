package com.codehong.library.widget.draganddrop

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropItemCard(
    modifier: Modifier = Modifier,
    item: Any,
    isShaking: Boolean,
    cardRadius: Dp = 0.dp,
    onLongClick: () -> Unit,
    onClick: () -> Unit ,
    content: @Composable (() -> Unit)
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shake_animation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    val currentRotation = if (isShaking) rotation else 0f

    Card(
        modifier = Modifier
            .then(modifier)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(cardRadius)
            )
            .graphicsLayer { rotationZ = currentRotation }
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            ),
        elevation = CardDefaults
            .cardElevation(10.dp),
        shape = RoundedCornerShape(cardRadius),
    ) {
        if (isShaking) {
            DragTarget(
                dataToDrop = item
            ) {
                content()
            }
        } else {
            content()
        }
    }
}