package com.codehong.lib.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.text.HongTextBuilder

@Composable
fun SampleHeader(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(id = HongColor.MAIN_ORANGE_100.colorResId)),
        contentAlignment = Alignment.Center
    ) {
        HongTextCompose(
            option = HongTextBuilder()
                .text(title)
                .color(HongColor.WHITE_100.hex)
                .typography(HongTypo.BODY_18_B)
                .applyOption()
        )
    }
}

@Composable
fun SampleComposeDespContainer(
    desp: String,
    testCompose: @Composable () -> Unit
) {
    HongTextCompose(
        option = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .text(desp)
            .padding(
                HongSpacingInfo(
                    left = 20f,
                    right = 20f,
                )
            )
            .color(HongColor.BLACK_100.hex)
            .typography(HongTypo.BODY_14_B)
            .applyOption()
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = HongColor.WHITE_100.colorResId))
            .padding(top = 10.dp, bottom = 25.dp)
    ) {
        testCompose()
    }
}
