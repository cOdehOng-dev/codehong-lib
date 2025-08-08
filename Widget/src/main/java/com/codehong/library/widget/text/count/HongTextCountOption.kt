package com.codehong.library.widget.text.count

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongCountType
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTextCountOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_COUNT
) : HongWidgetCommonOption {
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



    var countType: HongCountType = HongCountType.LONG

    var startCount: Number = 1
    var countTypo = HongTypo.TITLE_36_B
    var countColorHex = HongColor.BLACK_100.hex

    var unitText: String = ""
    var unitTypo = HongTypo.CONTENTS_12
    var unitColorHex = HongColor.BLACK_50.hex

    var minCount: Number = 0
    var maxCount: Number? = null

    var amount: Number = 1

    var buttonType = HongButtonIconType.SIZE_40

    var onCountChange: (String) -> Unit = {}

}