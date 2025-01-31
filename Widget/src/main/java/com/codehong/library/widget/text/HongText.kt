package com.codehong.library.widget.text

import androidx.annotation.ColorRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.codehong.library.widget.HongFontWeight
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.util.dpToSp

@Composable
fun HongText(
    modifier: Modifier = Modifier,
    text: String?,
    size: Int? = null,
    fontFamily: FontFamily? = pretendardFontFamily,
    fontWeight: HongFontWeight = HongFontWeight.W_500,
    @ColorRes color: Int? = null,
    lineHeight: Float? = null,
    textAlign: TextAlign? = null,
    isEmptyOrNullHideView: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip
) {
    if (isEmptyOrNullHideView && text.isNullOrEmpty()) {
        return
    }
    Text(
        modifier = modifier,
        text = text ?: "",
        fontFamily = fontFamily,
        fontWeight = when (fontWeight) {
            HongFontWeight.W_700 -> FontWeight.ExtraBold
            HongFontWeight.W_600 -> FontWeight.Bold
            HongFontWeight.W_500 -> FontWeight.Normal
            HongFontWeight.W_400 -> FontWeight.Light
        },
        fontSize = if (size != null) dpToSp(dp = size) else TextUnit.Unspecified,
        color = if (color != null) colorResource(id = color) else Color.Unspecified,
        lineHeight = if (lineHeight != null) dpToSp(dp = lineHeight) else TextUnit.Unspecified,
        textAlign = textAlign,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow
    )
}
