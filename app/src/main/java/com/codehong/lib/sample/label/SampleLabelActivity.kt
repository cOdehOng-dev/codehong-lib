package com.codehong.lib.sample.label

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.label.HongLabelView
import com.codehong.library.widget.label.HongLabelViewCompose
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class SampleLabelActivity : BaseSampleMixActivity() {

    private val option1 = HongLabelBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .labelTextOption(
            HongTextBuilder()
                .copy(HongLabelOption.DEFAULT_LABEL_OPTION)
                .text("text align")
                .applyOption()
        )
        .descriptionTextOption(
            HongTextBuilder()
                .copy(HongLabelOption.DEFAULT_DESCRIPTION_OPTION)
                .text("width가 MATCH_PARENT인 경우, textAlign이 적용되지 않습니다.")
                .applyOption()
        )
        .applyOption()

    private val option2 = HongLabelBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .labelTextOption(
            HongTextBuilder()
                .copy(HongLabelOption.DEFAULT_LABEL_OPTION)
                .text("text align")
                .typography(HongTypo.BODY_17_B)
                .color(HongColor.MAIN_ORANGE_100)
                .applyOption()
        )
        .descriptionTextOption(
            HongTextBuilder()
                .copy(HongLabelOption.DEFAULT_DESCRIPTION_OPTION)
                .typography(HongTypo.CONTENTS_12)
                .color(HongColor.BLACK_100)
                .applyOption()
        )
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                add(HongLabelView(this@SampleLabelActivity).set(option))
            }
        }
    }


    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongLabelViewCompose(it)
        }
    }
}