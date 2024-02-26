import lotto.model.ExceptionManager
import lotto.model.UserEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserEventTest {
    @Test
    fun `구매 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.PurchaseEvent.InvalidDataType)
        ).isEqualTo(ExceptionManager.INVALID_DATA_TYPE)

        assertThat(
            ExceptionManager.getException(UserEvent.PurchaseEvent.InvalidPrice)
        ).isEqualTo(ExceptionManager.INVALID_PRICE)
    }

    @Test
    fun `로또 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidDataType)
        ).isEqualTo(ExceptionManager.INVALID_DATA_TYPE)

        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumRange)
        ).isEqualTo(ExceptionManager.INVALID_NUM_RANGE)

        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumCount)
        ).isEqualTo(ExceptionManager.INVALID_NUM_COUNT)

        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidDuplication)
        ).isEqualTo(ExceptionManager.INVALID_DUPLICATION)
    }

    @Test
    fun `수동 로또 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.ManualEvent.InvalidManualCount)
        ).isEqualTo(ExceptionManager.INVALID_MANUAL_COUNT)
    }

    @Test
    fun `보너스 입력 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.BonusEvent.InvalidDataType)
        ).isEqualTo(ExceptionManager.INVALID_DATA_TYPE)

        assertThat(
            ExceptionManager.getException(UserEvent.BonusEvent.InvalidNumRange)
        ).isEqualTo(ExceptionManager.INVALID_NUM_RANGE)

        assertThat(
            ExceptionManager.getException(UserEvent.BonusEvent.InvalidBonusDuplication)
        ).isEqualTo(ExceptionManager.INVALID_BONUS_DUPLICATION)
    }
}
