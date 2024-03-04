# kotlin-lotto

# 기능 목록

- 비즈니스 로직
    - [x] 구매할 수 있는 로또의 개수 계산한다.
    - [x] 구매한 수 만큼 6개의 숫자를 가진 로또를 발행한다.
    - [x] 발행한 로또와 당첨 번호가 몇개의 번호가 일치하는지 판단한다.
    - [x] 발행한 로또와 보너스 번호가 일치하는지 판단한다.
    - [x] 일치한 번호의 개수에 맞게 당첨 여부를 판단한다.
    - [x] 총 수익률을 계산한다.
    - [x] 수동 로또를 포함하여 로또 묶음을 발행한다.

- 입력
    - [x] 로또의 구입 금액을 입력 받는다.
        - [x] 1,000원 단위로 입력해야 한다.
        - [x] 1,000원 미만이거나 너무 큰 값일 수 없다.
        - [x] 숫자가 아닌 문자를 입력할 수 없다.
    - [x] 당첨 번호를 입력 받는다.
        - [x] 번호는 쉼표를 기준으로 구분한다.
        - [x] 1~45 범위여야 한다.
        - [x] 6개의 숫자를 입력해야 한다.
        - [x] 중복되지 않은 숫자를 입력해야 한다.
        - [x] 숫자가 아닌 문자를 입력할 수 없다.
    - [x] 보너스 번호를 입력 받는다.
        - [x] 1~45 범위여야 한다.
        - [x] 당첨 번호와 중복되는지 판단한다.
        - [x] 숫자가 아닌 문자를 입력할 수 없다.
    - [x] 수동으로 구매할 번호를 입력받는다. 
        - [x] 숫자가 아닌 문자를 입력할 수 없다.
        - [x] 음수를 입력할 수 없다.
        - [x] 구입 금액보다 로또를 더 구매할 수 없다.

- 출력
    - [x] 발행한 로또들의 번호를 출력한다.
    - [x] 당첨 통계를 출력한다.
