package lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
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
}
