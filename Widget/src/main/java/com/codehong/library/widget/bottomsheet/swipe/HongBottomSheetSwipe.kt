package com.codehong.library.widget.bottomsheet.swipe

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.header.close.HongHeaderCloseBuilder
import com.codehong.library.widget.header.close.HongHeaderCloseCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import kotlin.math.max
import kotlin.math.min

private val LocalOption = compositionLocalOf { HongBottomSheetSwipeOption() }
private val LocalContentScale = compositionLocalOf { 1f }
private val LocalContentOffsetY = compositionLocalOf { 0f }

@Composable
fun HongBottomSheetSwipe(
    option: HongBottomSheetSwipeOption
) {
    val remOption by remember { mutableStateOf(option) }

    val bottomSheetMinHeight by rememberSaveable { mutableFloatStateOf(remOption.bottomSheetMinHeight) }
    val bottomsheetMaxHeight by rememberSaveable { mutableFloatStateOf(remOption.bottomSheetMaxHeight) }

    var bottomSheetHeight by rememberSaveable { mutableFloatStateOf(bottomSheetMinHeight) }
    var isDragging by rememberSaveable { mutableStateOf(false) }

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

    CompositionLocalProvider(
        LocalOption provides remOption,
        LocalContentScale provides contentScale,
        LocalContentOffsetY provides contentOffsetY,
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets.systemBars
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hongBackground(
                        color = remOption.backgroundColorHex
                    )
                    .padding(it)
            ) {
                HongHeaderCloseCompose(
                    HongHeaderCloseBuilder()
                        .closeIconColor(remOption.closeIconColorHex)
                        .close { remOption.onCloseClick() }
                        .applyOption()
                )

                MainContent()

                BottomSheetContent(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .hongWidth(HongLayoutParam.MATCH_PARENT.value)
                        .hongHeight(animatedHeight.toInt())
                        .hongBackground(
                            color = option.bottomSheetBackgroundColorHex,
                            radius = HongRadiusInfo(
                                topLeft = option.bottomSheetTopRadius,
                                topRight = option.bottomSheetTopRadius
                            )
                        ),
                    onDragAmount = { dragAmount ->
                        val newHeight = bottomSheetHeight - dragAmount.y
                        bottomSheetHeight = max(bottomSheetMinHeight, min(bottomsheetMaxHeight, newHeight))
                    },
                    onDragging = { isDrag ->
                        isDragging = isDrag
                    },
                    onContentClick = {
                        bottomSheetHeight = if (bottomSheetHeight <= (bottomSheetMinHeight + 20f)) {
                            bottomsheetMaxHeight
                        } else {
                            bottomSheetMinHeight
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MainContent() {
    val option = LocalOption.current
    val contentScale = LocalContentScale.current
    val contentOffsetY = LocalContentOffsetY.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .offset(y = contentOffsetY.dp)
            .scale(contentScale),
    ) {
        option.content(this)
    }
}

@Composable
private fun BottomSheetContent(
    modifier: Modifier = Modifier,
    onDragAmount: (Offset) -> Unit,
    onDragging: (Boolean) -> Unit,
    onContentClick: () -> Unit
) {
    val option = LocalOption.current

    Box(
        modifier = Modifier
            .then(modifier)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        onDragging(true)
                    },
                    onDragEnd = {
                        onDragging(false)
                    }
                ) { _, dragAmount ->
                    onDragAmount(dragAmount)
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
                        onContentClick()
                    }
            )
            option.bottomSheetContent(this)
        }
    }
}
