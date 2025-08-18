package com.codehong.library.widget.header

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
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose

@Composable
fun HongCloseHeaderCompose(
    option: HongCloseHeaderOption
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .hongWidth(option.width)
            .hongHeight(option.height)
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
                    .copy(option.headerTitleTextOption)
                    .maxLines(1)
                    .overflow(HongTextOverflow.ELLIPSIS)
                    .applyOption()
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .disableRippleClickable { option.close?.invoke() },
            contentAlignment = Alignment.Center
        ) {
            HongImageCompose(
                option = HongImageBuilder()
                    .width(20)
                    .height(20)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .drawableResId(R.drawable.honglib_ic_close)
                    .useShapeCircle(true)
                    .applyOption()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongCloseHeaderCompose() {
    val option = HongCloseHeaderBuilder()
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
    HongCloseHeaderCompose(option)
}
