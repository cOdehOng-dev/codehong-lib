package com.codehong.library.widget.rule

enum class HongWidgetType(
    val id: Int,
    val value: String,
    val allowPlayground: Boolean = false,
) {
    TEXT(1, "Text", true),
    TEXT_CHECK(2, "TextCheck", true),
    TEXT_UP_DOWN(3, "TextUpDown", true),
    TEXT_UNIT(4, "TextUnit", true),
    TEXT_BADGE(5, "TextBadge", true),
    IMAGE(6, "Image", true),
    CLOSE_HEADER(7, "CloseHeader", true),
    TEXT_FILED(8, "TextField", true),
    UNDERLINE_TEXT_FIELD(9, "UnderlineTextField", true),
    TIMER_TEXT_FIELD(9, "TimerTextField", true),
    NUMBER_TEXT_FIELD(10, "NumberTextField", true),
//    SEARCH_BAR(5, "SearchBar", true),
    BUTTON_TEXT(11, "ButtonText", true),
    BUTTON_SELECT(12, "ButtonSelect", true),
    BUTTON_ICON(13, "ButtonIcon", true),
    CALENDAR(14, "Calendar", true),
    HORIZONTAL_PAGER(15, "HorizontalViewPager", true),
    TAB_SCROLL(16, "TabScroll", true),
    TAB_SEGMENT(17, "TabSegment", true),
    CAPTURE_SHARE(18, "CaptureShare", false),
    DYNAMIC_ISLAND(19, "DynamicIsland", false),
    VIDEO_POPUP(20, "VideoPopup", false),
    VIDEO_PLAYER(21, "VideoPlayer", false),
    CHECKBOX(22, "Checkbox", true),
    SWITCH(23, "Switch", true),
    LABEL(24, "Label", true),
    LABEL_INPUT(25, "LabelInput", true),
    LABEL_SELECT_INPUT(26, "LabelSelectInput", false),
    LABEL_SWITCH(27, "LabelSwitch", true),
    LABEL_CHECKBOX(28, "LabelCheckbox", true),
    PICKER(29, "Picker", false),
    NO_VALUE(-1, "no_value");

    companion object {
        fun String?.toHongWidgetType(): HongWidgetType {
            return entries.firstOrNull { it.value.equals(this, true) }
                ?: NO_VALUE
        }
    }
}
