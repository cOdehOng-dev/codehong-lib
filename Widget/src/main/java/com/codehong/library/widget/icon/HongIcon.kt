package com.codehong.library.widget.icon

import androidx.compose.runtime.Composable
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongIconType
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.color.HongColor

// TODO 컴포넌트화 하기
@Composable
fun HongIcon(
    type: HongIconType,
    resId: Int,
    iconColor: HongColor
) {
    HongImageCompose(
        HongImageBuilder()
            .width(type.size)
            .height(type.size)
            .drawableResId(resId)
            .imageColor(iconColor)
            .scaleType(HongScaleType.CENTER_INSIDE)
            .applyOption()
    )
}