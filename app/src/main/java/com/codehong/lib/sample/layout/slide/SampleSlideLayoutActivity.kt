package com.codehong.lib.sample.layout.slide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.R
import com.codehong.lib.sample.SampleHeader
import com.codehong.lib.sample.SampleMenu
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.button.HongTextButton
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.rule.color.HongColor

class SampleSlideLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var visibleState by rememberSaveable { mutableStateOf(false) }
            Scaffold(
                topBar = {
                    SampleHeader(title = "레이아웃")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .padding(horizontal = 20.dp)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "슬라이드 레이아웃") {
                        HongTextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(horizontal = 20.dp),
                            buttonText = "뷰 슬라이드",
                            buttonBackgroundColor = HongComposeColor(
                                type = HongColor.MAIN_PURPLE
                            ),
                            allRadius = 12,
                            buttonTextStyle = HongComposeTextStyle(
                                color = HongComposeColor(
                                    resId = R.color.color_ffffff
                                ),
                                size = 15,
                                fontWeight = FontWeight.W700
                            ),
                            verticalPadding = 15,
                            click = {
                                visibleState = true
                            }
                        )
                    }
                }
            }

            // TODO 버그 수정 필요
//            HongSlideVisibleLayout(
//                isVisible = visibleState,
//                onAnimationEnd = {
//                    Log.d("TAG", "애니메이션 종료")
//                }
//            ) {
//                Scaffold(
//                    topBar = {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(52.dp)
//                                .background(colorResource(id = HongColor.MAIN_PURPLE.colorResId)),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            HongText(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 20.dp)
//                                    .disableRippleClickable { visibleState = false },
//                                text = "닫기",
//                                typo = HongTypo.BODY_18_B,
//                                colorType = HongColor.WHITE_100,
//                                textAlign = TextAlign.End
//                            )
//                        }
//                    }
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(it)
//                            .background(colorResource(id = HongColor.BLACK_15.colorResId))
//                    )
//                }
//            }
        }
    }
}
