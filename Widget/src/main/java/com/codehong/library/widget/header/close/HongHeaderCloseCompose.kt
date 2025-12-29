package com.codehong.library.widget.header.close

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.label.HongTextBuilder
import com.codehong.library.widget.text.label.HongTextCompose

@Composable
fun HongHeaderCloseCompose(
    option: HongHeaderCloseOption
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .hongWidth(HongLayoutParam.MATCH_PARENT.value)
            .hongHeight(52)
            .hongBackground(option.backgroundColorHex),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.size(40.dp))

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

        Box(
            modifier = Modifier
                .size(40.dp)
                .disableRippleClickable { option.onCloseClick() },
            contentAlignment = Alignment.Center
        ) {
            HongImageCompose(
                option = HongImageBuilder()
                    .width(20)
                    .height(20)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .imageInfo(R.drawable.honglib_ic_close)
                    .imageColor(option.closeIconColorHex)
                    .useShapeCircle(true)
                    .applyOption()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongCloseHeaderCompose() {
    val option = HongHeaderCloseBuilder()
        .title("헤더 제목")
        .titleTypo(HongTypo.BODY_16_B)
        .titleColor(HongColor.BLACK_100.hex)
        .close {}
        .applyOption()
    HongHeaderCloseCompose(option)
}
