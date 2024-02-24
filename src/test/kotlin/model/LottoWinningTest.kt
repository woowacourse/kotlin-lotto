package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoWinningTest {
    private lateinit var winningTicket: LottoTicket

    @BeforeEach
    fun setup() {
        winningTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
    }

    @Test
    fun `번호 6개 일치하면 1등이다`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(winningTicket, LottoNumber(bonusNumber), userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `번호 5개 일치하고 보너스번호 일치하면 2등이다`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 45).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(winningTicket, LottoNumber(bonusNumber), userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `번호 5개 일치하고 보너스번호 일치하지 않으면 3등이다`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 44).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(winningTicket, LottoNumber(bonusNumber), userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `번호 4개 일치하면 4등이다`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 4, 43, 44).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(winningTicket, LottoNumber(bonusNumber), userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `번호 3개 일치하면 5등이다`() {
        val userTicket = LottoTicket(listOf(1, 2, 3, 42, 43, 44).map { LottoNumber(it) })
        val userTickets = listOf(userTicket)
        val bonusNumber = 45
        val lottoWinning = LottoWinning(winningTicket, LottoNumber(bonusNumber), userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(Rank.FIFTH)
    }
}
