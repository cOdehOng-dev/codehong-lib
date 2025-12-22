package com.codehong.lib.sample.draganddrop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.R
import com.codehong.lib.sample.draganddrop.model.BreadItem
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import java.text.DecimalFormat

@Composable
fun BreadItemContent(
    breadItem: BreadItem
) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White
            )
            .border(
                width = 1.dp,
                color = colorResource(R.color.color_0A000000)
            )
    ) {
        Column {
            HongImageCompose(
                option = HongImageBuilder()
                    .width(120)
                    .height(120)
                    .backgroundColor("0A000000")
                    .scaleType(HongScaleType.CENTER_INSIDE)
                    .imageInfo(breadItem.image)
                    .applyOption()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            ) {
                HongTextCompose(
                    option = HongTextBuilder()
                        .width(HongLayoutParam.WRAP_CONTENT.value)
                        .text(breadItem.storeName)
                        .typography(HongTypo.CONTENTS_12_B)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )

                Spacer(modifier = Modifier.height(2.dp))

                HongTextCompose(
                    option = HongTextBuilder()
                        .width(HongLayoutParam.WRAP_CONTENT.value)
                        .text(breadItem.prdName)
                        .typography(HongTypo.BODY_13)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row {
                    if (breadItem.discountPer.isNotEmpty()) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .width(HongLayoutParam.WRAP_CONTENT.value)
                                .text("${breadItem.discountPer}%")
                                .typography(HongTypo.BODY_15_B)
                                .color(HongColor.RED_100)
                                .applyOption()
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                    HongTextCompose(
                        option = HongTextBuilder()
                            .width(HongLayoutParam.WRAP_CONTENT.value)
                            .text("${DecimalFormat("#,###").format(breadItem.price.toLong())}원")
                            .typography(HongTypo.BODY_15_B)
                            .color(HongColor.BLACK_100)
                            .applyOption()
                    )

                }
            }
        }
    }
}
