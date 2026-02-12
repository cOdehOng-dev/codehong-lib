package com.codehong.lib.sample.liquidglass.tabbar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.library.network.debug.TimberUtil
import com.codehong.library.widget.R
import com.codehong.library.widget.liquid.tabbar.HongLiquidGlassTabBar
import com.codehong.library.widget.liquidglass.tabbar.HongLiquidGlassTabItem
import com.codehong.library.widget.liquidglass.tabbar.HongLiquidGlassTabBarBuilder

class SampleLiquidGlassTabBarActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }

            val backgroundColor by animateColorAsState(
                targetValue = if (isDarkTheme) Color(0xFF050510) else Color(0xFFFFFFFF),
                animationSpec = tween(1000),
                label = "BgColor"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(49) { index ->
                            val backgroundColor = Color.hsv((index * 30) % 360f, 0.8f, 0.9f)

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(backgroundColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Item #$index",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
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
                            .outerRadius(40)
                            .tabBarHeight(80)
                            .tabVerticalPadding(12)
                            .innerSideGap(16)
                            .onSelectedTab { i, item ->
                                TimberUtil.d("Selected tab index: $i, title: $item")
                            }
                            .applyOption()
                    )
                }
            }
        }
    }
}