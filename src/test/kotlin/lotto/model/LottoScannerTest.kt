package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoScannerTest {
    @Test
    fun `로또 당첨을 판단한다`() {
        val bonusNumber = LottoNumber(7)

        val winningNumbers =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
                bonusNumber,
            )

        val lottoTicket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val lottoScanner = LottoScanner(winningNumbers)
        val rank = lottoScanner.getRank(lottoTicket)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }
}
