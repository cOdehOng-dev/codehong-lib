package com.codehong.library.widget.rule

import android.widget.ImageView.ScaleType
import androidx.compose.ui.layout.ContentScale

enum class HongScaleType(val value: String) {
    FIT_XY("fitXY"),
    FIT_START("fitStart"),
    FIT_CENTER("fitCenter"),
    FIT_END("fitEnd"),
    FIT_WIDTH("fitWidth"),
    FIT_HEIGHT("fitHeight"),
    CENTER_CROP("centerCrop"),
    CENTER_INSIDE("centerInside"),
    MATRIX("matrix");

    companion object {
        fun HongScaleType.toContentScale(): ContentScale {
            return when (this) {
                FIT_XY -> ContentScale.FillBounds // 이미지가 뷰 전체를 채움 (비율 무시)
                FIT_START -> ContentScale.Fit     // Fit은 위치 지정 불가, 최선
                FIT_CENTER -> ContentScale.Fit
                FIT_WIDTH -> ContentScale.FillWidth
                FIT_HEIGHT -> ContentScale.FillHeight
                FIT_END -> ContentScale.Fit       // Compose에 정렬 개념은 Modifier로
                CENTER_CROP -> ContentScale.Crop  // View의 CENTER_CROP과 정확히 대응
                CENTER_INSIDE -> ContentScale.Inside // 비율 유지하며 전체 표시
                MATRIX -> ContentScale.None        // MATRIX는 사용자 정의이므로 None이 가장 근접
            }
        }

        fun HongScaleType.toScaleType(): ScaleType {
            return when (this) {
                FIT_XY -> ScaleType.FIT_XY
                FIT_START -> ScaleType.FIT_START
                FIT_CENTER -> ScaleType.FIT_CENTER
                FIT_END -> ScaleType.FIT_END
                CENTER_CROP -> ScaleType.CENTER_CROP
                CENTER_INSIDE -> ScaleType.CENTER_INSIDE
                MATRIX -> ScaleType.MATRIX
                FIT_WIDTH -> ScaleType.FIT_XY
                FIT_HEIGHT -> ScaleType.FIT_XY
            }
        }

        fun String?.toHongScaleType(): HongScaleType {
            if (this.isNullOrEmpty()) {
                return FIT_START // 기본값
            }
            return when (this) {
                FIT_XY.value -> FIT_XY
                FIT_START.value -> FIT_START
                FIT_CENTER.value -> FIT_CENTER
                FIT_END.value -> FIT_END
                CENTER_CROP.value -> CENTER_CROP
                CENTER_INSIDE.value -> CENTER_INSIDE
                MATRIX.value -> MATRIX
                FIT_HEIGHT.value -> FIT_HEIGHT
                FIT_WIDTH.value -> FIT_WIDTH
                else -> FIT_START
            }
        }
    }
}