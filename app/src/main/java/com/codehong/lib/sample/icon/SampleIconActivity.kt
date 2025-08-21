package com.codehong.lib.sample.icon

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.icon.HongIconBuilder
import com.codehong.library.widget.icon.HongIconCompose
import com.codehong.library.widget.icon.HongIconVIew
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.icon.HongIconType

class SampleIconActivity : BaseSampleMixActivity() {

    private val option = HongIconBuilder()
        .iconResId(R.drawable.honglib_ic_20_arrow_down)
        .iconType(HongIconType.H20)
        .iconColor(HongColor.GRAY_30)
        .applyOption()

    private val option2 = HongIconBuilder()
        .iconResId(R.drawable.honglib_ic_alarm)
        .iconType(HongIconType.H28)
        .iconColor(HongColor.MAIN_ORANGE_100)
        .applyOption()

    private val optionList = listOf(
        option,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { opt ->
                add(HongIconVIew(this@SampleIconActivity).set(opt))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { opt ->
            HongIconCompose(opt)
        }
    }
}