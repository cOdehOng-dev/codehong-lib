package com.codehong.library.widget.pager

import com.codehong.library.widget.rule.HongSpacingInfo

class HongHorizontalPagerBuilder {

    var option = HongHorizontalPagerOption()

    fun width(width: Int?) = apply {
        width?.let { option.width = it }
    }

    fun height(height: Int?) = apply {
        height?.let { option.height = it }
    }

    fun margin(margin: HongSpacingInfo) = apply {
        option.margin = margin
    }

    fun verticalPadding(
        topPadding: Float,
        bottomPadding: Float
    ) = apply {
        option.padding = HongSpacingInfo(
            top = topPadding,
            bottom = bottomPadding
        )
    }

    fun backgroundColor(colorHex: String) = apply {
        option.backgroundColorHex = colorHex
    }

    fun pageInfoList(pageInfoList: List<Any>?) = apply {
        option.pageInfoList = pageInfoList ?: emptyList()
    }

    fun pageSpacing(pageSpacing: Int) = apply {
        option.pageSpacing = pageSpacing
    }

    fun pageVisibleWidth(
        prev: Float,
        next: Float
    ) = apply {
        option.prevPageVisibleWidth = prev
        option.nextPageVisibleWidth = next
    }

    fun autoScrollMillSecond(millSecond: Long) = apply {
        option.autoScrollMillSecond = millSecond
    }

    fun initialLoadPageSize(size: Int) = apply {
        option.initialLoadPageSize = size
    }

    fun infiniteScroll(
        on: Boolean,
        isRollbackFirst: Boolean
    ) = apply {
        option.infiniteScroll = Pair(on, isRollbackFirst)
    }

    fun currentIndex(currentIndex: (Int) -> Unit) = apply {
        option.currentIndex = currentIndex
    }

    fun applyOption(): HongHorizontalPagerOption {
        return option
    }

    fun copy(injectOption: HongHorizontalPagerOption): HongHorizontalPagerBuilder {
        return HongHorizontalPagerBuilder()
            .width(injectOption.width)
            .height(injectOption.height)
            .margin(injectOption.margin)
            .verticalPadding(
                topPadding = injectOption.padding.top,
                bottomPadding = injectOption.padding.bottom
            )
            .backgroundColor(injectOption.backgroundColorHex)
            .pageInfoList(injectOption.pageInfoList)
            .pageSpacing(injectOption.pageSpacing)
            .pageVisibleWidth(
                prev = injectOption.prevPageVisibleWidth,
                next = injectOption.nextPageVisibleWidth
            )
            .autoScrollMillSecond(injectOption.autoScrollMillSecond)
            .initialLoadPageSize(injectOption.initialLoadPageSize)
            .infiniteScroll(
                on = injectOption.infiniteScroll.first,
                isRollbackFirst = injectOption.infiniteScroll.second
            )
            .currentIndex(injectOption.currentIndex)
    }
}