package com.codehong.library.widget.liquidglass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehong.library.widget.extensions.liquidGlass
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

// ==========================================
// 3. 헤더
// ==========================================
@Composable
fun GlassHeader(hazeState: HazeState, isDarkTheme: Boolean) {
    val shape = RoundedCornerShape(24.dp)

    val mainTextColor = animateColorAsState(
        if (isDarkTheme) Color.White else Color.Black.copy(0.8f),
        label = "text"
    )
    val subTextColor = animateColorAsState(
        if (isDarkTheme) Color.White.copy(0.7f) else Color.Black.copy(0.5f),
        label = "subtext"
    )
    val iconBgColor = if (isDarkTheme) Color.White.copy(0.15f) else Color.Black.copy(0.05f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .height(80.dp)
            .liquidGlass(shape, isDarkTheme)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Liquid Tab", color = subTextColor.value, fontSize = 14.sp)
            Text(
                "With Labels",
                color = mainTextColor.value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                    color = if (isDarkTheme) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        0.5f
                    ),
                    fontSize = 16.sp
                )
            }
        }
    }
}

// ==========================================
// 2. 배경 애니메이션
// ==========================================
@Composable
private fun AnimatedColorfulBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val offset1 by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Reverse),
        label = "blob1"
    )
    val offset2 by infiniteTransition.animateFloat(
        initialValue = 50f,
        targetValue = -150f,
        animationSpec = infiniteRepeatable(tween(4500, easing = LinearEasing), RepeatMode.Reverse),
        label = "blob2"
    )
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(tween(3500, easing = LinearEasing), RepeatMode.Reverse),
        label = "scale"
    )
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(10000, easing = LinearEasing), RepeatMode.Restart),
        label = "rotation"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingBlob(
            Brush.sweepGradient(
                listOf(
                    Color(0xFFFF00CC),
                    Color(0xFF3300FF),
                    Color(0xFFFF00CC)
                )
            ), (-80 + offset1).dp, (-50).dp, scale, rotation
        )
        FloatingBlob(
            Brush.radialGradient(listOf(Color(0xFF00FFFF), Color(0xFF0000FF))),
            120.dp,
            (-100 + offset2).dp,
            scale * 0.8f,
            -rotation
        )
        FloatingBlob(
            Brush.linearGradient(listOf(Color(0xFFCCFF00), Color(0xFF00FF00))),
            (-50 + offset2).dp,
            150.dp,
            scale * 1.1f,
            rotation * 0.5f
        )
    }
}

@Composable
fun FloatingBlob(brush: Brush, offsetX: Dp, offsetY: Dp, scale: Float = 1f, rotation: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .offset(x = offsetX, y = offsetY)
            .graphicsLayer { scaleX = scale; scaleY = scale; rotationZ = rotation }
            .background(brush, CircleShape))
}
