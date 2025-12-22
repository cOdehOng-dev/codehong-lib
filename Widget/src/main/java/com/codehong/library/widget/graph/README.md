# HongGraph

Android Jetpack Compose 기반의 커스터마이징 가능한 그래프 위젯 라이브러리

## 📋 개요

HongGraph는 라인 그래프와 바 그래프를 지원하는 Android 그래프 위젯입니다. Builder 패턴을 사용하여 간편하게 설정할 수 있으며, Jetpack Compose를 기반으로 구현되어 있습니다. 그래프 색상, 크기, 레이블 등 다양한 속성을 커스터마이징할 수 있습니다.

## 🏗️ 구조

```
graph/
├── HongGraphBuilder.kt          # 그래프 빌더 클래스
├── HongGraphOption.kt            # 그래프 옵션 데이터 클래스
├── line/
│   ├── HongGraphLineView.kt     # 라인 그래프 View
│   └── HongGraphLineCompose.kt  # 라인 그래프 Compose
└── bar/
    ├── HongGraphBarView.kt      # 바 그래프 View
    └── HongGraphBarCompose.kt   # 바 그래프 Compose
```

## 📦 주요 클래스

### HongGraphBuilder
빌더 패턴을 사용하여 그래프 옵션을 설정하는 클래스입니다. 메서드 체이닝을 통해 직관적으로 그래프를 구성할 수 있습니다.

### HongGraphOption
그래프의 모든 설정값을 담고 있는 데이터 클래스입니다. `HongWidgetCommonOption`을 구현하여 공통 위젯 속성을 상속받습니다.

### HongGraphLineView / HongGraphLineCompose
라인 그래프를 그리는 클래스입니다. Canvas를 사용하여 점들을 연결한 곡선 그래프를 그립니다.

### HongGraphBarView / HongGraphBarCompose
바 그래프를 그리는 클래스입니다. Canvas를 사용하여 각 데이터 포인트를 막대 형태로 표현합니다.

## 🚀 사용법

### 라인 그래프

```kotlin
// 그래프 데이터 준비
val graphPoints = listOf(
    GraphPoint(label = "1월", point = 50.0),
    GraphPoint(label = "2월", point = 75.0),
    GraphPoint(label = "3월", point = 60.0),
    GraphPoint(label = "4월", point = 90.0)
)

// 그래프 옵션 빌드
val graphOption = HongGraphBuilder()
    .graphHeight(200)
    .graphPointList(graphPoints)
    .graphColor(HongColor.MAIN_ORANGE_100)
    .graphLineWidth(7)
    .dotLineColor(HongColor.GRAY_30)
    .dividerColor(HongColor.GRAY_20)
    .labelColor(HongColor.BLACK_100)
    .labelTypo(HongTypo.CONTENTS_10)
    .padding(HongSpacingInfo(left = 20f, right = 20f, top = 20f))
    .applyOption()

// View로 사용
val graphView = HongGraphLineView(context).set(graphOption)

// 또는 Compose로 사용
HongGraphLineCompose(graphOption)
```

### 바 그래프

```kotlin
val graphOption = HongGraphBuilder()
    .graphHeight(200)
    .graphPointList(graphPoints)
    .graphColor(HongColor.MAIN_ORANGE_100)
    .applyOption()

// View로 사용
val graphView = HongGraphBarView(context).set(graphOption)

// 또는 Compose로 사용
HongGraphBarCompose(graphOption)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `graphHeight(Int)` | 그래프의 높이 설정 (기본값: 198) |
| `graphPointList(List<GraphPoint>)` | 그래프에 표시할 데이터 포인트 리스트 설정 |
| `graphColor(HongColor/String)` | 그래프 선 또는 바의 색상 설정 |
| `graphLineWidth(Int)` | 그래프 선의 두께 설정 (기본값: 7) |
| `dotLineColor(HongColor/String)` | 격자선 색상 설정 |
| `dotLineWidth(Float)` | 격자선 두께 설정 (기본값: 1.5f) |
| `dividerColor(HongColor/String)` | 하단 구분선 색상 설정 |
| `dividerWidth(Int)` | 하단 구분선 두께 설정 (기본값: 1) |
| `labelColor(HongColor/String)` | 레이블 텍스트 색상 설정 |
| `labelTypo(HongTypo)` | 레이블 타이포그래피 설정 |
| `copy(HongGraphOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 📝 참고사항

- `graphPointList`가 비어있으면 그래프가 표시되지 않습니다
- 라인 그래프는 부드러운 곡선을 위해 `PathEffect.cornerPathEffect(40f)`를 사용합니다
- 바 그래프의 막대 너비는 자동으로 계산되어 균등하게 배치됩니다
- 격자선은 4개의 수평 점선으로 표시됩니다
- 모든 그래프는 상단 24dp, 좌우 24dp의 내부 여백을 가집니다
- `HongWidgetCommonOption`을 구현하여 border, radius, shadow 등의 공통 위젯 속성을 사용할 수 있습니다
- Canvas를 사용한 커스텀 렌더링으로 높은 성능과 유연성을 제공합니다
