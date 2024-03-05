package model

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
        val bonusNumber = LottoNumber.from(1)

        val exception = assertThrows<IllegalArgumentException> { WinningLotto(lottery, bonusNumber) }

        assertThat(exception.message).isEqualTo("보너스 번호인 1와 당첨번호인 1, 2, 3, 4, 5, 6는 중복되면 안됩니다.")
    }

    @Test
    fun `당첨번호와 로또 번호가 다 일치할 때 count 6을 반환하는지`() {
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(7))
        val targetLotto = Lotto(1, 2, 3, 4, 5, 6)
        val result = winningLotto.countMatch(targetLotto)

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `구매한 로또가 보너스 번호를 포함할떄 true를 반환하는지`() {
        val lottery = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(7)
        val winningLotto = WinningLotto(lottery, bonusNumber)
        val targetLotto = Lotto(1, 2, 3, 4, 5, 7)

        assertThat(winningLotto.matchBonus(targetLotto)).isTrue()
    }
}
