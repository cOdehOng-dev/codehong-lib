# HongPicker

바텀시트 스타일의 스크롤 피커 위젯입니다. 단일 또는 이중 선택을 지원하며, 스냅 스크롤과 슬라이드 애니메이션을 제공합니다.

## 📋 개요

HongPicker는 iOS 스타일의 휠 피커를 Jetpack Compose로 구현한 바텀시트 위젯입니다. 단일 선택 또는 이중 선택 모드를 지원하며, 직접 선택 또는 확인 버튼을 통한 선택 방식을 제공합니다. 부드러운 스냅 스크롤링과 그라디언트 페이드 효과로 자연스러운 사용자 경험을 제공합니다.

## 🏗️ 구조

```
picker/
├── HongPickerOption.kt       # 피커 옵션 데이터 클래스
├── HongPickerBuilder.kt      # 빌더 패턴 클래스
└── HongPickerCompose.kt      # Compose UI 구현
```

## 📦 주요 클래스

### HongPickerOption
피커의 모든 설정을 담는 데이터 클래스입니다.

**주요 속성:**
- `title: String` - 피커 타이틀
- `titleColorHex: String` - 타이틀 색상
- `buttonText: String` - 확인 버튼 텍스트 (기본값: "선택")
- `firstOptionList: List<String>` - 첫 번째 선택 리스트
- `initialFirstOption: Int` - 첫 번째 선택의 초기값 인덱스
- `secondOptionList: List<String>?` - 두 번째 선택 리스트 (이중 선택 시)
- `initialSecondOption: Int` - 두 번째 선택의 초기값 인덱스
- `useDimClickCLose: Boolean` - Dim 영역 클릭 시 닫기 여부
- `selectorColorHex: String` - 선택된 영역 배경색
- `onDismiss: () -> Unit` - 닫기 콜백
- `onConfirm: ((Pair<Int, String>, Pair<Int, String?>) -> Unit)?` - 확인 버튼 클릭 콜백
- `onDirectSelect: ((Pair<Int, String>, Pair<Int, String?>) -> Unit)?` - 직접 선택 콜백

### HongPickerBuilder
빌더 패턴을 사용하여 피커 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `title(String)` - 타이틀 설정
- `titleColor(HongColor/String)` - 타이틀 색상 설정
- `buttonText(String)` - 확인 버튼 텍스트 설정
- `firstOptionList(List<String>)` - 첫 번째 선택 리스트 설정
- `initialFirstOption(Int)` - 첫 번째 초기값 설정
- `secondOptionList(List<String>?)` - 두 번째 선택 리스트 설정
- `initialSecondOption(Int)` - 두 번째 초기값 설정
- `useDimClickClose(Boolean)` - Dim 클릭 닫기 설정
- `selectorColor(HongColor/String)` - 선택 영역 색상 설정
- `radius(Int, Int)` - 상단 모서리 둥글기 설정
- `onDismiss(() -> Unit)` - 닫기 콜백 설정
- `onConfirm(콜백)` - 확인 버튼 콜백 설정
- `onDirectSelect(콜백)` - 직접 선택 콜백 설정
- `copy(HongPickerOption)` - 옵션 복사

### HongPickerCompose
실제 UI를 렌더링하는 Composable 함수입니다.

**핵심 기능:**
- **바텀시트 애니메이션**: 300ms 슬라이드 인/아웃 애니메이션
- **스냅 스크롤링**: `rememberSnapFlingBehavior`를 사용한 부드러운 스냅
- **이중 피커 지원**: `secondOptionList`가 있을 경우 두 개의 피커 표시
- **선택 감지**: 뷰포트 중앙에 위치한 아이템 자동 선택
- **그라디언트 페이드**: 상/하단 그라디언트로 무한 스크롤 효과 연출
- **동적 스타일링**: 선택된 항목은 진한 색상과 볼드 폰트로 강조

## 🚀 사용법

### 단일 선택 피커 (확인 버튼 모드)

```kotlin
var showPicker by remember { mutableStateOf(false) }
var selectedValue by remember { mutableStateOf("") }

HongPickerCompose(
    visible = showPicker,
    option = HongPickerBuilder()
        .title("옵션 선택")
        .buttonText("확인")
        .firstOptionList(listOf("사과", "바나나", "오렌지", "포도"))
        .initialFirstOption(0)
        .onConfirm { first, _ ->
            selectedValue = first.second
        }
        .onDismiss {
            showPicker = false
        }
        .applyOption()
)
```

### 이중 선택 피커

```kotlin
var showPicker by remember { mutableStateOf(false) }
var selectedDate by remember { mutableStateOf("") }

HongPickerCompose(
    visible = showPicker,
    option = HongPickerBuilder()
        .title("날짜 선택")
        .firstOptionList((1..12).map { "${it}월" })
        .initialFirstOption(0)
        .secondOptionList((1..31).map { "${it}일" })
        .initialSecondOption(0)
        .onConfirm { first, second ->
            selectedDate = "${first.second} ${second.second}"
        }
        .onDismiss {
            showPicker = false
        }
        .applyOption()
)
```

### 직접 선택 모드

```kotlin
var showPicker by remember { mutableStateOf(false) }

HongPickerCompose(
    visible = showPicker,
    option = HongPickerBuilder()
        .title("빠른 선택")
        .firstOptionList(listOf("옵션1", "옵션2", "옵션3"))
        .onDirectSelect { first, _ ->
            // 스크롤 시 즉시 선택됨
            println("선택됨: ${first.second}")
        }
        .onDismiss {
            showPicker = false
        }
        .applyOption()
)
```

### 커스터마이징

```kotlin
HongPickerCompose(
    visible = showPicker,
    option = HongPickerBuilder()
        .title("커스텀 피커")
        .titleColor(HongColor.MAIN_ORANGE_100)
        .backgroundColor(HongColor.WHITE_100.hex)
        .selectorColor(HongColor.BLUE_10)
        .radius(topLeft = 24, topRight = 24)
        .useDimClickClose(true) // Dim 영역 클릭 시 닫기
        .firstOptionList(listOf("A", "B", "C"))
        .onConfirm { first, _ ->
            println("확인: ${first.second}")
        }
        .onDismiss {
            showPicker = false
        }
        .applyOption()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `title(String)` | 피커 상단에 표시될 타이틀 설정 |
| `titleColor(HongColor/String)` | 타이틀 색상 설정 |
| `buttonText(String)` | 확인 버튼 텍스트 설정 (기본값: "선택") |
| `firstOptionList(List<String>)` | 첫 번째 피커의 선택 항목 리스트 설정 |
| `initialFirstOption(Int)` | 첫 번째 피커의 초기 선택 인덱스 |
| `secondOptionList(List<String>?)` | 두 번째 피커의 선택 항목 리스트 (이중 선택 시) |
| `initialSecondOption(Int)` | 두 번째 피커의 초기 선택 인덱스 |
| `useDimClickClose(Boolean)` | Dim 영역 클릭 시 피커 닫기 여부 |
| `selectorColor(HongColor/String)` | 선택된 항목 배경 영역 색상 설정 |
| `radius(Int, Int)` | 바텀시트 상단 좌/우 모서리 둥글기 설정 |
| `onDismiss(() -> Unit)` | 피커 닫기 시 호출되는 콜백 |
| `onConfirm(콜백)` | 확인 버튼 클릭 시 호출 (선택값 전달) |
| `onDirectSelect(콜백)` | 스크롤 시 즉시 선택 (확인 버튼 숨김) |

## 🎨 UI 구성

### 레이아웃 구조
1. **Dim 배경**: 반투명 회색 배경 (클릭 닫기 옵션)
2. **바텀시트 컨테이너**: 하단에서 슬라이드 업
3. **헤더 영역**: 타이틀 + 닫기 버튼 (직접 선택 모드에서는 닫기 버튼 숨김)
4. **피커 영역**: 157dp 고정 높이, 1개 또는 2개의 피커
5. **확인 버튼**: 48dp 높이의 주황색 버튼 (직접 선택 모드에서도 표시)

### 피커 동작
- **항목 높이**: 36dp 고정
- **선택 영역**: 회색 배경으로 강조 (기본값: GRAY_10)
- **선택 항목**: 볼드 + 검정색 (BODY_20_B)
- **비선택 항목**: 일반 + 회색 (BODY_18, GRAY_50)
- **그라디언트**: 상/하단 8dp 페이드 효과

## 📝 참고사항

- `onConfirm`과 `onDirectSelect`는 함께 사용할 수 없습니다
  - `onDirectSelect` 설정 시 닫기 버튼이 표시되고 확인 버튼은 닫기 전용
  - `onConfirm` 설정 시 선택 후 확인 버튼 클릭 필요
- `secondOptionList`가 `null` 또는 빈 리스트면 단일 피커로 동작
- 피커는 `visible` 파라미터로 표시/숨김 제어
- 초기값 인덱스는 0부터 시작하며, 리스트 범위를 벗어나지 않도록 주의
- 바텀시트 애니메이션은 300ms 소요
- 선택된 항목은 뷰포트 중앙을 기준으로 자동 계산
- 기본 상단 모서리 둥글기는 16dp
- `HongWidgetCommonOption`을 상속하여 공통 위젯 기능 사용 가능
- Dim 배경색은 GRAY_30으로 고정
- 확인 버튼 배경색은 MAIN_ORANGE_100으로 고정
