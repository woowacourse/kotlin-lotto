# kotlin-lotto

## 기능 목록

### LottoNumber
-[x] 번호는 1이상 45이하여야한다.

### InputView
- [x] 구입 금액을 입력받는다.
- [x] 수동으로 입력받을 로또의 개수를 입력받는다.
- [x] 로또를 수동으로 입력받는다.
- [x] 당첨 로또를 입력받는다.
- [x] 보너스 볼 번호를 입력받는다.

### ResultView
-[x] 발행된 로또를 출력한다.
-[x] 당첨통계 및 수익률을 출력한다.

### Lotto (6개의 번호를 가진다)
-[x] 로또 번호는 6개여야한다.
-[x] 번호는 서로 중복될 수 없다.
-[x] 자신의 당첨 결과를 확인한다.

### PurchasedLottos
- [x] 구입한 로또들의 당첨 결과를 종합한다.

### LottoSeller
- [x] 구입 금액만큼 로또를 발행한다.

### LottoCustomer
- [x] 구입 금액으로 살 수 있는 로또보다 수동으로 발행받을 로또가 많을 수 없다.
- [x] 자동으로 발급받을 로또의 개수를 구할 수 있다.

### ManualLottoGenerator
- [x] 로또를 수동으로 발행할 수 있다.

### AutomaticLottoGenerator
- [x] 로도를 자동으로 발행할 수 있다.

### PurchaseMoney (구입 금액을 가진다)
- [x] 금액에 대한 검증을 수행한다.
  - [x] 구입 금액은 0원보다 커야한다.
  - [x] 구입 금액은 1000원 단위여야한다.

### NumericValidator
- [x] 입력이 숫자 형태의 스트링인지 검증한다.

### WinningLotto
- [x] 로또(catch Lotto)와 로또 번호(LottoNumber) 하나를 가진다
- [x] catch Lotto에 포함된 숫자는 보너스 번호가 될 수 없다.
