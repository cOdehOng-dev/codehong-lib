package com.codehong.lib.sample.closeheader

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.header.HongCloseHeaderBuilder
import com.codehong.library.widget.header.HongCloseHeaderCompose
import com.codehong.library.widget.header.HongCloseHeaderView
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class SampleCloseHeaderActivity : BaseSampleMixActivity() {

    private val option1 = HongCloseHeaderBuilder()
        .headerTitleTextOption(
            HongTextBuilder()
                .text("헤더 제목")
                .typography(HongTypo.BODY_16)
                .color(HongColor.BLACK_100.hex)
                .applyOption()
        )
        .close {

        }
        .applyOption()

    private val option2 = HongCloseHeaderBuilder()
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .headerTitleTextOption(
            HongTextBuilder()
                .text("헤더 제목")
                .typography(HongTypo.BODY_16_B)
                .color(HongColor.BLACK_100.hex)
                .applyOption()
        )
        .close {

        }
        .applyOption()

    override fun optionViewList(): List<View> {
        return listOf(
            HongCloseHeaderView(this).set(option1),
            HongCloseHeaderView(this).set(option2)
        )
    }

    @Composable
    override fun InitCompose() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HongColor.WHITE_100.hex.toColor())
        ) {
            HongCloseHeaderCompose(option1)

            HongCloseHeaderCompose(
                option = HongCloseHeaderBuilder()
                    .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
                    .headerTitleTextOption(
                        HongTextBuilder()
                            .text("헤더 제목")
                            .typography(HongTypo.BODY_16_B)
                            .color(HongColor.BLACK_100.hex)
                            .applyOption()
                    )
                    .close {

                    }
                    .applyOption()
            )
        }
    }
}
