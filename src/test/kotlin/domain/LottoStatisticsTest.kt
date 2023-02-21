package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStatisticsTest {
    @Test
    fun `Ticket을 넘겨 받아서 당첨 결과를 확인한다`() {
        // given
        val ticket = Ticket(
            listOf(
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 13),
                Lotto(1, 2, 3, 4, 5, 9),
            )
        )
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        // when
        val result: Map<Rank, Int> = lottoStatistics.compareTicket(ticket)

        // then
        assertThat(result[Rank.FIRST]).isEqualTo(2)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `총 수익률을 계산한다`() {
        // given
        val winResult = mutableMapOf(
            Rank.FIRST to 0,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 1,
        )
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val expected = 5000

        // when
        val result = lottoStatistics.calculateProfit(winResult)

        // then
        assertThat(result).isEqualTo(expected)
    }
}
