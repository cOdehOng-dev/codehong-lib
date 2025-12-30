# HongScrollFadeLayout

스크롤 시 헤더가 페이드 인/아웃되는 Jetpack Compose 레이아웃 컴포넌트입니다. 스크롤 방향과 위치를 감지하여 자연스러운 헤더 전환 효과를 제공합니다.

## 📋 개요

HongScrollFadeLayout은 스크롤에 반응하는 동적 헤더를 제공하는 레이아웃 컴포넌트입니다. 메인 콘텐츠가 화면에서 사라질 때 헤더의 배경색과 아이콘/텍스트 색상이 부드럽게 전환되며, 스크롤 방향을 감지하여 사용자 경험을 최적화합니다.

## 🏗️ 구조

```
fade/
├── HongScrollFadeLayoutOption.kt      # 레이아웃 옵션 데이터 클래스
├── HongScrollFadeLayoutBuilder.kt     # 빌더 패턴 클래스
└── HongScrollFadeLayoutCompose.kt     # Compose UI 구현
```

## 📦 주요 클래스

### HongScrollFadeLayoutOption
레이아웃의 모든 설정을 담는 데이터 클래스입니다.

**주요 속성:**
- `mainContentHeightDp`: 메인 콘텐츠 높이 (기본값: 446dp)
- `mainContent`: 메인 콘텐츠 Composable
- `subContentList`: 스크롤 가능한 서브 콘텐츠 리스트
- `bottomContent`: 하단 고정 콘텐츠
- `titleText`, `titleTypo`, `titleColorHex`: 헤더 타이틀 설정
- `leftIconInfo`, `rightIconInfo`: 좌/우 아이콘 설정
- `headerBackgroundColorHex`: 헤더 배경색

### HongScrollFadeLayoutBuilder
빌더 패턴을 사용하여 옵션을 구성하는 클래스입니다.

**주요 메서드:**
- `mainContentHeightDp(Int)`: 메인 콘텐츠 높이 설정
- `mainContent(Composable)`: 메인 콘텐츠 설정
- `subContentList(LazyListScope)`: 서브 콘텐츠 리스트 설정
- `bottomContent(Composable)`: 하단 콘텐츠 설정
- `titleText(String)`: 타이틀 텍스트 설정
- `leftIconInfo(Any)`, `rightIconInfo(Any)`: 좌/우 아이콘 설정
- `headerBackgroundColor(HongColor)`: 헤더 배경색 설정
- `copy(HongScrollFadeLayoutOption)`: 옵션 복사

### HongScrollFadeLayoutCompose
실제 UI를 렌더링하는 Composable 함수입니다.

**핵심 기능:**
- 스크롤 상태 감지 및 오프셋 계산
- 스크롤 방향 감지 (상/하)
- 알파 애니메이션 (500ms tween)
- 동적 색상 전환 (투명 ↔ 불투명)

## 🚀 사용법

```kotlin
HongScrollFadeLayoutCompose(
    option = HongScrollFadeLayoutBuilder()
        .mainContentHeightDp(500)
        .mainContent {
            // 상단 메인 콘텐츠
            Text("메인 콘텐츠", fontSize = 32.sp)
        }
        .subContentList { lazyListScope ->
            items(20) { index ->
                Text("아이템 $index")
            }
        }
        .bottomContent {
            // 하단 고정 콘텐츠
            Button(onClick = {}) { Text("버튼") }
        }
        .titleText("페이지 제목")
        .titleTypo(HongTypo.TITLE_26_B)
        .leftIconInfo(R.drawable.ic_back)
        .leftIconClick { /* 뒤로가기 */ }
        .rightIconInfo(R.drawable.ic_menu)
        .rightIconClick { /* 메뉴 */ }
        .headerBackgroundColor(HongColor.WHITE_100)
        .applyOption()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `mainContentHeightDp(Int)` | 메인 콘텐츠의 높이를 dp 단위로 설정 |
| `mainContent(Composable)` | 상단 메인 콘텐츠 영역 설정 |
| `subContentList(LazyListScope)` | 스크롤 가능한 서브 콘텐츠 리스트 설정 |
| `bottomContent(Composable)` | 하단 고정 콘텐츠 영역 설정 |
| `titleText(String)` | 헤더 타이틀 텍스트 설정 |
| `titleTypo(HongTypo)` | 헤더 타이틀 타이포그래피 설정 |
| `titleColor(Pair<HongColor, HongColor>)` | 타이틀 색상 설정 (투명 상태 / 불투명 상태) |
| `useTitleOverFlow(Boolean)` | 타이틀 오버플로우 처리 여부 |
| `leftIconInfo(Any)` | 좌측 아이콘 리소스 설정 |
| `leftIconColor(Pair<HongColor, HongColor>)` | 좌측 아이콘 색상 설정 |
| `leftIconClick(() -> Unit)` | 좌측 아이콘 클릭 리스너 |
| `rightIconInfo(Any)` | 우측 아이콘 리소스 설정 |
| `rightIconColor(Pair<HongColor, HongColor>)` | 우측 아이콘 색상 설정 |
| `rightIconClick(() -> Unit)` | 우측 아이콘 클릭 리스너 |
| `headerBackgroundColor(HongColor)` | 헤더 배경색 설정 |

## 🎨 애니메이션 동작 원리

1. **스크롤 감지**: `LazyListState`를 통해 메인 콘텐츠의 오프셋 추적
2. **방향 감지**: 이전 오프셋과 현재 오프셋을 비교하여 스크롤 방향 판단
3. **알파 계산**:
   - 메인 콘텐츠 완전히 사라짐 → 알파 1.0 (헤더 불투명)
   - 메인 콘텐츠 70% 이상 사라짐 → 스크롤 방향에 따라 전환
   - 메인 콘텐츠 표시 중 → 알파 0.0 (헤더 투명)
4. **애니메이션**: `animateFloatAsState`로 500ms tween 애니메이션 적용

## 📝 참고사항

- 색상은 `Pair<String, String>` 형태로 설정 (투명 상태 색상, 불투명 상태 색상)
- 기본 메인 콘텐츠 높이는 446dp
- 스크롤 감지는 메인 콘텐츠 높이의 50%를 기준으로 동작
- 10px 미만의 미세한 스크롤 움직임은 무시하여 불필요한 애니메이션 방지
- 헤더 높이는 고정 50dp
- `HongWidgetCommonOption`을 상속하여 공통 위젯 기능 사용 가능
