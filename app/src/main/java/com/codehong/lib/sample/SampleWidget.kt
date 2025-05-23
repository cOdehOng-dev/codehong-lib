package com.codehong.lib.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongText

@Composable
fun SampleScaffold(
    title: String,
    horizontalPadding: Int = 0,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier
            .background(colorResource(id = HongColor.WHITE_100.colorResId)),
        topBar = {
            SampleHeader(title = title)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = horizontalPadding.dp, end = horizontalPadding.dp)
                .background(colorResource(HongColor.WHITE_100.colorResId))
                .verticalScroll(scrollState)
        ) {
            MarginTopOrBottom(value = 30)
            content()
        }
    }
}

@Composable
fun SampleHeader(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(id = HongColor.MAIN_PURPLE.colorResId)),
        contentAlignment = Alignment.Center
    ) {
        HongText(
            text = title,
            typo = HongTypo.BODY_18_B,
            colorType = HongColor.WHITE_100
        )
    }
}

@Composable
fun SampleMenu(
    title: String,
    horizontalPadding: Int = 0,
    testCompose: @Composable () -> Unit
) {
    HongText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(colorResource(id = HongColor.WHITE_100.colorResId)),
        text = title,
        typo = HongTypo.BODY_18_B,
        colorType = HongColor.BLACK_100
    )
    MarginTopOrBottom(10)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding.dp)
    ) {
        testCompose()
    }
    MarginTopOrBottom(25)
}
