# HongLabelSwitch

라벨, 설명, 토글 스위치가 결합된 설정 전환 위젯입니다.

## 📋 개요

HongLabelSwitch는 주 라벨, 부가 설명, 토글 스위치를 하나의 컴포넌트로 결합한 설정 전환 위젯입니다. 설정 화면에서 기능 활성화/비활성화를 제어할 때 주로 사용되며, 라벨과 스위치가 수평으로 배치되어 직관적인 UI를 제공합니다. 빌더 패턴을 통해 라벨, 설명, 스위치 스타일을 쉽게 커스터마이징할 수 있으며, Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
label/toggleswitch/
├── HongLabelSwitchBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongLabelSwitchOption.kt        # 옵션 데이터 클래스
├── HongLabelSwitchCompose.kt       # Compose용 컴포넌트
└── HongLabelSwitchView.kt          # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongLabelSwitchBuilder
빌더 패턴을 사용하여 라벨 스위치의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 라벨 설정 (`label`, `labelColor`, `labelTypo`)
- 설명 설정 (`description`, `descriptionColor`, `descriptionTypo`)
- 스위치 옵션 설정 (`switchOption`)
- 옵션 복사 (`copy`)

### HongLabelSwitchOption
라벨 스위치의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `label`: 주 라벨 텍스트
- `labelColorHex`: 라벨 색상 (기본값: `HongColor.BLACK_100`)
- `labelTypo`: 라벨 타이포그래피 (기본값: `HongTypo.BODY_15_B`)
- `description`: 부가 설명 텍스트
- `descriptionColorHex`: 설명 색상 (기본값: `HongColor.BLACK_60`)
- `descriptionTypo`: 설명 타이포그래피 (기본값: `HongTypo.CONTENTS_10`)
- `switchOption`: 스위치 상세 옵션

**기본 스위치 스타일:**
- 너비: 55dp
- 높이: 30dp
- ON 상태 색상: `HongColor.MAIN_ORANGE_100` (주황색)
- OFF 상태 색상: `HongColor.GRAY_20` (회색)
- 커서 크기: 25dp
- 커서 수평 마진: 3dp
- 커서 색상: `HongColor.WHITE_100` (흰색)

### HongLabelSwitchCompose
Jetpack Compose에서 사용할 수 있는 라벨 스위치 컴포넌트입니다.

**구조:**
- Row 레이아웃 (수평 정렬, 세로 중앙 정렬)
- Box (weight=1f, 라벨 영역)
  - HongLabel (주 라벨 + 부가 설명)
- Spacer (5dp)
- HongSwitch (토글 스위치)

### HongLabelSwitchView
기존 View 시스템에서 사용할 수 있는 커스텀 LinearLayout 뷰입니다.

**구조:**
- Horizontal LinearLayout (세로 중앙 정렬)
- FrameLayout (weight=1f, 라벨 영역, 5dp 오른쪽 마진)
  - HongLabelView (주 라벨 + 부가 설명)
- HongSwitchView (토글 스위치)

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun SettingsScreen() {
    var isNotificationEnabled by remember { mutableStateOf(true) }

    val option = HongLabelSwitchBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("알림 활성화")
        .description("푸시 알림을 받습니다")
        .switchOption(
            HongSwitchBuilder()
                .width(55)
                .height(30)
                .onColor(HongColor.MAIN_ORANGE_100)
                .offColor(HongColor.GRAY_20)
                .cursorSize(25)
                .cursorHorizontalMargin(3)
                .cursorColor(HongColor.WHITE_100)
                .initialState(isNotificationEnabled)
                .switchClick { _, isEnabled ->
                    isNotificationEnabled = isEnabled
                }
                .applyOption()
        )
        .applyOption()

    HongLabelSwitchCompose(option = option)
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<com.codehong.library.widget.label.toggleswitch.HongLabelSwitchView
    android:id="@+id/labelSwitch"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isAutoPlayEnabled = false

        val option = HongLabelSwitchBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f,
                    top = 10f,
                    bottom = 10f
                )
            )
            .label("자동 재생")
            .description("동영상을 자동으로 재생합니다")
            .switchOption(
                HongSwitchBuilder()
                    .width(55)
                    .height(30)
                    .onColor(HongColor.MAIN_ORANGE_100)
                    .offColor(HongColor.GRAY_20)
                    .cursorSize(25)
                    .cursorHorizontalMargin(3)
                    .cursorColor(HongColor.WHITE_100)
                    .initialState(isAutoPlayEnabled)
                    .switchClick { _, isEnabled ->
                        isAutoPlayEnabled = isEnabled
                        // 자동 재생 설정 저장
                    }
                    .applyOption()
            )
            .applyOption()

        findViewById<HongLabelSwitchView>(R.id.labelSwitch)
            .set(option)
    }
}
```

### 커스텀 스위치 스타일

```kotlin
val customSwitchOption = HongLabelSwitchBuilder()
    .label("다크 모드")
    .description("어두운 테마를 사용합니다")
    .switchOption(
        HongSwitchBuilder()
            .width(60)
            .height(35)
            .onColor(HongColor.BLUE_100)
            .offColor(HongColor.GRAY_30)
            .cursorSize(30)
            .cursorHorizontalMargin(2)
            .cursorColor(HongColor.WHITE_100)
            .initialState(false)
            .switchClick { _, isEnabled ->
                // 다크 모드 전환
            }
            .applyOption()
    )
    .applyOption()
```

### 설명 없이 사용

```kotlin
val simpleOption = HongLabelSwitchBuilder()
    .label("Wi-Fi")
    .switchOption(
        HongSwitchBuilder()
            .initialState(true)
            .switchClick { _, isEnabled ->
                // Wi-Fi 전환
            }
            .applyOption()
    )
    .applyOption()
```

## ⚙️ 주요 메서드

### HongLabelSwitchBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `label(label)` | `String?` | 주 라벨 텍스트 설정 |
| `labelColor(color)` | `HongColor` 또는 `String` | 라벨 색상 설정 |
| `labelTypo(typo)` | `HongTypo` | 라벨 타이포그래피 설정 |
| `description(description)` | `String?` | 부가 설명 텍스트 설정 |
| `descriptionColor(color)` | `HongColor` 또는 `String` | 설명 색상 설정 |
| `descriptionTypo(typo)` | `HongTypo` | 설명 타이포그래피 설정 |
| `switchOption(option)` | `HongSwitchOption?` | 스위치 상세 옵션 설정 |
| `copy(inject)` | `HongLabelSwitchOption?` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongLabelSwitchView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongLabelSwitchOption` | `HongLabelSwitchView` | 옵션을 적용하여 뷰 구성 |

## 📝 참고사항

### 레이아웃 구조
- **전체 크기:** MATCH_PARENT × WRAP_CONTENT (기본값)
- **배치:** 수평 방향 (Row/Horizontal LinearLayout)
- **정렬:** 세로 중앙 정렬
- **라벨-스위치 간격:** 5dp
- **라벨 영역:** weight=1f (나머지 공간 차지)
- **스위치 영역:** 고정 크기

### 기본값
- 라벨 타이포그래피: `HongTypo.BODY_15_B`
- 라벨 색상: `HongColor.BLACK_100` (#000000)
- 설명 타이포그래피: `HongTypo.CONTENTS_10`
- 설명 색상: `HongColor.BLACK_60` (60% 불투명도)
- 배경 색상: `HongColor.WHITE_100` (#FFFFFF)

### 스위치 기본값
- 너비: 55dp
- 높이: 30dp
- ON 색상: `HongColor.MAIN_ORANGE_100` (주황색)
- OFF 색상: `HongColor.GRAY_20` (회색)
- 커서 크기: 25dp
- 커서 수평 마진: 3dp
- 커서 색상: `HongColor.WHITE_100` (흰색)

### 주의사항
- `label`이 null이거나 빈 문자열인 경우에도 스위치는 표시됩니다
- `description`이 null이거나 빈 문자열인 경우: 설명 텍스트가 표시되지 않습니다
- View 시스템에서 설정을 변경하려면 `set()` 메서드를 다시 호출해야 합니다
- Compose에서는 상태 관리를 별도로 구현해야 합니다 (remember, mutableStateOf 등)
- 스위치의 상태 변경은 `switchClick` 콜백을 통해 처리됩니다
- `switchOption`을 설정하지 않으면 기본 스위치 스타일이 적용됩니다

### 의존성
- HongLabel (라벨 위젯)
- HongSwitch (토글 스위치 위젯)
- HongColor, HongTypo (디자인 시스템)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)

### 사용 사례
- 설정 화면의 기능 토글 (알림, 자동 재생, Wi-Fi 등)
- 권한 활성화/비활성화
- 기능 플래그 (Feature Flag)
- 개인정보 설정 (위치 공유, 데이터 수집 등)
- 접근성 설정 (고대비 모드, 자막 등)
- 앱 환경 설정 (다크 모드, 언어, 화면 회전 등)
- 알림 설정 (푸시, 이메일, SMS 등)

### 상태 관리 예시 (Compose)

```kotlin
@Composable
fun NotificationSettingsScreen() {
    var isPushEnabled by remember { mutableStateOf(true) }
    var isEmailEnabled by remember { mutableStateOf(false) }
    var isSmsEnabled by remember { mutableStateOf(false) }

    Column {
        HongLabelSwitchCompose(
            HongLabelSwitchBuilder()
                .label("푸시 알림")
                .description("실시간 푸시 알림을 받습니다")
                .switchOption(
                    HongSwitchBuilder()
                        .initialState(isPushEnabled)
                        .switchClick { _, isEnabled ->
                            isPushEnabled = isEnabled
                        }
                        .applyOption()
                )
                .applyOption()
        )

        HongLabelSwitchCompose(
            HongLabelSwitchBuilder()
                .label("이메일 알림")
                .description("이메일로 알림을 받습니다")
                .switchOption(
                    HongSwitchBuilder()
                        .initialState(isEmailEnabled)
                        .switchClick { _, isEnabled ->
                            isEmailEnabled = isEnabled
                        }
                        .applyOption()
                )
                .applyOption()
        )

        HongLabelSwitchCompose(
            HongLabelSwitchBuilder()
                .label("SMS 알림")
                .description("문자 메시지로 알림을 받습니다")
                .switchOption(
                    HongSwitchBuilder()
                        .initialState(isSmsEnabled)
                        .switchClick { _, isEnabled ->
                            isSmsEnabled = isEnabled
                        }
                        .applyOption()
                )
                .applyOption()
        )
    }
}
```

### 비교: HongLabelSwitch vs HongLabelCheckbox
- **HongLabelSwitch**: 라벨 + 토글 스위치 (즉시 적용되는 설정 전환)
- **HongLabelCheckbox**: 라벨 + 체크박스 (선택/미선택 상태 표시, 폼 제출 시 적용)

HongLabelSwitch는 설정 화면에서 즉시 적용되는 기능 활성화/비활성화에 적합하며, HongLabelCheckbox는 여러 항목을 선택한 후 한 번에 저장하는 시나리오에 적합합니다.

### 스위치 콜백

```kotlin
val option = HongLabelSwitchBuilder()
    .label("블루투스")
    .switchOption(
        HongSwitchBuilder()
            .initialState(false)
            .switchClick { switchView, isEnabled ->
                // switchView: HongSwitchView 또는 HongSwitchCompose 참조
                // isEnabled: 변경된 스위치 상태 (true/false)

                if (isEnabled) {
                    // 블루투스 활성화
                } else {
                    // 블루투스 비활성화
                }
            }
            .applyOption()
    )
    .applyOption()
```
