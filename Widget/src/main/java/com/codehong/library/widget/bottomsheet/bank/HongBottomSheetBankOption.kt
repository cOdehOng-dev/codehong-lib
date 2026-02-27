package com.codehong.library.widget.bottomsheet.bank

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.util.KoreanBank

data class HongBottomSheetBankOption(
    override val type: HongWidgetType = HongWidgetType.BOTTOM_SHEET_BANK
) : HongWidgetCommonOption {

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


    var topRadius: Int = 24
    var dimColorHex: String = HongColor.GRAY_30.hex

    var dragHandleColorHex: String = HongColor.GRAY_50.hex

    var titleTypo: HongTypo = HongTypo.BODY_20_B
    var titleColorHex = HongColor.BLACK_100.hex

    var onBankSelected: ((KoreanBank) -> Unit) = {}

    // 뷰가 완전히 사라졌을 때 콜백
    var onDismissed: (() -> Unit) = {}
}
