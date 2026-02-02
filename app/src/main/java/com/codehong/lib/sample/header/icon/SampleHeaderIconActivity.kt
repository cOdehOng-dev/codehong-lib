package com.codehong.lib.sample.header.icon

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.header.icon.HongHeaderIcon
import com.codehong.library.widget.header.icon.HongHeaderIconBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class SampleHeaderIconActivity : BaseSampleComposeActivity() {
    private val option1 = HongHeaderIconBuilder()
        .title("헤더 제목")
        .titleTypo(HongTypo.BODY_16_B)
        .titleColor(HongColor.BLACK_100.hex)
        .backIcon(R.drawable.honglib_ic_arrow_left)
        .onBack { finish() }
        .applyOption()

    private val option2 = HongHeaderIconBuilder()
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .titleColor(HongColor.WHITE_100)
        .titleTypo(HongTypo.BODY_16_B)
        .title("헤더 제목")
        .backIcon(R.drawable.honglib_ic_arrow_left)
        .backIconColor(HongColor.WHITE_100)
        .onBack { finish() }
        .applyOption()
    private val optionList = listOf(
        option1,
        option2
    )

    @Composable
    override fun InitCompose() {
        Column {
            optionList.forEach {
                HongHeaderIcon(it)
            }
        }
    }

}