package com.codehong.library.widget.tab.scroll

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTabScrollOption(
    override val type: HongWidgetType = HongWidgetType.TAB_SCROLL
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()

    var tabList: List<Any> = emptyList()

    var tabTextList: List<String> = emptyList()


    var selectTabTextTypo: HongTypo = HongTypo.BODY_14_B
    var selectBackgroundColorHex: String = HongColor.MAIN_ORANGE_100.hex
    var selectBorderColorHex: String = HongColor.TRANSPARENT.hex
    var selectTabTextColorHex: String = HongColor.WHITE_100.hex
    var selectBorderWidth = 0


    var unselectTabTextTypo: HongTypo = HongTypo.BODY_14_B
    var unselectTabTextColorHex: String = HongColor.BLACK_100.hex
    var unselectBackgroundColorHex: String = HongColor.WHITE_100.hex
    var unselectBorderColorHex: String = HongColor.LINE.hex
    var unselectBorderWidth = 1

    var tabBetweenPadding: Int = 0

    var tabTextHorizontalPadding: Int = 16
    var tabTextVerticalPadding: Int = 8

    var initialSelectIndex: Int = 0

    var tabClick: ((index: Int, item: Any) -> Unit)? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTabScrollOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (tabList != other.tabList) return false
        if (tabTextList != other.tabTextList) return false
        if (selectBorderWidth != other.selectBorderWidth) return false
        if (unselectBorderWidth != other.unselectBorderWidth) return false
        if (selectBackgroundColorHex != other.selectBackgroundColorHex) return false
        if (selectBorderColorHex != other.selectBorderColorHex) return false
        if (selectTabTextTypo != other.selectTabTextTypo) return false
        if (selectTabTextColorHex != other.selectTabTextColorHex) return false
        if (unselectTabTextTypo != other.unselectTabTextTypo) return false
        if (unselectTabTextColorHex != other.unselectTabTextColorHex) return false
        if (unselectBackgroundColorHex != other.unselectBackgroundColorHex) return false
        if (unselectBorderColorHex != other.unselectBorderColorHex) return false
        if (tabBetweenPadding != other.tabBetweenPadding) return false
        if (tabTextHorizontalPadding != other.tabTextHorizontalPadding) return false
        if (tabTextVerticalPadding != other.tabTextVerticalPadding) return false
        if (initialSelectIndex != other.initialSelectIndex) return false
        if (tabClick != other.tabClick) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + tabList.hashCode()
        result = 31 * result + tabTextList.hashCode()
        result = 31 * result + selectBorderWidth
        result = 31 * result + unselectBorderWidth
        result = 31 * result + selectBackgroundColorHex.hashCode()
        result = 31 * result + selectBorderColorHex.hashCode()
        result = 31 * result + selectTabTextTypo.hashCode()
        result = 31 * result + selectTabTextColorHex.hashCode()
        result = 31 * result + unselectTabTextTypo.hashCode()
        result = 31 * result + unselectTabTextColorHex.hashCode()
        result = 31 * result + unselectBackgroundColorHex.hashCode()
        result = 31 * result + unselectBorderColorHex.hashCode()
        result = 31 * result + tabBetweenPadding
        result = 31 * result + tabTextHorizontalPadding
        result = 31 * result + tabTextVerticalPadding
        result = 31 * result + initialSelectIndex
        result = 31 * result + (tabClick?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongTabScrollOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "tabList=$tabList, " +
                "tabTextList=$tabTextList, " +
                "selectBorderWidth=$selectBorderWidth, " +
                "unselectBorderWidth=$unselectBorderWidth, " +
                "selectBackgroundColorHex='$selectBackgroundColorHex', " +
                "selectBorderColorHex='$selectBorderColorHex', " +
                "selectTabTextTypo=$selectTabTextTypo, " +
                "selectTabTextColorHex='$selectTabTextColorHex', " +
                "unselectTabTextTypo=$unselectTabTextTypo, " +
                "unselectTabTextColorHex='$unselectTabTextColorHex', " +
                "unselectBackgroundColor='$unselectBackgroundColorHex', " +
                "unselectBorderColor='$unselectBorderColorHex', " +
                "tabBetweenPadding=$tabBetweenPadding, " +
                "tabTextHorizontalPadding=$tabTextHorizontalPadding, " +
                "tabTextVerticalPadding=$tabTextVerticalPadding, " +
                "initialSelectIndex=$initialSelectIndex, " +
                "tabClick=$tabClick" +
                ")"
    }

}