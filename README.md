# kotlin-lotto

# domain
## LottoNumber
- [X] 로또 숫자의 원시 값을 포장
- [X] value class로 선언

## PurchaseLottoMoney
- [X] 구입 금액의 원시값을 포장. value class 로 선언
- [X] 구입 금액으로부터 몇 장을 구매가능한지 반환해주는 기능

## LottoPurchaseInfo
- [X] 수동 발급과 자동 발급의 개수를 감싸는 클래스
- [X] 생성자로 PurchaseLottoMoney와 수동발급의 개수를 입력받는다.
  - [X] 수동 발급의 개수가 구입 가능 개수를 초과하지는 않는지 검증
  - [X] 수동 발급의 개수가 음수는 아닌지 검증

## Lotto
- [X] 일급 컬렉션으로 사용(Set)
- [X] 검증이 필요한 부분
  - [X] 개수가 6개면 로또 생성
  - [X] 개수가 6개가 아니라면 exception 발생
  - [X] 번호가 범위안에 들어오는지 검증
  - [X] 당첨번호와 매칭되는 등수를 반환하는지 검증

## Ticket
- [X] 로또들의 정보를 저장

## WinningLotto
- [X] 일급 컬렉션으로 사용
- [X] 검증
  - [X] 당첨번호와 보너스 번호가 같으면 exception 발생
  - [X] 당첨번호와 보너스 번호가 다르면 winningLotto 생성
  - [X] 보너스번호가 범위안에 들어오는지 검증
- [X] 받은 Lotto의 당첨결과를 반환하는 기능

## LottoResult
- [X] 로또의 결과를 래핑하는 클래스
  - [X] 각 랭크를 키 값으로 해서 널인 값을 가지면 안된다. 
- [X] 로또당첨결과의 당첨 금액의 총합을 구해서 반환하는 기능 추가

## LottoSeller
- [X] 로또를 한 장 발급한다.
- [X] 파라미터(PurchaseLottoMoney)로 살 수 있는 만큼 로또를 자동 발급한다.
- [X] 파라미터(LottoPurchaseInfo와 Tikcket)를 받아서 로또를 수동 및 자동발급해준다.
  - [X] LottoPurchaseInfo의 프로퍼티 값에 따라서 로또를 자동 및 수동 발급해준다.
  - [X] 만약 LottoPurchaseInfo의 수동 발급 개수와 제출한 Ticket의 크기가 다르다면 null을 반환한다.

## LottoStatistics
- [X] 당첨 통계를 낸다.
  - [X] 단일 로또를 넘겨 받아서 당첨 결과인 LottoResult 를 반환한다.
- [X] Ticket을 넘겨 받아서 당첨 번호와 비교한다.
  - [X] 총 수익률을 계산한다.

## RandomGenerator
- [X] 6개의 무작위 수 생성

## Enum class
- [X] 등수와 금액을 저장한다.
- [X] 당첨 번호와 보너스 볼 매치 여부로 당첨 등수 반환

---

# view
## InputView
- [X] 구입 금액을 입력받는다.
- [X] 수동 로또 구매 수를 입력받는다.
- [X] 수동 로또 구매 수만큼 수동으로 구매할 로또 번호를 입력받는다.
- [X] 당첨 번호를 입력받는다.
- [X] 보너스 볼을 입력받는다.

## OutputView
- [X] 전달받은 문자열을 출력한다.
- [X] 수동과 자동으로 발급한 로또 번호들을 출력한다.
  - [X] 받은 lotto를 토대로 "[1 ,2, 3, 4, 5, 6]"과 같은 문자열이 출력되는 기능
- [X] 당첨 통계를 출력한다.
- [X] 수익률을 출력한다.

## validator 
- [X] 검증에서 실패하면 널을 반환한다.
  - [X] 스트링에서 정수로 형변환 가능한지 검증하고 된다면 정수 반환
  - [X] 스트링에서 정수 배열로 형변환이 가능한지 검증하고 된다면 정수 배열 반환
  - [X] 받은 숫자가 PurchaseLottoMoney로 변환이 되는지 검증하고 된다면 반환
  - [X] 주어진 돈으로 그만큼의 수동 구매 개수를 구매가능한지 검증하고 된다면 반환
  - [X] 주어진 배열이 Lotto로 변환되는지 검증하고 된다면 변환해서 반환
  - [X] 주어진 숫자가 LottoNumber로 변환되는지 검증하고 된다면 반환
  - [X] 주어진 Lotto와 LottoNumber로 WinningLotto가 만들어질 수 있는지 검증하고 된다면 반환
---

# Controller
## Controller
- [X] 전체 로직을 담당한다.
  - [X] 구입 금액을 입력받아 PurchaseLottoMoney를 얻음
  - [X] 수동 구매 개수를 입력받아서 LottoPurchaseInfo를 얻음
  - [X] 수동 구매 개수의 크기를 가지는 Ticket을 얻음
  - [X] 이렇게 해서 얻은 LottoPurchaseInfo와 Ticket으로 수동 및 자동발급을 수행해서 Ticket을 얻음
    - [X] 만약 수동 발급 개수와 Ticket의 크기가 다르다면 널을 반환받고 다시 입력 받기로 돌아감. 
  - [X] Ticket의 목록을 출력
  - [X] 당첨 번호와 보너스 볼을 입력받아 WinningLotto 생성
  - [X] LottoStatistics를 통해 당첨 통계 계산
  - [X] outputView에 결과를 전달하여 출력




