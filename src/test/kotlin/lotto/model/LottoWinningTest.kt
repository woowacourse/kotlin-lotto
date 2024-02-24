package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoWinningTest {
    private var winningNumbers: List<LottoNumber> = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }

    @Test
    fun `번호 6개 일치하면 1등이다`() {
        val userTicket = UserLottoTicket(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        val rankMap = lottoWinning.makeRankMap()
        assertThat(rankMap[Rank.FIRST]).isEqualTo(1)
    }

    @Test
    fun `번호 5개 일치하고 보너스번호 있으면 2등이다`() {
        val userTicket = UserLottoTicket(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 7
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        val rankMap = lottoWinning.makeRankMap()
        assertThat(rankMap[Rank.SECOND]).isEqualTo(1)
    }

    @Test
    fun `번호 5개 일치하고 보너스번호 없으면 3등이다`() {
        val userTicket = UserLottoTicket(listOf(1, 2, 3, 4, 5, 44).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        val rankMap = lottoWinning.makeRankMap()
        assertThat(rankMap[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `번호 4개 일치하면 4등이다`() {
        val userTicket = UserLottoTicket(listOf(1, 2, 3, 4, 43, 44).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        val rankMap = lottoWinning.makeRankMap()
        assertThat(rankMap[Rank.FOURTH]).isEqualTo(1)
    }

    @Test
    fun `번호 3개 일치하면 5등이다`() {
        val userTicket = UserLottoTicket(listOf(1, 2, 3, 42, 43, 44).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        val rankMap = lottoWinning.makeRankMap()
        assertThat(rankMap[Rank.FIFTH]).isEqualTo(1)
    }
}
