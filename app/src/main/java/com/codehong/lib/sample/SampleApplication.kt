package com.codehong.lib.sample

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.naver.maps.map.NaverMapSdk

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NcpKeyClient("7q99m8ujdt")
    }
}