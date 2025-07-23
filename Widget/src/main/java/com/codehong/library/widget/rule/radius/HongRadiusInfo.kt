package com.codehong.library.widget.rule.radius

import android.content.Context
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.codehong.library.widget.extensions.dpToFloatPx
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

data class HongRadiusInfo(
    var all: Int = 0,
    var top: Int = 0,
    var bottom: Int = 0,
    var topLeft: Int = 0,
    var topRight: Int = 0,
    var bottomLeft: Int = 0,
    var bottomRight: Int = 0
) {

    companion object {
        fun HongRadiusInfo?.toRadius(
            position: HongRadiusPositionType
        ): Int {
            if (this == null) {
                return 0
            }
            val hvRadius = when (position) {
                HongRadiusPositionType.TOP_LEFT,
                HongRadiusPositionType.TOP_RIGHT -> top
                HongRadiusPositionType.BOTTOM_LEFT,
                HongRadiusPositionType.BOTTOM_RIGHT -> bottom
            }
            val cornerRadius = when (position) {
                HongRadiusPositionType.TOP_LEFT -> topLeft
                HongRadiusPositionType.TOP_RIGHT -> topRight
                HongRadiusPositionType.BOTTOM_LEFT -> bottomLeft
                HongRadiusPositionType.BOTTOM_RIGHT -> bottomRight
            }
            return if (all > 0) {
                all
            } else {
                hvRadius.takeIf { it > 0 } ?: run { cornerRadius }
            }
        }

        fun HongRadiusInfo?.toRoundedCornersTransformation(context: Context) : Transformation {
            return RoundedCornersTransformation(
                topLeft = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.TOP_LEFT)),
                topRight = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.TOP_RIGHT)),
                bottomLeft = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.BOTTOM_LEFT)),
                bottomRight = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.BOTTOM_RIGHT)),
            )
        }

        fun HongRadiusInfo?.toRoundedCornerShape(): RoundedCornerShape {
            return RoundedCornerShape(
                topStart = this.toRadius(HongRadiusPositionType.TOP_LEFT).dp,
                topEnd = this.toRadius(HongRadiusPositionType.TOP_RIGHT).dp,
                bottomStart = this.toRadius(HongRadiusPositionType.BOTTOM_LEFT).dp,
                bottomEnd = this.toRadius(HongRadiusPositionType.BOTTOM_RIGHT).dp
            )
        }

        fun HongRadiusInfo?.toMaterialShapeDrawable(context: Context): MaterialShapeDrawable {
            return MaterialShapeDrawable().apply {
                shapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setTopLeftCornerSize(
                        context.dpToFloatPx(this@toMaterialShapeDrawable.toRadius(HongRadiusPositionType.TOP_LEFT))
                    )
                    .setTopRightCornerSize(
                        context.dpToFloatPx(this@toMaterialShapeDrawable.toRadius(HongRadiusPositionType.TOP_RIGHT))
                    )
                    .setBottomLeftCornerSize(
                        context.dpToFloatPx(this@toMaterialShapeDrawable.toRadius(HongRadiusPositionType.BOTTOM_LEFT))
                    )
                    .setBottomRightCornerSize(
                        context.dpToFloatPx(this@toMaterialShapeDrawable.toRadius(HongRadiusPositionType.BOTTOM_RIGHT))
                    )
                    .build()
            }
        }

        fun HongRadiusInfo?.toCornerRadii(context: Context): FloatArray {
            val topLeft = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.TOP_LEFT))
            val topRight = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.TOP_RIGHT))
            val bottomRight = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.BOTTOM_LEFT))
            val bottomLeft = context.dpToFloatPx(this.toRadius(HongRadiusPositionType.BOTTOM_RIGHT))
            return floatArrayOf(
                topLeft, topLeft,
                topRight, topRight,
                bottomRight, bottomRight,
                bottomLeft, bottomLeft
            )
        }

        fun HongRadiusInfo?.toRoundCornerShape(
            useShapeCircle: Boolean = false
        ): RoundedCornerShape {
            return if (useShapeCircle) {
                CircleShape
            } else {
                this.toRoundedCornerShape()
            }
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongRadiusInfo

        if (all != other.all) return false
        if (top != other.top) return false
        if (bottom != other.bottom) return false
        if (topLeft != other.topLeft) return false
        if (topRight != other.topRight) return false
        if (bottomLeft != other.bottomLeft) return false
        if (bottomRight != other.bottomRight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = all
        result = 31 * result + top
        result = 31 * result + bottom
        result = 31 * result + topLeft
        result = 31 * result + topRight
        result = 31 * result + bottomLeft
        result = 31 * result + bottomRight
        return result
    }

    override fun toString(): String {
        return "HongRadiusInfo(" +
                "allRadius=$all, " +
                "topRadius=$top, " +
                "bottomRadius=$bottom, " +
                "topLeftRadius=$topLeft, " +
                "topRightRadius=$topRight, " +
                "bottomLeftRadius=$bottomLeft, " +
                "bottomRightRadius=$bottomRight" +
                ")"
    }
}
