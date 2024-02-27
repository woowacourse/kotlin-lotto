# 프로젝트 전체
- [ ] : lotto 상위 package 추가... 
- [ ] : 한 메서드에 오직 한 단계의 들여쓰기만 한다.
- [ ] : 함수의 길이가 15라인을 넘어가지 않도록 구현한다
## View
- [x] : 수동으로 구매할 로또 수 입력 받기(input) - n
- [x] : 수동으로 구매할 번호들을 입력(input) - List<List<Int>> 반환
- [x] : lotto 구매 구매 현황 수동 + 자동 현황 View 추가 (output)

## Controller
- [ ] : 수동 구매할 로또 수  
- [x] : purchaseExpense Int -> Money로 수정

## Model
- [ ] : 테스트 코드 상수화 (코드 리뷰) - 일단 보류
- [ ] : 랜덤값도 테스트 해보자! (코드 리뷰)
- [x] : Money - times operator 함수 만들 도록 하자
- [x] : Money - compareTo operator 함수 구현
- [X] : List<Int> -> Lotto 팩토리 메서드 함수 추가
- [ ] : 수동 로또 생성기 추가 구현
- [ ] : 수동 로또 만드는 부분 (구매 금액 >= 수동 로또 수 * 로또 가격) 누구에게 책임을 묻지? - 수동 로또 생성기가 하는게 맞음
- [x] : 자동 로또 생성기 네이밍 변경 (LottieGenerator -> AutoLottieGenerator)
- [ ] : input : 로또 구매 금액 -> output : 구매할 수 있는 로또 count 를 반환하는 객체를 만들자

# PR에 적을 질문 내용
기존 코드를 고치기보다는 새로운 코드를 작성하여 리팩토링하는 방식으로 진행했습니다!
이에 대한 이유는 로또 미션같은 경우는 작은 sample 프로젝트라 전부 고치는 방향으로도 리팩토링할 수 있지만,  
복잡한 프로젝트 경우, 여러 가지 class 들이 커플링 돼있을 가능성이 높아 기존 코드를 고칠 시 예상치 못한 사이드 이펙트가 발생할 수 있습니다.  
따라서, 이에 대해 대처하는 연습을 하고자 `기존 코드를 고치기보다는 새로운 코드를 작성` 하는 방식으로 리팩토링을 진행했습니다!

## LottoTest 자주 사용하는 값 상수화
> Lotto(1, 2, 3, 4, 5, 6)의 경우 테스트에서 많이 사용되고 있는데, 
> 이를 재사용하기 위해서 DEFAULT_LOTTO = Lotto(1, 2, 3, 4, 5, 6) 등으로 작성해보시는 것도 고민해보면 좋을 것 같습니다!💪

DEFUALT_LOTTO를 상수화 하여 반영을 하려 하던 중, 의문이 생겨 질문을 드립니다!! 🙋‍   

```kotlin
val DEFAULT_LOTTO = Lotto(1, 2, 3, 4, 5, 6)
```
위와 같이 자주 사용하는 Lotto 객체의 경우 상수화를 사용하면 test를 작성하는 사람 입장에서는 편하지만,  
test를 읽는 사람이 한 번 더 생각하게 만들어, 테스트 코드에 온전한 집중을 못하게 만들 수 있다고 생각합니다!!
```kotlin
@Test
fun `로또는 다른 로또와 일치하는 로또 넘버 개수를 반환할 수 있다`() {
    // 이 테스트를 처음 보는 입장에서는 DEFAULT_LOTTO 값을 알기 위해 
    // companion object 를 한 번 더 봐야한다는 번거로움이 있을 것 같습니다.
    val lotto = DEFAULT_LOTTO
    val lotto2 = Lotto(2, 3, 4, 5, 6, 7)
}
```
아래와 같은 경우는 minus 라는 상수를 쓰는 것이 더 readable 한 것 같습니다.
```kotlin
@Test
fun `Money는 음수일 수 없다`() {
    assertThrows<IllegalArgumentException> {
        Money(MINUS_MONEY_AMOUNT)
    }
}
```

그러나, 테스트 코드가 코드의 신뢰성을 주는 역할도 하지만 `설명서` 역할도 한다고 생각합니다.  
따라서, 테스트 코드를 보는 사람이 이해하기 쉽게 작성하는 것이 중요하다고 생각하기 때문에
자주 반복되더라도 readable 하지 않다면 상수화하는 것을 꺼립니다.  

이에 대한 리뷰어님의 의견을 듣고 싶습니다. 🙇‍

## 구체 클래스에 대한 추상화에 대한 고찰..
오늘 수업 시간에서도 배웠지만, 현 시점에서 객체 지향 프로그래밍에서 `확장성` 을 고려하지 않고 개발할 수 없습니다.  
따라서, 보통 생성자를 경계를 두고 interface 나 abstract class 타입으로 받아 시스템을 유연하게 설계할 수 있도록 합니다.  
```kotlin
class LottoGameController(
    private val inputView: LottoGameInputView = ConsoleLottoGameInputView(),
    private val outputView: LottoGameOutputView = ConsoleLottoGameOutputView(),
    private val lottieGenerator: AutoLottieGenerator = AutoLottieGenerator { ... },
) 
```
그러나, 모든 구체 클래스를 interface 나 abstract class 를 상속하지 않는 이상 모든 확장에 대처할 수 없다고 생각합니다.  
`변동 가능성이 높은` 것들을 위주로 추상화하고, `고정될 가능성이 높은` 것들은 구체 클래스로 구현하는 방식으로 개발을 하려 노력하지만
그 기준을 판별하는 역량이 많이 부족하다고 생각합니다..  

예시로, Lotto, LottoNumber, LottoGenerator 등등.. 모두 변동 가능성이 있는 것으로 판단이 되어 싹 다 추상화를 해야하나..? 라는 생각이 듭니다.  

혹시, 이에 대한 역량을 기르기 위해 추천해주실 docs 나 책이 있을까요?! 
