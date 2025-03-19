package com.codehong.lib.sample.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleHeader
import com.codehong.lib.sample.SampleMenu
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.R
import com.codehong.library.widget.button.HongTextButton
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.util.ToastUtil

class SampleTextButtonActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    SampleHeader(title = "텍스트 버튼")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = ColorType.WHITE_100.colorResId))
                        .padding(it)
                        .padding(horizontal = 20.dp)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "버튼") {
                        HongTextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(horizontal = 20.dp),
                            buttonText = "최저가 검색",
                            buttonBackgroundColor = HongComposeColor(
                                type = ColorType.MAIN_PURPLE
                            ),
                            allRadius = 12,
                            buttonTextStyle = HongComposeTextStyle(
                                color = HongComposeColor(
                                    resId = R.color.honglib_color_ffffff
                                ),
                                size = 15,
                                fontWeight = FontWeight.W700
                            ),
                            verticalPadding = 15,
                            click = {
                                ToastUtil.showToast(this@SampleTextButtonActivity, "버튼 클릭")
                            }
                        )
                    }
                }
            }
        }
    }
}
