# 달력

날짜 범위 선택이 가능한 커스터마이징 캘린더 위젯입니다. Jetpack Compose와 Android View 시스템 두 가지 방식을 모두 지원합니다.

## 📋 개요

HongCalendar는 시작일과 종료일을 선택할 수 있는 기간 선택 캘린더 위젯입니다. 공휴일 표시, 다국어 요일 지원, 다양한 텍스트 스타일 커스터마이징 기능을 제공합니다.

**주요 기능:**
- 날짜 범위 선택 (시작일 ~ 종료일)
- Jetpack Compose 및 Android View 지원
- 공휴일 및 주말 자동 표시
- 과거 날짜 비활성화
- 한국어/영어 요일 지원
- 텍스트 스타일 및 색상 커스터마이징
- 초기 선택 날짜 설정 가능

## 🏗️ 구조

```
calendar/
├── HongCalendarBuilder.kt              # 빌더 패턴으로 옵션 구성
├── HongCalendarCompose.kt              # Compose UI 구현
├── HongCalendarOption.kt               # 캘린더 설정 옵션
├── HongCalendarView.kt                 # View 시스템 UI 구현
├── adapter/
│   ├── HongCalendarListAdapter.kt      # RecyclerView 어댑터
│   └── HongCalendarViewHolder.kt       # RecyclerView 뷰홀더
└── model/
    ├── HongCalendarDayOfWeekLangType.kt          # 요일 언어 타입 (KOR/ENG)
    ├── HongCalendarSelectBackgroundColorHex.kt   # 선택 배경색 설정
    ├── HongCalendarSelectedType.kt               # 선택 타입 (START/END/BETWEEN)
    └── InitialSelectedInfo.kt                    # 초기 선택 날짜 정보
```

## 📦 주요 클래스

### HongCalendarBuilder
빌더 패턴을 사용하여 캘린더의 다양한 옵션을 설정하는 클래스입니다. 메서드 체이닝을 통해 직관적으로 설정을 구성할 수 있습니다.

### HongCalendarCompose
Jetpack Compose를 사용하여 구현된 캘린더 UI입니다. LazyColumn을 사용하여 효율적인 스크롤을 지원하며, 날짜 선택 시 즉각적인 UI 업데이트가 가능합니다.

### HongCalendarView
Android View 시스템을 사용하여 구현된 캘린더 뷰입니다. RecyclerView를 사용하여 월별 날짜를 표시합니다.

### HongCalendarOption
캘린더의 모든 설정 값을 담는 데이터 클래스입니다. 텍스트 스타일, 색상, 간격, 공휴일 목록 등 다양한 옵션을 포함합니다.

## 🚀 사용법

### 1. Jetpack Compose 사용

```kotlin
@Composable
fun MyScreen() {
    val option = HongCalendarBuilder()
        .backgroundColor(HongColor.WHITE_100.hex)
        .initialSelectedInfo(
            InitialSelectedInfo(
                startDate = "20250710",
                endDate = "20250720"
            )
        )
        .dayOfWeekLangType(HongCalendarDayOfWeekLangType.KOR)
        .yearMonthPattern("yyyy.MM")
        .selectBackgroundColorHex(
            HongCalendarSelectBackgroundColorHex(
                startDayColorHex = HongColor.MAIN_ORANGE_100.hex,
                endDayColorHex = HongColor.MAIN_ORANGE_100.hex,
                rangeDaysColorHex = HongColor.MAIN_ORANGE_15.hex
            )
        )
        .startDayTextOption(
            HongTextBuilder()
                .size(17)
                .color(HongColor.WHITE_100.hex)
                .fontType(HongFont.PRETENDARD_700)
                .applyOption()
        )
        .holidayList(HongDateUtil.KOREAN_HOLIDAY_LIST)
        .onSelected { startDate, endDate ->
            // 날짜 선택 시 콜백
            println("시작일: $startDate, 종료일: $endDate")
        }
        .applyOption()

    HongCalendarCompose(option)
}
```

### 2. Android View 사용

```kotlin
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calendarView = HongCalendarView(this)
        val option = HongCalendarBuilder()
            .backgroundColor(HongColor.WHITE_100.hex)
            .dayOfWeekLangType(HongCalendarDayOfWeekLangType.ENG)
            .onSelected { startDate, endDate ->
                // 날짜 선택 시 콜백
                Log.d("Calendar", "시작일: $startDate, 종료일: $endDate")
            }
            .applyOption()

        calendarView.set(option)
    }
}
```

## ⚙️ 주요 설정 옵션

| 메서드 | 설명 |
|--------|------|
| `height(Int)` | 캘린더 높이 설정 |
| `margin(HongSpacingInfo)` | 외부 여백 설정 |
| `spacingHorizontal(Int)` | 좌우 내부 여백 설정 |
| `backgroundColor(String)` | 배경색 설정 (HEX) |
| `dayOfWeekTextOption(HongTextOption)` | 요일 텍스트 스타일 |
| `dayOfWeekBottomLineColorHex(String)` | 요일 하단 구분선 색상 |
| `yearMonthTextOption(HongTextOption)` | 년월 표시 텍스트 스타일 |
| `yearMonthPattern(String)` | 년월 표시 패턴 (기본: "yyyy.MM") |
| `selectBackgroundColorHex(HongCalendarSelectBackgroundColorHex)` | 선택 영역 배경색 설정 |
| `startDayTextOption(HongTextOption)` | 시작일 텍스트 스타일 |
| `endDayTextOption(HongTextOption)` | 종료일 텍스트 스타일 |
| `rangeDaysTextOption(HongTextOption)` | 범위 내 날짜 텍스트 스타일 |
| `holidaysTextOption(HongTextOption)` | 공휴일 텍스트 스타일 |
| `pastDaysTextOption(HongTextOption)` | 과거 날짜 텍스트 스타일 |
| `unselectTodayTextOption(HongTextOption)` | 선택되지 않은 오늘 표시 스타일 |
| `selectTodayTextOption(HongTextOption)` | 선택된 오늘 표시 스타일 |
| `defaultDayTextOption(HongTextOption)` | 기본 날짜 텍스트 스타일 |
| `bottomSpacingDayOfWeek(Int)` | 요일 하단 간격 |
| `bottomSpacingMonth(Int)` | 월 간 간격 |
| `bottomSpacingWeek(Int)` | 주 간 간격 |
| `holidayList(List<LocalDate>)` | 공휴일 목록 설정 |
| `maxYears(Int)` | 최대 표시 년도 범위 (기본: 1년) |
| `initialSelectedInfo(InitialSelectedInfo)` | 초기 선택 날짜 설정 |
| `dayOfWeekLangType(HongCalendarDayOfWeekLangType)` | 요일 언어 설정 (KOR/ENG) |
| `onSelected((LocalDate?, LocalDate?) -> Unit)` | 날짜 선택 콜백 |

## 📝 날짜 선택 동작

1. **첫 번째 클릭**: 시작일 선택
2. **두 번째 클릭**:
   - 시작일보다 이후 날짜 클릭 → 종료일로 설정
   - 시작일보다 이전 날짜 클릭 → 새로운 시작일로 설정
3. **세 번째 클릭**: 기존 선택 초기화 후 새로운 시작일 선택

## 🎨 선택 영역 스타일

날짜 선택 영역은 3가지 스타일로 구분됩니다:

- **startDayColorHex**: 시작일 배경색 (원형)
- **endDayColorHex**: 종료일 배경색 (원형)
- **rangeDaysColorHex**: 범위 내 날짜 배경색 (사각형)

## 📝 참고사항

- 과거 날짜는 자동으로 비활성화되며 클릭할 수 없습니다
- 공휴일 목록을 제공하지 않으면 일요일만 공휴일로 표시됩니다
- 초기 선택 날짜는 `InitialSelectedInfo`를 사용하여 설정 가능합니다
- 날짜 형식은 ThreeTen-BP 라이브러리의 `LocalDate`를 사용합니다
- `maxYears` 설정으로 표시할 최대 년도 범위를 제한할 수 있습니다 (기본: 1년)
- 요일은 한국어(일~토) 또는 영어(Sun~Sat) 중 선택 가능합니다