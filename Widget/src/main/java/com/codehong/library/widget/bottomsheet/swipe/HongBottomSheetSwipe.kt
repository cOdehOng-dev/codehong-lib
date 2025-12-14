package com.codehong.library.widget.bottomsheet.swipe

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.header.HongHeaderCloseBuilder
import com.codehong.library.widget.header.HongHeaderCloseCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import kotlin.math.max
import kotlin.math.min

@Composable
fun HongBottomSheetSwipe(
    option: HongBottomSheetSwipeOption
) {
    val density = LocalDensity.current

    val bottomSheetMinHeight = option.bottomSheetMinHeight
    val bottomsheetMaxHeight = option.bottomSheetMaxHeight

    var bottomSheetHeight by remember(bottomSheetMinHeight) { mutableFloatStateOf(bottomSheetMinHeight) }
    var isDragging by remember { mutableStateOf(false) }
    var isMaxBottomSheetHeight by remember { mutableStateOf(false) }


    LaunchedEffect(bottomSheetHeight) {
        isMaxBottomSheetHeight = if (bottomSheetHeight >= bottomsheetMaxHeight) {
            true
        } else {
            false
        }
    }

    val animatedHeight by animateFloatAsState(
        targetValue = bottomSheetHeight,
        animationSpec = if (isDragging) tween(0) else tween(300),
        label = "height"
    )

    val contentScale by animateFloatAsState(
        targetValue = 1f - (bottomSheetHeight - bottomSheetMinHeight) / (bottomsheetMaxHeight - bottomSheetMinHeight) * 0.3f,
        animationSpec = if (isDragging) tween(0) else tween(300),
        label = "scale"
    )

    val contentOffsetY by animateFloatAsState(
        targetValue = -((bottomSheetHeight - bottomSheetMinHeight) / (bottomsheetMaxHeight - bottomSheetMinHeight) * 100f),
        animationSpec = if (isDragging) tween(0) else tween(300),
        label = "offsetY"
    )


    @SuppressLint("UseOfNonLambdaOffsetOverload")
    @Composable
    fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
                .offset(y = with(density) { contentOffsetY.dp })
                .scale(contentScale),
        ) {
            option.content.invoke(this)
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hongBackground(
                    color = option.backgroundColorHex
                )
                .padding(it)
        ) {
            HongHeaderCloseCompose(
                HongHeaderCloseBuilder()
                    .closeIconColor(option.closeIconColorHex)
                    .close { option.onCloseClick.invoke() }
                    .applyOption()
            )

            Content()

            // 바텀시트
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .hongWidth(HongLayoutParam.MATCH_PARENT.value)
                    .hongHeight(with(density) { animatedHeight }.toInt())
                    .hongBackground(
                        color = option.bottomSheetBackgroundColorHex,
                        radius = HongRadiusInfo(
                            topLeft = option.bottomSheetTopRadius,
                            topRight = option.bottomSheetTopRadius
                        )
                    )
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                isDragging = true
                            },
                            onDragEnd = {
                                isDragging = false
                            }
                        ) { _, dragAmount ->
                            val newHeight = bottomSheetHeight - dragAmount.y
                            bottomSheetHeight = max(bottomSheetMinHeight, min(bottomsheetMaxHeight, newHeight))
                        }
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .hongSpacing(
                                HongSpacingInfo(
                                    top = 10f,
                                    bottom = 4f
                                )
                            )
                            .hongWidth(40)
                            .hongHeight(6)
                            .hongBackground(
                                color = HongColor.GRAY_30,
                                radius = HongRadiusInfo(3)
                            )
                            .clickable {
                                bottomSheetHeight = if (bottomSheetHeight <= (bottomSheetMinHeight + 20f)) {
                                    bottomsheetMaxHeight
                                } else {
                                    bottomSheetMinHeight
                                }
                            }
                    )
                    option.bottomSheetContent.invoke(this)
                }
            }
        }
    }
}