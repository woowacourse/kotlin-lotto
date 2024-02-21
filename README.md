# kotlin-lotto

## 기능 목록
---

### 유효성 검사

- [x] 구입금액에 대한 유효성을 검사한다.
    - [x] 구입금액이 1000원 미만이면 예외 반환
    - [x] "구입금액은 1000원 이상이어야 합니다."
    - [x] 구입금액이 1000원 단위가 아니면 예외 반환
        - [x] "구입금액은 1000원 단위여야 합니다."
    - [x] 정수형이 아니면 예외 반환
        - [x] "구입금액은 정수여야 합니다."

- [x] 로또 번호에 대한 유효성을 검사한다.
    - [x] 로또 번호가 1 ~ 45를 벗어나면 예외 반환
        - [x] "로또 번호는 1 ~ 45 사이의 정수여야 합니다."
    - [x] 로또 번호가 중복되면 예외 반환
        - [x] "로또 번호끼리는 중복되면 안됩니다."
    - [x] 로또 번호가 6개가 아니면 예외 반환
        - [x] "로또 번호는 6개여야 합니다."

- [ ] 보너스 번호에 대한 유효성을 검사한다.
    - [x] 보너스 번호가 1 ~ 45를 벗어나면 예외 반환
        - [x] "보너스 번호는 1 ~ 45 사이의 정수여야 합니다."
    - [ ] 보너스 번호가 당첨번호와 중복되면 예외 반환
        - [ ] "보너스 번호는 당첨번호와 중복되면 안됩니다."

### 입력

- [x] 로또 구입금액을 입력받는다.
- [x] 당첨 번호를 입력받는다.
- [x] 보너스 번호를 입력받는다.

### 출력

- [x] 구매 수량을 출력한다.
- [x] 로또 티켓들을 출력한다.
- [ ] 당첨 통계를 출력한다.
- [ ] 수익률을 출력한다.

### 기능

- [x] 구입 금액 만큼 티켓 수량을 반환한다
- [x] 로또 번호 생성기
    - [x] 1 ~ 45 사이의 정수 중 랜덤하게 6개를 뽑는다
- [x] 로또 번호 일치 갯수 계산기
    - [x] 당첨 번호와 일치 갯수를 구한다
    - [x] 보너스 볼 매치 여부를 반환한다
- [x] 당첨 통계 계산기
    - [x] 당첨 통계를 반환한다
- [x] 수익률 계산기
