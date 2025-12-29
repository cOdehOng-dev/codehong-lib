# HongTextField

심플한 텍스트 입력 필드 위젯입니다. 플레이스홀더, 지우기 버튼, 디바운스 입력 콜백 등의 기본 기능을 제공합니다.

## 📋 개요

HongTextField는 단순하고 깔끔한 텍스트 입력 필드입니다. 복잡한 레이아웃 없이 순수한 입력 기능에 집중하며, 검색창, 댓글 입력, 간단한 폼 입력 등에 활용할 수 있습니다.

**주요 특징:**
- 심플한 디자인의 텍스트 입력 필드
- 플레이스홀더 지원
- 선택적 지우기 버튼 (입력값이 있을 때만 표시)
- 디바운스 입력 콜백 (검색 등에 활용)
- 단일 줄 / 여러 줄 입력 지원
- 비밀번호 입력 지원
- 다양한 키보드 타입 및 액션 설정
- View/Compose 양쪽 시스템 지원

## 🏗️ 구조

```
def/
├── HongTextFieldBuilder.kt    # 빌더 패턴 구성 클래스
├── HongTextFieldOption.kt     # 데이터 클래스 (옵션 보유)
├── HongTextFieldCompose.kt    # Jetpack Compose 구현
└── HongTextFieldView.kt       # View 시스템 구현
```

## 📦 주요 클래스

### HongTextFieldBuilder
빌더 패턴을 사용하여 텍스트 필드를 구성하는 클래스입니다.

**주요 메서드:**
- `radius(HongRadiusInfo)`: 모서리 둥글기 설정
- `border(HongBorderInfo)`: 테두리 설정
- `placeholder(String)`: 플레이스홀더 텍스트
- `placeholderColor(HongColor)`: 플레이스홀더 색상
- `placeholderTypo(HongTypo)`: 플레이스홀더 타이포그래피
- `placeholderPadding(HongSpacingInfo)`: 플레이스홀더 패딩
- `input(String)`: 초기 입력값
- `inputTypo(HongTypo)`: 입력 텍스트 타이포그래피
- `inputColor(HongColor)`: 입력 텍스트 색상
- `clearIconRes(Int)`: 지우기 아이콘 리소스 ID
- `clearIconSize(Int)`: 지우기 아이콘 크기
- `clearIconMargin(HongSpacingInfo)`: 지우기 아이콘 여백
- `cursorColor(HongColor)`: 커서 색상
- `useHideKeyboard(Boolean)`: 완료 시 키보드 숨기기 여부
- `singleLine(Boolean)`: 단일 줄 입력 여부
- `maxLines(Int)`: 최대 줄 수
- `minLines(Int)`: 최소 줄 수
- `keyboardOption(Pair<HongKeyboardType, HongKeyboardActionType>)`: 키보드 타입 및 액션
- `delayInputCallback(Long)`: 입력 콜백 지연 시간 (ms)
- `onTextChanged((String) -> Unit)`: 텍스트 변경 콜백

### HongTextFieldOption
텍스트 필드의 모든 설정값을 포함하는 데이터 클래스입니다.

**기본값:**
- 배경색: BLACK_05 (#F2F2F2)
- 플레이스홀더: BODY_16, BLACK_30
- 입력 텍스트: BODY_16_B, BLACK_100
- 지우기 아이콘: 크기 20, 좌측 여백 8f
- 커서: MAIN_ORANGE_100
- 키보드 숨기기: true
- 단일 줄: true
- 최대 줄: Int.MAX_VALUE
- 최소 줄: 1
- 키보드 옵션: TEXT + DONE
- 입력 콜백 지연: 0ms

### HongTextFieldCompose
Jetpack Compose에서 사용할 수 있는 텍스트 필드 컴포넌트입니다.

**구조:**
- HongWidgetContainer (공통 컨테이너)
  - Row (입력 필드 + 지우기 버튼)
    - Box (BasicTextField + 플레이스홀더)
    - HongImage (지우기 아이콘, 조건부 표시)

**4가지 케이스 처리:**
1. 지우기 버튼 + 디바운스 콜백
2. 지우기 버튼만
3. 디바운스 콜백만
4. 기본 (둘 다 없음)

**주요 기능:**
- BasicTextField 사용
- 플레이스홀더 오버레이 표시
- 지우기 버튼 자동 표시/숨김
- LaunchedEffect + delay로 디바운스 구현
- visualTransformation으로 비밀번호 입력 지원
- rememberSaveable로 상태 유지

### HongTextFieldView
기존 View 시스템에서 사용할 수 있는 LinearLayout 기반 뷰입니다.

**구조:**
- LinearLayout (Horizontal)
  - AppCompatEditText (weight 1f)
  - HongImageView (지우기 버튼, 조건부 표시)

**주요 기능:**
- AppCompatEditText 사용
- doAfterTextChanged로 텍스트 변경 감지
- CoroutineScope + delay로 디바운스 구현
- 지우기 버튼 visibility 자동 관리
- Enter 키 처리로 키보드 숨기기
- 포커스 변경 시 커서 표시/숨김
- 폰트 자동 전환

## 🚀 사용법

### Jetpack Compose 사용

**1. 기본 사용**

```kotlin
@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf("") }

    val option = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(HongSpacingInfo(16f, 16f, 12f, 12f))
        .backgroundColor(HongColor.BLACK_05)
        .radius(HongRadiusInfo(all = 50))
        .placeholder("검색어를 입력하세요")
        .onTextChanged { query ->
            searchQuery = query
        }
        .applyOption()

    HongTextFieldCompose(option = option)
}
```

**2. 지우기 버튼 추가**

```kotlin
val option = HongTextFieldBuilder()
    .placeholder("이름을 입력하세요")
    .clearIconRes(R.drawable.ic_clear)
    .clearIconSize(20)
    .clearIconMargin(HongSpacingInfo(left = 8f))
    .onTextChanged { name ->
        Log.d("TextField", "Name: $name")
    }
    .applyOption()
```

**3. 디바운스 검색**

```kotlin
val option = HongTextFieldBuilder()
    .placeholder("상품 검색")
    .delayInputCallback(500L) // 500ms 지연
    .onTextChanged { query ->
        // 500ms 후에 호출됨
        performSearch(query)
    }
    .applyOption()
```

**4. 여러 줄 입력**

```kotlin
val option = HongTextFieldBuilder()
    .placeholder("내용을 입력하세요")
    .singleLine(false)
    .minLines(3)
    .maxLines(10)
    .onTextChanged { content ->
        // 텍스트 변경 처리
    }
    .applyOption()
```

**5. 비밀번호 입력**

```kotlin
val option = HongTextFieldBuilder()
    .placeholder("비밀번호")
    .keyboardOption(
        Pair(
            HongKeyboardType.PASSWORD,
            HongKeyboardActionType.DONE
        )
    )
    .onTextChanged { password ->
        // 비밀번호 처리
    }
    .applyOption()
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<FrameLayout
    android:id="@+id/searchContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
val option = HongTextFieldBuilder()
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(44)
    .padding(HongSpacingInfo(16f, 16f, 12f, 12f))
    .backgroundColor(HongColor.BLACK_05.hex)
    .radius(HongRadiusInfo(all = 50))
    .placeholder("검색어를 입력하세요")
    .clearIconRes(R.drawable.ic_clear)
    .delayInputCallback(300L)
    .onTextChanged { query ->
        performSearch(query)
    }
    .applyOption()

val textFieldView = HongTextFieldView(context).set(option)
findViewById<FrameLayout>(R.id.searchContainer).addView(textFieldView)
```

## ⚙️ 주요 메서드

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `radius()` | HongRadiusInfo | 모서리 둥글기 설정 |
| `border()` | HongBorderInfo | 테두리 설정 |
| `placeholder()` | String | 플레이스홀더 텍스트 |
| `placeholderColor()` | HongColor / String | 플레이스홀더 색상 |
| `placeholderTypo()` | HongTypo | 플레이스홀더 타이포그래피 |
| `placeholderPadding()` | HongSpacingInfo | 플레이스홀더 패딩 |
| `input()` | String | 초기 입력값 |
| `inputTypo()` | HongTypo | 입력 텍스트 타이포그래피 |
| `inputColor()` | HongColor / String | 입력 텍스트 색상 |
| `clearIconRes()` | Int | 지우기 아이콘 리소스 ID |
| `clearIconSize()` | Int | 지우기 아이콘 크기 |
| `clearIconScaleType()` | HongScaleType | 지우기 아이콘 스케일 타입 |
| `clearIconMargin()` | HongSpacingInfo | 지우기 아이콘 여백 |
| `cursorColor()` | HongColor / String | 커서 색상 |
| `useHideKeyboard()` | Boolean | 완료 시 키보드 숨기기 |
| `singleLine()` | Boolean | 단일 줄 입력 여부 |
| `maxLines()` | Int | 최대 줄 수 |
| `minLines()` | Int | 최소 줄 수 |
| `keyboardOption()` | Pair<Type, Action> | 키보드 타입 및 액션 |
| `delayInputCallback()` | Long | 입력 콜백 지연 시간 (ms) |
| `onTextChanged()` | (String) -> Unit | 텍스트 변경 콜백 |

## 📝 참고사항

### 지우기 버튼
- `clearIconRes`를 설정하면 지우기 버튼이 활성화됩니다
- 입력값이 있을 때만 자동으로 표시됩니다
- 버튼 클릭 시 입력값이 모두 삭제됩니다

### 디바운스 콜백
- `delayInputCallback`을 0보다 크게 설정하면 디바운스가 활성화됩니다
- 사용자가 입력을 멈춘 후 지정된 시간(ms) 후에 콜백이 호출됩니다
- 검색 API 호출 등 성능 최적화에 활용

### 키보드 옵션
- `HongKeyboardType`: TEXT, NUMBER, PHONE, EMAIL, PASSWORD 등
- `HongKeyboardActionType`: DONE, NEXT, SEARCH, SEND 등
- 비밀번호 타입 사용 시 자동으로 마스킹 처리됩니다

### 단일 줄 vs 여러 줄
- `singleLine(true)`: 한 줄 입력, Enter 키로 키보드 닫기
- `singleLine(false)`: 여러 줄 입력, Enter 키로 줄바꿈
- `maxLines`, `minLines`로 줄 수 제한 가능

### 커서 색상
- 기본 커서 색상: MAIN_ORANGE_100
- `cursorColor()`로 변경 가능

### 플레이스홀더
- 입력값이 없을 때만 표시됩니다
- 입력 필드와 동일한 위치에 오버레이로 표시
- `placeholderPadding`으로 위치 조정 가능

### 상태 유지
- Compose: `rememberSaveable`로 화면 회전 시에도 상태 유지
- View: EditText의 기본 상태 복원 메커니즘 사용

## 💡 사용 팁

- 검색창 구현 시 디바운스 콜백 활용으로 API 호출 최적화
- 댓글 입력은 여러 줄 모드 사용
- 로그인 폼은 단일 줄 + 비밀번호 타입 조합
- 지우기 버튼으로 사용자 편의성 향상
- 심플한 입력 필드가 필요한 경우 적합 (복잡한 폼은 HongTextFieldBorder 사용)

## 🔄 HongTextFieldBorder와의 차이점

| 항목 | HongTextField | HongTextFieldBorder |
|------|---------------|---------------------|
| 용도 | 심플한 입력 필드 | 완전한 폼 입력 UI |
| 라벨 | ❌ 없음 | ✅ 있음 |
| 테두리 상태 | 고정 | 포커스 상태별 자동 변경 |
| 필수 표시 | ❌ 없음 | ✅ 있음 (*) |
| 도움말 텍스트 | ❌ 없음 | ✅ 있음 |
| 접미사 | ❌ 없음 | ✅ 있음 |
| ENABLE/DISABLE | ❌ 없음 | ✅ 있음 |
| 디바운스 | ✅ 있음 | ❌ 없음 |
| 추천 사용처 | 검색창, 댓글 | 회원가입, 설정 폼 |
