# kotlin-lotto

## 기능 목록

### LottoNumber
- 번호는 1이상 45이하여야한다.

### InputView
- 구입 금액을 입력받는다.
- 당첨 번호를 입력받는다.
- 보너스 볼 번호를 입력받는다.

### ResultView
- 발행된 로또를 출력한다.
- 당첨통계 및 수익률을 출력한다.

### Lotto (6개의 번호를 가진다)
- 로또 번호는 6개여야한다.
- 번호는 서로 중복될 수 없다.
- 자신의 당첨 결과를 확인한다.

### LottoGenerator
- 당첨 로또를 발행한다.
- 구입 금액만큼 로또를 발행한다.
- 구입 금액은 1000원 단위여야한다.

### PurchaseMoney (구입 금액을 가진다)
- 금액에 대한 검증을 수행한다.
  - 구입 금액은 0원보다 커야한다.

### NumericValidator
- 입력이 숫자 형태의 스트링인지 검증한다.

### WinningNumbers
- 로또(winning Lotto)와 로또 번호(LottoNumber) 하나를 가진다