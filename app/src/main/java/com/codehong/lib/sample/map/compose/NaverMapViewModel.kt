package com.codehong.lib.sample.map.compose

import android.location.Location
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.codehong.lib.sample.map.NaverDirectionResponse
import com.codehong.lib.sample.map.NaverDirectionService
import com.naver.maps.geometry.LatLng
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverMapViewModel : ViewModel() {

    private val handler = Handler(Looper.getMainLooper())
    private var totalDistance = 0f
    private var movedDistance = 0f
    private val speedPerSecond = 5f
    private val alarmDistance = 1000f
    private var lastNotifiedDistance = 0f

    private val _pathCoords = mutableStateListOf<LatLng>()
    val pathCoords: List<LatLng> get() = _pathCoords

    var movedPosition by mutableStateOf<LatLng?>(startLatLng)
        private set

    val startLatLng: LatLng get() = LatLng(37.55969195722661, 126.82551646411272)
    val endLatLng: LatLng get() = LatLng(37.52191399818966, 126.92416255521461)

    var followMarker by mutableStateOf(true)

    private val sectionDistances = mutableListOf<Float>()

    private val moveRunnable = object : Runnable {
        override fun run() {
            movedDistance += speedPerSecond
            moveMarkerAlongRoute()

            if (movedDistance < totalDistance) {
                handler.postDelayed(this, 1000)
            }
        }
    }

    fun loadRoute() {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.apigw.ntruss.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NaverDirectionService::class.java)

        val apiKeyId = "7q99m8ujdt"
        val apiKey = "Pb7hGj6pa8qesXelcVqJ1KUXdVPDkMN1JIo5VgZj"
        val start = "126.82551646411272,37.55969195722661"
        val goal = "126.92416255521461,37.52191399818966"

        service.getDrivingRoute(apiKeyId, apiKey, start, goal, null, option = "traavoidcaronly")
            .enqueue(object : Callback<NaverDirectionResponse> {
                override fun onResponse(
                    call: Call<NaverDirectionResponse>,
                    response: Response<NaverDirectionResponse>
                ) {
                    if (response.isSuccessful) {
                        val coords = response.body()?.route?.traavoidcaronly?.firstOrNull()?.path
                            ?.map { LatLng(it[1], it[0]) } ?: emptyList()

                        _pathCoords.clear()
                        _pathCoords.addAll(coords)
                        initRoute()
                        startSimulation()
                    } else {
                        Log.e("MAP", "API 실패: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NaverDirectionResponse>, t: Throwable) {
                    Log.e("MAP", "API 실패", t)
                }
            })
    }

    private fun initRoute() {
        movedDistance = 0f
        lastNotifiedDistance = 0f
        totalDistance = 0f
        sectionDistances.clear()

        for (i in 0 until pathCoords.size - 1) {
            val result = FloatArray(1)
            Location.distanceBetween(
                pathCoords[i].latitude, pathCoords[i].longitude,
                pathCoords[i + 1].latitude, pathCoords[i + 1].longitude,
                result
            )
            sectionDistances.add(result[0])
            totalDistance += result[0]
        }
    }

    private fun startSimulation() {
        handler.removeCallbacks(moveRunnable)
        movedDistance = 0f
        movedPosition = pathCoords.firstOrNull()
        handler.post(moveRunnable)
    }

    private fun moveMarkerAlongRoute() {
        var accumulated = 0f

        if (movedDistance - lastNotifiedDistance >= alarmDistance) {
            lastNotifiedDistance += alarmDistance
            Log.d("MAP", "🚗 이동 거리: ${lastNotifiedDistance.toInt()}m")
        }

        for (i in 0 until sectionDistances.size) {
            val section = sectionDistances[i]
            if (movedDistance <= accumulated + section) {
                val ratio = (movedDistance - accumulated) / section
                val start = pathCoords[i]
                val end = pathCoords[i + 1]
                val lat = start.latitude + (end.latitude - start.latitude) * ratio
                val lng = start.longitude + (end.longitude - start.longitude) * ratio
                movedPosition = LatLng(lat, lng)
                return
            }
            accumulated += section
        }

        // 도착
        movedPosition = pathCoords.lastOrNull()
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(moveRunnable)
    }
}
