package com.codehong.lib.sample.button.icon

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.R
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.button.icon.HongButtonIconCompose
import com.codehong.library.widget.button.icon.HongIconButtonBuilder
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.button.HongButtonIconType

class SampleButtonIconActivity : BaseSampleMixActivity() {

    override fun optionViewList(): List<View> {
        return emptyList()
    }


    @Composable
    override fun InitCompose() {
        HongButtonIconCompose(
            option = HongIconButtonBuilder()
                .margin(
                    HongSpacingInfo(
                        left = 20f
                    )
                )
                .buttonType(HongButtonIconType.SIZE_56)
                .iconResId(R.drawable.ic_close)
                .state(HongClickState.ENABLE)
                .onClick {
                    Log.d("TAG", "test here click 11")
                }
                .applyOption(),
        )

        HongButtonIconCompose(
            option = HongIconButtonBuilder()
                .buttonType(HongButtonIconType.SIZE_32)
                .iconResId(R.drawable.ic_close)
                .state(HongClickState.ENABLE)
                .onClick {
                    Log.d("TAG", "test here click 22")
                }
                .applyOption(),
        )

        HongButtonIconCompose(
            option = HongIconButtonBuilder()
                .buttonType(HongButtonIconType.SIZE_32)
                .iconResId(R.drawable.ic_close)
                .state(HongClickState.DISABLE)
                .onClick {
                    Log.d("TAG", "test here click 33")
                }
                .applyOption(),
        )
    }
}