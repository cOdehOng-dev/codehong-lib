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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.player.HongVideoPlayerCompose
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.extensions.hongBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HongComposeVideoPopupView(
    option: HongVideoPopupOption,
    onShow: () -> Unit = {},
    onHide: (isClickClose: Boolean) -> Unit = { _ -> },
    showPopup: (Boolean) -> Unit = {},
    clickLanding: ((String?) -> Unit)? = null,
) {
    if (option.videoPlayerOption.videoUrl.isNullOrEmpty()) return

    var isVisible by remember { mutableStateOf(false) } // 광고 노출 여부
    var isAnimating by remember { mutableStateOf(false) } // 슬라이드 아웃 중 여부
    var isClickNoShow by remember { mutableStateOf(false) } // 일주일 또는 닫기 버튼 클릭 여부
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
        isClickNoShow = isClickClose
        isAnimating = true
        isVisible = false
        scope.launch {
            delay(300)
            clear()
            isShow = false
            isAnimating = false
            onHide(isClickClose)
        }
    }

    LaunchedEffect(isShow) {
        showPopup.invoke(isShow)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = alpha * 0.5f)) // dim 처리
            .clickable(enabled = !option.blockTouchOutside) {
                dismiss(true)
            }
    ) {
        val landingLink = option.landingLink
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset { IntOffset(0, with(density) { offsetY.roundToPx() }) }
                .fillMaxWidth()
                .hongBackground(
                    color = HongColor.WHITE_100.hex,
                    radius = option.videoPlayerOption.radius,
                )
//                .clip(RoundedCornerShape(topStart = topRadius.dp, topEnd = topRadius.dp))
//                .background(Color.White)
                .clickable { if (!landingLink.isNullOrEmpty()) clickLanding?.invoke(landingLink) }
        ) {
            HongVideoPlayerCompose(
                option = option.videoPlayerOption,
                onEnd = {
                    // 영상 끝날 때 닫기
                    dismiss(true)
                },
                onError = {
                    // 영상 오류 시 닫기
                    dismiss(true)
                },
                onReady = {
                    // 영상 준비 완료 시
                    scope.launch {
                        delay(50)
                        isShow = true
                        isVisible = true
                        onShow()
                    }
                },
                onPlayerReference = { clearFunc ->
                    videoClearRef = clearFunc
                }
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(color = colorResource(R.color.honglib_color_default)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { dismiss(false) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "오늘은 그만 보기",
                        color = Color.White,
                        fontSize = dpToSp(16),
                        fontFamily = FontFamily(Font(R.font.pretendard_400)),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { dismiss(true) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "닫기",
                        color = Color.White,
                        fontSize = dpToSp(16),
                        fontFamily = FontFamily(Font(R.font.pretendard_400)),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}
