package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PaymentMoneyTest() {
    @ParameterizedTest
    @CsvSource("500", "300", "100", "900")
    fun `구입 금액이 1000원 미만이 될 수 없다`(money: Int) {
        // given
        val exception = assertThrows<IllegalArgumentException> {
            PaymentMoney(money)
        }

        // then
        assertEquals(PaymentMoney.PAYMENT_MONEY_MINIMUM_ERROR_MESSAGE, exception.message)
    }

    @Test
    fun `구입 금액이 14000원이면 총 로또 개수는 14개이다`() {
        // given
        val money = 14000

        // when
        val actual = PaymentMoney(14000).getTotalLottoCount().value

        // then
        val expected = 14
        assertEquals(actual, expected)
    }
}
