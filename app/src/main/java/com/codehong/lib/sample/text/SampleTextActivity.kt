package com.codehong.lib.sample.text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleHeader
import com.codehong.lib.sample.SampleMenu
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeHighlightTextInfo
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.text.HongText
import com.codehong.library.widget.typo.TypoType

class SampleTextActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    SampleHeader(title = "Text")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .padding(horizontal = 20.dp)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "하이라이트 텍스트") {
                        HongText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            text = "가나다라마바사",
                            typo = TypoType.BODY_16,
                            colorType = ColorType.BLACK_100,
                            highlight = HongComposeHighlightTextInfo(
                                highlightText = "가나다라",
                                style = HongComposeTextStyle(
                                    color = HongComposeColor(
                                        type = ColorType.MAIN_PURPLE
                                    ),
                                    typo = TypoType.BODY_16_B
                                )
                            )
                        )
                    }

                    SampleMenu(title = "Alpha null") {
                        HongText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            text = "가나다라마바사",
                            style = HongComposeTextStyle(
                                color = HongComposeColor(
                                    hexCode = "#277CFF"
                                ),
                                typo = TypoType.BODY_16
                            )
                        )
                    }

                    SampleMenu(title = "Alpha 50") {
                        HongText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            text = "가나다라마바사",
                            style = HongComposeTextStyle(
                                color = HongComposeColor(
                                    alpha = 50,
                                    hexCode = "#277CFF"
                                ),
                                typo = TypoType.BODY_16
                            )
                        )
                    }
                }
            }
        }
    }
}
