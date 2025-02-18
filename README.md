# 🎰 kotlin-lotto

## 📚️ 개요

구매한 로또의 당첨 결과와 수익률을 계산한다.

## 🛠️ 구현할 기능

- [x] 구입 금액을 1000원 단위 숫자로 입력한다.
- [x] 구입 금액은 0원 이상만 가능하다.
- [x] 로또는 6개의 번호만 가진다.
    - [x] 로또의 각 번호는 1이상 45이하의 숫자이다.
- [x] 로또 1개에 랜덤 번호 6개를 부여한다.
- [x] { 입력한 금액 / 1,000 }개의 로또를 반환한다.
- [ ] 당첨번호는 6개이다.
    - [ ] 당첨번호는`, `로 구분한다.
- [ ] 각 당첨 종류에 따른 당첨 통계를 반환한다.
- [ ] 총 수익률을 소수점 둘째자리까지 반환한다.

## 🖥️ 출력 예시

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