package com.codehong.lib.sample.dynamicisland

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.dynamicisland.DynamicIslandInfo
import com.codehong.library.widget.dynamicisland.HongDynamicIslandManager
import com.codehong.library.widget.dynamicisland.HongDynamicIslandType
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.dpToSp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun SampleDynamicsIslandScreen() {
    val context = LocalContext.current
    var isOnPush by remember { mutableStateOf(HongDynamicIslandManager.isGranted(context)) }

    val requestOverlayPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        if (HongDynamicIslandManager.isGranted(context)) {
            startDynamicIsland(context)
            isOnPush = true
        } else {
            isOnPush = false
            HongToastUtil.showToast(context, "다이나믹 아일랜드 권한을 거부하였습니다")
        }
    }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 19.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "다이나믹 아일랜드",
                style = TextStyle(
                    color = Color(0XFF000000),
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = dpToSp(dp = 15),
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )

            Switch(
                checked = isOnPush,
                onCheckedChange = { isChecked ->
                    isOnPush = isChecked
                    HongDynamicIslandManager.setPermission(
                        context,
                        isChecked = isChecked,
                        launcher = requestOverlayPermissionLauncher
                    ) {
                        startDynamicIsland(context)
                    }
                },
                colors = SwitchDefaults.colors(
                    // 스위치가 켜져 있을 때 색상
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0XFF4154FF),
                    checkedBorderColor = Color.Transparent,
                    // 스위치가 꺼져 있을 때 색상
                    uncheckedThumbColor = Color(0xFFB6B7BB),
                    uncheckedTrackColor = Color.White,
                    uncheckedBorderColor = Color(0xFFB6B7BB)
                )

            )
        }
    }
}

private fun startDynamicIsland(
    context: Context
) {
    val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA)
    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
    val startFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 60) // 1시간 후
    val start = dateFormat.format(startFutureTime)
    val endFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 120) // 2시간 후
    val end = dateFormat.format(endFutureTime)
    val ticketInfo = DynamicIslandInfo(
        type = HongDynamicIslandType.AIR.type,
        thumbnailUrl = "https://images.unsplash.com/photo-1664190426315-b3abf1cf07ad?q=80&w=2187&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        fromCity = "FROM   SEOUL/INCHEON",
        toCity = "TO         TOKYO",
        startDate = start,
        endDate = end
    )
    if (HongDynamicIslandManager.isRunning()) {
        HongDynamicIslandManager.reset(ticketInfo)
    } else {
        HongDynamicIslandManager.schedule(context, ticketInfo)
    }
}