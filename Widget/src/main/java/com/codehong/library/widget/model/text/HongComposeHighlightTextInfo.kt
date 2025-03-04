package com.codehong.library.widget.model.text

import com.codehong.library.widget.ColorType
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.typo.TypoType

data class HongComposeHighlightTextInfo(
    val highlightText: String,
    val style: HongComposeTextStyle = HongComposeTextStyle(
        typo = TypoType.BODY_16_B,
        color = HongComposeColor(
            type = ColorType.PRIMARY_MINT
        )
    )
)
