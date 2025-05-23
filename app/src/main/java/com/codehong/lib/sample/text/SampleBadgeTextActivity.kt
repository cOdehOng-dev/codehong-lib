package com.codehong.lib.sample.text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleHeader
import com.codehong.lib.sample.SampleMenu
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.text.HongBadgeText

class SampleBadgeTextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(HongColor.WHITE_100.colorResId)),
                topBar = {
                    SampleHeader(title = "BadgeText")
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(HongColor.WHITE_100.colorResId))
                        .padding(paddingValues)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "border 없는 BadgeText") {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        ) {
                            HongBadgeText(
                                text = "지금이 아니면 상품 없음",
                                backgroundColor = HongComposeColor(
                                    hexCode = "#FF322E",
                                    alpha = 7
                                ),
                                textStyle = HongComposeTextStyle(
                                    color = HongComposeColor(
                                        hexCode = "#FF322E"
                                    ),
                                    fontWeight = FontWeight.W700,
                                    size = 12
                                ),
                                paddingTop = 4f,
                                paddingBottom = 4f,
                                paddingStart = 8f,
                                paddingEnd = 8f,
                                allRadius = 6
                            )
                        }
                    }

                    SampleMenu(title = "border 있는 BadgeText 1") {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        ) {
                            HongBadgeText(
                                text = "모두 보라보라해해에에에",
                                backgroundColor = HongComposeColor(
                                    type = HongColor.WHITE_100
                                ),
                                textStyle = HongComposeTextStyle(
                                    color = HongComposeColor(
                                        hexCode = "#8E43E7"
                                    ),
                                    fontWeight = FontWeight.W700,
                                    size = 12
                                ),
                                borderWidth = 1,
                                borderColor = HongComposeColor(
                                    hexCode = "#DFB4FC"
                                ),
                                paddingTop = 1.5f,
                                paddingBottom = 1.5f,
                                paddingStart = 4f,
                                paddingEnd = 4f,
                                allRadius = 4
                            )
                        }
                    }
                }
            }
        }
    }
}
