# 기능 요구 사항
- [ ] : 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- [ ] : 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.

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
- [ ] : 수동 로또 만드는 부분 (구매 금액 >= 수동 로또 수 * 로또 가격) 누구에게 책임을 묻지?
- [ ] : 수동 로또 생성

# PR에 적을 질문 내용

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
