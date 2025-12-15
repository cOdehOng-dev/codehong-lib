# HorizontalPager

안드로이드 및 Jetpack Compose에서 사용할 수 있는 수평 페이저(Horizontal Pager) 위젯 라이브러리입니다. View 시스템과 Compose 모두를 지원하며, 자동 스크롤, 무한 스크롤, 페이지 미리보기 등 다양한 기능을 제공합니다.

## 📋 개요

HongHorizontalPager는 ViewPager2와 Jetpack Compose HorizontalPager를 기반으로 한 커스텀 페이저 위젯입니다. Builder 패턴을 통해 쉽게 설정할 수 있으며, 다양한 UI 옵션과 동작 방식을 지원합니다.

**주요 기능:**
- ✅ View 시스템 및 Compose 지원
- ✅ 자동 스크롤 (설정 가능한 간격)
- ✅ 무한 스크롤 (롤백 옵션 포함)
- ✅ 이전/다음 페이지 미리보기
- ✅ 페이지 간격 조정
- ✅ 커스터마이징 가능한 레이아웃 (width, height, margin, padding)
- ✅ 배경색, 테두리, 그림자, 둥근 모서리 설정

## 🏗️ 구조

```
pager/
├── HongHorizontalPagerBuilder.kt       # Builder 패턴으로 옵션 구성
├── HongHorizontalPagerOption.kt        # 페이저 설정 옵션 데이터 클래스
├── HongHorizontalPagerCompose.kt       # Jetpack Compose 구현체
├── HongHorizontalPagerView.kt          # Android View 구현체
└── adapter/
    └── HongHorizontalPagerAdapter.kt   # ViewPager2용 RecyclerView Adapter
```

## 📦 주요 클래스

### HongHorizontalPagerBuilder
Builder 패턴을 사용하여 `HongHorizontalPagerOption`을 구성하는 클래스입니다.

**주요 메서드:**
- `width(Int)` / `height(Int)`: 페이저 크기 설정
- `margin(HongSpacingInfo)`: 외부 여백 설정
- `verticalPadding(Float, Float)`: 상/하 내부 여백 설정
- `backgroundColor(String)`: 배경색 설정 (HEX)
- `pageInfoList(List<Any>)`: 페이지 데이터 리스트 설정
- `pageSpacing(Int)`: 페이지 간격 설정
- `pageVisibleWidth(Float, Float)`: 이전/다음 페이지 보이는 너비 설정
- `autoScrollMillSecond(Long)`: 자동 스크롤 간격 설정 (밀리초)
- `initialLoadPageSize(Int)`: 초기 로드 페이지 수 설정
- `infiniteScroll(Boolean, Boolean)`: 무한 스크롤 및 첫 페이지 롤백 여부 설정
- `currentIndex((Int) -> Unit)`: 현재 페이지 인덱스 콜백 설정

### HongHorizontalPagerOption
HorizontalPager의 모든 설정을 담는 데이터 클래스입니다. `HongWidgetCommonOption` 인터페이스를 구현하여 공통 위젯 속성을 상속받습니다.

**주요 속성:**
- `width` / `height`: 레이아웃 크기
- `margin` / `padding`: 여백 설정
- `backgroundColorHex`: 배경색 (HEX)
- `radius`: 둥근 모서리 정보
- `shadow`: 그림자 정보
- `border`: 테두리 정보
- `pageSpacing`: 페이지 간 간격
- `pageInfoList`: 페이지 데이터 리스트
- `prevPageVisibleWidth` / `nextPageVisibleWidth`: 양옆 페이지 미리보기 너비
- `autoScrollMillSecond`: 자동 스크롤 간격
- `initialLoadPageSize`: 초기 로드 페이지 수
- `infiniteScroll`: 무한 스크롤 설정 (무한 스크롤 여부, 첫 페이지 롤백 여부)
- `currentIndex`: 현재 페이지 인덱스 콜백

### HongHorizontalPagerCompose
Jetpack Compose를 사용한 HorizontalPager 구현체입니다.

**기능:**
- `@Composable` 함수로 선언적 UI 구성
- 자동 스크롤 지원 (LaunchedEffect 사용)
- 무한 스크롤 지원 (리스트 10배 확장)
- 페이지 상태 추적 및 콜백 처리

### HongHorizontalPagerView
Android View 시스템 기반의 HorizontalPager 구현체입니다. ViewPager2를 내부적으로 사용합니다.

**기능:**
- `FrameLayout`을 상속받아 View로 사용 가능
- `ViewPager2` 기반 페이징
- `Handler`를 사용한 자동 스크롤
- `RecyclerView` padding을 통한 양옆 미리보기
- `MarginPageTransformer`를 통한 페이지 간격 조정

### HongHorizontalPagerAdapter
ViewPager2에서 사용하는 RecyclerView Adapter입니다.

**기능:**
- 페이지 데이터 리스트 관리
- 커스텀 바인더 함수 지원
- ViewBinding 기반 UI 구성

## 🚀 사용법

### 1. Jetpack Compose 사용 예시

```kotlin
@Composable
fun MyScreen() {
    val option = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(200)
        .pageInfoList(listOf("Page 1", "Page 2", "Page 3"))
        .pageSpacing(16)
        .pageVisibleWidth(prev = 32f, next = 32f)
        .autoScrollMillSecond(3000)
        .infiniteScroll(on = true, isRollbackFirst = false)
        .currentIndex { index ->
            println("Current page: $index")
        }
        .applyOption()

    HongHorizontalPagerCompose(option = option) { pageData ->
        Text(
            text = pageData.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
```

### 2. Android View 사용 예시

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val option = HongHorizontalPagerBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(200)
            .pageInfoList(listOf("Page 1", "Page 2", "Page 3"))
            .pageSpacing(16)
            .pageVisibleWidth(prev = 32f, next = 32f)
            .autoScrollMillSecond(3000)
            .infiniteScroll(on = true, isRollbackFirst = false)
            .currentIndex { index ->
                Log.d("Pager", "Current page: $index")
            }
            .applyOption()

        val pagerView = HongHorizontalPagerView(this)
        pagerView.set(option) { binding, data ->
            binding.textView.text = data.toString()
        }

        // Activity 종료 시 자동 스크롤 정지
        // pagerView.stopAutoScroll()
    }
}
```

### 3. Builder 패턴 활용

```kotlin
val baseOption = HongHorizontalPagerBuilder()
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(200)
    .pageSpacing(16)
    .applyOption()

// 기존 옵션을 복사하여 수정
val customOption = HongHorizontalPagerBuilder()
    .copy(baseOption)
    .pageInfoList(myPageList)
    .autoScrollMillSecond(5000)
    .applyOption()
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `width(Int)` | 페이저 너비 설정 |
| `height(Int)` | 페이저 높이 설정 |
| `margin(HongSpacingInfo)` | 외부 여백 설정 |
| `verticalPadding(Float, Float)` | 상/하 내부 여백 설정 |
| `backgroundColor(String)` | 배경색 설정 (HEX 코드) |
| `pageInfoList(List<Any>)` | 페이지 데이터 리스트 설정 |
| `pageSpacing(Int)` | 페이지 간 간격 설정 (dp) |
| `pageVisibleWidth(Float, Float)` | 이전/다음 페이지 보이는 너비 설정 (dp) |
| `autoScrollMillSecond(Long)` | 자동 스크롤 간격 설정 (밀리초, 0이면 비활성화) |
| `initialLoadPageSize(Int)` | 초기 로드 페이지 수 설정 |
| `infiniteScroll(Boolean, Boolean)` | 무한 스크롤 여부 / 첫 페이지 롤백 여부 설정 |
| `currentIndex((Int) -> Unit)` | 현재 페이지 인덱스 콜백 설정 |
| `applyOption()` | Builder에서 설정한 옵션 적용 |
| `copy(HongHorizontalPagerOption)` | 기존 옵션을 복사하여 새로운 Builder 생성 |
| `stopAutoScroll()` | 자동 스크롤 중지 (View 전용) |

## 📝 참고사항

### 무한 스크롤 동작 방식
- `infiniteScroll(on = true, isRollbackFirst = false)`: 페이지 리스트를 10배 확장하여 무한 스크롤 효과 제공
- `infiniteScroll(on = false, isRollbackFirst = true)`: 페이지 리스트를 10배 확장하고 첫 페이지로 롤백
- `infiniteScroll(on = false, isRollbackFirst = false)`: 일반 스크롤 (무한 스크롤 비활성화)

### 자동 스크롤
- `autoScrollMillSecond` 값이 0보다 크면 자동 스크롤 활성화
- View 사용 시 `stopAutoScroll()`로 명시적으로 중지 필요 (메모리 누수 방지)
- Compose 사용 시 자동으로 생명주기 관리

### 페이지 미리보기
- `pageVisibleWidth`를 설정하면 현재 페이지 양옆의 이전/다음 페이지가 보임
- `RecyclerView`의 `clipToPadding = false` 속성을 사용하여 구현

### currentIndex 콜백
- 페이지가 변경될 때마다 호출됨
- 무한 스크롤 모드에서도 원본 리스트 기준 인덱스를 반환 (`position % pageList.size`)

### 성능 최적화
- `initialLoadPageSize`: ViewPager의 `beyondViewportPageCount` 설정으로 미리 로드할 페이지 수 지정
- 기본값은 0 (인접 페이지만 로드)
