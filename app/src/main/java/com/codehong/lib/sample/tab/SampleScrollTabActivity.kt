package com.codehong.lib.sample.tab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.codehong.lib.sample.SampleHeader
import com.codehong.lib.sample.SampleMenu
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.tab.HongScrollTab

class SampleScrollTabActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    SampleHeader(title = "ScrollTab")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(ColorType.WHITE_100.colorResId))
                        .padding(it)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "스크롤 탭") {
                        HongScrollTab(
                            tabList = listOf(
                                "패키지",
                                "국내숙소",
                                "해외숙소",
                                "투어·티켓"
                            ),
                            tabTitleList = listOf(
                                "패키지",
                                "국내숙소",
                                "해외숙소",
                                "투어·티켓"
                            ),
                            allRadius = 100,
                            selectBackgroundColor = HongComposeColor(
                                type = ColorType.BLACK_100
                            ),
                            tabTextHorizontalPadding = 12,
                            tabLayoutStartPadding = 16,
                            tabLayoutEndPadding = 16,
                            tabBetweenPadding = 6
                        ) { i, item ->
                            (item as? String)?.let {

                            }
                        }
                    }

                    SampleMenu(title = "스크롤 탭") {
                        HongScrollTab(
                            tabList = listOf(
                                "뮤지컬",
                                "콘서트",
                                "스포츠",
                                "전시/행사",
                                "클래식/무용",
                                "아동/가족",
                                "연극",
                                "레저/캠핑"
                            ),
                            tabTitleList = listOf(
                                "뮤지컬",
                                "콘서트",
                                "스포츠",
                                "전시/행사",
                                "클래식/무용",
                                "아동/가족",
                                "연극",
                                "레저/캠핑"
                            ),
                            allRadius = 100,
                            selectBackgroundColor = HongComposeColor(
                                type = ColorType.MAIN_PURPLE
                            ),
                            tabTextHorizontalPadding = 12,
                            tabLayoutStartPadding = 16,
                            tabLayoutEndPadding = 16,
                            tabBetweenPadding = 8
                        ) { i, item ->
                            (item as? String)?.let {

                            }
                        }
                    }
                }
            }
        }
    }
}