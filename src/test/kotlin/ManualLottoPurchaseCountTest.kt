import exception.ErrorCode
import exception.Exceptions
import model.ManualLottoPurchaseCount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ManualLottoPurchaseCountTest {
    @ParameterizedTest
    @CsvSource("3,6", "2,3")
    fun `올바른 수동 로또 개수에 대해서, 에러를 던지지 않아야한다`(
        count: String,
        numberOfLotto: Int,
    ) {
        assertDoesNotThrow { ManualLottoPurchaseCount.from(count, numberOfLotto) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", " ", "sdf"])
    fun `수동 로또 개수가 자연수가 아닌 경우, INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO을 던져야한다`(count: String) {
        val exception =
            assertThrows<Exceptions.PurchaseSizeOfManualLottoException> {
                ManualLottoPurchaseCount.from(count, 10000)
            }

        assertEquals(ErrorCode.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO.message, exception.message)
    }

    @ParameterizedTest
    @CsvSource("5,3", "4,3", "3,1")
    fun `수동 로또 개수가 총 개수보다 많은 경우, INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO_AMOUNT을 던져야한다`(
        count: String,
        numberOfLotto: Int,
    ) {
        val exception =
            assertThrows<Exceptions.PurchaseSizeOfManualLottoAmountException> {
                ManualLottoPurchaseCount.from(count, numberOfLotto)
            }

        assertEquals(ErrorCode.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO_AMOUNT.message, exception.message)
    }
}
