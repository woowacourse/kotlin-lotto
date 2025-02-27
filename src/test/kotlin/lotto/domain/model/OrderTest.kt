package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class OrderTest {
    @CsvSource(
        "1000, 1, 0",
        "1000, 0, 1",
        "2000, 1, 1",
    )
    @ParameterizedTest(name = "구입 금액 {0}원일 떄, 수동 구매 수가 {1}이라면 자동 구매 수는 {2}이다")
    fun `구입 금액과 수동 로또 구매 개수를 통해 자동 로또 구매 개수를 구할 수 있다`(
        purchaseAmount: Int,
        manualLottoAmount: Int,
        autoLottoAmount: Int,
    ) {
        val amount = PurchaseAmount(purchaseAmount)
        val actual = Order(amount, manualLottoAmount).autoLottoAmount

        assertThat(actual).isEqualTo(autoLottoAmount)
    }

    @Test
    fun `구입 금액이 1000원일 때, 수동 구매 수가 2라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Order(PurchaseAmount(1000), 2) }
    }
}
