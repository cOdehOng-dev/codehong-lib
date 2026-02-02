package com.codehong.lib.sample

import android.app.Application
import com.codehong.library.network.debug.TimberConfig
import com.codehong.library.network.debug.TimberUtil
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        TimberUtil.init(
            config = TimberConfig.Builder()
                .isEnabled(true)
                .packageName(this::class.java.`package`?.name ?: "")
                .tag("샘플앱")
                .build()
        )
    }
}