# codehong-lib

🚀 **한번쯤 사용하고 싶은 안드로이드 라이브러리**

안드로이드 개발에서 자주 필요하지만 매번 만들기 번거로운 커스텀뷰와 Jetpack Compose 컴포넌트를 모았습니다.

## ✨ 특징

- 실무에서 바로 사용 가능한 커스텀뷰
- Jetpack Compose 지원
- 간편한 커스터마이징
- 가벼운 의존성

## 📦 위젯 리스트

| 위젯 | 설명 | 문서 |
|------|------|------|
| Calendar | 커스텀 달력 위젯 | [README](Widget/src/main/java/com/codehong/library/widget/calendar/README.md) |
| HorizontalPager | 자동 스크롤, 무한 스크롤, 페이지 미리보기 등을 지원하는 수평 페이저 위젯 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/pager/README.md) |
| Dynamic Island | iOS의 Dynamic Island를 모티프로 한 안드로이드 오버레이 알림 위젯. 항공편/숙박 예약 정보를 시각적으로 표시 | [README](Widget/src/main/java/com/codehong/library/widget/dynamicisland/README.md) |
| HongVideoPlayer | ExoPlayer 기반의 비디오 플레이어 위젯. URL 기반 재생, 화면 비율 조정, 재생 상태 콜백 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/player/README.md) |
| HongDragAndDrop | Jetpack Compose 기반의 그리드 드래그 앤 드롭 위젯. Long Press로 편집 모드 진입, Shaking 애니메이션 지원 | [README](Widget/src/main/java/com/codehong/library/widget/draganddrop/README.md) |
| HongGraph | Jetpack Compose 기반의 커스터마이징 가능한 라인/바 그래프 위젯. Canvas를 사용한 커스텀 렌더링 지원 | [README](Widget/src/main/java/com/codehong/library/widget/graph/README.md) |
| HongImage | Coil 기반의 고성능 이미지 로딩 위젯. 캐싱, placeholder, error 처리, 로딩 상태 콜백 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/image/README.md) |
| HongIcon | 빌더 패턴 기반의 커스텀 아이콘 위젯. 크기, 색상, ScaleType 등을 쉽게 설정 가능 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/icon/README.md) |
| HongButtonIcon | 아이콘 버튼 위젯. 버튼 상태에 따라 자동 색상 변경, 다양한 크기 옵션 지원 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/button/icon/README.md) |
| HongSelectButton | 이중 선택 버튼 위젯. 취소/확인 등 양자택일 UI 제공 (View/Compose 지원) | [README](Widget/src/main/java/com/codehong/library/widget/button/select/README.md) |

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