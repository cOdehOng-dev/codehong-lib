package com.codehong.library.widget.layout.fade

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongScrollFadeLayoutOption(
    override val type: HongWidgetType = HongWidgetType.SCROLL_FADE_ANIM_LAYOUT
) : HongWidgetCommonOption {

    companion object {
        const val MAIN_CONTENT_HEIGHT = 446
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()


    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    var mainContentHeightDp: Int = MAIN_CONTENT_HEIGHT

    var headerContent: @Composable (Boolean, Float) -> Unit = { _, _ -> }
    var mainContent: @Composable () -> Unit = {}
    var subContentList: (LazyListScope) -> Unit = {}
    var bottomContent: @Composable () -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongScrollFadeLayoutOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (mainContentHeightDp != other.mainContentHeightDp) return false
        if (headerContent != other.headerContent) return false
        if (mainContent != other.mainContent) return false
        if (subContentList != other.subContentList) return false
        if (bottomContent != other.bottomContent) return false

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
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + mainContentHeightDp
        result = 31 * result + headerContent.hashCode()
        result = 31 * result + mainContent.hashCode()
        result = 31 * result + subContentList.hashCode()
        result = 31 * result + bottomContent.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongScrollFadeLayoutOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "mainContentHeightDp=$mainContentHeightDp, " +
                "headerContent=$headerContent, " +
                "mainContent=$mainContent, " +
                "subContentList=$subContentList, " +
                "bottomContent=$bottomContent" +
                ")"
    }


}
