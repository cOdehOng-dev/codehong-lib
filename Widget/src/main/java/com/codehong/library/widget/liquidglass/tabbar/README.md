# HongLiquidGlassTabBar

iOS 26 Liquid Glass 스타일의 탭 바 위젯

## 📋 개요

HongLiquidGlassTabBar는 Apple의 Liquid Glass 디자인을 Android에서 구현한 Jetpack Compose 탭 바 위젯입니다. 유리질감의 반투명 배경, 드래그 시 젤리처럼 늘어나는 인디케이터, 렌즈 확대 효과 등 풍부한 애니메이션을 제공합니다. Light/Dark 테마를 모두 지원하며, Builder 패턴을 통해 간편하게 설정할 수 있습니다.

## 🏗️ 구조

```
liquidglass/tabbar/
├── HongLiquidGlassTabBar.kt          # Compose 기반 탭 바 위젯
├── HongLiquidGlassTabBarBuilder.kt   # 탭 바 빌더 클래스
├── HongLiquidGlassTabBarOption.kt    # 탭 바 옵션 데이터 클래스
└── HongLiquidGlassTabItem.kt        # 탭 아이템 데이터 클래스
```

## 📦 주요 클래스

### HongLiquidGlassTabBar
Jetpack Compose 기반의 Liquid Glass 탭 바 위젯입니다. 주요 특징:
- **유리 질감 배경**: `liquidGlass` Modifier를 통해 반투명 글래스 효과 적용
- **젤리 인디케이터**: 드래그 시 인디케이터가 늘어나고 확대되는 물리 기반 애니메이션
- **렌즈 확대 효과**: 드래그 중 선택된 아이콘이 확대되는 렌즈 효과
- **탭/스와이프 지원**: 탭 클릭과 수평 드래그 제스처 모두 지원
- **Refraction Rim**: 인디케이터 가장자리에 굴절 효과 적용
- **Light/Dark 테마**: `isDarkTheme` 옵션으로 테마 자동 전환

### HongLiquidGlassTabBarBuilder
빌더 패턴을 사용하여 탭 바 옵션을 설정하는 클래스입니다. `HongWidgetCommonBuilder`를 구현하여 메서드 체이닝으로 설정할 수 있습니다.

### HongLiquidGlassTabBarOption
탭 바의 모든 설정값을 담고 있는 데이터 클래스입니다. `HongWidgetCommonOption`을 구현하여 공통 위젯 속성을 상속받습니다.

### HongLiquidGlassTabItem
각 탭 아이템을 나타내는 데이터 클래스입니다. 아이콘 리소스 ID와 라벨 텍스트로 구성됩니다.

## 🚀 사용법

### 기본 사용

```kotlin
HongLiquidGlassTabBar(
    HongLiquidGlassTabBarBuilder()
        .isDarkTheme(true)
        .tabList(
            listOf(
                HongLiquidGlassTabItem(R.drawable.ic_home, "Home"),
                HongLiquidGlassTabItem(R.drawable.ic_search, "Search"),
                HongLiquidGlassTabItem(R.drawable.ic_favorite, "Favorite"),
                HongLiquidGlassTabItem(R.drawable.ic_profile, "Profile")
            )
        )
        .onSelectedTab { index, item ->
            Log.d("TabBar", "Selected: $index, ${item.label}")
        }
        .applyOption()
)
```

### 커스텀 스타일 적용

```kotlin
HongLiquidGlassTabBar(
    HongLiquidGlassTabBarBuilder()
        .isDarkTheme(false)
        .tabList(tabItems)
        .outerRadius(30)
        .tabBarHeight(70)
        .tabVerticalPadding(10)
        .innerSideGap(12)
        .tabSelectTypo(HongTypo.CONTENTS_12_B)
        .tabDefTypo(HongTypo.CONTENTS_12)
        .onSelectedTab { index, item ->
            // 탭 선택 처리
        }
        .applyOption()
)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `tabList(List<HongLiquidGlassTabItem>)` | 탭 아이템 리스트 설정 |
| `isDarkTheme(Boolean)` | 다크 테마 여부 설정 (기본값: false) |
| `outerRadius(Int)` | 탭 바 외곽 라운드 설정 (최대 40dp, 기본값: 40) |
| `tabBarHeight(Int)` | 탭 바 높이 설정 (기본값: 80dp) |
| `tabVerticalPadding(Int)` | 인디케이터 상하 패딩 설정 (기본값: 12dp) |
| `innerSideGap(Int)` | 내부 좌우 간격 설정 (최대 16dp, 기본값: 16) |
| `tabSelectTypo(HongTypo)` | 선택된 탭 타이포그래피 설정 |
| `tabDefTypo(HongTypo)` | 미선택 탭 타이포그래피 설정 |
| `onSelectedTab((Int, HongLiquidGlassTabItem) -> Unit)` | 탭 선택 콜백 |

## 🎨 애니메이션 효과

| 효과 | 설명 |
|------|------|
| Tab Bar Scale | 드래그 시 탭 바가 1.05배 확대 |
| Jelly Effect | 인디케이터가 드래그 방향으로 늘어남 (높이 +18dp, 너비 +16dp) |
| Lens Effect | 드래그 중 가까운 아이콘이 최대 1.3배 확대 |
| Refraction Rim | 인디케이터 가장자리에 빛 굴절 효과 |
| Lens Highlight | 인디케이터 표면에 광택 효과 |
| Spring Animation | 드래그 종료 시 스프링 물리 기반 복귀 |

## 📝 참고사항

- **Compose 전용** 위젯으로, View 기반은 지원하지 않음
- `outerRadius`는 최대 40dp로 제한됨
- `innerSideGap`은 최대 16dp로 제한됨
- 탭 아이템의 `icon`은 Drawable 리소스 ID (Int)로 설정
- 아이콘 크기는 24x24dp로 고정
- 드래그 인디케이터는 `spring` 애니메이션으로 부드럽게 이동
- Light/Dark 테마에 따라 인디케이터 색상, 아이콘/텍스트 색상이 자동 변경
