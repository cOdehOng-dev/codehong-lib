# HongDragAndDrop

Jetpack Compose 기반의 그리드 레이아웃에서 드래그 앤 드롭 기능을 제공하는 라이브러리입니다. Long Press를 통해 편집 모드로 진입하고, 아이템을 드래그하여 위치를 변경할 수 있습니다.

## 📋 개요

HongDragAndDrop은 Jetpack Compose를 사용하여 직관적인 드래그 앤 드롭 인터랙션을 제공합니다. LazyVerticalGrid를 기반으로 하며, 아이템 재배치 시 Shaking 애니메이션과 함께 시각적 피드백을 제공합니다.

## 🏗️ 구조

```
draganddrop/
├── HongDragAndDrop.kt                 # 핵심 드래그 앤 드롭 로직
├── HongDragTargetInfo.kt              # 드래그 상태 관리
├── HongDragAndDropItem.kt             # 드래그 가능한 아이템 컴포넌트
├── HongGridDragAndDropCompose.kt      # 그리드 드래그 앤 드롭 UI
├── HongGridDragAndDropBuilder.kt      # Builder 패턴 구현
└── HongGridDragAndDropOption.kt       # 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongLongPressDraggable
드래그 가능한 영역을 제공하는 Composable 컨테이너입니다. Long Press 제스처를 감지하고 드래그 상태를 관리합니다.

### HongDragTarget
드래그 소스 및 드롭 타겟을 정의하는 Composable입니다. 드래그 제스처를 감지하고 드래그된 데이터를 처리합니다.

### HongDragTargetInfo
드래그 상태를 관리하는 내부 클래스입니다.
- `isDragging`: 현재 드래그 중인지 여부
- `dragPosition`: 드래그 시작 위치
- `dragOffset`: 드래그 이동 거리
- `draggableComposable`: 드래그 중인 Composable
- `dataToDrop`: 드롭될 데이터

### HongGridDragAndDropCompose
그리드 레이아웃의 드래그 앤 드롭 UI를 제공하는 메인 Composable입니다.
- LazyVerticalGrid 기반 그리드 레이아웃
- 편집 모드 지원 (Long Press로 활성화)
- BackHandler를 통한 편집 모드 취소

### HongDragAndDropItem
개별 드래그 가능한 아이템 컴포넌트입니다.
- Shaking 애니메이션 지원
- Long Press 및 Click 이벤트 처리
- 편집 모드에서만 드래그 가능

### HongGridDragAndDropBuilder
Builder 패턴을 사용하여 옵션을 설정합니다.
- 메서드 체이닝을 통한 직관적인 설정
- 불변성 보장

### HongGridDragAndDropOption
드래그 앤 드롭 그리드의 옵션을 정의하는 데이터 클래스입니다.
- 그리드 컬럼 수, 스페이싱, 패딩 설정
- 배경색, 테두리, 그림자 등 스타일링 옵션
- 아이템 클릭 및 뒤로가기 콜백

## 🚀 사용법

```kotlin
// Builder를 사용한 옵션 설정
val option = HongGridDragAndDropBuilder()
    .itemList(yourItemList)
    .gridColumns(3)
    .gridHorizontalSpacing(8)
    .gridVerticalSpacing(8)
    .onItemClick { /* 아이템 클릭 처리 */ }
    .onBackClick { /* 뒤로가기 처리 */ }
    .backgroundColor("#FFFFFF")
    .inboundColorHex("#FF6B35")
    .build()

// Composable 사용
HongGridDragAndDropCompose(
    option = option,
    subContent = { colorHex ->
        // 하단 추가 컨텐츠
    }
) { item ->
    // 각 아이템의 UI 정의
    YourItemContent(item)
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `itemList(List<Any>)` | 표시할 아이템 리스트 설정 |
| `gridColumns(Int)` | 그리드 컬럼 수 설정 (기본값: 3) |
| `gridHorizontalSpacing(Int)` | 수평 간격 설정 (기본값: 8dp) |
| `gridVerticalSpacing(Int)` | 수직 간격 설정 (기본값: 8dp) |
| `gridContentPadding(HongSpacingInfo)` | 그리드 내부 패딩 설정 |
| `onItemClick(() -> Unit)` | 아이템 클릭 콜백 설정 |
| `onBackClick(() -> Unit)` | 뒤로가기 버튼 콜백 설정 |
| `inboundColorHex(String)` | 드롭 영역 강조 색상 설정 |
| `backgroundColor(String)` | 배경색 설정 |

## 🎨 주요 기능

### 편집 모드
- **진입**: 아이템을 Long Press하면 편집 모드로 진입
- **시각적 피드백**: 편집 모드에서 아이템이 Shaking 애니메이션 표시
- **종료**: 뒤로가기 버튼 또는 `onBackClick` 콜백으로 종료

### 드래그 앤 드롭
- Long Press 후 드래그 가능
- 드래그 중 아이템의 크기가 70%로 축소되어 표시
- 드롭 위치에 따라 실시간으로 피드백 제공

### 애니메이션
- **Shaking 효과**: 편집 모드에서 아이템이 -2°~2° 범위로 회전
- **드래그 중 스케일**: 드래그 중인 아이템은 0.7배 크기로 표시
- **부드러운 전환**: LinearEasing을 사용한 자연스러운 애니메이션

## 📝 참고사항

- Jetpack Compose 기반으로 구동되므로 Compose UI 환경에서만 사용 가능합니다
- `HongWidgetCommonOption`을 구현하여 공통 위젯 옵션을 지원합니다
- 편집 모드가 아닐 때는 일반 클릭 이벤트만 동작합니다
- 드래그 제스처는 `detectDragGesturesAfterLongPress`를 사용하여 구현되었습니다
- CompositionLocal을 활용하여 드래그 상태를 전역적으로 관리합니다
- 원본 참고: [Android Drag and Drop UI Element in Jetpack Compose](https://canopas.com/android-drag-and-drop-ui-element-in-jetpack-compose-14922073b3f1)

## 🔗 관련 문서

- [HongWidgetCommonOption](../HongWidgetCommonOption.kt)
- [HongSpacingInfo](../rule/HongSpacingInfo.kt)
- [HongColor](../rule/color/HongColor.kt)
