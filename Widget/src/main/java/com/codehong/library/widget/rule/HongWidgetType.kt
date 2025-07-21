package com.codehong.library.widget.rule

enum class HongWidgetType(
    val id: Int,
    val value: String,
    val allowPlayground: Boolean = false,
) {
    TEXT(1, "Text", true),
    TEXT_CHECK(26, "TextCheck", true),
    TEXT_UP_DOWN(27, "TextUpDown", true),
    TEXT_UNIT(28, "TextUnit", true),
    TEXT_BADGE(10, "TextBadge", true),
    IMAGE(2, "Image", true),
    CLOSE_HEADER(3, "CloseHeader", true),
    TEXT_FILED(4, "TextField", true),
    UNDERLINE_TEXT_FIELD(23, "UnderlineTextField", true),
    TIMER_TEXT_FIELD(24, "TimerTextField", true),
    NUMBER_TEXT_FIELD(25, "NumberTextField", true),
//    SEARCH_BAR(5, "SearchBar", true),
    TEXT_BUTTON(6, "TextButton", true),
    SELECT_BUTTON(7, "SelectButton", true),
    CALENDAR(8, "Calendar", true),
    HORIZONTAL_PAGER(9, "HorizontalViewPager", true),
    SCROLL_TAB(11, "ScrollTab", true),
    CAPTURE_SHARE(12, "CaptureShare", false),
    DYNAMIC_ISLAND(13, "DynamicIsland", false),
    VIDEO_POPUP(14, "VideoPopup", false),
    VIDEO_PLAYER(15, "VideoPlayer", false),
    CHECKBOX(16, "Checkbox", true),
    SWITCH(17, "Switch", true),
    LABEL(18, "Label", true),
    LABEL_INPUT(19, "LabelInput", true),
    LABEL_SELECT_INPUT(20, "LabelSelectInput", false),
    LABEL_SWITCH(21, "LabelSwitch", true),
    LABEL_CHECKBOX(22, "LabelCheckbox", true),
    NO_VALUE(-1, "no_value");

    companion object {
        fun String?.toHongWidgetType(): HongWidgetType {
            return entries.firstOrNull { it.value.equals(this, true) }
                ?: NO_VALUE
        }
    }
}
