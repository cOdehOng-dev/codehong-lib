package com.codehong.library.widget.layout.fade

import androidx.compose.foundation.lazy.LazyListScope
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

data class HongScrollFadeLayoutOption(
    override val type: HongWidgetType = HongWidgetType.SCROLL_FADE_ANIM_LAYOUT
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_MAIN_CONTENT_HEIGHT = 446
        val DEFAULT_TITLE_TYPO = HongTypo.TITLE_26_B

        val DEFAULT_COLOR = Pair(HongColor.WHITE_100.hex, HongColor.BLACK_100.hex)
        val DEFAULT_HEADER_BACKGROUND_COLOR = HongColor.WHITE_100.hex
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()


    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    var mainContentHeightDp: Int = DEFAULT_MAIN_CONTENT_HEIGHT

    var mainContent: @Composable () -> Unit = {}
    var subContentList: (LazyListScope) -> Unit = {}
    var bottomContent: @Composable () -> Unit = {}

    var leftIconInfo: Any? = null
    var leftIconColorHex: Pair<String, String> = DEFAULT_COLOR
    var leftIconClick: () -> Unit = {}

    var rightIconInfo: Any? = null
    var rightIconColorHex: Pair<String, String> = DEFAULT_COLOR
    var rightIconClick: () -> Unit = {}


    var headerBackgroundColorHex: String = DEFAULT_HEADER_BACKGROUND_COLOR

    var titleText: String = ""
    var titleTypo: HongTypo = DEFAULT_TITLE_TYPO
    var useTitleOverFlow: Boolean = false
    var titleColorHex: Pair<String, String> = DEFAULT_COLOR









}
