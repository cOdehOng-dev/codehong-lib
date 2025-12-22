# HongSelectButton

Android Compose 기반의 이중 선택 버튼 위젯으로, 빌더 패턴을 통해 취소/확인 등 양자택일 UI를 쉽게 구현할 수 있습니다.

## 📋 개요

HongSelectButton은 Jetpack Compose와 기존 Android View 시스템 모두에서 사용 가능한 이중 선택 버튼 위젯입니다. 부정(Negative)과 긍정(Positive) 두 개의 버튼을 제공하며, 각 버튼의 텍스트, 색상, 타이포그래피를 독립적으로 커스터마이징할 수 있습니다.

## 🏗️ 구조

```
button/select/
├── HongSelectButtonBuilder.kt      # 빌더 패턴 구현
├── HongSelectButtonCompose.kt      # Compose 컴포저블
├── HongSelectButtonOption.kt       # 옵션 데이터 클래스
└── HongSelectButtonView.kt         # Android View 래퍼
```

## 📦 주요 클래스

### HongSelectButtonOption

이중 선택 버튼의 모든 속성을 정의하는 데이터 클래스입니다.

**주요 프로퍼티:**
- `negativeText: String` - 부정 버튼 텍스트 (기본값: "취소")
- `negativeTextTypo: HongTypo` - 부정 버튼 타이포그래피 (기본값: BODY_15_B)
- `negativeTextColorHex: String` - 부정 버튼 텍스트 색상 (기본값: MAIN_ORANGE_100)
- `negativeBorderColorHex: String` - 부정 버튼 테두리 색상 (기본값: MAIN_ORANGE_100)
- `negativeClick: (() -> Unit)?` - 부정 버튼 클릭 리스너
- `positiveText: String` - 긍정 버튼 텍스트 (기본값: "확인")
- `positiveTextTypo: HongTypo` - 긍정 버튼 타이포그래피 (기본값: BODY_15_B)
- `positiveTextColorHex: String` - 긍정 버튼 텍스트 색상 (기본값: WHITE_100)
- `positiveBackgroundColorHex: String` - 긍정 버튼 배경 색상 (기본값: MAIN_ORANGE_100)
- `positiveClick: (() -> Unit)?` - 긍정 버튼 클릭 리스너

### HongSelectButtonBuilder

빌더 패턴을 사용하여 이중 선택 버튼 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `negativeText(text: String)` - 부정 버튼 텍스트 설정
- `negativeTextTypo(typo: HongTypo)` - 부정 버튼 타이포그래피 설정
- `negativeTextColor(color: HongColor)` - 부정 버튼 텍스트 색상 설정 (HongColor)
- `negativeTextColor(colorHex: String)` - 부정 버튼 텍스트 색상 설정 (Hex)
- `negativeBorderColor(color: HongColor)` - 부정 버튼 테두리 색상 설정 (HongColor)
- `negativeBorderColor(colorHex: String)` - 부정 버튼 테두리 색상 설정 (Hex)
- `negativeClick(click: () -> Unit)` - 부정 버튼 클릭 리스너 설정
- `positiveText(text: String)` - 긍정 버튼 텍스트 설정
- `positiveTextTypo(typo: HongTypo)` - 긍정 버튼 타이포그래피 설정
- `positiveTextColor(color: HongColor)` - 긍정 버튼 텍스트 색상 설정 (HongColor)
- `positiveTextColor(colorHex: String)` - 긍정 버튼 텍스트 색상 설정 (Hex)
- `positiveBackgroundColor(color: HongColor)` - 긍정 버튼 배경 색상 설정 (HongColor)
- `positiveBackgroundColor(colorHex: String)` - 긍정 버튼 배경 색상 설정 (Hex)
- `positiveClick(click: () -> Unit)` - 긍정 버튼 클릭 리스너 설정
- `copy(inject: HongSelectButtonOption?)` - 기존 옵션 복사

### HongSelectButtonCompose

Jetpack Compose에서 이중 선택 버튼을 렌더링하는 컴포저블 함수입니다.

```kotlin
@Composable
fun HongSelectButtonCompose(option: HongSelectButtonOption)
```

**주요 기능:**
- 두 개의 버튼이 1:1 비율로 배치 (weight = 1f)
- 버튼 간 10dp 간격 유지
- 각 버튼은 48dp 높이, 10dp 모서리 둥글기
- 부정 버튼: 테두리 스타일 (투명 배경)
- 긍정 버튼: 채워진 스타일 (배경 색상)

### HongSelectButtonView

기존 Android View 시스템에서 이중 선택 버튼을 사용할 수 있도록 LinearLayout으로 변환하는 클래스입니다.

```kotlin
class HongSelectButtonView(private val context: Context) {
    fun set(option: HongSelectButtonOption): LinearLayout
}
```

## 🚀 사용법

### 1. Jetpack Compose에서 사용

```kotlin
@Composable
fun MyScreen() {
    val selectButtonOption = HongSelectButtonBuilder()
        .negativeText("취소")
        .negativeClick {
            Log.d("Button", "취소 clicked")
        }
        .positiveText("확인")
        .positiveClick {
            Log.d("Button", "확인 clicked")
        }
        .applyOption()

    HongSelectButtonCompose(option = selectButtonOption)
}
```

### 2. Android View에서 사용

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectButtonOption = HongSelectButtonBuilder()
            .negativeText("아니오")
            .negativeClick {
                Toast.makeText(this, "아니오 선택", Toast.LENGTH_SHORT).show()
            }
            .positiveText("예")
            .positiveClick {
                Toast.makeText(this, "예 선택", Toast.LENGTH_SHORT).show()
            }
            .applyOption()

        val buttonView = HongSelectButtonView(this).set(selectButtonOption)

        val container = findViewById<FrameLayout>(R.id.container)
        container.addView(buttonView)
    }
}
```

### 3. 커스텀 색상 및 스타일 적용

```kotlin
val customSelectButton = HongSelectButtonBuilder()
    .negativeText("나가기")
    .negativeTextColor(HongColor.RED_500)
    .negativeBorderColor(HongColor.RED_500)
    .negativeClick {
        // 나가기 처리
    }
    .positiveText("계속하기")
    .positiveTextColor(HongColor.WHITE_100)
    .positiveBackgroundColor(HongColor.BLUE_500)
    .positiveClick {
        // 계속하기 처리
    }
    .applyOption()

HongSelectButtonCompose(option = customSelectButton)
```

### 4. 타이포그래피 커스터마이징

```kotlin
val styledButton = HongSelectButtonBuilder()
    .negativeText("이전")
    .negativeTextTypo(HongTypo.BODY_14_R)
    .negativeTextColor(HongColor.GRAY_70)
    .negativeClick {
        // 이전 단계로
    }
    .positiveText("다음")
    .positiveTextTypo(HongTypo.BODY_16_B)
    .positiveTextColor(HongColor.WHITE_100)
    .positiveBackgroundColor(HongColor.GREEN_500)
    .positiveClick {
        // 다음 단계로
    }
    .applyOption()

HongSelectButtonCompose(option = styledButton)
```

### 5. 삭제/저장 버튼 조합

```kotlin
val deleteConfirmButton = HongSelectButtonBuilder()
    .negativeText("삭제")
    .negativeTextColor(HongColor.RED_500)
    .negativeBorderColor(HongColor.RED_500)
    .negativeClick {
        // 삭제 확인
        showDeleteDialog()
    }
    .positiveText("저장")
    .positiveTextColor(HongColor.WHITE_100)
    .positiveBackgroundColor(HongColor.BLUE_500)
    .positiveClick {
        // 저장 처리
        saveData()
    }
    .applyOption()

HongSelectButtonCompose(option = deleteConfirmButton)
```

### 6. 기존 옵션 복사하여 수정

```kotlin
val originalButton = HongSelectButtonBuilder()
    .negativeText("취소")
    .positiveText("확인")
    .positiveBackgroundColor(HongColor.MAIN_ORANGE_100)
    .applyOption()

// 기존 버튼을 복사하여 긍정 버튼 색상만 변경
val modifiedButton = HongSelectButtonBuilder()
    .copy(originalButton)
    .positiveBackgroundColor(HongColor.BLUE_500)
    .applyOption()

Column {
    HongSelectButtonCompose(option = originalButton)
    Spacer(modifier = Modifier.height(16.dp))
    HongSelectButtonCompose(option = modifiedButton)
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `negativeText(String)` | 부정 버튼 텍스트 설정 |
| `negativeTextTypo(HongTypo)` | 부정 버튼 타이포그래피 설정 |
| `negativeTextColor(HongColor/String)` | 부정 버튼 텍스트 색상 설정 |
| `negativeBorderColor(HongColor/String)` | 부정 버튼 테두리 색상 설정 |
| `negativeClick(() -> Unit)` | 부정 버튼 클릭 리스너 설정 |
| `positiveText(String)` | 긍정 버튼 텍스트 설정 |
| `positiveTextTypo(HongTypo)` | 긍정 버튼 타이포그래피 설정 |
| `positiveTextColor(HongColor/String)` | 긍정 버튼 텍스트 색상 설정 |
| `positiveBackgroundColor(HongColor/String)` | 긍정 버튼 배경 색상 설정 |
| `positiveClick(() -> Unit)` | 긍정 버튼 클릭 리스너 설정 |
| `copy(HongSelectButtonOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 📝 참고사항

- **기본 레이아웃**: 두 버튼이 1:1 비율로 나란히 배치되며, 10dp 간격으로 구분됩니다.
- **버튼 스타일**:
  - 부정 버튼: 테두리만 있는 투명 배경 스타일 (기본값)
  - 긍정 버튼: 배경 색상이 채워진 스타일 (기본값)
- **높이**: 각 버튼의 높이는 48dp로 고정되어 있습니다.
- **모서리 둥글기**: 각 버튼의 모서리는 10dp 둥글기가 기본 적용됩니다.
- **너비**: 위젯 전체 너비는 MATCH_PARENT로 설정되어 부모 컨테이너를 채웁니다.
- **내부 구현**: 내부적으로 `HongButtonTextCompose`를 사용하여 각 버튼을 렌더링합니다.
- **독립적 커스터마이징**: 부정 버튼과 긍정 버튼의 모든 속성을 독립적으로 설정할 수 있습니다.
- **Compose와 View 호환**: Compose와 View 시스템 모두에서 사용 가능하여 점진적 마이그레이션에 적합합니다.
- **용도**: 대화상자, 확인 팝업, 양자택일 선택 UI 등 다양한 상황에서 활용 가능합니다.
