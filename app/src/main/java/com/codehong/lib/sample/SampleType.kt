package com.codehong.lib.sample

enum class SampleType(
    val value: String
) {
    XML("xml"),
    OPTION_BUILDER("option builder"),
    COMPOSE("compose");

    companion object {
        fun String.toType(): SampleType? {
            return entries.find { it.value == this }
        }
    }
}
