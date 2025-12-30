# HongText

빌더 패턴 기반의 커스텀 텍스트 위젯. 타이포그래피 시스템, Span 텍스트, 숫자 포맷팅, 줄바꿈 제어 등 다양한 텍스트 스타일링 기능을 제공합니다 (View/Compose 지원).

## 📋 개요

HongText는 Android View와 Jetpack Compose 모두에서 사용 가능한 텍스트 위젯입니다. 빌더 패턴을 통해 간편하게 설정할 수 있으며, 일관된 타이포그래피 시스템과 다양한 텍스트 스타일링 옵션을 제공합니다.

**주요 기능:**
- 🎨 타이포그래피 시스템 (HongTypo)을 통한 일관된 텍스트 스타일 관리
- ✨ Span 텍스트를 통한 부분 스타일링 (색상, 크기, 폰트, 장식 등)
- 🔢 숫자 자동 포맷팅 (천 단위 콤마, 소수점 지원)
- 📏 음절 단위 줄바꿈 지원
- 🎭 텍스트 장식 (밑줄, 취소선)
- 📐 정렬 옵션 (좌/중/우)
- 📝 최대 줄 수 및 오버플로우 제어
- 🔄 View와 Compose 모두 지원

## 🏗️ 구조

```
text/label/
├── HongTextCompose.kt      # Jetpack Compose 구현
├── HongTextView.kt         # Android View 구현
├── HongTextBuilder.kt      # 빌더 패턴 클래스
└── HongTextOption.kt       # 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongTextCompose
Jetpack Compose 기반의 텍스트 컴포저블 함수입니다.

**주요 기능:**
- `buildAnnotatedString`을 사용한 Span 텍스트 처리
- 정규식 기반 텍스트 매칭 및 스타일 적용
- 숫자 포맷팅 (DecimalFormat)
- 음절 단위 줄바꿈 (SYLLABLE 모드)
- TextDecoration 조합 (밑줄, 취소선)
- PlatformTextStyle로 정확한 텍스트 정렬

### HongTextView
Android View 시스템 기반의 텍스트 뷰입니다.

**주요 기능:**
- LinearLayout 기반 구조
- HongCustomText (내부 클래스) 사용
- Paint flags를 통한 텍스트 장식
- SpannableString을 통한 Span 텍스트 처리
- 동적 줄 높이 및 폰트 설정

### HongTextBuilder
빌더 패턴을 사용하여 HongTextOption을 쉽게 구성할 수 있는 클래스입니다.

**주요 메서드:**
- `text()`: 텍스트 설정
- `typography()`: 타이포그래피 설정
- `color()`: 색상 설정
- `spanTextBuilderList()`: Span 텍스트 리스트 설정
- `useNumberDecimal()`: 숫자 포맷팅 활성화

### HongTextOption
텍스트 위젯의 모든 설정을 담는 데이터 클래스입니다.

**기본값:**
- `DEFAULT_LABEL_COLOR`: HongColor.BLACK_100
- `DEFAULT_TYPOGRAPHY`: HongTypo.BODY_14
- `DEFAULT_OVER_FLOW`: HongTextOverflow.ELLIPSIS

## 🚀 사용법

### Compose - 기본 사용

```kotlin
HongTextCompose(
    option = HongTextBuilder()
        .text("안녕하세요")
        .typography(HongTypo.HEADING_20)
        .color(HongColor.BLACK_100)
        .build()
)
```

### Compose - Span 텍스트 (부분 스타일링)

```kotlin
HongTextCompose(
    option = HongTextBuilder()
        .text("총 금액: 10,000원")
        .typography(HongTypo.BODY_14)
        .color(HongColor.GRAY_60)
        .spanTextBuilderList(
            listOf(
                HongTextBuilder()
                    .text("10,000원")
                    .color(HongColor.PRIMARY)
                    .fontType(HongFont.PRETENDARD_700)
            )
        )
        .build()
)
```

### Compose - 숫자 포맷팅

```kotlin
HongTextCompose(
    option = HongTextBuilder()
        .text("1234567")
        .useNumberDecimal(true)  // "1,234,567"로 자동 변환
        .typography(HongTypo.HEADING_24)
        .build()
)
```

### View - 기본 사용

```kotlin
val textView = HongTextView(context)
textView.set(
    HongTextBuilder()
        .text("안녕하세요")
        .typography(HongTypo.BODY_14)
        .color(HongColor.BLACK_100)
        .maxLines(2)
        .overflow(HongTextOverflow.ELLIPSIS)
        .build()
)
```

### 음절 단위 줄바꿈

```kotlin
HongTextCompose(
    option = HongTextBuilder()
        .text("아주 긴 텍스트입니다")
        .lineBreak(HongTextLineBreak.SYLLABLE)  // 음절 단위로 줄바꿈
        .build()
)
```

### 텍스트 장식

```kotlin
HongTextCompose(
    option = HongTextBuilder()
        .text("할인 중!")
        .isEnableUnderLine(true)      // 밑줄
        .isEnableCancelLine(true)     // 취소선
        .build()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `text(String)` | 표시할 텍스트를 설정합니다 |
| `typography(HongTypo)` | 타이포그래피를 설정합니다 (크기, 폰트, 줄 높이) |
| `color(HongColor/String)` | 텍스트 색상을 설정합니다 (HongColor 또는 hex) |
| `size(Int)` | 텍스트 크기를 직접 설정합니다 (dp) |
| `fontType(HongFont)` | 폰트 타입을 설정합니다 (PRETENDARD_400, PRETENDARD_700) |
| `textAlign(HongTextAlign)` | 텍스트 정렬을 설정합니다 (LEFT, CENTER, RIGHT) |
| `maxLines(Int)` | 최대 줄 수를 설정합니다 |
| `overflow(HongTextOverflow)` | 텍스트 오버플로우 처리를 설정합니다 (ELLIPSIS 등) |
| `lineBreak(HongTextLineBreak)` | 줄바꿈 방식을 설정합니다 (DEFAULT, SYLLABLE) |
| `isEnableCancelLine(Boolean)` | 취소선(중간선) 표시 여부를 설정합니다 |
| `isEnableUnderLine(Boolean)` | 밑줄 표시 여부를 설정합니다 |
| `spanTextBuilderList(List<HongTextBuilder>)` | 부분 스타일링을 위한 Span 텍스트 리스트를 설정합니다 |
| `useNumberDecimal(Boolean)` | 숫자에 콤마 포맷을 자동 적용합니다 (1000 → 1,000) |

## 📝 참고사항

- **Pretendard 폰트**: 기본 폰트 패밀리로 Pretendard를 사용합니다
- **Span 텍스트 매칭**: 정규식(Regex)을 통해 매칭되며, 같은 텍스트가 여러 번 나타날 경우 모두 스타일이 적용됩니다
- **숫자 포맷팅**: `useNumberDecimal(true)` 설정 시
  - 정수: `#,###` 포맷 (예: 1000 → 1,000)
  - 소수: `#,##0.##` 포맷 (예: 1234.56 → 1,234.56)
- **음절 단위 줄바꿈**: `HongTextLineBreak.SYLLABLE` 모드 사용 시 내부적으로 특수 문자(`\uff07`)를 삽입하여 음절 단위로 줄바꿈을 처리합니다
- **letterSpacing**: Compose 버전은 기본값 `-0.05sp`로 설정되어 있습니다
- **View vs Compose**: 두 버전 모두 같은 `HongTextOption`을 사용하여 일관된 API를 제공합니다
- **textAlign 주의사항**: width가 `WRAP_CONTENT`인 경우 정렬이 의도한 대로 동작하지 않을 수 있습니다
- **includeFontPadding**: Compose 버전은 `PlatformTextStyle`에서 `includeFontPadding = false`로 설정하여 정확한 텍스트 정렬을 제공합니다
