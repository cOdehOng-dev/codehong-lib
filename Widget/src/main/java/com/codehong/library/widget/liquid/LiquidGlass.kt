package com.codehong.library.widget.liquid

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

// 데이터 클래스
data class TabItem(val icon: ImageVector, val label: String)

// ==========================================
// 1. 핵심 수정: Light Mode 배경 투명도 대폭 상향 + 회색 틴트 추가
// ==========================================
@Composable
fun Modifier.iosLiquidGlass(
    hazeState: HazeState,
    shape: RoundedCornerShape,
    isDarkTheme: Boolean,
    blurRadius: Dp = 30.dp
): Modifier {
    // 1. 틴트 설정
    val tintColor = if (isDarkTheme) {
        Color.White.copy(alpha = 0.1f)
    } else {
        // [수정] Light Mode: 흰색 틴트를 조금 더 강하게
        Color.White.copy(alpha = 0.6f)
    }

    // 2. [핵심 수정] 배경색 설정
    val glassBackgroundColor = if (isDarkTheme) {
        Color.Black.copy(alpha = 0.3f)
    } else {
        // [수정] Light Mode:
        // 1. Alpha를 0.45f -> 0.85f로 대폭 올려서 불투명하게 만듦 (하얀색 위에서도 잘 보임)
        // 2. 순수 화이트 대신 아주 연한 회색(F5F5F5)을 베이스로 사용하여 하얀 배경과 대비를 줌
        Color(0xFFF5F5F7).copy(alpha = 0.8f)
    }

    // 3. 유리 테두리 (하이라이트)
    val glassBorderBrush = if (isDarkTheme) {
        Brush.verticalGradient(
            colors = listOf(Color.White.copy(alpha = 0.4f), Color.White.copy(alpha = 0.05f))
        )
    } else {
        // [수정] Light Mode 테두리를 더 선명하게 하여 경계 강조
        Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.9f),
                Color(0xFFE5E5EA).copy(alpha = 0.6f) // 아래쪽도 회색톤으로 잡아줌
            )
        )
    }

    // 4. 그림자 설정
    val shadowColor = if (isDarkTheme) {
        Color.Black.copy(alpha = 1.0f)
    } else {
        // Light Mode 그림자를 조금 더 진하게 (0.25f -> 0.3f)
        Color.Black.copy(alpha = 0.3f)
    }

    val shadowElevation = if (isDarkTheme) 24.dp else 16.dp

    return this
        // Layer 1: 그림자 (위쪽 포함 사방으로 퍼짐)
        .shadow(
            elevation = shadowElevation,
            shape = shape,
            spotColor = shadowColor,
            ambientColor = shadowColor,
            clip = false
        )
        // Layer 2: 형태 클리핑
        .clip(shape)

        // Layer 3: 블러 (Haze) + 배경색
        .hazeChild(
            state = hazeState,
            style = HazeStyle(
                backgroundColor = glassBackgroundColor, // 수정된 진한 배경색 적용
                tint = HazeTint(tintColor),
                blurRadius = blurRadius,
                noiseFactor = 0.02f
            )
        )
        // Layer 4: 유리 하이라이트
        .border(width = 1.dp, brush = glassBorderBrush, shape = shape)
}

// ==========================================
// 2. 배경 애니메이션
// ==========================================
@Composable
fun AnimatedColorfulBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val offset1 by infiniteTransition.animateFloat(
        initialValue = -100f, targetValue = 100f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Reverse), label = "blob1"
    )
    val offset2 by infiniteTransition.animateFloat(
        initialValue = 50f, targetValue = -150f,
        animationSpec = infiniteRepeatable(tween(4500, easing = LinearEasing), RepeatMode.Reverse), label = "blob2"
    )
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.3f,
        animationSpec = infiniteRepeatable(tween(3500, easing = LinearEasing), RepeatMode.Reverse), label = "scale"
    )
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(10000, easing = LinearEasing), RepeatMode.Restart), label = "rotation"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingBlob(Brush.sweepGradient(listOf(Color(0xFFFF00CC), Color(0xFF3300FF), Color(0xFFFF00CC))), Alignment.TopStart, (-80 + offset1).dp, (-50).dp, scale, rotation)
        FloatingBlob(Brush.radialGradient(listOf(Color(0xFF00FFFF), Color(0xFF0000FF))), Alignment.CenterEnd, 120.dp, (-100 + offset2).dp, scale * 0.8f, -rotation)
        FloatingBlob(Brush.linearGradient(listOf(Color(0xFFCCFF00), Color(0xFF00FF00))), Alignment.BottomStart, (-50 + offset2).dp, 150.dp, scale * 1.1f, rotation * 0.5f)
    }
}

@Composable
fun FloatingBlob(brush: Brush, alignment: Alignment, offsetX: Dp, offsetY: Dp, scale: Float = 1f, rotation: Float) {
    Box(modifier = Modifier.fillMaxWidth().height(450.dp).offset(x = offsetX, y = offsetY)
        .graphicsLayer { scaleX = scale; scaleY = scale; rotationZ = rotation }
        .background(brush, CircleShape))
}

// ==========================================
// 3. 헤더
// ==========================================
@Composable
fun GlassHeader(hazeState: HazeState, isDarkTheme: Boolean) {
    val shape = RoundedCornerShape(24.dp)

    val mainTextColor = animateColorAsState(if (isDarkTheme) Color.White else Color.Black.copy(0.8f), label = "text")
    val subTextColor = animateColorAsState(if (isDarkTheme) Color.White.copy(0.7f) else Color.Black.copy(0.5f), label = "subtext")
    val iconBgColor = if (isDarkTheme) Color.White.copy(0.15f) else Color.Black.copy(0.05f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .height(80.dp)
            .iosLiquidGlass(hazeState, shape, isDarkTheme, blurRadius = 30.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Liquid Tab", color = subTextColor.value, fontSize = 14.sp)
            Text("With Labels", color = mainTextColor.value, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(iconBgColor, CircleShape)
                .border(1.dp, mainTextColor.value.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, "Profile", tint = mainTextColor.value)
        }
    }
}

// ==========================================
// 4. 탭바 (색상 반전 및 가시성 개선)
// ==========================================

@Composable
fun GlassSlidingTabBar(hazeState: HazeState, isDarkTheme: Boolean) {
    val tabs = listOf(
        TabItem(Icons.Default.Home, "Home"),
        TabItem(Icons.Default.Search, "Search"),
        TabItem(Icons.Filled.Favorite, "Likes"),
        TabItem(Icons.Filled.Notifications, "Noti"),
        TabItem(Icons.Default.Settings, "Setting")
    )

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
    val targetBaseColor = if (isDarkTheme) {
        Color.White.copy(alpha = 0.05f)
    } else {
        Color.Black.copy(alpha = 0.1f)
    }

    // [드래그 시 배경색 투명도(Alpha) 조정]
    val dragAlpha = if (isDarkTheme) 0.01f else 0.06f

    val animatedBaseColor by animateColorAsState(
        targetValue = if (isDragging) targetBaseColor.copy(alpha = dragAlpha) else targetBaseColor,
        animationSpec = tween(durationMillis = 300),
        label = "IndicatorColor"
    )

    val tabBarHeight = 80.dp
    val outerRadius = 40.dp
    val tabBarShape = RoundedCornerShape(outerRadius)
    val verticalPadding = 12.dp
    val indicatorShape = RoundedCornerShape(outerRadius - verticalPadding)
    val innerSideGap = 16.dp

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
                .height(tabBarHeight)
                .iosLiquidGlass(hazeState, tabBarShape, isDarkTheme, blurRadius = 40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = innerSideGap)
                    .onGloballyPositioned { coordinates ->
                        if (tabWidthPx == 0f) {
                            tabWidthPx = coordinates.size.width.toFloat() / tabs.size
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { offset ->
                                val index = (offset.x / tabWidthPx).toInt().coerceIn(0, tabs.lastIndex)
                                selectedIndex = index
                                scope.launch { indicatorOffset.animateTo(index * tabWidthPx, spring(0.7f, 400f)) }
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
                                val targetIndex = (indicatorOffset.value / tabWidthPx).roundToInt().coerceIn(0, tabs.lastIndex)
                                selectedIndex = targetIndex
                                scope.launch { indicatorOffset.animateTo(targetIndex * tabWidthPx, spring(0.8f, 300f)) }
                            },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                scope.launch {
                                    val maxOffset = tabWidthPx * (tabs.size - 1)
                                    val newOffset = (indicatorOffset.value + dragAmount).coerceIn(0f, maxOffset)
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
                    val indicatorHeight = tabBarHeight - (verticalPadding * 2)
                    val stretchWidth = 16.dp * bulgeIntensity
                    val currentOffsetDp = with(density) { indicatorOffset.value.toDp() }
                    val targetX = currentOffsetDp + (tabWidthDp - indicatorWidth) / 2 - (stretchWidth / 2)

                    // 1. [Refraction Rim]
                    val refractionRimBrush = Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.05f * lensEffectAlpha),
                            Color.White.copy(alpha = 0.2f * lensEffectAlpha),
                            Color.White.copy(alpha = 0.6f * lensEffectAlpha)
                        ),
                        radius = with(density) { (indicatorWidth.toPx() + stretchWidth.toPx()) * 0.7f }
                    )

                    // 2. [Lens Highlight]
                    val lensHighlight = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.5f * lensEffectAlpha),
                            Color.White.copy(alpha = 0.1f * lensEffectAlpha),
                            Color.Transparent,
                            Color.Transparent,
                            Color.White.copy(alpha = 0.3f * lensEffectAlpha)
                        ),
                        start = Offset.Zero, end = Offset.Infinite
                    )

                    // 3. [Border]
                    val borderBrush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.7f * lensEffectAlpha),
                            Color.White.copy(alpha = 0.2f * lensEffectAlpha)
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
                                scaleY = 1f - (0.05f * bulgeIntensity)
                            }
                            .clip(indicatorShape)
                            .hazeChild(
                                state = hazeState,
                                style = HazeStyle(
                                    tint = HazeTint(Color.White.copy(alpha = 0.2f)),
                                    blurRadius = 15.dp,
                                    noiseFactor = 0.05f,
                                    backgroundColor = if (isDarkTheme) Color.Black else Color.White
                                )
                            )
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
                    tabs.forEachIndexed { index, tab ->
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
                        } catch (e: Exception) { index == 0 }

                        val animatedScale by animateFloatAsState(
                            targetValue = targetScale,
                            animationSpec = spring(dampingRatio = 0.6f, stiffness = 500f),
                            label = "IconScale"
                        )

                        // [핵심 수정] 드래그 중이더라도 선택되지 않은 탭은 흐린 색상 유지
                        val contentColor = if (isDarkTheme) {
                            if (isSelected) Color.White else Color.White.copy(0.5f)
                        } else {
                            if (isSelected) Color.Black else Color.Black.copy(0.5f)
                        }

                        val animatedContentColor by animateColorAsState(
                            targetValue = contentColor,
                            animationSpec = tween(300),
                            label = "ContentColor"
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .scale(animatedScale),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = animatedContentColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tab.label,
                                color = animatedContentColor,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// 5. 최종 화면: 밝기 조절 토글
// ==========================================
@Composable
fun LiquidGlassScreen() {
    val hazeState = remember { HazeState() }
    var isDarkTheme by remember { mutableStateOf(true) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isDarkTheme) Color(0xFF050510) else Color(0xFFFFFFFF),
        animationSpec = tween(1000),
        label = "BgColor"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            AnimatedColorfulBackground()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .haze(state = hazeState)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GlassHeader(hazeState, isDarkTheme)

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isDarkTheme) "Dark Mode Glass" else "Light Mode Glass",
                    color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Light",
                        color = if (isDarkTheme) Color.White.copy(0.5f) else Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { isDarkTheme = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFF3300FF),
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = Color.White
                        )
                    )
                    Text(
                        "Dark",
                        color = if (isDarkTheme) Color.White else Color.Black.copy(0.5f),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Swipe the tab bar below",
                    color = if(isDarkTheme) Color.White.copy(alpha = 0.5f) else Color.Black.copy(0.5f),
                    fontSize = 16.sp
                )
            }

            GlassSlidingTabBar(hazeState, isDarkTheme)
        }
    }
}