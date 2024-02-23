package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `특정 등수에 당첨되지 않았을 경우 0으로 센다`() {
        val rankMap = emptyMap<Rank, Int>()
        val lottoResult = LottoResult(rankMap)
        val actual = lottoResult.getNum(Rank.THIRD)
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `특정 등수에 당첨됐는지 그 개수를 센다`() {
        val userLottoIterator =
            listOf(
                LottoTicket(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket(listOf(1, 2, 3, 4, 5, 6)),
            ).iterator()

        val lottoPurchase = LottoPurchase(4000) { userLottoIterator.next() }
        val winningTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 7

        val userTickets = lottoPurchase.makeUserTickets()
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val winningChart = lottoWinning.makeWinningChart()

        val actual = winningChart.getNum(Rank.FIRST)
        val expected = 4

        assertThat(actual).isEqualTo(expected)
    }
}
