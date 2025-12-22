# HongIcon

Android Compose 기반의 커스텀 아이콘 위젯으로, 빌더 패턴을 통해 아이콘의 크기, 색상, ScaleType 등을 쉽게 설정할 수 있습니다.

## 📋 개요

HongIcon은 Jetpack Compose와 기존 Android View 시스템 모두에서 사용 가능한 아이콘 위젯입니다. 빌더 패턴을 통해 직관적으로 아이콘을 구성할 수 있으며, 다양한 커스터마이징 옵션을 제공합니다.

## 🏗️ 구조

```
icon/
├── HongIconBuilder.kt      # 빌더 패턴 구현
├── HongIconCompose.kt      # Compose 컴포저블
├── HongIconOption.kt       # 옵션 데이터 클래스
└── HongIconVIew.kt         # Android View 래퍼
```

## 📦 주요 클래스

### HongIconOption

아이콘의 모든 속성을 정의하는 데이터 클래스입니다.

**주요 프로퍼티:**
- `iconType: HongIconType` - 아이콘 크기 타입 (기본값: H24)
- `iconResId: Int?` - 아이콘 리소스 ID
- `iconColorHex: String` - 아이콘 색상 (Hex 코드)
- `iconScaleType: HongScaleType` - 아이콘 스케일 타입 (기본값: CENTER_INSIDE)

### HongIconBuilder

빌더 패턴을 사용하여 아이콘 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `iconType(iconType: HongIconType)` - 아이콘 크기 설정
- `iconResId(iconResId: Int?)` - 아이콘 리소스 설정
- `iconColor(iconColor: HongColor)` - 아이콘 색상 설정 (HongColor)
- `iconColor(iconColorHex: String)` - 아이콘 색상 설정 (Hex)
- `iconScaleType(iconScaleType: HongScaleType)` - 스케일 타입 설정
- `copy(inject: HongIconOption?)` - 기존 옵션 복사

### HongIconCompose

Jetpack Compose에서 아이콘을 렌더링하는 컴포저블 함수입니다.

```kotlin
@Composable
fun HongIconCompose(option: HongIconOption)
```

### HongIconView

기존 Android View 시스템에서 아이콘을 사용할 수 있도록 ComposeView로 변환하는 클래스입니다.

```kotlin
class HongIconVIew(private val context: Context) {
    fun set(option: HongIconOption): ComposeView
}
```

## 🚀 사용법

### 1. Jetpack Compose에서 사용

```kotlin
@Composable
fun MyScreen() {
    val iconOption = HongIconBuilder()
        .iconType(HongIconType.H24)
        .iconResId(R.drawable.ic_star)
        .iconColor(HongColor.BLUE_500)
        .iconScaleType(HongScaleType.CENTER_INSIDE)
        .padding(HongSpacingInfo(all = 8))
        .applyOption()

    HongIconCompose(option = iconOption)
}
```

### 2. Android View에서 사용

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val iconOption = HongIconBuilder()
            .iconType(HongIconType.H32)
            .iconResId(R.drawable.ic_home)
            .iconColor("#FF5722")
            .applyOption()

        val iconView = HongIconVIew(this).set(iconOption)

        val container = findViewById<FrameLayout>(R.id.container)
        container.addView(iconView)
    }
}
```

### 3. 빌더 패턴 활용 (체이닝)

```kotlin
val option = HongIconBuilder()
    .iconType(HongIconType.H48)
    .iconResId(R.drawable.ic_settings)
    .iconColor(HongColor.BLACK_100)
    .iconScaleType(HongScaleType.FIT_CENTER)
    .margin(HongSpacingInfo(top = 16, bottom = 16))
    .padding(HongSpacingInfo(all = 12))
    .backgroundColor(HongColor.GRAY_50)
    .radius(HongRadiusInfo(all = 8))
    .onClick { option ->
        // 클릭 이벤트 처리
        Log.d("HongIcon", "Icon clicked!")
    }
    .applyOption()

HongIconCompose(option = option)
```

### 4. 기존 옵션 복사하여 수정

```kotlin
val originalOption = HongIconBuilder()
    .iconType(HongIconType.H24)
    .iconResId(R.drawable.ic_heart)
    .iconColor(HongColor.RED_500)
    .applyOption()

// 기존 옵션을 복사하여 색상만 변경
val modifiedOption = HongIconBuilder()
    .copy(originalOption)
    .iconColor(HongColor.PINK_500)
    .applyOption()

HongIconCompose(option = modifiedOption)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `iconType(HongIconType)` | 아이콘 크기 타입 설정 (H24, H32, H48 등) |
| `iconResId(Int?)` | 아이콘 리소스 ID 설정 |
| `iconColor(HongColor)` | 아이콘 색상 설정 (HongColor 객체) |
| `iconColor(String)` | 아이콘 색상 설정 (Hex 문자열) |
| `iconScaleType(HongScaleType)` | 아이콘 스케일 타입 설정 (CENTER_INSIDE, FIT_CENTER 등) |
| `copy(HongIconOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |
| `margin(HongSpacingInfo)` | 외부 여백 설정 |
| `padding(HongSpacingInfo)` | 내부 여백 설정 |
| `backgroundColor(HongColor/String)` | 배경 색상 설정 |
| `radius(HongRadiusInfo)` | 모서리 둥글기 설정 |
| `onClick(람다)` | 클릭 이벤트 리스너 설정 |

## 📝 참고사항

- HongIcon은 내부적으로 `HongImageCompose`를 사용하여 아이콘을 이미지로 렌더링합니다.
- `HongWidgetCommonOption`을 상속받아 공통 위젯 속성(margin, padding, radius, border, shadow 등)을 모두 사용할 수 있습니다.
- Compose와 View 시스템 모두에서 사용 가능하므로, 점진적 마이그레이션 시나리오에 적합합니다.
- `copy()` 메서드를 활용하면 기존 스타일을 재사용하면서 일부 속성만 변경할 수 있어 효율적입니다.
- 아이콘 색상은 `HongColor` 객체 또는 Hex 문자열로 설정 가능합니다.
