package com.codehong.library.widget.typo

import androidx.compose.ui.text.font.FontWeight

enum class TypoType(val styleName: String, val code: Int) {
    TITLE_36_B("title_36_b", 10),
    TITLE_28_B("title_28_b", 11),
    TITLE_26_B("title_26_b", 12),
    TITLE_24_B("title_24_b", 13),
    TITLE_22_B("title_22_b", 14),

    TITLE_36("title_36", 20),
    TITLE_28("title_28", 21),
    TITLE_26("title_26", 22),
    TITLE_24("title_24", 23),
    TITLE_22("title_22", 24),

    BODY_20_B("body_20_b", 30),
    BODY_18_B("body_18_b", 31),
    BODY_16_B("body_16_b", 32),
    BODY_14_B("body_14_b", 33),

    BODY_20("body_20", 40),
    BODY_18("body_18", 41),
    BODY_16("body_16", 42),
    BODY_14("body_14", 43),

    CONTENTS_22_B("contents_22_b", 50),
    CONTENTS_20_B("contents_20_b", 51),
    CONTENTS_16_B("contents_16_b", 52),

    CONTENTS_20("contents_20", 60),
    CONTENTS_16("contents_16", 61),
    CONTENTS_14("contents_14", 62)
}

fun TypoType.size(): Int = when (this) {
    TypoType.TITLE_36_B, TypoType.TITLE_36 -> 36
    TypoType.TITLE_28_B, TypoType.TITLE_28 -> 28
    TypoType.TITLE_26_B, TypoType.TITLE_26 -> 26
    TypoType.TITLE_24_B, TypoType.TITLE_24 -> 24
    TypoType.TITLE_22_B, TypoType.TITLE_22 -> 22

    TypoType.BODY_20_B, TypoType.BODY_20 -> 20
    TypoType.BODY_18_B, TypoType.BODY_18 -> 18
    TypoType.BODY_16_B, TypoType.BODY_16 -> 16
    TypoType.BODY_14_B, TypoType.BODY_14 -> 14

    TypoType.CONTENTS_22_B -> 22
    TypoType.CONTENTS_20_B, TypoType.CONTENTS_20 -> 20
    TypoType.CONTENTS_16_B, TypoType.CONTENTS_16 -> 16
    TypoType.CONTENTS_14 -> 14
}

fun TypoType.lineHeight(): Int = when (this) {
    TypoType.TITLE_36_B, TypoType.TITLE_36 -> 44
    TypoType.TITLE_28_B, TypoType.TITLE_28 -> 36
    TypoType.TITLE_26_B, TypoType.TITLE_26 -> 34
    TypoType.TITLE_24_B, TypoType.TITLE_24 -> 32
    TypoType.TITLE_22_B, TypoType.TITLE_22 -> 28

    TypoType.BODY_20_B, TypoType.BODY_20 -> 28
    TypoType.BODY_18_B, TypoType.BODY_18 -> 24
    TypoType.BODY_16_B, TypoType.BODY_16 -> 22
    TypoType.BODY_14_B, TypoType.BODY_14 -> 19

    TypoType.CONTENTS_22_B -> 32
    TypoType.CONTENTS_20_B -> 26
    TypoType.CONTENTS_16_B -> 24
    TypoType.CONTENTS_20 -> 30
    TypoType.CONTENTS_16 -> 26
    TypoType.CONTENTS_14 -> 24
}
fun TypoType.isBold() = this.styleName.endsWith("b", ignoreCase = true)


fun TypoType.fontWeight(): FontWeight = if (this.isBold()) {
    FontWeight.W700
} else {
    FontWeight.W400
}
