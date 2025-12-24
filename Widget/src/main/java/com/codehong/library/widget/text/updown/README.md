# HongTextUpDown

마이너스/플러스 버튼으로 숫자를 증감시키는 위젯입니다. 원형 `-`/`+` 버튼과 중앙의 숫자 표시로 구성되며, 수량 선택이 필요한 UI에서 사용할 수 있습니다.

## 📋 개요

HongTextUpDown은 증감 버튼을 통해 수량을 조절할 수 있는 인터랙티브 위젯입니다. 원형 버튼과 중앙의 숫자/단위 표시가 수평으로 배치되며, 버튼 클릭 시 지정된 gap 값만큼 증감합니다. 천 단위 콤마 자동 추가 기능과 다양한 커스터마이징 옵션을 제공합니다.

**주요 특징:**
- 원형 `-`/`+` 버튼으로 직관적인 증감 조작
- 중앙에 숫자와 단위 표시
- 증감 단위(gap) 커스터마이징
- 천 단위 콤마 자동 추가 옵션
- 버튼 크기, 간격, 색상 등 세부 스타일 설정
- 값 변경 콜백 지원

## 🏗️ 구조

```
text/updown/
├── HongTextUpDownCompose.kt    # Jetpack Compose UI 구현
├── HongTextUpDownView.kt        # Android View 래퍼 클래스
├── HongTextUpDownOption.kt      # 설정 데이터 클래스
└── HongTextUpDownBuilder.kt     # 빌더 패턴 구현
```

## 📦 주요 클래스

### HongTextUpDownCompose

Jetpack Compose로 구현된 증감 버튼 위젯입니다.

**주요 기능:**
- `Row` 레이아웃으로 버튼과 표시 영역을 수평 배치
- `HongImageCompose`로 원형 `-`/`+` 버튼 렌더링
- `HongTextUnitCompose`로 숫자와 단위 표시
- `rememberSaveable`로 상태 관리 (화면 회전 시에도 값 유지)
- 버튼 클릭 시 `gap` 값만큼 증감
- `onResult` 콜백으로 변경된 값 전달

```kotlin
@Composable
fun HongTextUpDownCompose(
    option: HongTextUpDownOption
)
```

**레이아웃 구조:**
```
[  -  ] [  7장  ] [  +  ]
 ↑       ↑         ↑
마이너스  숫자+단위  플러스
버튼     표시      버튼
```

### HongTextUpDownView

Android View 시스템을 위한 래퍼 클래스입니다. `LinearLayout`을 상속받아 구현되었습니다.

```kotlin
class HongTextUpDownView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    fun set(option: HongTextUpDownOption): HongTextUpDownView
}
```

**특징:**
- `HORIZONTAL` orientation
- `Gravity.CENTER_VERTICAL`로 수직 정렬
- 버튼 클릭 시 `FrameLayout` 내부 뷰를 동적으로 업데이트
- `getDisplayTextUnitOption()` 헬퍼 메서드로 표시 옵션 생성

### HongTextUpDownOption

위젯의 모든 설정을 포함하는 데이터 클래스입니다.

**주요 속성:**

| 속성 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `amount` | `Int` | `0` | 초기 수량 |
| `unit` | `String?` | `null` | 단위 텍스트 (예: "개", "장") |
| `gap` | `Int` | `1` | 증감 단위 |
| `useDecimal` | `Boolean` | `false` | 천 단위 콤마 사용 여부 |
| `displayTypo` | `HongTypo` | `BODY_16` | 숫자 표시 타이포그래피 |
| `displayColorHex` | `String` | `BLACK_100` | 숫자 표시 색상 |
| `buttonSize` | `Int` | `25` | 버튼 크기 (dp) |
| `spaceButtonAndDisplay` | `Int` | `5` | 버튼과 표시 간격 (dp) |
| `borderColorHex` | `String` | `GRAY_30` | 버튼 테두리 색상 |
| `iconColorHex` | `String` | `GRAY_50` | 버튼 아이콘 색상 |
| `onResult` | `(Int) -> Unit` | `{}` | 값 변경 콜백 |

### HongTextUpDownBuilder

빌더 패턴을 사용하여 `HongTextUpDownOption`을 쉽게 구성할 수 있습니다.

```kotlin
class HongTextUpDownBuilder :
    HongWidgetCommonBuilder<HongTextUpDownOption, HongTextUpDownBuilder>
```

**주요 메서드:**
- `amount(Int)` - 초기 수량 설정
- `unit(String?)` - 단위 텍스트 설정
- `gap(Int)` - 증감 단위 설정
- `displayTypo(HongTypo)` - 숫자 표시 타이포그래피
- `displayColor(HongColor/String)` - 숫자 표시 색상
- `useDecimal(Boolean)` - 천 단위 콤마 사용 여부
- `buttonSize(Int)` - 버튼 크기
- `spaceButtonAndDisplay(Int)` - 버튼과 표시 간격
- `borderColor(HongColor/String)` - 버튼 테두리 색상
- `iconColor(HongColor/String)` - 버튼 아이콘 색상
- `onResult((Int) -> Unit)` - 값 변경 리스너

## 🚀 사용법

### Jetpack Compose

```kotlin
@Composable
fun ProductQuantityScreen() {
    var quantity by remember { mutableStateOf(7) }

    HongTextUpDownCompose(
        HongTextUpDownBuilder()
            .amount(7)
            .unit("장")
            .gap(1)  // 1씩 증감
            .spaceButtonAndDisplay(8)
            .margin(HongSpacingInfo(left = 20f, right = 20f))
            .onResult { newAmount ->
                quantity = newAmount
                Log.d("Quantity", "New: $newAmount")
            }
            .applyOption()
    )
}
```

### Android View

```kotlin
val upDownView = HongTextUpDownView(context)
    .set(
        HongTextUpDownBuilder()
            .amount(5)
            .unit("개")
            .gap(1)
            .spaceButtonAndDisplay(8)
            .onResult { newAmount ->
                updateCart(newAmount)
            }
            .applyOption()
    )

parentLayout.addView(upDownView)
```

### 사용 예시: 천 단위 콤마 사용

```kotlin
HongTextUpDownCompose(
    HongTextUpDownBuilder()
        .amount(1000)
        .unit("원")
        .gap(100)  // 100원 단위로 증감
        .useDecimal(true)  // "1,000원"으로 표시
        .displayTypo(HongTypo.TITLE_20_B)
        .displayColor(HongColor.MAIN_ORANGE_100)
        .onResult { amount ->
            viewModel.updatePrice(amount)
        }
        .applyOption()
)
```

### 사용 예시: 커스텀 스타일

```kotlin
HongTextUpDownCompose(
    HongTextUpDownBuilder()
        .amount(3)
        .unit("개")
        .gap(1)
        .buttonSize(30)  // 버튼 크기 30dp
        .spaceButtonAndDisplay(12)  // 간격 12dp
        .borderColor(HongColor.MAIN_ORANGE_100)
        .iconColor(HongColor.MAIN_ORANGE_100)
        .displayTypo(HongTypo.TITLE_18_B)
        .displayColor(HongColor.BLACK_100)
        .onResult { amount ->
            Log.d("Amount", "Selected: $amount")
        }
        .applyOption()
)
```

## ⚙️ 주요 기능

### 1. 증감 조작

- `-` 버튼: 현재 값에서 `gap` 값만큼 감소
- `+` 버튼: 현재 값에서 `gap` 값만큼 증가
- 예: `gap = 1`이면 1씩 증감, `gap = 10`이면 10씩 증감

### 2. 숫자 포맷팅

- `useDecimal(true)` 설정 시 천 단위 콤마 자동 추가
- 예: `1000` → `"1,000"`, `50000` → `"50,000"`
- `toFigureStringCoverZero()` 확장 함수 활용

### 3. 상태 관리

- **Compose**: `rememberSaveable`로 화면 회전 시에도 값 유지
- **View**: 내부 `amount` 변수로 상태 관리, 버튼 클릭 시 뷰 업데이트

### 4. 커스터마이징

- 버튼 크기, 간격, 테두리 색상, 아이콘 색상 설정
- 숫자 표시 타이포그래피 및 색상 커스터마이징
- 단위 텍스트 표시 제어

## 📝 참고사항

- **초기값**: `amount`로 초기 수량을 설정합니다.
- **증감 단위**: `gap` 값으로 한 번 클릭 시 변경되는 양을 조절할 수 있습니다.
- **콜백**: `onResult` 콜백은 값이 변경될 때마다 호출되며, 변경된 값(Int)을 전달합니다.
- **버튼 크기**: `buttonSize`는 dp 단위이며, 기본값은 25dp입니다.
- **간격**: `spaceButtonAndDisplay`로 버튼과 숫자 표시 영역 간의 간격을 조절합니다.
- **원형 버튼**: 버튼은 `useShapeCircle(true)` 설정으로 원형 모양입니다.
- **아이콘**: `-`는 `honglib_ic_minus`, `+`는 `honglib_ic_plus` drawable 리소스를 사용합니다.

## 🔗 의존성

이 컴포넌트는 다음 모듈에 의존합니다:

- `HongImageCompose` - 원형 버튼 렌더링
- `HongTextUnitCompose` - 숫자와 단위 표시
- `HongWidgetContainer` - 공통 위젯 컨테이너
- `HongColor` - 색상 시스템
- `HongTypo` - 타이포그래피 시스템

## 📄 라이선스

이 컴포넌트는 codehong-lib 프로젝트의 일부입니다.
