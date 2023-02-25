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
        val lottoStatistics = LottoStatistics(winningLotto, ticket)

        // when
        val result: Map<Rank, Int> = lottoStatistics.getWinningCountBy()

        // then
        assertThat(result[Rank.FIRST]).isEqualTo(2)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `총 수익률을 계산한다`() {
        // given
        val ticket = Ticket(
            listOf(
                Lotto(1, 2, 3, 10, 11, 12),
                Lotto(12, 13, 14, 15, 16, 17),
                Lotto(17, 18, 19, 20, 21, 22),
                Lotto(23, 24, 25, 26, 27, 28),
                Lotto(29, 30, 31, 32, 33, 34),
            )
        )
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto, ticket)
        val expected = "1.0"

        // when
        val result = lottoStatistics.calculateProfitRatio(Money(5000))

        // then
        assertThat(result).isEqualTo(expected)
    }
}
