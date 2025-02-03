package com.codehong.lib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.lib.ui.theme.CodehonglibTheme
import com.codehong.library.widget.R
import com.codehong.library.widget.text.HongText

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme()
        }
    }
}

@Composable
fun SampleTheme() {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        colorResource(id = R.color.honglib_purple_200)
                    ),
                contentAlignment = Alignment.Center
            ) {
                HongText(
                    text = "코드홍의 라이브러리 월드",
                    fontWeight = FontWeight.W700,
                    textSize = 30,
                    textColor = R.color.honglib_color_000000
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        colorResource(id = R.color.honglib_purple_200)
                    )
            ) {
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodehonglibTheme {
        Greeting("Android")
    }
}
