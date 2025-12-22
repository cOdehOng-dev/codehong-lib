# HongVideoPlayer

ExoPlayer 기반의 커스텀 비디오 플레이어 위젯입니다. Jetpack Compose와 Android View 시스템을 모두 지원하며, 다양한 스타일링 옵션과 재생 상태 콜백을 제공합니다.

## 📋 개요

HongVideoPlayer는 Media3 ExoPlayer를 사용하여 동영상을 재생하는 위젯입니다. URL 기반 비디오 재생을 지원하며, 비디오 비율 조정, 모서리 라운드, 재생 상태 콜백 등의 기능을 제공합니다.

## 🏗️ 구조

```
player/
├── HongVideoPlayerOption.kt      # 플레이어 옵션 데이터 클래스
├── HongVideoPlayerBuilder.kt     # Builder 패턴 구현
├── HongVideoPlayerCompose.kt     # Compose용 플레이어
└── HongVideoPlayerView.kt        # View 시스템용 플레이어
```

## 📦 의존성

이 위젯은 Media3 ExoPlayer를 사용합니다. 프로젝트의 build.gradle에 아래 의존성을 추가하세요.

```gradle
implementation("androidx.media3:media3-exoplayer:1.x.x")
implementation("androidx.media3:media3-ui:1.x.x")
```

## 📦 주요 클래스

### HongVideoPlayerOption

비디오 플레이어의 모든 설정을 담는 옵션 클래스입니다.

**주요 속성:**
- `videoUrl: String?` - 재생할 비디오 URL
- `ratio: String?` - 비디오 화면 비율 (예: "16:9", "4:3")
- `radius: HongRadiusInfo` - 모서리 둥글기
- `width`, `height` - 플레이어 크기
- `margin`, `padding` - 여백 설정
- `backgroundColorHex: String` - 배경색
- `border: HongBorderInfo` - 테두리
- `shadow: HongShadowInfo` - 그림자

### HongVideoPlayerBuilder

Builder 패턴을 사용하여 `HongVideoPlayerOption`을 생성하는 클래스입니다.

**주요 메서드:**
- `setVideoUrl(videoUrl: String?)` - 비디오 URL 설정
- `ratio(ratio: String?)` - 비디오 화면 비율 설정
- `radius(radius: HongRadiusInfo)` - 모서리 둥글기 설정
- `height(height: Int?)` - 높이 설정
- `margin(margin: HongSpacingInfo)` - 마진 설정
- `padding(padding: HongSpacingInfo)` - 패딩 설정
- `onClick(onClick: (HongVideoPlayerOption) -> Unit?)` - 클릭 이벤트 설정
- `applyOption()` - 옵션 생성
- `copy(inject: HongVideoPlayerOption)` - 기존 옵션 복사

### HongVideoPlayerCompose

Jetpack Compose용 비디오 플레이어 컴포저블입니다. ExoPlayer를 래핑하여 Compose에서 사용할 수 있게 합니다.

**함수 시그니처:**
```kotlin
@Composable
fun HongVideoPlayerCompose(
    option: HongVideoPlayerOption,
    onPlayVideo: () -> Unit = {},
    onRenderingFinish: () -> Unit = {},
    onReady: () -> Unit = {},
    onBuffering: () -> Unit = {},
    onEnd: () -> Unit = {},
    onError: () -> Unit = {},
    onPlayerReference: (() -> Unit) -> Unit = {}
)
```

**콜백 설명:**
- `onPlayVideo` - 비디오 재생 시작
- `onRenderingFinish` - 첫 프레임 렌더링 완료
- `onReady` - 플레이어 준비 완료
- `onBuffering` - 버퍼링 중
- `onEnd` - 재생 종료
- `onError` - 에러 발생
- `onPlayerReference` - 플레이어 제어 함수 참조 전달

### HongVideoPlayerView

Android View 시스템용 비디오 플레이어입니다. `ConstraintLayout`을 상속하여 구현되었습니다.

**주요 메서드:**
- `set(option: HongVideoPlayerOption, onReady, onEnd, onError)` - 옵션 설정
- `play()` - 비디오 재생 시작
- `clearPlayer()` - 플레이어 리소스 해제

## 🚀 사용법

### 1. Compose - 기본 사용

```kotlin
val option = HongVideoPlayerBuilder()
    .setVideoUrl("https://example.com/video.mp4")
    .ratio("16:9")
    .height(200)
    .radius(HongRadiusInfo(all = 12))
    .applyOption()

HongVideoPlayerCompose(
    option = option,
    onReady = {
        Log.d("VideoPlayer", "Ready to play")
    },
    onEnd = {
        Log.d("VideoPlayer", "Playback ended")
    },
    onError = {
        Log.e("VideoPlayer", "Error occurred")
    }
)
```

### 2. Compose - 재생 상태 관리

```kotlin
var isBuffering by remember { mutableStateOf(false) }
var isPlaying by remember { mutableStateOf(false) }

HongVideoPlayerCompose(
    option = option,
    onBuffering = {
        isBuffering = true
    },
    onReady = {
        isBuffering = false
        isPlaying = true
    },
    onEnd = {
        isPlaying = false
    }
)

if (isBuffering) {
    CircularProgressIndicator()
}
```

### 3. Compose - 플레이어 제어

```kotlin
var clearPlayerFunc by remember { mutableStateOf<(() -> Unit)?>(null) }

HongVideoPlayerCompose(
    option = option,
    onPlayerReference = { clearFunc ->
        clearPlayerFunc = clearFunc
    }
)

Button(onClick = {
    clearPlayerFunc?.invoke()
}) {
    Text("Stop Video")
}
```

### 4. View - 기본 사용

```kotlin
val option = HongVideoPlayerBuilder()
    .setVideoUrl("https://example.com/video.mp4")
    .ratio("16:9")
    .applyOption()

val playerView = HongVideoPlayerView(context).apply {
    set(
        option = option,
        onReady = {
            Log.d("VideoPlayer", "Ready")
        },
        onEnd = {
            Log.d("VideoPlayer", "Ended")
        },
        onError = {
            Log.e("VideoPlayer", "Error")
        }
    )
    play()
}
```

### 5. 스타일링 적용

```kotlin
val option = HongVideoPlayerBuilder()
    .setVideoUrl("https://example.com/video.mp4")
    .ratio("16:9")
    .height(200)
    .margin(HongSpacingInfo(left = 16f, right = 16f))
    .radius(HongRadiusInfo(
        topLeft = 12,
        topRight = 12,
        bottomLeft = 12,
        bottomRight = 12
    ))
    .applyOption()

HongVideoPlayerCompose(option = option)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `HongVideoPlayerBuilder.setVideoUrl()` | 재생할 비디오 URL 설정 |
| `HongVideoPlayerBuilder.ratio()` | 비디오 화면 비율 설정 (16:9, 4:3 등) |
| `HongVideoPlayerBuilder.radius()` | 플레이어 모서리 둥글기 설정 |
| `HongVideoPlayerView.play()` | 비디오 재생 시작 (View 전용) |
| `HongVideoPlayerView.clearPlayer()` | 플레이어 리소스 해제 (View 전용) |

## 🎨 주요 기능

### 비디오 재생
- **URL 기반 재생** - HTTP/HTTPS 비디오 URL 지원
- **자동 재생** - 플레이어 준비 완료 시 자동 재생
- **무음 재생** - 기본적으로 볼륨 0으로 재생 (필요 시 조정 가능)
- **비율 조정** - 16:9, 4:3 등 다양한 화면 비율 지원

### 스타일링
- **모서리 둥글기** - 각 모서리 개별 설정 가능
- **테두리** - 두께, 색상 설정
- **그림자** - 색상, 블러, 오프셋, 스프레드 설정
- **배경색** - 플레이어 배경색 설정

### 재생 상태 콜백
- `onPlayVideo` - 비디오 재생 시작
- `onRenderingFinish` - 첫 프레임 렌더링 완료
- `onReady` - 플레이어 준비 완료 (STATE_READY)
- `onBuffering` - 버퍼링 중 (STATE_BUFFERING)
- `onEnd` - 재생 종료 (STATE_ENDED)
- `onError` - 에러 발생 (STATE_IDLE)

### 리소스 관리
- **자동 해제** - DisposableEffect를 통한 자동 리소스 해제 (Compose)
- **수동 해제** - `clearPlayer()` 메서드를 통한 수동 해제
- **메모리 관리** - 플레이어 인스턴스 정리 및 리스너 제거

## 📝 참고사항

- ExoPlayer는 무음(volume = 0f)으로 자동 재생됩니다
- Compose 버전은 `DisposableEffect`를 사용하여 자동으로 리소스를 해제합니다
- View 버전은 명시적으로 `clearPlayer()`를 호출하여 리소스를 해제해야 합니다
- 비디오 URL이 null이거나 비어있으면 플레이어가 렌더링되지 않습니다
- `onPlayerReference` 콜백을 사용하여 외부에서 플레이어를 제어할 수 있습니다
- 재생 상태가 `STATE_IDLE` 또는 `STATE_ENDED`일 때 자동으로 플레이어가 정리됩니다

## 🔗 관련 파일

- `HongVideoPlayerOption.kt` - 플레이어 옵션 데이터 클래스
- `HongVideoPlayerBuilder.kt` - Builder 패턴 구현
- `HongVideoPlayerCompose.kt` - Compose 컴포저블
- `HongVideoPlayerView.kt` - Android View 구현
