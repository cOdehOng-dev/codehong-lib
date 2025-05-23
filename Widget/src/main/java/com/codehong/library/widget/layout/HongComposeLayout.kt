package com.codehong.library.widget.layout

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.util.dpToPx
import kotlin.math.abs

@Composable
fun FadeAnimHeaderLayout(
    firstItemHeight: Int,
    header: @Composable (Boolean, Float) -> Unit,
    firstContent: @Composable () -> Unit,
    contentList: (LazyListScope) -> Unit,
    tabBar: @Composable () -> Unit
) {
    val scrollState = rememberLazyListState()
    var firstItemOffset by remember { mutableStateOf(0) }
    var previousOffset by remember { mutableStateOf(0) }
    val itemHeight = dpToPx(dp = firstItemHeight) / 2 // 첫 번째 아이템 높이의 절반

    // 애니메이션을 적용한 배경 투명도 (0f = 투명, 1f = 흰색)
    var targetAlpha by remember { mutableStateOf(0f) }

    // 애니메이션 적용 (속도 완화)
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = 500), // 0.5초 동안 부드럽게 변경
        label = "AlphaAnimation"
    )

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(firstItemHeight.dp)
                                .background(colorResource(id = R.color.honglib_transparent))
                                .onGloballyPositioned { layoutCoordinates ->
                                    firstItemOffset = layoutCoordinates.positionInParent().y.toInt()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            firstContent()
                        }
                    }
                    contentList(this)
                }

                header(targetAlpha == 0f, animatedAlpha)
            }
        },
        bottomBar = tabBar
    )

    // 첫 번째 아이템이 반 이상 사라졌는지 + 완전히 사라졌는지 + 스크롤 방향 체크
    LaunchedEffect(firstItemOffset) {
        val isScrollingDown = firstItemOffset < previousOffset

        // 작은 움직임 무시 (미세한 스크롤 반응 제거)
        if (abs(firstItemOffset - previousOffset) < 10) return@LaunchedEffect

        // 첫 번째 아이템이 완전히 사라졌다면 배경색을 흰색으로 유지
        when {
            firstItemOffset <= -itemHeight * 2 -> {
                // 첫 번째 아이템이 완전히 사라짐! 헤더 흰색 유지
                targetAlpha = 1f // 완전히 흰색 고정
            }

            firstItemOffset <= -itemHeight * 0.7f -> { // 변화 시작 지점 완화
                targetAlpha = if (isScrollingDown) 1f else 0f
            }

            else -> {
                targetAlpha = 0f
            }
        }

        previousOffset = firstItemOffset
    }
}