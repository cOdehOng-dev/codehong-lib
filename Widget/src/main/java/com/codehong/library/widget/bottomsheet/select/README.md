# HongBottomSheetSelect

여러 옵션 중 하나를 선택할 수 있는 바텀시트 컴포넌트입니다. Material3 ModalBottomSheet를 기반으로 제작되었으며, 제목과 설명이 있는 선택 항목 리스트를 제공합니다.

## 📋 개요

HongBottomSheetSelect는 Jetpack Compose 기반의 선택 바텀시트 컴포넌트로, 다음과 같은 특징을 제공합니다:

- Material3 ModalBottomSheet 기반
- 제목(Title)과 부제목(Subtitle)을 가진 선택 항목
- 선택된 항목 강조 표시 (Border)
- 드래그 핸들을 통한 바텀시트 제어
- 자동 닫기 기능 (항목 선택 시)
- Builder 패턴을 통한 쉬운 설정
- 완전한 커스터마이징 지원 (색상, 타이포그래피, 반경 등)

## 🏗️ 구조

```
select/
├── HongBottomSheetSelectCompose.kt    # 메인 Composable 함수
├── HongBottomSheetSelectBuilder.kt    # Builder 클래스
└── HongBottomSheetSelectOption.kt     # 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongBottomSheetSelectCompose
메인 Composable 함수로, 선택 가능한 바텀시트 UI를 렌더링합니다.

**주요 기능:**
- Material3 ModalBottomSheet를 사용한 바텀시트 표시
- 헤더 영역 (제목 + 닫기 버튼)
- 선택 항목 리스트 렌더링
- 선택된 항목 border 강조
- 항목 선택 시 자동 닫기

**파라미터:**
- `showBottomSheet: Boolean` - 바텀시트 표시 여부
- `option: HongBottomSheetSelectOption` - 바텀시트 설정 옵션

### HongBottomSheetSelectBuilder
Builder 패턴을 통해 `HongBottomSheetSelectOption`을 구성하는 클래스입니다.

**주요 메서드:**
- `topRadius(Int)` - 바텀시트 상단 모서리 반경 설정
- `dimColor(String/HongColor)` - 딤 처리 색상 설정
- `dragHandleColor(String/HongColor)` - 드래그 핸들 색상 설정
- `title(String)` - 바텀시트 제목 설정
- `titleTypo(HongTypo)` - 제목 타이포그래피 설정
- `titleColor(String/HongColor)` - 제목 색상 설정
- `selectionBackgroundColor(String/HongColor)` - 선택 항목 배경색 설정
- `selectionRadius(HongRadiusInfo)` - 선택 항목 모서리 반경 설정
- `selectionSelectorBorder(HongBorderInfo)` - 선택된 항목의 border 설정
- `selectionTitleTypo(HongTypo)` - 선택 항목 제목 타이포그래피 설정
- `selectionTitleColor(String/HongColor)` - 선택 항목 제목 색상 설정
- `selectionSubtitleTypo(HongTypo)` - 선택 항목 부제목 타이포그래피 설정
- `selectionSubtitleColor(String/HongColor)` - 선택 항목 부제목 색상 설정
- `initialSelection(Pair<String, String>)` - 초기 선택 항목 설정
- `selectionList(List<Pair<String, String>>)` - 선택 가능한 항목 리스트 설정
- `selectSelectionCallback((Pair<String, String>) -> Unit)` - 항목 선택 시 콜백 설정
- `onChangeVisibleState((Boolean) -> Unit)` - 바텀시트 표시 상태 변경 콜백 설정

### HongBottomSheetSelectOption
바텀시트의 모든 설정값을 담는 데이터 클래스입니다.

**주요 속성:**
- `topRadius` - 바텀시트 상단 모서리 반경 (기본값: 24)
- `dimColorHex` - 딤 처리 색상 (기본값: GRAY_30)
- `dragHandleColorHex` - 드래그 핸들 색상 (기본값: GRAY_50)
- `title` - 바텀시트 제목 (기본값: "")
- `titleTypo` - 제목 타이포그래피 (기본값: BODY_20_B)
- `titleColorHex` - 제목 색상 (기본값: BLACK_100)
- `selectionBackgroundColorHex` - 선택 항목 배경색 (기본값: GRAY_05)
- `selectionRadius` - 선택 항목 모서리 반경 (기본값: 8)
- `selectionSelectorBorder` - 선택된 항목 border (기본값: 2px, MAIN_ORANGE_100)
- `selectionTitleTypo` - 선택 항목 제목 타이포그래피 (기본값: BODY_16)
- `selectionTitleColorHex` - 선택 항목 제목 색상 (기본값: BLACK_100)
- `selectionSubtitleTypo` - 선택 항목 부제목 타이포그래피 (기본값: CONTENTS_12)
- `selectionSubtitleColorHex` - 선택 항목 부제목 색상 (기본값: BLACK_70)
- `selectionList` - 선택 가능한 항목 리스트 (Pair<제목, 부제목>)
- `initialSelection` - 초기 선택 항목
- `selectSelectionCallback` - 항목 선택 시 콜백
- `onChangeVisibleState` - 바텀시트 표시 상태 변경 콜백

## 🚀 사용법

```kotlin
// 상태 관리
var showBottomSheet by remember { mutableStateOf(false) }
var selectedOption by remember { mutableStateOf(Pair("옵션1", "설명1")) }

// 선택 항목 리스트
val optionList = listOf(
    Pair("옵션1", "설명1"),
    Pair("옵션2", "설명2"),
    Pair("옵션3", "설명3"),
    Pair("옵션4", "") // 부제목 없음
)

// 바텀시트 표시 버튼
Button(onClick = { showBottomSheet = true }) {
    Text("옵션 선택")
}

// 바텀시트 컴포넌트
HongBottomSheetSelectCompose(
    showBottomSheet = showBottomSheet,
    option = HongBottomSheetSelectBuilder()
        .topRadius(24)
        .dimColor(HongColor.GRAY_30)
        .dragHandleColor(HongColor.GRAY_50)
        .title("옵션을 선택하세요")
        .titleTypo(HongTypo.BODY_20_B)
        .titleColor(HongColor.BLACK_100)
        .selectionBackgroundColor(HongColor.GRAY_05)
        .selectionRadius(HongRadiusInfo(8))
        .selectionSelectorBorder(
            HongBorderInfo(
                width = 2,
                color = HongColor.MAIN_ORANGE_100.hex
            )
        )
        .selectionTitleTypo(HongTypo.BODY_16)
        .selectionTitleColor(HongColor.BLACK_100)
        .selectionSubtitleTypo(HongTypo.CONTENTS_12)
        .selectionSubtitleColor(HongColor.BLACK_70)
        .initialSelection(selectedOption)
        .selectionList(optionList)
        .selectSelectionCallback { selected ->
            selectedOption = selected
            // 선택된 항목 처리
            Log.d("Selected", "${selected.first}: ${selected.second}")
        }
        .onChangeVisibleState { isVisible ->
            showBottomSheet = isVisible
        }
        .applyOption()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `topRadius(Int)` | 바텀시트 상단 모서리의 반경을 설정합니다 |
| `dimColor(HongColor/String)` | 바텀시트 뒤의 딤 처리 색상을 설정합니다 |
| `dragHandleColor(HongColor/String)` | 드래그 핸들의 색상을 설정합니다 |
| `title(String)` | 바텀시트 상단의 제목을 설정합니다 |
| `titleTypo(HongTypo)` | 제목의 타이포그래피를 설정합니다 |
| `titleColor(HongColor/String)` | 제목의 색상을 설정합니다 |
| `selectionBackgroundColor(HongColor/String)` | 선택 항목의 배경색을 설정합니다 |
| `selectionRadius(HongRadiusInfo)` | 선택 항목의 모서리 반경을 설정합니다 |
| `selectionSelectorBorder(HongBorderInfo)` | 선택된 항목의 border 스타일을 설정합니다 |
| `selectionTitleTypo(HongTypo)` | 선택 항목 제목의 타이포그래피를 설정합니다 |
| `selectionTitleColor(HongColor/String)` | 선택 항목 제목의 색상을 설정합니다 |
| `selectionSubtitleTypo(HongTypo)` | 선택 항목 부제목의 타이포그래피를 설정합니다 |
| `selectionSubtitleColor(HongColor/String)` | 선택 항목 부제목의 색상을 설정합니다 |
| `initialSelection(Pair<String, String>)` | 초기 선택 항목을 설정합니다 |
| `selectionList(List<Pair<String, String>>)` | 선택 가능한 항목들의 리스트를 설정합니다 |
| `selectSelectionCallback((Pair<String, String>) -> Unit)` | 항목 선택 시 호출될 콜백을 설정합니다 |
| `onChangeVisibleState((Boolean) -> Unit)` | 바텀시트 표시 상태 변경 시 호출될 콜백을 설정합니다 |
| `copy(HongBottomSheetSelectOption?)` | 기존 옵션을 복사하여 새로운 Builder를 생성합니다 |

## 📝 참고사항

- 선택 항목은 `Pair<String, String>` 형태로, first는 제목, second는 부제목입니다
- 부제목이 없는 경우 빈 문자열("")을 사용하면 부제목이 표시되지 않습니다
- 선택된 항목은 `selectionSelectorBorder`로 설정한 border로 강조 표시됩니다
- 항목 선택 시 자동으로 바텀시트가 닫히며, `selectSelectionCallback`이 호출됩니다
- `onChangeVisibleState` 콜백을 통해 바텀시트의 표시 상태를 외부에서 관리할 수 있습니다
- Material3의 `ModalBottomSheet`를 사용하므로 `@OptIn(ExperimentalMaterial3Api::class)` 어노테이션이 필요합니다
- `skipPartiallyExpanded = true`로 설정되어 부분 확장 상태를 건너뜁니다
- 드래그 핸들을 통해 사용자가 직관적으로 바텀시트를 제어할 수 있습니다
- 닫기 버튼을 클릭하거나 바텀시트 외부를 클릭하면 바텀시트가 닫힙니다
