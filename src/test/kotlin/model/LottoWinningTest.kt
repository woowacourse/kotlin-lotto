package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoWinningTest {
    @Test
    fun `등수 통계 테스트`() {
        val lottoTicketList =
            listOf(
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 7)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 8)),
                LottoTicket.from(listOf(1, 2, 3, 4, 9, 8)),
                LottoTicket.from(listOf(1, 2, 3, 10, 9, 8)),
                LottoTicket.from(listOf(43, 12, 36, 41, 25, 7)),
            )

        val winningTicket = LottoTicket.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.of(7)
        val lottoWinning = LottoWinning(winningTicket, bonusNumber)

        val winningChart = lottoWinning.makeLottoResult(lottoTicketList)

        val actual =
            Rank.entries.map {
                it to winningChart.getNum(it)
            }
        val expected =
            listOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 1,
                Rank.FOURTH to 1,
                Rank.FIFTH to 1,
                Rank.MISS to 1,
            )
        assertThat(actual).isEqualTo(expected)
    }
}
