# 수정 사항


## InputView
- [ ] : InputView 에 간단한 mapping 작업에 대한 책임 추가

## Error  

- [ ] : 에러 처리 리팩토링

## Domain  
- [ ] : DefaultManualLottieGeneratorTest.kt 테스트명 네이밍 수정  
- [ ] : 스스로 기준을 정해서 랜덤 테스트 해보기!
- [ ] : LottoCount 에러 메세지 상수화하기

## Comment 달 것!

## 1) Readable 한 코드 
> 오둥이가 생각하시는 readble 한 코드는 어떤것인지 궁금합니다!!🤔  

저는 작성한 코드의 의도가 클러이언트에게 쉽게 잘 전달되는 코드가 readble 한 코드라 생각합니다!  
여기서 `의도가 잘 전달되는 코드` 는 함수 시그니처 혹은 Class 명 만 보고도 `어떤 동작을 할지 예측이 쉬운 코드`입니다.  

 예시로는 저는 `get` 이라는 prefix 를 지양하고 있습니다.  
개인적으로 `getXXX()` 라는 함수 네이밍보다는 `fetchXXX()`, `loadXXX()`, `provideXXX()`, `createXXX()` 와 같이  
데이터를 새로 만들어서 제공해주는 것인지, 이미 만들어진 것을 불러오는 것인지, 누군가에게 받아서 전달해주는 것인지 에 따라  
함수 네이밍을 하여 함수 내부 구현체를 들어가지 않고 함수 네이밍만 봐도 제 코드의 의도를 쉽게 파악할 수 있도록 노력하는 것 같습니다.  

그러나, 만약 팀 컨벤션이나 팀원이 `getXXX()` 를 쓰자고 강력하게 주장한다면 이에 따르는 편입니다.😃   
```kotlin  
// Lotto.kt
fun getMatchCount(other: Lotto): Int
```


