# HongVideoPopup

동영상 재생 기능이 포함된 팝업 위젯입니다.

## 📋 개요

HongVideoPopup은 동영상을 재생할 수 있는 팝업 위젯으로, 광고 영상이나 프로모션 영상을 표시할 때 사용됩니다. 화면 하단에서 슬라이드 업 애니메이션으로 나타나며, 동영상 재생 후 자동으로 닫히거나 사용자가 수동으로 닫을 수 있습니다. "오늘은 그만 보기" 기능을 통해 24시간 동안 팝업을 표시하지 않도록 설정할 수 있으며, 랜딩 링크를 통해 광고 클릭 시 특정 페이지로 이동할 수 있습니다. Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
videopopup/
├── HongVideoPopupBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongVideoPopupOption.kt        # 옵션 데이터 클래스
├── HongVideoPopupCompose.kt       # Compose용 컴포넌트
├── HongVideoPopupView.kt          # View 시스템용 커스텀 뷰
└── HongVideoPopupManager.kt       # "오늘은 그만 보기" 타임스탬프 관리
```

## 📦 주요 클래스

### HongVideoPopupBuilder
빌더 패턴을 사용하여 비디오 팝업의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 높이 설정 (`height`)
- 여백 설정 (`margin`, `padding`)
- 클릭 이벤트 (`onClick`)
- 비디오 플레이어 옵션 설정 (`videoPlayerOption`)
- 외부 터치 차단 설정 (`blockTouchOutside`)
- 랜딩 링크 설정 (`landingLink`)
- 옵션 복사 (`copy`)

### HongVideoPopupOption
비디오 팝업의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `videoPlayerOption`: 비디오 플레이어 상세 옵션 (기본 비율: 16:9, 둥근 모서리)
- `blockTouchOutside`: 팝업 외부 터치 시 닫기 차단 여부 (기본값: `true`)
- `landingLink`: 광고 클릭 시 이동할 URL

**기본 비디오 플레이어 스타일:**
- 비율: 16:9
- 둥근 모서리: 상단 좌/우 10dp

### HongVideoPopupCompose
Jetpack Compose에서 사용할 수 있는 비디오 팝업 컴포넌트입니다.

**구조:**
- Box (전체 화면, 딤 처리)
  - Column (하단 정렬, 슬라이드 업 애니메이션)
    - HongVideoPlayerCompose (비디오 플레이어)
    - Row (버튼 영역, 64dp 높이)
      - "오늘은 그만 보기" 버튼 (weight=1f)
      - "닫기" 버튼 (weight=1f)

**애니메이션:**
- 슬라이드 업/다운: 300ms, 0dp ↔ 300dp
- 딤 페이드: 150ms, 0f ↔ 0.5f (50% 불투명도)

### HongVideoPopupView
기존 View 시스템에서 사용할 수 있는 커스텀 ConstraintLayout 뷰입니다.

**구조:**
- ConstraintLayout (전체 화면)
  - vDim (딤 배경, 전체 화면)
  - llContent (콘텐츠 영역, 하단 정렬)
    - vVideoContainer (비디오 플레이어 컨테이너)
    - llBottom (버튼 영역, 64dp 높이)
      - tvNoShow ("오늘은 그만 보기" 텍스트)
      - tvClose ("닫기" 텍스트)

**애니메이션:**
- 슬라이드 업: `slide_in_bottom.xml` (250ms, DecelerateInterpolator)
- 슬라이드 다운: `translationY` 애니메이션 (250ms)
- 딤 페이드: `fade_in.xml` (150ms)

### HongVideoPopupManager
싱글톤 객체로, "오늘은 그만 보기" 기능의 타임스탬프를 관리합니다.

**주요 기능:**
- `isAllowDisplaying()`: 팝업을 표시할 수 있는지 확인 (24시간 경과 여부)
- `getOneDayLastSeenTimestamp()`: 마지막으로 "오늘은 그만 보기"를 클릭한 시간 조회
- `saveOneDayLastSeenTimestamp()`: 현재 시간을 타임스탬프로 저장
- `resetLastSeenTimestamp()`: 저장된 타임스탬프 초기화

**저장소:**
- SharedPreferences 사용
- 키: `Consts.KEY_VIDEO_POPUP_NO_SHOW_ONE_DAY`
- 값: `Long` (밀리초 단위 타임스탬프)

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun MainScreen() {
    var showPopup by remember { mutableStateOf(false) }

    if (showPopup) {
        val option = HongVideoPopupBuilder()
            .height(HongLayoutParam.WRAP_CONTENT.value)
            .videoPlayerOption(
                HongVideoPlayerBuilder()
                    .ratio("16:9")
                    .videoUrl("https://example.com/video.mp4")
                    .radius(
                        HongRadiusInfo(
                            topLeft = 10,
                            topRight = 10
                        )
                    )
                    .applyOption()
            )
            .blockTouchOutside(true)
            .landingLink("https://example.com/promo")
            .applyOption()

        HongVideoPopupCompose(
            option = option,
            onShow = {
                // 팝업이 표시될 때
            },
            onHide = { isClickClose ->
                showPopup = false
                if (!isClickClose) {
                    // "오늘은 그만 보기" 클릭
                    HongVideoPopupManager.saveOneDayLastSeenTimestamp(context)
                }
            },
            showPopup = { isShowing ->
                // 팝업 상태 변경 시
            },
            clickLanding = { link ->
                // 랜딩 링크 클릭 시
                // link: "https://example.com/promo"
            }
        )
    }

    LaunchedEffect(Unit) {
        // 팝업 표시 가능 여부 확인
        if (HongVideoPopupManager.isAllowDisplaying(context)) {
            showPopup = true
        }
    }
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<FrameLayout
    android:id="@+id/popupContainer"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

**Kotlin 코드:**
```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var videoPopup: HongVideoPopupView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 팝업 표시 가능 여부 확인
        if (HongVideoPopupManager.isAllowDisplaying(this)) {
            showVideoPopup()
        }
    }

    private fun showVideoPopup() {
        val container = findViewById<FrameLayout>(R.id.popupContainer)

        val option = HongVideoPopupBuilder()
            .height(HongLayoutParam.WRAP_CONTENT.value)
            .videoPlayerOption(
                HongVideoPlayerBuilder()
                    .ratio("16:9")
                    .videoUrl("https://example.com/video.mp4")
                    .radius(
                        HongRadiusInfo(
                            topLeft = 10,
                            topRight = 10
                        )
                    )
                    .applyOption()
            )
            .blockTouchOutside(true)
            .landingLink("https://example.com/promo")
            .onClick { link ->
                // 랜딩 링크 클릭 시
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            }
            .applyOption()

        videoPopup = HongVideoPopupView(this).apply {
            set(
                option = option,
                onShow = {
                    // 팝업이 표시될 때
                },
                onHide = { isClickClose ->
                    if (!isClickClose) {
                        // "오늘은 그만 보기" 클릭
                        HongVideoPopupManager.saveOneDayLastSeenTimestamp(this@MainActivity)
                    }
                    container.removeView(this)
                }
            )
        }

        container.addView(videoPopup)
        videoPopup.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::videoPopup.isInitialized && videoPopup.isShow()) {
            videoPopup.dismiss(true)
        }
    }
}
```

### 외부 터치로 닫기 허용

```kotlin
val option = HongVideoPopupBuilder()
    .videoPlayerOption(
        HongVideoPlayerBuilder()
            .videoUrl("https://example.com/video.mp4")
            .applyOption()
    )
    .blockTouchOutside(false) // 외부 터치 시 닫기 허용
    .applyOption()
```

### 랜딩 링크 없이 사용

```kotlin
val option = HongVideoPopupBuilder()
    .videoPlayerOption(
        HongVideoPlayerBuilder()
            .videoUrl("https://example.com/video.mp4")
            .applyOption()
    )
    // landingLink를 설정하지 않음
    .applyOption()
```

### "오늘은 그만 보기" 타임스탬프 관리

```kotlin
// 팝업 표시 가능 여부 확인
if (HongVideoPopupManager.isAllowDisplaying(context)) {
    // 팝업 표시
    showVideoPopup()
}

// "오늘은 그만 보기" 클릭 시
HongVideoPopupManager.saveOneDayLastSeenTimestamp(context)

// 타임스탬프 초기화 (테스트 또는 설정 리셋 시)
HongVideoPopupManager.resetLastSeenTimestamp(context)

// 마지막 타임스탬프 조회
val timestamp = HongVideoPopupManager.getOneDayLastSeenTimestamp(context)
if (timestamp != Consts.NO_VALUE) {
    // timestamp: Long (밀리초)
    val date = Date(timestamp)
}
```

## ⚙️ 주요 메서드

### HongVideoPopupBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `height(height)` | `Int` | 팝업 높이 설정 (기본값: WRAP_CONTENT) |
| `margin(margin)` | `HongSpacingInfo` | 외부 여백 설정 |
| `padding(padding)` | `HongSpacingInfo` | 내부 여백 설정 |
| `onClick(callback)` | `(String?) -> Unit` | 랜딩 링크 클릭 콜백 (View 시스템 전용) |
| `videoPlayerOption(option)` | `HongVideoPlayerOption?` | 비디오 플레이어 상세 옵션 설정 |
| `blockTouchOutside(block)` | `Boolean` | 외부 터치 시 닫기 차단 여부 (기본값: `true`) |
| `landingLink(link)` | `String?` | 광고 클릭 시 이동할 URL |
| `copy(inject)` | `HongVideoPopupOption?` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongVideoPopupView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option, onShow, onHide)` | `HongVideoPopupOption`, `() -> Unit`, `(Boolean) -> Unit` | `HongVideoPopupView` | 옵션을 적용하여 뷰 구성 |
| `show()` | - | `Unit` | 팝업을 애니메이션과 함께 표시 |
| `dismiss(isClickClose, onHide)` | `Boolean`, `(Boolean) -> Unit` | `Unit` | 팝업을 애니메이션과 함께 닫기 |
| `isShow()` | - | `Boolean` | 팝업 표시 상태 확인 |
| `checkVisible()` | - | `Boolean` | 팝업의 가시성 확인 (VISIBLE 여부) |

### HongVideoPopupCompose

| 파라미터 | 타입 | 설명 |
|---------|------|------|
| `option` | `HongVideoPopupOption` | 팝업 옵션 |
| `onShow` | `() -> Unit` | 팝업이 표시될 때 콜백 (기본값: 빈 함수) |
| `onHide` | `(Boolean) -> Unit` | 팝업이 닫힐 때 콜백, 파라미터: isClickClose (기본값: 빈 함수) |
| `showPopup` | `(Boolean) -> Unit` | 팝업 표시 상태 변경 콜백 (기본값: 빈 함수) |
| `clickLanding` | `((String?) -> Unit)?` | 랜딩 링크 클릭 콜백 (기본값: null) |

### HongVideoPopupManager

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `isAllowDisplaying(context)` | `Context?` | `Boolean` | 팝업을 표시할 수 있는지 확인 (24시간 경과 여부) |
| `getOneDayLastSeenTimestamp(context)` | `Context?` | `Long` | 마지막 "오늘은 그만 보기" 클릭 시간 조회 (밀리초) |
| `saveOneDayLastSeenTimestamp(context)` | `Context?` | `Unit` | 현재 시간을 타임스탬프로 저장 |
| `resetLastSeenTimestamp(context)` | `Context?` | `Unit` | 저장된 타임스탬프 초기화 |

## 📝 참고사항

### 레이아웃 구조
- **전체 크기:** MATCH_PARENT × WRAP_CONTENT (기본값)
- **배치:** 화면 하단에서 슬라이드 업
- **딤 배경:** 50% 불투명도 검은색 (`Color.Black.copy(alpha = 0.5f)`)
- **콘텐츠 영역:** 비디오 플레이어 + 버튼 영역 (64dp)
- **버튼 영역:** 수평 2분할 ("오늘은 그만 보기" / "닫기")

### 애니메이션
- **슬라이드 업/다운:** 300ms (Compose), 250ms (View)
- **딤 페이드:** 150ms
- **인터폴레이터:** DecelerateInterpolator (View 시스템)
- **트랜지션:** `updateTransition` (Compose), `AnimationUtils` (View)

### 기본값
- 높이: `HongLayoutParam.WRAP_CONTENT` (콘텐츠에 맞춤)
- 비디오 비율: 16:9
- 둥근 모서리: 상단 좌/우 10dp
- `blockTouchOutside`: `true` (외부 터치 시 닫기 차단)
- `landingLink`: `null` (랜딩 링크 없음)
- 버튼 배경색: `colorResource(R.color.honglib_color_default)`
- 버튼 텍스트 색상: `Color.White`
- 버튼 폰트: Pretendard 400, 16sp

### 주의사항
- `videoPlayerOption.videoUrl`이 null이거나 빈 문자열인 경우: 팝업이 표시되지 않습니다 (Compose)
- View 시스템에서는 `show()` 메서드를 호출해야 팝업이 표시됩니다
- Compose에서는 상태 관리를 별도로 구현해야 합니다 (`remember`, `mutableStateOf` 등)
- "오늘은 그만 보기" 클릭 시 `HongVideoPopupManager.saveOneDayLastSeenTimestamp()`를 호출해야 24시간 동안 팝업이 표시되지 않습니다
- `onHide` 콜백의 `isClickClose` 파라미터:
  - `true`: "닫기" 버튼 또는 외부 터치로 닫힌 경우
  - `false`: "오늘은 그만 보기" 버튼으로 닫힌 경우
- 비디오 재생 종료 시 또는 오류 발생 시 자동으로 팝업이 닫힙니다
- 랜딩 링크가 설정되어 있으면 비디오 플레이어 영역 클릭 시 `clickLanding` 콜백이 호출됩니다
- View 시스템에서는 Activity/Fragment 종료 시 `dismiss()`를 호출하여 리소스를 정리해야 합니다
- Compose에서는 비디오 플레이어 참조(`videoClearRef`)를 통해 자동으로 리소스가 정리됩니다

### 의존성
- HongVideoPlayer (비디오 플레이어 위젯)
- HongColor (디자인 시스템 색상)
- HongTypo (디자인 시스템 타이포그래피)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)
- HongDateUtil (24시간 경과 여부 확인 유틸리티)
- SharedPreferences (타임스탬프 저장)
- Jetpack Compose Animation (Compose 전용)
- ViewBinding (View 시스템 전용)

### 사용 사례
- 앱 시작 시 프로모션 영상 표시
- 광고 영상 팝업
- 이벤트 안내 영상
- 튜토리얼 영상
- 신규 기능 소개 영상
- 마케팅 캠페인 영상

### 상태 관리 예시 (Compose)

```kotlin
@Composable
fun AppLaunchScreen() {
    val context = LocalContext.current
    var showVideoPopup by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // 앱 시작 시 팝업 표시 가능 여부 확인
        showVideoPopup = HongVideoPopupManager.isAllowDisplaying(context)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 메인 콘텐츠
        MainContent()

        // 비디오 팝업
        if (showVideoPopup) {
            HongVideoPopupCompose(
                option = HongVideoPopupBuilder()
                    .videoPlayerOption(
                        HongVideoPlayerBuilder()
                            .videoUrl("https://example.com/promo.mp4")
                            .applyOption()
                    )
                    .landingLink("https://example.com/event")
                    .applyOption(),
                onHide = { isClickClose ->
                    showVideoPopup = false
                    if (!isClickClose) {
                        // "오늘은 그만 보기" 클릭 시 타임스탬프 저장
                        HongVideoPopupManager.saveOneDayLastSeenTimestamp(context)
                    }
                },
                clickLanding = { link ->
                    // 랜딩 링크 클릭 시 브라우저 열기
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    context.startActivity(intent)
                }
            )
        }
    }
}
```

### 비디오 이벤트 처리

```kotlin
val option = HongVideoPopupBuilder()
    .videoPlayerOption(
        HongVideoPlayerBuilder()
            .videoUrl("https://example.com/video.mp4")
            .onReady {
                // 비디오 준비 완료 시
                Log.d("VideoPopup", "Video ready")
            }
            .onEnd {
                // 비디오 재생 종료 시 (자동으로 팝업 닫힘)
                Log.d("VideoPopup", "Video ended")
            }
            .onError {
                // 비디오 오류 발생 시 (자동으로 팝업 닫힘)
                Log.e("VideoPopup", "Video error")
            }
            .applyOption()
    )
    .applyOption()
```

### 커스텀 비디오 플레이어 스타일

```kotlin
val option = HongVideoPopupBuilder()
    .videoPlayerOption(
        HongVideoPlayerBuilder()
            .videoUrl("https://example.com/video.mp4")
            .ratio("4:3") // 4:3 비율
            .radius(
                HongRadiusInfo(
                    topLeft = 20,
                    topRight = 20,
                    bottomLeft = 0,
                    bottomRight = 0
                )
            )
            .backgroundColor(HongColor.BLACK_100)
            .applyOption()
    )
    .blockTouchOutside(false) // 외부 터치 허용
    .applyOption()
```

### SharedPreferences 저장 구조

```kotlin
// 저장 위치
val prefs = context.getSharedPreferences(Consts.PREF_DATA_STORE, Context.MODE_PRIVATE)

// 키
val key = Consts.KEY_VIDEO_POPUP_NO_SHOW_ONE_DAY

// 값
val timestamp: Long = System.currentTimeMillis() // 밀리초 단위

// 저장
prefs.edit().putLong(key, timestamp).apply()

// 조회
val savedTimestamp = prefs.getLong(key, Consts.NO_VALUE)

// 24시간 경과 확인
val (isAllowDisplaying, remainingMinutes) = HongDateUtil.checkNoShowTime(
    savedTimestamp,
    HongDateUtil.MINUTES_IN_A_DAY // 1440분 (24시간)
)
```

### 타임스탬프 초기화 (테스트/디버깅)

```kotlin
// 앱 설정 화면에서 "팝업 초기화" 버튼 제공
Button(onClick = {
    HongVideoPopupManager.resetLastSeenTimestamp(context)
    Toast.makeText(context, "팝업 설정이 초기화되었습니다", Toast.LENGTH_SHORT).show()
}) {
    Text("팝업 초기화")
}
```
