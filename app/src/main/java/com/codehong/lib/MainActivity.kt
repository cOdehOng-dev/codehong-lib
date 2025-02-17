package com.codehong.lib

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.button.SampleTextButtonActivity
import com.codehong.lib.sample.textfield.SampleTextFieldActivity
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.R
import com.codehong.library.widget.button.HongTextButton
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.text.HongText
import com.codehong.library.widget.typo.TypoType

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme(this)
        }
    }
}

@Composable
fun SampleTheme(
    activity: ComponentActivity
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        colorResource(id = ColorType.PRIMARY_MINT.colorResId)
                    ),
                contentAlignment = Alignment.Center
            ) {
                HongText(
                    text = "코드홍의 라이브러리 월드",
                    fontWeight = FontWeight.W700,
                    textSize = 30,
                    textColorResId = R.color.honglib_color_ffffff
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        colorResource(id = ColorType.PRIMARY_MINT.colorResId)
                    )
            ) {
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(
                mutableListOf<SampleTestItem>().apply {
                    ComposeItem.values().forEach { item ->
                        add(
                            SampleTestItem(
                                title = item.title,
                                compose = item
                            )
                        )
                    }
                }
            ) { item ->
                HongTextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    buttonText = item.title,
                    buttonTextTypoType = TypoType.BODY_14_B,
                    allRadius = 20,
                    buttonTextColor = HongComposeColor(
                        colorType = ColorType.WHITE_100
                    ),
                    buttonBackgroundColor = HongComposeColor(
                        colorType = ColorType.PRIMARY_MINT
                    ),
                    verticalPadding = 20
                ) {
                    when (item.compose) {
                        ComposeItem.TEXT_FILED -> {
                            Intent(activity, SampleTextFieldActivity::class.java).apply {
                                activity.startActivity(this)
                            }
                        }
                        ComposeItem.TEXT_BUTTON -> {
                            Intent(activity, SampleTextButtonActivity::class.java).apply {
                                activity.startActivity(this)
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

enum class ComposeItem(val title: String) {
    TEXT_TYPO("텍스트 Typo"),
    TEXT("Text"),
    CALENDAR("달력"),
    HEADER("헤더"),
    TEXT_FILED("TextField"),
    SEARCH_BAR("검색바"),
    TEXT_BUTTON("텍스트 버튼")
}
