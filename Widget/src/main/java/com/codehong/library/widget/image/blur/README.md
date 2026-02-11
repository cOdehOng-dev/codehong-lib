# HongImageBlur

블러 효과가 적용된 이미지 위젯

## 📋 개요

HongImageBlur는 Coil 기반의 이미지에 Gaussian Blur 효과를 적용하는 Jetpack Compose 위젯입니다. 배경 이미지 흐림 처리, 오버레이 효과 등에 활용할 수 있으며, Builder 패턴을 통해 블러 강도, 크기, 모양 등을 간편하게 설정할 수 있습니다.

## 🏗️ 구조

```
image/blur/
├── HongImageBlur.kt             # Compose 기반 블러 이미지 위젯
├── HongImageBlurBuilder.kt      # 블러 이미지 빌더 클래스
└── HongImageBlurOption.kt       # 블러 이미지 옵션 데이터 클래스
```

## 📦 주요 클래스

### HongImageBlur
Jetpack Compose 기반 블러 이미지 위젯입니다. `Modifier.blur()`를 사용하여 이미지에 블러 효과를 적용하며, Coil의 `rememberAsyncImagePainter`로 비동기 이미지 로딩을 수행합니다.

### HongImageBlurBuilder
빌더 패턴을 사용하여 블러 이미지 옵션을 설정하는 클래스입니다. `HongWidgetCommonBuilder`를 구현하여 width, height, margin, padding 등 공통 속성과 블러 전용 속성을 메서드 체이닝으로 설정할 수 있습니다.

### HongImageBlurOption
블러 이미지의 모든 설정값을 담고 있는 데이터 클래스입니다. `HongWidgetCommonOption`을 구현하여 공통 위젯 속성을 상속받으며, 블러 강도(`blur`), 이미지 소스(`imageInfo`), 스케일 타입(`scaleType`) 등의 전용 속성을 포함합니다.

## 🚀 사용법

### 기본 사용

```kotlin
val option = HongImageBlurBuilder()
    .imageInfo("https://example.com/image.jpg")
    .blur(30)
    .applyOption()

HongImageBlur(option)
```

### 크기 지정

```kotlin
val option = HongImageBlurBuilder()
    .width(150)
    .height(200)
    .imageInfo("https://example.com/image.jpg")
    .blur(30)
    .applyOption()

HongImageBlur(option)
```

### 원형 블러 이미지

```kotlin
val option = HongImageBlurBuilder()
    .width(150)
    .height(150)
    .imageInfo("https://example.com/image.jpg")
    .blur(80)
    .useShapeCircle(true)
    .applyOption()

HongImageBlur(option)
```

### 배경 블러 + 원본 이미지 오버레이

```kotlin
Box(
    modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(4f / 5f)
        .clipToBounds(),
    contentAlignment = Alignment.Center
) {
    // 배경 블러 이미지
    HongImageBlur(
        HongImageBlurBuilder()
            .imageInfo(imageUrl)
            .blur(30)
            .applyOption()
    )

    // 원본 이미지
    HongImageCompose(
        option = HongImageBuilder()
            .width(150)
            .height(200)
            .scaleType(HongScaleType.CENTER_CROP)
            .imageInfo(imageUrl)
            .applyOption()
    )
}
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `imageInfo(Any?)` | 이미지 소스 설정 (URL, Uri, Drawable 등) |
| `blur(Int)` | 블러 강도 설정 (dp 단위, 기본값: 30) |
| `scaleType(HongScaleType)` | 이미지 스케일 타입 설정 |
| `radius(HongRadiusInfo)` | 모서리 둥글기 설정 |
| `useShapeCircle(Boolean)` | 원형 이미지 여부 설정 |
| `width(Int)` | 너비 설정 (dp) |
| `height(Int)` | 높이 설정 (dp) |
| `copy(HongImageBlurOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 📝 참고사항

- `blur` 값이 클수록 더 강한 블러 효과가 적용됩니다 (dp 단위)
- 기본 블러 강도는 30dp이며, 배경용으로는 30~50, 강한 효과에는 80 이상 권장
- 기본 ScaleType은 `CENTER_CROP`으로 설정되어 있음
- `imageInfo`가 null이면 위젯이 렌더링되지 않음
- `useShapeCircle`을 true로 설정하면 radius 설정은 무시되고 원형으로 표시됨
- Compose 전용 위젯으로, View 기반은 지원하지 않음
- `HongWidgetContainer`를 통해 공통 위젯 속성(margin, padding, click 등)이 자동 적용됨
