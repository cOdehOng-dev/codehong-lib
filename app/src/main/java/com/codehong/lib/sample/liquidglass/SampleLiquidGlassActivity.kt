package com.codehong.lib.sample.liquidglass

import android.os.Bundle
import androidx.activity.compose.setContent
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.library.widget.liquid.LiquidGlassScreen

class SampleLiquidGlassActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiquidGlassScreen()

//            LiquidGlassTheme {
//                LiquidGlassSampleApp()
//            }
        }

    }
}

//@Composable
//fun LiquidGlassScreen() {
//    Box(modifier = Modifier.fillMaxSize()) {
//        // 1. 배경 레이어
//        ColorfulBackground()
//
//        // 2. UI 레이어
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
////            LiquidHeader()
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp))
//            // 중간 컨텐츠 영역 (예시)
//            Box(modifier = Modifier.weight(1f))
//            LiquidTabBar()
//        }
//    }
//}