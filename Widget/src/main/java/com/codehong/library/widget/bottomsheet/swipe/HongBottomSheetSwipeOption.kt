package com.codehong.library.widget.bottomsheet.swipe

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongBottomSheetSwipeOption(
    override val type: HongWidgetType = HongWidgetType.BOTTOM_SHEET_SWIPE
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_BOTTOM_SHEET_MAX_HEIGHT = 280f
        const val DEFAULT_BOTTOM_SHEET_MIN_HEIGHT = 50f
        const val DEFAULT_BOTTOM_SHEET_TOP_RADIUS = 20
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()
    override var radius: HongRadiusInfo = HongRadiusInfo()


    override var backgroundColorHex: String = HongColor.MAIN_ORANGE_100.hex

    var bottomSheetMaxHeight: Float = DEFAULT_BOTTOM_SHEET_MAX_HEIGHT
    var bottomSheetMinHeight: Float = DEFAULT_BOTTOM_SHEET_MIN_HEIGHT

    var bottomSheetBackgroundColorHex: String = HongColor.WHITE_100.hex
    var bottomSheetTopRadius = DEFAULT_BOTTOM_SHEET_TOP_RADIUS

    var closeIconColorHex: String = HongColor.BLACK_100.hex

    var onCloseClick: () -> Unit = {}

    var content: @Composable BoxScope.() -> Unit = {}
    var bottomSheetContent: @Composable ColumnScope.() -> Unit = {}


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongBottomSheetSwipeOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (radius != other.radius) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (bottomSheetMaxHeight != other.bottomSheetMaxHeight) return false
        if (bottomSheetMinHeight != other.bottomSheetMinHeight) return false
        if (bottomSheetBackgroundColorHex != other.bottomSheetBackgroundColorHex) return false
        if (bottomSheetTopRadius != other.bottomSheetTopRadius) return false
        if (closeIconColorHex != other.closeIconColorHex) return false
        if (onCloseClick != other.onCloseClick) return false
        if (content != other.content) return false
        if (bottomSheetContent != other.bottomSheetContent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + bottomSheetMaxHeight.hashCode()
        result = 31 * result + bottomSheetMinHeight.hashCode()
        result = 31 * result + bottomSheetBackgroundColorHex.hashCode()
        result = 31 * result + bottomSheetTopRadius
        result = 31 * result + closeIconColorHex.hashCode()
        result = 31 * result + onCloseClick.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + bottomSheetContent.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongBottomSheetSwipeOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "radius=$radius, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "bottomSheetMaxHeight=$bottomSheetMaxHeight, " +
                "bottomSheetMinHeight=$bottomSheetMinHeight, " +
                "bottomSheetBackgroundColorHex='$bottomSheetBackgroundColorHex', " +
                "bottomSheetTopRadius=$bottomSheetTopRadius, " +
                "closeIconColorHex='$closeIconColorHex', " +
                "onCloseClick=$onCloseClick, " +
                "content=$content, " +
                "bottomSheetContent=$bottomSheetContent" +
                ")"
    }
}