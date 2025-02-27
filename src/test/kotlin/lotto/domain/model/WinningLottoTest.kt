package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private fun lotto(vararg numbers: Int): LottoTicket = LottoTicket(numbers.map { LottoNumber(it) })

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외를 발생시킨다`() {
        val winningNumbers = lotto(1, 2, 3, 4, 5, 7)
        val bonusNumber = 7

        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumbers.toSet(), LottoNumber(bonusNumber))
        }
    }

    @Test
    fun `로또 당첨을 판단한다`() {
        val bonusNumber = LottoNumber(7)
        val winningNumbers = lotto(1, 2, 3, 4, 5, 6)
        val lottoTicket = lotto(1, 2, 3, 4, 5, 6)
        val expect = Rank.FIRST

        val rank = WinningLotto(winningNumbers.toSet(), bonusNumber).getRank(lottoTicket)

        assertThat(rank).isEqualTo(expect)
    }
}
