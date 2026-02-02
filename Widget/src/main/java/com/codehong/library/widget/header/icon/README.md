# HongHeaderIcon

뒤로가기 아이콘과 중앙 정렬 제목을 가진 헤더 위젯입니다. 앱의 상단 네비게이션 바로 사용하기에 적합합니다.

## 📋 개요

- 왼쪽 뒤로가기 아이콘 버튼
- 중앙 정렬된 제목 텍스트
- 빌더 패턴을 통한 간편한 옵션 설정
- Jetpack Compose 지원

## 🏗️ 구조

```
header/icon/
├── HongHeaderIcon.kt           # 헤더 Composable 함수
├── HongHeaderIconBuilder.kt    # 빌더 클래스
└── HongHeaderIconOption.kt     # 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongHeaderIcon

헤더를 렌더링하는 Composable 함수입니다. `HongHeaderIconOption`을 파라미터로 받아 UI를 구성합니다.

### HongHeaderIconBuilder

빌더 패턴으로 헤더 옵션을 설정하는 클래스입니다. `HongWidgetCommonBuilder`를 상속받아 공통 속성(width, height, margin, padding 등)도 설정 가능합니다.

### HongHeaderIconOption

헤더의 모든 설정값을 담는 데이터 클래스입니다. 기본값이 설정되어 있어 필요한 속성만 변경하여 사용할 수 있습니다.

## 🚀 사용법

```kotlin
// 기본 사용법
val option = HongHeaderIconBuilder()
    .title("헤더 제목")
    .titleTypo(HongTypo.BODY_18)
    .titleColor(HongColor.BLACK_100.hex)
    .backIcon(R.drawable.honglib_ic_arrow_left)
    .onBack {
        // 뒤로가기 클릭 처리
    }
    .applyOption()

HongHeaderIcon(option)
```

```kotlin
// 커스텀 스타일 적용
val option = HongHeaderIconBuilder()
    .title("마이페이지")
    .titleTypo(HongTypo.TITLE_20)
    .titleColor("#333333")
    .backIcon(R.drawable.ic_back)
    .backIconColor(HongColor.GRAY_80)
    .backgroundColor("#FFFFFF")
    .height(56)
    .onBack { navController.popBackStack() }
    .applyOption()

HongHeaderIcon(option)
```

## ⚙️ 주요 메서드

### HongHeaderIconBuilder

| 메서드 | 설명 |
|--------|------|
| `title(title: String?)` | 헤더 제목 설정 |
| `titleTypo(typo: HongTypo)` | 제목 타이포그래피 설정 |
| `titleColor(color: HongColor)` | 제목 색상 설정 (HongColor) |
| `titleColor(colorHex: String)` | 제목 색상 설정 (Hex 문자열) |
| `backIcon(@DrawableRes iconRes: Int)` | 뒤로가기 아이콘 리소스 설정 |
| `backIconColor(color: HongColor)` | 아이콘 색상 설정 (HongColor) |
| `backIconColor(colorHex: String)` | 아이콘 색상 설정 (Hex 문자열) |
| `onBack(onBack: () -> Unit)` | 뒤로가기 클릭 콜백 설정 |
| `copy(inject: HongHeaderIconOption)` | 기존 옵션을 복사하여 새 빌더 생성 |

### HongHeaderIconOption 기본값

| 속성 | 기본값 |
|------|--------|
| `width` | `MATCH_PARENT` |
| `height` | `50` |
| `backgroundColorHex` | `HongColor.WHITE_100.hex` |
| `titleTypo` | `HongTypo.BODY_18` |
| `titleColorHex` | `HongColor.BLACK_100.hex` |
| `backIconColorHex` | `HongColor.BLACK_100.hex` |

## 📝 참고사항

- 제목은 최대 1줄로 표시되며, 길어질 경우 말줄임(...) 처리됩니다.
- 뒤로가기 아이콘은 34x34dp 크기로 고정되며, 터치 영역은 40x40dp입니다.
- `backIcon`을 설정하지 않으면 뒤로가기 아이콘이 표시되지 않습니다.
- `HongWidgetCommonBuilder`를 상속받아 `margin`, `padding`, `backgroundColor` 등 공통 속성도 사용 가능합니다.
