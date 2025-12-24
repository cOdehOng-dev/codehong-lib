# HongTabScroll

수평 스크롤이 가능한 탭 선택 위젯입니다. 선택된 탭이 자동으로 중앙으로 이동하며, 탭 간 간격과 스타일을 세밀하게 커스터마이징할 수 있습니다.

## 📋 개요

HongTabScroll은 HorizontalScrollView 기반의 탭 네비게이션 위젯입니다. 탭을 선택하면 자동으로 해당 탭이 화면 중앙으로 스크롤되며, 선택/미선택 상태에 따라 배경색, 테두리, 텍스트 스타일이 자동으로 변경됩니다.

**주요 특징:**
- 수평 스크롤 가능한 탭 레이아웃
- 선택된 탭 자동 중앙 정렬
- 부드러운 스크롤 애니메이션
- 선택/미선택 상태에 따른 자동 스타일 변경
- 탭 간격 및 패딩 커스터마이징
- 탭 클릭 이벤트 콜백 지원

## 🏗️ 구조

```
tab/scroll/
├── HongTabScrollCompose.kt    # Jetpack Compose UI 구현
├── HongTabScrollView.kt        # Android View 래퍼 클래스
├── HongTabScrollOption.kt      # 설정 데이터 클래스
└── HongTabScrollBuilder.kt     # 빌더 패턴 구현
```

## 📦 주요 클래스

### HongTabScrollCompose

Jetpack Compose로 구현된 수평 스크롤 탭 레이아웃입니다.

**주요 기능:**
- `horizontalScroll()`로 수평 스크롤 지원
- `LaunchedEffect`로 선택된 탭 자동 중앙 정렬
- `animateScrollTo()`로 부드러운 스크롤 애니메이션
- `rememberScrollState()`로 스크롤 상태 관리
- 동적 패딩 계산 (마지막 탭은 오른쪽 패딩 제거)

```kotlin
@Composable
fun HongTabScrollCompose(
    option: HongTabScrollOption
)
```

**레이아웃 구조:**
```
┌────────────────────────────────┐
│  [탭1] [탭2] [탭3] [탭4] ...   │ ← 수평 스크롤
└────────────────────────────────┘
     ↑ 선택된 탭이 중앙으로 이동
```

### HongTabScrollView

Android View 시스템을 위한 래퍼 클래스입니다. `HorizontalScrollView`를 상속받아 구현되었습니다.

```kotlin
class HongTabScrollView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {
    fun set(option: HongTabScrollOption): HongTabScrollView
}
```

**특징:**
- `LinearLayout` 컨테이너로 탭 배치
- `smoothScrollTo()`로 자동 스크롤 구현
- `HonglibItemScrollTabBinding`으로 데이터 바인딩
- `scrollToSelectedTab()`로 중앙 정렬 로직

### HongTabScrollOption

위젯의 모든 설정을 포함하는 데이터 클래스입니다.

**주요 속성:**

| 속성 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `tabList` | `List<Any>` | `emptyList()` | 탭 데이터 객체 리스트 |
| `tabTextList` | `List<String>` | `emptyList()` | 탭 레이블 텍스트 리스트 |
| `selectTabTextTypo` | `HongTypo` | `BODY_16_B` | 선택된 탭 타이포그래피 (Bold) |
| `unselectTabTextTypo` | `HongTypo` | `BODY_16` | 미선택 탭 타이포그래피 |
| `selectTabTextColorHex` | `String` | `BLACK_100` | 선택된 탭 텍스트 색상 |
| `unselectTabTextColorHex` | `String` | `BLACK_100` | 미선택 탭 텍스트 색상 |
| `selectBackgroundColorHex` | `String` | `WHITE_100` | 선택된 탭 배경색 |
| `unselectBackgroundColorHex` | `String` | `WHITE_100` | 미선택 탭 배경색 |
| `selectBorderColorHex` | `String` | `BLACK_80` | 선택된 탭 테두리 색상 |
| `unselectBorderColorHex` | `String` | `GRAY_30` | 미선택 탭 테두리 색상 |
| `selectBorderWidth` | `Int` | `2` | 선택된 탭 테두리 두께 (px) |
| `unselectBorderWidth` | `Int` | `1` | 미선택 탭 테두리 두께 (px) |
| `tabBetweenPadding` | `Int` | `10` | 탭 간 간격 (dp) |
| `tabTextHorizontalPadding` | `Int` | `21` | 탭 내부 가로 패딩 (dp) |
| `tabTextVerticalPadding` | `Int` | `8` | 탭 내부 세로 패딩 (dp) |
| `tabRadius` | `HongRadiusInfo` | `18` (전체) | 탭 모서리 둥글기 |
| `initialSelectIndex` | `Int` | `0` | 초기 선택된 탭 인덱스 |
| `tabClick` | `((Int, Any) -> Unit)?` | `null` | 탭 클릭 시 콜백 |

### HongTabScrollBuilder

빌더 패턴을 사용하여 `HongTabScrollOption`을 쉽게 구성할 수 있습니다.

```kotlin
class HongTabScrollBuilder :
    HongWidgetCommonBuilder<HongTabScrollOption, HongTabScrollBuilder>
```

**주요 메서드:**

**선택된 탭 (Active Tab):**
- `selectTabTextTypo(HongTypo)` - 선택된 탭 타이포그래피
- `selectTabTextColor(HongColor/String)` - 선택된 탭 텍스트 색상
- `selectBackgroundColor(HongColor/String)` - 선택된 탭 배경색
- `selectBorderColor(HongColor/String)` - 선택된 탭 테두리 색상
- `selectBorderWidth(Int)` - 선택된 탭 테두리 두께

**미선택 탭 (Inactive Tab):**
- `unselectTabTextTypo(HongTypo)` - 미선택 탭 타이포그래피
- `unselectTabTextColor(HongColor/String)` - 미선택 탭 텍스트 색상
- `unselectBackgroundColor(HongColor/String)` - 미선택 탭 배경색
- `unselectBorderColor(HongColor/String)` - 미선택 탭 테두리 색상
- `unselectBorderWidth(Int)` - 미선택 탭 테두리 두께

**공통 설정:**
- `tabBetweenPadding(Int)` - 탭 간 간격
- `tabTextHorizontalPadding(Int)` - 탭 내부 가로 패딩
- `tabTextVerticalPadding(Int)` - 탭 내부 세로 패딩
- `radius(HongRadiusInfo)` - 탭 모서리 둥글기
- `initialSelectIndex(Int)` - 초기 선택 탭 인덱스
- `onTabClick((Int, Any) -> Unit)` - 탭 클릭 리스너
- `copy(HongTabScrollOption?)` - 옵션 복사

## 🚀 사용법

### Jetpack Compose

```kotlin
@Composable
fun CategoryScreen() {
    val categories = listOf("전체", "의류", "식품", "가전", "도서", "스포츠", "뷰티")
    var selectedCategory by remember { mutableStateOf(0) }

    HongTabScrollCompose(
        HongTabScrollBuilder()
            .tabList(categories)
            .tabTextList(categories)
            .initialSelectIndex(0)
            .tabBetweenPadding(12)
            .onTabClick { index, item ->
                selectedCategory = index
                Log.d("Category", "Selected: $item")
            }
            .applyOption()
    )
}
```

### Android View

```kotlin
val tabScrollView = HongTabScrollView(context)
    .set(
        HongTabScrollBuilder()
            .tabList(listOf("옵션1", "옵션2", "옵션3", "옵션4", "옵션5"))
            .tabTextList(listOf("옵션1", "옵션2", "옵션3", "옵션4", "옵션5"))
            .initialSelectIndex(0)
            .tabBetweenPadding(12)
            .onTabClick { index, item ->
                handleTabSelection(index, item)
            }
            .applyOption()
    )

parentLayout.addView(tabScrollView)
```

### 사용 예시: 커스텀 스타일

```kotlin
HongTabScrollCompose(
    HongTabScrollBuilder()
        .tabList(listOf("사과", "바나나", "오렌지", "포도", "딸기", "수박", "키위", "망고"))
        .tabTextList(listOf("사과", "바나나", "오렌지", "포도", "딸기", "수박", "키위", "망고"))
        .initialSelectIndex(0)
        .tabBetweenPadding(16)
        .tabTextHorizontalPadding(24)
        .tabTextVerticalPadding(10)
        .radius(HongRadiusInfo(24, 24, 24, 24))  // 더 둥글게
        .selectBackgroundColor(HongColor.MAIN_ORANGE_100)
        .unselectBackgroundColor(HongColor.GRAY_10)
        .selectBorderWidth(0)  // 테두리 없음
        .unselectBorderWidth(0)
        .selectTabTextColor(HongColor.WHITE_100)
        .unselectTabTextColor(HongColor.GRAY_60)
        .selectTabTextTypo(HongTypo.TITLE_18_B)
        .unselectTabTextTypo(HongTypo.BODY_16)
        .onTabClick { index, fruit ->
            viewModel.selectFruit(index)
        }
        .applyOption()
)
```

### 사용 예시: 데이터 객체와 함께 사용

```kotlin
data class Product(val id: String, val name: String)

val products = listOf(
    Product("1", "스마트폰"),
    Product("2", "노트북"),
    Product("3", "태블릿"),
    Product("4", "워치")
)

HongTabScrollCompose(
    HongTabScrollBuilder()
        .tabList(products)  // Product 객체 리스트
        .tabTextList(products.map { it.name })  // 표시할 텍스트
        .initialSelectIndex(0)
        .onTabClick { index, item ->
            val product = item as Product
            Log.d("Product", "Selected: ${product.name}")
            viewModel.selectProduct(product)
        }
        .applyOption()
)
```

## ⚙️ 주요 기능

### 1. 자동 중앙 정렬

- 탭 선택 시 자동으로 해당 탭이 화면 중앙으로 스크롤
- 부드러운 애니메이션 효과 적용
- `LaunchedEffect`와 `animateScrollTo()` 활용

### 2. 선택 상태 관리

- 선택된 탭의 인덱스를 내부 상태로 관리
- 화면 회전 시에도 선택 상태 유지
- 탭 클릭 시 자동으로 상태 업데이트 및 콜백 호출

### 3. 선택/미선택 시각적 구분

- **배경색**: 선택/미선택에 따라 다른 배경색 적용
- **테두리**: 선택된 탭은 더 굵은 테두리 (기본 2px), 미선택은 얇은 테두리 (기본 1px)
- **텍스트**: 선택된 탭은 볼드 타이포그래피 적용

### 4. 간격 제어

- **탭 간 간격**: `tabBetweenPadding`으로 설정
- **탭 내부 패딩**: `tabTextHorizontalPadding`, `tabTextVerticalPadding`으로 설정
- 마지막 탭은 오른쪽 패딩 자동 제거

### 5. 데이터 바인딩

- `tabList`로 실제 데이터 객체 전달
- `tabTextList`로 표시할 텍스트 분리
- 탭 클릭 시 원본 데이터 객체 반환

## 📝 참고사항

- **초기 선택**: `initialSelectIndex`로 초기 선택된 탭을 설정할 수 있습니다.
- **탭 높이**: 각 탭의 높이는 36dp로 고정되어 있습니다.
- **스크롤 지연**: 자동 중앙 정렬은 200ms 지연 후 실행됩니다 (애니메이션 안정성).
- **콜백**: `tabClick` 콜백은 선택된 탭의 인덱스(Int)와 원본 데이터 객체(Any)를 전달합니다.
- **간격 계산**: 마지막 탭은 오른쪽 패딩이 자동으로 제거되어 깔끔한 레이아웃을 유지합니다.
- **성능**: 많은 수의 탭도 효율적으로 렌더링되며, 스크롤 성능이 최적화되어 있습니다.

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
