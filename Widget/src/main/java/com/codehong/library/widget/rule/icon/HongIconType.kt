package com.codehong.library.widget.rule.icon

enum class HongIconType(
    val key: String,
    val size: Int
) {
    H16("h16", 16),
    H20("h20", 20),
    H24("h24", 24),
    H28("h45", 45),

    ;

    companion object {
        fun String?.toIconType(): HongIconType {
            if (this.isNullOrEmpty()) return H24
            return entries.find { it.key == this } ?: H24
        }

        fun HongIconType?.toIconTypeKey(): String {
            return this?.key ?: H24.key
        }
    }
}