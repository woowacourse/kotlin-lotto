# kotlin-lotto

## 기능 요구 사항

로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
로또 1장의 가격은 1000원이다.

## 기능 목록

- [x] 로또 번호는 1과 45사이이다.
- [x] 로또 한장은 6개의 로또 번호를 가지고 있다.
- [x] 6개의 로또 번호는 중복되면 안된다.
- [x] 로또 구매 개수만큼 로또를 발행한다.
- [x] 로또 당첨을 판단한다.
- [ ] 수익률을 판단한다
- [ ] 구입 금액을 전달하면 로또의 장수를 반환한다.
- [ ] 보너스번호, 입력된 당첨 번호가 1과 45사이인지 판단한다.
- [ ] 보너스 번호는 입력된 당첨 번호에 포함되지 않는다.

# 세부 기능 구현

- [ ] 입출력 구현 InputView, ResultView
- [ ] 구입금액이 빈값이면 에러를 출력한다.
- [ ] 구입금액이 Int타입이 아니면 에러를 출력한다.
- [ ] 구입금액이 0과 음수면 에러를 출력한다.
- [ ] 구입금액이 1000원 단위로 입력되었는지 판단한다.