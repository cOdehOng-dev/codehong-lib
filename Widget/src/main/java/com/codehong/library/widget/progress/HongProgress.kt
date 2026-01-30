package com.codehong.library.widget.progress

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R

@Composable
fun HongProgress(
    modifier: Modifier = Modifier
) {
    // 무한 회전 애니메이션 (1초에 한 바퀴)
    val infiniteTransition = rememberInfiniteTransition(label = "progress_rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation_animation"
    )

    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { /* 터치 이벤트 차단 */ },
        contentAlignment = Alignment.Center
    ) {
        // 배경 이미지
        Image(
            modifier = Modifier
                .size(48.dp),
            painter = painterResource(id = R.drawable.bg_indicator),
            contentDescription = null
        )

        // 회전하는 Progress 아이콘
        Image(
            modifier = Modifier
                .size(20.dp)
                .rotate(rotation),
            painter = painterResource(id = R.drawable.honglib_ic_indicator),
            contentDescription = "Loading"
        )
    }
}