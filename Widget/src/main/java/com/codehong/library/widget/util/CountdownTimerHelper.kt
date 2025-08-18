package com.codehong.library.widget.util

import android.os.CountDownTimer
import java.util.Locale

class CountdownTimerHelper(
    min: Int,
    sec: Int,
    private val remain: (String) -> Unit
) {
    private var countDownTimer: CountDownTimer? = null

    init {
        val totalMillis = (min * 60 + sec) * 1000L

        countDownTimer = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val totalSec = millisUntilFinished / 1000
                val remainMin = totalSec / 60
                val remainSec = totalSec % 60
                remain.invoke(String.format(Locale.KOREA,"%02d:%02d", remainMin, remainSec))
            }

            override fun onFinish() {
                remain.invoke("00:00")
            }
        }.start()
    }

    fun cancel() {
        countDownTimer?.cancel()
    }

}