# kotlin-lotto

## 기능 요구사항

로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.  
로또 1장의 가격은 1000원이다.
사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.

## 기능 목록

- [x] 로또 번호 한개를 저장한다: LotteryNumber
    - 1 이상 45 이하의 숫자여야 한다.
    - 문자열로부터 번호를 생성한다: `from` 메서드

- [x] 로또 번호 6개만을 저장한다: Lottery
    - 중복된 번호가 없어야 한다.
    - Int 타입 가변인자로 Lottery 를 생성한다: `of`
    - 문자열로부터 로또를 생성한다: `from`
    - 다른 로또와 비교하여 일치하는 번호의 개수를 센다: `compareLottery`
    - 어떤 로또 번호를 포함하는지 여부를 구한다: `contains`

- [x] 로또 여러 장을 저장한다: Lotteries
    - 로또 여러 장끼리의 덧셈 연산을 구현한다: `plus`
    - 로또 여러 장으로부터 당첨 결과를 얻는다: `evaluate`
- [x] 당첨 로또 번호와 보너스 번호를 저장한다: `WinningLottery`
    - 당첨 로또 번호는 보너스 번호를 포함하면 안된다
    - 당첨 순위를 계산한다: `evaluateWinningRank`

- [x] 금액을 저장한다: Money
    - Money 간의 사칙 연산과 나머지 연산을 구현한다.
    - Money 간의 대소 비교 기능을 구현한다.
    - 총 당첨 상금을 통해 수익률을 계산한다: `calculateProfitRate`


- [x] 금액을 받아서 로또 구매 수량을 알려준다: LotterySeller: `getLotteryQuantity`
    - 1,000 원 이하의 금액은 에러 메시지를 띄운다.
    - 100,000원 초과로 구매시 예외를 던진다. (국내 로또 구매 기준)

- [x] 로또 당첨 통계를 상수화 하는 enum class: WinningRank
    - 매치 수량과 보너스 번호 매치 여부를 가지고 당첨 순위를 만든다: `of`

- [x] 로또 번호를 생성한다. LotteryMachine
    - 로또를 랜덤으로 생성한다. `generateRandomLottery`
    - 로또를 수동으로 생성한다. `generateRandomLottery`

- [x] 당첨된 등수(WinningRank)와 그 갯수(Int)를 저장한다. WinningResult

- [x] 로또들에 대해 당첨 번호와 보너스 번호를 비교하여 최종 당첨 결과를 구한다: LotteryResultEvaluator
    - Lottery 의 개수만큼 LotteryRankEvaluator 의 evaluate 메서드를 실행한다.
    - WinningResult(Map<WinningRank, 개수>) 를 리턴한다.

- [x] 소수 수익률을 가지고 있다: ProfitRate

- [x] 이득, 손해, 본전의 수익 상태를 가진다: ProfitStatus
    - 구매 금액과 당첨 상금을 통해 수익 상태(이득)을 구한다.

- [x] 구입 금액을 입력받는다: InputView
- [x] 수동으로 구매할 로또 수량을 입력받는다.
- [x] 수동으로 구매할 로또 번호를 입력받는다.

- [x] 지난 주 로또의 당첨 번호와 보너스 번호를 입력받는다.

- [x] 구매한 로또 장 수를 출력한다: OutputView
- [x] 로또의 수동의 개수와 자동의 개수를 출력한다.
- [x] 구매한 로또의 번호들을 출력한다.

- [x] 당첨 통계를 출력한다.
