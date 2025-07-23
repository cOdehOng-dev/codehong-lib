package com.codehong.lib.sample.text.updown

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.updown.HongTextUpDownBuilder
import com.codehong.library.widget.text.updown.HongTextUpDownCompose
import com.codehong.library.widget.text.updown.HongTextUpDownView

class SampleTextUpDownActivity : BaseSampleMixActivity() {

    private val option1 = HongTextUpDownBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 10f
            )
        )
        .amount(7)
        .unit("장")
        .spaceButtonAndDisplay(8)
        .gap(1)
        .applyOption()

    private val optionList get() = listOf(
        option1
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { 
                add(HongTextUpDownView(this@SampleTextUpDownActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextUpDownCompose(it)
        }
    }
}