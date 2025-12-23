# HongBottomSheetSwipe

드래그 제스처를 통해 높이를 조절할 수 있는 스와이프 바텀시트 컴포넌트입니다. 바텀시트가 확장될 때 배경 콘텐츠가 스케일 및 오프셋 애니메이션과 함께 변화하여 시각적으로 몰입감 있는 사용자 경험을 제공합니다.

## 📋 개요

HongBottomSheetSwipe는 Jetpack Compose 기반의 커스텀 바텀시트 컴포넌트로, 다음과 같은 특징을 제공합니다:

- 드래그 제스처를 통한 바텀시트 높이 조절
- 바텀시트 확장 시 배경 콘텐츠의 스케일 및 위치 애니메이션
- 최소/최대 높이 설정 가능
- 상단 닫기 버튼 제공
- Builder 패턴을 통한 쉬운 설정
- 완전한 커스터마이징 지원 (색상, 반경, 콘텐츠 등)

## 🏗️ 구조

```
swipe/
├── HongBottomSheetSwipe.kt           # 메인 Composable 함수
├── HongBottomSheetSwipeBuilder.kt    # Builder 클래스
└── HongBottomSheetSwipeOption.kt     # 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongBottomSheetSwipe
메인 Composable 함수로, 스와이프 가능한 바텀시트 UI를 렌더링합니다.

**주요 기능:**
- 드래그 제스처 감지 및 처리
- 바텀시트 높이 애니메이션
- 배경 콘텐츠 스케일 및 오프셋 애니메이션
- 상단 드래그 핸들 클릭으로 최소/최대 높이 토글

### HongBottomSheetSwipeBuilder
Builder 패턴을 통해 `HongBottomSheetSwipeOption`을 구성하는 클래스입니다.

**주요 메서드:**
- `bottomSheetMaxHeight(Float)` - 바텀시트 최대 높이 설정
- `bottomSheetMinHeight(Float)` - 바텀시트 최소 높이 설정
- `bottomSheetBackgroundColor(String/HongColor)` - 바텀시트 배경색 설정
- `bottomSheetTopRadius(Int)` - 바텀시트 상단 모서리 반경 설정
- `closeIconColor(String/HongColor)` - 닫기 아이콘 색상 설정
- `onClose(() -> Unit)` - 닫기 버튼 클릭 이벤트 핸들러
- `content(@Composable BoxScope.() -> Unit)` - 배경 콘텐츠 설정
- `bottomSheetContent(@Composable ColumnScope.() -> Unit)` - 바텀시트 콘텐츠 설정

### HongBottomSheetSwipeOption
바텀시트의 모든 설정값을 담는 데이터 클래스입니다.

**주요 속성:**
- `bottomSheetMaxHeight` - 바텀시트 최대 높이 (기본값: 280f)
- `bottomSheetMinHeight` - 바텀시트 최소 높이 (기본값: 50f)
- `bottomSheetBackgroundColorHex` - 바텀시트 배경색 (기본값: WHITE_100)
- `bottomSheetTopRadius` - 바텀시트 상단 모서리 반경 (기본값: 20)
- `backgroundColorHex` - 전체 배경색 (기본값: MAIN_ORANGE_100)
- `closeIconColorHex` - 닫기 아이콘 색상 (기본값: BLACK_100)
- `onCloseClick` - 닫기 버튼 클릭 콜백
- `content` - 배경 콘텐츠 Composable
- `bottomSheetContent` - 바텀시트 콘텐츠 Composable

## 🚀 사용법

```kotlin
// Builder 패턴을 사용한 기본 사용법
HongBottomSheetSwipe(
    HongBottomSheetSwipeBuilder()
        .bottomSheetMaxHeight(600f)
        .bottomSheetMinHeight(100f)
        .bottomSheetBackgroundColor(HongColor.WHITE_100)
        .bottomSheetTopRadius(24)
        .closeIconColor(HongColor.BLACK_100)
        .onClose {
            // 닫기 버튼 클릭 시 동작
            finish()
        }
        .content {
            // 배경 콘텐츠
            Text("메인 콘텐츠")
        }
        .bottomSheetContent {
            // 바텀시트 콘텐츠
            Column {
                Text("바텀시트 내용")
                Button(onClick = { }) {
                    Text("확인")
                }
            }
        }
        .applyOption()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `bottomSheetMaxHeight(Float)` | 바텀시트의 최대 높이를 설정합니다 |
| `bottomSheetMinHeight(Float)` | 바텀시트의 최소 높이를 설정합니다 |
| `bottomSheetBackgroundColor(HongColor/String)` | 바텀시트의 배경색을 설정합니다 |
| `bottomSheetTopRadius(Int)` | 바텀시트 상단 모서리의 반경을 설정합니다 |
| `closeIconColor(HongColor/String)` | 닫기 아이콘의 색상을 설정합니다 |
| `backgroundColor(HongColor/String)` | 전체 배경색을 설정합니다 |
| `onClose(() -> Unit)` | 닫기 버튼 클릭 시 실행될 콜백을 설정합니다 |
| `content(@Composable BoxScope.() -> Unit)` | 배경에 표시될 콘텐츠를 설정합니다 |
| `bottomSheetContent(@Composable ColumnScope.() -> Unit)` | 바텀시트에 표시될 콘텐츠를 설정합니다 |
| `copy(HongBottomSheetSwipeOption?)` | 기존 옵션을 복사하여 새로운 Builder를 생성합니다 |

## 📝 참고사항

- 드래그 제스처를 통해 바텀시트 높이를 자유롭게 조절할 수 있습니다
- 상단의 드래그 핸들(회색 바)을 클릭하면 최소/최대 높이로 토글됩니다
- 바텀시트가 확장될 때 배경 콘텐츠는 최대 30%까지 축소되며 위로 이동합니다
- 모든 애니메이션은 300ms tween 애니메이션으로 부드럽게 전환됩니다
- 드래그 중에는 애니메이션이 비활성화되어 즉각적인 반응을 제공합니다
- `HongWidgetCommonBuilder` 인터페이스를 구현하여 공통 위젯 속성을 지원합니다
- 상단에 `HongHeaderCloseCompose`를 통한 닫기 버튼이 자동으로 추가됩니다
