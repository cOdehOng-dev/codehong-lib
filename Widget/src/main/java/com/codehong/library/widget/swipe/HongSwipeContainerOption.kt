package com.codehong.library.widget.swipe

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

data class HongSwipeContainerOption(
    override val type: HongWidgetType = HongWidgetType.SWIPE_CONTAINER
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

    var buttonColorHex: String = HongColor.RED_100.hex

    var buttonText: String = "삭제"
    var buttonTextColorHex: String = HongColor.WHITE_100.hex
    var buttonTextTypo = HongTypo.BODY_14_B

    var onClickButton: () -> Unit = {}
    var content: @Composable () -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongSwipeContainerOption

        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (type != other.type) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (buttonColorHex != other.buttonColorHex) return false
        if (buttonText != other.buttonText) return false
        if (buttonTextColorHex != other.buttonTextColorHex) return false
        if (buttonTextTypo != other.buttonTextTypo) return false
        if (onClickButton != other.onClickButton) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + buttonColorHex.hashCode()
        result = 31 * result + (buttonText?.hashCode() ?: 0)
        result = 31 * result + buttonTextColorHex.hashCode()
        result = 31 * result + buttonTextTypo.hashCode()
        result = 31 * result + onClickButton.hashCode()
        result = 31 * result + content.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongSwipeContainerOption(" +
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
                "buttonColorHex='$buttonColorHex', " +
                "buttonText=$buttonText, " +
                "buttonTextColorHex='$buttonTextColorHex', " +
                "buttonTextTypo=$buttonTextTypo, " +
                "onClickButton=$onClickButton, " +
                "content=$content" +
                ")"
    }


}