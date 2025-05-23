package com.codehong.lib.sample

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }
}