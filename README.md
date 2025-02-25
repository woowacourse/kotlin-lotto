# kotlin-lotto

### 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

### step2 추가사항
- [ ] 수동으로 로또를 만들 수 있다
- [ ] 수동으로 만든 로또의 수를 확인할 수 있다
- [ ] 수동과 자동으로 구매한 로또의 수를 구분할 수 있다
- [x] 수동으로 만든 로또의 개수는 구매한 가격으로부터 받은 로또의 개수보다 클 수 없다
- [x] 수동으로 만든 로또의 개수는 0개 이상이어야 한다.

### step1 기능 구현 목록 
- [x] 로또 1장의 가격은 1000원이다
- [x] 구입 금액에 맞는 로또 개수를 계산할 수 있다
- [x] 로또는 1000원 단위로 구입할 수 있다
- [x] 로또 개수에 따라 로또를 여러개 발급할 수 있다
- [x] 구입 금액은 1000원 이상이어야 한다.
- [x] 1000원으로 나누어 떨어지지 않는 구입 금액에 따른 로또 개수를 계산할 수 있다

- [x] 로또는 6개의 숫자를 가진다
- [x] 로또 번호는 1~45 숫자 사이에 해당한다
- [x] 로또 번호는 중복되지 않는다
- [x] 로또 번호는 오름차순으로 출력된다

- [x] 로또 당첨 번호는 1~45 숫자 사이에 해당한다
- [x] 로또 당첨 번호는 6개의 숫자를 가진다
- [x] 로또 당첨 번호는 중복되지 않는다
- [x] 보너스 볼 번호는 1~45 숫자 사이에 해당한다
- [x] 보너스 볼 번호는 당첨 번호와 중복되지 않는다

- [x] 구입 금액은 정수 형태로 입력되어야 한다
- [x] 당첨 번호는 정수 형태로 입력되어야 한다
- [x] 당첨 번호 입력 시에는 ,로 구분되어야 한다
- [x] 당첨 번호는 공백이 불가하다
- [x] 보너스 볼 번호는 공백이 불가하다
- [x] 보너스 볼 번호는 정수 형태로 입력되어야 한다

- [x] 구입 금액을 입력할 수 있다
- [x] 금액에 따라 구매 개수를 계산할 수 있다
- [x] 금액에 따라 구매 개수를 출력할 수 있다
- [x] 로또를 구매 개수만큼 자동 생성할 수 있다
- [x] 자동 생성된 로또를 구입 개수만큼 출력할 수 있다

- [x] 당첨 번호를 입력할 수 있다
- [x] 당첨 번호는 , 으로 구분 할 수 있다.
- [x] 보너스 볼 번호를 입력할 수 있다

- [x] 자동 생성된 로또와 당첨 번호를 비교할 수 있다
- [x] 자동 생성된 로또와 보너스 번호를 비교할 수 있다

- [x] 당첨 통계를 출력한다
- [x] 당첨 통계는 일치하는 번호의 개수에 맞게 차례로 출력한다
- [x] 당첨 기준에 띠라 당첨 여부가 결정된다
- [x] 수익률을 계산할 수 있다.