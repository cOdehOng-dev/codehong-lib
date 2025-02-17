package com.codehong.lib.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.text.HongTypoText
import com.codehong.library.widget.typo.TypoType

@Composable
fun TestMenuView(
    title: String,
    testCompose: @Composable () -> Unit
) {
    HongTypoText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        text = title,
        typo = TypoType.BODY_18_B,
        colorType = ColorType.BLACK_100
    )
    MarginTopOrBottom(10)
    testCompose()
    MarginTopOrBottom(25)
}
