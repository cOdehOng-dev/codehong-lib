package com.codehong.library.widget.text.unit

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
import com.codehong.library.widget.text.HongTextOption

data class HongTextUnitOption(
    override val type: HongWidgetType = HongWidgetType.TEXT
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_TEXT_OPTION = HongTextBuilder()
            .typography(HongTypo.BODY_16)
            .color(HongColor.BLACK_100)
            .applyOption()
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()

    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var text: String? = null
    var textOption: HongTextOption = DEFAULT_TEXT_OPTION

    var unitText: String? = null
    var unitTextOption: HongTextOption = DEFAULT_TEXT_OPTION

    var useNumberDecimal = false


}