# HongTextCount

숫자 입력 및 증감 버튼을 제공하는 카운터 위젯입니다. ➖ 버튼과 ➕ 버튼으로 값을 조절할 수 있으며, 직접 입력도 가능합니다.

## 📋 개요

HongTextCount는 사용자가 숫자를 입력하고 증감시킬 수 있는 인터랙티브 카운터 컴포넌트입니다. 최소값/최대값 제한, 단위 텍스트 표시, Long/Double 타입 지원 등 다양한 기능을 제공하며, Jetpack Compose와 Android View 시스템 모두에서 사용할 수 있습니다.

**주요 특징:**
- ➖/➕ 버튼을 통한 값 증감
- 직접 숫자 입력 가능
- Long/Double 타입 지원
- 최소값/최대값 범위 제한
- 단위 텍스트 표시 (예: "개", "kg")
- 동적 너비 조절
- 커스텀 스타일링 지원

## 🏗️ 구조

```
text/count/
├── HongTextCountCompose.kt    # Jetpack Compose UI 구현
├── HongTextCountView.kt        # Android View 래퍼 클래스
├── HongTextCountOption.kt      # 설정 데이터 클래스
└── HongTextCountBuilder.kt     # 빌더 패턴 구현
```

## 📦 주요 클래스

### HongTextCountCompose

Jetpack Compose로 구현된 카운터 UI 컴포넌트입니다.

**주요 기능:**
- `BasicTextField`를 사용한 숫자 직접 입력
- 동적 너비 조절 (80dp ~ 300dp)
- 최소값/최대값에 따른 버튼 자동 비활성화
- 텍스트 측정을 통한 레이아웃 최적화
- 애니메이션 없는 즉각적인 반응성

```kotlin
@Composable
fun HongTextCountCompose(
    option: HongTextCountOption
)
```

### HongTextCountView

Android View 시스템을 위한 래퍼 클래스입니다. 내부적으로 `ComposeView`를 생성하여 `HongTextCountCompose`를 호스팅합니다.

```kotlin
class HongTextCountView(
    private val context: Context
) {
    fun set(option: HongTextCountOption): ComposeView
}
```

### HongTextCountOption

카운터 위젯의 모든 설정을 포함하는 데이터 클래스입니다.

**주요 속성:**

| 속성 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `countType` | `HongCountType` | `LONG` | 숫자 타입 (LONG/DOUBLE) |
| `startCount` | `Number` | `1` | 초기값 |
| `countTypo` | `HongTypo` | `TITLE_36_B` | 숫자 타이포그래피 |
| `countColorHex` | `String` | `BLACK_100` | 숫자 색상 |
| `unitText` | `String` | `""` | 단위 텍스트 |
| `unitTypo` | `HongTypo` | `CONTENTS_12` | 단위 타이포그래피 |
| `unitColorHex` | `String` | `BLACK_50` | 단위 색상 |
| `minCount` | `Number` | `0` | 최소값 |
| `maxCount` | `Number?` | `null` | 최대값 (null일 경우 무제한) |
| `amount` | `Number` | `1` | 증감 단위 |
| `buttonType` | `HongButtonIconType` | `SIZE_40` | 버튼 크기 |
| `onCountChange` | `(String) -> Unit` | `{}` | 값 변경 콜백 |

### HongTextCountBuilder

빌더 패턴을 사용하여 `HongTextCountOption`을 쉽게 구성할 수 있습니다.

```kotlin
class HongTextCountBuilder :
    HongWidgetCommonBuilder<HongTextCountOption, HongTextCountBuilder>
```

**주요 메서드:**
- `countType(HongCountType)` - 숫자 타입 설정
- `startCount(Number)` - 초기값 설정
- `countTypo(HongTypo)` - 숫자 폰트 스타일
- `countColor(HongColor/String)` - 숫자 색상
- `unitText(String)` - 단위 텍스트
- `minCount(Number)` / `maxCount(Number?)` - 범위 제한
- `amount(Number)` - 증감 단위
- `buttonType(HongButtonIconType)` - 버튼 크기
- `onCountChange((String) -> Unit)` - 값 변경 리스너

## 🚀 사용법

### Jetpack Compose

```kotlin
@Composable
fun CounterScreen() {
    var count by remember { mutableStateOf("1") }

    HongTextCountCompose(
        HongTextCountBuilder()
            .countType(HongCountType.LONG)
            .startCount(1)
            .unitText("개")
            .minCount(0)
            .maxCount(10)
            .amount(1)
            .onCountChange { newValue ->
                count = newValue
            }
            .applyOption()
    )
}
```

### Android View

```kotlin
val countView = HongTextCountView(context)
    .set(
        HongTextCountBuilder()
            .countType(HongCountType.DOUBLE)
            .startCount(2.5)
            .unitText("kg")
            .minCount(0.0)
            .maxCount(100.0)
            .amount(0.5)
            .onCountChange { newValue ->
                Log.d("Counter", "New value: $newValue")
            }
            .applyOption()
    )

parentLayout.addView(countView)
```

### 사용 예시: 소수점 카운터

```kotlin
HongTextCountCompose(
    HongTextCountBuilder()
        .countType(HongCountType.DOUBLE)  // 소수점 타입
        .startCount(0.0)
        .unitText("km")
        .minCount(0.0)
        .maxCount(999.9)
        .amount(0.1)  // 0.1씩 증감
        .countTypo(HongTypo.TITLE_28_B)
        .countColor(HongColor.MAIN_ORANGE_100)
        .onCountChange { distance ->
            viewModel.updateDistance(distance)
        }
        .applyOption()
)
```

## ⚙️ 주요 기능

### 1. 숫자 타입 지원

- **HongCountType.LONG**: 정수 타입으로 동작
- **HongCountType.DOUBLE**: 소수점 1자리 고정 (예: `1.0`, `2.5`)

### 2. 범위 제한

- `minCount` 설정으로 음수 방지
- `maxCount` 설정으로 값 제한 (null일 경우 무제한)
- 범위 초과 시 ➖/➕ 버튼 자동 비활성화

### 3. 유연한 UI

- 입력 필드의 동적 너비 조절 (숫자 길이에 따라 자동 조정)
- 단위 텍스트 표시 지원
- 커스텀 타이포그래피 및 색상 지원
- 다양한 버튼 크기 옵션 (`SIZE_40`, `SIZE_48` 등)

### 4. 다양한 플랫폼 지원

- **Jetpack Compose**: 네이티브 Compose 컴포넌트로 사용
- **Android View**: `ComposeView`를 통해 기존 View 시스템에서도 사용 가능

## 📝 참고사항

- **값 변경 콜백**: `onCountChange`는 값이 변경될 때마다 호출되며, String 타입으로 전달됩니다.
- **동적 너비**: 입력 필드의 너비는 80dp에서 300dp 사이에서 자동으로 조절됩니다.
- **버튼 비활성화**: 최소값/최대값에 도달하면 해당 버튼이 자동으로 비활성화됩니다.
- **키보드 타입**: 숫자 입력 전용 키보드가 표시됩니다.
- **포커스 관리**: Done 버튼 클릭 시 포커스가 자동으로 해제됩니다.
- **Double 타입 형식**: Double 타입 사용 시 항상 소수점 1자리로 표시됩니다 (예: `"%.1f"` 형식).

## 🔗 의존성

이 컴포넌트는 다음 모듈에 의존합니다:

- `HongButtonIconCompose` - 증감 버튼 UI
- `HongTextCompose` - 단위 텍스트 표시
- `HongWidgetContainer` - 공통 위젯 컨테이너
- `HongColor` - 색상 시스템
- `HongTypo` - 타이포그래피 시스템

## 📄 라이선스

이 컴포넌트는 codehong-lib 프로젝트의 일부입니다.
