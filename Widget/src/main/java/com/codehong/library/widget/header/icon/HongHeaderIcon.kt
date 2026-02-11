package com.codehong.library.widget.header.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.image.def.HongImageCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose

@Composable
fun HongHeaderIcon(
    option: HongHeaderIconOption
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .hongWidth(HongLayoutParam.MATCH_PARENT.value)
            .hongHeight(option.height)
            .hongBackground(option.backgroundColorHex),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 8.dp)
                .disableRippleClickable { option.onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            option.backIconRes?.let { iconRes ->
                HongImageCompose(
                    option = HongImageBuilder()
                        .width(34)
                        .height(34)
                        .scaleType(HongScaleType.CENTER_CROP)
                        .imageInfo(iconRes)
                        .imageColor(option.backIconColorHex)
                        .applyOption()
                )
            }
        }

//        Spacer(modifier = Modifier.size(56.dp))

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            HongTextCompose(
                option = HongTextBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .height(HongLayoutParam.WRAP_CONTENT.value)
                    .textAlign(HongTextAlign.CENTER)
                    .maxLines(1)
                    .overflow(HongTextOverflow.ELLIPSIS)
                    .typography(option.titleTypo)
                    .color(option.titleColorHex)
                    .text(option.title)
                    .applyOption()
            )
        }

        Spacer(modifier = Modifier.size(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongHeaderIcon() {
    val option = HongHeaderIconBuilder()
        .title("헤더 제목")
        .titleTypo(HongTypo.BODY_18)
        .titleColor(HongColor.BLACK_100.hex)
        .backIcon(R.drawable.honglib_ic_arrow_left)
        .onBack {}
        .applyOption()
    HongHeaderIcon(option)
}
