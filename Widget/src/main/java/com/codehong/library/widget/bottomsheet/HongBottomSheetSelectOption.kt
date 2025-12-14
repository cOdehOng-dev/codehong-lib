package com.codehong.library.widget.bottomsheet

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


    var title: String = ""
    var titleTypo: HongTypo = HongTypo.BODY_20_B
    var titleColorHex = HongColor.BLACK_100.hex


    // selection
    var selectionBackgroundColorHex: String = HongColor.GRAY_05.hex

    var selectionRadius = HongRadiusInfo(
        topLeft = 8,
        topRight = 8,
        bottomLeft = 8,
        bottomRight = 8
    )

    var selectionSelectorBorder = HongBorderInfo(
        width = 2,
        color = HongColor.MAIN_ORANGE_100.hex
    )


    var selectionTitleTypo: HongTypo = HongTypo.BODY_16
    var selectionTitleColorHex: String = HongColor.BLACK_100.hex

    var selectionSubtitleTypo: HongTypo = HongTypo.CONTENTS_12
    var selectionSubtitleColorHex: String = HongColor.BLACK_70.hex


    var selectionList: List<Pair<String, String>> = emptyList()

    var initialSelection: Pair<String,String> = Pair("", "")

    var selectSelectionCallback: ((Pair<String, String>) -> Unit) = {}
    var onChangeVisibleState: ((Boolean) -> Unit) = {}


}
