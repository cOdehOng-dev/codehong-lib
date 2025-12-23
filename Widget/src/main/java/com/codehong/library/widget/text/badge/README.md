# HongTextBadge

빌더 패턴 기반의 텍스트 배지 위젯. 작고 간결한 라벨을 표시하는 데 사용되며, 배경색, 테두리, 모서리 둥글기 등 다양한 스타일 커스터마이징을 지원합니다 (View/Compose 지원).

## 📋 개요

HongTextBadge는 상태, 카테고리, 태그 등을 시각적으로 표시하는 배지 위젯입니다. 단일 줄 텍스트에 최적화되어 있으며, 빌더 패턴을 통해 간편하게 스타일을 설정할 수 있습니다.

**주요 기능:**
- 🏷️ 단일 줄 텍스트 배지 (maxLines 자동 1로 고정)
- 🎨 텍스트 색상 및 타이포그래피 커스터마이징
- 🖼️ 배경색, 테두리, 모서리 둥글기 설정
- 💧 그림자 효과 지원
- 📐 패딩 및 여백 조절
- 🔄 View와 Compose 모두 지원
- ✨ HongText 기반으로 일관된 텍스트 스타일링

## 🏗️ 구조

```
text/badge/
├── HongTextBadgeCompose.kt    # Jetpack Compose 구현
├── HongTextBadgeView.kt       # Android View 구현
├── HongTextBadgeBuilder.kt    # 빌더 패턴 클래스
└── HongTextBadgeOption.kt     # 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongTextBadgeCompose
Jetpack Compose 기반의 배지 컴포저블 함수입니다.

**주요 특징:**
- `HongWidgetContainer`로 래핑하여 공통 위젯 스타일 적용
- 내부적으로 `HongTextCompose` 사용
- maxLines를 1로 고정하여 단일 줄 배지 보장
- Preview 함수 포함으로 개발 중 실시간 확인 가능

### HongTextBadgeView
Android View 시스템 기반의 배지 뷰입니다.

**주요 특징:**
- `FrameLayout` 기반 구조
- `hongText {}` DSL을 통한 텍스트 설정
- `hongBackground()`로 배경, 테두리, 둥근 모서리 일괄 적용
- `set()` 메서드로 옵션 적용

### HongTextBadgeBuilder
빌더 패턴을 사용하여 HongTextBadgeOption을 쉽게 구성할 수 있는 클래스입니다.

**주요 메서드:**
- `text()`: 배지 텍스트 설정
- `textColor()`: 텍스트 색상 설정
- `textTypo()`: 타이포그래피 설정
- `backgroundColor()`: 배경색 설정
- `border()`: 테두리 설정
- `radius()`: 모서리 둥글기 설정
- `shadow()`: 그림자 설정

### HongTextBadgeOption
배지 위젯의 모든 설정을 담는 데이터 클래스입니다.

**기본값:**
- `width/height`: WRAP_CONTENT
- `backgroundColorHex`: HongColor.WHITE_100
- `border`: width 1, color WHITE_100
- `textTypography`: HongTypo.CONTENTS_12_B

## 🚀 사용법

### Compose - 기본 배지

```kotlin
HongTextBadgeCompose(
    option = HongTextBadgeBuilder()
        .text("NEW")
        .textColor(HongColor.WHITE_100)
        .textTypo(HongTypo.CONTENTS_12_B)
        .backgroundColor(HongColor.PRIMARY.hex)
        .radius(HongRadiusInfo(all = 4))
        .padding(HongSpacingInfo(left = 8f, right = 8f, top = 4f, bottom = 4f))
        .applyOption()
)
```

### Compose - 테두리 배지

```kotlin
HongTextBadgeCompose(
    option = HongTextBadgeBuilder()
        .text("인기")
        .textColor(HongColor.PRIMARY)
        .textTypo(HongTypo.CONTENTS_12_B)
        .backgroundColor(HongColor.WHITE_100.hex)
        .border(
            HongBorderInfo(
                width = 1,
                color = HongColor.PRIMARY.hex
            )
        )
        .radius(HongRadiusInfo(all = 12))
        .padding(HongSpacingInfo(left = 6f, right = 6f, top = 2f, bottom = 2f))
        .applyOption()
)
```

### Compose - 카테고리 배지

```kotlin
HongTextBadgeCompose(
    option = HongTextBadgeBuilder()
        .text("여행")
        .textColor("#8B5CF6")
        .textTypo(HongTypo.CONTENTS_12_B)
        .backgroundColor("#F5F3FF")
        .border(
            HongBorderInfo(
                width = 1,
                color = "#DDD6FE"
            )
        )
        .radius(HongRadiusInfo(all = 6))
        .padding(HongSpacingInfo(left = 8f, right = 8f, top = 3f, bottom = 3f))
        .applyOption()
)
```

### View - 기본 사용

```kotlin
val badgeView = HongTextBadgeView(context)
badgeView.set(
    HongTextBadgeBuilder()
        .text("SALE")
        .textColor(HongColor.RED_100)
        .textTypo(HongTypo.CONTENTS_12_B)
        .backgroundColor(HongColor.RED_10.hex)
        .radius(HongRadiusInfo(all = 4))
        .padding(HongSpacingInfo(left = 6f, right = 6f, top = 2f, bottom = 2f))
        .applyOption()
)
```

### 그림자 효과

```kotlin
HongTextBadgeCompose(
    option = HongTextBadgeBuilder()
        .text("Premium")
        .textColor(HongColor.WHITE_100)
        .textTypo(HongTypo.CONTENTS_12_B)
        .backgroundColor(HongColor.BLACK_100.hex)
        .radius(HongRadiusInfo(all = 8))
        .shadow(
            HongShadowInfo(
                offsetX = 0f,
                offsetY = 2f,
                blurRadius = 8f,
                color = "#00000040"
            )
        )
        .padding(HongSpacingInfo(left = 10f, right = 10f, top = 4f, bottom = 4f))
        .applyOption()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `text(String)` | 배지에 표시할 텍스트를 설정합니다 |
| `textColor(HongColor/String)` | 텍스트 색상을 설정합니다 (HongColor 또는 hex) |
| `textTypo(HongTypo)` | 텍스트 타이포그래피를 설정합니다 |
| `backgroundColor(String)` | 배지의 배경색을 설정합니다 (hex) |
| `border(HongBorderInfo)` | 테두리 스타일을 설정합니다 (width, color) |
| `radius(HongRadiusInfo)` | 모서리 둥글기를 설정합니다 |
| `shadow(HongShadowInfo)` | 그림자 효과를 설정합니다 (offsetX, offsetY, blurRadius, color) |
| `padding(HongSpacingInfo)` | 내부 여백을 설정합니다 (left, right, top, bottom) |
| `margin(HongSpacingInfo)` | 외부 여백을 설정합니다 (left, right, top, bottom) |
| `width(Int)` | 너비를 설정합니다 (기본값: WRAP_CONTENT) |
| `height(Int)` | 높이를 설정합니다 (기본값: WRAP_CONTENT) |

## 📝 참고사항

- **단일 줄 고정**: maxLines가 자동으로 1로 설정되어 배지는 항상 한 줄로 표시됩니다
- **HongText 기반**: 내부적으로 HongText를 사용하므로 HongText의 모든 타이포그래피 시스템을 활용할 수 있습니다
- **기본 스타일**: 기본적으로 흰색 배경에 흰색 테두리(width: 1)가 적용됩니다
- **타이포그래피**: 기본 타이포그래피는 `HongTypo.CONTENTS_12_B` (12sp, Bold)입니다
- **WRAP_CONTENT**: width와 height가 기본적으로 WRAP_CONTENT로 설정되어 텍스트 길이에 맞춰 크기가 조절됩니다
- **패딩 권장사항**: 일반적으로 좌우 패딩 6~10dp, 상하 패딩 2~4dp가 적절합니다
- **둥근 모서리**: `radius` 값을 텍스트 높이의 절반 이상으로 설정하면 완전히 둥근 캡슐 형태가 됩니다
- **View vs Compose**: 두 버전 모두 같은 `HongTextBadgeOption`을 사용하여 일관된 API를 제공합니다
