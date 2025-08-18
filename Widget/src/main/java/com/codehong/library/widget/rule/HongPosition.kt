package com.codehong.library.widget.rule

enum class HongPosition(val alias: String) {
    TOP("top"),
    BOTTOM("bottom"),
    LEFT("left"),
    RIGHT("right");

    companion object {
        fun String?.toHongPosition(): HongPosition {
            if (this == null) return LEFT
            return entries.find { it.alias == this } ?: LEFT
        }
    }
}