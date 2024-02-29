import lotto.model.ExceptionManager
import lotto.model.user.UserEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserEventTest {
    @Test
    fun `구매 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.PurchaseEvent.InvalidDataType).message
        ).isEqualTo(ExceptionManager.INVALID_DATA_TYPE)

        assertThat(
            ExceptionManager.getException(UserEvent.PurchaseEvent.InvalidPrice).message
        ).isEqualTo(ExceptionManager.INVALID_PRICE)
    }

    @Test
    fun `로또 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidDataType).message
        ).isEqualTo(ExceptionManager.INVALID_DATA_TYPE)

        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumRange).message
        ).isEqualTo(ExceptionManager.INVALID_NUM_RANGE)

        assertThat(
            ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumCount).message
        ).isEqualTo(ExceptionManager.INVALID_NUM_COUNT)
    }

    @Test
    fun `수동 로또 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.ManualEvent.InvalidManualCount).message
        ).isEqualTo(ExceptionManager.INVALID_MANUAL_COUNT)
    }

    @Test
    fun `보너스 입력 오류 처리`() {
        assertThat(
            ExceptionManager.getException(UserEvent.BonusEvent.InvalidDataType).message
        ).isEqualTo(ExceptionManager.INVALID_DATA_TYPE)

        assertThat(
            ExceptionManager.getException(UserEvent.BonusEvent.InvalidNumRange).message
        ).isEqualTo(ExceptionManager.INVALID_NUM_RANGE)

        assertThat(
            ExceptionManager.getException(UserEvent.BonusEvent.InvalidBonusDuplication).message
        ).isEqualTo(ExceptionManager.INVALID_BONUS_DUPLICATION)
    }
}
