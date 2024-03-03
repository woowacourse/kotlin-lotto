import lotto.model.LottoEvent
import lotto.model.manual.ManualEvent
import lotto.model.user.UserException
import lotto.model.wallet.WalletEvent
import lotto.model.winning.BonusEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserEventTest {
    @Test
    fun `구매 오류 처리`() {
        assertThat(
            WalletEvent.checkWallet(WalletEvent.InvalidDataType).message
        ).isEqualTo(UserException.INVALID_DATA_TYPE)

        assertThat(
            WalletEvent.checkWallet(WalletEvent.InvalidPrice).message
        ).isEqualTo(WalletEvent.INVALID_PRICE)
    }

    @Test
    fun `로또 오류 처리`() {
        assertThat(
            LottoEvent.checkLotto(LottoEvent.InvalidDataType).message
        ).isEqualTo(UserException.INVALID_DATA_TYPE)

        assertThat(
            LottoEvent.checkLotto(LottoEvent.InvalidNumRange).message
        ).isEqualTo(LottoEvent.INVALID_NUM_RANGE)

        assertThat(
            LottoEvent.checkLotto(LottoEvent.InvalidNumCount).message
        ).isEqualTo(LottoEvent.INVALID_NUM_COUNT)

        assertThat(
            LottoEvent.checkLotto(LottoEvent.InvalidDuplication).message
        ).isEqualTo(LottoEvent.INVALID_DUPLICATION)
    }

    @Test
    fun `수동 로또 오류 처리`() {
        assertThat(
            ManualEvent.checkManual(ManualEvent.InvalidManualCount).message
        ).isEqualTo(ManualEvent.INVALID_MANUAL_COUNT)
    }

    @Test
    fun `보너스 입력 오류 처리`() {
        assertThat(
            BonusEvent.checkBonusEvent(BonusEvent.InvalidDataType).message
        ).isEqualTo(UserException.INVALID_DATA_TYPE)

        assertThat(
            BonusEvent.checkBonusEvent(BonusEvent.InvalidNumRange).message
        ).isEqualTo(LottoEvent.INVALID_NUM_RANGE)

        assertThat(
            BonusEvent.checkBonusEvent(BonusEvent.InvalidBonusDuplication).message
        ).isEqualTo(BonusEvent.INVALID_BONUS_DUPLICATION)
    }
}
