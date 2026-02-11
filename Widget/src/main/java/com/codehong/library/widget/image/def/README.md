# HongImage

Coil 기반의 고성능 이미지 로딩 위젯

## 📋 개요

HongImage는 Coil 라이브러리를 기반으로 한 Android 이미지 위젯입니다. URL, 리소스 ID, Uri 등 다양한 소스로부터 이미지를 로드할 수 있으며, 캐싱, placeholder, error 처리, 로딩 상태 콜백 등 풍부한 기능을 제공합니다. View와 Compose 모두 지원하며, Builder 패턴을 통해 간편하게 설정할 수 있습니다.

## 🏗️ 구조

```
image/
├── HongImageBuilder.kt          # 이미지 빌더 클래스
├── HongImageOption.kt            # 이미지 옵션 데이터 클래스
├── HongImageCompose.kt           # Compose 기반 이미지 위젯
└── HongImageView.kt              # View 기반 이미지 위젯
```

## 📦 주요 클래스

### HongImageBuilder
빌더 패턴을 사용하여 이미지 옵션을 설정하는 클래스입니다. 메서드 체이닝을 통해 직관적으로 이미지 위젯을 구성할 수 있습니다.

### HongImageOption
이미지의 모든 설정값을 담고 있는 데이터 클래스입니다. `HongWidgetCommonOption`을 구현하여 공통 위젯 속성을 상속받습니다.

### HongImageCompose
Jetpack Compose 기반 이미지 위젯입니다. Coil의 `AsyncImage`를 사용하여 비동기 이미지 로딩을 수행합니다.

### HongImageView
View 기반 이미지 위젯입니다. `FrameLayout`을 상속받으며, 내부적으로 `ImageView`와 Coil의 `ImageRequest`를 사용합니다.

## 🚀 사용법

### 기본 사용

```kotlin
// URL로 이미지 로드
val imageOption = HongImageBuilder()
    .imageInfo("https://example.com/image.jpg")
    .width(200)
    .height(200)
    .scaleType(HongScaleType.CENTER_CROP)
    .applyOption()

// View로 사용
val imageView = HongImageView(context).set(imageOption)

// 또는 Compose로 사용
HongImageCompose(imageOption)
```

### Placeholder와 Error 이미지 설정

```kotlin
val imageOption = HongImageBuilder()
    .imageInfo("https://example.com/image.jpg")
    .placeholder(R.drawable.ic_placeholder)
    .error(R.drawable.ic_error)
    .width(200)
    .height(200)
    .applyOption()

HongImageCompose(imageOption)
```

### 원형 이미지 만들기

```kotlin
val imageOption = HongImageBuilder()
    .imageInfo("https://example.com/profile.jpg")
    .width(100)
    .height(100)
    .useShapeCircle(true)
    .border(HongBorderInfo(width = 2f, color = HongColor.GRAY_50.hex))
    .applyOption()

HongImageCompose(imageOption)
```

### 둥근 모서리와 그림자

```kotlin
val imageOption = HongImageBuilder()
    .imageInfo(R.drawable.sample_image)
    .width(300)
    .height(200)
    .radius(HongRadiusInfo(all = 16f))
    .shadow(HongShadowInfo(
        offsetX = 0f,
        offsetY = 4f,
        blurRadius = 8f,
        color = "#33000000"
    ))
    .applyOption()

HongImageCompose(imageOption)
```

### 로딩 상태 콜백

```kotlin
val imageOption = HongImageBuilder()
    .imageInfo("https://example.com/large-image.jpg")
    .onLoading {
        // 로딩 시작 시 처리
        showLoadingIndicator()
    }
    .onSuccess {
        // 로딩 성공 시 처리
        hideLoadingIndicator()
    }
    .onError {
        // 로딩 실패 시 처리
        showErrorMessage()
    }
    .applyOption()

HongImageView(context).set(imageOption)
```

### 캐시 정책 설정

```kotlin
val imageOption = HongImageBuilder()
    .imageInfo("https://example.com/image.jpg")
    .memoryCache(CachePolicy.ENABLED)  // 메모리 캐시 활성화
    .diskCache(CachePolicy.ENABLED)     // 디스크 캐시 활성화
    .applyOption()

HongImageCompose(imageOption)
```

### 이미지 색상 필터

```kotlin
val imageOption = HongImageBuilder()
    .imageInfo(R.drawable.ic_icon)
    .imageColor(HongColor.MAIN_ORANGE_100)  // 또는 .imageColor("#FF6B35")
    .width(48)
    .height(48)
    .applyOption()

HongImageCompose(imageOption)
```

## ⚙️ 주요 메서드

| 메서드 | 설명 |
|--------|------|
| `imageInfo(Any?)` | 이미지 소스 설정 (URL, Uri, Drawable, Bitmap 등) |
| `scaleType(HongScaleType)` | 이미지 스케일 타입 설정 (FIT_START, CENTER_CROP 등) |
| `placeholder(Int?)` | 로딩 중 표시할 이미지 리소스 ID |
| `error(Int?)` | 로딩 실패 시 표시할 이미지 리소스 ID |
| `radius(HongRadiusInfo)` | 이미지 모서리 둥글기 설정 |
| `border(HongBorderInfo)` | 이미지 테두리 설정 |
| `shadow(HongShadowInfo)` | 이미지 그림자 설정 |
| `useShapeCircle(Boolean)` | 원형 이미지 여부 설정 |
| `onLoading(() -> Unit)` | 로딩 시작 시 콜백 |
| `onSuccess(() -> Unit)` | 로딩 성공 시 콜백 |
| `onError(() -> Unit)` | 로딩 실패 시 콜백 |
| `memoryCache(CachePolicy)` | 메모리 캐시 정책 설정 |
| `diskCache(CachePolicy)` | 디스크 캐시 정책 설정 |
| `imageColor(HongColor/String)` | 이미지 색상 필터 설정 |
| `copy(HongImageOption?)` | 기존 옵션을 복사하여 새로운 빌더 생성 |

## 🎨 지원되는 ScaleType

- `FIT_START` - 이미지를 시작 부분에 맞춤
- `FIT_CENTER` - 이미지를 중앙에 맞춤
- `FIT_END` - 이미지를 끝 부분에 맞춤
- `CENTER_CROP` - 이미지를 중앙 기준으로 크롭하여 채움
- `CENTER_INSIDE` - 이미지를 내부에 맞춤

## 📝 참고사항

- Coil 라이브러리 기반으로 고성능 이미지 로딩 제공
- 메모리 캐시와 디스크 캐시를 기본적으로 활성화 (CachePolicy.ENABLED)
- `imageInfo`는 다양한 타입 지원: String (URL), Int (리소스 ID), Uri, Drawable, Bitmap 등
- Compose 버전은 `AsyncImage`를 사용하여 비동기 로딩
- View 버전은 `ImageView`를 내부적으로 사용하며 코너 반경 변환 지원
- `HongWidgetCommonOption`을 구현하여 width, height, margin, padding, click 등의 공통 위젯 속성 사용 가능
- `useShapeCircle`을 true로 설정하면 radius 설정은 무시되고 원형으로 표시됨
- 이미지 색상 필터는 `SRC_IN` 모드를 사용하여 단색 아이콘에 색상을 적용할 때 유용
- Compose 버전은 crossfade 효과를 자동으로 적용 (부드러운 전환)
