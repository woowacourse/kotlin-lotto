# kotlin-lotto

## 🔍 로또 - 기능 목록

- [x] 구입금액 입력
    - [x] "구입금액을 입력해 주세요."라는 입력 안내 문구를 출력한다.
    - [x] 사용자가 잘못된 구입금액을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
        - [x] 구입 금액이 1,000원 이상의 숫자가 아닌 경우 예외 처리한다.
        - [x] 구입 금액이 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다.


- [x] 발행한 로또 수량 계산 및 출력
    - [x] 발행한 로또 수량(구입금액 / 1,000)을 계산한다.
    - [x] 발행한 로또 수량을 출력한다.


- [x] 로또 번호 생성 및 출력
    - [x] 로또 번호는 1~45사이의 숫자에서 중복되지 않는 6개의 숫자를 뽑는다.
    - [x] 로또 번호는 오름차순으로 정렬하여 출력한다.


- [x] 당첨 번호 입력
    - [x] "지난 주 당첨 번호를 입력해 주세요."라는 입력 안내 문구를 출력한다.
    - [x] 사용자가 잘못된 당첨 번호를 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
        - [x] 당첨 번호는 쉼표(,)를 기준으로 구분한다.
        - [x] 당첨 번호의 개수가 6개가 아닌 경우 예외 처리한다.
        - [x] 당첨 번호가 1~45사이의 숫자가 아닌 경우 예외 처리한다.
        - [x] 당첨 숫자가 중복되는 경우 예외 처리한다.


- [x] 보너스 번호 입력
    - [x] "보너스 볼을 입력해 주세요."라는 입력 안내 문구를 출력한다.
    - [x] 사용자가 잘못된 보너스 번호를 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
        - [x] 보너스 번호가 1~45사이의 숫자가 아닌 경우 예외 처리한다.
        - [x] 보너스 번호가 당첨 숫자와 중복되는 경우 예외 처리한다.


- [ ] 당첨 통계 출력
    - [ ] 사용자가 구매한 로또 번호와 당첨 번호, 보너스 번호를 비교하여 당첨 등수를 판단한다.
    - [ ] "당첨 통계"라는 출력 안내 문구를 출력한다.
    - [ ] "---------"라는 출력 구분 문구를 출력한다.
    - [ ] 당첨 내역을 출력한다.


- [ ] 수익률 출력
    - [ ] 수익률(총 당첨 금액 / 구입 금액)을 계산한다.
    - [ ] 수익률을 소수점 둘째 자리에서 반올림하여 출력한다.