# HongTabFlow

FlowRow 레이아웃을 사용하는 다중 행 탭 선택 위젯입니다. 탭이 자동으로 여러 줄로 배치되며, 선택된 탭은 시각적으로 강조됩니다.

## 📋 개요

HongTabFlow는 많은 수의 탭을 효율적으로 표시할 수 있는 위젯입니다. `FlowRow`를 사용하여 탭이 자동으로 줄바꿈되며, 한 줄에 표시할 최대 탭 개수를 설정할 수 있습니다. 선택/미선택 상태에 따라 배경색, 테두리, 텍스트 스타일이 자동으로 변경됩니다.

**주요 특징:**
- FlowRow 레이아웃으로 자동 줄바꿈
- 한 줄당 최대 탭 개수 설정 (기본값: 3개)
- 선택/미선택 상태에 따른 자동 스타일 변경
- 탭 간격 및 행 간격 커스터마이징
- 중앙 정렬 레이아웃
- 선택 이벤트 콜백 지원

## 🏗️ 구조

```
tab/flow/
├── HongTabFlowCompose.kt    # Jetpack Compose UI 구현
├── HongTabFlowView.kt        # Android View 래퍼 클래스
├── HongTabFlowOption.kt      # 설정 데이터 클래스
└── HongTabFlowBuilder.kt     # 빌더 패턴 구현
```

## 📦 주요 클래스

### HongTabFlowCompose

Jetpack Compose로 구현된 FlowRow 기반 탭 레이아웃입니다.

**주요 기능:**
- `FlowRow`로 탭을 자동 줄바꿈하여 배치
- `maxItemsInEachRow`로 한 줄당 최대 탭 개수 제한
- `Arrangement.Center`로 수평/수직 중앙 정렬
- 선택된 탭의 인덱스를 `rememberSaveable`로 상태 관리
- 동적 패딩 계산 (마지막 탭 및 줄 끝 탭은 오른쪽 패딩 제거)
- 탭 클릭 시 `onSelect` 콜백 호출

```kotlin
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HongTabFlowCompose(
    option: HongTabFlowOption
)
```

**레이아웃 구조:**
```
┌─────────┬─────────┬─────────┐
│  탭 1   │  탭 2   │  탭 3   │  ← 첫 번째 행
├─────────┼─────────┼─────────┤
│  탭 4   │  탭 5   │  탭 6   │  ← 두 번째 행
└─────────┴─────────┴─────────┘
      (최대 3개씩 자동 배치)
```

### HongTabFlowView

Android View 시스템을 위한 래퍼 클래스입니다. `ComposeView`를 생성하여 `HongTabFlowCompose`를 호스팅합니다.

```kotlin
class HongTabFlowView(
    private val context: Context
) {
    fun set(option: HongTabFlowOption): ComposeView
}
```

**특징:**
- 간단한 래퍼 패턴으로 View 시스템 통합
- 내부적으로 Compose UI를 재사용

### HongTabFlowOption

위젯의 모든 설정을 포함하는 데이터 클래스입니다.

**주요 속성:**

| 속성 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `tabList` | `List<String>` | `emptyList()` | 탭 레이블 리스트 |
| `initialSelectedIndex` | `Int` | `0` | 초기 선택된 탭 인덱스 |
| `maxRowCount` | `Int` | `3` | 한 줄당 최대 탭 개수 |
| `betweenTabSpacing` | `Int` | `10` | 탭 간 가로 간격 (dp) |
| `rowSpacing` | `Int` | `10` | 행 간 세로 간격 (dp) |
| `tabRadius` | `HongRadiusInfo` | `18` (전체) | 탭 모서리 둥글기 |
| `selectBackgroundColorHex` | `String` | `WHITE_100` | 선택된 탭 배경색 |
| `unselectTabBackgroundColorHex` | `String` | `WHITE_100` | 미선택 탭 배경색 |
| `selectedBorder` | `HongBorderInfo` | 2px, `BLACK_80` | 선택된 탭 테두리 |
| `unselectedBorder` | `HongBorderInfo` | 1px, `GRAY_30` | 미선택 탭 테두리 |
| `selectTextColorHex` | `String` | `BLACK_100` | 선택된 탭 텍스트 색상 |
| `unselectTextColorHex` | `String` | `BLACK_100` | 미선택 탭 텍스트 색상 |
| `selectTextTypo` | `HongTypo` | `BODY_16_B` | 선택된 탭 타이포그래피 (Bold) |
| `unselectTextTypo` | `HongTypo` | `BODY_16` | 미선택 탭 타이포그래피 |
| `onSelect` | `(Int) -> Unit?` | `null` | 탭 선택 시 콜백 |

### HongTabFlowBuilder

빌더 패턴을 사용하여 `HongTabFlowOption`을 쉽게 구성할 수 있습니다.

```kotlin
class HongTabFlowBuilder :
    HongWidgetCommonBuilder<HongTabFlowOption, HongTabFlowBuilder>
```

**주요 메서드:**
- `tabList(List<String>)` - 탭 레이블 리스트 설정
- `initialSelectedIndex(Int)` - 초기 선택 탭 인덱스
- `maxRowCount(Int)` - 한 줄당 최대 탭 개수
- `betweenTabSpacing(Int)` - 탭 간 가로 간격
- `rowSpacing(Int)` - 행 간 세로 간격
- `tabRadius(HongRadiusInfo)` - 탭 모서리 둥글기
- `selectBackgroundColor(HongColor/String)` - 선택된 탭 배경색
- `unselectTabBackgroundColor(HongColor/String)` - 미선택 탭 배경색
- `selectedBorder(HongBorderInfo)` - 선택된 탭 테두리
- `unselectedBorder(HongBorderInfo)` - 미선택 탭 테두리
- `selectTextColor(HongColor/String)` - 선택된 탭 텍스트 색상
- `unselectTextColor(HongColor/String)` - 미선택 탭 텍스트 색상
- `selectTextTypo(HongTypo)` - 선택된 탭 타이포그래피
- `unselectTextTypo(HongTypo)` - 미선택 탭 타이포그래피
- `onSelect((Int) -> Unit)` - 탭 선택 리스너
- `copy(HongTabFlowOption?)` - 옵션 복사

## 🚀 사용법

### Jetpack Compose

```kotlin
@Composable
fun CategoryFilterScreen() {
    var selectedCategory by remember { mutableStateOf(0) }

    HongTabFlowCompose(
        HongTabFlowBuilder()
            .tabList(
                listOf(
                    "전체", "의류", "식품",
                    "가전", "도서", "스포츠",
                    "뷰티", "가구", "문구"
                )
            )
            .initialSelectedIndex(0)
            .maxRowCount(3)  // 한 줄에 최대 3개
            .betweenTabSpacing(12)
            .rowSpacing(12)
            .onSelect { index ->
                selectedCategory = index
                Log.d("Category", "Selected: ${categories[index]}")
            }
            .applyOption()
    )
}
```

### Android View

```kotlin
val tabFlowView = HongTabFlowView(context)
    .set(
        HongTabFlowBuilder()
            .tabList(listOf("옵션1", "옵션2", "옵션3", "옵션4", "옵션5"))
            .initialSelectedIndex(0)
            .maxRowCount(3)
            .onSelect { index ->
                handleTabSelection(index)
            }
            .applyOption()
    )

parentLayout.addView(tabFlowView)
```

### 사용 예시: 커스텀 스타일

```kotlin
HongTabFlowCompose(
    HongTabFlowBuilder()
        .tabList(listOf("사과", "바나나", "오렌지", "포도", "딸기", "수박"))
        .initialSelectedIndex(0)
        .maxRowCount(2)  // 한 줄에 2개씩
        .betweenTabSpacing(16)
        .rowSpacing(16)
        .tabRadius(HongRadiusInfo(24, 24, 24, 24))  // 더 둥글게
        .selectBackgroundColor(HongColor.MAIN_ORANGE_100)
        .unselectTabBackgroundColor(HongColor.GRAY_10)
        .selectedBorder(
            HongBorderInfo(
                width = 0,  // 테두리 없음
                color = HongColor.TRANSPARENT.hex
            )
        )
        .selectTextColor(HongColor.WHITE_100)
        .unselectTextColor(HongColor.GRAY_60)
        .selectTextTypo(HongTypo.TITLE_18_B)
        .unselectTextTypo(HongTypo.BODY_16)
        .onSelect { index ->
            viewModel.selectFruit(index)
        }
        .applyOption()
)
```

### 사용 예시: 많은 탭

```kotlin
HongTabFlowCompose(
    HongTabFlowBuilder()
        .tabList(
            listOf(
                "서울", "부산", "대구", "인천", "광주",
                "대전", "울산", "세종", "경기", "강원",
                "충북", "충남", "전북", "전남", "경북", "경남", "제주"
            )
        )
        .initialSelectedIndex(0)
        .maxRowCount(4)  // 한 줄에 4개씩
        .betweenTabSpacing(8)
        .rowSpacing(10)
        .onSelect { index ->
            filterByRegion(index)
        }
        .applyOption()
)
```

## ⚙️ 주요 기능

### 1. 자동 줄바꿈 레이아웃

- `FlowRow`를 사용하여 탭이 자동으로 여러 줄로 배치
- `maxRowCount`로 한 줄당 최대 탭 개수 제한
- 탭 개수가 많아도 자동으로 적절히 배치

### 2. 선택 상태 관리

- 선택된 탭의 인덱스를 내부 상태로 관리
- 화면 회전 시에도 선택 상태 유지 (`rememberSaveable`)
- 탭 클릭 시 자동으로 상태 업데이트 및 콜백 호출

### 3. 선택/미선택 시각적 구분

- **배경색**: 선택/미선택에 따라 다른 배경색 적용
- **테두리**: 선택된 탭은 더 굵은 테두리 (기본 2px), 미선택은 얇은 테두리 (기본 1px)
- **텍스트**: 선택된 탭은 볼드 타이포그래피 적용

### 4. 간격 제어

- **탭 간 가로 간격**: `betweenTabSpacing`으로 설정
- **행 간 세로 간격**: `rowSpacing`으로 설정
- 마지막 탭 및 줄 끝 탭은 오른쪽 패딩 자동 제거

### 5. 중앙 정렬

- 수평/수직 모두 중앙 정렬 (`Arrangement.Center`)
- 탭이 균등하게 분산되어 배치

## 📝 참고사항

- **FlowRow**: Compose의 `ExperimentalLayoutApi`를 사용합니다.
- **초기 선택**: `initialSelectedIndex`로 초기 선택된 탭을 설정할 수 있습니다.
- **탭 높이**: 각 탭의 높이는 36dp로 고정되어 있습니다.
- **텍스트 패딩**: 탭 내부 텍스트는 좌우 21dp, 상하 8dp의 패딩을 가집니다.
- **콜백**: `onSelect` 콜백은 선택된 탭의 인덱스(Int)를 전달합니다.
- **간격 계산**: 마지막 탭과 줄 끝 탭은 오른쪽 패딩이 자동으로 제거되어 깔끔한 레이아웃을 유지합니다.
- **성능**: 많은 수의 탭도 효율적으로 렌더링됩니다.

## 🔗 의존성

이 컴포넌트는 다음 모듈에 의존합니다:

- `HongTextCompose` - 탭 레이블 텍스트 렌더링
- `HongColor` - 색상 시스템
- `HongTypo` - 타이포그래피 시스템
- `HongBorderInfo` - 테두리 설정
- `HongRadiusInfo` - 모서리 둥글기 설정
- `HongSpacingInfo` - 간격 설정

## 📄 라이선스

이 컴포넌트는 codehong-lib 프로젝트의 일부입니다.
