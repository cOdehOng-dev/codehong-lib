package com.codehong.library.widget.rule.graph

data class GraphPoint(
    val label: String,
    val point: Double,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GraphPoint

        if (label != other.label) return false
        if (point != other.point) return false

        return true
    }

    override fun hashCode(): Int {
        var result = label.hashCode()
        result = 31 * result + point.hashCode()
        return result
    }

    override fun toString(): String {
        return "GraphPoint(" +
                "label='$label', " +
                "point=$point" +
                ")"
    }
}
