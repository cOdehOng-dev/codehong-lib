# HongTextFieldTimer

타이머가 내장된 텍스트 입력 필드 위젯. 제한 시간 내 입력을 받아야 하는 UI(인증번호 입력, 제한 시간 입력 등)에 최적화된 컴포넌트입니다.

## 📋 개요

HongTextFieldTimer는 카운트다운 타이머와 텍스트 입력 필드를 결합한 위젯으로, 시간 제한이 있는 입력 폼에서 사용됩니다. 타이머가 종료되면 자동으로 콜백을 호출하고, 언더라인 색상을 변경하여 시각적 피드백을 제공합니다.

## 🏗️ 구조

```
textfield/timer/
├── HongTextFieldTimerOption.kt      # 위젯 옵션 데이터 클래스
├── HongTextFieldTimerBuilder.kt     # 빌더 패턴 설정 클래스
├── HongTextFieldTimerView.kt        # Android View 구현체
└── HongTextFieldTimerCompose.kt     # Jetpack Compose 구현체
```

## 📦 주요 클래스

### HongTextFieldTimerOption
위젯의 모든 설정 값을 담는 데이터 클래스입니다.

**주요 속성:**
- `input` - 입력된 텍스트
- `placeholder` - 플레이스홀더 텍스트
- `inputTypo` / `placeholderTypo` - 텍스트 타이포그래피
- `clearImageOption` - 클리어 버튼 이미지 옵션
- `underlineFocusColorHex` - 포커스 시 언더라인 색상
- `underlineOutFocusColorHex` - 포커스 해제 시 언더라인 색상
- `underlineFinishColorHex` - 타이머 종료 시 언더라인 색상
- `min` / `sec` - 카운트다운 타이머 설정 (분/초)
- `countDownTextOption` - 카운트다운 텍스트 스타일
- `onFinish` - 타이머 종료 시 콜백
- `onTextChanged` - 텍스트 변경 시 콜백

### HongTextFieldTimerBuilder
빌더 패턴으로 옵션을 설정하는 클래스입니다.

**주요 메서드:**
- `placeholder()` - 플레이스홀더 설정
- `input()` - 초기 입력 값 설정
- `inputTypo()` / `placeholderTypo()` - 타이포그래피 설정
- `inputColor()` / `placeholderColor()` - 색상 설정
- `clearImageOption()` - 클리어 버튼 이미지 옵션 설정
- `underlineFocusColor()` - 포커스 시 언더라인 색상 설정
- `underlineOutFocusColor()` - 포커스 해제 시 언더라인 색상 설정
- `underlineFinishColor()` - 타이머 종료 시 언더라인 색상 설정
- `underlineHeight()` - 언더라인 높이 설정
- `min()` / `sec()` - 타이머 시간 설정 (분/초)
- `countDownTextOption()` - 카운트다운 텍스트 스타일 설정
- `onFinish()` - 타이머 종료 시 콜백 설정
- `onTextChanged()` - 텍스트 변경 시 콜백 설정

### HongTextFieldTimerView
Android View 기반의 구현체입니다.

**특징:**
- `FrameLayout`을 상속받은 커스텀 뷰
- `AppCompatEditText`를 사용한 텍스트 입력
- `CountdownTimerHelper`를 사용한 타이머 기능
- 타이머 종료 시 언더라인 색상 자동 변경 및 `onFinish()` 콜백 호출
- 포커스 상태에 따른 언더라인 색상 자동 변경
- 입력 값이 있을 때만 클리어 버튼 표시

### HongTimerTextFieldCompose
Jetpack Compose 기반의 구현체입니다.

**특징:**
- `BasicTextField`를 사용한 텍스트 입력
- `LaunchedEffect`를 사용한 카운트다운 타이머
- 타이머 종료 시 언더라인 색상 자동 변경 및 `onFinish()` 콜백 호출
- 포커스 상태에 따른 언더라인 색상 자동 변경
- 입력 값이 있을 때만 클리어 버튼 표시

## 🚀 사용법

### Android View

```kotlin
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.color.HongColor

// XML 레이아웃에 추가
<com.codehong.library.widget.textfield.timer.HongTextFieldTimerView
    android:id="@+id/timerTextField"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

// Activity/Fragment에서 설정
binding.timerTextField.set(
    HongTextFieldTimerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .placeholder("인증번호를 입력해주세요")
        .underlineFocusColor(HongColor.MAIN_ORANGE_100)
        .underlineOutFocusColor(HongColor.GRAY_20)
        .underlineFinishColor(HongColor.RED_100)
        .min(3)  // 3분
        .sec(0)  // 0초
        .clearImageOption(
            HongImageBuilder()
                .width(16)
                .height(16)
                .imageInfo(R.drawable.ic_close)
                .applyOption()
        )
        .onTextChanged { input ->
            // 텍스트 변경 시 처리
            Log.d("Timer", "Input: $input")
        }
        .onFinish {
            // 타이머 종료 시 처리
            Toast.makeText(context, "시간이 초과되었습니다", Toast.LENGTH_SHORT).show()
        }
        .applyOption()
)
```

### Jetpack Compose

```kotlin
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerBuilder
import com.codehong.library.widget.textfield.timer.HongTimerTextFieldCompose

@Composable
fun VerificationScreen() {
    val option = HongTextFieldTimerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .placeholder("인증번호를 입력해주세요")
        .underlineFocusColor(HongColor.MAIN_ORANGE_100)
        .underlineOutFocusColor(HongColor.GRAY_20)
        .underlineFinishColor(HongColor.RED_100)
        .min(3)  // 3분
        .sec(0)  // 0초
        .clearImageOption(
            HongImageBuilder()
                .width(16)
                .height(16)
                .imageInfo(R.drawable.ic_close)
                .applyOption()
        )
        .onTextChanged { input ->
            // 텍스트 변경 시 처리
            Log.d("Timer", "Input: $input")
        }
        .onFinish {
            // 타이머 종료 시 처리
            Log.d("Timer", "Time's up!")
        }
        .applyOption()

    HongTimerTextFieldCompose(option)
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
| `clearImageOption()` | 클리어 버튼 이미지 옵션 설정 |
| `cursorColor()` | 커서 색상 설정 |
| `underlineFocusColor()` | 포커스 시 언더라인 색상 설정 |
| `underlineOutFocusColor()` | 포커스 해제 시 언더라인 색상 설정 |
| `underlineFinishColor()` | 타이머 종료 시 언더라인 색상 설정 |
| `underlineHeight()` | 언더라인 높이 설정 (dp) |
| `min()` | 타이머 분 설정 |
| `sec()` | 타이머 초 설정 |
| `countDownTextOption()` | 카운트다운 텍스트 스타일 설정 |
| `onFinish()` | 타이머 종료 시 콜백 설정 |
| `onTextChanged()` | 텍스트 변경 시 콜백 설정 |
| `useHideKeyboard()` | 키보드 자동 숨김 설정 |
| `keyboardOption()` | 키보드 타입 및 액션 설정 |

## 📝 참고사항

- 타이머는 분(min)과 초(sec)로 설정하며, 둘 다 0이면 타이머가 동작하지 않습니다
- 타이머가 00:00에 도달하면 자동으로 `onFinish()` 콜백이 호출됩니다
- 타이머 종료 시 `underlineFinishColor`가 설정되어 있으면 언더라인 색상이 자동으로 변경됩니다
- 포커스 상태에 따라 언더라인 색상이 자동으로 변경됩니다 (포커스: `underlineFocusColor`, 비포커스: `underlineOutFocusColor`)
- 클리어 버튼은 입력 값이 있을 때만 표시되며, 클릭 시 입력 값을 초기화합니다
- View와 Compose 모두 지원하며, 동일한 Builder를 사용하여 옵션을 설정합니다
- 카운트다운 텍스트는 "MM:SS" 형식으로 표시됩니다
- 주요 사용 사례: 인증번호 입력, SMS 인증, 제한 시간 입력 등
