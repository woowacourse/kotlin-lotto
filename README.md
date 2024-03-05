# kotlin-lotto

## 기능 요구사항

로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.  
로또 1장의 가격은 1000원이다.
사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.

## 기능 목록

### LotteryNumber

- [x] 로또 번호 한개를 저장한다
- [x] 1 이상 45 이하의 숫자여야 한다. `MIN_LOTTERY_NUMBER = 1`, `MAX_LOTTERY_NUMBER = 45`
- [x] 45 개의 로또 번호는 NUMBERS 에 미리 생성하여 저장하고 있다.(캐싱)
- [x] Int 값으로 캐싱된 로또 번호를 생성한다.

### Lottery

- [x] 로또 번호 6개를 저장한다. `LOTTERY_NUMBER_COUNT = 6`
- [x] 중복된 번호가 없어야 한다
- [x] NumbersStrategy(숫자 전략)으로 로또를 생성한다.
- [x] 다른 로또와 비교하여 일치하는 번호의 개수를 센다 `compareLottery(Lottery)`
- [x] 보너스 번호를 포함하고 있는지 여부를 알아낸다. `contains(LotteryNumber)`

### NumbersStrategy

- [x] 번호들을 생성하는 전략을 이 인터페이스의 구현체에 구현한다.
- [x] 번호들은 List<Int> 리턴된다: `generate(): List<Int>`

### ExplicitNumbersStrategy

- [x] 주 생성자에 명시한 정수 리스트를 오름차순으로 정렬하여 생성한다.

### RandomNumberStrategy

- [x] 1 이상 45 이하의 범위의 6개의 숫자를 랜덤으로 뽑아서 오름차순으로 정렬하여 생성한다.
> 이 전략은 범위에 대해 `LotteryNumber` 에 의존하고 있고, 숫자의 수량에 대해 `Lottery` 에 의존하고 있다.
만약 `LotteryNumber` 에서 범위를 바꾸거나, `Lottery` 에서 수량을 변경하면 이 전략에 영향을 미친다는 것을 유의하세요

### Lotteries

- [x] 로또 여러 장을 저장한다.
- [x] 로또 여러 장끼리의 덧셈 연산을 구현한다: `plus`
- [x] 로또 여러 장으로부터 당첨 결과를 얻는다: `evaluateWinning`
- [x] 최상위 확장 함수로, `Int` 값을 `Quantity`(양)으로 변환한다: `Int.toQuantity()`

### Money

- [x] 금액을 저장한다.
- [x] 금액은 0 이상이어야 한다
- [x] Money 간의 사칙 연산과 나머지 연산을 할 수 있다.
- [x] Money 간의 대소 비교를 할 수 있다.
- [x] Int 값과 Long 값을 통해 Money 를 생성한다.

### LotterySeller

- [x] 금액을 받아서 로또 구매 수량을 알려준다 `getLotteryQuantity`
- [x] 로또 한 장의 가격은 `1,000` 원이며, 최대 `100,000` 원까지 구매할 수 있다.
- [x] 1,000 원 이하의 금액은 에러 메시지를 띄운다. (`MIN_PRICE_AMOUNT = 1_000`)
- [x] 100,000원 초과로 구매시 예외를 던진다. (국내 로또 구매 기준 `MAX_PRICE_AMOUNT = 100_000`)

> 만약 로또 최대 구매 가능 금액에 대한 제한이 풀린다면, `Money` 와 `LotterySeller` 를 변경해야 합니다.
`LotterySeller` 는 최대 구매 가능 금액을 알고 있으며 `Money` 의 프로퍼티인 `Long` 타입도 큰 범위로 변경되어야 합니다.

### WinningRank

- [x] 순위에 따른 로또와 당첨 번호 사이 일치하는 숫자 개수와 당첨금을 저장한다.
- [x] 로또와 당첨 번호 사이 일치하는 숫자 개수와 보너스 번호 일치 여부를 통해 당첨 순위를 계산한다
- 
> 만약 로또 1등 당첨 금액이 현재 수준보다 훨씬 더 커진다면, `Money` 를 변경해야 할 수도 있습니다.
Money 의 현재 타입 Long 이 미래에 증가된 당첨 금액을 충분히 저장하지 못할 수 있습니다.

### WinningResult

- [x] 당첨된 등수(`WinningRank`)와 그 갯수(`Quantity`)를 저장한다.
- [x] 총 당첨근을 계산한다: `calculateProfitRate(Money)

### ProfitRate

- [x] 수익률을 저장한다: `rate: Double`
- [x] 수익률은 '총 당첨 금액/지불 금액' 으로 계산된다.
- [x] 수익률이 1 초과이면 이득, 1이면 본전, 1 미만이면 손해이다.
- [x] 수익 상태를 구한다: `decideProfitStatus`

### ProfitStatus

- [x] 수익 상태에 대한 문자열을 저장한다 ("이득", "손해", "본전")

### Quantity

- [x] 수량을 저장한다: `count: Int`
- [x] 수량은 0 이상이어야 한다.
- [x] 수량끼리의 덧셈, 뺄셈 연산을 할 수 있다.

### InputView

- [x] 구입 금액을 입력받는다.
- [x] 수동으로 구매할 로또 수량을 입력받는다.
- [x] 수동으로 구매할 로또 번호를 입력받는다.
- [x] 지난 주 로또의 당첨 번호와 보너스 번호를 입력받는다.

### OutputView

- [x] 구매한 로또 장 수를 출력한다.
- [x] 로또의 수동의 개수와 자동의 개수를 출력한다.
- [x] 구매한 로또의 번호들을 출력한다.
- [x] 당첨 통계를 출력한다.
