# codehong-lib

🚀 **한번쯤 사용하고 싶은 안드로이드 라이브러리**

안드로이드 개발에서 자주 필요하지만 매번 만들기 번거로운 커스텀뷰와 Jetpack Compose 컴포넌트를 모았습니다.

## ✨ 특징

- 실무에서 바로 사용 가능한 커스텀뷰
- Jetpack Compose 지원
- 간편한 커스터마이징
- 가벼운 의존성

## 📦 위젯 리스트

| 위젯                   | 설명 | 문서 |
|----------------------|------|------|
| Calendar             | 커스텀 달력 위젯 | [README](Widget/src/main/java/com/codehong/library/widget/calendar/README.md) |
| HongCheckBox         | 체크박스 위젯. 체크 상태 및 활성화/비활성화 상태에 따라 자동 색상 변경, 원형 모양 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/checkbox/README.md) |
| HorizontalPager      | 자동 스크롤, 무한 스크롤, 페이지 미리보기 등을 지원하는 수평 페이저 위젯 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/pager/README.md) |
| HongDynamicIsland    | iOS의 Dynamic Island를 모티프로 한 안드로이드 오버레이 알림 위젯. 항공편/숙박 예약 정보를 시각적으로 표시 | [README](Widget/src/main/java/com/codehong/library/widget/dynamicisland/README.md) |
| HongVideoPlayer      | ExoPlayer 기반의 비디오 플레이어 위젯. URL 기반 재생, 화면 비율 조정, 재생 상태 콜백 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/player/README.md) |
| HongDragAndDrop      | Jetpack Compose 기반의 그리드 드래그 앤 드롭 위젯. Long Press로 편집 모드 진입, Shaking 애니메이션 지원 | [README](Widget/src/main/java/com/codehong/library/widget/draganddrop/README.md) |
| HongGraph            | Jetpack Compose 기반의 커스터마이징 가능한 라인/바 그래프 위젯. Canvas를 사용한 커스텀 렌더링 지원 | [README](Widget/src/main/java/com/codehong/library/widget/graph/README.md) |
| HongImage            | Coil 기반의 고성능 이미지 로딩 위젯. 캐싱, placeholder, error 처리, 로딩 상태 콜백 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/image/README.md) |
| HongIcon             | 빌더 패턴 기반의 커스텀 아이콘 위젯. 크기, 색상, ScaleType 등을 쉽게 설정 가능 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/icon/README.md) |
| HongButtonIcon       | 아이콘 버튼 위젯. 버튼 상태에 따라 자동 색상 변경, 다양한 크기 옵션 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/button/icon/README.md) |
| HongButtonText       | 텍스트 버튼 위젯. 다양한 스타일 커스터마이징과 ENABLED/DISABLED 상태 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/button/text/README.md) |
| HongSelectButton     | 이중 선택 버튼 위젯. 취소/확인 등 양자택일 UI 제공 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/button/select/README.md) |
| HongBottomSheetSwipe | 드래그 제스처로 높이를 조절할 수 있는 스와이프 바텀시트. 배경 콘텐츠 스케일/오프셋 애니메이션 지원 (Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/bottomsheet/swipe/README.md) |
| HongBottomSheetSelect | 여러 옵션 중 하나를 선택할 수 있는 바텀시트. 제목/부제목이 있는 선택 항목 리스트와 선택된 항목 강조 표시 지원 (Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/bottomsheet/select/README.md) |
| HongText             | 빌더 패턴 기반의 커스텀 텍스트 위젯. 타이포그래피 시스템, Span 텍스트, 숫자 포맷팅, 줄바꿈 제어 등 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/text/label/README.md) |
| HongTextBadge        | 텍스트 배지 위젯. 상태, 카테고리, 태그 등을 표시하는 단일 줄 배지. 배경색, 테두리, 모서리 둥글기 커스터마이징 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/text/badge/README.md) |
| HongCheckText        | 체크 가능한 텍스트 위젯. 체크마크, 텍스트, 화살표로 구성된 인터랙티브 선택 UI (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/text/check/README.md) |
| HongTextCount        | 숫자 입력 및 증감 버튼을 제공하는 카운터 위젯. ➖/➕ 버튼으로 값 조절, Long/Double 타입 지원, 최소값/최대값 범위 제한 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/text/count/README.md) |

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