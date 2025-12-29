# HongLabelCheckbox

라벨, 설명, 체크박스가 결합된 선택 가능한 위젯입니다.

## 📋 개요

HongLabelCheckbox는 체크박스와 텍스트 라벨을 결합한 UI 컴포넌트로, 주 라벨과 부가 설명을 함께 표시할 수 있습니다. 체크박스의 위치를 왼쪽 또는 오른쪽으로 설정할 수 있으며, 빌더 패턴을 통해 타이포그래피, 색상 등을 쉽게 커스터마이징할 수 있습니다. Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
label/checkbox/
├── HongLabelCheckboxBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongLabelCheckboxOption.kt        # 옵션 데이터 클래스
├── HongLabelCheckboxCompose.kt       # Compose용 컴포넌트
└── HongLabelCheckboxView.kt          # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongLabelCheckboxBuilder
빌더 패턴을 사용하여 라벨 체크박스의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 주 라벨 설정 (`label`, `labelColor`, `labelTypo`)
- 부가 설명 설정 (`description`, `descriptionColor`, `descriptionTypo`)
- 체크박스 상태 설정 (`checkState`)
- 체크박스 크기 설정 (`checkboxSize`)
- 체크박스 옵션 커스터마이징 (`checkboxOption`)
- 체크박스 위치 설정 (`checkboxPosition`)
- 옵션 복사 (`copy`)

### HongLabelCheckboxOption
라벨 체크박스의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `label`: 주 라벨 텍스트
- `labelColorHex`: 라벨 색상 (기본값: `HongColor.BLACK_100`)
- `labelTypo`: 라벨 타이포그래피 (기본값: `HongTypo.BODY_15_B`)
- `description`: 부가 설명 텍스트
- `descriptionColorHex`: 설명 색상 (기본값: `HongColor.BLACK_60`)
- `descriptionTypo`: 설명 타이포그래피 (기본값: `HongTypo.CONTENTS_10`)
- `checkboxOption`: 체크박스 상세 옵션
- `checkboxSize`: 체크박스 크기
- `isChecked`: 체크 상태
- `checkboxPosition`: 체크박스 위치 (기본값: `HongPosition.LEFT`)
- `DEFAULT_BETWEEN_SPACER`: 체크박스와 라벨 사이 간격 (10dp)

**기본 체크박스 스타일:**
- 크기: 22dp
- 배경: 투명
- 체크 시 색상: `HongColor.MAIN_ORANGE_100`
- 체크마크 색상: `HongColor.WHITE_100`
- 테두리: 2dp, `HongColor.GRAY_20`
- 모서리 둥글기: 4dp

### HongLabelCheckboxCompose
Jetpack Compose에서 사용할 수 있는 라벨 체크박스 컴포넌트입니다.

**구조:**
- Row 레이아웃 (수평 정렬, 세로 중앙 정렬)
- 체크박스 (왼쪽 또는 오른쪽)
- 10dp 간격
- 라벨 영역 (주 라벨 + 부가 설명)

### HongLabelCheckboxView
기존 View 시스템에서 사용할 수 있는 커스텀 LinearLayout 뷰입니다.

**구조:**
- Horizontal LinearLayout
- 체크박스 (고정 크기)
- 라벨 영역 (weight=1f로 나머지 공간 차지)
- 10dp 간격

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun MyScreen() {
    val option = HongLabelCheckboxBuilder()
        .label("이용 약관에 동의합니다")
        .description("필수 동의 항목입니다")
        .labelTypo(HongTypo.BODY_15_B)
        .labelColor(HongColor.BLACK_100)
        .descriptionTypo(HongTypo.CONTENTS_10)
        .descriptionColor(HongColor.GRAY_50)
        .checkState(false)
        .checkboxSize(22)
        .checkboxPosition(HongPosition.LEFT)
        .onClick { option ->
            // 클릭 시 동작
            val newState = !(option as HongLabelCheckboxOption).isChecked ?: false
            // 상태 업데이트 로직
        }
        .applyOption()

    HongLabelCheckboxCompose(option = option)
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<com.codehong.library.widget.label.checkbox.HongLabelCheckboxView
    android:id="@+id/labelCheckbox"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val option = HongLabelCheckboxBuilder()
            .label("마케팅 정보 수신 동의")
            .description("이벤트 및 프로모션 정보를 받아보세요")
            .checkState(false)
            .checkboxSize(24)
            .onClick { option ->
                val currentOption = option as HongLabelCheckboxOption
                // 체크박스 상태 토글
                val newOption = HongLabelCheckboxBuilder()
                    .copy(currentOption)
                    .checkState(!(currentOption.isChecked ?: false))
                    .applyOption()

                findViewById<HongLabelCheckboxView>(R.id.labelCheckbox)
                    .set(newOption)
            }
            .applyOption()

        findViewById<HongLabelCheckboxView>(R.id.labelCheckbox)
            .set(option)
    }
}
```

### 체크박스 위치 변경

```kotlin
// 체크박스를 오른쪽에 배치
val rightCheckbox = HongLabelCheckboxBuilder()
    .label("알림 받기")
    .description("새로운 소식을 알려드려요")
    .checkState(true)
    .checkboxPosition(HongPosition.RIGHT)
    .applyOption()
```

### 체크박스 커스터마이징

```kotlin
// 체크박스 스타일 직접 설정
val customCheckbox = HongLabelCheckboxBuilder()
    .label("전체 동의")
    .checkState(false)
    .checkboxOption(
        HongCheckboxBuilder()
            .size(28)
            .backgroundColor(HongColor.WHITE_100)
            .checkedColor(HongColor.BLUE_100)
            .checkmarkColor(HongColor.WHITE_100)
            .border(
                HongBorderInfo(
                    width = 2,
                    color = HongColor.BLUE_50.hex
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 6,
                    topRight = 6,
                    bottomLeft = 6,
                    bottomRight = 6
                )
            )
            .applyOption()
    )
    .applyOption()
```

## ⚙️ 주요 메서드

### HongLabelCheckboxBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `label(label)` | `String?` | 주 라벨 텍스트 설정 |
| `labelColor(color)` | `HongColor` 또는 `String` | 라벨 색상 설정 |
| `labelTypo(typo)` | `HongTypo` | 라벨 타이포그래피 설정 |
| `description(description)` | `String?` | 부가 설명 텍스트 설정 |
| `descriptionColor(color)` | `HongColor` 또는 `String` | 설명 색상 설정 |
| `descriptionTypo(typo)` | `HongTypo` | 설명 타이포그래피 설정 |
| `checkState(isChecked)` | `Boolean?` | 체크박스 체크 상태 설정 |
| `checkboxSize(size)` | `Int?` | 체크박스 크기 설정 (dp) |
| `checkboxOption(option)` | `HongCheckboxOption` | 체크박스 상세 옵션 설정 |
| `checkboxPosition(position)` | `HongPosition?` | 체크박스 위치 설정 (LEFT/RIGHT) |
| `copy(inject)` | `HongLabelCheckboxOption?` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongLabelCheckboxView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongLabelCheckboxOption` | `HongLabelCheckboxView` | 옵션을 적용하여 뷰 구성 |

## 📝 참고사항

### 레이아웃 구조
- **전체 크기:** WRAP_CONTENT (기본값)
- **체크박스 크기:** 22dp (기본값)
- **체크박스-라벨 간격:** 10dp
- **라벨 영역:** 주 라벨(위) + 부가 설명(아래)
- **정렬:** 세로 중앙 정렬

### 기본값
- 라벨 타이포그래피: `HongTypo.BODY_15_B`
- 라벨 색상: `HongColor.BLACK_100` (#000000)
- 설명 타이포그래피: `HongTypo.CONTENTS_10`
- 설명 색상: `HongColor.BLACK_60` (60% 불투명도)
- 체크박스 위치: `HongPosition.LEFT` (왼쪽)
- 배경 색상: `HongColor.TRANSPARENT`

### 체크박스 기본 스타일
- 크기: 22dp
- 체크 시 배경색: `HongColor.MAIN_ORANGE_100` (주황색)
- 체크마크 색상: `HongColor.WHITE_100` (흰색)
- 테두리: 2dp, `HongColor.GRAY_20` (회색)
- 모서리 둥글기: 4dp

### 주의사항
- `label`은 필수 항목이 아니지만, 없으면 시각적으로 의미가 없을 수 있습니다
- `description`은 선택사항으로, 없으면 표시되지 않습니다
- View 시스템에서 상태를 변경하려면 `set()` 메서드를 다시 호출해야 합니다
- Compose에서는 상태 관리를 별도로 구현해야 합니다 (remember, mutableStateOf 등)
- `checkboxOption`을 직접 설정하면 `checkState`와 `checkboxSize`가 무시될 수 있으므로 주의가 필요합니다

### 의존성
- HongCheckBox (체크박스 위젯)
- HongLabel (라벨 위젯)
- HongColor, HongTypo (디자인 시스템)
- HongPosition (위치 열거형)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)

### 사용 사례
- 약관 동의 체크박스
- 설정 화면의 옵션 선택
- 마케팅 정보 수신 동의
- 필터 옵션 선택
- 다중 선택 목록
- 기능 활성화/비활성화 토글

### 상태 관리 예시 (Compose)

```kotlin
@Composable
fun TermsAgreementScreen() {
    var isChecked by remember { mutableStateOf(false) }

    val option = HongLabelCheckboxBuilder()
        .label("이용 약관에 동의합니다")
        .description("필수 동의 항목입니다")
        .checkState(isChecked)
        .onClick {
            isChecked = !isChecked
        }
        .applyOption()

    HongLabelCheckboxCompose(option = option)
}
```
