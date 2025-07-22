package com.codehong.lib.sample.map.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.PolylineOverlay
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun TestNaverMapScreen(
    viewModel: NaverMapViewModel = remember { NaverMapViewModel() }
) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()

    val coroutineScope = rememberCoroutineScope()

    // 사용자 조작 상태 및 자동추적 플래그
    var autoTrackingEnabled by remember { mutableStateOf(true) }
    var resumeTrackingJob by remember { mutableStateOf<Job?>(null) }

    // 출발지 카메라 이동
    LaunchedEffect(viewModel.startLatLng) {
        if (viewModel.startLatLng.latitude != 0.0 && viewModel.startLatLng.longitude != 0.0) {
            cameraPositionState.move(
                CameraUpdate.scrollTo(viewModel.startLatLng).animate(CameraAnimation.None)
            )
        }
    }

    // 이동 마커 상태 remember
    val movedMarkerState = remember { MarkerState() }

    // 이동 위치 업데이트 및 카메라 추적 (자동추적 활성화 시)
    LaunchedEffect(viewModel.movedPosition, autoTrackingEnabled) {
        viewModel.movedPosition?.let { latLng ->
            movedMarkerState.position = latLng
            if (autoTrackingEnabled) {
                cameraPositionState.move(
                    CameraUpdate.scrollTo(latLng).animate(CameraAnimation.None)
                )
            }
        }
    }

    // 지도 카메라 움직임 감지: 사용자가 직접 조작 시 자동추적 비활성화 및 5초 후 복구
    LaunchedEffect(cameraPositionState.isMoving) {
        if (cameraPositionState.isMoving) {
            // 사용자가 지도를 움직이는 중으로 간주
            if (autoTrackingEnabled) {
                autoTrackingEnabled = false
            }
            // 기존 복구 작업 있으면 취소
            resumeTrackingJob?.cancel()
            // 5초 후 자동추적 재활성화
            resumeTrackingJob = coroutineScope.launch {
                delay(5000)
                autoTrackingEnabled = true
            }
        }
    }

    // 경로 로드
    LaunchedEffect(Unit) {
        viewModel.loadRoute()
    }

    NaverMap(
        cameraPositionState = cameraPositionState,
        locationSource = null,
        onMapClick = { _, _ ->
            // 지도 클릭 시 자동추적 끄고 5초 후 켜기
            if (autoTrackingEnabled) autoTrackingEnabled = false
            resumeTrackingJob?.cancel()
            resumeTrackingJob = coroutineScope.launch {
                delay(5000)
                autoTrackingEnabled = true
            }
        }
    ) {
        // 전체 경로 표시
        if (viewModel.pathCoords.isNotEmpty()) {
            PolylineOverlay(
                coords = viewModel.pathCoords,
                color = Color.Green,
                width = 5.dp
            )
        }

        // 출발지 마커
        Marker(
            state = remember { MarkerState(position = viewModel.startLatLng) },
            icon = MarkerIcons.RED,
            captionText = "출발지"
        )

        // 도착지 마커
        Marker(
            state = remember { MarkerState(position = viewModel.endLatLng) },
            icon = MarkerIcons.BLUE,
            captionText = "도착지"
        )

        // 이동 중인 마커
        if (viewModel.movedPosition != null) {
            Marker(
                state = movedMarkerState,
                icon = MarkerIcons.BLACK
            )
        }
    }
}
