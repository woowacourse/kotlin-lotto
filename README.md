# kotlin-lotto

## 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 도메인 기능
- [x] 로또 번호는 1~45 사이이다.
- [x] 로또 번호는 중복되지 않는 6개의 숫자를 갖는다.
- [x] 로또 1장의 가격은 1000원이다.
- [x] 구입 금액을 넣으면 몇 개의 로또를 구매했는지 알 수 있다.
- [x] 6개 숫자를 넣으면 일치한 숫자의 개수를 알 수 있다.
- [x] 5개의 숫자가 일치하면 보너스 볼 일치 여부를 확인한다.
- [x] 구입한 로또들의 당첨 통계를 알 수 있다.
- [x] 당첨 통계로 당첨 금액을 계산한다.
- [x] 당첨 금액과 구입 금액으로 수익률을 계산한다.
- [x] 로또 번호는 오름차순으로 정렬한다.

## 뷰 기능
- [x] 사용자로부터 구입금액, 당첨 번호, 보너스 볼을 입력 받는다.
- [x] 구매한 로또, 당첨 통계, 수익률을 출력한다.

## 예외처리
- [x] 입력값 검증(구매가격, 당첨 번호, 보너스 볼)
- [x] 구매 가격은 1,000단위여야 한다.
- [x] 구매 가격은 양수만 가능하다.
- [x] 당첨 번호는 중복되지 않은 6개의 숫자이다.
- [x] 당첨 번호는 1~45 사이이다.
- [x] 당첨 번호는 콤마(,)로 구분한다.
- [x] 보너스 번호는 1~45 사이이다.
- [x] 보너스 번호는 당첨 번호와 중복되지 않는다.

## TODO
- [x] Rank 매치 결과 저장 타입과 저장 여부, 저장 위치 고민