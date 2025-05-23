package com.codehong.library.widget.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.util.getColor
import kotlinx.coroutines.delay

/**
 * @param isInfiniteScrollWithBackToFirstItem true이면 autoScrollMillSecond을 설정해야함
 */
@Composable
fun HongHorizontalPager(
    modifier: Modifier = Modifier,
    pagerBackgroundColor: HongComposeColor = HongComposeColor(
        type = HongColor.WHITE_100
    ),
    pageList: List<Any>?,
    prevPageVisibleWidth: Float = 25f,
    nextPageVisibleWidth: Float = 20f,
    autoScrollMillSecond: Long = 0,
    initialLoadPageSize: Int? = null,
    isInfiniteScroll: Boolean = false,
    isInfiniteScrollWithBackToFirstItem: Boolean = false,
    currentIndex: (Int) -> Unit,
    content: @Composable (Any) -> Unit
) {
    if (pageList.isNullOrEmpty()) {
        return
    }

    if (isInfiniteScrollWithBackToFirstItem && autoScrollMillSecond == 0L) {
        throw IllegalArgumentException(
            "isInfiniteScrollWithBackToFirstItem is true, autoScrollMillSecond must be greater than 0"
        )
    }

    var currentPage by remember { mutableStateOf(0) }

    val finalList = if (isInfiniteScroll) {
        List(10) { pageList }.flatten()
    } else {
        pageList
    }

    val finalPageSize = finalList.size
    val pagerState = rememberPagerState { finalPageSize }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                currentPage = page
                currentIndex.invoke(currentPage % pageList.size)
            }
    }

    if (autoScrollMillSecond > 0) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(autoScrollMillSecond)
                val nextPage = (pagerState.currentPage + 1) % finalPageSize
                pagerState.animateScrollToPage(
                    if (isInfiniteScroll || isInfiniteScrollWithBackToFirstItem) {
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

    HorizontalPager(
        state = pagerState,
        beyondViewportPageCount = initialLoadPageSize ?: PagerDefaults.BeyondViewportPageCount,
        modifier = Modifier
            .then(modifier)
            .background(pagerBackgroundColor.getColor()),
        contentPadding = PaddingValues(
            start = prevPageVisibleWidth.dp,
            end = nextPageVisibleWidth.dp
        )
    ) { pageIndex ->
        if (isInfiniteScrollWithBackToFirstItem) {
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
