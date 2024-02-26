import lotto.model.ErrorEvent
import lotto.util.ErrorMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ErrorEventTest {
    @Test
    fun `구매 오류 처리`() {
        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.PurchaseEvent.InvalidDataType)
        ).isEqualTo(ErrorMessage.INVALID_DATA_TYPE)

        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.PurchaseEvent.InvalidPrice)
        ).isEqualTo(ErrorMessage.INVALID_PRICE)
    }

    @Test
    fun `로또 오류 처리`(){
        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.LottoEvent.InvalidDataType)
        ).isEqualTo(ErrorMessage.INVALID_DATA_TYPE)

        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.LottoEvent.InvalidNumRange)
        ).isEqualTo(ErrorMessage.INVALID_NUM_RANGE)

        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.LottoEvent.InvalidNumCount)
        ).isEqualTo(ErrorMessage.INVALID_NUM_COUNT)

        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.LottoEvent.InvalidDuplication)
        ).isEqualTo(ErrorMessage.INVALID_DUPLICATION)
    }

    @Test
    fun `수동 로또 오류 처리`(){
        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.ManualEvent.InvalidManualCount)
        ).isEqualTo(ErrorMessage.INVALID_MANUAL_COUNT)
    }

    @Test
    fun `보너스 입력 오류 처리`(){
        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.BonusEvent.InvalidDataType)
        ).isEqualTo(ErrorMessage.INVALID_DATA_TYPE)

        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.BonusEvent.InvalidNumRange)
        ).isEqualTo(ErrorMessage.INVALID_NUM_RANGE)

        assertThat(
            ErrorMessage
                .getErrorMessage(ErrorEvent.BonusEvent.InvalidBonusDuplication)
        ).isEqualTo(ErrorMessage.INVALID_BONUS_DUPLICATION)
    }
}
