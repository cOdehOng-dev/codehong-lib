package com.codehong.lib.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codehong.library.widget.dynamicisland.DynamicIslandInfo
import com.codehong.library.widget.dynamicisland.DynamicIslandManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(this)
        }
        checkDynamicIsland()
    }

    private fun checkDynamicIsland() {
        if (DynamicIslandManager.isGranted(this)) {
            startDynamicIsland()
        }
    }

    private fun startDynamicIsland() {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA)
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val startFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 60) // 1시간 후
        val start = dateFormat.format(startFutureTime)
        val endFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 120) // 2시간 후
        val end = dateFormat.format(endFutureTime)
        val ticketInfo = DynamicIslandInfo(
            thumbnailUrl = "https://images.unsplash.com/photo-1644645233471-d2cbe70e3871?q=80&w=2187&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            fromCity = "FROM   TOKYO",
            toCity = "TO       SEOUL/INCHEON",
            startDate = start,
            endDate = end
        )
        if (DynamicIslandManager.isRunning()) {
            DynamicIslandManager.reset(ticketInfo)
        } else {
            DynamicIslandManager.schedule(this, ticketInfo)
        }
    }
}





