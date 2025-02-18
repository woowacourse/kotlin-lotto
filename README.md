# kotlin-lotto

제품 설명?

## 기능 요구 사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 구현할 기능 목록

- [ ] 사용자는 구입 금액을 입력한다.
- [x] 구입금액만큼 로또를 생성한다.
- [x] 로또는 1부터 45중 6개의 숫자를 갖고 있다.
    - [ ] 출력할 때는 정렬하여 출력한다.
    - [x] 로또 번호가 중복되면 안 된다.
- [ ] 사용자는 지난 주 당첨 번호를 입력한다.
    - [ ] 당첨 번호는 쉼표로 구분된다.
    - [x] 당첨 번호는 총 6개를 입력해야 한다.
    - [x] 당첨 번호는 1부터 45 사이의 숫자여야 한다.
- [ ] 사용자는 보너스 볼 번호를 입력한다.
    - [x] 보너스 번호는 1부터 45 사이의 숫자여야 한다.
    - [x] 보너스 번호는 당첨 번호와 달라야 한다.
- [x] 지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다.
- [ ] 구입 금액과 당첨금을 통해 수익률을 계산한다.
    - [ ] 손해인지 이득인지 출력한다.

## 세부 구현

### class `Lotto`

- 프로퍼티 `Set<Int>`을 가짐
- 상수 로또 1장 가격(1000)

### class `Lottos`

- 프로퍼티 `Set<Lotto>`을 가짐
- 정적 함수 `buy(amount: Int): Lottos` 정의

### enum `LottoResult`

- 1등, 2등, 3등, 4등, 5등, 낙첨에 대한 상수 모음
    - 각 상수에는 당첨금액 프로퍼티가 있어야 함.
- 정적 함수 `from(wonLotto: Lotto, boughtLotto: Lotto): LottoResult`

### class `WinningLotto`

- 프로퍼티 `Lotto`을 가짐
- 프로퍼티 `bonusNumber: Int`를 가짐

