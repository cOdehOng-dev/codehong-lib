package com.codehong.library.widget.rule.typo

import androidx.compose.ui.text.font.FontWeight

enum class HongTypo(
    val styleName: String,
    val code: Int
) {
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
    BODY_17_B("body_17_b", 32),
    BODY_16_B("body_16_b", 33),
    BODY_15_B("body_15_b", 34),
    BODY_14_B("body_14_b", 35),

    BODY_20("body_20", 40),
    BODY_18("body_18", 41),
    BODY_17("body_17", 42),
    BODY_16("body_16", 43),
    BODY_15("body_15", 44),
    BODY_14("body_14", 45),

    CONTENTS_22_B("contents_22_b", 50),
    CONTENTS_20_B("contents_20_b", 51),
    CONTENTS_16_B("contents_16_b", 52),
    CONTENTS_12_B("contents_12_b", 53),
    CONTENTS_10_B("contents_10_b", 54),

    CONTENTS_20("contents_20", 60),
    CONTENTS_16("contents_16", 61),
    CONTENTS_14("contents_14", 62),
    CONTENTS_12("contents_12", 63),
    CONTENTS_10("contents_10", 64),
}

fun HongTypo.size(): Int = when (this) {
    HongTypo.TITLE_36_B, HongTypo.TITLE_36 -> 36
    HongTypo.TITLE_28_B, HongTypo.TITLE_28 -> 28
    HongTypo.TITLE_26_B, HongTypo.TITLE_26 -> 26
    HongTypo.TITLE_24_B, HongTypo.TITLE_24 -> 24
    HongTypo.TITLE_22_B, HongTypo.TITLE_22 -> 22

    HongTypo.BODY_20_B, HongTypo.BODY_20 -> 20
    HongTypo.BODY_18_B, HongTypo.BODY_18 -> 18
    HongTypo.BODY_17_B, HongTypo.BODY_17 -> 17
    HongTypo.BODY_16_B, HongTypo.BODY_16 -> 16
    HongTypo.BODY_15_B, HongTypo.BODY_15 -> 15
    HongTypo.BODY_14_B, HongTypo.BODY_14 -> 14

    HongTypo.CONTENTS_22_B -> 22
    HongTypo.CONTENTS_20_B, HongTypo.CONTENTS_20 -> 20
    HongTypo.CONTENTS_16_B, HongTypo.CONTENTS_16 -> 16
    HongTypo.CONTENTS_14 -> 14
    HongTypo.CONTENTS_12_B, HongTypo.CONTENTS_12 -> 12
    HongTypo.CONTENTS_10_B, HongTypo.CONTENTS_10 -> 10
}

fun HongTypo.lineHeight(): Int = when (this) {
    HongTypo.TITLE_36_B, HongTypo.TITLE_36 -> 44
    HongTypo.TITLE_28_B, HongTypo.TITLE_28 -> 36
    HongTypo.TITLE_26_B, HongTypo.TITLE_26 -> 34
    HongTypo.TITLE_24_B, HongTypo.TITLE_24 -> 32
    HongTypo.TITLE_22_B, HongTypo.TITLE_22 -> 28

    HongTypo.BODY_20_B, HongTypo.BODY_20 -> 28
    HongTypo.BODY_18_B, HongTypo.BODY_18 -> 24
    HongTypo.BODY_17_B, HongTypo.BODY_17 -> 23
    HongTypo.BODY_16_B, HongTypo.BODY_16 -> 22
    HongTypo.BODY_15_B, HongTypo.BODY_15 -> 20
    HongTypo.BODY_14_B, HongTypo.BODY_14 -> 19

    HongTypo.CONTENTS_22_B -> 32
    HongTypo.CONTENTS_20_B -> 26
    HongTypo.CONTENTS_16_B -> 24
    HongTypo.CONTENTS_12_B, HongTypo.CONTENTS_12 -> 20
    HongTypo.CONTENTS_20 -> 30
    HongTypo.CONTENTS_16 -> 26
    HongTypo.CONTENTS_14 -> 24
    HongTypo.CONTENTS_10_B, HongTypo.CONTENTS_10 -> 18
}
fun HongTypo.isBold() = this.styleName.endsWith("b", ignoreCase = true)

fun HongTypo.fontWeight(): FontWeight = if (this.isBold()) {
    FontWeight.W700
} else {
    FontWeight.W400
}

fun HongTypo?.fontType(): HongFont {
    return if (this?.isBold() == true) {
        HongFont.PRETENDARD_700
    } else {
        HongFont.PRETENDARD_400
    }
}

