# HongTabSegment

세그먼트 컨트롤 방식의 탭 선택 위젯입니다.

## 📋 개요

HongTabSegment는 iOS의 UISegmentedControl과 유사한 세그먼트 컨트롤 위젯입니다. 여러 옵션 중 하나를 선택할 수 있는 UI를 제공하며, 선택된 탭을 강조하는 슬라이딩 인디케이터 애니메이션이 특징입니다. 필터링, 카테고리 선택, 뷰 모드 전환 등 2~5개의 옵션 중 하나를 선택해야 하는 상황에 적합합니다. 빌더 패턴을 통해 인디케이터 색상, 탭 크기, 텍스트 스타일을 쉽게 커스터마이징할 수 있으며, Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
tab/segment/
├── HongTabSegmentBuilder.kt       # 빌더 패턴을 사용한 설정 클래스
├── HongTabSegmentOption.kt        # 옵션 데이터 클래스
├── HongTabSegmentCompose.kt       # Compose용 컴포넌트
└── HongTabSegmentView.kt          # View 시스템용 래퍼 클래스
```

## 📦 주요 클래스

### HongTabSegmentBuilder
빌더 패턴을 사용하여 세그먼트 탭의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 둥근 모서리 설정 (`radius`)
- 탭 텍스트 목록 설정 (`tabTextList`)
- 초기 선택 인덱스 설정 (`initialSelectIndex`)
- 인디케이터 색상 설정 (`indicatorColor`)
- 선택/미선택 텍스트 색상 설정 (`selectTextColor`, `unselectTabTextColor`)
- 탭 크기 설정 (`tabWidth`, `tabHeight`)
- 선택/미선택 타이포그래피 설정 (`selectTypo`, `unselectTypo`)
- 탭 클릭 콜백 설정 (`onTabClick`)
- 옵션 복사 (`copy`)

### HongTabSegmentOption
세그먼트 탭의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `tabTextList`: 탭 텍스트 목록 (기본값: 빈 리스트)
- `initialSelectIndex`: 초기 선택된 탭 인덱스 (기본값: `0`)
- `indicatorColorHex`: 인디케이터(선택된 탭 배경) 색상 (기본값: `HongColor.WHITE_100`)
- `selectTextColorHex`: 선택된 탭 텍스트 색상 (기본값: `HongColor.BLACK_100`)
- `unselectTabColorHex`: 미선택 탭 텍스트 색상 (기본값: `HongColor.GRAY_50`)
- `selectTypo`: 선택된 탭 타이포그래피 (기본값: `HongTypo.BODY_15_B`)
- `unselectTypo`: 미선택 탭 타이포그래피 (기본값: `HongTypo.BODY_15`)
- `tabWidth`: 각 탭의 너비 (기본값: `100`)
- `tabHeight`: 각 탭의 높이 (기본값: `40`)
- `tabClick`: 탭 클릭 시 호출되는 콜백 (인덱스 전달)

**기본 스타일:**
- 전체 너비: MATCH_PARENT
- 전체 높이: WRAP_CONTENT
- 외부 여백: 16dp (모든 방향)
- 내부 여백: 4dp (모든 방향)
- 둥근 모서리: 24dp (모든 모서리)
- 배경색: 투명 (`HongColor.TRANSPARENT`)
- 인디케이터 색상: 흰색 (`HongColor.WHITE_100`)
- 선택된 탭 텍스트: 검은색, 굵은 글씨 (BODY_15_B)
- 미선택 탭 텍스트: 회색, 일반 글씨 (BODY_15)

### HongTabSegmentCompose
Jetpack Compose에서 사용할 수 있는 세그먼트 탭 컴포넌트입니다.

**구조:**
- Box (전체 컨테이너, 중앙 정렬)
  - Box (배경 + 패딩)
    - Box (슬라이딩 인디케이터, 애니메이션)
    - Row (탭 목록)
      - Box (각 탭, 클릭 가능)
        - HongTextCompose (탭 텍스트)

**애니메이션:**
- 인디케이터 슬라이딩: 300ms, `animateDpAsState`
- 오프셋 계산: `(selectedIndex * tabWidth).dp`

**주요 기능:**
- 탭 클릭 시 인디케이터가 부드럽게 이동
- 선택된 탭은 굵은 글씨 + 어두운 색상
- 미선택 탭은 일반 글씨 + 밝은 색상
- 리플 효과 없는 클릭 (`disableRippleClickable`)

### HongTabSegmentView
기존 View 시스템에서 사용할 수 있는 래퍼 클래스입니다.

**구조:**
- 내부적으로 `ComposeView`를 생성하여 반환
- `HongTabSegmentCompose`를 호스팅

**주요 기능:**
- View 시스템과 Compose 간의 브릿지 역할
- `set()` 메서드로 옵션을 적용하여 ComposeView 반환

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun FilterScreen() {
    var selectedFilter by remember { mutableIntStateOf(0) }

    val option = HongTabSegmentBuilder()
        .margin(
            HongSpacingInfo(
                left = 16f,
                top = 16f,
                right = 16f,
                bottom = 16f
            )
        )
        .padding(
            HongSpacingInfo(
                left = 4f,
                top = 4f,
                right = 4f,
                bottom = 4f
            )
        )
        .radius(
            HongRadiusInfo(
                topLeft = 24,
                topRight = 24,
                bottomLeft = 24,
                bottomRight = 24
            )
        )
        .backgroundColor(HongColor.GRAY_10)
        .tabTextList(listOf("전체", "진행중", "완료"))
        .initialSelectIndex(selectedFilter)
        .indicatorColor(HongColor.WHITE_100)
        .selectTextColor(HongColor.BLACK_100)
        .unselectTabTextColor(HongColor.GRAY_50)
        .selectTypo(HongTypo.BODY_15_B)
        .unselectTypo(HongTypo.BODY_15)
        .tabWidth(100)
        .tabHeight(40)
        .onTabClick { index ->
            selectedFilter = index
            // 필터 변경 처리
        }
        .applyOption()

    HongTabSegmentCompose(option)
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<FrameLayout
    android:id="@+id/segmentContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
class MainActivity : AppCompatActivity() {
    private var selectedCategory = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<FrameLayout>(R.id.segmentContainer)

        val option = HongTabSegmentBuilder()
            .margin(
                HongSpacingInfo(
                    left = 16f,
                    top = 16f,
                    right = 16f,
                    bottom = 16f
                )
            )
            .padding(
                HongSpacingInfo(
                    left = 4f,
                    top = 4f,
                    right = 4f,
                    bottom = 4f
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 24,
                    topRight = 24,
                    bottomLeft = 24,
                    bottomRight = 24
                )
            )
            .backgroundColor(HongColor.GRAY_10)
            .tabTextList(listOf("추천", "계좌", "연락처"))
            .initialSelectIndex(selectedCategory)
            .onTabClick { index ->
                selectedCategory = index
                // 카테고리 변경 처리
            }
            .applyOption()

        val segmentView = HongTabSegmentView(this).set(option)
        container.addView(segmentView)
    }
}
```

### 2개 탭 사용 (토글 스위치 스타일)

```kotlin
val option = HongTabSegmentBuilder()
    .backgroundColor(HongColor.GRAY_10)
    .tabTextList(listOf("리스트", "그리드"))
    .initialSelectIndex(0)
    .indicatorColor(HongColor.WHITE_100)
    .tabWidth(120)
    .tabHeight(44)
    .onTabClick { index ->
        when (index) {
            0 -> showListView()
            1 -> showGridView()
        }
    }
    .applyOption()
```

### 커스텀 색상 및 스타일

```kotlin
val option = HongTabSegmentBuilder()
    .backgroundColor(HongColor.BLUE_10)
    .radius(
        HongRadiusInfo(
            topLeft = 16,
            topRight = 16,
            bottomLeft = 16,
            bottomRight = 16
        )
    )
    .tabTextList(listOf("일간", "주간", "월간"))
    .initialSelectIndex(0)
    .indicatorColor(HongColor.BLUE_100)
    .selectTextColor(HongColor.WHITE_100)
    .unselectTabTextColor(HongColor.BLUE_60)
    .selectTypo(HongTypo.BODY_14_B)
    .unselectTypo(HongTypo.BODY_14)
    .tabWidth(90)
    .tabHeight(36)
    .onTabClick { index ->
        // 통계 기간 변경
    }
    .applyOption()
```

### 다양한 탭 개수

```kotlin
// 4개 탭
val option4 = HongTabSegmentBuilder()
    .tabTextList(listOf("홈", "검색", "알림", "프로필"))
    .tabWidth(80)
    .applyOption()

// 5개 탭
val option5 = HongTabSegmentBuilder()
    .tabTextList(listOf("월", "화", "수", "목", "금"))
    .tabWidth(70)
    .applyOption()
```

### 상태 관리 (Compose)

```kotlin
@Composable
fun ContentViewModeSelector() {
    var viewMode by remember { mutableIntStateOf(0) }
    val viewModeLabels = listOf("카드", "리스트", "그리드")

    Column {
        HongTabSegmentCompose(
            HongTabSegmentBuilder()
                .backgroundColor(HongColor.GRAY_10)
                .tabTextList(viewModeLabels)
                .initialSelectIndex(viewMode)
                .onTabClick { index ->
                    viewMode = index
                }
                .applyOption()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (viewMode) {
            0 -> CardView()
            1 -> ListView()
            2 -> GridView()
        }
    }
}
```

## ⚙️ 주요 메서드

### HongTabSegmentBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `radius(radiusInfo)` | `HongRadiusInfo` | 둥근 모서리 설정 |
| `tabTextList(list)` | `List<String>` | 탭 텍스트 목록 설정 |
| `initialSelectIndex(index)` | `Int` | 초기 선택된 탭 인덱스 (기본값: `0`) |
| `indicatorColor(color)` | `HongColor` 또는 `String` | 인디케이터 색상 설정 |
| `selectTextColor(color)` | `HongColor` 또는 `String` | 선택된 탭 텍스트 색상 설정 |
| `unselectTabTextColor(color)` | `HongColor` 또는 `String` | 미선택 탭 텍스트 색상 설정 |
| `tabWidth(width)` | `Int` | 각 탭의 너비 (dp) (기본값: `100`) |
| `tabHeight(height)` | `Int` | 각 탭의 높이 (dp) (기본값: `40`) |
| `selectTypo(typo)` | `HongTypo` | 선택된 탭 타이포그래피 설정 |
| `unselectTypo(typo)` | `HongTypo` | 미선택 탭 타이포그래피 설정 |
| `onTabClick(callback)` | `((Int) -> Unit)?` | 탭 클릭 콜백 설정 (인덱스 전달) |
| `copy(inject)` | `HongTabSegmentOption?` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongTabSegmentView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongTabSegmentOption` | `ComposeView` | 옵션을 적용하여 ComposeView 생성 및 반환 |

### HongTabSegmentCompose

| 파라미터 | 타입 | 설명 |
|---------|------|------|
| `option` | `HongTabSegmentOption` | 세그먼트 탭 옵션 |

## 📝 참고사항

### 레이아웃 구조
- **전체 크기:** MATCH_PARENT × WRAP_CONTENT (기본값)
- **배치:** 수평 방향 (Row)
- **정렬:** 중앙 정렬
- **인디케이터:** 선택된 탭 위치로 슬라이딩 애니메이션
- **탭 크기:** 고정 크기 (tabWidth × tabHeight)

### 애니메이션
- **인디케이터 슬라이딩:** 300ms
- **애니메이션 타입:** `animateDpAsState` with `tween`
- **오프셋 계산:** `(selectedIndex * tabWidth).dp`
- **리플 효과:** 비활성화 (`disableRippleClickable`)

### 기본값
- 전체 너비: `HongLayoutParam.MATCH_PARENT`
- 전체 높이: `HongLayoutParam.WRAP_CONTENT`
- 외부 여백: 16dp (모든 방향)
- 내부 여백: 4dp (모든 방향)
- 둥근 모서리: 24dp (모든 모서리)
- 배경색: `HongColor.TRANSPARENT` (투명)
- 인디케이터 색상: `HongColor.WHITE_100` (흰색)
- 선택된 탭 텍스트 색상: `HongColor.BLACK_100` (검은색)
- 미선택 탭 텍스트 색상: `HongColor.GRAY_50` (회색)
- 선택된 탭 타이포그래피: `HongTypo.BODY_15_B` (굵은 글씨)
- 미선택 탭 타이포그래피: `HongTypo.BODY_15` (일반 글씨)
- 탭 너비: 100dp
- 탭 높이: 40dp
- 초기 선택 인덱스: 0

### 주의사항
- `tabTextList`가 빈 리스트인 경우: 아무것도 표시되지 않습니다 (early return)
- 탭 개수에 따라 `tabWidth`를 조정해야 합니다 (전체 너비를 고려)
- `initialSelectIndex`는 `tabTextList`의 유효한 인덱스여야 합니다
- Compose에서는 상태 관리를 별도로 구현해야 합니다 (`remember`, `mutableIntStateOf` 등)
- View 시스템에서는 `ComposeView`를 반환하므로 Compose 런타임이 필요합니다
- 탭 클릭 시 `onTabClick` 콜백이 호출되며, 선택된 인덱스가 전달됩니다
- 인디케이터는 고정 너비(`tabWidth`)로 이동하므로, 모든 탭의 너비가 동일해야 합니다
- 탭 텍스트가 너무 길면 잘릴 수 있으므로, 적절한 `tabWidth`를 설정하거나 짧은 텍스트를 사용하세요
- `initialSelectIndex`는 초기 렌더링 시에만 적용되며, 이후 상태 변경은 외부에서 관리해야 합니다

### 의존성
- HongText (텍스트 위젯)
- HongColor (디자인 시스템 색상)
- HongTypo (디자인 시스템 타이포그래피)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)
- Jetpack Compose Animation (`animateDpAsState`)

### 사용 사례
- 필터 선택 (전체/진행중/완료)
- 뷰 모드 전환 (리스트/그리드)
- 카테고리 선택 (추천/계좌/연락처)
- 기간 선택 (일간/주간/월간)
- 정렬 방식 (최신순/인기순/추천순)
- 탭 네비게이션 (대안적 탭 UI)
- 요일 선택 (월/화/수/목/금)
- 성별 선택 (남성/여성)
- 배송 방법 선택 (일반/빠른/새벽)

### 탭 개수별 권장 너비

```kotlin
// 2개 탭 (토글 스위치 스타일)
.tabWidth(120) // 전체: 240dp + padding

// 3개 탭
.tabWidth(100) // 전체: 300dp + padding

// 4개 탭
.tabWidth(80) // 전체: 320dp + padding

// 5개 탭
.tabWidth(70) // 전체: 350dp + padding
```

### 상태 동기화 (Compose)

```kotlin
@Composable
fun StateSyncExample() {
    var currentTab by remember { mutableIntStateOf(0) }

    // currentTab 변경 시 옵션 재생성
    val option = remember(currentTab) {
        HongTabSegmentBuilder()
            .tabTextList(listOf("탭1", "탭2", "탭3"))
            .initialSelectIndex(currentTab)
            .onTabClick { index ->
                currentTab = index
            }
            .applyOption()
    }

    HongTabSegmentCompose(option)
}
```

### 동적 탭 목록

```kotlin
@Composable
fun DynamicTabsExample() {
    val categories = listOf("전자제품", "의류", "도서", "식품")
    var selectedIndex by remember { mutableIntStateOf(0) }

    val option = HongTabSegmentBuilder()
        .backgroundColor(HongColor.GRAY_10)
        .tabTextList(categories)
        .initialSelectIndex(selectedIndex)
        .tabWidth(90)
        .onTabClick { index ->
            selectedIndex = index
        }
        .applyOption()

    HongTabSegmentCompose(option)
}
```

### 비교: HongTabSegment vs 일반 탭

| 특징 | HongTabSegment | 일반 탭 (TabLayout) |
|------|----------------|---------------------|
| **UI 스타일** | 세그먼트 컨트롤 (iOS 스타일) | 머티리얼 디자인 탭 |
| **인디케이터** | 슬라이딩 배경 박스 | 하단 밑줄 |
| **탭 개수** | 2~5개 권장 | 무제한 (스크롤 가능) |
| **사용 사례** | 필터, 뷰 모드 전환 | 페이지 네비게이션 |
| **애니메이션** | 슬라이딩 박스 (300ms) | 밑줄 이동 |
| **배경** | 둥근 모서리 박스 | 투명 또는 단색 |

HongTabSegment는 적은 수의 옵션 중 하나를 선택하는 데 적합하며, 일반 탭은 여러 페이지 간 네비게이션에 적합합니다.

### 접근성 고려사항

```kotlin
// 명확한 라벨 사용
.tabTextList(listOf("전체 보기", "진행 중", "완료됨"))

// 충분한 탭 크기 (최소 44dp 권장)
.tabHeight(44)

// 충분한 색상 대비
.selectTextColor(HongColor.BLACK_100)
.unselectTabTextColor(HongColor.GRAY_60)
```

### 인디케이터 커스터마이징

```kotlin
val option = HongTabSegmentBuilder()
    .backgroundColor(HongColor.MAIN_ORANGE_10)
    .radius(HongRadiusInfo(20, 20, 20, 20))
    .indicatorColor(HongColor.MAIN_ORANGE_100)
    .selectTextColor(HongColor.WHITE_100)
    .unselectTabTextColor(HongColor.MAIN_ORANGE_60)
    .tabTextList(listOf("옵션1", "옵션2", "옵션3"))
    .applyOption()
```

### 성능 최적화

```kotlin
@Composable
fun OptimizedTabSegment() {
    val tabList = remember { listOf("탭1", "탭2", "탭3") }
    var selected by remember { mutableIntStateOf(0) }

    // 옵션을 remember로 캐싱 (tabList와 selected 변경 시에만 재생성)
    val option = remember(tabList, selected) {
        HongTabSegmentBuilder()
            .tabTextList(tabList)
            .initialSelectIndex(selected)
            .onTabClick { index -> selected = index }
            .applyOption()
    }

    HongTabSegmentCompose(option)
}
```
