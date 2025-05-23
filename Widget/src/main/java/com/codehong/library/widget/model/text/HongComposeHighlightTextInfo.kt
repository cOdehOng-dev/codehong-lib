package com.codehong.library.widget.model.text

import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.rule.typo.HongTypo

data class HongComposeHighlightTextInfo(
    val highlightText: String,
    val style: HongComposeTextStyle = HongComposeTextStyle(
        typo = HongTypo.BODY_16_B,
        color = HongComposeColor(
            type = HongColor.MAIN_PURPLE
        )
    )
)
