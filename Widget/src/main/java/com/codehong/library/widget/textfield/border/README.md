# HongTextFieldBorder

테두리가 있는 텍스트 입력 필드 위젯입니다. 라벨, 플레이스홀더, 도움말 텍스트, 필수 입력 표시, 지우기 버튼 등 완전한 폼 입력 UI를 제공합니다.

## 📋 개요

HongTextFieldBorder는 폼 입력을 위한 완전한 기능을 갖춘 텍스트 필드 위젯입니다. 포커스 상태에 따라 테두리 색상이 변경되며, 활성화/비활성화 상태를 지원합니다.

**주요 특징:**
- 포커스 상태에 따른 테두리 색상 자동 변경
- 라벨 + 필수 입력 표시(*)
- 플레이스홀더 지원
- 도움말 텍스트 표시
- 지우기 버튼 자동 표시/숨김
- 접미사(suffix) 표시 (예: "원", "kg")
- 숫자 키패드 지원
- ENABLE/DISABLE 상태 관리
- View/Compose 양쪽 시스템 지원

## 🏗️ 구조

```
border/
├── HongTextFieldBorderBuilder.kt    # 빌더 패턴 구성 클래스
├── HongTextFieldBorderOption.kt     # 데이터 클래스 (옵션 보유)
├── HongTextFieldBorderCompose.kt    # Jetpack Compose 구현
└── HongTextFieldBorderView.kt       # View 시스템 래퍼
```

## 📦 주요 클래스

### HongTextFieldBorderBuilder
빌더 패턴을 사용하여 텍스트 필드를 구성하는 클래스입니다.

**주요 메서드:**
- `inputRadius(HongRadiusInfo)`: 입력 필드 모서리 둥글기 설정
- `enableBorderColor(HongColor)`: 일반 상태 테두리 색상
- `focusedBorderColor(HongColor)`: 포커스 상태 테두리 색상
- `inputBackgroundColor(HongColor)`: 입력 필드 배경색
- `label(String)`: 라벨 텍스트
- `labelTypo(HongTypo)`: 라벨 타이포그래피
- `initialInput(String)`: 초기 입력값
- `inputTextColor(HongColor)`: 입력 텍스트 색상
- `placeholder(String)`: 플레이스홀더 텍스트
- `placeholderColor(HongColor)`: 플레이스홀더 색상
- `helperText(String)`: 도움말 텍스트
- `isRequired(Boolean)`: 필수 입력 표시 여부
- `state(HongInputState)`: 입력 상태 (ENABLE/DISABLE)
- `suffix(String)`: 접미사 (예: "원", "kg")
- `useClearButton(Boolean)`: 지우기 버튼 사용 여부
- `useNumberKeypad(Boolean)`: 숫자 키패드 사용 여부
- `onChangeInput((String) -> Unit)`: 입력 변경 콜백

### HongTextFieldBorderOption
텍스트 필드의 모든 설정값을 포함하는 데이터 클래스입니다.

**기본값:**
- 입력 필드 모서리: 12dp (모든 모서리)
- 일반 테두리: GRAY_20 (#E8E8E8)
- 포커스 테두리: BLACK_80 (#5F5F5F)
- 배경색: WHITE_100 (#FFFFFF)
- 라벨 타이포: CONTENTS_12
- 플레이스홀더 타이포: BODY_16
- 도움말 타이포: CONTENTS_10
- 지우기 버튼: true (기본 사용)
- 숫자 키패드: false

### HongTextFieldBorderCompose
Jetpack Compose에서 사용할 수 있는 텍스트 필드 컴포넌트입니다.

**구조:**
- HongWidgetContainer (공통 컨테이너)
  - Column
    - Row (입력 필드 영역)
      - Column (라벨 + 입력 영역)
        - Row (라벨 + 필수 표시)
        - Box (BasicTextField + 플레이스홀더)
      - Row (접미사 + 지우기 버튼)
    - HongText (도움말 텍스트)

**주요 기능:**
- 포커스 상태 감지: `MutableInteractionSource`
- 상태별 색상 자동 변경: 테두리, 배경, 텍스트
- 키보드 액션: IME Done 시 포커스 해제
- 커서 색상: MAIN_ORANGE_100
- SingleLine 입력

### HongTextFieldBorderView
기존 View 시스템에서 사용할 수 있는 래퍼 클래스입니다.

**주요 기능:**
- ComposeView 반환
- Context 기반 생성

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun UserInfoForm() {
    var name by remember { mutableStateOf("") }

    val option = HongTextFieldBorderBuilder()
        .label("이름")
        .placeholder("이름을 입력하세요")
        .helperText("실명을 입력해주세요")
        .isRequired(true)
        .initialInput(name)
        .onChangeInput { newValue ->
            name = newValue
        }
        .applyOption()

    HongTextFieldBorderCompose(option = option)
}
```

### 커스텀 스타일

```kotlin
val option = HongTextFieldBorderBuilder()
    .inputRadius(HongRadiusInfo(8, 8, 8, 8))
    .enableBorderColor(HongColor.GRAY_30)
    .focusedBorderColor(HongColor.MAIN_BLUE_100)
    .inputBackgroundColor(HongColor.WHITE_100)
    .label("금액")
    .labelTypo(HongTypo.CONTENTS_14)
    .placeholder("금액을 입력하세요")
    .suffix("원")
    .useNumberKeypad(true)
    .useClearButton(true)
    .onChangeInput { value ->
        Log.d("TextField", "입력값: $value")
    }
    .applyOption()
```

### 비활성화 상태

```kotlin
val option = HongTextFieldBorderBuilder()
    .label("이메일")
    .initialInput("user@example.com")
    .state(HongInputState.DISABLE)
    .applyOption()
```

### View 시스템 사용

```kotlin
val option = HongTextFieldBorderBuilder()
    .label("전화번호")
    .placeholder("010-0000-0000")
    .useNumberKeypad(true)
    .applyOption()

val textFieldView = HongTextFieldBorderView(context).set(option)
container.addView(textFieldView)
```

## ⚙️ 주요 메서드

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `inputRadius()` | HongRadiusInfo | 입력 필드 모서리 둥글기 설정 |
| `enableBorderColor()` | HongColor / String | 일반 상태 테두리 색상 |
| `focusedBorderColor()` | HongColor / String | 포커스 상태 테두리 색상 |
| `inputBackgroundColor()` | HongColor / String | 입력 필드 배경색 |
| `label()` | String | 라벨 텍스트 |
| `labelColorHex()` | String | 라벨 색상 (Hex) |
| `labelTypo()` | HongTypo | 라벨 타이포그래피 |
| `initialInput()` | String | 초기 입력값 |
| `inputTextColor()` | HongColor / String | 입력 텍스트 색상 |
| `placeholder()` | String | 플레이스홀더 텍스트 |
| `placeholderColor()` | HongColor / String | 플레이스홀더 색상 |
| `placeholderTypo()` | HongTypo | 플레이스홀더 타이포그래피 |
| `helperText()` | String | 도움말 텍스트 |
| `helperTextTypo()` | HongTypo | 도움말 타이포그래피 |
| `isRequired()` | Boolean | 필수 입력 표시 여부 |
| `state()` | HongInputState | 입력 상태 (ENABLE/DISABLE) |
| `suffix()` | String | 접미사 텍스트 |
| `suffixTypo()` | HongTypo | 접미사 타이포그래피 |
| `useClearButton()` | Boolean | 지우기 버튼 표시 여부 |
| `useNumberKeypad()` | Boolean | 숫자 키패드 사용 여부 |
| `onChangeInput()` | (String) -> Unit | 입력 변경 콜백 |

## 📝 참고사항

### 포커스 상태별 색상
- **일반 상태**: enableBorderColor 적용
- **포커스 상태**: focusedBorderColor 적용
- **비활성화 상태**: 모든 요소가 GRAY 계열로 변경

### 지우기 버튼
- 입력값이 있을 때만 표시됩니다
- ENABLE 상태에서만 동작합니다
- `useClearButton(false)`로 비활성화 가능

### 필수 입력 표시
- `isRequired(true)` 설정 시 라벨 옆에 주황색 `*` 표시
- ENABLE 상태에서만 표시됩니다

### 접미사(Suffix)
- 입력 필드 오른쪽에 표시됩니다
- 금액 단위, 무게 단위 등에 활용
- 지우기 버튼과 함께 표시될 수 있습니다

### 도움말 텍스트
- 입력 필드 아래에 표시됩니다
- 최대 2줄까지 표시됩니다
- 입력 가이드, 에러 메시지 등에 활용

### 숫자 키패드
- `useNumberKeypad(true)` 설정 시 숫자 전용 키패드 표시
- 전화번호, 금액, 수량 입력 등에 활용

### 상태 관리
- `HongInputState.ENABLE`: 입력 가능
- `HongInputState.DISABLE`: 입력 불가능, 회색으로 표시

### 커서 색상
- 기본 커서 색상: MAIN_ORANGE_100
- 변경 불가능 (고정값)

## 💡 사용 팁

- 폼 입력 화면의 완전한 텍스트 필드 구현에 최적화
- 라벨 + 입력 + 도움말이 하나의 컴포넌트로 제공됨
- 필수/선택 입력 구분이 필요한 경우 `isRequired` 활용
- 숫자 입력 시 `useNumberKeypad(true)` 설정으로 UX 개선
- 접미사를 활용하여 단위 표시 가능
- DISABLE 상태로 읽기 전용 필드 구현 가능
