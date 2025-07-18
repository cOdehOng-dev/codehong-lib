package com.codehong.library.widget.label.toggleswitch

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchOption

data class HongLabelSwitchOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_SWITCH,
) : HongWidgetCommonOption {

    companion object {

        val DEFAULT_SWITCH_OPTION = HongSwitchBuilder()
            .width(55)
            .height(30)
            .onColor(HongColor.MAIN_ORANGE_100)
            .offColor(HongColor.GRAY_20)
            .cursorSize(25)
            .cursorHorizontalMargin(3)
            .cursorColor(HongColor.WHITE_100)
            .applyOption()
    }


    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var backgroundColor: HongColor = HongColor.WHITE_100
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null


    var label: String? = null
    var labelTextOption = HongTextBuilder()
        .copy(HongLabelOption.DEFAULT_LABEL_OPTION)
        .applyOption()

    var description: String? = null
    var descriptionTextOption = HongTextBuilder()
        .copy(HongLabelOption.DEFAULT_DESCRIPTION_OPTION)
        .applyOption()

    var labelOption: HongLabelOption = HongLabelOption()

    var switchOption: HongSwitchOption = DEFAULT_SWITCH_OPTION


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLabelSwitchOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (label != other.label) return false
        if (labelTextOption != other.labelTextOption) return false
        if (description != other.description) return false
        if (descriptionTextOption != other.descriptionTextOption) return false
        if (labelOption != other.labelOption) return false
        if (switchOption != other.switchOption) return false

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
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + labelTextOption.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + descriptionTextOption.hashCode()
        result = 31 * result + labelOption.hashCode()
        result = 31 * result + switchOption.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongLabelSwitchOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "label=$label, " +
                "labelTextOption=$labelTextOption, " +
                "description=$description, " +
                "descriptionTextOption=$descriptionTextOption, " +
                "labelOption=$labelOption, " +
                "switchOption=$switchOption" +
                ")"
    }


}