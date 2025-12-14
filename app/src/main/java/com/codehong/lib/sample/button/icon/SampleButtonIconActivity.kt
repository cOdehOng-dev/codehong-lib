package com.codehong.lib.sample.button.icon

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.R
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.button.icon.HongButtonIconBuilder
import com.codehong.library.widget.button.icon.HongButtonIconCompose
import com.codehong.library.widget.button.icon.HongButtonIconView
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor

class SampleButtonIconActivity : BaseSampleMixActivity() {

    private val option = HongButtonIconBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .buttonType(HongButtonIconType.SIZE_56)
        .iconResId(R.drawable.ic_close)
        .state(HongClickState.ENABLE)
        .onClick {
            Log.d("TAG", "click 11")
        }
        .applyOption()

    private val option2 = HongButtonIconBuilder()
        .buttonType(HongButtonIconType.SIZE_32)
        .backgroundColor(HongColor.BLACK_100)
        .iconResId(R.drawable.ic_close)
        .state(HongClickState.ENABLE)
        .onClick {
            Log.d("TAG", "click 22")
        }
        .applyOption()

    private val option3 = HongButtonIconBuilder()
        .buttonType(HongButtonIconType.SIZE_32)
        .iconResId(R.drawable.ic_close)
        .state(HongClickState.DISABLE)
        .onClick {
            Log.d("TAG", "click 33")
        }
        .applyOption()

    private val optionList = listOf(
        option,
        option2,
        option3
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            addAll(optionList.map { HongButtonIconView(this@SampleButtonIconActivity).set(it) })
        }
    }


    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongButtonIconCompose(
                option = it,
            )
        }
    }
}