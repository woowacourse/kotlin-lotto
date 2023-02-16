# kotlin-lotto

## 기능 요구 사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 기능 목록

- [x] 로또는 번호 여섯개를 갖는다.
- [x] 로또는 1~45 사이의 랜덤한 중복되지 않는 숫자를 갖는다.
- [x] 랜덤한 로또 번호를 생성한다.
- [x] 당첨 번호와 몇개 일치하는지 판단한다.
- [x] 수익률을 구한다. (구입개수, 총 당첨 Rank)
- [x] 일치 번호 개수와 보너스 넘버로 당첨 등수를 구한다.
- [x] 로또 번호가 보너스 번호와 일치하는지 판단한다.
- [x] 입력받은 당첨번호와 보너스 번호가 중복되지 않는지 검사한다.
- [x] 현재 사용자가 가지고 있는 로또가 몇등인지 판단한다.

- [x] 로또 구입 금액을 입력받는다.
- [x] 구입 금액만큼 로또를 발행한다.
- [x] 발행된 로또를 출력한다.
- [x] 당첨 번호를 입력받는다.
- [x] 보너스 볼 번호를 입력받는다.
- [x] 당첨 통계를 출력한다.

## 클래스

### 로또

- 당첨 번호와 몇개 일치하는지 판단한다.
- 보너스 번호와 일치하는지 판단한다.

### YieldCalculator

- 수익률을 계산한다.

### InputView

### OutputView

### LottoGenerator

### RandomNumberGenerator

### Validator

- 금액은 숫자여야 한다.
- 금액은 음수면 안된다.
- 금액은 1000원으로 나누어 떨어져야 한다.

### WinningNumbers

- 입력받은 당첨번호와 보너스 번호가 중복되지 않는지 검사한다.

### UserLotto

- 가지고 있는 로또가 몇등에 당첨됐는지 구한다.
- 가지고 있는 로또의 개수를 반환한다.

## 힌트

- 로또 자동 생성은 shuffled()을 활용한다.
- sorted()를 활용해 정렬 가능하다.
- contains()를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.
  일급 컬렉션을 쓴다.
- 6개의 숫자 값을 가지는 컬렉션을 감싸는 객체를 추가해 구현해 본다.
- 하드 코딩을 하지 않기 위해 상수 값을 사용하면 많은 상수 값이 발생한다. Enum 클래스를 활용해 상수 값을 제거한다. 즉, Enum 클래스를 활용해 일치하는 수를 로또 등수로 변경해 본다.

```kotlin

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return values().find {
                // ...
            }
        }
    }
}
```