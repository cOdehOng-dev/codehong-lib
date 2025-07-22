package com.codehong.lib.sample.map

import com.google.gson.annotations.SerializedName

data class NaverDirectionResponse(
    val code: Int,
    val message: String,
    val route: Route
)

data class Route(
    @SerializedName("traavoidcaronly", alternate = ["traoptimal"])
    val traavoidcaronly: List<Traoptimal>,
)

data class Traoptimal(
    val summary: Summary,
    val path: List<List<Double>> // 경로 좌표 리스트 (경도, 위도)
)

data class Summary(
    val distance: Int, // 총 거리 (m)
    val duration: Int  // 예상 소요 시간 (초)
)