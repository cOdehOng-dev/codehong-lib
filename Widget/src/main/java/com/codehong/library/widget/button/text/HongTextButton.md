# 📦 [HongTextButton]

> 텍스트 중심의 커스텀 버튼 컴포넌트.

---

## 🔧 기능 (Features)

- ✅ 텍스트 커스터마이징 (타이포, 컬러, 상태)
- ✅ 배경, 테두리, 반지름, 그림자 설정
- ✅ ENABLED / DISABLED 상태 처리
- ✅ Builder 패턴으로 간편한 구성
- ✅ 공통 인터페이스(HongWidgetCommonOption) 기반

---

## 🛠️ 사용 방법 (Usage)

```kotlin
HongTextButtonBuilder()
    .width(HongLayoutParam.MATCH_PARENT.value)
    .height(48)
    .padding(HongSpacingInfo(top = 8f, bottom = 8f))
    .radius(HongRadiusInfo(all = 10))
    .textOption(
        HongTextBuilder()
            .text("확인")
            .typography(HongTypo.BODY_15_B)
            .color(HongColor.WHITE_100.hex)
            .applyOption()
    )
    .backgroundColor(HongColor.MAIN_ORANGE_100)
    .applyOption()
