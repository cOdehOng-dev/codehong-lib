# HongSwitch

iOS 스타일의 토글 스위치 위젯입니다.

## 📋 개요

HongSwitch는 iOS의 UISwitch와 유사한 토글 스위치 위젯입니다. ON/OFF 상태를 시각적으로 표현하며, 원형 커서가 좌우로 슬라이딩하는 애니메이션이 특징입니다. 설정 화면의 기능 활성화/비활성화, 알림 토글, 다크 모드 전환 등 이진 선택이 필요한 상황에 적합합니다. 빌더 패턴을 통해 ON/OFF 색상, 커서 크기, 애니메이션을 쉽게 커스터마이징할 수 있으며, Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
toggleswitch/
├── HongSwitchBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongSwitchOption.kt        # 옵션 데이터 클래스
├── HongSwitchCompose.kt       # Compose용 컴포넌트
└── HongSwitchView.kt          # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongSwitchBuilder
빌더 패턴을 사용하여 토글 스위치의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- ON 상태 색상 설정 (`onColor`)
- OFF 상태 색상 설정 (`offColor`)
- 커서 크기 설정 (`cursorSize`)
- 커서 수평 마진 설정 (`cursorHorizontalMargin`)
- 커서 색상 설정 (`cursorColor`)
- 초기 상태 설정 (`initialState`)
- 스위치 전환 콜백 설정 (`switchClick`)
- 테두리 설정 (`border`)
- 옵션 복사 (`copy`)

### HongSwitchOption
토글 스위치의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `onColor`: ON 상태 배경색 (HongColor, 기본값: `MAIN_ORANGE_100`)
- `onColorHex`: ON 상태 배경색 Hex (기본값: `HongColor.MAIN_ORANGE_100.hex`)
- `offColor`: OFF 상태 배경색 (HongColor, 기본값: `GRAY_20`)
- `offColorHex`: OFF 상태 배경색 Hex (기본값: `HongColor.GRAY_20.hex`)
- `cursorSize`: 커서(원형 토글) 크기 (기본값: `25`)
- `cursorHorizontalMargin`: 커서 좌우 여백 (기본값: `3`)
- `cursorColor`: 커서 색상 (HongColor, 기본값: `WHITE_100`)
- `cursorColorHex`: 커서 색상 Hex (기본값: `HongColor.WHITE_100.hex`)
- `initialState`: 초기 ON/OFF 상태 (기본값: `false`)
- `switchClick`: 스위치 전환 시 호출되는 콜백 (위젯, 상태 전달)
- `useShapeCircle`: 원형 모양 사용 여부 (기본값: `true`)

**기본 스타일:**
- 너비: 55dp
- 높이: 30dp
- ON 색상: 주황색 (`HongColor.MAIN_ORANGE_100`)
- OFF 색상: 회색 (`HongColor.GRAY_20`)
- 커서 크기: 25dp
- 커서 수평 마진: 3dp
- 커서 색상: 흰색 (`HongColor.WHITE_100`)
- 초기 상태: OFF (`false`)

### HongSwitchCompose
Jetpack Compose에서 사용할 수 있는 토글 스위치 컴포넌트입니다.

**구조:**
- HongWidgetContainer (공통 컨테이너)
  - Box (스위치 배경, 클릭 가능)
    - Box (원형 커서, 슬라이딩 애니메이션)

**애니메이션:**
- 커서 슬라이딩: 200ms, `animateDpAsState` with `tween`
- 오프셋 계산: OFF → 0dp, ON → (width - cursorSize - cursorHorizontalMargin * 2).dp

**주요 기능:**
- 상태 관리: `rememberSaveable` (화면 회전 시에도 상태 유지)
- 배경색 자동 변경: ON/OFF 상태에 따라 자동 전환
- 리플 효과 없는 클릭 (`disableRippleClickable`)
- 커서는 항상 원형 (`CircleShape`)

### HongSwitchView
기존 View 시스템에서 사용할 수 있는 커스텀 FrameLayout 뷰입니다.

**구조:**
- FrameLayout (스위치 배경)
  - View (원형 커서)

**애니메이션:**
- `TranslateAnimation` (200ms)
- 커서 위치 변경: Gravity.START ↔ Gravity.END

**주요 기능:**
- 상태 관리: 내부 `isOn` 변수
- 배경색 자동 변경: `updateOnOffState()` 메서드
- 커서 위치 자동 조정: `updateCursorView()` 메서드
- 애니메이션 완료 후 콜백 호출

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun SettingsScreen() {
    var isNotificationEnabled by remember { mutableStateOf(true) }

    val option = HongSwitchBuilder()
        .width(55)
        .height(30)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                top = 10f,
                bottom = 10f
            )
        )
        .onColor(HongColor.MAIN_ORANGE_100)
        .offColor(HongColor.GRAY_20)
        .cursorSize(25)
        .cursorHorizontalMargin(3)
        .cursorColor(HongColor.WHITE_100)
        .initialState(isNotificationEnabled)
        .switchClick { _, isEnabled ->
            isNotificationEnabled = isEnabled
            // 알림 설정 저장
        }
        .applyOption()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("알림 활성화")
        HongSwitchCompose(option)
    }
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:gravity="center_vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="다크 모드" />

    <com.codehong.library.widget.toggleswitch.HongSwitchView
        android:id="@+id/switchDarkMode"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>
```

**Kotlin 코드:**
```kotlin
class SettingsActivity : AppCompatActivity() {
    private var isDarkModeEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val switchView = findViewById<HongSwitchView>(R.id.switchDarkMode)

        val option = HongSwitchBuilder()
            .width(55)
            .height(30)
            .onColor(HongColor.MAIN_ORANGE_100)
            .offColor(HongColor.GRAY_20)
            .cursorSize(25)
            .cursorHorizontalMargin(3)
            .cursorColor(HongColor.WHITE_100)
            .initialState(isDarkModeEnabled)
            .switchClick { _, isEnabled ->
                isDarkModeEnabled = isEnabled
                // 다크 모드 적용
                applyDarkMode(isEnabled)
            }
            .applyOption()

        switchView.set(option)
    }

    private fun applyDarkMode(enabled: Boolean) {
        // 다크 모드 적용 로직
    }
}
```

### 커스텀 색상 및 크기

```kotlin
val option = HongSwitchBuilder()
    .width(60)
    .height(35)
    .onColor(HongColor.BLUE_100)
    .offColor(HongColor.GRAY_30)
    .cursorSize(30)
    .cursorHorizontalMargin(2)
    .cursorColor(HongColor.WHITE_100)
    .initialState(false)
    .switchClick { _, isEnabled ->
        // 상태 변경 처리
    }
    .applyOption()
```

### 작은 크기 스위치

```kotlin
val smallOption = HongSwitchBuilder()
    .width(40)
    .height(24)
    .onColor(HongColor.GREEN_100)
    .offColor(HongColor.GRAY_20)
    .cursorSize(20)
    .cursorHorizontalMargin(2)
    .cursorColor(HongColor.WHITE_100)
    .initialState(true)
    .applyOption()
```

### 테두리 추가

```kotlin
val option = HongSwitchBuilder()
    .width(55)
    .height(30)
    .onColor(HongColor.MAIN_ORANGE_100)
    .offColor(HongColor.GRAY_20)
    .cursorSize(25)
    .cursorHorizontalMargin(3)
    .cursorColor(HongColor.WHITE_100)
    .border(
        HongBorderInfo(
            width = 2,
            color = HongColor.BLACK_20.hex
        )
    )
    .initialState(false)
    .applyOption()
```

### 상태 관리 (Compose)

```kotlin
@Composable
fun NotificationSettings() {
    var isPushEnabled by remember { mutableStateOf(true) }
    var isEmailEnabled by remember { mutableStateOf(false) }
    var isSmsEnabled by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        SettingRow(
            label = "푸시 알림",
            isEnabled = isPushEnabled,
            onToggle = { isPushEnabled = it }
        )

        SettingRow(
            label = "이메일 알림",
            isEnabled = isEmailEnabled,
            onToggle = { isEmailEnabled = it }
        )

        SettingRow(
            label = "SMS 알림",
            isEnabled = isSmsEnabled,
            onToggle = { isSmsEnabled = it }
        )
    }
}

@Composable
fun SettingRow(
    label: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        HongSwitchCompose(
            HongSwitchBuilder()
                .initialState(isEnabled)
                .switchClick { _, enabled -> onToggle(enabled) }
                .applyOption()
        )
    }
}
```

## ⚙️ 주요 메서드

### HongSwitchBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `onColor(color)` | `HongColor` 또는 `String` | ON 상태 배경색 설정 |
| `offColor(color)` | `HongColor` 또는 `String` | OFF 상태 배경색 설정 |
| `cursorSize(size)` | `Int` | 커서 크기 (dp) 설정 (기본값: `25`) |
| `cursorHorizontalMargin(margin)` | `Int` | 커서 좌우 여백 (dp) 설정 (기본값: `3`) |
| `cursorColor(color)` | `HongColor?` 또는 `String` | 커서 색상 설정 |
| `initialState(isOn)` | `Boolean` | 초기 ON/OFF 상태 설정 (기본값: `false`) |
| `switchClick(callback)` | `((HongWidgetCommonOption, Boolean) -> Unit)?` | 스위치 전환 콜백 (위젯, 상태 전달) |
| `border(borderInfo)` | `HongBorderInfo` | 테두리 설정 |
| `copy(inject)` | `HongSwitchOption` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongSwitchView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongSwitchOption` | `HongSwitchView` | 옵션을 적용하여 뷰 구성 |

### HongSwitchCompose

| 파라미터 | 타입 | 설명 |
|---------|------|------|
| `option` | `HongSwitchOption` | 토글 스위치 옵션 |

## 📝 참고사항

### 레이아웃 구조
- **기본 크기:** 55dp × 30dp
- **커서 크기:** 25dp (원형)
- **커서 여백:** 좌우 각 3dp
- **배경:** 원형 모양 (`useShapeCircle: true`)
- **애니메이션:** 커서 슬라이딩 (200ms)

### 애니메이션
- **Compose:** `animateDpAsState` with `tween(200ms)`
- **View:** `TranslateAnimation` (200ms)
- **커서 이동 범위:** 0dp ~ (width - cursorSize - cursorHorizontalMargin * 2).dp

### 기본값
- 너비: 55dp (`DEFAULT_WIDTH`)
- 높이: 30dp (`DEFAULT_HEIGHT`)
- 커서 크기: 25dp (`DEFAULT_CURSOR_SIZE`)
- 커서 수평 마진: 3dp (`DEFAULT_CURSOR_HORIZONTAL_MARGIN`)
- ON 색상: `HongColor.MAIN_ORANGE_100` (주황색)
- OFF 색상: `HongColor.GRAY_20` (회색)
- 커서 색상: `HongColor.WHITE_100` (흰색)
- 초기 상태: `false` (OFF)
- 원형 모양: `true` (`useShapeCircle`)

### 주의사항
- `cursorSize`는 `height`보다 작아야 합니다 (권장: height - 5dp)
- `cursorHorizontalMargin`은 커서가 스위치 경계를 벗어나지 않도록 적절히 설정하세요
- Compose에서는 상태 관리를 `rememberSaveable`로 처리하여 화면 회전 시에도 상태가 유지됩니다
- View 시스템에서는 내부적으로 상태를 관리하므로, 외부에서 상태를 변경하려면 `set()`을 다시 호출해야 합니다
- `switchClick` 콜백의 두 번째 파라미터는 변경된 상태 (ON: `true`, OFF: `false`)를 전달합니다
- 커서는 항상 원형 (`CircleShape`)으로 표시됩니다
- 배경색은 ON/OFF 상태에 따라 자동으로 변경됩니다
- 애니메이션 진행 중에는 추가 클릭이 무시되지 않으므로, 빠른 연속 클릭 시 의도하지 않은 동작이 발생할 수 있습니다

### 의존성
- HongColor (디자인 시스템 색상)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)
- Jetpack Compose Animation (`animateDpAsState`, `updateTransition`)
- Android View Animation (`TranslateAnimation`)

### 사용 사례
- 설정 화면의 기능 토글 (알림, Wi-Fi, 블루투스 등)
- 다크 모드 전환
- 자동 재생 ON/OFF
- 푸시 알림 활성화/비활성화
- 위치 서비스 ON/OFF
- 소리/진동 모드 전환
- 데이터 세이버 모드
- 백업 자동 실행
- 개인정보 보호 설정
- 접근성 기능 활성화

### 크기별 권장 설정

```kotlin
// 작은 크기 (모바일 리스트 아이템)
.width(40)
.height(24)
.cursorSize(20)
.cursorHorizontalMargin(2)

// 기본 크기 (일반 설정 화면)
.width(55)
.height(30)
.cursorSize(25)
.cursorHorizontalMargin(3)

// 큰 크기 (태블릿, 강조 표시)
.width(70)
.height(40)
.cursorSize(36)
.cursorHorizontalMargin(2)
```

### 색상 조합 예시

```kotlin
// 기본 (주황색)
.onColor(HongColor.MAIN_ORANGE_100)
.offColor(HongColor.GRAY_20)

// 파란색 테마
.onColor(HongColor.BLUE_100)
.offColor(HongColor.GRAY_20)

// 녹색 (성공/활성화)
.onColor(HongColor.GREEN_100)
.offColor(HongColor.GRAY_20)

// 빨간색 (경고/위험)
.onColor(HongColor.RED_100)
.offColor(HongColor.GRAY_20)

// 다크 모드
.onColor(HongColor.MAIN_ORANGE_100)
.offColor(HongColor.GRAY_70)
.cursorColor(HongColor.GRAY_10)
```

### 상태 동기화 (Compose)

```kotlin
@Composable
fun SyncedSwitch() {
    var currentState by remember { mutableStateOf(false) }

    // currentState 변경 시 옵션 재생성
    val option = remember(currentState) {
        HongSwitchBuilder()
            .initialState(currentState)
            .switchClick { _, isEnabled ->
                currentState = isEnabled
            }
            .applyOption()
    }

    HongSwitchCompose(option)
}
```

### 외부 상태와 연동 (Compose)

```kotlin
@Composable
fun ExternalStateSwitch(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val option = remember(isEnabled) {
        HongSwitchBuilder()
            .initialState(isEnabled)
            .switchClick { _, enabled ->
                onToggle(enabled)
            }
            .applyOption()
    }

    HongSwitchCompose(option)
}

// 사용 예
@Composable
fun ParentScreen() {
    var darkModeEnabled by remember { mutableStateOf(false) }

    ExternalStateSwitch(
        isEnabled = darkModeEnabled,
        onToggle = { enabled ->
            darkModeEnabled = enabled
            applyDarkMode(enabled)
        }
    )
}
```

### 비교: HongSwitch vs Checkbox

| 특징 | HongSwitch | HongCheckBox |
|------|-----------|--------------|
| **UI 스타일** | 슬라이딩 토글 (iOS 스타일) | 체크마크 박스 |
| **시각적 피드백** | ON/OFF 색상 + 커서 이동 | 체크마크 표시/숨김 |
| **사용 사례** | 즉시 적용되는 설정 | 선택/미선택, 폼 제출 |
| **애니메이션** | 슬라이딩 (200ms) | 체크마크 페이드 |
| **크기** | 고정 (55×30dp) | 가변 (설정 가능) |

HongSwitch는 설정 화면에서 즉시 적용되는 기능 활성화/비활성화에 적합하며, HongCheckBox는 여러 항목을 선택한 후 한 번에 저장하는 시나리오에 적합합니다.

### 접근성 고려사항

```kotlin
// 충분한 클릭 영역 확보 (최소 48dp 권장)
.width(55)
.height(30)
.margin(HongSpacingInfo(top = 9f, bottom = 9f)) // 전체 높이 48dp

// 명확한 색상 대비
.onColor(HongColor.MAIN_ORANGE_100) // 밝은 주황색
.offColor(HongColor.GRAY_30) // 충분히 어두운 회색
.cursorColor(HongColor.WHITE_100) // 흰색 커서

// 라벨과 함께 사용
Row {
    Text("알림", fontSize = 16.sp)
    Spacer(modifier = Modifier.width(16.dp))
    HongSwitchCompose(option)
}
```

### 성능 최적화

```kotlin
@Composable
fun OptimizedSwitch() {
    var isEnabled by remember { mutableStateOf(false) }

    // 옵션을 remember로 캐싱 (isEnabled 변경 시에만 재생성)
    val option = remember(isEnabled) {
        HongSwitchBuilder()
            .initialState(isEnabled)
            .switchClick { _, enabled -> isEnabled = enabled }
            .applyOption()
    }

    HongSwitchCompose(option)
}
```

### SharedPreferences 연동

```kotlin
@Composable
fun PreferencesSwitch(
    context: Context,
    key: String,
    defaultValue: Boolean = false
) {
    val prefs = remember {
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    var isEnabled by remember {
        mutableStateOf(prefs.getBoolean(key, defaultValue))
    }

    HongSwitchCompose(
        HongSwitchBuilder()
            .initialState(isEnabled)
            .switchClick { _, enabled ->
                isEnabled = enabled
                prefs.edit().putBoolean(key, enabled).apply()
            }
            .applyOption()
    )
}

// 사용 예
PreferencesSwitch(
    context = LocalContext.current,
    key = "dark_mode_enabled",
    defaultValue = false
)
```
