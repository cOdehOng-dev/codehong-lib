package com.codehong.library.widget.rule

enum class HongWidgetType(
    val id: Int,
    val value: String,
    val allowPlayground: Boolean = false
) {
    TEXT(1, "text", true),
    IMAGE(2, "Image", true),
    HEADER(3, "Header", true),
    TEXT_FILED(4, "TextField", true),
    SEARCH_BAR(5, "검색바", true),
    TEXT_BUTTON(6, "텍스트 버튼", true),
    SLIDE_LAYOUT(7, "SlideLayout", true),
    CALENDAR_1(8, "달력(초기 X)", true),
    HORIZONTAL_PAGER(9, "HorizontalViewPager", true),
    BADGE_TEXT(10, "BadgeText", true),
    SCROLL_TAB(11, "ScrollTab", true),
    CAPTURE_SHARE(12, "CaptureShare", false),
    DYNAMIC_ISLAND(13, "DynamicIsland", false),
    VIDEO_POPUP(14, "VideoPopup", false),
    VIDEO_PLAYER(15, "VideoPlayer", false),
    NO_VALUE(-1, "no_value");

    companion object {
        fun String?.toHongWidgetType(): HongWidgetType {
            return entries.firstOrNull { it.value.equals(this, true) }
                ?: NO_VALUE
        }
    }
}
