# HongBottomSheetBank

한국 은행 선택을 위한 커스텀 바텀시트 위젯. Material3 ModalBottomSheet 대신 Compose `Dialog` 기반으로 직접 구현하여 시스템 nav bar / status bar 처리, 드래그 제스처, 스냅 애니메이션을 완전히 제어합니다 (Compose 지원)

## 📋 개요

- 한국 주요 은행 24개를 3열 그리드로 표시
- 아래에서 위로 슬라이드업 진입 애니메이션
- 드래그 핸들로 시트 높이 조절 (Partial → Expanded → Hidden 3단계 스냅)
- 콘텐츠 스크롤과 시트 드래그 자동 조율 (NestedScrollConnection)
- 은행 선택 후 슬라이드 다운 애니메이션으로 자동 닫힘
- Edge-to-edge 지원 (status bar에 dim 색상 적용)
- 드래그로만 닫기 가능 (스크림 클릭 닫기 비활성화)

## 🏗️ 구조

```
bottomsheet/bank/
├── HongBottomSheetBank.kt        # 메인 Composable 함수
├── HongBottomSheetBankOption.kt  # 옵션 데이터 클래스
├── HongBottomSheetBankBuilder.kt # 빌더 패턴 헬퍼
└── README.md
```

> `KoreanBank` enum은 `com.codehong.library.widget.util.KoreanBank`에 위치합니다.

## 📦 주요 클래스

### HongBottomSheetBank
메인 Composable. `showBottomSheet`가 `true`일 때 Dialog를 표시하며, 내부적으로 3단계 스냅 상태를 관리합니다.

| 상태 | offsetY 값 | 설명 |
|------|-----------|------|
| Partial | `maxSheetHeightPx / 2` | 초기 노출 상태 (화면 1/3 높이 노출) |
| Expanded | `0f` | 완전히 펼쳐진 상태 (화면 2/3 높이) |
| Hidden | `maxSheetHeightPx` | 완전히 숨겨진 상태 |

### HongBottomSheetBankOption
바텀시트의 모든 옵션을 담는 데이터 클래스.

| 프로퍼티 | 타입 | 기본값 | 설명 |
|---------|------|--------|------|
| `topRadius` | `Int` | `24` | 상단 모서리 둥글기 |
| `dimColorHex` | `String` | `GRAY_30` | 배경 딤 색상 |
| `dragHandleColorHex` | `String` | `GRAY_50` | 드래그 핸들 색상 |
| `titleTypo` | `HongTypo` | `BODY_20_B` | 타이틀 타이포그래피 |
| `titleColorHex` | `String` | `BLACK_100` | 타이틀 텍스트 색상 |
| `onBankSelected` | `(KoreanBank) -> Unit` | `{}` | 은행 선택 콜백 |
| `onDismissed` | `() -> Unit` | `{}` | 뷰 완전히 사라진 후 콜백 |

### HongBottomSheetBankBuilder
빌더 패턴으로 `HongBottomSheetBankOption`을 구성하는 헬퍼 클래스.

## 🚀 사용법

### 기본 사용

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

HongBottomSheetBank(
    showBottomSheet = showBottomSheet,
    option = HongBottomSheetBankBuilder()
        .onBankSelected { bank ->
            // 슬라이드 다운 애니메이션 완료 후 호출됨
            selectBankName = bank.bankName
            showBottomSheet = false
        }
        .onDismissed {
            // 뷰가 완전히 사라졌을 때 콜백
            showBottomSheet = false
        }
        .applyOption()
)
```

### 커스터마이징

```kotlin
HongBottomSheetBank(
    showBottomSheet = showBottomSheet,
    option = HongBottomSheetBankBuilder()
        .topRadius(16)
        .dimColor(HongColor.BLACK_70)
        .dragHandleColor(HongColor.GRAY_30)
        .titleTypo(HongTypo.BODY_16_B)
        .titleColor(HongColor.BLACK_100)
        .onBankSelected { bank ->
            selectBank = bank
            showBottomSheet = false
        }
        .onDismissed {
            showBottomSheet = false
        }
        .applyOption()
)
```

## ⚙️ 주요 동작

| 제스처 / 이벤트 | 동작 |
|--------------|------|
| 진입 시 | 아래에서 위로 슬라이드업 (300ms) |
| 위로 빠른 플링 (속도 > 500) | Expanded 상태로 스냅 |
| 아래로 빠른 플링 (속도 > 500) | Partial 또는 Hidden으로 스냅 |
| 드래그 핸들 아래로 빠른 드래그 (속도 > 800) | 슬라이드 다운 후 닫힘 |
| 콘텐츠 위로 스크롤 (시트 미확장 상태) | 시트 먼저 Expanded로 확장 |
| 콘텐츠 최상단에서 아래 스크롤 | 시트 아래로 드래그 |
| 은행 아이템 클릭 | 슬라이드 다운 애니메이션(250ms) → `onBankSelected` 콜백 |

## 📝 참고사항

- `onBankSelected` 콜백은 슬라이드 다운 애니메이션이 **완료된 후** 호출됩니다. 콜백에서 `showBottomSheet = false`를 설정해도 애니메이션이 중단되지 않습니다.
- `onDismissed`는 드래그로 닫거나 은행 선택으로 닫힌 경우 모두 호출됩니다.
- Dialog 창에 `WindowCompat.setDecorFitsSystemWindows(false)`가 적용되어 dim이 status bar 영역까지 덮습니다.
- 시트 최대 높이는 화면 높이의 2/3로 고정됩니다.
- `KoreanBank` enum에 포함된 은행 목록: 카카오뱅크, KB국민, 신한, 우리, 하나, NH농협, IBK기업, KDB산업, 수협, 전북, 광주, 제주, 경남, 부산, 대구, 토스뱅크, 케이뱅크, SC제일, 한국씨티, 우체국, 새마을금고, 신협, 카카오페이, 네이버파이낸셜 (총 24개)
