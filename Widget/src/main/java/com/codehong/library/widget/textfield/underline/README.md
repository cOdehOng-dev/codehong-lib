# HongTextFieldUnderline

언더라인이 있는 텍스트 입력 필드 위젯. 포커스 상태에 따라 언더라인 색상이 자동으로 변경되어 시각적 피드백을 제공하는 심플한 텍스트 입력 컴포넌트입니다.

## 📋 개요

HongTextFieldUnderline은 하단에 언더라인을 가진 텍스트 입력 필드로, 포커스 상태에 따라 언더라인 색상이 자동으로 변경됩니다. 클리어 버튼을 제공하며, Android View와 Jetpack Compose를 모두 지원합니다.

## 🏗️ 구조

```
textfield/underline/
├── HongTextFieldUnderlineOption.kt      # 위젯 옵션 데이터 클래스
├── HongTextFieldUnderlineBuilder.kt     # 빌더 패턴 설정 클래스
├── HongTextFieldUnderlineView.kt        # Android View 구현체
└── HongTextFieldUnderlineCompose.kt     # Jetpack Compose 구현체
```

## 📦 주요 클래스

### HongTextFieldUnderlineOption
위젯의 모든 설정 값을 담는 데이터 클래스입니다.

**주요 속성:**
- `input` - 입력된 텍스트
- `placeholder` / `placeholderTypo` - 플레이스홀더 텍스트 및 타이포그래피
- `inputTypo` / `inputColorHex` - 입력 텍스트 타이포그래피 및 색상
- `underlineFocusColor` - 포커스 시 언더라인 색상
- `underlineOutFocusColor` - 포커스 해제 시 언더라인 색상
- `underlineHeight` - 언더라인 높이 (dp)
- `clearIconRes` - 클리어 버튼 아이콘 리소스
- `clearIconSize` - 클리어 버튼 크기
- `cursorColorHex` - 커서 색상
- `keyboardOption` - 키보드 타입 및 액션
- `onTextChanged` - 텍스트 변경 시 콜백

### HongTextFieldUnderlineBuilder
빌더 패턴으로 옵션을 설정하는 클래스입니다. 모든 설정 메서드는 `apply` 패턴을 사용하여 메서드 체이닝을 지원합니다.

**주요 메서드:**
- `placeholder()` - 플레이스홀더 텍스트 설정
- `input()` - 초기 입력 값 설정
- `inputTypo()` / `inputColor()` - 입력 텍스트 스타일 설정
- `placeholderTypo()` / `placeholderColor()` - 플레이스홀더 스타일 설정
- `underlineFocusColor()` - 포커스 시 언더라인 색상 설정
- `underlineOutFocusColor()` - 포커스 해제 시 언더라인 색상 설정
- `underlineHeight()` - 언더라인 높이 설정
- `clearIconRes()` - 클리어 버튼 아이콘 설정
- `clearIconSize()` - 클리어 버튼 크기 설정
- `cursorColor()` - 커서 색상 설정
- `onTextChanged()` - 텍스트 변경 시 콜백 설정

### HongTextFieldUnderlineView
Android View 기반의 구현체입니다.

**특징:**
- `FrameLayout`을 상속받은 커스텀 뷰
- `AppCompatEditText`를 사용한 텍스트 입력
- 포커스 상태에 따른 언더라인 색상 자동 변경
- 입력 값이 있을 때만 클리어 버튼 표시
- Enter 키 입력 시 키보드 자동 숨김

### HongUnderlineTextFieldCompose
Jetpack Compose 기반의 구현체입니다.

**특징:**
- `BasicTextField`를 사용한 텍스트 입력
- 포커스 상태에 따른 언더라인 색상 자동 변경
- 입력 값이 있을 때만 클리어 버튼 표시
- `HongDivider`를 사용한 언더라인 렌더링

## 🚀 사용법

### Android View

```kotlin
import com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.color.HongColor

// XML 레이아웃에 추가
<com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineView
    android:id="@+id/underlineTextField"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

// Activity/Fragment에서 설정
binding.underlineTextField.set(
    HongTextFieldUnderlineBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .placeholder("이름을 입력해주세요")
        .underlineFocusColor(HongColor.MAIN_ORANGE_100)
        .underlineOutFocusColor(HongColor.GRAY_20)
        .underlineHeight(2)
        .clearIconRes(R.drawable.ic_close)
        .clearIconSize(16)
        .onTextChanged { input ->
            // 텍스트 변경 시 처리
            Log.d("TextField", "Input: $input")
        }
        .applyOption()
)
```

### Jetpack Compose

```kotlin
import com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineBuilder
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldCompose

@Composable
fun InputScreen() {
    val option = HongTextFieldUnderlineBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .placeholder("이름을 입력해주세요")
        .underlineFocusColor(HongColor.MAIN_ORANGE_100)
        .underlineOutFocusColor(HongColor.GRAY_20)
        .underlineHeight(2)
        .clearIconRes(R.drawable.ic_close)
        .clearIconSize(16)
        .onTextChanged { input ->
            // 텍스트 변경 시 처리
            Log.d("TextField", "Input: $input")
        }
        .applyOption()

    HongUnderlineTextFieldCompose(option)
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `placeholder()` | 플레이스홀더 텍스트 설정 |
| `input()` | 초기 입력 값 설정 |
| `inputTypo()` | 입력 텍스트 타이포그래피 설정 |
| `inputColor()` | 입력 텍스트 색상 설정 |
| `placeholderTypo()` | 플레이스홀더 타이포그래피 설정 |
| `placeholderColor()` | 플레이스홀더 색상 설정 |
| `underlineFocusColor()` | 포커스 시 언더라인 색상 설정 |
| `underlineOutFocusColor()` | 포커스 해제 시 언더라인 색상 설정 |
| `underlineHeight()` | 언더라인 높이 설정 (dp) |
| `clearIconRes()` | 클리어 버튼 아이콘 리소스 설정 |
| `clearIconSize()` | 클리어 버튼 크기 설정 (dp) |
| `clearIconMargin()` | 클리어 버튼 마진 설정 |
| `cursorColor()` | 커서 색상 설정 |
| `onTextChanged()` | 텍스트 변경 시 콜백 설정 |
| `useHideKeyboard()` | 키보드 자동 숨김 설정 |
| `keyboardOption()` | 키보드 타입 및 액션 설정 |

## 📝 참고사항

- 포커스 상태에 따라 언더라인 색상이 자동으로 변경됩니다 (포커스: `underlineFocusColor`, 비포커스: `underlineOutFocusColor`)
- 클리어 버튼은 `clearIconRes`가 설정되어 있고 입력 값이 있을 때만 표시됩니다
- 클리어 버튼을 클릭하면 입력 값이 초기화됩니다
- View와 Compose 모두 지원하며, 동일한 Builder를 사용하여 옵션을 설정합니다
- 단일 행 입력만 지원하며, maxLines는 항상 1입니다
- Enter 키 입력 시 키보드가 자동으로 숨겨집니다 (View만 해당)
- 기본 키보드 타입은 `NUMBER`, 기본 액션은 `DONE`입니다
- 언더라인 높이는 기본값 2dp이며, `underlineHeight()`로 변경 가능합니다
- 주요 사용 사례: 이름 입력, 이메일 입력, 검색 입력 등 일반적인 텍스트 입력 폼
