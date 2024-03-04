package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoWinningRankTest {
    private var winningNumbers: List<LottoNumber> =
        listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }

    @Test
    fun `번호 6개 일치하면 1등이다`() {
        val userTicket =
            UserLottoTicket.of(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinningRank =
            LottoWinningRank(winningNumbers, LottoNumber.of(bonusNumber))
        val winningTable = lottoWinningRank.makeWinningTable(userTickets)
        Assertions.assertThat(winningTable.winnings[Rank.FIRST]!!.num).isEqualTo(1)
    }

    @Test
    fun `번호 5개 일치하고 보너스번호 있으면 2등이다`() {
        val userTicket =
            UserLottoTicket.of(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 7
        val lottoWinningRank =
            LottoWinningRank(winningNumbers, LottoNumber.of(bonusNumber))
        val winningTable = lottoWinningRank.makeWinningTable(userTickets)
        Assertions.assertThat(winningTable.winnings[Rank.SECOND]!!.num).isEqualTo(1)
    }

    @Test
    fun `번호 5개 일치하고 보너스번호 없으면 3등이다`() {
        val userTicket =
            UserLottoTicket.of(listOf(1, 2, 3, 4, 5, 44).map { LottoNumber.of(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinningRank =
            LottoWinningRank(winningNumbers, LottoNumber.of(bonusNumber))
        val winningTable = lottoWinningRank.makeWinningTable(userTickets)
        Assertions.assertThat(winningTable.winnings[Rank.THIRD]!!.num).isEqualTo(1)
    }

    @Test
    fun `번호 4개 일치하면 4등이다`() {
        val userTicket =
            UserLottoTicket.of(listOf(1, 2, 3, 4, 43, 44).map { LottoNumber.of(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinningRank =
            LottoWinningRank(winningNumbers, LottoNumber.of(bonusNumber))
        val winningTable = lottoWinningRank.makeWinningTable(userTickets)
        Assertions.assertThat(winningTable.winnings[Rank.FOURTH]!!.num).isEqualTo(1)
    }

    @Test
    fun `번호 3개 일치하고 보너스번호 있으면 5등이다`() {
        val userTicket =
            UserLottoTicket.of(listOf(1, 2, 3, 42, 43, 44).map { LottoNumber.of(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 44
        val lottoWinningRank =
            LottoWinningRank(winningNumbers, LottoNumber.of(bonusNumber))
        val winningTable = lottoWinningRank.makeWinningTable(userTickets)
        Assertions.assertThat(winningTable.winnings[Rank.FIFTH]!!.num).isEqualTo(1)
    }
}
