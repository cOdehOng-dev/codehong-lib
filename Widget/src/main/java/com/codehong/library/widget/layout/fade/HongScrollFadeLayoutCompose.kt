package com.codehong.library.widget.layout.fade

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.dpToPx
import kotlin.math.abs

@Composable
fun HongScrollFadeLayoutCompose(
    option: HongScrollFadeLayoutOption,
//    mainContentHeightDp: Int,
//    headerContent: @Composable (Boolean, Float) -> Unit,
//    mainContent: @Composable () -> Unit,
//    subContentList: (LazyListScope) -> Unit,
//    bottomContent: @Composable () -> Unit = {}
) {
    val scrollState = rememberLazyListState()
    var mainContentOffset by remember { mutableIntStateOf(0) }
    var previousOffset by remember { mutableIntStateOf(0) }
    val itemHeight = dpToPx(dp = option.mainContentHeightDp) / 2

    var targetAlpha by remember { mutableFloatStateOf(0f) }

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = 500),
        label = "AlphaAnimation"
    )

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hongBackground(
                        color = option.backgroundColorHex,
                    )
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
                                .height(option.mainContentHeightDp.dp)
                                .background(HongColor.TRANSPARENT.hex.toColor())
                                .onGloballyPositioned { layoutCoordinates ->
                                    mainContentOffset = layoutCoordinates.positionInParent().y.toInt()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            option.mainContent()
                        }
                    }
                    option.subContentList(this)
                }

                option.headerContent(targetAlpha == 0f, animatedAlpha)
            }
        },
        bottomBar = option.bottomContent
    )

    // 첫 번째 아이템이 반 이상 사라졌는지 + 완전히 사라졌는지 + 스크롤 방향 체크
    LaunchedEffect(mainContentOffset) {
        val isScrollingDown = mainContentOffset < previousOffset

        // 작은 움직임 무시 (미세한 스크롤 반응 제거)
        if (abs(mainContentOffset - previousOffset) < 10) return@LaunchedEffect

        // 첫 번째 아이템이 완전히 사라졌다면 배경색을 흰색으로 유지
        when {
            mainContentOffset <= -itemHeight * 2 -> {
                // 첫 번째 아이템이 완전히 사라짐! 헤더 흰색 유지
                targetAlpha = 1f // 완전히 흰색 고정
            }

            mainContentOffset <= -itemHeight * 0.7f -> { // 변화 시작 지점 완화
                targetAlpha = if (isScrollingDown) 1f else 0f
            }

            else -> {
                targetAlpha = 0f
            }
        }

        previousOffset = mainContentOffset
    }
}