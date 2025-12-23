# HongCheckBox

Android Compose 기반의 체크박스 위젯으로, 빌더 패턴을 통해 다양한 스타일의 체크박스를 쉽게 구현할 수 있습니다.

## 📋 개요

HongCheckBox는 Jetpack Compose와 기존 Android View 시스템 모두에서 사용 가능한 체크박스 위젯입니다. 체크 상태, 활성화/비활성화 상태에 따라 자동으로 색상과 스타일이 변경되며, 크기, 색상, 테두리, 모서리 둥글기 등 다양한 속성을 독립적으로 커스터마이징할 수 있습니다.

## 🏗️ 구조

```
checkbox/
├── HongCheckboxBuilder.kt      # 빌더 패턴 구현
├── HongCheckboxCompose.kt      # Compose 컴포저블
├── HongCheckboxOption.kt       # 옵션 데이터 클래스
└── HongCheckboxView.kt         # Android View 래퍼
```

## 📦 주요 클래스

### HongCheckboxOption

체크박스의 모든 속성을 정의하는 데이터 클래스입니다.

**주요 프로퍼티:**
- `size: Int` - 체크박스 크기 (기본값: 24)
- `checkedColorHex: String` - 체크된 상태의 배경 색상 (기본값: MAIN_ORANGE_100)
- `checkmarkColorHex: String` - 체크마크 색상 (기본값: WHITE_100)
- `checkState: Boolean` - 체크 상태 (기본값: false)
- `enableState: HongState` - 활성화 상태 (기본값: ENABLED)
- `border: HongBorderInfo` - 테두리 정보 (기본값: 2px, GRAY_40)
- `radius: HongRadiusInfo` - 모서리 둥글기 (기본값: 4dp)
- `useShapeCircle: Boolean` - 원형 모양 사용 여부
- `width: Int` - 너비 (기본값: WRAP_CONTENT)
- `height: Int` - 높이 (기본값: WRAP_CONTENT)
- `margin: HongSpacingInfo` - 외부 여백
- `padding: HongSpacingInfo` - 내부 여백
- `click: ((HongWidgetCommonOption) -> Unit)?` - 클릭 리스너

---

### HongCheckboxBuilder

빌더 패턴을 사용하여 체크박스 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `size(size: Int)` - 체크박스 크기 설정
- `checkedColor(color: HongColor)` - 체크된 상태의 배경 색상 설정 (HongColor)
- `checkedColor(colorHex: String)` - 체크된 상태의 배경 색상 설정 (Hex String)
- `checkmarkColor(color: HongColor)` - 체크마크 색상 설정 (HongColor)
- `checkmarkColor(colorHex: String)` - 체크마크 색상 설정 (Hex String)
- `border(borderInfo: HongBorderInfo)` - 테두리 설정
- `radius(radiusInfo: HongRadiusInfo)` - 모서리 둥글기 설정
- `checkState(checkState: Boolean)` - 체크 상태 설정
- `enableState(enableState: HongState)` - 활성화 상태 설정 (ENABLED/DISABLED)
- `useShapeCircle(useShapeCircle: Boolean)` - 원형 모양 사용 여부 설정
- `copy(inject: HongCheckboxOption)` - 기존 옵션을 복사하여 새로운 빌더 생성

---

### HongCheckBoxCompose

Jetpack Compose에서 체크박스를 렌더링하는 컴포저블 함수입니다.

```kotlin
@Composable
fun HongCheckBoxCompose(option: HongCheckboxOption)
```

**주요 기능:**
- **상태 관리**: `rememberSaveable`을 사용하여 체크 상태 및 활성화 상태 저장
- **자동 색상 변경**:
  - DISABLED 상태: 회색 배경(`GRAY_20`)과 회색 체크마크(`GRAY_40`)
  - 체크 상태: 지정된 체크 색상(`checkedColorHex`)으로 배경 및 테두리 변경
  - 미체크 상태: 투명 배경과 지정된 테두리 색상
- **체크마크 아이콘**: Material Icons의 `Icons.Default.Check` 사용
- **클릭 처리**: 활성화 상태일 때만 체크 상태 토글

---

### HongCheckboxView

기존 Android View 시스템에서 체크박스를 사용할 수 있도록 LinearLayout으로 변환하는 클래스입니다.

```kotlin
class HongCheckboxView(private val context: Context) {
    fun set(option: HongCheckboxOption): HongCheckboxView
    fun toggle()
    fun setChecked(value: Boolean)
    fun isChecked(): Boolean
}
```

**주요 기능:**
- LinearLayout 기반 (CENTER gravity)
- `AppCompatImageView`를 사용하여 체크마크 표시
- DISABLED 상태에서 클릭 비활성화
- Compose와 동일한 색상 변경 로직
- 프로그래밍 방식으로 체크 상태 변경 가능 (`toggle()`, `setChecked()`)

## 🚀 사용법

### 1. Jetpack Compose에서 사용

#### 기본 사용

```kotlin
@Composable
fun MyScreen() {
    val checkboxOption = HongCheckboxBuilder()
        .size(24)
        .checkedColor(HongColor.MAIN_ORANGE_100)
        .checkmarkColor(HongColor.WHITE_100)
        .checkState(false)
        .enableState(HongState.ENABLED)
        .border(
            HongBorderInfo(
                width = 2,
                color = HongColor.GRAY_40.hex
            )
        )
        .radius(HongRadiusInfo(all = 4))
        .applyOption()

    HongCheckBoxCompose(option = checkboxOption)
}
```

#### 체크된 상태의 체크박스

```kotlin
val checkedCheckbox = HongCheckboxBuilder()
    .size(24)
    .checkedColor(HongColor.MAIN_ORANGE_100)
    .checkmarkColor(HongColor.WHITE_100)
    .checkState(true)  // 체크된 상태
    .applyOption()

HongCheckBoxCompose(option = checkedCheckbox)
```

#### DISABLED 상태의 체크박스

```kotlin
val disabledCheckbox = HongCheckboxBuilder()
    .size(24)
    .enableState(HongState.DISABLED)  // 비활성화 상태
    .applyOption()

HongCheckBoxCompose(option = disabledCheckbox)
```

---

### 2. Android View에서 사용

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val checkboxOption = HongCheckboxBuilder()
            .size(24)
            .checkedColor(HongColor.MAIN_ORANGE_100)
            .checkmarkColor(HongColor.WHITE_100)
            .checkState(false)
            .border(
                HongBorderInfo(
                    width = 2,
                    color = HongColor.GRAY_40.hex
                )
            )
            .radius(HongRadiusInfo(all = 4))
            .applyOption()

        val checkboxView = HongCheckboxView(this).set(checkboxOption)

        val container = findViewById<FrameLayout>(R.id.container)
        container.addView(checkboxView)
    }
}
```

#### 프로그래밍 방식으로 체크 상태 변경

```kotlin
val checkboxView = HongCheckboxView(this).set(checkboxOption)

// 체크 상태 토글
checkboxView.toggle()

// 체크 상태 설정
checkboxView.setChecked(true)

// 체크 상태 확인
val isChecked = checkboxView.isChecked()
```

---

### 3. 원형 체크박스

```kotlin
val circleCheckbox = HongCheckboxBuilder()
    .size(24)
    .checkedColor(HongColor.BLUE_500)
    .checkmarkColor(HongColor.WHITE_100)
    .useShapeCircle(true)  // 원형 모양
    .border(
        HongBorderInfo(
            width = 2,
            color = HongColor.BLUE_500.hex
        )
    )
    .applyOption()

HongCheckBoxCompose(option = circleCheckbox)
```

---

### 4. 커스텀 색상 체크박스

```kotlin
val customCheckbox = HongCheckboxBuilder()
    .size(28)
    .checkedColor(HongColor.GREEN_500)  // 초록색 배경
    .checkmarkColor(HongColor.WHITE_100)  // 흰색 체크마크
    .border(
        HongBorderInfo(
            width = 2,
            color = HongColor.GREEN_500.hex
        )
    )
    .radius(HongRadiusInfo(all = 6))
    .applyOption()

HongCheckBoxCompose(option = customCheckbox)
```

---

### 5. 큰 크기의 체크박스

```kotlin
val largeCheckbox = HongCheckboxBuilder()
    .size(32)  // 큰 크기
    .checkedColor(HongColor.MAIN_ORANGE_100)
    .checkmarkColor(HongColor.WHITE_100)
    .border(
        HongBorderInfo(
            width = 3,
            color = HongColor.GRAY_40.hex
        )
    )
    .radius(HongRadiusInfo(all = 8))
    .applyOption()

HongCheckBoxCompose(option = largeCheckbox)
```

---

### 6. 기존 옵션 복사하여 수정

```kotlin
val originalCheckbox = HongCheckboxBuilder()
    .size(24)
    .checkedColor(HongColor.MAIN_ORANGE_100)
    .checkmarkColor(HongColor.WHITE_100)
    .applyOption()

// 기존 체크박스를 복사하여 크기와 색상만 변경
val modifiedCheckbox = HongCheckboxBuilder()
    .copy(originalCheckbox)
    .size(28)
    .checkedColor(HongColor.BLUE_500)
    .applyOption()

Column {
    HongCheckBoxCompose(option = originalCheckbox)
    Spacer(modifier = Modifier.height(16.dp))
    HongCheckBoxCompose(option = modifiedCheckbox)
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `size(Int)` | 체크박스 크기 설정 |
| `checkedColor(HongColor/String)` | 체크된 상태의 배경 색상 설정 |
| `checkmarkColor(HongColor/String)` | 체크마크 색상 설정 |
| `border(HongBorderInfo)` | 테두리 설정 |
| `radius(HongRadiusInfo)` | 모서리 둥글기 설정 |
| `checkState(Boolean)` | 체크 상태 설정 |
| `enableState(HongState)` | 활성화 상태 설정 (ENABLED/DISABLED) |
| `useShapeCircle(Boolean)` | 원형 모양 사용 여부 설정 |
| `copy(HongCheckboxOption)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 📝 참고사항

- **기본 크기**: 기본 체크박스 크기는 24dp입니다.
- **자동 색상 변경**: 체크 상태와 활성화 상태에 따라 배경 색상과 테두리 색상이 자동으로 변경됩니다.
- **DISABLED 상태**:
  - 배경 색상: `GRAY_20`
  - 체크마크 색상: `GRAY_40`
  - 클릭 이벤트: 차단됨
- **체크마크 크기**: 체크마크는 체크박스 크기의 90% 크기로 자동 조정됩니다.
- **상태 관리**: Compose에서는 `rememberSaveable`을 사용하여 상태를 자동으로 저장하고 복원합니다.
- **기본 테두리**: 기본 테두리는 2px 너비에 `GRAY_40` 색상입니다.
- **기본 둥글기**: 기본 모서리 둥글기는 4dp입니다.
- **원형 모양**: `useShapeCircle(true)` 설정 시 완전한 원형 체크박스가 됩니다.
- **내부 구현**: Compose에서는 Material Icons의 `Check` 아이콘을 사용하고, View에서는 `R.drawable.honglib_ic_check` 리소스를 사용합니다.
- **빌더 패턴**: 메서드 체이닝을 통해 유연하게 옵션을 구성할 수 있습니다.
- **Compose와 View 호환**: Compose와 View 시스템 모두에서 사용 가능하여 점진적 마이그레이션에 적합합니다.
- **프로그래밍 제어**: View 버전에서는 `toggle()`, `setChecked()`, `isChecked()` 메서드를 통해 프로그래밍 방식으로 체크 상태를 제어할 수 있습니다.
- **용도**: 약관 동의, 옵션 선택, 필터링 UI 등 다양한 상황에서 활용 가능합니다.
