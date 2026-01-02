package com.codehong.library.widget.rule

import com.codehong.library.widget.rule.color.HongColor

data class HongShadowInfo(
    val color: String = HongColor.TRANSPARENT.hex,
    val blur: Float = 0f,
    val offsetY: Float = 0f,
    val offsetX: Float = 0f,
    val spread: Float = 0f
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongShadowInfo

        if (color != other.color) return false
        if (blur != other.blur) return false
        if (offsetY != other.offsetY) return false
        if (offsetX != other.offsetX) return false
        if (spread != other.spread) return false

        return true
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + blur.hashCode()
        result = 31 * result + offsetY.hashCode()
        result = 31 * result + offsetX.hashCode()
        result = 31 * result + spread.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongShadow(" +
                "color='$color', " +
                "blur=$blur, " +
                "offsetY=$offsetY, " +
                "offsetX=$offsetX, " +
                "spread=$spread" +
                ")"
    }
}
