import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PaymentMoneyTest() {
    @ParameterizedTest
    @CsvSource("500", "300", "100", "900")
    fun `구입 금액이 1000원 이상이 아니라면 예외를 발생한다`(money: Int) {
        val exception = assertThrows<IllegalArgumentException> {
            PaymentMoney(money)
        }
        assertEquals(PaymentMoney.PAYMENT_MONEY_MINIMUM_ERROR_MESSAGE, exception.message)
    }
}
