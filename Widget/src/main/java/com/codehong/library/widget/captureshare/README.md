# HongCaptureShare

Android 스크린샷 자동 감지 및 공유 기능을 제공하는 라이브러리입니다.

## 📋 개요

HongCaptureShare는 사용자가 앱 내에서 스크린샷을 촬영하면 자동으로 감지하여 공유 UI를 표시하는 기능을 제공합니다. Jetpack Compose와 기존 View 시스템 모두를 지원하며, 권한 관리와 사용자 경험을 고려한 다양한 기능을 포함하고 있습니다.

## 🏗️ 구조

```
captureshare/
├── HongCaptureShareMainScreen.kt    # Compose 메인 화면 (샘플)
├── HongCaptureShareManager.kt        # 스크린샷 감지 및 권한 관리
├── HongCaptureShareView.kt           # View 시스템용 커스텀 뷰
└── HongCaptureShareCompose.kt        # Compose UI 컴포넌트
```

## 📦 주요 클래스

### HongCaptureShareManager
스크린샷 감지 및 권한 관리를 담당하는 싱글톤 객체입니다.

**주요 기능:**
- 스크린샷 자동 감지 (`observeScreenshot`)
- 권한 체크 및 요청 (`checkPermission`)
- 사용자 안내 다이얼로그 표시 (`showPermissionDialog`, `showSettingDialog`)
- 권한 재요청 주기 관리 (1개월 단위)

### HongCaptureShareView
기존 View 시스템에서 사용할 수 있는 커스텀 뷰입니다.

**주요 기능:**
- 스크린샷 감지 시 슬라이드 다운 애니메이션으로 공유 UI 표시
- 위로 스와이프하여 UI 닫기
- 5초 후 자동 사라짐
- Glide를 사용한 이미지 로딩

### HongCaptureShareCompose
Jetpack Compose용 공유 UI 컴포넌트입니다.

**주요 기능:**
- 캡처 이미지 미리보기
- 위로 드래그하여 닫기
- 5초 후 자동 사라짐
- Coil을 사용한 이미지 로딩

### CaptureShareMainScreen
Compose 환경에서의 전체 화면 샘플 구현입니다.

**주요 기능:**
- ContentObserver를 통한 스크린샷 감지
- 권한 요청 처리
- 캡처 이미지 캐싱 및 공유

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun YourScreen(activity: Activity) {
    CaptureShareMainScreen(
        activity = activity,
        shareLink = "https://example.com/share-link"
    )
}
```

### View 시스템 사용

```kotlin
class MainActivity : AppCompatActivity() {
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { /* 권한 결과 처리 */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val captureShareView = findViewById<HongCaptureShareView>(R.id.captureShareView)
        captureShareView.initialScreenShotShareView(
            activity = this,
            launcher = permissionLauncher,
            shareLink = "https://example.com/share-link"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        captureShareView.removeAutoDismissHandler(this)
    }
}
```

### 직접 스크린샷 감지 구현

```kotlin
HongCaptureShareManager.observeScreenshot(activity) { bitmap ->
    // 스크린샷 비트맵 처리
    val uri = saveBitmapToCache(bitmap)
    // UI 표시 또는 공유 로직
}
```

## ⚙️ 주요 메서드

### HongCaptureShareManager

| 메서드 | 설명 |
|--------|------|
| `observeScreenshot(activity, callback)` | 최근 스크린샷을 감지하여 Bitmap으로 반환 |
| `checkPermission(activity, launcher)` | 필요한 권한을 체크하고 요청 |
| `showPermissionDialog(activity, launcher)` | 권한 요청 안내 다이얼로그 표시 |
| `showSettingDialog(activity)` | 설정 화면 이동 안내 다이얼로그 표시 |
| `saveOneMonthLater(context)` | 다음 권한 요청 시점을 1개월 후로 저장 |
| `isReachedOneMonthLater(context)` | 권한 재요청 시점 도달 여부 확인 |

### HongCaptureShareView

| 메서드 | 설명 |
|--------|------|
| `initialScreenShotShareView(activity, launcher, shareLink)` | 뷰 초기화 및 스크린샷 감지 시작 |
| `setShareLink(shareLink)` | 공유 시 함께 전달할 링크 설정 |
| `removeAutoDismissHandler(activity)` | Observer 등록 해제 및 핸들러 정리 |

### HongCaptureShareCompose

| 파라미터 | 설명 |
|--------|------|
| `imageUri` | 표시할 캡처 이미지 URI |
| `onShareClicked` | 공유 버튼 클릭 시 콜백 |
| `onDismiss` | UI 닫힘 시 콜백 |

## 📝 참고사항

### 필수 권한
```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"
    android:maxSdkVersion="32" />
```

### FileProvider 설정
```xml
<!-- AndroidManifest.xml -->
<provider
    android:name="androidx.core.content.FileProvider"
    android:authorities="${applicationId}.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths" />
</provider>
```

```xml
<!-- res/xml/file_paths.xml -->
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <cache-path name="shared_images" path="images/" />
</paths>
```

### 주의사항
- 스크린샷 감지는 `MediaStore.Images.Media.EXTERNAL_CONTENT_URI`의 변경을 감지하여 동작합니다
- Screenshots 폴더의 이미지만 감지하도록 필터링되어 있습니다
- 앱이 포그라운드 상태일 때만 감지가 동작합니다
- 권한 거부 시 1개월 동안 다이얼로그가 표시되지 않습니다
- `HongCaptureShareView` 사용 시 반드시 `removeAutoDismissHandler()`를 호출하여 리소스를 정리해야 합니다
- 디바운스 처리(3초)로 중복 감지를 방지합니다

### 의존성
- Jetpack Compose (Compose 사용 시)
- Glide (View 시스템 사용 시)
- Coil (Compose 사용 시)
- ThreeTenABP (날짜 처리)
- HongDialog (권한 안내 다이얼로그)
- PermissionManager (권한 체크)
