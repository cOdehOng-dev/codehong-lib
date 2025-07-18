package com.codehong.library.widget.rule

enum class HongState(
    val alias: String
) {
    ENABLED("enabled"),
    DISABLED("disabled");

    companion object {
        fun String?.toHonState(): HongState {
            if (this.isNullOrEmpty()) return ENABLED
            return entries.find { it.alias == this } ?: ENABLED
        }

        fun HongState?.isEnabled(): Boolean {
            if (this == null) return true
            return this == ENABLED
        }
    }
}