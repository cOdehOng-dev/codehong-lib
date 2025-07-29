package com.codehong.library.widget.label.toggleswitch

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchOption

data class HongLabelSwitchOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_SWITCH,
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_LABEL_OPTION = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(HongTypo.BODY_15_B)
            .color(HongColor.BLACK_100)
            .applyOption()

        val DEFAULT_DESCRIPTION_OPTION = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(HongTypo.CONTENTS_10)
            .margin(
                HongSpacingInfo(
                    top = 2f
                )
            )
            .color("#333333")
            .applyOption()
        val DEFAULT_LABEL_VIEW_OPTION = HongLabelBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .backgroundColor(HongColor.TRANSPARENT)
            .labelTextOption(DEFAULT_LABEL_OPTION)
            .descriptionTextOption(DEFAULT_DESCRIPTION_OPTION)
            .applyOption()
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
    
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()

    var label: String? = null
    var labelColorHex: String = HongColor.BLACK_100.hex
    var labelTypo: HongTypo = HongTypo.BODY_15_B

    var description: String? = null
    var descriptionColorHex: String = HongColor.BLACK_60.hex
    var descriptionTypo: HongTypo = HongTypo.CONTENTS_10




    var switchOption: HongSwitchOption = DEFAULT_SWITCH_OPTION



}