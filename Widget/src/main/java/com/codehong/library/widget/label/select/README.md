# HongLabelSelectInput

라벨, 설명, 선택 버튼, 텍스트 입력 필드를 결합한 복합 선택 입력 위젯입니다.

## 📋 개요

HongLabelSelectInput은 드롭다운 선택과 직접 입력을 모두 지원하는 복합 입력 위젯입니다. 주 라벨, 부가 설명, 선택 버튼, 그리고 선택적으로 표시할 수 있는 텍스트 입력 필드로 구성되어 있습니다. 선택 버튼을 클릭하면 피커 다이얼로그가 표시되며, 필요시 텍스트 필드를 추가로 표시하여 사용자가 직접 값을 입력할 수 있습니다. Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
label/select/
├── HongLabelSelectInputBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongLabelSelectInputOption.kt        # 옵션 데이터 클래스
├── HongLabelSelectInputCompose.kt       # Compose용 컴포넌트
└── HongLabelSelectInputView.kt          # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongLabelSelectInputBuilder
빌더 패턴을 사용하여 라벨 선택 입력 위젯의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 라벨 설정 (`label`, `labelColor`, `labelTypo`)
- 설명 설정 (`description`, `descriptionColor`, `descriptionTypo`)
- 입력 텍스트 설정 (`inputText`, `placeholder`)
- 버튼 텍스트 설정 (`buttonText`, `buttonTextColor`, `buttonTextTypo`)
- 선택 옵션 설정 (`selectList`, `selectPosition`)
- 숫자 전용 입력 모드 (`useOnlyNumber`)
- 직접 콜백 모드 (`useDirectCallback`)
- 입력 필드 표시 여부 (`showInput`)
- 콜백 설정 (`pickerCallback`, `inputCallback`)
- 옵션 복사 (`copy`)

### HongLabelSelectInputOption
라벨 선택 입력 위젯의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `label`: 주 라벨 텍스트
- `labelColorHex`: 라벨 색상 (기본값: `HongColor.BLACK_100`)
- `labelTypo`: 라벨 타이포그래피 (기본값: `HongTypo.BODY_15_B`)
- `description`: 부가 설명 텍스트
- `descriptionColorHex`: 설명 색상 (기본값: `HongColor.BLACK_60`)
- `descriptionTypo`: 설명 타이포그래피 (기본값: `HongTypo.CONTENTS_10`)
- `input`: 입력된 텍스트
- `placeholder`: Placeholder 텍스트
- `textFieldOption`: 텍스트 필드 상세 옵션
- `buttonText`: 선택 버튼 텍스트
- `buttonTextColorHex`: 버튼 텍스트 색상 (기본값: `HongColor.MAIN_ORANGE_100`)
- `buttonTextTypo`: 버튼 텍스트 타이포그래피 (기본값: `HongTypo.BODY_15`)
- `selectPosition`: 현재 선택된 위치 (기본값: 0)
- `selectList`: 선택 가능한 옵션 리스트
- `useOnlyNumber`: 숫자만 입력 가능 여부 (기본값: false)
- `useDirectCallback`: 다이얼로그 확인 없이 즉시 콜백 호출 여부 (기본값: false)
- `showInput`: 입력 필드 표시 여부 (기본값: false)
- `pickerCallback`: 피커에서 선택 시 콜백 `(선택값, 인덱스) -> Unit`
- `inputCallback`: 텍스트 입력 시 콜백 `(입력값) -> Unit`

**기본 텍스트 필드 스타일:**
- 배경색: `HongColor.BLACK_05`
- 높이: WRAP_CONTENT
- 모서리 둥글기: 10dp
- 내부 패딩: 상하 11dp, 좌우 10dp
- 상단 마진: 10dp
- 커서 색상: `HongColor.MAIN_ORANGE_100`

### HongLabelSelectInputCompose
Jetpack Compose에서 사용할 수 있는 라벨 선택 입력 컴포넌트입니다.

**구조:**
- Column 레이아웃 (세로 배치)
- HongLabel (주 라벨 + 부가 설명)
- HongButtonText (선택 버튼, 10dp 상단 마진, 48dp 높이)
- HongTextField (선택적 표시, 10dp 상단 마진)

**특징:**
- 버튼 클릭 시 OptionPickerDialog 표시
- 선택 시 pickerCallback 호출
- 입력 시 inputCallback 호출
- 상태 관리 자동화 (rememberSaveable)

### HongLabelSelectInputView
기존 View 시스템에서 사용할 수 있는 커스텀 LinearLayout 뷰입니다.

**구조:**
- Vertical LinearLayout
- HongLabelView (라벨이 있을 경우에만)
- HongButtonTextView (선택 버튼)
- HongTextFieldView (showInput이 true일 경우에만)

**주요 메서드:**
- `setSelectView(option)`: 선택 버튼만 있는 뷰 구성
- `setSelectInputView(option)`: 선택 버튼 + 입력 필드 뷰 구성
- `showInput()`: 입력 필드 표시
- `hideInput()`: 입력 필드 숨김
- `setInputText(input)`: 입력 텍스트 설정

## 🚀 사용법

### Jetpack Compose 사용 - 기본 선택

```kotlin
@Composable
fun SettingsScreen() {
    val options = listOf("옵션1", "옵션2", "옵션3")
    var selectedOption by remember { mutableStateOf(options.first()) }

    val option = HongLabelSelectInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("카테고리")
        .description("카테고리를 선택하세요")
        .buttonText(selectedOption)
        .selectList(options)
        .selectPosition(0)
        .pickerCallback { selected, index ->
            selectedOption = selected
        }
        .applyOption()

    HongLabelSelectInputCompose(option = option)
}
```

### Jetpack Compose 사용 - 선택 + 직접 입력

```kotlin
@Composable
fun SizeSettingScreen() {
    val sizeOptions = listOf("MATCH_PARENT", "WRAP_CONTENT", "직접 입력")
    var selectedSize by remember { mutableStateOf("MATCH_PARENT") }
    var customSize by remember { mutableStateOf("") }
    val showInputField = selectedSize == "직접 입력"

    val option = HongLabelSelectInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f,
                top = 10f,
                bottom = 10f
            )
        )
        .label("너비")
        .description("너비를 선택하거나 직접 입력하세요")
        .buttonText(selectedSize)
        .inputText(if (showInputField) customSize else selectedSize)
        .placeholder("숫자를 입력하세요")
        .selectList(sizeOptions)
        .selectPosition(sizeOptions.indexOf(selectedSize))
        .useOnlyNumber(true)
        .useDirectCallback(true)
        .showInput(showInputField)
        .pickerCallback { selected, index ->
            selectedSize = selected
        }
        .inputCallback { input ->
            customSize = input ?: ""
        }
        .applyOption()

    HongLabelSelectInputCompose(option = option)
}
```

### View 시스템 사용 - 선택 버튼만

```kotlin
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val languageOptions = listOf("한국어", "English", "日本語", "中文")
        var currentLanguage = languageOptions.first()

        val option = HongLabelSelectInputBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f,
                    top = 10f,
                    bottom = 10f
                )
            )
            .label("언어")
            .description("앱에서 사용할 언어를 선택하세요")
            .buttonText(currentLanguage)
            .selectList(languageOptions)
            .selectPosition(0)
            .pickerCallback { selected, index ->
                currentLanguage = selected
                // 언어 변경 처리
            }
            .applyOption()

        findViewById<HongLabelSelectInputView>(R.id.labelSelectInput)
            .setSelectView(option)
    }
}
```

### View 시스템 사용 - 선택 + 입력 필드

```kotlin
class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quantityOptions = listOf("1개", "2개", "3개", "직접 입력")
        var selectedQuantity = quantityOptions.first()
        var customQuantity = ""

        val option = HongLabelSelectInputBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f,
                    top = 10f,
                    bottom = 10f
                )
            )
            .label("수량")
            .description("구매 수량을 선택하거나 직접 입력하세요")
            .buttonText(selectedQuantity)
            .placeholder("수량 입력")
            .selectList(quantityOptions)
            .selectPosition(0)
            .useOnlyNumber(true)
            .showInput(selectedQuantity == "직접 입력")
            .pickerCallback { selected, index ->
                selectedQuantity = selected
                // showInput 상태 업데이트
                if (selected == "직접 입력") {
                    findViewById<HongLabelSelectInputView>(R.id.labelSelectInput)
                        .showInput()
                } else {
                    findViewById<HongLabelSelectInputView>(R.id.labelSelectInput)
                        .hideInput()
                }
            }
            .inputCallback { input ->
                customQuantity = input ?: ""
            }
            .applyOption()

        findViewById<HongLabelSelectInputView>(R.id.labelSelectInput)
            .setSelectInputView(option)
    }
}
```

### 숫자 전용 입력

```kotlin
val ageOptions = listOf("10대", "20대", "30대", "40대", "50대 이상", "직접 입력")

val option = HongLabelSelectInputBuilder()
    .label("나이")
    .description("연령대를 선택하거나 직접 입력하세요")
    .buttonText(ageOptions.first())
    .placeholder("나이를 입력하세요")
    .selectList(ageOptions)
    .selectPosition(0)
    .useOnlyNumber(true)  // 숫자만 입력 가능
    .showInput(false)
    .pickerCallback { selected, index ->
        // 선택 처리
    }
    .inputCallback { input ->
        // 입력 처리
    }
    .applyOption()
```

### 직접 콜백 모드 (확인 버튼 없이)

```kotlin
val colorOptions = listOf("빨강", "파랑", "초록", "노랑")

val option = HongLabelSelectInputBuilder()
    .label("색상")
    .buttonText(colorOptions.first())
    .selectList(colorOptions)
    .selectPosition(0)
    .useDirectCallback(true)  // 다이얼로그에서 선택 즉시 콜백 호출
    .pickerCallback { selected, index ->
        // 선택 즉시 처리됨 (확인 버튼 없음)
    }
    .applyOption()
```

## ⚙️ 주요 메서드

### HongLabelSelectInputBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `label(label)` | `String?` | 주 라벨 텍스트 설정 |
| `labelColor(color)` | `HongColor` 또는 `String` | 라벨 색상 설정 |
| `labelTypo(typo)` | `HongTypo` | 라벨 타이포그래피 설정 |
| `description(description)` | `String?` | 부가 설명 텍스트 설정 |
| `descriptionColor(color)` | `HongColor` 또는 `String` | 설명 색상 설정 |
| `descriptionTypo(typo)` | `HongTypo` | 설명 타이포그래피 설정 |
| `inputText(input)` | `String?` | 입력된 텍스트 설정 |
| `placeholder(placeholder)` | `String?` | Placeholder 텍스트 설정 |
| `buttonText(buttonText)` | `String?` | 선택 버튼 텍스트 설정 |
| `buttonTextColor(color)` | `HongColor` 또는 `String` | 버튼 텍스트 색상 설정 |
| `buttonTextTypo(typo)` | `HongTypo` | 버튼 텍스트 타이포그래피 설정 |
| `selectPosition(position)` | `Int` | 현재 선택된 위치 설정 |
| `selectList(list)` | `List<String>` | 선택 가능한 옵션 리스트 설정 |
| `useOnlyNumber(flag)` | `Boolean` | 숫자만 입력 가능 여부 설정 |
| `useDirectCallback(flag)` | `Boolean` | 다이얼로그 즉시 콜백 여부 설정 |
| `showInput(show)` | `Boolean` | 입력 필드 표시 여부 설정 |
| `pickerCallback(callback)` | `(String, Int) -> Unit` | 피커 선택 콜백 설정 |
| `inputCallback(callback)` | `(String?) -> Unit` | 텍스트 입력 콜백 설정 |
| `copy(inject)` | `HongLabelSelectInputOption` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongLabelSelectInputView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `setSelectView(option)` | `HongLabelSelectInputOption` | `HongLabelSelectInputView` | 선택 버튼만 있는 뷰 구성 |
| `setSelectInputView(option)` | `HongLabelSelectInputOption` | `HongLabelSelectInputView` | 선택 버튼 + 입력 필드 뷰 구성 |
| `showInput()` | - | `Unit` | 입력 필드 표시 |
| `hideInput()` | - | `Unit` | 입력 필드 숨김 |
| `setInputText(input)` | `String` | `Unit` | 입력 텍스트 설정 |

## 📝 참고사항

### 레이아웃 구조
- **전체 크기:** MATCH_PARENT × WRAP_CONTENT (기본값)
- **버튼 높이:** 48dp
- **버튼 모서리 둥글기:** 10dp
- **버튼 내부 패딩:** 상하 14dp
- **라벨-버튼 간격:** 10dp
- **버튼-입력 필드 간격:** 10dp
- **배치:** 세로 방향 (Column/Vertical LinearLayout)

### 기본값
- 라벨 타이포그래피: `HongTypo.BODY_15_B`
- 라벨 색상: `HongColor.BLACK_100` (#000000)
- 설명 타이포그래피: `HongTypo.CONTENTS_10`
- 설명 색상: `HongColor.BLACK_60` (60% 불투명도)
- 버튼 텍스트 타이포그래피: `HongTypo.BODY_15`
- 버튼 텍스트 색상: `HongColor.MAIN_ORANGE_100` (주황색)
- 버튼 테두리: 1dp, `HongColor.MAIN_ORANGE_100`
- 버튼 배경: `HongColor.WHITE_100` (흰색)
- 입력 필드 배경: `HongColor.BLACK_05` (5% 검은색)
- 선택 위치: 0 (첫 번째 항목)
- 숫자 전용 모드: false
- 직접 콜백 모드: false
- 입력 필드 표시: false

### 주의사항
- `selectList`가 비어있으면 피커 다이얼로그가 정상 동작하지 않습니다
- `selectPosition`은 `selectList`의 유효한 인덱스여야 합니다
- `useDirectCallback`을 true로 설정하면 다이얼로그에서 확인 버튼 없이 선택 즉시 콜백이 호출됩니다
- `useOnlyNumber`를 true로 설정하면 키보드가 숫자 키패드로 변경됩니다
- `showInput`이 false일 때는 입력 필드가 렌더링되지 않습니다
- View 시스템에서는 `setSelectView()`와 `setSelectInputView()` 중 하나를 선택하여 사용합니다
  - `setSelectView()`: 선택 버튼만 표시
  - `setSelectInputView()`: 선택 버튼 + 입력 필드 표시
- Compose에서는 상태 관리를 별도로 구현해야 합니다 (remember, mutableStateOf 등)
- 피커 다이얼로그의 제목은 자동으로 "[라벨] 옵션 선택" 형식으로 생성됩니다

### 의존성
- HongLabel (라벨 위젯)
- HongButtonText (텍스트 버튼 위젯)
- HongTextField (텍스트 입력 필드)
- OptionPickerDialog (선택 다이얼로그)
- HongColor, HongTypo (디자인 시스템)
- HongKeyboardType, HongKeyboardActionType (키보드 옵션)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)

### 사용 사례
- 설정 화면의 옵션 선택 (언어, 테마 등)
- 폼 입력에서 선택 + 직접 입력 (수량, 크기, 나이 등)
- 레이아웃 파라미터 선택 (MATCH_PARENT, WRAP_CONTENT, 직접 입력)
- 카테고리 선택 + 기타 입력
- 국가/지역 선택 + 상세 주소 입력
- 날짜 범위 선택 (오늘, 일주일, 한달, 직접 설정)
- 필터 옵션 선택 + 커스텀 값

### 상태 관리 예시 (Compose)

```kotlin
@Composable
fun DynamicInputScreen() {
    val sizeOptions = listOf("작게", "중간", "크게", "직접 입력")
    var selectedSize by remember { mutableStateOf(sizeOptions.first()) }
    var customSizeValue by remember { mutableStateOf("") }

    // 선택에 따라 입력 필드 표시 여부 결정
    val showInputField = selectedSize == "직접 입력"

    val option = HongLabelSelectInputBuilder()
        .label("폰트 크기")
        .description("원하는 크기를 선택하거나 직접 입력하세요")
        .buttonText(selectedSize)
        .inputText(if (showInputField) customSizeValue else "")
        .placeholder("크기 입력 (예: 16)")
        .selectList(sizeOptions)
        .selectPosition(sizeOptions.indexOf(selectedSize))
        .useOnlyNumber(true)
        .showInput(showInputField)
        .pickerCallback { selected, index ->
            selectedSize = selected
            // "직접 입력"이 아닌 경우 커스텀 값 초기화
            if (selected != "직접 입력") {
                customSizeValue = ""
            }
        }
        .inputCallback { input ->
            customSizeValue = input ?: ""
        }
        .applyOption()

    HongLabelSelectInputCompose(option = option)

    // 최종 값 계산
    val finalValue = if (showInputField) customSizeValue else selectedSize
}
```

### 비교: HongLabelSelectInput vs HongLabelInput
- **HongLabelSelectInput**: 라벨 + 선택 버튼 + 선택적 입력 필드 (선택과 입력 모두 지원)
- **HongLabelInput**: 라벨 + 입력 필드 (입력만 지원)

HongLabelSelectInput은 사전 정의된 옵션 중 선택하거나 사용자가 직접 값을 입력할 수 있는 시나리오에 적합합니다.

### 피커 다이얼로그 동작
- **기본 모드 (useDirectCallback = false)**:
  - 선택 후 "확인" 버튼을 눌러야 콜백 호출
  - "취소" 버튼으로 선택 취소 가능
- **직접 콜백 모드 (useDirectCallback = true)**:
  - 옵션 선택 즉시 콜백 호출 및 다이얼로그 닫힘
  - 빠른 선택이 필요한 경우에 유용

### XML 레이아웃

```xml
<com.codehong.library.widget.label.select.HongLabelSelectInputView
    android:id="@+id/labelSelectInput"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```
