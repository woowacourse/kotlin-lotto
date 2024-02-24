package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoWinningTest {
    private lateinit var winningTicket: LottoTicket

    @BeforeEach
    fun setup() {
        winningTicket = LottoTicket.from(listOf(1, 2, 3, 4, 5, 6))
    }

    @ParameterizedTest
    @MethodSource("generateArgumentRankTest")
    fun `랭크 판정 테스트`(args: Pair<LottoTicket, Rank>) {
        val (userTicket, expected) = args
        val userTickets = listOf(userTicket)
        val bonusNumber = LottoNumber(7)
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `등수 통계 테스트`() {
        val userLottoIterator =
            listOf(
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 7)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 8)),
                LottoTicket.from(listOf(1, 2, 3, 4, 9, 8)),
                LottoTicket.from(listOf(1, 2, 3, 10, 9, 8)),
                LottoTicket.from(listOf(43, 12, 36, 41, 25, 7)),
            ).iterator()
        val lottoPurchase = LottoPurchase(6000) { userLottoIterator.next() }
        val winningTicket = LottoTicket.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(7)

        val userTickets = lottoPurchase.makeUserTickets()
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val winningChart = lottoWinning.makeLottoResult()

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

    companion object {
        @JvmStatic
        fun generateArgumentRankTest() =
            listOf(
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)) to Rank.FIRST,
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 7)) to Rank.SECOND,
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 8)) to Rank.THIRD,
                LottoTicket.from(listOf(1, 2, 3, 4, 44, 45)) to Rank.FOURTH,
                LottoTicket.from(listOf(1, 2, 3, 43, 44, 45)) to Rank.FIFTH,
                LottoTicket.from(listOf(10, 11, 12, 13, 14, 15)) to Rank.MISS,
            )
    }
}
