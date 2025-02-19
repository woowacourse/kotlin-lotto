package lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 999, 1001, 1234, 10100])
    fun `로또 구입 금액은 1,000원 단위가 아닌 경우 예외를 발생한다`(money: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { PurchaseAmount(money) }
            .withMessage("구입 금액은 1,000원 단위입니다.")
    }
}
