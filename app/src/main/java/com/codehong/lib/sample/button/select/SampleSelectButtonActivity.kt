package com.codehong.lib.sample.button.select

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.button.select.HongButtonSelectBuilder
import com.codehong.library.widget.button.select.HongButtonSelectCompose
import com.codehong.library.widget.button.select.HongButtonSelectView
import com.codehong.library.widget.rule.HongSpacingInfo

class SampleSelectButtonActivity : BaseSampleMixActivity() {

    private val option1 = HongButtonSelectBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
            )
        )
        .onNegativeClick {
            Log.d("TAG", "취소 버튼!!")
        }
        .onPositiveClick {
            Log.d("TAG", "확인 버튼!!")
        }
        .applyOption()

    private val optionList get() = listOf(
        option1
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongButtonSelectView(this@SampleSelectButtonActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongButtonSelectCompose(it)
        }
    }
}