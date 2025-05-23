package com.codehong.library.widget.rule.typo

import androidx.compose.ui.text.font.FontWeight
import com.codehong.library.widget.R

enum class HongFont(
    val font: Int,
    val weight: FontWeight,
    val fileName: String
) {
    PRETENDARD_400(R.font.pretendard_400, FontWeight.W400, "pretendard_400"),
    PRETENDARD_500(R.font.pretendard_500, FontWeight.W500, "pretendard_500"),
    PRETENDARD_600(R.font.pretendard_600, FontWeight.W600, "pretendard_600"),
    PRETENDARD_700(R.font.pretendard_700, FontWeight.W700, "pretendard_700"),
    PRETENDARD_800(R.font.pretendard_800, FontWeight.W800, "pretendard_800")
}
