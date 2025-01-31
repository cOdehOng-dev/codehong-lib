package com.codehong.library.widget

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

enum class HongFontWeight {
    W_700, W_600, W_500, W_400
}

val pretendardFontFamily = FontFamily(
    Font(R.font.pretendard_400, FontWeight.Light),
    Font(R.font.pretendard_500, FontWeight.Normal),
    Font(R.font.pretendard_600, FontWeight.Bold),
    Font(R.font.pretendard_700, FontWeight.ExtraBold)
)
