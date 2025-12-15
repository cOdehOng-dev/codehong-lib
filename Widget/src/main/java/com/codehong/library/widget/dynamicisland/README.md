# 다이나믹 아일랜드

iOS의 Dynamic Island를 모티프로 한 안드로이드 오버레이 알림 위젯입니다. 항공편 및 숙박 예약 정보를 시각적으로 표시하고, 사용자가 탭하거나 길게 눌러 상세 정보를 확인할 수 있습니다.

## 주요 기능

- **오버레이 알림**: 화면 상단에 떠 있는 알림 형태로 표시
- **확장/축소 애니메이션**: 탭하거나 길게 눌러 상세 정보 보기
- **자동 관리**: 설정된 시간에 자동으로 표시되고 사라짐
- **다양한 타입 지원**: 항공편(AIR), 숙박(LODGING), 기타(ETC)
- **실시간 카운트다운**: 출발/체크인 시간까지 남은 시간 표시
- **딥링크 연동**: 알림 클릭 시 관련 앱으로 이동

## 아키텍처

### 핵심 컴포넌트

1. **DynamicIslandService**: 오버레이 윈도우를 관리하는 메인 서비스
2. **DynamicIslandManager**: 권한 체크 및 서비스 스케줄링 관리
3. **DynamicIslandScreen**: Jetpack Compose로 구현된 UI 컴포넌트
4. **DynamicIslandInfo**: 알림 정보를 담는 데이터 클래스
5. **DynamicIslandType**: 알림 타입(항공편/숙박) 정의
6. **DynamicIslandConst**: 상수 정의

### 동작 원리

```
1. DynamicIslandManager.schedule() 호출
   ↓
2. DynamicIslandService 시작
   ↓
3. WindowManager를 통해 오버레이 뷰 표시
   ↓
4. Handler로 1초마다 시간 업데이트
   ↓
5. 표시 종료 시간(시작 시간 + 10분) 도달 시 자동 종료
```

## 사용 방법

### 1. 권한 확인 및 요청

오버레이 권한(`SYSTEM_ALERT_WINDOW`)이 필요합니다.

```kotlin
val launcher = rememberLauncherForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { /* 권한 결과 처리 */ }

// 권한 확인 및 요청
DynamicIslandManager.checkPermission(
    context = context,
    launcher = launcher,
    granted = {
        // 권한이 허용된 경우 실행할 코드
    }
)
```

### 2. 알림 스케줄링

```kotlin
val info = DynamicIslandInfo(
    type = DynamicIslandType.AIR.type,
    appName = "항공사 앱",
    fromCity = "서울/인천",
    toCity = "뉴욕",
    thumbnailUrl = "https://example.com/image.jpg",
    startDate = "20250115143000", // yyyyMMddHHmmss
    endDate = "20250115180000",
    link = "myapp://flight/12345"
)

DynamicIslandManager.schedule(
    context = context,
    info = info
)
```

### 3. 알림 업데이트

실행 중인 알림 정보를 업데이트할 수 있습니다.

```kotlin
DynamicIslandManager.reset(updatedInfo)
```

### 4. 실행 상태 확인

```kotlin
val isRunning = DynamicIslandManager.isRunning()
```

## API 레퍼런스

### DynamicIslandInfo

알림에 표시할 정보를 담는 데이터 클래스입니다.

| 필드 | 타입 | 설명 |
|------|------|------|
| `type` | Int | 알림 타입 (1: 숙박, 2: 항공편, 3: 기타) |
| `appName` | String? | 앱 이름 |
| `fromCity` | String? | 출발지 (항공편) 또는 숙소 정보 |
| `toCity` | String? | 도착지 (항공편) |
| `thumbnailUrl` | String? | 썸네일 이미지 URL |
| `startDate` | String? | 시작 시간 (yyyyMMddHHmmss) |
| `endDate` | String? | 종료 시간 (yyyyMMddHHmmss) |
| `link` | String? | 딥링크 URL |

**자동 계산 속성:**
- `dispStartTime`: 시작 시간 2시간 전부터 표시
- `dispEndTime`: 시작 시간 10분 후까지 표시

### DynamicIslandType

```kotlin
enum class DynamicIslandType(val type: Int) {
    LODGING(1),  // 숙박
    AIR(2),      // 항공편
    ETC(3)       // 기타
}
```

**확장 함수:**
- `Int.toType()`: Int 값을 DynamicIslandType으로 변환
- `DynamicIslandType.toStateMessage(diffMillis: Long, isSmall: Boolean)`: 남은 시간에 따른 상태 메시지 생성

### DynamicIslandManager

| 메서드 | 설명 |
|--------|------|
| `isRunning()` | 서비스 실행 여부 확인 |
| `isGranted(context)` | 오버레이 권한 허용 여부 확인 |
| `checkPermission(context, launcher, granted)` | 권한 확인 및 요청 |
| `schedule(context, info)` | 알림 스케줄링 |
| `reset(info)` | 실행 중인 알림 정보 업데이트 |
| `setPermission(context, isChecked, launcher, start)` | 권한 설정 토글 |

## 상태 메시지

알림은 남은 시간에 따라 다른 메시지를 표시합니다.

### 항공편 (AIR)
- **1~60분 남음**:
  - 작은 뷰: "탑승 전"
  - 확장 뷰: "X분 뒤 탑승 예정"
- **1분 미만**:
  - 작은 뷰: "탑승 준비"
  - 확장 뷰: "잠시 후 탑승 예정"
- **시작 시간**:
  - 작은 뷰: "탑승"
  - 확장 뷰: "지금 탑승하세요!"

### 숙박 (LODGING)
- **1~60분 남음**:
  - 작은 뷰: "체크인 전"
  - 확장 뷰: "X분 뒤 체크인 예정"
- **1분 미만**:
  - 작은 뷰: "체크인 준비"
  - 확장 뷰: "잠시 후 체크인 예정"
- **시작 시간**:
  - 작은 뷰: "체크인"
  - 확장 뷰: "지금 체크인하세요!"

## UI 인터랙션

### 작은 뷰 (SmallIslandView)
- **탭**: 딥링크 실행
- **길게 누르기**: 확장 뷰로 전환

### 확장 뷰 (ExpandedIslandView)
- **탭**: 확장 뷰 닫기 + 딥링크 실행
- **배경 탭**: 확장 뷰 닫기

## 애니메이션

- **코너 반경**: 30dp (작은 뷰) ↔ 35dp (확장 뷰)
  - Spring 애니메이션 (Low Bouncy, Medium-Low Stiffness)
- **컨텐츠 전환**: Fade In/Out + Size Transform
  - 300ms 페이드 애니메이션
  - Spring 기반 크기 전환 (dampingRatio: 0.75f, stiffness: 300f)
- **윈도우 크기 조정**: 400ms 지연 후 적용

## 기술 스택

- **UI**: Jetpack Compose
- **이미지 로딩**: Coil
- **라이프사이클**: SavedStateRegistryOwner, ViewModelStoreOwner
- **윈도우 관리**: WindowManager with TYPE_APPLICATION_OVERLAY
- **애니메이션**: Compose AnimatedContent, animateDpAsState

## 요구사항

- **최소 SDK**: Android 6.0 (API 23) - TYPE_APPLICATION_OVERLAY 지원
- **권한**: `SYSTEM_ALERT_WINDOW` (오버레이 표시)
- **매니페스트 등록**:
  ```xml
  <service android:name="com.codehong.library.widget.dynamicisland.DynamicIslandService" />
  ```

## 주의사항

1. **권한 체크 필수**: 오버레이 권한이 없으면 서비스가 실행되지 않습니다.
2. **자동 종료**: 시작 시간 + 10분 후 자동으로 종료됩니다.
3. **싱글톤 서비스**: 동시에 하나의 알림만 표시됩니다. 새로운 알림을 스케줄링하면 기존 알림이 업데이트됩니다.
4. **메모리 관리**: WeakReference를 사용하여 메모리 누수를 방지합니다.
5. **백그라운드 제한**: Android 8.0 이상에서는 백그라운드 서비스 제한이 있을 수 있습니다.

## 예제

전체 예제는 샘플 앱의 `SampleDynamicIslandActivity`를 참고하세요.

```kotlin
class MainActivity : ComponentActivity() {
    private val overlayPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (DynamicIslandManager.isGranted(this)) {
            scheduleNotification()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = { requestPermissionAndSchedule() }) {
                Text("알림 표시")
            }
        }
    }

    private fun requestPermissionAndSchedule() {
        DynamicIslandManager.checkPermission(
            context = this,
            launcher = overlayPermissionLauncher,
            granted = { scheduleNotification() }
        )
    }

    private fun scheduleNotification() {
        val info = DynamicIslandInfo(
            type = DynamicIslandType.AIR.type,
            fromCity = "서울/인천",
            toCity = "제주",
            startDate = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
                .format(Date(System.currentTimeMillis() + 3600000)), // 1시간 후
            link = "myapp://detail"
        )
        DynamicIslandManager.schedule(this, info)
    }
}
```
