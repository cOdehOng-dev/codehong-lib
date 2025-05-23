package com.codehong.library.widget.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun HongSlideVisibleLayout(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    duration: Int = 300,
    onAnimationEnd: (() -> Unit)? = null,
    content: @Composable (AnimatedVisibilityScope) -> Unit
) {
    val transition = updateTransition(targetState = isVisible, label = "VisibilityTransition")
    AnimatedVisibility(
        modifier = modifier,
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

    // 애니메이션이 끝났을 때 콜백 실행
    LaunchedEffect(transition.currentState, !transition.isRunning) {
        if (!transition.isRunning && transition.currentState) {
            onAnimationEnd?.invoke()
        }
    }
}
