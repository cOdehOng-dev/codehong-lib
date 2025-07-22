package com.codehong.lib.sample.map

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    private var pathCoords: List<LatLng> = emptyList()  // 네이버 경로 API에서 받은 경로 좌표 리스트

    private val sectionDistances = mutableListOf<Float>()
    private val visited = mutableSetOf<Int>()
    private var totalDistance = 0f
    private var movedDistance = 0f
    private val speedPerSecond = 5f // 5m/s

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

    // 지도 조작 시 마커 따라 카메라 이동 멈추고, 5초 후 다시 따라오기
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

            // 지도 조작 이벤트 리스너 등록
            map.addOnCameraChangeListener { _, isCameraTrackingMode ->
                // 카메라가 자동 이동 중이면 무시
                if (isCameraTrackingMode) return@addOnCameraChangeListener

                // 사용자가 조작 시작 시 따라오기 끔
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

        // TODO: 발급받은 API 키로 바꿔주세요
        val apiKeyId = "7q99m8ujdt"
        val apiKey = "Pb7hGj6pa8qesXelcVqJ1KUXdVPDkMN1JIo5VgZj"
        val start = "126.82551646411272,37.55969195722661" // 경도,위도
        val goal = "126.92416255521461,37.52191399818966"    // 경도,위도

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
                            PathOverlay().apply {
                                coords = pathCoords
                                color = Color.BLUE
                                width = 10
                                map = this@TestNaverMapActivity.map
                            }

                            // 마커 초기화
                            marker = Marker().apply {
                                position = pathCoords.first()
                                icon = MarkerIcons.BLACK
                                width = 60
                                height = 80
                                map = this@TestNaverMapActivity.map
                            }

                            // 카메라 초기 위치 설정
                            map.moveCamera(CameraUpdate.scrollTo(pathCoords.first()))
                            map.moveCamera(CameraUpdate.zoomTo(16.5))

                            // API 경로 기준 거리 계산 후 시뮬레이션 시작
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
        handler.post(moveRunnable)
    }

    private fun moveMarkerAlongRoute(moved: Float) {
        var accumulated = 0f

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
        Toast.makeText(this, "📍도달: $name", Toast.LENGTH_SHORT).show()
    }

    // 생명주기 콜백들
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
