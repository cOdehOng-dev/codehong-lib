package com.codehong.lib.sample.liquidglass.tabbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.liquid.tabbar.HongLiquidGlassTabBar
import com.codehong.library.widget.liquidglass.FloatingBlob
import com.codehong.library.widget.liquidglass.HongLiquidGlassTabItem
import com.codehong.library.widget.liquidglass.tabbar.HongLiquidGlassTabBarBuilder
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

class SampleLiquidGlassTabBarActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
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

                HongLiquidGlassTabBar(
                    HongLiquidGlassTabBarBuilder()
                        .isDarkTheme(isDarkTheme)
                        .tabList(
                            listOf(
                                HongLiquidGlassTabItem(
                                    R.drawable.honglib_ic_home,
                                    "Home"
                                ),
                                HongLiquidGlassTabItem(
                                    R.drawable.honglib_ic_search,
                                    "Search"
                                ),
                                HongLiquidGlassTabItem(
                                    R.drawable.honglib_ic_favorite,
                                    "Favorite"
                                ),
                                HongLiquidGlassTabItem(
                                    R.drawable.honglib_ic_persion,
                                    "Profile"
                                )
                            )
                        )
                        .applyOption()
                )
            }
        }
    }

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
}