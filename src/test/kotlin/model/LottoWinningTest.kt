package model

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class LottoWinningTest {
    @Test
    fun `로또 번호 겹침 개수 테스트`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val userTickets = listOf(userTicket)
        val winningTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val countMatchNumber = lottoWinning.countMatchNumber(userTicket)
        assertThat(countMatchNumber).isEqualTo(6)
    }

    @Test
    fun `로또 번호 겹침 역수 테스트`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val userTickets = listOf(userTicket)
        val winningTicket = LottoTicket(listOf(6, 5, 4, 3, 2, 1))
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val countMatchNumber = lottoWinning.countMatchNumber(userTicket)
        assertThat(countMatchNumber).isEqualTo(6)
    }

    @Test
    fun `로또 번호 일부 겹침 테스트`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val userTickets = listOf(userTicket)
        val winningTicket = LottoTicket(listOf(1, 2, 3, 7, 8, 9))
        val bonusNumber = 10
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val countMatchNumber = lottoWinning.countMatchNumber(userTicket)
        assertThat(countMatchNumber).isEqualTo(3)
    }

    @Test
    fun `등수 분류 테스트`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val userTickets = listOf(userTicket)
        val winningTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(Rank.FIRST)
    }
    /*
    @Test
    fun `등수 통계 테스트`() {
        val userLottoSequence = sequence {
            yield(LottoTicket(listOf(1, 2, 3, 4, 5, 6)))
            yield(LottoTicket(listOf(1, 2, 3, 4, 5, 7)))
            yield(LottoTicket(listOf(1, 2, 3, 4, 9, 8)))
            yield(LottoTicket(listOf(1, 2, 3, 10, 9, 8)))
            yield(LottoTicket(listOf(1, 2, 11, 10, 9, 8)))
            yield(LottoTicket(listOf(43, 12, 36, 41, 25, 7)))
            yield(LottoTicket(listOf(41, 12, 37, 43, 35, 7)))
        }
        val lottoPurchase = LottoPurchase(7000)
        val winningTicket = LottoTicket(listOf(1,2,3,4,5,6))
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, lottoPurchase)
        val winningChart = lottoWinning.makeWinningChart()
        val actual = winningChart[Rank.]
        assertThat(actual).isEqualTo(7)
    }
     */
}
