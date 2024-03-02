import exception.ErrorCode
import exception.Exceptions
import model.Buyer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BuyerTest {
    @ParameterizedTest
    @CsvSource("8000,8", "1000,1", "15000,15")
    fun `구매 금액 1000원당, 로또를 1장씩 구매할 수 있다`(
        purchaseAmount: String,
        numberOfLotto: Int,
    ) {
        val buyer = Buyer.from(purchaseAmount)
        val actual = buyer.calculateNumberOfLotto()

        assertThat(actual).isEqualTo(numberOfLotto)
    }

    @ParameterizedTest
    @ValueSource(strings = ["8000", "1000", "15000"])
    fun `올바른 구매 금액에 대해서, 에러를 던지지 않아야한다`(purchaseAmount: String) {
        assertDoesNotThrow { Buyer.from(purchaseAmount) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["400", "999", "-1"])
    fun `구매 금액이 1000원 미만인 경우, INVALID_PURCHASE_AMOUNT_RANGE을 던져야한다`(purchaseAmount: String) {
        val exception =
            assertThrows<Exceptions.PurchaseAmountRangeException> {
                Buyer.from(purchaseAmount)
            }

        assertThat(exception.message).isEqualTo(ErrorCode.INVALID_PURCHASE_AMOUNT_RANGE.message)
    }
}
