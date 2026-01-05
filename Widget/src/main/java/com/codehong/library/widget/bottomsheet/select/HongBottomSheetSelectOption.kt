package com.codehong.library.widget.bottomsheet.select

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongBottomSheetSelectOption(
    override val type: HongWidgetType = HongWidgetType.BOTTOM_SHEET_SELECT
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_TOP_RADIUS = 24
        val DEFAULT_SELECT_RADIUS = HongRadiusInfo(
            topLeft = 8,
            topRight = 8,
            bottomLeft = 8,
            bottomRight = 8
        )
        val DEFAULT_SELECTOR_BORDER = HongBorderInfo(
            width = 2,
            color = HongColor.MAIN_ORANGE_100.hex
        )
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()

    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()
    override var radius: HongRadiusInfo = HongRadiusInfo()


    var topRadius: Int = DEFAULT_TOP_RADIUS
    var dimColorHex: String = HongColor.GRAY_30.hex

    var dragHandleColorHex: String = HongColor.GRAY_50.hex

    var title: String = ""
    var titleTypo: HongTypo = HongTypo.BODY_20_B
    var titleColorHex = HongColor.BLACK_100.hex

    var selectionBackgroundColorHex: String = HongColor.GRAY_05.hex
    var selectionRadius = DEFAULT_SELECT_RADIUS
    var selectionSelectorBorder = DEFAULT_SELECTOR_BORDER

    var selectionTitleTypo: HongTypo = HongTypo.BODY_16
    var selectionTitleColorHex: String = HongColor.BLACK_100.hex

    var selectionSubtitleTypo: HongTypo = HongTypo.CONTENTS_12
    var selectionSubtitleColorHex: String = HongColor.BLACK_70.hex


    var selectionList: List<Pair<String, String>> = emptyList()

    var initialSelection: Pair<String,String> = Pair("", "")

    var selectSelectionCallback: ((Pair<String, String>) -> Unit) = {}
    var onChangeVisibleState: ((Boolean) -> Unit) = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongBottomSheetSelectOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (radius != other.radius) return false
        if (topRadius != other.topRadius) return false
        if (dimColorHex != other.dimColorHex) return false
        if (dragHandleColorHex != other.dragHandleColorHex) return false
        if (title != other.title) return false
        if (titleTypo != other.titleTypo) return false
        if (titleColorHex != other.titleColorHex) return false
        if (selectionBackgroundColorHex != other.selectionBackgroundColorHex) return false
        if (selectionRadius != other.selectionRadius) return false
        if (selectionSelectorBorder != other.selectionSelectorBorder) return false
        if (selectionTitleTypo != other.selectionTitleTypo) return false
        if (selectionTitleColorHex != other.selectionTitleColorHex) return false
        if (selectionSubtitleTypo != other.selectionSubtitleTypo) return false
        if (selectionSubtitleColorHex != other.selectionSubtitleColorHex) return false
        if (selectionList != other.selectionList) return false
        if (initialSelection != other.initialSelection) return false
        if (selectSelectionCallback != other.selectSelectionCallback) return false
        if (onChangeVisibleState != other.onChangeVisibleState) return false

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
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + topRadius
        result = 31 * result + dimColorHex.hashCode()
        result = 31 * result + dragHandleColorHex.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + titleTypo.hashCode()
        result = 31 * result + titleColorHex.hashCode()
        result = 31 * result + selectionBackgroundColorHex.hashCode()
        result = 31 * result + selectionRadius.hashCode()
        result = 31 * result + selectionSelectorBorder.hashCode()
        result = 31 * result + selectionTitleTypo.hashCode()
        result = 31 * result + selectionTitleColorHex.hashCode()
        result = 31 * result + selectionSubtitleTypo.hashCode()
        result = 31 * result + selectionSubtitleColorHex.hashCode()
        result = 31 * result + selectionList.hashCode()
        result = 31 * result + initialSelection.hashCode()
        result = 31 * result + selectSelectionCallback.hashCode()
        result = 31 * result + onChangeVisibleState.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongBottomSheetSelectOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "radius=$radius, " +
                "topRadius=$topRadius, " +
                "dimColorHex='$dimColorHex', " +
                "dragHandleColorHex='$dragHandleColorHex', " +
                "title='$title', " +
                "titleTypo=$titleTypo, " +
                "titleColorHex='$titleColorHex', " +
                "selectionBackgroundColorHex='$selectionBackgroundColorHex', " +
                "selectionRadius=$selectionRadius, " +
                "selectionSelectorBorder=$selectionSelectorBorder, " +
                "selectionTitleTypo=$selectionTitleTypo, " +
                "selectionTitleColorHex='$selectionTitleColorHex', " +
                "selectionSubtitleTypo=$selectionSubtitleTypo, " +
                "selectionSubtitleColorHex='$selectionSubtitleColorHex', " +
                "selectionList=$selectionList, " +
                "initialSelection=$initialSelection, " +
                "selectSelectionCallback=$selectSelectionCallback, " +
                "onChangeVisibleState=$onChangeVisibleState" +
                ")"
    }
}