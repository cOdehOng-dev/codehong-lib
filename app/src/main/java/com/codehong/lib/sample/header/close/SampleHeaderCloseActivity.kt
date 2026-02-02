package com.codehong.lib.sample.header.close

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.header.close.HongHeaderCloseBuilder
import com.codehong.library.widget.header.close.HongHeaderCloseCompose
import com.codehong.library.widget.header.close.HongHeaderCloseView
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class SampleHeaderCloseActivity : BaseSampleMixActivity() {

    private val option1 = HongHeaderCloseBuilder()
        .title("헤더 제목")
        .titleTypo(HongTypo.BODY_16)
        .titleColor(HongColor.BLACK_100.hex)
        .close {}
        .applyOption()

    private val option2 = HongHeaderCloseBuilder()
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .titleColor(HongColor.BLACK_100.hex)
        .titleTypo(HongTypo.BODY_16_B)
        .title("헤더 제목")
        .close {}
        .applyOption()

    private val optionList = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                add(HongHeaderCloseView(this@SampleHeaderCloseActivity).set(option))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongHeaderCloseCompose(it)
        }
    }
}
