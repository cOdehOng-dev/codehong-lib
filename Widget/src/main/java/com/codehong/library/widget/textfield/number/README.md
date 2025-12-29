# HongTextFieldNumber

숫자 전용 텍스트 입력 필드 위젯입니다. 자동 천 단위 콤마 포맷팅, 숫자 키패드, 지우기 버튼 등을 제공합니다.

## 📋 개요

HongTextFieldNumber는 숫자 입력에 특화된 텍스트 필드입니다. 숫자 키패드를 자동으로 표시하고, 선택적으로 천 단위 콤마 포맷팅을 적용하여 가격, 수량, 전화번호 등의 입력에 최적화되어 있습니다.

**주요 특징:**
- 숫자 전용 키패드 (NUMBER 키보드)
- 자동 천 단위 콤마 포맷팅 (선택 가능)
- 플레이스홀더 지원
- 선택적 지우기 버튼
- 단일 줄 입력 (고정)
- 커서 색상 커스터마이징
- View/Compose 양쪽 시스템 지원

## 🏗️ 구조

```
number/
├── HongTextFieldNumberBuilder.kt    # 빌더 패턴 구성 클래스
├── HongTextFieldNumberOption.kt     # 데이터 클래스 (옵션 보유)
├── HongTextFieldNumberCompose.kt    # Jetpack Compose 구현
└── HongTextFieldNumberView.kt       # View 시스템 구현
```

## 📦 주요 클래스

### HongTextFieldNumberBuilder
빌더 패턴을 사용하여 숫자 입력 필드를 구성하는 클래스입니다.

**주요 메서드:**
- `placeholder(String)`: 플레이스홀더 텍스트
- `placeholderColor(HongColor)`: 플레이스홀더 색상
- `placeholderTypo(HongTypo)`: 플레이스홀더 타이포그래피
- `input(String)`: 초기 입력값
- `inputTypo(HongTypo)`: 입력 텍스트 타이포그래피
- `inputColor(HongColor)`: 입력 텍스트 색상
- `clearIconRes(Int)`: 지우기 아이콘 리소스 ID
- `clearIconSize(Int)`: 지우기 아이콘 크기
- `clearIconScaleType(HongScaleType)`: 지우기 아이콘 스케일 타입
- `clearIconMargin(HongSpacingInfo)`: 지우기 아이콘 여백
- `cursorColor(HongColor)`: 커서 색상
- `useHideKeyboard(Boolean)`: 완료 시 키보드 숨기기 여부
- `keyboardOption(Pair<Type, Action>)`: 키보드 옵션 (타입은 항상 NUMBER로 강제)
- `useDecimal(Boolean)`: 천 단위 콤마 포맷팅 사용 여부
- `onTextChanged((String) -> Unit)`: 텍스트 변경 콜백

### HongTextFieldNumberOption
숫자 입력 필드의 모든 설정값을 포함하는 데이터 클래스입니다.

**기본값:**
- 배경색: BLACK_05 (#F2F2F2)
- 플레이스홀더: BODY_16, BLACK_30
- 입력 텍스트: BODY_16_B, BLACK_100
- 지우기 아이콘: 크기 20, 좌측 여백 8f
- 커서: MAIN_ORANGE_100
- 키보드: NUMBER + DONE
- 키보드 숨기기: true
- 천 단위 콤마: true (useDecimal)

### HongTextFieldNumberCompose
Jetpack Compose에서 사용할 수 있는 숫자 입력 필드 컴포넌트입니다.

**구조:**
- HongWidgetContainer (공통 컨테이너)
  - Row (입력 필드 + 지우기 버튼)
    - Box (BasicTextField + 플레이스홀더)
    - HongImageCompose (지우기 아이콘, 조건부 표시)

**주요 기능:**
- TextFieldValue 사용 (커서 위치 제어)
- 자동 숫자 포맷팅:
  1. 입력값에서 콤마/공백 제거
  2. Long으로 파싱
  3. DecimalFormat("#,###")으로 포맷팅
  4. NumberFormatException 발생 시 빈 문자열
  5. 커서를 끝으로 이동
- isFormatting 플래그로 재귀 업데이트 방지
- 단일 줄 고정 (maxLines = 1)
- 키보드 타입 NUMBER 강제
- rememberSaveable로 상태 유지

### HongTextFieldNumberView
기존 View 시스템에서 사용할 수 있는 LinearLayout 기반 뷰입니다.

**구조:**
- LinearLayout (Horizontal)
  - AppCompatEditText (weight 1f)
  - HongImageView (지우기 버튼, 조건부 표시)

**주요 기능:**
- TextWatcher로 텍스트 변경 감지
- afterTextChanged에서 숫자 포맷팅:
  1. 콤마 제거
  2. Long으로 파싱
  3. useDecimal이 true면 DecimalFormat("#,###") 적용
  4. false면 파싱된 숫자를 그대로 문자열로 변환
  5. 커서를 끝으로 이동
- isFormatting 플래그로 재귀 업데이트 방지
- 지우기 버튼 visibility 자동 관리
- Enter 키로 키보드 숨기기
- 폰트 자동 전환

## 🚀 사용법

### Jetpack Compose 사용

**1. 기본 숫자 입력 (천 단위 콤마 적용)**

```kotlin
@Composable
fun PriceInput() {
    val option = HongTextFieldNumberBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(HongSpacingInfo(16f, 16f, 12f, 12f))
        .backgroundColor(HongColor.BLACK_05)
        .placeholder("가격을 입력하세요")
        .useDecimal(true) // 천 단위 콤마 적용
        .onTextChanged { price ->
            // "1,234,567" 형태로 콜백됨
            Log.d("Price", price)
        }
        .applyOption()

    HongNumberTextFieldCompose(option = option)
}
```

**2. 지우기 버튼 추가**

```kotlin
val option = HongTextFieldNumberBuilder()
    .placeholder("수량 입력")
    .clearIconRes(R.drawable.ic_clear)
    .clearIconSize(20)
    .clearIconMargin(HongSpacingInfo(left = 8f))
    .useDecimal(false) // 콤마 없이 순수 숫자만
    .onTextChanged { quantity ->
        // "1234" 형태로 콜백됨
        Log.d("Quantity", quantity)
    }
    .applyOption()
```

**3. 초기값 설정**

```kotlin
val option = HongTextFieldNumberBuilder()
    .placeholder("금액")
    .input("53600") // 초기값 설정
    .useDecimal(true)
    .onTextChanged { amount ->
        // 포맷팅 적용: "53,600"
    }
    .applyOption()
```

**4. 커스텀 스타일**

```kotlin
val option = HongTextFieldNumberBuilder()
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(48)
    .backgroundColor(HongColor.WHITE_100.hex)
    .radius(HongRadiusInfo(all = 12))
    .border(HongBorderInfo(width = 1, color = HongColor.GRAY_20.hex))
    .placeholder("전화번호 (숫자만)")
    .placeholderTypo(HongTypo.BODY_14)
    .inputTypo(HongTypo.BODY_16_B)
    .inputColor(HongColor.BLACK_100)
    .cursorColor(HongColor.MAIN_ORANGE_100)
    .useDecimal(false)
    .onTextChanged { phone ->
        // 콤마 없는 순수 숫자
    }
    .applyOption()
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<FrameLayout
    android:id="@+id/numberInputContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
val option = HongTextFieldNumberBuilder()
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(44)
    .padding(HongSpacingInfo(16f, 16f, 12f, 12f))
    .backgroundColor(HongColor.BLACK_05.hex)
    .placeholder("가격을 입력하세요")
    .clearIconRes(R.drawable.ic_clear)
    .useDecimal(true)
    .onTextChanged { price ->
        // 포맷팅된 가격: "1,234,567"
        Log.d("Price", price)
    }
    .applyOption()

val numberFieldView = HongTextFieldNumberView(context).set(option)
findViewById<FrameLayout>(R.id.numberInputContainer).addView(numberFieldView)
```

## ⚙️ 주요 메서드

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `placeholder()` | String | 플레이스홀더 텍스트 |
| `placeholderColor()` | HongColor / String | 플레이스홀더 색상 |
| `placeholderTypo()` | HongTypo | 플레이스홀더 타이포그래피 |
| `input()` | String | 초기 입력값 |
| `inputTypo()` | HongTypo | 입력 텍스트 타이포그래피 |
| `inputColor()` | HongColor / String | 입력 텍스트 색상 |
| `clearIconRes()` | Int | 지우기 아이콘 리소스 ID |
| `clearIconSize()` | Int | 지우기 아이콘 크기 |
| `clearIconScaleType()` | HongScaleType | 지우기 아이콘 스케일 타입 |
| `clearIconMargin()` | HongSpacingInfo | 지우기 아이콘 여백 |
| `cursorColor()` | HongColor / String | 커서 색상 |
| `useHideKeyboard()` | Boolean | 완료 시 키보드 숨기기 |
| `keyboardOption()` | Pair<Type, Action> | 키보드 옵션 (타입은 NUMBER 고정) |
| `useDecimal()` | Boolean | 천 단위 콤마 포맷팅 사용 여부 |
| `onTextChanged()` | (String) -> Unit | 텍스트 변경 콜백 |

## 📝 참고사항

### 숫자 키패드
- 키보드 타입은 항상 **NUMBER**로 고정됩니다
- `keyboardOption()`에서 타입을 변경해도 NUMBER로 강제됨
- 액션 타입(DONE, NEXT 등)만 변경 가능

### 천 단위 콤마 포맷팅
- `useDecimal(true)`: 자동으로 천 단위 콤마 추가 (예: "1,234,567")
- `useDecimal(false)`: 순수 숫자만 표시 (예: "1234567")
- 포맷팅은 입력 즉시 실시간으로 적용됩니다
- 콜백 함수에 전달되는 값도 포맷팅된 문자열입니다

### 입력값 처리
- 입력 시 콤마와 공백은 자동 제거
- Long 타입으로 파싱 (Int 범위 초과 가능)
- 숫자가 아닌 문자 입력 시 빈 문자열로 처리
- 포맷팅 후 커서는 항상 끝으로 이동

### 지우기 버튼
- `clearIconRes`를 설정하면 지우기 버튼 활성화
- 입력값이 있을 때만 자동으로 표시
- 클릭 시 입력값 전체 삭제

### 단일 줄 고정
- 숫자 입력은 항상 단일 줄입니다
- `singleLine`, `maxLines`, `minLines` 옵션 없음
- Enter 키 입력 시 키보드 닫힘

### 커서 색상
- 기본 커서 색상: MAIN_ORANGE_100
- `cursorColor()`로 변경 가능

### 초기값 설정
- `input()` 메서드로 초기값 설정
- 초기값도 포맷팅 규칙에 따라 자동 포맷팅됨
- 예: `input("53600")` → 화면에 "53,600" 표시 (useDecimal=true 시)

### 상태 유지
- Compose: `rememberSaveable`로 화면 회전 시에도 상태 유지
- View: EditText의 기본 상태 복원 메커니즘 사용

## 💡 사용 팁

- 가격 입력: `useDecimal(true)` + `placeholder("가격을 입력하세요")`
- 수량 입력: `useDecimal(false)` + `placeholder("수량")`
- 전화번호 입력: `useDecimal(false)` + `keyboardOption(NUMBER, DONE)`
- 계좌번호 입력: `useDecimal(false)` (콤마 없이)
- 우편번호 입력: `useDecimal(false)` + 간단한 스타일
- 지우기 버튼으로 사용자 편의성 향상
- 포맷팅된 값을 서버로 보낼 때는 콤마 제거 필요: `price.replace(",", "")`

## 🔄 다른 TextField와의 차이점

| 항목 | HongTextField | HongTextFieldNumber | HongTextFieldBorder |
|------|---------------|---------------------|---------------------|
| 용도 | 일반 텍스트 입력 | 숫자 전용 입력 | 완전한 폼 입력 UI |
| 키보드 | 모든 타입 지원 | NUMBER 고정 | 모든 타입 지원 |
| 포맷팅 | ❌ 없음 | ✅ 천 단위 콤마 | ❌ 없음 |
| 디바운스 | ✅ 있음 | ❌ 없음 | ❌ 없음 |
| 라벨 | ❌ 없음 | ❌ 없음 | ✅ 있음 |
| 여러 줄 | ✅ 지원 | ❌ 단일 줄 고정 | ✅ 지원 |
| 추천 사용처 | 검색, 댓글 | 가격, 수량, 전화번호 | 회원가입, 설정 폼 |
