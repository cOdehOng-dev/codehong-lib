package com.codehong.library.widget.icon

import androidx.compose.runtime.Composable
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.image.def.HongImageCompose
import com.codehong.library.widget.util.HongWidgetNoneClickContainer

@Composable
fun HongIconCompose(
    option: HongIconOption
) {
    HongWidgetNoneClickContainer(
        HongIconBuilder()
            .padding(option.padding)
            .margin(option.margin)
            .applyOption()
    ) {
        HongImageCompose(
            HongImageBuilder()
                .width(option.iconType.size)
                .height(option.iconType.size)
                .imageInfo(option.iconResId)
                .imageColor(option.iconColorHex)
                .scaleType(option.iconScaleType)
                .applyOption()
        )
    }
}