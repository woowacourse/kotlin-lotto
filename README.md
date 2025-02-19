# kotlin-lotto

### 기능 요구 사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
- 로또 하나에 포함되는 숫자는 중복되지 않은 6개의 숫자다.
- 당첨 번호는 1 ~ 45 사이의 범위다.
- 보너스 번호는 당첨번호와 중복되지 않은 숫자다.
- 구입한 로또들에서 당첨 현황에 대한 통계를 출력해야 한다.(수익률 포함)

### 기능 목록

- 로또
    - [x] 로또 하나의 숫자는 중복되지 않은 6개의 숫자이다.
    - [x] 로또 한 장의 금액은 1,000원이다.
- 당첨 번호
    - [x] 당첨 번호는 1 ~ 45 사이의 6개의 중복되지 않은 숫자다.
- 보너스 번호
    - [x] 보너스 번호는 1 ~ 45 사이의 1개의 당첨 번호와 중복되지 않은 숫자다.
- 당첨 로또
    - [x] 당첨 로또는 당첨번호와 보너스 번호를 갖는다.
- 등수
    - [x] 각 등수에 대한 상금과 당첨 개수와 보너스 번호 일치 여부를 가진다.
    - [x] 맞춘 개수와 보너스 일치 여부에 따라 등수를 판단한다.
- 로또 구입 금액
    - [x] 천 원 단위로 입력한다.
    - [x] 최소 금액은 1,000으로 제한한다.
    - [x] 최대 금액은 100,000원으로 제한한다.
    - [x] 구입 금액으로 구매할 수 있는 로또 개수를 반환한다.
- 로또 생성기
    - [ ] 로또 생성기에서 중복되지 않은 6개의 숫자를 뽑아 로또를 생성한다.
- 입력
    - [ ] 로또 구입 금액을 입력한다.
        - [ ] 숫자만 입력 가능하다
    - [ ] 로또 당첨 번호 6개를 입력한다.
        - [ ] 숫자만 입력 가능하다
        - [ ] 선택한 번호는 쉼표(,) 로 구분한다.
    - [ ] 로또 보너스 번호를 입력한다.
        - [ ] 숫자만 입력 가능하다
- 출력
    - [ ] 발행한 로또 번호를 출력한다.
    - [ ] 구입한 로또에 대한 당첨 통계를 출력한다.
    - [ ] 구입한 로또에 대한 수익률을 출력한다.