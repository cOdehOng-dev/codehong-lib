package com.codehong.library.widget.videopopup

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.player.HongVideoPlayerCompose
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HongVideoPopupCompose(
    option: HongVideoPopupOption
) {
    if (option.videoPlayerOption.videoUrl.isNullOrEmpty()) return

    val remOption by remember { mutableStateOf(option) }

    var isVisible by remember { mutableStateOf(false) } // 광고 노출 여부
    var videoClearRef by remember { mutableStateOf<(() -> Unit)?>(null) }
    var isShow by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    val transition = updateTransition(targetState = isVisible, label = "popupVisibility")

    val offsetY by transition.animateDp(
        transitionSpec = { tween(durationMillis = 300) }, label = "offsetY"
    ) { visible -> if (visible) 0.dp else 300.dp }

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 150) }, label = "alpha"
    ) { visible -> if (visible) 1f else 0f }

    fun clear() {
        videoClearRef?.invoke()
    }

    fun dismiss(isClickClose: Boolean) {
        isVisible = false
        scope.launch {
            delay(300)
            clear()
            isShow = false
            remOption.onHide(isClickClose)
        }
    }

    LaunchedEffect(isShow) {
        remOption.showPopup(isShow)
    }

    val videoPlayerOption = HongVideoPlayerBuilder()
        .copy(remOption.videoPlayerOption)
        .onEnd {
            // 영상 끝날 때 닫기
            dismiss(true)
        }
        .onError {
            // 영상 오류 시 닫기
            dismiss(true)
        }
        .onReady {
            // 영상 준비 완료 시
            scope.launch {
                delay(50)
                isShow = true
                isVisible = true
                remOption.onShow()
            }
        }
        .onPlayerReference { clearFunc ->
            videoClearRef = clearFunc
        }
        .applyOption()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = alpha * 0.5f))
            .clickable(enabled = !remOption.blockTouchOutside) {
                dismiss(true)
            }
    ) {
        val landingLink = remOption.landingLink
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset { IntOffset(0, with(density) { offsetY.roundToPx() }) }
                .fillMaxWidth()
                .hongBackground(
                    color = HongColor.WHITE_100.hex,
                    radius = videoPlayerOption.radius,
                )
                .clickable { if (!landingLink.isNullOrEmpty()) remOption.clickLanding?.invoke(landingLink) }
        ) {
            HongVideoPlayerCompose(
                option = videoPlayerOption
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(color = colorResource(R.color.honglib_color_default)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonContent(
                    modifier = Modifier
                        .weight(1f),
                    buttonText = "오늘은 그만 보기",
                ) {
                    dismiss(false)
                }

                ButtonContent(
                    modifier = Modifier
                        .weight(1f),
                    buttonText = "닫기",
                ) {
                    dismiss(true)
                }
            }
        }
    }
}

@Composable
private fun ButtonContent(
    modifier: Modifier,
    buttonText: String,
    onClick: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxHeight()
            .clickable { onClick(false) },
        contentAlignment = Alignment.Center
    ) {
        HongTextCompose(
            option = HongTextBuilder()
                .margin(
                    HongSpacingInfo(
                        top = 8f,
                        bottom = 8f
                    )
                )
                .text(buttonText)
                .size(16)
                .color(HongColor.WHITE_100)
                .applyOption()
        )
    }
}