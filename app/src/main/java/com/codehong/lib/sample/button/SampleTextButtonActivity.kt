package com.codehong.lib.sample.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleMenu
import com.codehong.lib.sample.SampleScaffold
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.R
import com.codehong.library.widget.button.HongTextButton
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.util.ToastUtil

class SampleTextButtonActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleScaffold(title = "TextButton") {
                SampleMenu(title = "꽉찬 버튼버튼") {
                    HongTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 20.dp),
                        buttonText = "검색하기",
                        buttonBackgroundColor = HongComposeColor(
                            type = HongColor.MAIN_PURPLE
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

                    SampleMenu(title = "버튼") {
                        HongTextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(horizontal = 20.dp),
                            buttonText = "이동하기",
                            buttonBackgroundColor = HongComposeColor(
                                type = HongColor.MAIN_PURPLE
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
