package lotto

import lotto.model.LottoPurchaseAmount
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottoPurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [-1_000, 0, 999, 100_001])
    fun `구입 금액은 최소 1,000원 이상 최대 100,000원 이하가 아닐 경우 예외를 발생한다`(money: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { LottoPurchaseAmount(money) }
            .withMessage("구입 금액은 최소 1,000원 이상 최대 100,000원 이하 입니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [1_100, 10_100])
    fun `로또 구입 금액은 1,000원 단위가 아닌 경우 예외를 발생한다`(money: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { LottoPurchaseAmount(money) }
            .withMessage("구입 금액은 1,000원 단위입니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [1_000, 14_000, 100_000, 99_000, 21_000])
    fun `구입 금액은 최소 1,000원 이상 최대 100,000원 이하이면서 1,000원 단위일 경우 예외를 발생하지 않는다`(money: Int) {
        Assertions.assertDoesNotThrow {
            LottoPurchaseAmount(money)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1_000:1", "2_000:2", "100_000:100"], delimiter = ':')
    fun `로또 구입 금액에 따른 로또 개수를 반환한다`(
        money: Int,
        expectedLottoCount: Int,
    ) {
        assertThat(
            LottoPurchaseAmount(money).getLottoCount(),
        ).isEqualTo(expectedLottoCount)
    }
}
