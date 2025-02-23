package lotto.model

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoScannerTest {
    private fun lotto(vararg numbers: Int): Set<LottoNumber> = numbers.map { LottoNumber(it) }.toSet()

    @Test
    fun `로또 당첨을 판단한다`() {
        val bonusNumber = LottoNumber(7)

        val winningNumbers = lotto(1, 2, 3, 4, 5, 6)

        val lottoTicket =
            LottoTicket(
                lotto(1, 2, 3, 4, 5, 6),
            )

        val rank = WinningLotto(winningNumbers, bonusNumber).getRank(lottoTicket)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }
}
