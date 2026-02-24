package com.codehong.library.widget.swipe

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HongSwipeContainer(
    option: HongSwipeContainerOption
) {
    val buttonWidthDp = 80.dp
    val density = LocalDensity.current
    val buttonWidthPx = with(density) { buttonWidthDp.toPx() }

    // onSizeChanged로 컨테이너 실제 너비 측정 (BoxWithConstraints 대신)
    var containerWidthPx by remember { mutableStateOf(0f) }

    val offsetX = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    val draggableState = rememberDraggableState { delta ->
        coroutineScope.launch {
            if (containerWidthPx <= 0f) return@launch
            val newRaw = offsetX.value + delta
            // 85% 지점까지 자유 드래그, 이후 rubber-band
            val newValue = if (newRaw < -containerWidthPx * 0.85f) {
                val overscroll = newRaw + containerWidthPx * 0.85f
                (-containerWidthPx * 0.85f + overscroll * 0.3f)
                    .coerceAtLeast(-containerWidthPx)
            } else {
                newRaw.coerceIn(-containerWidthPx, 0f)
            }
            offsetX.snapTo(newValue)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .onSizeChanged { size -> containerWidthPx = size.width.toFloat() }
    ) {
        // 삭제 영역: 컨테이너 전체를 채우고, 콘텐츠가 밀릴수록 같이 늘어나는 효과
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hongBackground(option.buttonColorHex)
                .clickable {
                    coroutineScope.launch {
                        offsetX.animateTo(0f, animationSpec = spring(stiffness = Spring.StiffnessMedium))
                    }
                    option.onClickButton()
                }
        ) {
            // offset { } 은 placement 단계에서 실행되므로 offsetX.value 를 읽어도 recomposition 없이 re-layout만 발생
            // 버튼 중심 = 오른쪽 끝에서 (노출 너비 / 2) → 항상 노출 영역 중앙 유지
            val buttonSize = 50.dp
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset {
                        val buttonHalfWidthPx = (buttonSize / 2).toPx()
                        // CenterEnd 기준에서 왼쪽으로 이동: (노출영역 중심 - 버튼 반폭) 만큼
                        IntOffset(
                            x = (buttonHalfWidthPx + offsetX.value / 2).roundToInt(),
                            y = 0
                        )
                    }
                    .width(buttonSize)
                    .height(buttonSize),
                // 풀스와이프 임계값 초과 시 색상 반전으로 "놓으면 삭제" 피드백
                contentAlignment = Alignment.Center
            ) {
                HongTextCompose(
                    HongTextBuilder()
                        .text(option.buttonText)
                        .typography(option.buttonTextTypo)
                        .color(option.buttonTextColorHex)
                        .applyOption()
                )
            }
        }

        // 콘텐츠 (전경) - 밀릴수록 빨간 영역이 늘어나 보임
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        coroutineScope.launch {
                            val fullSwipeThreshold = containerWidthPx * 0.7f
                            when {
                                // 풀스와이프: 컨테이너 70% 초과 OR 빠른 스와이프 → 화면 끝까지 슬라이드 후 삭제
                                offsetX.value <= -fullSwipeThreshold ||
                                        (velocity < -1000f && offsetX.value < -buttonWidthPx * 0.5f) -> {
                                    offsetX.animateTo(
                                        -containerWidthPx,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioNoBouncy,
                                            stiffness = Spring.StiffnessHigh
                                        )
                                    )
                                    option.onClickButton()
                                }
                                // 절반 이상 당겼으면 삭제 버튼 노출 유지
                                offsetX.value < -buttonWidthPx / 2f -> {
                                    offsetX.animateTo(
                                        -buttonWidthPx,
                                        animationSpec = spring(stiffness = Spring.StiffnessMedium)
                                    )
                                }
                                // 그 외: 원위치
                                else -> {
                                    offsetX.animateTo(
                                        0f,
                                        animationSpec = spring(stiffness = Spring.StiffnessMedium)
                                    )
                                }
                            }
                        }
                    }
                )
        ) {
            option.content()
        }
    }
}
