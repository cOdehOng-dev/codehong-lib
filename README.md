# codehong-lib

🚀 **한번쯤 사용하고 싶은 안드로이드 라이브러리**

안드로이드 개발에서 자주 필요하지만 매번 만들기 번거로운 커스텀뷰와 Jetpack Compose 컴포넌트를 모았습니다.

## ✨ 특징

- 실무에서 바로 사용 가능한 커스텀뷰
- Jetpack Compose 지원
- 간편한 커스터마이징
- 가벼운 의존성

## 📦 위젯 리스트

| 위젯                        | 설명                                                                                                                   | 문서                                                                             |
|---------------------------|----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| HongCalendar              | 커스텀 달력 위젯                                                                                                            | [README](Widget/src/main/java/com/codehong/library/widget/calendar)            |
| HongCheckBox              | 체크박스 위젯. 체크 상태 및 활성화/비활성화 상태에 따라 자동 색상 변경, 원형 모양 지원 (View/Compose 지원)                                                | [README](Widget/src/main/java/com/codehong/library/widget/checkbox)            |
| HorizontalPager           | 자동 스크롤, 무한 스크롤, 페이지 미리보기 등을 지원하는 수평 페이저 위젯 (View/Compose 지원)                                                         | [README](Widget/src/main/java/com/codehong/library/widget/pager)               |
| HongDynamicIsland         | iOS의 Dynamic Island를 모티프로 한 안드로이드 오버레이 알림 위젯. 항공편/숙박 예약 정보를 시각적으로 표시                                                 | [README](Widget/src/main/java/com/codehong/library/widget/dynamicisland)       |
| HongVideoPlayer           | ExoPlayer 기반의 비디오 플레이어 위젯. URL 기반 재생, 화면 비율 조정, 재생 상태 콜백 지원 (View/Compose 지원)                                        | [README](Widget/src/main/java/com/codehong/library/widget/player)              |
| HongVideoPopup            | HongVideoPlayer를 바탕으로 만든 비디오 팝업 (View/Compose 지원)                                                                    | [README](Widget/src/main/java/com/codehong/library/widget/videopopup)          |
| HongDragAndDrop           | Jetpack Compose 기반의 그리드 드래그 앤 드롭 위젯. Long Press로 편집 모드 진입, Shaking 애니메이션 지원                                          | [README](Widget/src/main/java/com/codehong/library/widget/draganddrop)         |
| HongGraph                 | Jetpack Compose 기반의 커스터마이징 가능한 라인/바 그래프 위젯. Canvas를 사용한 커스텀 렌더링 지원                                                   | [README](Widget/src/main/java/com/codehong/library/widget/graph)               |
| HongImage                 | Coil 기반의 고성능 이미지 로딩 위젯. 캐싱, placeholder, error 처리, 로딩 상태 콜백 지원 (View/Compose 지원)                                     | [README](Widget/src/main/java/com/codehong/library/widget/image/def)           |
| HongImageBlur             | 블러 효과가 적용된 이미지 위젯. Gaussian Blur로 배경 흐림 처리, 블러 강도 조절 지원 (Compose 지원)                                                    | [README](Widget/src/main/java/com/codehong/library/widget/image/blur)          |
| HongIcon                  | 빌더 패턴 기반의 커스텀 아이콘 위젯. 크기, 색상, ScaleType 등을 쉽게 설정 가능 (View/Compose 지원)                                                | [README](Widget/src/main/java/com/codehong/library/widget/icon)                |
| HongButtonIcon            | 아이콘 버튼 위젯. 버튼 상태에 따라 자동 색상 변경, 다양한 크기 옵션 지원 (View/Compose 지원)                                                        | [README](Widget/src/main/java/com/codehong/library/widget/button/icon)         |
| HongButtonText            | 텍스트 버튼 위젯. 다양한 스타일 커스터마이징과 ENABLED/DISABLED 상태 지원 (View/Compose 지원)                                                  | [README](Widget/src/main/java/com/codehong/library/widget/button/text)         |
| HongSelectButton          | 이중 선택 버튼 위젯. 취소/확인 등 양자택일 UI 제공 (View/Compose 지원)                                                                    | [README](Widget/src/main/java/com/codehong/library/widget/button/select)       |
| HongBottomSheetSwipe      | 드래그 제스처로 높이를 조절할 수 있는 스와이프 바텀시트. 배경 콘텐츠 스케일/오프셋 애니메이션 지원 (Compose 지원)                                                | [README](Widget/src/main/java/com/codehong/library/widget/bottomsheet/swipe)   |
| HongBottomSheetSelect     | 여러 옵션 중 하나를 선택할 수 있는 바텀시트. 제목/부제목이 있는 선택 항목 리스트와 선택된 항목 강조 표시 지원 (Compose 지원)                                        | [README](Widget/src/main/java/com/codehong/library/widget/bottomsheet/select)  |
| HongText                  | 빌더 패턴 기반의 커스텀 텍스트 위젯. 타이포그래피 시스템, Span 텍스트, 숫자 포맷팅, 줄바꿈 제어 등 지원 (View/Compose 지원)                                    | [README](Widget/src/main/java/com/codehong/library/widget/text/def)            |
| HongTextBadge             | 텍스트 배지 위젯. 상태, 카테고리, 태그 등을 표시하는 단일 줄 배지. 배경색, 테두리, 모서리 둥글기 커스터마이징 지원 (View/Compose 지원)                               | [README](Widget/src/main/java/com/codehong/library/widget/text/badge)          |
| HongCheckText             | 체크 가능한 텍스트 위젯. 체크마크, 텍스트, 화살표로 구성된 인터랙티브 선택 UI (View/Compose 지원)                                                     | [README](Widget/src/main/java/com/codehong/library/widget/text/check)          |
| HongTextCount             | 숫자 입력 및 증감 버튼을 제공하는 카운터 위젯. ➖/➕ 버튼으로 값 조절, Long/Double 타입 지원, 최소값/최대값 범위 제한 (View/Compose 지원)                        | [README](Widget/src/main/java/com/codehong/library/widget/text/count)          |
| HongTextUnit              | 숫자와 단위를 함께 표시하는 텍스트 위젯. 천 단위 콤마 자동 추가, 단위 표시 ON/OFF 제어, 독립적인 스타일 설정 지원 (View/Compose 지원)                             | [README](Widget/src/main/java/com/codehong/library/widget/text/unit)           |
| HongTextUpDown            | 마이너스/플러스 버튼으로 숫자를 증감시키는 위젯. 원형 버튼, 증감 단위 설정, 천 단위 콤마 지원 (View/Compose 지원)                                            | [README](Widget/src/main/java/com/codehong/library/widget/text/updown)         |
| HongTabFlow               | FlowRow 레이아웃을 사용하는 다중 행 탭 선택 위젯. 자동 줄바꿈, 한 줄당 최대 탭 개수 설정, 선택/미선택 시각적 구분 (View/Compose 지원)                            | [README](Widget/src/main/java/com/codehong/library/widget/tab/flow)            |
| HongTabScroll             | 수평 스크롤이 가능한 탭 선택 위젯. 선택된 탭이 자동으로 중앙으로 이동하며, 선택/미선택 상태에 따라 자동 스타일 변경 (View/Compose 지원)                                | [README](Widget/src/main/java/com/codehong/library/widget/tab/scroll)          |
| HongCaptureShare          | 스크린샷 자동 감지 및 공유 기능. 앱 내 스크린샷 촬영 시 자동으로 공유 UI 표시, 권한 관리 포함 (View/Compose 지원)                                          | [README](Widget/src/main/java/com/codehong/library/widget/captureshare)        |
| HongHeaderClose           | 제목과 닫기 버튼을 가진 심플한 헤더 위젯. 가운데 정렬된 제목, 오른쪽 닫기 버튼, 타이포그래피/색상 커스터마이징 지원 (View/Compose 지원)                                | [README](Widget/src/main/java/com/codehong/library/widget/header)              |
| HongHeaderIcon            | 뒤로가기 아이콘과 중앙 정렬 제목을 가진 헤더 위젯. 왼쪽 뒤로가기 버튼, 타이포그래피/색상 커스터마이징 지원 (Compose 지원)                                            | [README](Widget/src/main/java/com/codehong/library/widget/header/icon)         |
| HongLabelCheckbox         | 라벨, 설명, 체크박스가 결합된 선택 위젯. 주 라벨+부가 설명 표시, 체크박스 위치 선택(좌/우), 타이포그래피/색상 커스터마이징 지원 (View/Compose 지원)                       | [README](Widget/src/main/java/com/codehong/library/widget/label/checkbox)      |
| HongLabel                 | 주 라벨과 부가 설명을 표시하는 기본 라벨 위젯. 세로 배치, 타이포그래피/색상 커스터마이징, 설명 선택적 표시 지원 (View/Compose 지원)                                  | [README](Widget/src/main/java/com/codehong/library/widget/label/def)           |
| HongLabelInput            | 라벨, 설명, 텍스트 입력 필드가 결합된 폼 입력 위젯. Placeholder 지원, Clear 아이콘, 키보드 타입 설정, 텍스트 변경 콜백 등 제공 (View/Compose 지원)               | [README](Widget/src/main/java/com/codehong/library/widget/label/input)         |
| HongLabelSelectInput      | 라벨, 설명, 선택 버튼, 텍스트 입력 필드를 결합한 복합 선택 입력 위젯. 피커 다이얼로그, 직접 입력 모드, 숫자 전용 입력 지원 (View/Compose 지원)                         | [README](Widget/src/main/java/com/codehong/library/widget/label/select)        |
| HongLabelSwitch           | 라벨, 설명, 토글 스위치가 결합된 설정 전환 위젯. 설정 화면의 기능 활성화/비활성화에 최적화, 즉시 적용 지원 (View/Compose 지원)                                    | [README](Widget/src/main/java/com/codehong/library/widget/label/toggleswitch)  |
| HongTabSegment            | 세그먼트 컨트롤 방식의 탭 선택 위젯. iOS 스타일의 슬라이딩 인디케이터 애니메이션, 2~5개 옵션 선택에 최적화 (View/Compose 지원)                                   | [README](Widget/src/main/java/com/codehong/library/widget/tab/segment)         |
| HongSwitch                | iOS 스타일의 토글 스위치 위젯. 원형 커서가 슬라이딩하는 애니메이션, 설정 화면의 기능 활성화/비활성화에 최적화 (View/Compose 지원)                                   | [README](Widget/src/main/java/com/codehong/library/widget/toggleswitch)        |
| HongTextField             | 심플한 텍스트 입력 필드 위젯. 플레이스홀더, 지우기 버튼, 디바운스 입력 콜백 등의 기본 기능 제공 (View/Compose 지원)                                           | [README](Widget/src/main/java/com/codehong/library/widget/textfield/def)       |
| HongTextFieldNumber       | 숫자 전용 텍스트 입력 필드 위젯. 자동 천 단위 콤마 포맷팅, 숫자 키패드, 지우기 버튼 등 제공 (View/Compose 지원)                                            | [README](Widget/src/main/java/com/codehong/library/widget/textfield/number)    |
| HongTextFieldBorder       | 테두리가 있는 텍스트 입력 필드 위젯. 라벨, 플레이스홀더, 도움말, 필수 표시, 지우기 버튼, 접미사 등 완전한 폼 입력 UI 제공 (View/Compose 지원)                         | [README](Widget/src/main/java/com/codehong/library/widget/textfield/border)    |
| HongTextFieldBorderSelect | 라벨, 설명, 선택 버튼, 텍스트 입력 필드를 결합한 복합 선택 입력 위젯. 피커 다이얼로그, 직접 입력 모드, 숫자 전용 입력 지원 (Compose 지원)                              | [README](Widget/src/main/java/com/codehong/library/widget/textfield/select)    |
| HongTextFieldTimer        | 카운트다운 타이머가 내장된 텍스트 입력 필드 위젯. 제한 시간 내 입력을 받아야 하는 UI(인증번호 입력 등)에 최적화. 타이머 종료 시 자동 콜백 및 언더라인 색상 변경 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/textfield/timer)     |
| HongTextFieldUnderline    | 언더라인이 있는 텍스트 입력 필드 위젯. 포커스 상태에 따라 언더라인 색상이 자동으로 변경되어 시각적 피드백을 제공하는 심플한 텍스트 입력 컴포넌트 (View/Compose 지원)                 | [README](Widget/src/main/java/com/codehong/library/widget/textfield/underline) |
| HongScrollFadeLayout      | 스크롤 시 헤더가 페이드 인/아웃되는 레이아웃 위젯. 메인 콘텐츠가 사라질 때 헤더의 배경색과 아이콘/텍스트 색상이 부드럽게 전환 (Compose 지원)                                | [README](Widget/src/main/java/com/codehong/library/widget/layout/fade)         |
| HongPicker                | 바텀시트 스타일의 스크롤 피커 위젯. 단일/이중 선택 지원, 스냅 스크롤링, 직접 선택 또는 확인 버튼 모드 제공 (Compose 지원)                                         | [README](Widget/src/main/java/com/codehong/library/widget/picker)              |
| HongLiquidGlassTabBar     | iOS 26 Liquid Glass 스타일의 탭 바 위젯. 유리 질감 배경, 젤리 인디케이터, 렌즈 확대 효과, Light/Dark 테마 지원 (Compose 지원)                          | [README](Widget/src/main/java/com/codehong/library/widget/liquidglass/tabbar)  |
| HongSwipeContainer        | 좌측 스와이프 시 액션 버튼이 나타나는 스와이프 컨테이너 위젯. 삭제 등 아이템 단방향 액션 UI에 최적화, 스프링 애니메이션 및 풀스와이프 지원 (Compose 지원)                      | [README](Widget/src/main/java/com/codehong/library/widget/swipe)               |

## 🛠 설치

build.gradle

```gradle
implementation("com.codehong.library:widget:${LASTEST_VERSION}")
```

## 📄 License

```
Copyright 2024 codehong

Licensed under the Apache License, Version 2.0
```