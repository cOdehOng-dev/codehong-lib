# HongLabel

주 라벨과 부가 설명을 표시하는 기본 라벨 위젯입니다.

## 📋 개요

HongLabel은 주 라벨 텍스트와 선택적인 부가 설명을 세로로 배치하여 표시하는 기본 라벨 컴포넌트입니다. 빌더 패턴을 통해 타이포그래피, 색상 등을 쉽게 커스터마이징할 수 있으며, Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
label/def/
├── HongLabelBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongLabelOption.kt         # 옵션 데이터 클래스
├── HongLabelCompose.kt        # Compose용 컴포넌트
└── HongLabelView.kt           # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongLabelBuilder
빌더 패턴을 사용하여 라벨의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 주 라벨 설정 (`label`, `labelColor`, `labelTypo`)
- 부가 설명 설정 (`description`, `descriptionColor`, `descriptionTypo`)
- 옵션 복사 (`copy`)

### HongLabelOption
라벨의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `label`: 주 라벨 텍스트
- `labelColorHex`: 라벨 색상 (기본값: `HongColor.BLACK_100`)
- `labelTypo`: 라벨 타이포그래피 (기본값: `HongTypo.BODY_15_B`)
- `description`: 부가 설명 텍스트
- `descriptionColorHex`: 설명 색상 (기본값: `HongColor.BLACK_60`)
- `descriptionTypo`: 설명 타이포그래피 (기본값: `HongTypo.CONTENTS_10`)
- `width`: 라벨 너비 (기본값: MATCH_PARENT)
- `height`: 라벨 높이 (기본값: WRAP_CONTENT)
- `backgroundColorHex`: 배경 색상 (기본값: `HongColor.WHITE_100`)

### HongLabelViewCompose
Jetpack Compose에서 사용할 수 있는 라벨 컴포넌트입니다.

**구조:**
- Column 레이아웃 (세로 배치)
- 주 라벨 (HongText)
- 부가 설명 (HongText, 2dp 상단 마진)

**특징:**
- 라벨이 비어있으면 컴포넌트가 렌더링되지 않음
- 설명이 비어있으면 표시되지 않음

### HongLabelView
기존 View 시스템에서 사용할 수 있는 커스텀 LinearLayout 뷰입니다.

**구조:**
- Vertical LinearLayout
- 주 라벨 (HongTextView)
- 부가 설명 (HongTextView, 2dp 상단 마진)

**특징:**
- 라벨이 비어있으면 visibility가 GONE으로 설정됨
- 설명이 비어있으면 추가되지 않음

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun MyScreen() {
    val option = HongLabelBuilder()
        .label("상품명")
        .description("필수 입력 항목입니다")
        .labelTypo(HongTypo.BODY_15_B)
        .labelColor(HongColor.BLACK_100)
        .descriptionTypo(HongTypo.CONTENTS_10)
        .descriptionColor(HongColor.GRAY_50)
        .backgroundColor(HongColor.WHITE_100)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 20f,
                right = 20f
            )
        )
        .applyOption()

    HongLabelViewCompose(option = option)
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<com.codehong.library.widget.label.def.HongLabelView
    android:id="@+id/labelView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val option = HongLabelBuilder()
            .label("이메일")
            .description("이메일 형식으로 입력해주세요")
            .labelTypo(HongTypo.BODY_15_B)
            .labelColor(HongColor.BLACK_100)
            .descriptionTypo(HongTypo.CONTENTS_10)
            .descriptionColor(HongColor.GRAY_50)
            .backgroundColor(HongColor.WHITE_100)
            .padding(
                HongSpacingInfo(
                    top = 10f,
                    bottom = 5f
                )
            )
            .applyOption()

        findViewById<HongLabelView>(R.id.labelView)
            .set(option)
    }
}
```

### 라벨만 표시 (설명 없이)

```kotlin
val simpleLabelOption = HongLabelBuilder()
    .label("제목")
    .labelTypo(HongTypo.TITLE_18_B)
    .labelColor(HongColor.BLACK_100)
    .applyOption()
```

### 커스텀 색상 적용

```kotlin
val customColorOption = HongLabelBuilder()
    .label("특별 안내")
    .description("중요한 정보입니다")
    .labelColor("#FF5722")  // Hex 색상 직접 입력
    .descriptionColor("#9E9E9E")
    .backgroundColor("#F5F5F5")
    .applyOption()
```

## ⚙️ 주요 메서드

### HongLabelBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `label(label)` | `String?` | 주 라벨 텍스트 설정 |
| `labelColor(color)` | `HongColor` 또는 `String` | 라벨 색상 설정 (HongColor 또는 Hex) |
| `labelTypo(typo)` | `HongTypo` | 라벨 타이포그래피 설정 |
| `description(description)` | `String?` | 부가 설명 텍스트 설정 |
| `descriptionColor(color)` | `HongColor` 또는 `String` | 설명 색상 설정 (HongColor 또는 Hex) |
| `descriptionTypo(typo)` | `HongTypo` | 설명 타이포그래피 설정 |
| `backgroundColor(color)` | `String` | 배경 색상 설정 (Hex) |
| `width(width)` | `Int` | 라벨 너비 설정 |
| `height(height)` | `Int` | 라벨 높이 설정 |
| `padding(padding)` | `HongSpacingInfo` | 내부 여백 설정 |
| `margin(margin)` | `HongSpacingInfo` | 외부 여백 설정 |
| `copy(inject)` | `HongLabelOption?` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongLabelView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongLabelOption` | `HongLabelView` | 옵션을 적용하여 뷰 구성 |

## 📝 참고사항

### 레이아웃 구조
- **전체 너비:** MATCH_PARENT (기본값)
- **전체 높이:** WRAP_CONTENT (기본값)
- **배치:** 세로 방향 (Column/Vertical LinearLayout)
- **라벨-설명 간격:** 2dp

### 기본값
- 라벨 타이포그래피: `HongTypo.BODY_15_B`
- 라벨 색상: `HongColor.BLACK_100` (#000000)
- 설명 타이포그래피: `HongTypo.CONTENTS_10`
- 설명 색상: `HongColor.BLACK_60` (60% 불투명도)
- 배경 색상: `HongColor.WHITE_100` (#FFFFFF)

### 주의사항
- `label`이 null이거나 빈 문자열인 경우:
  - Compose: 컴포넌트가 렌더링되지 않음
  - View: visibility가 GONE으로 설정됨
- `description`이 null이거나 빈 문자열인 경우: 설명 텍스트가 표시되지 않음
- View 시스템에서 설정을 변경하려면 `set()` 메서드를 다시 호출해야 합니다
- 라벨과 설명 사이의 간격은 2dp로 고정되어 있습니다

### 의존성
- HongText (텍스트 위젯)
- HongColor, HongTypo (디자인 시스템)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)
- HongSpacingInfo (여백 정보)

### 사용 사례
- 폼 필드의 라벨 (입력창 위의 안내 텍스트)
- 설정 항목의 제목과 설명
- 리스트 아이템의 제목과 부제목
- 정보 표시 섹션의 헤더
- 안내 메시지와 상세 설명
- 카테고리 타이틀

### 비교: HongLabel vs HongText
- **HongLabel**: 주 라벨 + 부가 설명의 조합 (2개의 텍스트)
- **HongText**: 단일 텍스트

HongLabel은 내부적으로 HongText를 사용하여 라벨과 설명을 각각 렌더링합니다.

### 예시: 폼 라벨

```kotlin
@Composable
fun FormField() {
    Column {
        HongLabelViewCompose(
            HongLabelBuilder()
                .label("비밀번호")
                .description("8자 이상, 영문/숫자/특수문자 포함")
                .labelColor(HongColor.BLACK_100)
                .descriptionColor(HongColor.GRAY_50)
                .padding(HongSpacingInfo(bottom = 8f))
                .applyOption()
        )

        // 여기에 입력 필드 추가
        // TextField(...)
    }
}
```
