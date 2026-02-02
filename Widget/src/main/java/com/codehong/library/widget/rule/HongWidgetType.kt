package com.codehong.library.widget.rule

enum class HongWidgetType(
    val value: String,
    val allowPlayground: Boolean = false
) {
    TEXT("Text", true),
    TEXT_CHECK("TextCheck", true),
    TEXT_UP_DOWN("TextUpDown", true),
    TEXT_UNIT("TextUnit", true),
    TEXT_BADGE("TextBadge", true),
    TEXT_COUNT("TextCount", true),
    IMAGE("Image", true),
    HEADER_CLOSE("HeaderClose", true),
    HEADER_ICON("HeaderIcon", true),
    TEXT_FILED("TextField", true),
    TEXT_FIELD_UNDERLINE("TextFieldUnderline", true),
    TEXT_FIELD_TIMER("TextFieldTimer", true),
    TEXT_FIELD_NUMBER("TextFieldNumber", true),
    TEXT_FIELD_BORDER("TextFieldBorder", true),
    TEXT_FIELD_BORDER_SELECT("TextFieldBorderSelect", true),
    BUTTON_TEXT("ButtonText", true),
    BUTTON_SELECT("ButtonSelect", true),
    BUTTON_ICON("ButtonIcon", true),
    CALENDAR("Calendar", true),
    HORIZONTAL_PAGER("HorizontalViewPager", true),
    TAB_SCROLL("TabScroll", true),
    TAB_SEGMENT("TabSegment", true),
    TAB_FLOW("TabFlow", true),
    CAPTURE_SHARE("CaptureShare", false),
    DYNAMIC_ISLAND("DynamicIsland", false),
    VIDEO_POPUP("VideoPopup", false),
    VIDEO_PLAYER("VideoPlayer", false),
    CHECKBOX("Checkbox", true),
    SWITCH("Switch", true),
    LABEL("Label", true),
    LABEL_INPUT("LabelInput", true),
    LABEL_SELECT_INPUT("LabelSelectInput", false),
    LABEL_SWITCH("LabelSwitch", true),
    LABEL_CHECKBOX("LabelCheckbox", true),
    PICKER("Picker", false),
    BOTTOM_SHEET_SELECT("BottomSheetSelect", false),
    BOTTOM_SHEET_SWIPE("BottomSheetSwipe", false),
    GRAPH_LINE("GraphLine", true),
    GRAPH_BAR("GraphBar", true),
    ICON("Icon", true),
    GRID_DRAG_AND_DROP("GridDragAndDrop", false),
    SCROLL_FADE_ANIM_LAYOUT("ScrollFadeAnimLayout", false),
    LIQUID_GLASS("LiquidGlass", false),
    PROGRESS("Progress", false),
    NO_VALUE("no_value");

    companion object {
        fun String?.toHongWidgetType(): HongWidgetType {
            return entries.firstOrNull { it.value.equals(this, true) }
                ?: NO_VALUE
        }
    }
}