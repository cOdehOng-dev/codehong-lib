package com.codehong.library.widget.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
fun SlideVisibleLayout(
    isVisible: Boolean,
    duration: Int = 300,
    content: @Composable (AnimatedVisibilityScope) -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it }, // 아래에서 위로
            animationSpec = tween(durationMillis = duration, easing = FastOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }, // 위에서 아래로
            animationSpec = tween(durationMillis = duration, easing = FastOutSlowInEasing)
        )
    ) {
        content(this)
    }
}
