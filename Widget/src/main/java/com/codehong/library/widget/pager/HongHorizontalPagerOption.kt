package com.codehong.library.widget.pager

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor

/**
 * HorizontalPager의 옵션을 정의하는 클래스입니다.
 *
 * 이 클래스는 HorizontalPager의 레이아웃, margin, padding, click 이벤트 등을 설정할 수 있습니다
 * 또한, 페이지 리스트, 페이지 배경 색상, 이전/다음 페이지의 width, 자동 스크롤 시간 등을 설정할 수 있습니다
 * 이 클래스는 무한 스크롤 여부와 현재 페이지 인덱스를 설정할 수 있는 기능도 제공합니다.
 */
data class HongHorizontalPagerOption(
    override val type: HongWidgetType = HongWidgetType.HORIZONTAL_PAGER,
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_NEXT_PAGE_VISIBLE_WIDTH = 0f
        const val DEFAULT_PREV_PAGE_VISIBLE_WIDTH = 0f
        const val DEFAULT_AUTO_SCROLL_MILL_SECOND = 0L
        const val DEFAULT_INITIAL_LOAD_PAGE_SIZE = 0
        const val DEFAULT_INFINITE_SCROLL = false
        const val BACK_TO_FIRST_ITEM = false
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)

    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var pageSpacing: Int = 0

    var pageInfoList: List<Any>? = null

    var prevPageVisibleWidth: Float = DEFAULT_PREV_PAGE_VISIBLE_WIDTH
    var nextPageVisibleWidth: Float = DEFAULT_NEXT_PAGE_VISIBLE_WIDTH

    var autoScrollMillSecond: Long = DEFAULT_AUTO_SCROLL_MILL_SECOND

    var initialLoadPageSize: Int = DEFAULT_INITIAL_LOAD_PAGE_SIZE

    /**
     * 무한 스크롤 여부와 첫 페이지로 돌아갈지 여부를 설정합니다.
     * @param infiniteScroll 첫 번째 값은 무한 스크롤 여부, 두 번째 값은 첫 페이지로 돌아갈지 여부입니다
     */
    var infiniteScroll: Pair<Boolean, Boolean> = Pair(
        DEFAULT_INFINITE_SCROLL,
        BACK_TO_FIRST_ITEM
    )

    var currentIndex: (Int) -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongHorizontalPagerOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (pageSpacing != other.pageSpacing) return false
        if (pageInfoList != other.pageInfoList) return false
        if (prevPageVisibleWidth != other.prevPageVisibleWidth) return false
        if (nextPageVisibleWidth != other.nextPageVisibleWidth) return false
        if (autoScrollMillSecond != other.autoScrollMillSecond) return false
        if (initialLoadPageSize != other.initialLoadPageSize) return false
        if (infiniteScroll != other.infiniteScroll) return false
        if (currentIndex != other.currentIndex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + pageSpacing
        result = 31 * result + (pageInfoList?.hashCode() ?: 0)
        result = 31 * result + prevPageVisibleWidth.hashCode()
        result = 31 * result + nextPageVisibleWidth.hashCode()
        result = 31 * result + autoScrollMillSecond.hashCode()
        result = 31 * result + initialLoadPageSize
        result = 31 * result + infiniteScroll.hashCode()
        result = 31 * result + currentIndex.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongHorizontalPagerOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "pageSpacing=$pageSpacing, " +
                "pageInfoList=$pageInfoList, " +
                "prevPageVisibleWidth=$prevPageVisibleWidth, " +
                "nextPageVisibleWidth=$nextPageVisibleWidth, " +
                "autoScrollMillSecond=$autoScrollMillSecond, " +
                "initialLoadPageSize=$initialLoadPageSize, " +
                "infiniteScroll=$infiniteScroll, " +
                "currentIndex=$currentIndex" +
                ")"
    }
}