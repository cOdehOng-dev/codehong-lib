package com.codehong.library.widget.text.updown

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTextUpDownOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_UP_DOWN
) : HongWidgetCommonOption {

    companion object {
//        val DEFAULT_DISPLAY_TEXT_OPTION = HongTextUnitBuilder()
//            .useNumberDecimal(true)
//            .applyOption()

//        val DEFAULT_TEXT_OPTION = HongTextBuilder()
//            .typography(HongTypo.BODY_16)
//            .color(HongColor.BLACK_100)
//            .applyOption()
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

    var amount: Int = 0
    var unit: String? = null
//    var amountTextOption: HongTextOption? = DEFAULT_TEXT_OPTION


    var useDecimal = false

    var displayTypo: HongTypo = HongTypo.BODY_16
    var displayColorHex: String = HongColor.BLACK_100.hex

//    var unitTextOption: HongTextOption? = DEFAULT_TEXT_OPTION

//    var displayTextOption: HongTextUnitOption = DEFAULT_DISPLAY_TEXT_OPTION

    var buttonSize: Int = 25

    var spaceButtonAndDisplay = 5

    var onResult: (Int) -> Unit = {}
    var gap: Int = 1



}