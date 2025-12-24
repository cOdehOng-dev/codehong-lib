# HongTextUnit

숫자와 단위를 함께 표시하는 텍스트 위젯입니다. 텍스트와 단위 텍스트를 수평으로 배치하여 "1,000장", "100개"와 같은 형식으로 표현할 수 있습니다.

## 📋 개요

HongTextUnit은 숫자(또는 텍스트)와 단위를 함께 표시하는 위젯입니다. 숫자에 천 단위 콤마를 자동으로 추가할 수 있으며, 단위 표시 여부를 동적으로 제어할 수 있습니다. Jetpack Compose와 Android View 시스템 모두에서 사용 가능합니다.

**주요 특징:**
- 텍스트와 단위 텍스트 수평 배치
- 숫자 천 단위 콤마 자동 추가 옵션
- 단위 표시 ON/OFF 제어
- 각 텍스트(본문/단위)에 대한 독립적인 스타일 설정
- 커스텀 타이포그래피 및 색상 지원

## 🏗️ 구조

```
text/unit/
├── HongTextUnitCompose.kt    # Jetpack Compose UI 구현
├── HongTextUnitView.kt        # Android View 래퍼 클래스
├── HongTextUnitOption.kt      # 설정 데이터 클래스
└── HongTextUnitBuilder.kt     # 빌더 패턴 구현
```

## 📦 주요 클래스

### HongTextUnitCompose

Jetpack Compose로 구현된 텍스트 단위 표시 컴포넌트입니다.

**주요 기능:**
- `Row` 레이아웃으로 텍스트와 단위를 수평 배치
- `Alignment.CenterVertically`로 수직 중앙 정렬
- `useUnit` 플래그에 따라 단위 텍스트 표시 여부 결정
- 각 텍스트에 대한 독립적인 `HongTextCompose` 사용

```kotlin
@Composable
fun HongTextUnitCompose(
    option: HongTextUnitOption
)
```

### HongTextUnitView

Android View 시스템을 위한 래퍼 클래스입니다. `LinearLayout`을 상속받아 구현되었습니다.

```kotlin
class HongTextUnitView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    fun set(option: HongTextUnitOption): HongTextUnitView
}
```

**특징:**
- `HORIZONTAL` orientation
- `Gravity.CENTER_VERTICAL`로 수직 정렬
- `HongTextCompose`를 사용하여 텍스트 렌더링

### HongTextUnitOption

위젯의 모든 설정을 포함하는 데이터 클래스입니다.

**주요 속성:**

| 속성 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `text` | `String?` | `null` | 표시할 텍스트 (숫자 등) |
| `textOption` | `HongTextOption` | `DEFAULT_TEXT_OPTION` | 텍스트 스타일 옵션 |
| `unitText` | `String?` | `null` | 단위 텍스트 (예: "개", "장") |
| `unitTextOption` | `HongTextOption` | `DEFAULT_TEXT_OPTION` | 단위 텍스트 스타일 옵션 |
| `useNumberDecimal` | `Boolean` | `false` | 숫자 천 단위 콤마 사용 여부 |
| `useUnit` | `Boolean` | `true` | 단위 텍스트 표시 여부 |

**기본 텍스트 옵션:**
- 타이포그래피: `HongTypo.BODY_16`
- 색상: `HongColor.BLACK_100`

### HongTextUnitBuilder

빌더 패턴을 사용하여 `HongTextUnitOption`을 쉽게 구성할 수 있습니다.

```kotlin
class HongTextUnitBuilder :
    HongWidgetCommonBuilder<HongTextUnitOption, HongTextUnitBuilder>
```

**주요 메서드:**
- `text(String?)` - 표시할 텍스트 설정
- `textOption(HongTextOption)` - 텍스트 스타일 옵션 설정
- `unitText(String?)` - 단위 텍스트 설정
- `useNumberDecimal(Boolean)` - 천 단위 콤마 사용 여부
- `useUnit(Boolean)` - 단위 표시 여부
- `copy(HongTextUnitOption?)` - 옵션 복사

## 🚀 사용법

### Jetpack Compose

```kotlin
@Composable
fun ProductQuantityScreen() {
    HongTextUnitCompose(
        HongTextUnitBuilder()
            .text("1000")
            .unitText("장")
            .useUnit(true)
            .useNumberDecimal(true)  // "1,000장"으로 표시
            .margin(HongSpacingInfo(left = 20f, right = 20f))
            .applyOption()
    )
}
```

### Android View

```kotlin
val unitView = HongTextUnitView(context)
    .set(
        HongTextUnitBuilder()
            .text("500")
            .unitText("개")
            .useUnit(true)
            .useNumberDecimal(true)
            .applyOption()
    )

parentLayout.addView(unitView)
```

### 사용 예시: 커스텀 스타일

```kotlin
HongTextUnitCompose(
    HongTextUnitBuilder()
        .text("2500")
        .textOption(
            HongTextBuilder()
                .typography(HongTypo.TITLE_24_B)
                .color(HongColor.MAIN_ORANGE_100)
                .applyOption()
        )
        .unitText("원")
        .useUnit(true)
        .useNumberDecimal(true)  // "2,500원"으로 표시
        .applyOption()
)
```

### 사용 예시: 단위 숨기기

```kotlin
HongTextUnitCompose(
    HongTextUnitBuilder()
        .text("9999")
        .unitText("장")
        .useUnit(false)  // 단위 표시 안함
        .useNumberDecimal(true)  // "9,999"만 표시
        .applyOption()
)
```

## ⚙️ 주요 기능

### 1. 숫자 포맷팅

- `useNumberDecimal(true)` 설정으로 숫자에 천 단위 콤마 자동 추가
- 예: "1000" → "1,000"

### 2. 단위 표시 제어

- `useUnit` 플래그로 단위 텍스트 표시 여부 동적 제어
- 상황에 따라 단위를 보이거나 숨길 수 있음

### 3. 독립적인 스타일링

- 텍스트와 단위 텍스트에 각각 다른 스타일 적용 가능
- `textOption`과 `unitTextOption`으로 분리된 설정

### 4. 수평 정렬

- Row 레이아웃으로 텍스트와 단위를 자연스럽게 나란히 배치
- 수직 중앙 정렬로 깔끔한 UI 제공

## 📝 참고사항

- **기본 타이포그래피**: `HongTypo.BODY_16`이 기본값으로 설정됩니다.
- **기본 색상**: `HongColor.BLACK_100`이 기본값입니다.
- **레이아웃**: LinearLayout 기반으로 텍스트가 수평 배치됩니다.
- **천 단위 콤마**: `useNumberDecimal(true)` 설정 시 `HongTextCompose`의 숫자 포맷팅 기능을 활용합니다.
- **단위 표시**: `useUnit` 플래그로 런타임에 단위 표시 여부를 제어할 수 있습니다.
- **옵션 복사**: `copy()` 메서드로 기존 옵션을 복사하여 일부만 수정할 수 있습니다.

## 🔗 의존성

이 컴포넌트는 다음 모듈에 의존합니다:

- `HongTextCompose` - 텍스트 렌더링
- `HongWidgetContainer` - 공통 위젯 컨테이너
- `HongColor` - 색상 시스템
- `HongTypo` - 타이포그래피 시스템
- `HongTextOption` - 텍스트 설정 옵션

## 📄 라이선스

이 컴포넌트는 codehong-lib 프로젝트의 일부입니다.
