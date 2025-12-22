# HongButtonIcon

Android Compose 기반의 아이콘 버튼 위젯으로, 빌더 패턴을 통해 버튼 크기, 아이콘 색상, 상태(활성/비활성) 등을 쉽게 설정할 수 있습니다.

## 📋 개요

HongButtonIcon은 Jetpack Compose와 기존 Android View 시스템 모두에서 사용 가능한 아이콘 버튼 위젯입니다. 버튼 상태에 따라 자동으로 색상이 변경되며, 다양한 크기 옵션과 원형/사각형 모양을 지원합니다.

## 🏗️ 구조

```
button/icon/
├── HongButtonIconBuilder.kt      # 빌더 패턴 구현
├── HongButtonIconCompose.kt      # Compose 컴포저블
├── HongButtonIconOption.kt       # 옵션 데이터 클래스
└── HongButtonIconView.kt         # Android View 래퍼
```

## 📦 주요 클래스

### HongButtonIconOption

아이콘 버튼의 모든 속성을 정의하는 데이터 클래스입니다.

**주요 프로퍼티:**
- `buttonType: HongButtonIconType` - 버튼 크기 타입 (기본값: SIZE_28)
- `iconResId: Int` - 아이콘 리소스 ID
- `iconColorHex: String` - 아이콘 색상 (Hex 코드)
- `state: HongClickState` - 버튼 상태 (ENABLE/DISABLE, 기본값: ENABLE)
- `useShapeCircle: Boolean` - 원형 버튼 사용 여부 (기본값: true)
- `border: HongBorderInfo` - 테두리 정보 (기본값: 1px, GRAY_40)
- `backgroundColorHex: String` - 배경 색상 (기본값: WHITE_100)

### HongButtonIconBuilder

빌더 패턴을 사용하여 아이콘 버튼 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `buttonType(type: HongButtonIconType)` - 버튼 크기 설정
- `iconResId(resId: Int)` - 아이콘 리소스 설정
- `iconColor(color: HongColor)` - 아이콘 색상 설정 (HongColor)
- `iconColor(colorHex: String)` - 아이콘 색상 설정 (Hex)
- `state(state: HongClickState)` - 버튼 상태 설정 (ENABLE/DISABLE)
- `border(border: HongBorderInfo)` - 테두리 설정
- `radius(radiusInfo: HongRadiusInfo)` - 모서리 둥글기 설정
- `useShapeCircle(useShapeCircle: Boolean)` - 원형 버튼 사용 여부 설정
- `copy(inject: HongButtonIconOption?)` - 기존 옵션 복사

### HongButtonIconCompose

Jetpack Compose에서 아이콘 버튼을 렌더링하는 컴포저블 함수입니다.

```kotlin
@Composable
fun HongButtonIconCompose(option: HongButtonIconOption)
```

**주요 기능:**
- 버튼 상태(DISABLE)에 따라 자동으로 색상 변경
- 버튼 크기에 따라 아이콘 크기와 패딩 자동 조정
- 원형 또는 사각형 버튼 모양 지원

### HongButtonIconView

기존 Android View 시스템에서 아이콘 버튼을 사용할 수 있도록 ComposeView로 변환하는 클래스입니다.

```kotlin
class HongButtonIconView(private val context: Context) {
    fun set(option: HongButtonIconOption): ComposeView
}
```

## 🚀 사용법

### 1. Jetpack Compose에서 사용

```kotlin
@Composable
fun MyScreen() {
    val buttonOption = HongButtonIconBuilder()
        .buttonType(HongButtonIconType.SIZE_48)
        .iconResId(R.drawable.ic_close)
        .iconColor(HongColor.BLACK_100)
        .state(HongClickState.ENABLE)
        .onClick { option ->
            Log.d("Button", "Close button clicked!")
        }
        .applyOption()

    HongButtonIconCompose(option = buttonOption)
}
```

### 2. Android View에서 사용

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonOption = HongButtonIconBuilder()
            .buttonType(HongButtonIconType.SIZE_40)
            .iconResId(R.drawable.ic_settings)
            .iconColor("#333333")
            .onClick { option ->
                // 클릭 이벤트 처리
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            }
            .applyOption()

        val buttonView = HongButtonIconView(this).set(buttonOption)

        val container = findViewById<FrameLayout>(R.id.container)
        container.addView(buttonView)
    }
}
```

### 3. 버튼 상태 변경 (활성/비활성)

```kotlin
// 활성 상태 버튼
val enabledButton = HongButtonIconBuilder()
    .buttonType(HongButtonIconType.SIZE_32)
    .iconResId(R.drawable.ic_edit)
    .iconColor(HongColor.BLUE_500)
    .state(HongClickState.ENABLE)
    .applyOption()

// 비활성 상태 버튼 (자동으로 회색으로 표시됨)
val disabledButton = HongButtonIconBuilder()
    .buttonType(HongButtonIconType.SIZE_32)
    .iconResId(R.drawable.ic_edit)
    .iconColor(HongColor.BLUE_500)
    .state(HongClickState.DISABLE)
    .applyOption()

Column {
    HongButtonIconCompose(option = enabledButton)
    Spacer(modifier = Modifier.height(8.dp))
    HongButtonIconCompose(option = disabledButton)
}
```

### 4. 사각형 버튼 사용

```kotlin
val squareButton = HongButtonIconBuilder()
    .buttonType(HongButtonIconType.SIZE_48)
    .iconResId(R.drawable.ic_menu)
    .iconColor(HongColor.BLACK_100)
    .useShapeCircle(false) // 원형이 아닌 사각형 버튼
    .radius(HongRadiusInfo(all = 8)) // 모서리 둥글기
    .applyOption()

HongButtonIconCompose(option = squareButton)
```

### 5. 커스텀 스타일 버튼

```kotlin
val customButton = HongButtonIconBuilder()
    .buttonType(HongButtonIconType.SIZE_56)
    .iconResId(R.drawable.ic_favorite)
    .iconColor(HongColor.RED_500)
    .backgroundColor(HongColor.RED_50)
    .border(HongBorderInfo(width = 2, color = HongColor.RED_500.hex))
    .margin(HongSpacingInfo(all = 16))
    .onClick { option ->
        Log.d("Button", "Favorite clicked!")
    }
    .applyOption()

HongButtonIconCompose(option = customButton)
```

### 6. 기존 옵션 복사하여 수정

```kotlin
val originalButton = HongButtonIconBuilder()
    .buttonType(HongButtonIconType.SIZE_40)
    .iconResId(R.drawable.ic_share)
    .iconColor(HongColor.BLUE_500)
    .applyOption()

// 기존 버튼을 복사하여 색상만 변경
val modifiedButton = HongButtonIconBuilder()
    .copy(originalButton)
    .iconColor(HongColor.GREEN_500)
    .applyOption()

Row {
    HongButtonIconCompose(option = originalButton)
    Spacer(modifier = Modifier.width(8.dp))
    HongButtonIconCompose(option = modifiedButton)
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `buttonType(HongButtonIconType)` | 버튼 크기 타입 설정 (SIZE_28, SIZE_32, SIZE_40, SIZE_48, SIZE_56) |
| `iconResId(Int)` | 아이콘 리소스 ID 설정 |
| `iconColor(HongColor)` | 아이콘 색상 설정 (HongColor 객체) |
| `iconColor(String)` | 아이콘 색상 설정 (Hex 문자열) |
| `state(HongClickState)` | 버튼 상태 설정 (ENABLE/DISABLE) |
| `border(HongBorderInfo)` | 테두리 설정 (두께, 색상) |
| `radius(HongRadiusInfo)` | 모서리 둥글기 설정 |
| `useShapeCircle(Boolean)` | 원형 버튼 사용 여부 설정 |
| `backgroundColor(HongColor/String)` | 배경 색상 설정 |
| `margin(HongSpacingInfo)` | 외부 여백 설정 |
| `padding(HongSpacingInfo)` | 내부 여백 설정 |
| `onClick(람다)` | 클릭 이벤트 리스너 설정 |
| `copy(HongButtonIconOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 📝 참고사항

- **자동 상태 관리**: `state`가 `DISABLE`일 때 아이콘과 배경, 테두리 색상이 자동으로 회색으로 변경됩니다.
- **반응형 크기 조정**: `buttonType`에 따라 버튼 크기, 아이콘 크기, 패딩이 자동으로 최적화됩니다.
  - SIZE_56, SIZE_48: 아이콘 24px
  - SIZE_40, SIZE_32: 아이콘 16px
  - SIZE_28: 아이콘 12px
- **원형 버튼 기본**: `useShapeCircle`의 기본값은 `true`로 원형 버튼이 기본입니다.
- **최소 터치 영역**: 버튼 크기가 40px 미만이어도 터치 영역은 최소 40px로 보장됩니다.
- **Compose와 View 호환**: Compose와 View 시스템 모두에서 사용 가능하여 점진적 마이그레이션에 적합합니다.
- **빌더 패턴**: 체이닝 방식으로 직관적인 버튼 구성이 가능합니다.
