package domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `보너스 번호가 로또 번호와 중복될 때 예외를 던진다`() {
        val lottery = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(1)

        val exception = assertThrows<IllegalArgumentException> {
            WinningLotto(
                lottery,
                bonusNumber
            )
        }

        assertThat(exception.message).isEqualTo("보너스 번호인 ${bonusNumber}와 당첨번호인 ${lottery}는 중복되면 안됩니다.")
    }
}
