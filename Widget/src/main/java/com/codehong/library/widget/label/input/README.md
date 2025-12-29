# HongLabelInput

라벨, 설명, 텍스트 입력 필드가 결합된 폼 입력 위젯입니다.

## 📋 개요

HongLabelInput은 주 라벨, 부가 설명, 텍스트 입력 필드를 하나의 컴포넌트로 결합한 폼 입력 위젯입니다. Placeholder 지원, Clear 아이콘, 키보드 타입 설정, 텍스트 변경 콜백 등 입력 필드에 필요한 모든 기능을 제공하며, 빌더 패턴을 통해 쉽게 커스터마이징할 수 있습니다. Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
label/input/
├── HongLabelInputBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongLabelInputOption.kt        # 옵션 데이터 클래스
├── HongLabelInputCompose.kt       # Compose용 컴포넌트
└── HongLabelInputView.kt          # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongLabelInputBuilder
빌더 패턴을 사용하여 라벨 입력 필드의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 라벨 설정 (`label`, `labelColor`, `labelTypo`)
- 설명 설정 (`description`, `descriptionColor`, `descriptionTypo`)
- Placeholder 설정 (`placeholder`, `placeholderColor`, `placeholderTypo`)
- 입력 텍스트 설정 (`input`, `inputColor`, `inputTypo`)
- Clear 아이콘 커스터마이징 (`clearIconRes`, `clearIconSize`, `clearIconScaleType`, `clearIconMargin`)
- 키보드 옵션 설정 (`keyboardOption`)
- 텍스트 변경 콜백 (`onTextChanged`)
- 옵션 복사 (`copy`)

### HongLabelInputOption
라벨 입력 필드의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `label`: 주 라벨 텍스트
- `labelColorHex`: 라벨 색상 (기본값: `HongColor.BLACK_100`)
- `labelTypo`: 라벨 타이포그래피 (기본값: `HongTypo.BODY_15_B`)
- `description`: 부가 설명 텍스트
- `descriptionColorHex`: 설명 색상 (기본값: `HongColor.BLACK_60`)
- `descriptionTypo`: 설명 타이포그래피 (기본값: `HongTypo.CONTENTS_10`)
- `placeholder`: Placeholder 텍스트
- `placeholderColorHex`: Placeholder 색상 (기본값: `HongColor.BLACK_30`)
- `placeholderTypo`: Placeholder 타이포그래피 (기본값: `HongTypo.BODY_16`)
- `input`: 입력된 텍스트
- `inputColorHex`: 입력 텍스트 색상 (기본값: `HongColor.BLACK_100`)
- `inputTypo`: 입력 텍스트 타이포그래피 (기본값: `HongTypo.BODY_14`)
- `clearIconRes`: Clear 아이콘 리소스 ID (기본값: `honglib_ic_20_circle_close_fill`)
- `clearIconSize`: Clear 아이콘 크기 (기본값: 18dp)
- `clearIconScaleType`: Clear 아이콘 스케일 타입 (기본값: `CENTER_CROP`)
- `clearIconMargin`: Clear 아이콘 마진 (기본값: 왼쪽 8dp)
- `keyboardOption`: 키보드 타입 및 액션 (기본값: `TEXT`, `DONE`)
- `onTextChanged`: 텍스트 변경 콜백

### HongLabelInputCompose
Jetpack Compose에서 사용할 수 있는 라벨 입력 필드 컴포넌트입니다.

**구조:**
- Column 레이아웃 (세로 배치)
- HongLabel (주 라벨 + 부가 설명)
- HongTextField (입력 필드, 10dp 상단 마진)
  - 높이: 48dp
  - 모서리 둥글기: 10dp
  - 내부 패딩: 상하 11dp, 좌우 10dp

### HongLabelInputView
기존 View 시스템에서 사용할 수 있는 커스텀 LinearLayout 뷰입니다.

**구조:**
- Vertical LinearLayout
- HongLabelView (라벨이 있을 경우에만)
- HongTextField (입력 필드, 10dp 상단 마진)

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }

    val emailOption = HongLabelInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("이메일")
        .description("로그인에 사용할 이메일을 입력하세요")
        .placeholder("example@email.com")
        .input(email)
        .inputTypo(HongTypo.BODY_14)
        .keyboardOption(
            Pair(
                HongKeyboardType.EMAIL,
                HongKeyboardActionType.NEXT
            )
        )
        .onTextChanged { newText ->
            email = newText
        }
        .applyOption()

    HongLabelInputCompose(option = emailOption)
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<com.codehong.library.widget.label.input.HongLabelInputView
    android:id="@+id/labelInput"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val option = HongLabelInputBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .padding(
                HongSpacingInfo(
                    left = 16f,
                    right = 16f,
                    top = 10f,
                    bottom = 10f
                )
            )
            .label("비밀번호")
            .description("8자 이상, 영문/숫자/특수문자 포함")
            .placeholder("비밀번호를 입력하세요")
            .inputTypo(HongTypo.BODY_14)
            .keyboardOption(
                Pair(
                    HongKeyboardType.PASSWORD,
                    HongKeyboardActionType.DONE
                )
            )
            .onTextChanged { password ->
                // 비밀번호 유효성 검사
                validatePassword(password)
            }
            .applyOption()

        findViewById<HongLabelInputView>(R.id.labelInput)
            .set(option)
    }
}
```

### 다양한 키보드 타입

```kotlin
// 숫자 키보드
val phoneOption = HongLabelInputBuilder()
    .label("전화번호")
    .placeholder("010-0000-0000")
    .keyboardOption(
        Pair(
            HongKeyboardType.NUMBER,
            HongKeyboardActionType.DONE
        )
    )
    .applyOption()

// URL 키보드
val websiteOption = HongLabelInputBuilder()
    .label("웹사이트")
    .placeholder("https://example.com")
    .keyboardOption(
        Pair(
            HongKeyboardType.URI,
            HongKeyboardActionType.GO
        )
    )
    .applyOption()
```

### Clear 아이콘 커스터마이징

```kotlin
val customClearOption = HongLabelInputBuilder()
    .label("검색")
    .placeholder("검색어를 입력하세요")
    .clearIconRes(R.drawable.custom_clear_icon)
    .clearIconSize(20)
    .clearIconMargin(
        HongSpacingInfo(left = 10f)
    )
    .applyOption()
```

### 라벨 없이 사용

```kotlin
// 라벨을 설정하지 않으면 입력 필드만 표시됩니다
val simpleInputOption = HongLabelInputBuilder()
    .placeholder("간단한 입력")
    .input("")
    .onTextChanged { text ->
        // 텍스트 처리
    }
    .applyOption()
```

## ⚙️ 주요 메서드

### HongLabelInputBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `label(label)` | `String?` | 주 라벨 텍스트 설정 |
| `labelColor(color)` | `HongColor` 또는 `String` | 라벨 색상 설정 |
| `labelTypo(typo)` | `HongTypo` | 라벨 타이포그래피 설정 |
| `description(description)` | `String?` | 부가 설명 텍스트 설정 |
| `descriptionColor(color)` | `HongColor` 또는 `String` | 설명 색상 설정 |
| `descriptionTypo(typo)` | `HongTypo` | 설명 타이포그래피 설정 |
| `placeholder(placeholder)` | `String?` | Placeholder 텍스트 설정 |
| `placeholderColor(color)` | `HongColor` 또는 `String` | Placeholder 색상 설정 |
| `placeholderTypo(typo)` | `HongTypo` | Placeholder 타이포그래피 설정 |
| `input(input)` | `String?` | 입력된 텍스트 설정 |
| `inputColor(color)` | `HongColor` 또는 `String` | 입력 텍스트 색상 설정 |
| `inputTypo(typo)` | `HongTypo` | 입력 텍스트 타이포그래피 설정 |
| `clearIconRes(resId)` | `Int?` | Clear 아이콘 리소스 ID 설정 |
| `clearIconSize(size)` | `Int` | Clear 아이콘 크기 설정 (dp) |
| `clearIconScaleType(scaleType)` | `HongScaleType` | Clear 아이콘 스케일 타입 설정 |
| `clearIconMargin(margin)` | `HongSpacingInfo` | Clear 아이콘 마진 설정 |
| `keyboardOption(option)` | `Pair<HongKeyboardType, HongKeyboardActionType>?` | 키보드 타입 및 액션 설정 |
| `onTextChanged(callback)` | `(String) -> Unit` | 텍스트 변경 콜백 설정 |
| `copy(inject)` | `HongLabelInputOption` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongLabelInputView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongLabelInputOption` | `HongLabelInputView` | 옵션을 적용하여 뷰 구성 |

## 📝 참고사항

### 레이아웃 구조
- **전체 크기:** WRAP_CONTENT (기본값)
- **입력 필드 높이:** 48dp
- **입력 필드 모서리 둥글기:** 10dp
- **입력 필드 내부 패딩:** 상하 11dp, 좌우 10dp
- **라벨-입력 필드 간격:** 10dp
- **라벨-설명 간격:** 2dp (HongLabel 내부)

### 기본값
- 라벨 타이포그래피: `HongTypo.BODY_15_B`
- 라벨 색상: `HongColor.BLACK_100` (#000000)
- 설명 타이포그래피: `HongTypo.CONTENTS_10`
- 설명 색상: `HongColor.BLACK_60` (60% 불투명도)
- Placeholder 타이포그래피: `HongTypo.BODY_16`
- Placeholder 색상: `HongColor.BLACK_30` (30% 불투명도)
- 입력 텍스트 타이포그래피: `HongTypo.BODY_14`
- 입력 텍스트 색상: `HongColor.BLACK_100`
- Clear 아이콘 크기: 18dp
- Clear 아이콘 마진: 왼쪽 8dp
- 키보드 타입: TEXT
- 키보드 액션: DONE
- 배경 색상: `HongColor.TRANSPARENT`

### 키보드 타입
- `HongKeyboardType.TEXT`: 기본 텍스트 키보드
- `HongKeyboardType.NUMBER`: 숫자 키보드
- `HongKeyboardType.EMAIL`: 이메일 키보드
- `HongKeyboardType.PASSWORD`: 비밀번호 키보드
- `HongKeyboardType.URI`: URL 키보드
- `HongKeyboardType.PHONE`: 전화번호 키보드

### 키보드 액션
- `HongKeyboardActionType.DONE`: 완료
- `HongKeyboardActionType.NEXT`: 다음
- `HongKeyboardActionType.GO`: 이동
- `HongKeyboardActionType.SEARCH`: 검색
- `HongKeyboardActionType.SEND`: 전송

### 주의사항
- `label`이 null이거나 빈 문자열인 경우: View에서는 라벨 영역이 추가되지 않음
- `description`이 null이거나 빈 문자열인 경우: 설명 텍스트가 표시되지 않음
- View 시스템에서 텍스트 값을 변경하려면 새로운 옵션으로 `set()` 메서드를 다시 호출해야 합니다
- Compose에서는 상태 관리를 별도로 구현해야 합니다 (remember, mutableStateOf 등)
- Clear 아이콘은 텍스트가 입력되었을 때만 표시됩니다
- `clearIconRes`에 null을 설정하면 Clear 아이콘이 표시되지 않습니다

### 의존성
- HongLabel (라벨 위젯)
- HongTextField (텍스트 입력 필드)
- HongColor, HongTypo (디자인 시스템)
- HongKeyboardType, HongKeyboardActionType (키보드 옵션)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)

### 사용 사례
- 로그인/회원가입 폼
- 프로필 편집 화면
- 설정 입력 필드
- 검색 입력
- 결제 정보 입력
- 주소 입력
- 피드백/문의 폼

### 상태 관리 예시 (Compose)

```kotlin
@Composable
fun ProfileEditScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(true) }

    Column {
        HongLabelInputCompose(
            HongLabelInputBuilder()
                .label("이름")
                .description(
                    if (!isNameValid) "이름을 입력해주세요"
                    else "실명을 입력해주세요"
                )
                .descriptionColor(
                    if (!isNameValid) HongColor.RED_100
                    else HongColor.BLACK_60
                )
                .placeholder("홍길동")
                .input(name)
                .onTextChanged { newName ->
                    name = newName
                    isNameValid = newName.isNotEmpty()
                }
                .applyOption()
        )

        HongLabelInputCompose(
            HongLabelInputBuilder()
                .label("이메일")
                .placeholder("example@email.com")
                .input(email)
                .keyboardOption(
                    Pair(
                        HongKeyboardType.EMAIL,
                        HongKeyboardActionType.DONE
                    )
                )
                .onTextChanged { newEmail ->
                    email = newEmail
                }
                .applyOption()
        )
    }
}
```

### 비교: HongLabelInput vs HongTextField
- **HongLabelInput**: 라벨 + 설명 + 입력 필드의 완전한 폼 컴포넌트
- **HongTextField**: 입력 필드만 제공

HongLabelInput은 내부적으로 HongLabel과 HongTextField를 조합하여 사용합니다.
