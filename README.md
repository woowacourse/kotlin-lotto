# kotlin-lotto

# 기능 요구사항

- [x] 로또 번호는 6개이다.
- [x] 로또 번호를 1부터 45까지 입력 받는다.
- [x] 로또 번호는 중복이 없다.
- [x] 보너스 번호는 로또 당첨 번호와 중복될 수 없다.
- [x] 당첨 결과에 따라 순위를 계산한다.

```
1등 : 6개 일치 [상금 2,000,000,000원]
2등 : 5개 일치 + 보너스 번호 일치 [상금 30,000,000원]
3등 : 5개 일치 [상금 : 1,500,000원]
4등 : 4개 일치 [상금 : 50,000원]
5등 : 3개 일치 [상금 : 5,000원]
당첨 실패 : 2개 이하 [상금 : 0원]
```

- [x] 구입 금액을 입력 받는다.
- [x] 구입 금액은 최소 1000원 부터, 1000원 단위로 입력 받는다.
- [x] 구매한 로또를 발행 한다.
- [x] 구매한 로또 번호와 개수를 출력 한다.
- [x] 지난 주 당첨 번호를 입력 받는다.
- [x] 보너스 볼을 입력 받는다.
- [x] 보너스 볼은 1부터 45까지 입력 받는다.
- [x] 한 장의 Lotto에 대한 당첨 결과를 구한다.