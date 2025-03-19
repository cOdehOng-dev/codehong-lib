package com.codehong.lib.sample.pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import com.codehong.lib.sample.SampleHeader
import com.codehong.lib.sample.SampleMenu
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.image.HongImage
import com.codehong.library.widget.pager.HongHorizontalPager

class SampleHorizontalPagerActivity : ComponentActivity() {

    private val testImageUrlList = listOf(
        "https://images.unsplash.com/photo-1738168273959-952fdc961991?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1735094495719-80c0e43efccc?q=80&w=2060&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1739268515254-631fda640a1c?q=80&w=2052&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    SampleHeader(title = "HorizontalPager")
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(ColorType.WHITE_100.colorResId))
                        .padding(paddingValues)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "오토스크롤 X, 무한스크롤 X") {
                        HongHorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth(),
                            pageList = testImageUrlList,
                            nextPageVisibleWidth = 15f,
                            currentIndex = {
                            }
                        ) { item ->
                            (item as? String)
                                ?.toString()
                                ?.takeIf { it.isNotEmpty() }
                                ?.let { imageUrl ->
                                    HongImage(
                                        width = 335,
                                        height = 95,
                                        imageUrl = imageUrl,
                                        allRadius = 12,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                        }
                    }

                    MarginTopOrBottom(30)
                    SampleMenu(title = "오토스크롤 O, 무한스크롤 O") {
                        HongHorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth(),
                            pageList = testImageUrlList,
                            autoScrollMillSecond = 3000,
                            nextPageVisibleWidth = 15f,
                            isInfiniteScroll = true,
                            currentIndex = {
                            }
                        ) { item ->
                            (item as? String)
                                ?.toString()
                                ?.takeIf { it.isNotEmpty() }
                                ?.let { imageUrl ->
                                    HongImage(
                                        width = 335,
                                        height = 95,
                                        imageUrl = imageUrl,
                                        allRadius = 12,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                        }
                    }

                    MarginTopOrBottom(30)
                    SampleMenu(title = "오토스크롤 X, 무한스크롤 O") {
                        HongHorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth(),
                            pageList = testImageUrlList,
                            nextPageVisibleWidth = 15f,
                            isInfiniteScroll = true,
                            currentIndex = {
                            }
                        ) { item ->
                            (item as? String)
                                ?.toString()
                                ?.takeIf { it.isNotEmpty() }
                                ?.let { imageUrl ->
                                    HongImage(
                                        width = 335,
                                        height = 95,
                                        imageUrl = imageUrl,
                                        allRadius = 12,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                        }
                    }

                    MarginTopOrBottom(30)
                    SampleMenu(title = "스크롤 완료 후 처음으로 백") {
                        HongHorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth(),
                            pageList = testImageUrlList,
                            nextPageVisibleWidth = 15f,
                            isInfiniteScrollWithBackToFirstItem = true,
                            autoScrollMillSecond = 3000,
                            currentIndex = {
                            }
                        ) { item ->
                            (item as? String)
                                ?.toString()
                                ?.takeIf { it.isNotEmpty() }
                                ?.let { imageUrl ->
                                    HongImage(
                                        width = 335,
                                        height = 95,
                                        imageUrl = imageUrl,
                                        allRadius = 12,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                        }
                    }
                }
            }
        }
    }
}
