package com.codehong.lib.sample.layout.fade

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehong.library.widget.R
import com.codehong.library.widget.layout.fade.HongScrollFadeLayoutBuilder
import com.codehong.library.widget.layout.fade.HongScrollFadeLayoutCompose
import com.codehong.library.widget.layout.fade.HongScrollFadeLayoutOption
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class SampleScrollFadeLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HongScrollFadeLayoutCompose(
                option = HongScrollFadeLayoutBuilder()
                    .backgroundColor(HongColor.BLUE_10.hex)
                    .mainContentHeightDp(HongScrollFadeLayoutOption.DEFAULT_MAIN_CONTENT_HEIGHT)
                    .headerBackgroundColor(HongColor.WHITE_100)
                    .leftIconInfo(R.drawable.honglib_ic_arrow_left)
                    .leftIconColor(HongColor.WHITE_100 to HongColor.BLACK_100)
                    .leftIconClick {
                        finish()
                    }
                    .titleText("헤더헤더헤더")
                    .titleTypo(HongTypo.TITLE_26_B)
                    .titleColor(HongColor.WHITE_100 to HongColor.BLACK_100)
                    .rightIconInfo(R.drawable.honglib_ic_menu)
                    .rightIconColor(HongColor.WHITE_100 to HongColor.BLACK_100)
                    .useTitleOverFlow(true)
                    .mainContent {
                        Text(
                            text = "Item #0",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    .subContentList {
                        it.items(49) { index ->
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
                    .bottomContent {  }
                    .applyOption()
            )
        }
    }
}
