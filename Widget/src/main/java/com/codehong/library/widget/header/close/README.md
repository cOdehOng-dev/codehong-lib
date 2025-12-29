# HongHeaderClose

제목과 닫기 버튼을 가진 심플한 헤더 위젯입니다.

## 📋 개요

HongHeaderClose는 앱 화면 상단에 표시되는 헤더 컴포넌트로, 가운데 정렬된 제목과 오른쪽 닫기 버튼을 제공합니다. 빌더 패턴을 통해 타이포그래피, 색상 등을 쉽게 커스터마이징할 수 있으며, Jetpack Compose와 기존 View 시스템 모두를 지원합니다.

## 🏗️ 구조

```
header/
├── HongHeaderCloseBuilder.kt       # 빌더 패턴을 사용한 헤더 설정 클래스
├── HongHeaderCloseOption.kt        # 헤더 옵션 데이터 클래스
├── HongHeaderCloseCompose.kt       # Compose용 헤더 컴포넌트
└── HongHeaderCloseView.kt          # View 시스템용 커스텀 뷰
```

## 📦 주요 클래스

### HongHeaderCloseBuilder
빌더 패턴을 사용하여 헤더의 옵션을 구성하는 클래스입니다.

**주요 기능:**
- 제목 설정 (`title`)
- 타이포그래피 커스터마이징 (`titleTypo`)
- 제목 색상 설정 (`titleColor`)
- 닫기 아이콘 색상 설정 (`closeIconColor`)
- 닫기 버튼 클릭 이벤트 설정 (`close`)
- 옵션 복사 (`copy`)

### HongHeaderCloseOption
헤더의 모든 설정 값을 담고 있는 데이터 클래스입니다.

**주요 속성:**
- `title`: 헤더 제목 텍스트
- `titleTypo`: 제목의 타이포그래피 (기본값: `HongTypo.BODY_16_B`)
- `titleColorHex`: 제목 색상 (기본값: `HongColor.BLACK_100`)
- `closeIconColorHex`: 닫기 아이콘 색상 (기본값: `HongColor.BLACK_100`)
- `onCloseClick`: 닫기 버튼 클릭 콜백
- `width`: 헤더 너비 (기본값: MATCH_PARENT)
- `height`: 헤더 높이 (기본값: 52dp)
- `backgroundColorHex`: 배경 색상 (기본값: TRANSPARENT)

### HongHeaderCloseCompose
Jetpack Compose에서 사용할 수 있는 헤더 컴포넌트입니다.

**구조:**
- 왼쪽 40dp 여백
- 가운데 정렬된 제목 (weight=1f)
- 오른쪽 닫기 버튼 (40dp x 40dp)

### HongHeaderCloseView
기존 View 시스템에서 사용할 수 있는 커스텀 LinearLayout 뷰입니다.

**구조:**
- Horizontal LinearLayout
- 왼쪽 40dp 여백
- 가운데 정렬된 제목 (weight=1f)
- 오른쪽 닫기 버튼 (40dp x 40dp)

## 🚀 사용법

### Jetpack Compose 사용

```kotlin
@Composable
fun MyScreen() {
    val headerOption = HongHeaderCloseBuilder()
        .title("마이페이지")
        .titleTypo(HongTypo.BODY_16_B)
        .titleColor(HongColor.BLACK_100)
        .closeIconColor(HongColor.GRAY_50)
        .close {
            // 닫기 버튼 클릭 시 동작
            finish()
        }
        .backgroundColor(HongColor.WHITE)
        .applyOption()

    HongHeaderCloseCompose(option = headerOption)
}
```

### View 시스템 사용

**XML 레이아웃:**
```xml
<com.codehong.library.widget.header.HongHeaderCloseView
    android:id="@+id/headerClose"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Kotlin 코드:**
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val headerOption = HongHeaderCloseBuilder()
            .title("설정")
            .titleTypo(HongTypo.BODY_16_B)
            .titleColor(HongColor.BLACK_100)
            .close {
                finish()
            }
            .applyOption()

        findViewById<HongHeaderCloseView>(R.id.headerClose)
            .set(headerOption)
    }
}
```

### 커스터마이징 예시

```kotlin
// 배경색과 텍스트 색상 변경
val customHeader = HongHeaderCloseBuilder()
    .title("알림")
    .titleTypo(HongTypo.TITLE_18_B)
    .titleColor("#FFFFFF")  // Hex 색상 직접 입력
    .closeIconColor("#FFFFFF")
    .backgroundColor("#4A90E2")
    .height(60)
    .close { /* 닫기 동작 */ }
    .applyOption()
```

## ⚙️ 주요 메서드

### HongHeaderCloseBuilder

| 메서드 | 파라미터 | 설명 |
|--------|----------|------|
| `title(title)` | `String?` | 헤더 제목 설정 |
| `titleTypo(typo)` | `HongTypo` | 제목의 타이포그래피 설정 |
| `titleColor(color)` | `HongColor` 또는 `String` | 제목 색상 설정 (HongColor 또는 Hex) |
| `closeIconColor(color)` | `HongColor` 또는 `String` | 닫기 아이콘 색상 설정 |
| `close(onClose)` | `() -> Unit` | 닫기 버튼 클릭 콜백 설정 |
| `backgroundColor(color)` | `String` | 배경 색상 설정 (Hex) |
| `width(width)` | `Int` | 헤더 너비 설정 |
| `height(height)` | `Int` | 헤더 높이 설정 |
| `copy(inject)` | `HongHeaderCloseOption` | 기존 옵션을 복사하여 새 빌더 생성 |
| `applyOption()` | - | 빌더 설정을 Option 객체로 변환 |

### HongHeaderCloseView

| 메서드 | 파라미터 | 반환 타입 | 설명 |
|--------|----------|-----------|------|
| `set(option)` | `HongHeaderCloseOption` | `HongHeaderCloseView` | 옵션을 적용하여 뷰 구성 |

## 📝 참고사항

### 레이아웃 구조
- **전체 너비:** MATCH_PARENT (기본값)
- **전체 높이:** 52dp (Compose), 58dp (View)
- **왼쪽 여백:** 40dp
- **닫기 버튼:** 40dp x 40dp (터치 영역), 20dp x 20dp (아이콘)
- **제목:** 가운데 정렬, 한 줄 표시, 넘침 시 말줄임표

### 기본값
- 제목 타이포그래피: `HongTypo.BODY_16_B`
- 제목 색상: `HongColor.BLACK_100` (#000000)
- 닫기 아이콘 색상: `HongColor.BLACK_100` (#000000)
- 배경 색상: `HongColor.TRANSPARENT`

### 주의사항
- 제목은 한 줄로 표시되며, 길이가 길면 자동으로 말줄임표(...) 처리됩니다
- View 시스템에서는 높이가 58dp, Compose에서는 52dp로 약간 차이가 있습니다
- 닫기 아이콘은 `honglib_ic_close` 리소스를 사용하므로, 해당 drawable이 필요합니다
- `close` 메서드를 통해 닫기 버튼 클릭 이벤트를 반드시 설정해야 합니다

### 의존성
- HongText (제목 표시)
- HongImage (닫기 아이콘)
- HongWidgetCommonBuilder (공통 빌더 인터페이스)
- HongColor, HongTypo (디자인 시스템)

### 사용 사례
- 전체 화면 모달/다이얼로그 상단 헤더
- 상세 페이지 상단 헤더
- 설정 화면 헤더
- 마이페이지 헤더
