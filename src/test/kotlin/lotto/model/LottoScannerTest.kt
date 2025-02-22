package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoScannerTest {
    private fun lotto(vararg numbers: Int): Set<LottoNumber> = numbers.map { LottoNumber(it) }.toSet()

    @Test
    fun `로또 당첨을 판단한다`() {
        val bonusNumber = LottoNumber(7)

        val winningNumbers =
            LottoTicket(
                lotto(1, 2, 3, 4, 5, 6),
                bonusNumber,
            )

        val lottoTicket =
            LottoTicket(
                lotto(1, 2, 3, 4, 5, 6),
            )

        val lottoScanner = LottoScanner(winningNumbers)
        val rank = lottoScanner.getRank(lottoTicket)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }
}
