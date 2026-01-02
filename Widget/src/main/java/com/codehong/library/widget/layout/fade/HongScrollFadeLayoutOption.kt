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
import com.codehong.library.widget.rule.typo.HongTypo

data class HongScrollFadeLayoutOption(
    override val type: HongWidgetType = HongWidgetType.SCROLL_FADE_ANIM_LAYOUT
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_MAIN_CONTENT_HEIGHT = 446
        val DEFAULT_TITLE_TYPO = HongTypo.TITLE_26_B

        val DEFAULT_COLOR = Pair(HongColor.WHITE_100.hex, HongColor.BLACK_100.hex)
        val DEFAULT_HEADER_BACKGROUND_COLOR = HongColor.WHITE_100.hex
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
    var mainContentHeightDp: Int = DEFAULT_MAIN_CONTENT_HEIGHT

    var mainContent: @Composable () -> Unit = {}
    var subContentList: (LazyListScope) -> Unit = {}
    var bottomContent: @Composable () -> Unit = {}

    var leftIconInfo: Any? = null
    var leftIconColorHex: Pair<String, String> = DEFAULT_COLOR
    var leftIconClick: () -> Unit = {}

    var rightIconInfo: Any? = null
    var rightIconColorHex: Pair<String, String> = DEFAULT_COLOR
    var rightIconClick: () -> Unit = {}


    var headerBackgroundColorHex: String = DEFAULT_HEADER_BACKGROUND_COLOR

    var titleText: String = ""
    var titleTypo: HongTypo = DEFAULT_TITLE_TYPO
    var useTitleOverFlow: Boolean = false
    var titleColorHex: Pair<String, String> = DEFAULT_COLOR

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
        if (mainContent != other.mainContent) return false
        if (subContentList != other.subContentList) return false
        if (bottomContent != other.bottomContent) return false
        if (leftIconInfo != other.leftIconInfo) return false
        if (leftIconColorHex != other.leftIconColorHex) return false
        if (leftIconClick != other.leftIconClick) return false
        if (rightIconInfo != other.rightIconInfo) return false
        if (rightIconColorHex != other.rightIconColorHex) return false
        if (rightIconClick != other.rightIconClick) return false
        if (headerBackgroundColorHex != other.headerBackgroundColorHex) return false
        if (titleText != other.titleText) return false
        if (titleTypo != other.titleTypo) return false
        if (useTitleOverFlow != other.useTitleOverFlow) return false
        if (titleColorHex != other.titleColorHex) return false

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
        result = 31 * result + mainContent.hashCode()
        result = 31 * result + subContentList.hashCode()
        result = 31 * result + bottomContent.hashCode()
        result = 31 * result + (leftIconInfo?.hashCode() ?: 0)
        result = 31 * result + leftIconColorHex.hashCode()
        result = 31 * result + leftIconClick.hashCode()
        result = 31 * result + (rightIconInfo?.hashCode() ?: 0)
        result = 31 * result + rightIconColorHex.hashCode()
        result = 31 * result + rightIconClick.hashCode()
        result = 31 * result + headerBackgroundColorHex.hashCode()
        result = 31 * result + titleText.hashCode()
        result = 31 * result + titleTypo.hashCode()
        result = 31 * result + useTitleOverFlow.hashCode()
        result = 31 * result + titleColorHex.hashCode()
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
                "mainContent=$mainContent, " +
                "subContentList=$subContentList, " +
                "bottomContent=$bottomContent, " +
                "leftIconInfo=$leftIconInfo, " +
                "leftIconColorHex=$leftIconColorHex, " +
                "leftIconClick=$leftIconClick, " +
                "rightIconInfo=$rightIconInfo, " +
                "rightIconColorHex=$rightIconColorHex, " +
                "rightIconClick=$rightIconClick, " +
                "headerBackgroundColorHex='$headerBackgroundColorHex', " +
                "titleText='$titleText', " +
                "titleTypo=$titleTypo, " +
                "useTitleOverFlow=$useTitleOverFlow, " +
                "titleColorHex=$titleColorHex" +
                ")"
    }

}
