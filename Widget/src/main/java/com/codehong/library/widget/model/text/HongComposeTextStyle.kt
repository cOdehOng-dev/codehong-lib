package com.codehong.library.widget.model.text

import androidx.compose.ui.text.font.FontWeight
import com.codehong.library.widget.R
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size

data class HongComposeTextStyle(
    val fontWeight: FontWeight = FontWeight.W400,
    val size: Int = 14,
    val color: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_29292d
    ),
    val typo: HongTypo? = null
) {
    fun convertFontWeight(): FontWeight {
        if (typo == null) {
            return fontWeight
        }

        return typo.fontWeight()
    }

    fun convertTextSize(): Int {
        if (typo == null) {
            return size
        }

        return typo.size()
    }
}
