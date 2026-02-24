# HongSwipeContainer

좌측 스와이프 시 액션 버튼이 나타나는 스와이프 컨테이너 위젯. 삭제, 보관 등 아이템에 대한 단방향 액션 UI에 최적화되어 있으며, 스프링 애니메이션과 풀스와이프를 지원합니다. (Compose 지원)

## 📋 개요

리스트 아이템을 왼쪽으로 스와이프하면 배경에 숨겨진 액션 버튼이 노출되는 컨테이너 컴포넌트입니다.
절반 이상 스와이프 시 버튼이 고정 노출되고, 컨테이너 너비의 70% 초과 또는 빠른 스와이프 시 화면 밖으로 사라지며 버튼 액션이 자동 실행됩니다.

## 🏗️ 구조

```
swipe/
├── HongSwipeContainer.kt          # Compose UI 컴포넌트
├── HongSwipeContainerOption.kt    # 옵션 데이터 클래스
└── HongSwipeContainerBuilder.kt   # 빌더 패턴 클래스
```

## 📦 주요 클래스

### HongSwipeContainer
`@Composable` 함수로, `HongSwipeContainerOption`을 받아 스와이프 인터랙션이 가능한 컨테이너를 렌더링합니다.
드래그 제스처, 스프링 애니메이션, 풀스와이프 감지를 포함합니다.

### HongSwipeContainerOption
스와이프 컨테이너의 모든 설정 값을 담는 데이터 클래스입니다.
버튼 색상, 텍스트, 타이포그래피, 클릭 콜백, 전경 콘텐츠를 설정할 수 있습니다.

### HongSwipeContainerBuilder
빌더 패턴으로 `HongSwipeContainerOption`을 구성하는 클래스입니다.

## 🚀 사용법

```kotlin
HongSwipeContainer(
    HongSwipeContainerBuilder()
        .buttonColor(HongColor.RED_100)
        .buttonText("삭제")
        .buttonTextColor(HongColor.WHITE_100)
        .buttonTextTypo(HongTypo.BODY_14_B)
        .onClickButton {
            // 버튼 클릭 또는 풀스와이프 완료 시 실행
        }
        .content {
            // 전경에 표시될 Composable 콘텐츠
            Text("스와이프 가능한 아이템")
        }
        .applyOption()
)
```

## ⚙️ 주요 메서드 (Builder)

| 메서드 | 설명 |
|--------|------|
| `buttonColor(String)` | 버튼 배경 색상 설정 (Hex 문자열) |
| `buttonColor(HongColor)` | 버튼 배경 색상 설정 (HongColor) |
| `buttonText(String)` | 버튼에 표시할 텍스트 설정 |
| `buttonTextColor(String)` | 버튼 텍스트 색상 설정 (Hex 문자열) |
| `buttonTextColor(HongColor)` | 버튼 텍스트 색상 설정 (HongColor) |
| `buttonTextTypo(HongTypo)` | 버튼 텍스트 타이포그래피 설정 |
| `onClickButton(() -> Unit)` | 버튼 클릭 또는 풀스와이프 완료 시 콜백 |
| `content(@Composable () -> Unit)` | 전경에 표시될 컴포저블 콘텐츠 설정 |
| `copy(HongSwipeContainerOption?)` | 기존 옵션을 복사하여 새 빌더 생성 |

## 📝 참고사항

- Compose 전용 컴포넌트입니다 (View 미지원)
- 스와이프 버튼 너비는 80dp로 고정됩니다
- 드래그 거리가 컨테이너 너비의 **70% 초과** 또는 **빠른 스와이프** (velocity < -1000) 시 풀스와이프로 처리되어 `onClickButton`이 자동 호출됩니다
- 85% 지점을 초과하면 rubber-band 효과가 적용되어 자연스러운 드래그 저항감을 제공합니다
- 버튼은 노출 영역의 중앙에 항상 위치합니다
