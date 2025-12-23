# HongTextCheck

체크 가능한 텍스트 위젯으로, 체크마크와 텍스트, 화살표 아이콘을 포함한 인터랙티브 UI 컴포넌트입니다. Android View와 Jetpack Compose를 모두 지원합니다.

## 📋 개요

HongTextCheck는 체크박스와 텍스트 레이블을 결합한 위젯으로, 약관 동의, 옵션 선택 등 다양한 선택 UI에 사용할 수 있습니다. 체크마크 색상, 크기, 텍스트 스타일 등을 자유롭게 커스터마이징할 수 있으며, 체크 상태 변경 시 콜백을 제공합니다.

## 🏗️ 구조

```
check/
├── HongTextCheckView.kt          # Android View 기반 위젯
├── HongTextCheckCompose.kt       # Jetpack Compose 기반 컴포저블
├── HongTextCheckOption.kt        # 위젯 설정 옵션 데이터 클래스
└── HongTextCheckBuilder.kt       # Builder 패턴 구현
```

## 📦 주요 클래스

### HongTextCheckView
Android View 기반의 체크 가능한 텍스트 위젯입니다. LinearLayout을 확장하며, 체크마크 ImageView와 텍스트, 화살표 아이콘을 수평으로 배치합니다.

**주요 기능:**
- 체크마크 클릭 시 상태 토글
- 체크/언체크 색상 자동 변경
- 위젯 전체 클릭 이벤트 지원

### HongTextCheckCompose
Jetpack Compose 기반의 체크 가능한 텍스트 컴포저블입니다. Row 레이아웃으로 체크마크, 텍스트, 화살표를 배치하며, `rememberSaveable`로 체크 상태를 관리합니다.

**주요 기능:**
- 상태 보존 (Configuration Change 대응)
- Composable 기반 선언적 UI
- HongImageCompose, HongTextCompose 조합

### HongTextCheckOption
HongTextCheck 위젯의 모든 설정 옵션을 담는 데이터 클래스입니다. `HongWidgetCommonOption` 인터페이스를 구현하여 공통 위젯 속성을 제공합니다.

**주요 속성:**
- `text`: 표시할 텍스트
- `textOption`: 텍스트 스타일 옵션
- `checkSize`: 체크마크 크기
- `checkColor`: 체크 상태 색상
- `uncheckColor`: 언체크 상태 색상
- `checkState`: 초기 체크 상태
- `arrowSize`: 화살표 아이콘 크기
- `onCheck`: 체크 상태 변경 콜백

### HongTextCheckBuilder
Builder 패턴을 사용하여 `HongTextCheckOption`을 생성하는 클래스입니다. 메서드 체이닝 방식으로 직관적인 옵션 설정을 제공합니다.

**주요 메서드:**
- `text()`: 텍스트 설정
- `textOption()`: 텍스트 옵션 설정
- `checkSize()`: 체크마크 크기 설정
- `checkColor()`, `uncheckColor()`: 체크 색상 설정
- `checkState()`: 초기 체크 상태 설정
- `onCheck()`: 체크 변경 콜백 설정
- `copy()`: 기존 옵션 복사

## 🚀 사용법

### Android View 사용 예시

```kotlin
val checkTextView = HongTextCheckView(context)
checkTextView.set(
    HongTextCheckBuilder()
        .text("약관에 동의합니다")
        .checkSize(30)
        .checkColor(HongColor.MAIN_ORANGE_100)
        .uncheckColor(HongColor.GRAY_60)
        .checkState(false)
        .arrowSize(24)
        .onCheck { isChecked ->
            // 체크 상태 변경 처리
            Log.d("CheckText", "Checked: $isChecked")
        }
        .onClick { option ->
            // 위젯 전체 클릭 처리
            navigateToDetail()
        }
        .applyOption()
)
```

### Jetpack Compose 사용 예시

```kotlin
@Composable
fun MyScreen() {
    val option = HongTextCheckBuilder()
        .margin(HongSpacingInfo(left = 20f))
        .checkSize(30)
        .arrowSize(20)
        .text("휴대폰/카드 본인확인 서비스")
        .textOption(
            HongTextBuilder()
                .typography(HongTypo.BODY_15)
                .color(HongColor.GRAY_70)
                .applyOption()
        )
        .checkState(true)
        .onClick { /* 전체 클릭 이벤트 */ }
        .onCheck { isChecked ->
            // 체크 상태 변경 처리
        }
        .applyOption()

    HongTextCheckCompose(option)
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `HongTextCheckView.set(option)` | View 기반 위젯에 옵션 적용 |
| `HongTextCheckCompose(option)` | Compose 기반 위젯 생성 |
| `HongTextCheckBuilder.text(text)` | 표시할 텍스트 설정 |
| `HongTextCheckBuilder.checkSize(size)` | 체크마크 크기 설정 (dp) |
| `HongTextCheckBuilder.checkColor(color)` | 체크 상태 색상 설정 |
| `HongTextCheckBuilder.uncheckColor(color)` | 언체크 상태 색상 설정 |
| `HongTextCheckBuilder.checkState(state)` | 초기 체크 상태 설정 |
| `HongTextCheckBuilder.onCheck(callback)` | 체크 변경 콜백 설정 |
| `HongTextCheckBuilder.arrowSize(size)` | 화살표 아이콘 크기 설정 (dp) |
| `HongTextCheckBuilder.copy(option)` | 기존 옵션 복사 |

## 📝 참고사항

- 체크마크 이미지는 `R.drawable.honglib_ic_check` 리소스를 사용합니다
- 화살표 이미지는 `R.drawable.honglib_ic_arrow_right` 리소스를 사용합니다
- `HongTextCheckOption`은 `HongWidgetCommonOption` 인터페이스를 구현하여 공통 위젯 속성(margin, padding, background, border 등)을 모두 사용할 수 있습니다
- Compose 버전은 `rememberSaveable`을 사용하여 Configuration Change 시에도 체크 상태를 유지합니다
- 기본 텍스트 옵션은 `HongTypo.BODY_13`, `HongColor.GRAY_70`으로 설정됩니다
- 체크마크의 기본 여백(marginEnd)은 8dp입니다
- Builder의 `padding()` 메서드는 현재 빈 HongSpacingInfo를 설정합니다 (구현 확인 필요)
