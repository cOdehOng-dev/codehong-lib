package com.codehong.library.widget.draganddrop

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropItem(
    modifier: Modifier = Modifier,
    item: Any,
    isShaking: Boolean,
    onLongClick: () -> Unit,
    onClick: () -> Unit,
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

    Box(
        modifier = Modifier
            .then(modifier)
            .graphicsLayer { rotationZ = currentRotation }
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            ),
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