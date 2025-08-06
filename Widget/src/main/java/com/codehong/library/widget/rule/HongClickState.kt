package com.codehong.library.widget.rule

enum class HongClickState(
    val key: String
) {
    ENABLE("enable"),
    DISABLE("disable"),
    ;

    companion object {
        fun HongClickState.isEnable(): Boolean {
            return this == ENABLE
        }
    }
}