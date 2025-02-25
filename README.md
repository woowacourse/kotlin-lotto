# kotlin-lotto

로또를 구매하고 당첨금 및 수익률을 확인할 수 있는 프로그램

## 기능 요구 사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 구현할 기능 목록

- [x] 사용자는 구입 금액을 입력한다.
- [x] 사용자는 수동으로 구매할 로또의 개수를 입력한다.
  - [x] 수동으로 구매할 로또의 총 금액은 구입 금액을 초과할 수 없다.
  - [x] 수동으로 구매할 로또의 개수는 0 미만일 수 없다.
- [x] 사용자는 수동으로 구매할 로또의 번호를 입력한다.
- [x] 수동으로 입력된 번호에 따라 로또를 생성한다.
- [x] 구입 금액만큼 로또를 생성한다.
- [x] 로또는 1부터 45중 6개의 숫자를 갖고 있다.
    - [x] 출력할 때는 정렬하여 출력한다.
    - [x] 로또 번호가 중복되면 안 된다.
- [x] 사용자는 지난 주 당첨 번호를 입력한다.
    - [x] 당첨 번호는 쉼표로 구분된다.
    - [x] 당첨 번호는 총 6개를 입력해야 한다.
    - [x] 당첨 번호는 1부터 45 사이의 숫자여야 한다.
- [x] 사용자는 보너스 볼 번호를 입력한다.
    - [x] 보너스 번호는 1부터 45 사이의 숫자여야 한다.
    - [x] 보너스 번호는 당첨 번호와 달라야 한다.
- [x] 지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다.
- [x] 구입 금액과 당첨금을 통해 수익률을 계산한다.
    - [x] 손해인지 이득인지 출력한다.

## 세부 구현

### class `LottoNumber`

- 로또 번호를 보관할 `lottoNumber: Int`를 가짐

### class `Lotto`

- 로또 번호들을 보관할 프로퍼티 `numbers: Set<Int>`를 가짐
- 로또 한 장의 가격을 나타내는 상수를 가짐
- 로또 번호의 개수를 나타내는 상수를 가짐
- 로또 번호의 개수가 유효함을 검증함

### class `Lottos`

- 구매한 로또들을 보관할 프로퍼티 `List<Lotto>`를 가짐
- 로또가 적어도 한 개 있음을 검증함

### class `WinningLotto`

- 당첨 로또를 보관할 `lotto: Lotto`를 가짐
- 보너스 번호를 보관할 `bonusNumber: LottoNumber`를 가짐

### enum `LottoResult`

- 로또 결과(1등, 2등, 3등, 4등, 5등, 낙첨)에 대한 상수 모음 
  - 당첨 금액, 보너스 번호 일치 여부 및 일치하는 번호의 개수를 가짐
- 정적 함수 `from(wonLotto: Lotto, boughtLotto: Lotto): LottoResult`

### class `LottoResults`

- 로또 결과들을 보관할 프로퍼티 `List<LottoResult>`를 가짐
