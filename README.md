# kotlin-lotto

## 기능 요구 사항

로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
로또 1장의 가격은 1000원이다.

## 기능 목록

### 입력

- [ ] 구입금액을 입력 받는다.
- [ ] 지난주 로또 당첨 번호를 입력
- [ ] 보너스 번호 입력

### 기능

- 로또를 발행하는 기기
    - [X] 로또 구입 개수만큼 로또를 발행한다.

- lottoNumbers
    - [X] 1에서 45까지 숫자를 한개 뽑는다.
    - [X] 리스트 원소가 6개가 될때까지, 중복 없이 숫자를 추가한다.

- 로또를 당첨확인하는 기기
    - [ ] 로또 번호가 몇개 일치하는 지 확인
    - [ ] 수익률 계산 : 각 당첨 금액 * 당첨 개수 / 구입금액

### 출력

- [ ] 구입 개수를 출력한다.
- [ ] 로또 당첨 개수와 금액
- [ ] 수익률(소수점 두번째자리까지 출력)

## 실행 결과

```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```
