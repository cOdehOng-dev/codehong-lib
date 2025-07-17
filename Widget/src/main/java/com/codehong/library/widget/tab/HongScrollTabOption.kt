package com.codehong.library.widget.tab

import com.codehong.library.widget.HongWidgetAdvanceOption
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

data class HongScrollTabOption(
    override val type: HongWidgetType = HongWidgetType.SCROLL_TAB
) : HongWidgetAdvanceOption {

    companion object {
        const val DEFAULT_BORDER_WIDTH = 1

        val DEFAULT_SELECT_BORDER_COLOR = HongColor.WHITE_100.hex
        val DEFAULT_UNSELECT_BORDER_COLOR = HongColor.LINE.hex

        val DEFAULT_SELECT_BACKGROUND_COLOR = HongColor.MAIN_ORANGE_100.hex
        val DEFAULT_UNSELECT_BACKGROUND_COLOR = HongColor.WHITE_100.hex

        val DEFAULT_SELECT_TEXT_COLOR = HongColor.WHITE_100.hex
        val DEFAULT_SELECT_TEXT_TYPO = HongTypo.BODY_14_B

        val DEFAULT_UNSELECT_TEXT_COLOR = HongColor.BLACK_100.hex
        val DEFAULT_UNSELECT_TEXT_TYPO = HongTypo.BODY_14_B

        const val DEFAULT_TAB_BETWEEN_PADDING = 0

        const val DEFAULT_TAB_TEXT_HORIZONTAL_PADDING = 16
        const val DEFAULT_TAB_TEXT_VERTICAL_PADDING = 8

        const val DEFAULT_INITIAL_SELECT_INDEX = 0
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()

    var tabList: List<Any> = emptyList()

    var tabTitleList: List<String> = emptyList()

    var borderWidth = DEFAULT_BORDER_WIDTH

    var selectBackgroundColor: String = DEFAULT_SELECT_BACKGROUND_COLOR
    var selectBorderColor: String = DEFAULT_SELECT_BORDER_COLOR

    var selectTabTextOption = HongTextBuilder()
        .typography(DEFAULT_SELECT_TEXT_TYPO)
        .color(DEFAULT_SELECT_TEXT_COLOR)
        .applyOption()

    var unselectTabTextOption = HongTextBuilder()
        .typography(DEFAULT_UNSELECT_TEXT_TYPO)
        .color(DEFAULT_UNSELECT_TEXT_COLOR)
        .applyOption()


    var unselectBackgroundColor: String = DEFAULT_UNSELECT_BACKGROUND_COLOR
    var unselectBorderColor: String = DEFAULT_UNSELECT_BORDER_COLOR

    var tabBetweenPadding: Int = DEFAULT_TAB_BETWEEN_PADDING

    var tabTextHorizontalPadding: Int = DEFAULT_TAB_TEXT_HORIZONTAL_PADDING
    var tabTextVerticalPadding: Int = DEFAULT_TAB_TEXT_VERTICAL_PADDING



    var initialSelectIndex: Int = DEFAULT_INITIAL_SELECT_INDEX

    var tabClick: ((index: Int, item: Any) -> Unit)? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongScrollTabOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (tabList != other.tabList) return false
        if (tabTitleList != other.tabTitleList) return false
        if (borderWidth != other.borderWidth) return false
        if (selectBackgroundColor != other.selectBackgroundColor) return false
        if (selectBorderColor != other.selectBorderColor) return false
        if (selectTabTextOption != other.selectTabTextOption) return false
        if (unselectTabTextOption != other.unselectTabTextOption) return false
        if (unselectBackgroundColor != other.unselectBackgroundColor) return false
        if (unselectBorderColor != other.unselectBorderColor) return false
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
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + tabList.hashCode()
        result = 31 * result + tabTitleList.hashCode()
        result = 31 * result + borderWidth
        result = 31 * result + selectBackgroundColor.hashCode()
        result = 31 * result + selectBorderColor.hashCode()
        result = 31 * result + selectTabTextOption.hashCode()
        result = 31 * result + unselectTabTextOption.hashCode()
        result = 31 * result + unselectBackgroundColor.hashCode()
        result = 31 * result + unselectBorderColor.hashCode()
        result = 31 * result + tabBetweenPadding
        result = 31 * result + tabTextHorizontalPadding
        result = 31 * result + tabTextVerticalPadding
        result = 31 * result + initialSelectIndex
        result = 31 * result + (tabClick?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongScrollTabOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "tabList=$tabList, " +
                "tabTitleList=$tabTitleList, " +
                "borderWidth=$borderWidth, " +
                "selectBackgroundColor='$selectBackgroundColor', " +
                "selectBorderColor='$selectBorderColor', " +
                "selectTabTextOption=$selectTabTextOption, " +
                "unselectTabTextOption=$unselectTabTextOption, " +
                "unselectBackgroundColor='$unselectBackgroundColor', " +
                "unselectBorderColor='$unselectBorderColor', " +
                "tabBetweenPadding=$tabBetweenPadding, " +
                "tabTextHorizontalPadding=$tabTextHorizontalPadding, " +
                "tabTextVerticalPadding=$tabTextVerticalPadding, " +
                "initialSelectIndex=$initialSelectIndex, " +
                "tabClick=$tabClick" +
                ")"
    }

}