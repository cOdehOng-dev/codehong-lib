package com.codehong.lib.sample.map

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverDirectionService {
    @GET("map-direction/v1/driving")
    fun getDrivingRoute(
        @Header("X-NCP-APIGW-API-KEY-ID") apiKeyId: String,
        @Header("X-NCP-APIGW-API-KEY") apiKey: String,
        @Query("start") start: String,    // "경도,위도"
        @Query("goal") goal: String,      // "경도,위도"
        @Query("waypoints") waypoints: String? = null, // "경도,위도|경도,위도"
        @Query("option") option: String? = null
    ): Call<NaverDirectionResponse>
}