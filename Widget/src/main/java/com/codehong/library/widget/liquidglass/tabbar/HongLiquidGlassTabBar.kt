package com.codehong.library.widget.liquid.tabbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.liquidGlass
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.image.def.HongImageCompose
import com.codehong.library.widget.liquidglass.tabbar.HongLiquidGlassTabBarOption
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HongLiquidGlassTabBar(option: HongLiquidGlassTabBarOption) {

    var selectedIndex by remember { mutableIntStateOf(0) }
    var tabWidthPx by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val indicatorOffset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    // [탭바 확대]
    val tabBarScale by animateFloatAsState(
        targetValue = if (isDragging) 1.05f else 1f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 300f),
        label = "TabBarScale"
    )

    // [젤리 효과]
    val bulgeIntensity by animateFloatAsState(
        targetValue = if (isDragging) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 300f),
        label = "Bulge"
    )

    // [렌즈 투명도]
    val lensEffectAlpha by animateFloatAsState(
        targetValue = if (isDragging) 1f else 0.3f,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "LensAlpha"
    )

    // [인디케이터 베이스 색상 설정]
    val targetBaseColor = if (option.isDarkTheme) {
        HongColor.WHITE_100.toColor().copy(alpha = 0.05f)
    } else {
        HongColor.GRAY_15.toColor()
    }

    val dragAlpha = if (option.isDarkTheme) 0.01f else 0.06f

    val animatedBaseColor by animateColorAsState(
        targetValue = if (isDragging) targetBaseColor.copy(alpha = dragAlpha) else targetBaseColor,
        animationSpec = tween(durationMillis = 300),
        label = "IndicatorColor"
    )

    val tabBarShape = RoundedCornerShape(option.outerRadius.dp)
    val indicatorShape = RoundedCornerShape(option.outerRadius.dp - option.verticalPadding.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        // A. 배경 탭바
        Box(
            modifier = Modifier
                .scale(tabBarScale)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .hongHeight(option.tabBarHeight)
                .liquidGlass(tabBarShape, option.isDarkTheme)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = option.innerSideGap.dp)
                    .onGloballyPositioned { coordinates ->
                        if (tabWidthPx == 0f) {
                            tabWidthPx = coordinates.size.width.toFloat() / option.tabList.size
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { offset ->
                                val index = (offset.x / tabWidthPx).toInt()
                                    .coerceIn(0, option.tabList.lastIndex)
                                selectedIndex = index
                                option.onSelectedTab(selectedIndex, option.tabList[selectedIndex])
                                scope.launch {
                                    indicatorOffset.animateTo(
                                        index * tabWidthPx,
                                        spring(0.7f, 400f)
                                    )
                                }
                            },
                            onPress = { tryAwaitRelease() }
                        )
                    }
                    .pointerInput(tabWidthPx) {
                        if (tabWidthPx == 0f) return@pointerInput
                        detectHorizontalDragGestures(
                            onDragStart = { isDragging = true },
                            onDragCancel = { isDragging = false },
                            onDragEnd = {
                                isDragging = false
                                val targetIndex = (indicatorOffset.value / tabWidthPx).roundToInt()
                                    .coerceIn(0, option.tabList.lastIndex)
                                selectedIndex = targetIndex
                                scope.launch {
                                    indicatorOffset.animateTo(
                                        targetIndex * tabWidthPx,
                                        spring(0.8f, 300f)
                                    )
                                }
                            },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                scope.launch {
                                    val maxOffset = tabWidthPx * (option.tabList.size - 1)
                                    val newOffset =
                                        (indicatorOffset.value + dragAmount).coerceIn(0f, maxOffset)
                                    indicatorOffset.snapTo(newOffset)
                                }
                            }
                        )
                    }
            ) {
                // B. 인디케이터
                if (tabWidthPx > 0f) {
                    val tabWidthDp = with(density) { tabWidthPx.toDp() }
                    val widthExtension = 12.dp
                    val indicatorWidth = tabWidthDp + widthExtension

                    // [수정] 높이 증가량을 8.dp -> 18.dp로 대폭 늘림
                    val stretchHeight = 18.dp * bulgeIntensity

                    val indicatorHeight =
                        (option.tabBarHeight.dp - (option.verticalPadding.dp * 2)) + stretchHeight

                    val stretchWidth = 16.dp * bulgeIntensity
                    val currentOffsetDp = with(density) { indicatorOffset.value.toDp() }
                    val targetX =
                        currentOffsetDp + (tabWidthDp - indicatorWidth) / 2 - (stretchWidth / 2)

                    // 1. [Refraction Rim]
                    val refractionRimBrush = Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            HongColor.WHITE_100.toColor().copy(alpha = 0.05f * lensEffectAlpha),
                            HongColor.WHITE_100.toColor().copy(alpha = 0.2f * lensEffectAlpha),
                            HongColor.WHITE_100.toColor().copy(alpha = 0.6f * lensEffectAlpha)
                        ),
                        radius = with(density) { (indicatorWidth.toPx() + stretchWidth.toPx()) * 0.7f }
                    )

                    // 2. [Lens Highlight]
                    val lensHighlight = Brush.linearGradient(
                        colors = listOf(
                            HongColor.WHITE_100.toColor().copy(alpha = 0.5f * lensEffectAlpha),
                            HongColor.WHITE_100.toColor().copy(alpha = 0.1f * lensEffectAlpha),
                            Color.Transparent,
                            Color.Transparent,
                            HongColor.WHITE_100.toColor().copy(alpha = 0.3f * lensEffectAlpha)
                        ),
                        start = Offset.Zero, end = Offset.Infinite
                    )

                    // 3. [Border]
                    val borderBrush = Brush.verticalGradient(
                        colors = listOf(
                            HongColor.WHITE_100.toColor().copy(alpha = 0.7f * lensEffectAlpha),
                            HongColor.WHITE_100.toColor().copy(alpha = 0.2f * lensEffectAlpha)
                        )
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .offset(x = targetX)
                            .width(indicatorWidth + stretchWidth)
                            .height(indicatorHeight)
                            .graphicsLayer {
                                scaleX = 1f + (0.05f * bulgeIntensity)
                                scaleY = 1f // 높이가 물리적으로 변하므로 스케일 변형 없음
                            }
                            .clip(indicatorShape)
                            .background(animatedBaseColor) // 기본 투명도
                            .background(refractionRimBrush) // 가장자리 굴절
                            .background(lensHighlight) // 표면 광택
                            .border(1.dp, borderBrush, indicatorShape)
                    )
                }

                // C. 아이콘 + 텍스트
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    option.tabList.forEachIndexed { index, tab ->
                        val currentTabPosition = index * tabWidthPx
                        val distance = kotlin.math.abs(indicatorOffset.value - currentTabPosition)
                        val focusRange = tabWidthPx * 0.8f

                        // 렌즈 확대 효과
                        val lensScale = if (distance < focusRange) {
                            val factor = 1f - (distance / focusRange)
                            1f + (0.3f * factor)
                        } else {
                            1f
                        }
                        val targetScale = if (isDragging) lensScale else 1f

                        val isSelected = try {
                            (indicatorOffset.value / tabWidthPx).roundToInt() == index
                        } catch (e: Exception) {
                            index == 0
                        }

                        val animatedScale by animateFloatAsState(
                            targetValue = targetScale,
                            animationSpec = spring(dampingRatio = 0.6f, stiffness = 500f),
                            label = "IconScale"
                        )

                        // 선택 상태에 따른 색상 (드래그 영향 X)
                        val contentColor = if (option.isDarkTheme) {
                            HongColor.WHITE_100
                        } else {
                            HongColor.BLACK_100
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .scale(animatedScale),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HongImageCompose(
                                option = HongImageBuilder()
                                    .width(24)
                                    .height(24)
                                    .scaleType(HongScaleType.CENTER_CROP)
                                    .imageInfo(tab.icon)
                                    .imageColor(contentColor)
                                    .applyOption()
                            )
                            HongTextCompose(
                                option = HongTextBuilder()
                                    .margin(HongSpacingInfo(top = 4f))
                                    .text(tab.label)
                                    .typography(if (isSelected) option.tabSelectTypo else option.tabDefTypo)
                                    .color(contentColor)
                                    .textAlign(HongTextAlign.CENTER)
                                    .applyOption()
                            )
                        }
                    }
                }
            }
        }
    }
}