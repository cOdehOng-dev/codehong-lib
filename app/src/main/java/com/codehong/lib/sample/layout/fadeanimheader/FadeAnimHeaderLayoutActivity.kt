package com.codehong.lib.sample.layout.fadeanimheader

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
import com.codehong.library.widget.layout.FadeAnimHeaderLayout

class FadeAnimHeaderLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FadeAnimHeaderLayout(
                firstItemHeight = 446,
                header = { isTransparent, headerAlpha ->
                    FadeAnimHeaderLayoutHeader(
                        isTransparent = isTransparent,
                        headerAlpha = headerAlpha
                    )
                },
                firstContent = {
                    Text(
                        text = "Item #0",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                contentList = {
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
                },
                tabBar = {}
            )
        }
    }
}
