# HongTextFieldBorderSelect

라벨, 설명, 선택 버튼, 텍스트 입력 필드를 결합한 복합 선택 입력 위젯입니다. 피커 다이얼로그, 직접 입력 모드, 숫자 전용 입력을 지원합니다.

## 📋 개요

HongTextFieldBorderSelect는 선택과 입력을 동시에 지원하는 폼 입력 위젯입니다. 라벨, 필수 표시, 플레이스홀더, 헬퍼 텍스트 등 완전한 폼 입력 UI를 제공하며, 선택 버튼을 통한 피커 다이얼로그 연동과 직접 텍스트 입력을 모두 지원합니다.

**주요 특징:**
- 라벨과 필수 표시 (*) 지원
- 선택 버튼 (우측 화살표 아이콘)
- 직접 입력 모드 지원 (useDirectInput)
- 숫자 키패드 옵션 (useNumberKeypad)
- 포커스/활성화 상태에 따른 테두리 색상 자동 변경
- ENABLE/DISABLE 상태 지원
- 헬퍼 텍스트 지원
- Compose 전용 (View 버전 없음)

## 🏗️ 구조

```
select/
├── HongTextFieldBorderSelectBuilder.kt    # 빌더 패턴 구성 클래스
├── HongTextFieldBorderSelectOption.kt     # 데이터 클래스 (옵션 보유)
└── HongTextFieldBorderSelect.kt           # Jetpack Compose 구현
```

## 📦 주요 클래스

### HongTextFieldBorderSelectBuilder
빌더 패턴을 사용하여 선택 입력 필드를 구성하는 클래스입니다.

**주요 메서드:**
- `label(String)`: 라벨 텍스트
- `isRequired(Boolean)`: 필수 입력 표시 여부
- `placeholder(String)`: 플레이스홀더 텍스트
- `helperText(String)`: 도움말 텍스트
- `useDirectInput(Boolean)`: 직접 입력 모드 활성화
- `useNumberKeypad(Boolean)`: 숫자 키패드 사용 여부
- `state(HongInputState)`: 입력 필드 상태 (ENABLE/DISABLE)
- `enableBorderColor(HongColor)`: 기본 테두리 색상
- `focusedBorderColor(HongColor)`: 포커스 시 테두리 색상
- `onSelectionClick(() -> Unit)`: 선택 버튼 클릭 콜백
- `onChangeInput((String) -> Unit)`: 텍스트 변경 콜백

### HongTextFieldBorderSelectOption
선택 입력 필드의 모든 설정값을 포함하는 데이터 클래스입니다.

**기본값:**
- 라벨: CONTENTS_12, BLACK_100
- 입력 텍스트: BODY_16, BLACK_100
- 플레이스홀더: BODY_16, GRAY_50
- 헬퍼 텍스트: CONTENTS_10
- 입력 배경색: WHITE_100
- 기본 테두리: GRAY_20 (1px)
- 포커스 테두리: BLACK_80 (1px)
- 라운드: 12dp (전체)
- 상태: ENABLE
- 필수 표시: false
- 직접 입력: false
- 숫자 키패드: false

### HongTextFieldBorderSelect
Jetpack Compose에서 사용할 수 있는 선택 입력 필드 컴포넌트입니다.

**구조:**
- HongWidgetContainer (공통 컨테이너)
  - Column
    - Row (입력 영역)
      - Column (weight 1f)
        - Row (라벨 + 필수 표시)
        - Box (BasicTextField + 플레이스홀더)
      - Box (선택 버튼 - 화살표 아이콘)
    - HongTextCompose (헬퍼 텍스트, 조건부 표시)

**주요 기능:**
- 포커스 상태 감지 (MutableInteractionSource)
- 상태별 색상 자동 변경:
  - DISABLE: GRAY_10 배경, GRAY_30 텍스트
  - ENABLE + 포커스: focusedBorderColor
  - ENABLE: enableBorderColor
- 직접 입력 모드:
  - useDirectInput = true: 텍스트 입력 가능
  - useDirectInput = false: 읽기 전용 (선택만 가능)
- 숫자 키패드:
  - useNumberKeypad = true: KeyboardType.Number
  - useNumberKeypad = false: KeyboardType.Text
- 필수 표시: isRequired && isEnabled일 때만 * 표시
- 단일 줄 입력 (singleLine = true)
- 커서 색상: MAIN_ORANGE_100

## 🚀 사용법

### Jetpack Compose 사용

**1. 기본 선택 입력 (피커 다이얼로그 연동)**

```kotlin
@Composable
fun DatePicker() {
    var selectedDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val option = HongTextFieldBorderSelectBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .label("여행 날짜")
        .placeholder("날짜를 선택하세요")
        .isRequired(true)
        .initialInput(selectedDate)
        .useDirectInput(false) // 선택만 가능
        .onSelectionClick {
            showDialog = true
        }
        .applyOption()

    HongTextFieldBorderSelect(option = option)

    if (showDialog) {
        // DatePickerDialog 표시
        DatePickerDialog(
            onDateSelected = { date ->
                selectedDate = date
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}
```

**2. 직접 입력 모드 (선택 + 입력 모두 가능)**

```kotlin
val option = HongTextFieldBorderSelectBuilder()
    .label("수량")
    .placeholder("수량을 입력하거나 선택하세요")
    .isRequired(true)
    .useDirectInput(true) // 직접 입력 가능
    .useNumberKeypad(true) // 숫자 키패드
    .onChangeInput { quantity ->
        // 텍스트 입력 시 호출
        Log.d("Input", quantity)
    }
    .onSelectionClick {
        // 선택 버튼 클릭 시 호출
        // 수량 선택 다이얼로그 표시
    }
    .applyOption()

HongTextFieldBorderSelect(option = option)
```

**3. 헬퍼 텍스트 추가**

```kotlin
val option = HongTextFieldBorderSelectBuilder()
    .label("결제 수단")
    .placeholder("결제 수단을 선택하세요")
    .helperText("등록된 카드 중 선택하거나 새 카드를 등록하세요")
    .useDirectInput(false)
    .onSelectionClick {
        // 결제 수단 선택 다이얼로그
    }
    .applyOption()
```

**4. DISABLE 상태**

```kotlin
val option = HongTextFieldBorderSelectBuilder()
    .label("배송지")
    .placeholder("배송지를 선택하세요")
    .initialInput("서울시 강남구...")
    .state(HongInputState.DISABLE) // 비활성화
    .applyOption()
```

**5. 커스텀 스타일**

```kotlin
val option = HongTextFieldBorderSelectBuilder()
    .width(HongLayoutParam.MATCH_PARENT.value)
    .margin(HongSpacingInfo(16f, 16f, 16f, 16f))
    .label("카테고리")
    .labelTypo(HongTypo.SUBTITLE_14)
    .labelColorHex(HongColor.BLACK_100.hex)
    .placeholder("카테고리 선택")
    .placeholderTypo(HongTypo.BODY_16)
    .placeholderColor(HongColor.GRAY_50)
    .inputRadius(HongRadiusInfo(all = 8))
    .enableBorderColor(HongColor.GRAY_30)
    .focusedBorderColor(HongColor.MAIN_ORANGE_100)
    .inputBackgroundColor(HongColor.WHITE_100)
    .isRequired(true)
    .useDirectInput(false)
    .onSelectionClick {
        // 카테고리 선택
    }
    .applyOption()
```

## ⚙️ 주요 메서드

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `label()` | String | 라벨 텍스트 |
| `labelTypo()` | HongTypo | 라벨 타이포그래피 |
| `labelColorHex()` | String | 라벨 색상 |
| `isRequired()` | Boolean | 필수 입력 표시 여부 |
| `placeholder()` | String | 플레이스홀더 텍스트 |
| `placeholderTypo()` | HongTypo | 플레이스홀더 타이포그래피 |
| `placeholderColor()` | HongColor / String | 플레이스홀더 색상 |
| `helperText()` | String | 도움말 텍스트 |
| `helperTextTypo()` | HongTypo | 도움말 타이포그래피 |
| `initialInput()` | String | 초기 입력값 |
| `inputTextColor()` | HongColor / String | 입력 텍스트 색상 |
| `inputRadius()` | HongRadiusInfo | 입력 필드 모서리 둥글기 |
| `inputBackgroundColor()` | HongColor / String | 입력 필드 배경색 |
| `enableBorderColor()` | HongColor / String | 기본 테두리 색상 |
| `focusedBorderColor()` | HongColor / String | 포커스 시 테두리 색상 |
| `state()` | HongInputState | 입력 필드 상태 (ENABLE/DISABLE) |
| `useDirectInput()` | Boolean | 직접 입력 모드 활성화 |
| `useNumberKeypad()` | Boolean | 숫자 키패드 사용 여부 |
| `onSelectionClick()` | () -> Unit | 선택 버튼 클릭 콜백 |
| `onChangeInput()` | (String) -> Unit | 텍스트 변경 콜백 |

## 📝 참고사항

### 선택 모드 vs 직접 입력 모드

- **useDirectInput = false** (기본값):
  - 텍스트 입력 불가 (readOnly)
  - 선택 버튼 클릭으로만 값 설정
  - 날짜, 시간, 옵션 선택 등에 적합

- **useDirectInput = true**:
  - 텍스트 직접 입력 가능
  - 선택 버튼으로도 값 설정 가능
  - 수량, 금액 등 입력과 선택 모두 필요한 경우에 적합

### 필수 표시

- `isRequired(true)`로 설정 시 라벨 옆에 주황색 `*` 표시
- DISABLE 상태에서는 `*` 표시 안 됨
- 필수 입력 유효성 검사는 별도로 구현 필요

### 상태별 색상 변경

자동으로 상태에 따라 색상이 변경됩니다:

| 상태 | 배경색 | 테두리 | 텍스트 색상 |
|------|--------|--------|-------------|
| ENABLE (기본) | inputBackgroundColor | enableBorderColor | inputTextColor |
| ENABLE (포커스) | inputBackgroundColor | focusedBorderColor | inputTextColor |
| DISABLE | GRAY_10 | GRAY_10 | GRAY_30 |

### 헬퍼 텍스트

- `helperText`를 설정하면 입력 필드 하단에 표시
- 최대 2줄까지 표시
- 사용 안내, 제약 조건, 에러 메시지 등에 활용

### 선택 버튼

- 우측에 고정된 60dp x 52dp 영역
- 화살표 아이콘 (20x20, GRAY_30)
- 클릭 시 `onSelectionClick` 콜백 호출
- 피커 다이얼로그, 바텀시트 등과 연동

### 숫자 키패드

- `useNumberKeypad(true)`: 숫자 전용 키패드 표시
- 수량, 금액, 전화번호 등 숫자 입력에 활용
- 자동 포맷팅은 지원하지 않음 (직접 구현 필요)

### 단일 줄 고정

- 항상 단일 줄 입력 (singleLine = true)
- 여러 줄 입력이 필요한 경우 HongTextFieldBorder 사용

### 커서 색상

- 기본 커서 색상: MAIN_ORANGE_100 (고정)
- 변경 불가

## 💡 사용 팁

- 날짜/시간 선택: `useDirectInput(false)` + DatePickerDialog 연동
- 옵션 선택: `useDirectInput(false)` + BottomSheet 연동
- 수량 입력: `useDirectInput(true)` + `useNumberKeypad(true)` + 선택 버튼으로 빠른 수량 선택
- 주소 입력: `useDirectInput(false)` + 우편번호 검색 API 연동
- 카테고리 선택: `useDirectInput(false)` + 트리 구조 다이얼로그
- 헬퍼 텍스트로 입력 가이드 제공
- 필수 입력은 `isRequired(true)` + 유효성 검사 조합
- DISABLE 상태로 조건부 입력 제어

## 🔄 다른 TextField와의 차이점

| 항목 | HongTextField | HongTextFieldBorder | HongTextFieldBorderSelect |
|------|---------------|---------------------|---------------------------|
| 용도 | 심플한 입력 필드 | 완전한 폼 입력 UI | 선택+입력 복합 UI |
| 라벨 | ❌ 없음 | ✅ 있음 | ✅ 있음 |
| 선택 버튼 | ❌ 없음 | ❌ 없음 | ✅ 있음 (화살표) |
| 직접 입력 | ✅ 항상 가능 | ✅ 항상 가능 | ⚙️ 선택 가능 (useDirectInput) |
| 필수 표시 | ❌ 없음 | ✅ 있음 | ✅ 있음 |
| 헬퍼 텍스트 | ❌ 없음 | ✅ 있음 | ✅ 있음 |
| 상태 관리 | ❌ 없음 | ✅ 있음 | ✅ 있음 |
| View 지원 | ✅ 있음 | ✅ 있음 | ❌ 없음 (Compose만) |
| 추천 사용처 | 검색창, 댓글 | 이름, 이메일, 비밀번호 | 날짜, 카테고리, 옵션 선택 |
