package model

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoWinningTest {
    private lateinit var winningTicket: LottoTicket

    @BeforeEach
    fun setup(){
        winningTicket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
    }

    @ParameterizedTest
    @MethodSource("generateArgumentMatchTest")
    fun `로또 번호 겹침 개수 테스트`(args: Pair<LottoTicket, Int>) {
        val (userTicket, expected) = args
        val userTickets = listOf(userTicket)
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val countMatchNumber = lottoWinning.countMatchNumber(userTicket)
        assertThat(countMatchNumber).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("generateArgumentRankTest")
    fun `랭크 판정 테스트`(args: Pair<LottoTicket, Rank>) {
        val (userTicket, expected) = args
        val userTickets = listOf(userTicket)
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val rankList = lottoWinning.getRankList()
        val actual = rankList[0]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `보너스 번호 포함 여부 테스트`() {
        val winningTicket = LottoTicket(listOf(1,2,3,4,5,7))
        val userTicket = LottoTicket(listOf(1,2,3,4,5,6))
        val userTickets = listOf(userTicket)
        val bonusNumber = 7
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val actual = lottoWinning.isBonusInTicket(winningTicket)
        assertThat(actual).isEqualTo(true)
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
    companion object{
        @JvmStatic
        fun generateArgumentMatchTest() = listOf(
            LottoTicket(listOf(1, 2, 3, 4, 5, 6)) to 6, // 6개 겹침, 1등 케이스,
            LottoTicket(listOf(1, 2, 3, 4, 5, 7)) to 5, // 5개 겹침, 2등 케이스,
            LottoTicket(listOf(1, 2, 3, 4, 5, 8)) to 5, // 5개 겹침 , 3등 케이스
            LottoTicket(listOf(1, 2, 3, 4, 44, 45)) to 4, // 4개 겹침, 4등 케이스
            LottoTicket(listOf(1, 2, 3, 43, 44, 45)) to 3, // 3개 겹침, 5등 케이스
            LottoTicket(listOf(6, 5, 4, 3, 2, 1)) to 6, // 역순 케이스
        )

        @JvmStatic
        fun generateArgumentRankTest() = listOf(
            LottoTicket(listOf(1, 2, 3, 4, 5, 6)) to Rank.FIRST, // 6개 겹침, 1등 케이스,
            LottoTicket(listOf(1, 2, 3, 4, 5, 7)) to Rank.SECOND, // 5개 겹침, 2등 케이스,
            LottoTicket(listOf(1, 2, 3, 4, 5, 8)) to Rank.THIRD, // 5개 겹침 , 3등 케이스
            LottoTicket(listOf(1, 2, 3, 4, 44, 45)) to Rank.FOURTH, // 4개 겹침, 4등 케이스
            LottoTicket(listOf(1, 2, 3, 43, 44, 45)) to Rank.FIFTH, // 3개 겹침, 5등 케이스
            LottoTicket(listOf(10, 11, 12, 13, 14, 15)) to Rank.MISS, // 없음.
        )
    }
}
