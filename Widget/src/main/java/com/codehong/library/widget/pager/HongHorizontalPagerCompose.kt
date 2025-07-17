package com.codehong.library.widget.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.util.HongWidgetContainer
import kotlinx.coroutines.delay


@Composable
fun HongHorizontalPagerCompose(
    option: HongHorizontalPagerOption,
    content: @Composable (Any) -> Unit
) {
    val pageList = option.pageInfoList
    if (pageList.isNullOrEmpty()) {
        return
    }

    var currentPage by rememberSaveable { mutableIntStateOf(0) }

    val finalList = when {
        option.infiniteScroll.second -> List(10) { pageList }.flatten()
        option.infiniteScroll.first -> List(10) { pageList }.flatten()
        else -> pageList
    }

    val finalPageSize = finalList.size
    val pagerState = rememberPagerState { finalPageSize }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                currentPage = page
                option.currentIndex.invoke(currentPage % pageList.size)
            }
    }

    if (option.autoScrollMillSecond > 0) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(option.autoScrollMillSecond)
                val nextPage = (pagerState.currentPage + 1) % finalPageSize
                pagerState.animateScrollToPage(
                    if (option.infiniteScroll.first || option.infiniteScroll.second) {
                        nextPage
                    } else {
                        minOf(
                            nextPage,
                            finalPageSize - 1
                        )
                    }
                )
            }
        }
    }

    HongWidgetContainer(option) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = option.initialLoadPageSize,
            contentPadding = PaddingValues(
                start = option.prevPageVisibleWidth.dp,
                end = option.nextPageVisibleWidth.dp
            ),
            pageSpacing = option.pageSpacing.dp,
            pageSize = PageSize.Fill
        ) { pageIndex ->
            if (option.infiniteScroll.second) {
                finalList.getOrNull(
                    pageIndex % (finalPageSize)
                )?.let {
                    content(it)
                }
            } else {
                content(finalList[pageIndex])
            }
        }
    }
}

