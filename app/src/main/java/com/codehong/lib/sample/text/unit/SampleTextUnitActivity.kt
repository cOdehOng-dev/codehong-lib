package com.codehong.lib.sample.text.unit

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.unit.HongTextUnitBuilder
import com.codehong.library.widget.text.unit.HongTextUnitCompose
import com.codehong.library.widget.text.unit.HongTextUnitView

class SampleTextUnitActivity : BaseSampleMixActivity() {
    private val option = HongTextUnitBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 10f,
                bottom = 10f
            )
        )
        .text("1000")
        .unitText("장")
        .useNumberDecimal(true)
        .applyOption()
    private val option2 = HongTextUnitBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 10f,
                bottom = 10f
            )
        )
        .text("세")
        .unitText("장")
        .useNumberDecimal(true)
        .applyOption()


    private val option3 = HongTextUnitBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 10f,
                bottom = 10f
            )
        )
        .text("123")
        .unitText("장")
        .useNumberDecimal(true)
        .applyOption()

    private val optionList get() = listOf(
        option,
        option2,
        option3
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongTextUnitView(this@SampleTextUnitActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextUnitCompose(it)
        }
    }
}