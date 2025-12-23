# HongButtonText

Android Compose 기반의 텍스트 버튼 위젯으로, 빌더 패턴을 통해 다양한 스타일의 버튼을 쉽게 구현할 수 있습니다.

## 📋 개요

HongButtonText는 Jetpack Compose와 기존 Android View 시스템 모두에서 사용 가능한 텍스트 버튼 위젯입니다. 텍스트, 색상, 타이포그래피, 배경, 테두리, 그림자 등 다양한 속성을 독립적으로 커스터마이징할 수 있으며, ENABLED/DISABLED 상태를 지원합니다.

## 🏗️ 구조

```
button/text/
├── HongButtonTextBuilder.kt      # 빌더 패턴 구현
├── HongButtonTextCompose.kt      # Compose 컴포저블
├── HongButtonTextOption.kt       # 옵션 데이터 클래스
└── HongButtonTextView.kt         # Android View 래퍼
```

## 📦 주요 클래스

### HongButtonTextOption

텍스트 버튼의 모든 속성을 정의하는 데이터 클래스입니다.

**주요 프로퍼티:**
- `text: String` - 버튼 텍스트 (기본값: "")
- `textTypo: HongTypo` - 텍스트 타이포그래피 (기본값: BODY_16_B)
- `textColorHex: String` - 텍스트 색상 (기본값: WHITE_100)
- `state: HongState` - 버튼 상태 (기본값: ENABLED)
- `backgroundColorHex: String` - 배경 색상 (기본값: TRANSPARENT)
- `border: HongBorderInfo` - 테두리 정보
- `radius: HongRadiusInfo` - 모서리 둥글기 정보
- `shadow: HongShadowInfo` - 그림자 정보
- `width: Int` - 버튼 너비 (기본값: WRAP_CONTENT)
- `height: Int` - 버튼 높이 (기본값: WRAP_CONTENT)
- `margin: HongSpacingInfo` - 외부 여백
- `padding: HongSpacingInfo` - 내부 여백
- `click: ((HongWidgetCommonOption) -> Unit)?` - 클릭 리스너

**상수:**
- `DEFAULT_DISABLE_BACKGROUND_COLOR` - DISABLED 상태의 기본 배경 색상 (GRAY_70)

---

### HongButtonTextBuilder

빌더 패턴을 사용하여 텍스트 버튼 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `text(text: String)` - 버튼 텍스트 설정
- `textTypo(typo: HongTypo)` - 텍스트 타이포그래피 설정
- `textColor(color: HongColor)` - 텍스트 색상 설정 (HongColor)
- `textColor(colorHex: String)` - 텍스트 색상 설정 (Hex String)
- `state(state: HongState)` - 버튼 상태 설정 (ENABLED/DISABLED)
- `radius(radiusInfo: HongRadiusInfo)` - 모서리 둥글기 설정
- `border(border: HongBorderInfo)` - 테두리 설정
- `shadow(shadowInfo: HongShadowInfo?)` - 그림자 설정
- `copy(inject: HongButtonTextOption?)` - 기존 옵션을 복사하여 새로운 빌더 생성

---

### HongButtonTextCompose

Jetpack Compose에서 텍스트 버튼을 렌더링하는 컴포저블 함수입니다.

```kotlin
@Composable
fun HongButtonTextCompose(option: HongButtonTextOption)
```

**주요 기능:**
- **DISABLED 상태**: 회색 배경(`GRAY_70`)과 반투명 흰색 텍스트(`WHITE_60`)로 표시
- **ENABLED 상태**: 사용자 정의 스타일 적용
- 텍스트는 항상 중앙 정렬
- 내부적으로 `HongTextCompose`를 사용하여 텍스트 렌더링

---

### HongButtonTextView

기존 Android View 시스템에서 텍스트 버튼을 사용할 수 있도록 LinearLayout으로 변환하는 클래스입니다.

```kotlin
class HongButtonTextView(private val context: Context) {
    fun set(option: HongButtonTextOption): LinearLayout
}
```

**주요 기능:**
- LinearLayout 기반 (VERTICAL orientation, CENTER gravity)
- DISABLED 상태에서 `isEnabled = false` 설정
- Compose와 동일한 스타일 적용
- 텍스트 한 줄 표시 (maxLines = 1)

## 🚀 사용법

### 1. Jetpack Compose에서 사용

#### 기본 사용

```kotlin
@Composable
fun MyScreen() {
    val buttonOption = HongButtonTextBuilder()
        .text("검색하기")
        .textTypo(HongTypo.BODY_15_B)
        .textColor(HongColor.WHITE_100)
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .radius(
            HongRadiusInfo(all = 12)
        )
        .onClick {
            Log.d("Button", "버튼 클릭됨")
        }
        .applyOption()

    HongButtonTextCompose(option = buttonOption)
}
```

#### DISABLED 상태 버튼

```kotlin
val disabledButton = HongButtonTextBuilder()
    .text("비활성화 버튼")
    .textTypo(HongTypo.BODY_15_B)
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(48)
    .radius(HongRadiusInfo(all = 12))
    .state(HongState.DISABLED)
    .applyOption()

HongButtonTextCompose(option = disabledButton)
```

---

### 2. Android View에서 사용

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonOption = HongButtonTextBuilder()
            .text("확인")
            .textTypo(HongTypo.BODY_15_B)
            .textColor(HongColor.WHITE_100)
            .backgroundColor(HongColor.BLUE_500.hex)
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .radius(HongRadiusInfo(all = 8))
            .onClick {
                Toast.makeText(this, "버튼 클릭됨", Toast.LENGTH_SHORT).show()
            }
            .applyOption()

        val buttonView = HongButtonTextView(this).set(buttonOption)

        val container = findViewById<FrameLayout>(R.id.container)
        container.addView(buttonView)
    }
}
```

---

### 3. 테두리가 있는 버튼

```kotlin
val borderedButton = HongButtonTextBuilder()
    .text("취소")
    .textTypo(HongTypo.BODY_15_B)
    .textColor(HongColor.MAIN_ORANGE_100)
    .backgroundColor(HongColor.TRANSPARENT.hex)
    .border(
        HongBorderInfo(
            width = 1,
            color = HongColor.MAIN_ORANGE_100.hex
        )
    )
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(48)
    .radius(HongRadiusInfo(all = 12))
    .applyOption()

HongButtonTextCompose(option = borderedButton)
```

---

### 4. 그림자가 있는 버튼

```kotlin
val shadowButton = HongButtonTextBuilder()
    .text("저장")
    .textTypo(HongTypo.BODY_16_B)
    .textColor(HongColor.WHITE_100)
    .backgroundColor(HongColor.GREEN_500.hex)
    .shadow(
        HongShadowInfo(
            offsetX = 0f,
            offsetY = 4f,
            blurRadius = 8f,
            color = "#33000000"
        )
    )
    .width(200)
    .height(48)
    .radius(HongRadiusInfo(all = 10))
    .applyOption()

HongButtonTextCompose(option = shadowButton)
```

---

### 5. 기존 옵션 복사하여 수정

```kotlin
val originalButton = HongButtonTextBuilder()
    .text("원본 버튼")
    .textColor(HongColor.WHITE_100)
    .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(48)
    .applyOption()

// 기존 버튼을 복사하여 텍스트와 배경색만 변경
val modifiedButton = HongButtonTextBuilder()
    .copy(originalButton)
    .text("수정된 버튼")
    .backgroundColor(HongColor.BLUE_500.hex)
    .applyOption()

Column {
    HongButtonTextCompose(option = originalButton)
    Spacer(modifier = Modifier.height(16.dp))
    HongButtonTextCompose(option = modifiedButton)
}
```

---

### 6. 여백이 있는 버튼

```kotlin
val buttonWithMargin = HongButtonTextBuilder()
    .text("다음")
    .textTypo(HongTypo.BODY_15_B)
    .textColor(HongColor.WHITE_100)
    .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(48)
    .margin(
        HongSpacingInfo(
            top = 20f,
            left = 20f,
            right = 20f,
            bottom = 20f
        )
    )
    .radius(HongRadiusInfo(all = 12))
    .applyOption()

HongButtonTextCompose(option = buttonWithMargin)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `text(String)` | 버튼 텍스트 설정 |
| `textTypo(HongTypo)` | 텍스트 타이포그래피 설정 |
| `textColor(HongColor/String)` | 텍스트 색상 설정 |
| `state(HongState)` | 버튼 상태 설정 (ENABLED/DISABLED) |
| `radius(HongRadiusInfo)` | 모서리 둥글기 설정 |
| `border(HongBorderInfo)` | 테두리 설정 |
| `shadow(HongShadowInfo?)` | 그림자 설정 |
| `backgroundColor(String)` | 배경 색상 설정 |
| `width(Int)` | 버튼 너비 설정 |
| `height(Int)` | 버튼 높이 설정 |
| `margin(HongSpacingInfo)` | 외부 여백 설정 |
| `padding(HongSpacingInfo)` | 내부 여백 설정 |
| `onClick(() -> Unit)` | 클릭 리스너 설정 |
| `copy(HongButtonTextOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 📝 참고사항

- **기본 크기**: 너비와 높이는 기본적으로 WRAP_CONTENT로 설정됩니다.
- **텍스트 정렬**: 텍스트는 항상 중앙 정렬됩니다.
- **DISABLED 상태**:
  - 배경 색상: `GRAY_70`
  - 텍스트 색상: `WHITE_60`
  - 테두리: 제거됨
  - 클릭 이벤트: 차단됨 (View에서 `isEnabled = false`)
- **텍스트 라인**: View 시스템에서는 텍스트가 한 줄로 제한됩니다 (`maxLines = 1`).
- **투명 배경**: 기본 배경 색상은 투명(`TRANSPARENT`)입니다.
- **내부 구현**: 내부적으로 `HongTextCompose`를 사용하여 텍스트를 렌더링합니다.
- **빌더 패턴**: 메서드 체이닝을 통해 유연하게 옵션을 구성할 수 있습니다.
- **Compose와 View 호환**: Compose와 View 시스템 모두에서 사용 가능하여 점진적 마이그레이션에 적합합니다.
- **상태 관리**: ENABLED와 DISABLED 상태를 통해 버튼의 활성화/비활성화를 쉽게 관리할 수 있습니다.
- **용도**: CTA 버튼, 제출 버튼, 액션 버튼 등 다양한 상황에서 활용 가능합니다.
