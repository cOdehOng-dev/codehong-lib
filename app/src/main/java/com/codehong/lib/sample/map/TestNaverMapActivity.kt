package com.codehong.lib.sample.map

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.util.HongToastUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.MarkerIcons
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestNaverMapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var map: NaverMap
    private lateinit var marker: Marker
    private lateinit var startMarker: Marker
    private lateinit var endMarker: Marker

    private var pathCoords: List<LatLng> = emptyList()

    private val sectionDistances = mutableListOf<Float>()
    private val visited = mutableSetOf<Int>()
    private var totalDistance = 0f
    private var movedDistance = 0f
    private var lastNotifiedDistance = 0f
    private val speedPerSecond = 5f
    private val alarmDistance = 1000f

    private val handler = Handler(Looper.getMainLooper())

    private val moveRunnable = object : Runnable {
        override fun run() {
            movedDistance += speedPerSecond
            moveMarkerAlongRoute(movedDistance)

            if (movedDistance < totalDistance) {
                handler.postDelayed(this, 1000)
            }
        }
    }

    private var followMarker = true
    private val followRunnable = Runnable {
        followMarker = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView = MapView(this)
        setContentView(mapView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { naverMap ->
            map = naverMap

            map.addOnCameraChangeListener { _, isCameraTrackingMode ->
                if (isCameraTrackingMode) return@addOnCameraChangeListener

                if (followMarker) {
                    followMarker = false
                    handler.removeCallbacks(followRunnable)
                    handler.postDelayed(followRunnable, 5000)
                }
            }

            requestDrivingRoute()
        }
    }

    private fun requestDrivingRoute() {
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
                        pathCoords = response.body()?.route?.traavoidcaronly?.firstOrNull()?.path
                            ?.map { LatLng(it[1], it[0]) } ?: emptyList()

                        if (pathCoords.isNotEmpty()) {
                            // 경로 선
                            PathOverlay().apply {
                                coords = pathCoords
                                color = Color.GREEN
                                width = dpToPx(3)
                                map = this@TestNaverMapActivity.map
                            }

                            // 움직이는 마커
                            marker = Marker().apply {
                                position = pathCoords.first()
                                icon = MarkerIcons.BLACK
                                width = 60
                                height = 80
                                map = this@TestNaverMapActivity.map
                            }

                            // 출발지 마커
                            startMarker = Marker().apply {
                                position = pathCoords.first()
                                icon = MarkerIcons.RED
                                width = 50
                                height = 70
                                captionText = "출발지"
                                map = this@TestNaverMapActivity.map
                            }

                            // 도착지 마커
                            endMarker = Marker().apply {
                                position = pathCoords.last()
                                icon = MarkerIcons.BLUE
                                width = 50
                                height = 70
                                captionText = "도착지"
                                map = this@TestNaverMapActivity.map
                            }

                            // 초기 카메라 위치
                            map.moveCamera(CameraUpdate.scrollTo(pathCoords.first()))
                            map.moveCamera(CameraUpdate.zoomTo(16.5))

                            initRouteWithPathCoords()
                            startSimulation()
                        }
                    } else {
                        Log.e("TAG", "Response error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NaverDirectionResponse>, t: Throwable) {
                    Log.e("TAG", "Request failed", t)
                }
            })
    }

    private fun initRouteWithPathCoords() {
        sectionDistances.clear()
        totalDistance = 0f
        visited.clear()
        movedDistance = 0f
        lastNotifiedDistance = 0f

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
        lastNotifiedDistance = 0f
        handler.post(moveRunnable)
    }

    private fun moveMarkerAlongRoute(moved: Float) {
        var accumulated = 0f

        // ✅ 거리 알림
        if (moved - lastNotifiedDistance >= alarmDistance) {
            lastNotifiedDistance += alarmDistance
            HongToastUtil.showToast(this, "🚗 이동 거리: ${lastNotifiedDistance.toInt()}m")
        }

        for (i in 0 until sectionDistances.size) {
            val section = sectionDistances[i]

            if (moved <= accumulated + section) {
                val ratio = (moved - accumulated) / section
                val start = pathCoords[i]
                val end = pathCoords[i + 1]

                val lat = start.latitude + (end.latitude - start.latitude) * ratio
                val lng = start.longitude + (end.longitude - start.longitude) * ratio

                val newPosition = LatLng(lat, lng)
                marker.position = newPosition

                if (followMarker) {
                    map.moveCamera(CameraUpdate.scrollTo(newPosition))
                }

                if (!visited.contains(i + 1) && moved >= accumulated + section) {
                    visited.add(i + 1)
                    onCheckpointReached(i + 1)
                }

                break
            }
            accumulated += section
        }

        if (moved >= totalDistance) {
            marker.position = pathCoords.last()
            if (followMarker) {
                map.moveCamera(CameraUpdate.scrollTo(pathCoords.last()))
            }
        }
    }

    private fun onCheckpointReached(index: Int) {
        val name = when (index) {
            1 -> "양화한강공원"
            2 -> "여의도한강공원"
            3 -> "잠실한강공원 (도착!)"
            else -> "알 수 없음"
        }
        Log.d("Route", "📍도달: $name")
        HongToastUtil.showToast(this, "📍도달: $name")
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        handler.removeCallbacks(moveRunnable)
        handler.removeCallbacks(followRunnable)
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
