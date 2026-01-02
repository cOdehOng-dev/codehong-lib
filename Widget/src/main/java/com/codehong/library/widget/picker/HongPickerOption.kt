package com.codehong.library.widget.picker

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongPickerOption(
    override val type: HongWidgetType = HongWidgetType.PICKER,
) : HongWidgetCommonOption {
    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()

    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false


    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var radius: HongRadiusInfo = HongRadiusInfo(
        topLeft = 16,
        topRight = 16,
    )


    var titleColorHex: String = HongColor.BLACK_100.hex
    var title: String = ""

    var buttonText: String = ""

    var initialFirstOption: Int = 0
    var firstOptionList: List<String> = emptyList()


    var initialSecondOption: Int = 0
    var secondOptionList: List<String>? = null

    var useDimClickCLose: Boolean = false

    var selectorColorHex: String = HongColor.GRAY_10.hex

    var onDismiss: () -> Unit = {}

    var onConfirm: ((selectedFirstOption: Pair<Int, String>, selectedSecondOption: Pair<Int, String?>) -> Unit)? = null

    var onDirectSelect: ((selectedFirstOption: Pair<Int, String>, selectedSecondOption: Pair<Int, String?>) -> Unit)? = null



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongPickerOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (radius != other.radius) return false
        if (titleColorHex != other.titleColorHex) return false
        if (title != other.title) return false
        if (buttonText != other.buttonText) return false
        if (initialFirstOption != other.initialFirstOption) return false
        if (firstOptionList != other.firstOptionList) return false
        if (initialSecondOption != other.initialSecondOption) return false
        if (secondOptionList != other.secondOptionList) return false
        if (useDimClickCLose != other.useDimClickCLose) return false
        if (selectorColorHex != other.selectorColorHex) return false
        if (onDismiss != other.onDismiss) return false
        if (onConfirm != other.onConfirm) return false
        if (onDirectSelect != other.onDirectSelect) return false

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
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + titleColorHex.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + buttonText.hashCode()
        result = 31 * result + initialFirstOption
        result = 31 * result + firstOptionList.hashCode()
        result = 31 * result + initialSecondOption
        result = 31 * result + (secondOptionList?.hashCode() ?: 0)
        result = 31 * result + useDimClickCLose.hashCode()
        result = 31 * result + selectorColorHex.hashCode()
        result = 31 * result + onDismiss.hashCode()
        result = 31 * result + (onConfirm?.hashCode() ?: 0)
        result = 31 * result + (onDirectSelect?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongPickerOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "radius=$radius, " +
                "titleColorHex='$titleColorHex', " +
                "title='$title', " +
                "buttonText='$buttonText', " +
                "initialFirstOption=$initialFirstOption, " +
                "firstOptionList=$firstOptionList, " +
                "initialSecondOption=$initialSecondOption, " +
                "secondOptionList=$secondOptionList, " +
                "useDimClickCLose=$useDimClickCLose, " +
                "selectorColorHex='$selectorColorHex', " +
                "onDismiss=$onDismiss, " +
                "onConfirm=$onConfirm, " +
                "onDirectSelect=$onDirectSelect" +
                ")"
    }


}
