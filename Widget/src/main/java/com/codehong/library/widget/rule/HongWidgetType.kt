package com.codehong.library.widget.rule

enum class HongWidgetType(val id: Int, val value: String) {
    TEXT(1, "text"),
    NO_VALUE(-1, "no_value");

    companion object {
        fun String?.toHongWidgetType(): HongWidgetType {
            return values().firstOrNull { it.value.equals(this, true) }
                ?: NO_VALUE
        }
    }
}
