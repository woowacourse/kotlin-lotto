package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

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
        val lottoCount = 4
        val userLottoIterator =
            List(lottoCount) { winningTicket }.iterator()

        val userTickets = LottoTicketFactory { userLottoIterator.next() }.makeLottoTickets(lottoCount)
        val lottoResult = lottoWinning.makeLottoResult(userTickets)

        val actual = lottoResult.getNum(Rank.FIRST)
        val expected = 4

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun `당첨금을 합산한다`(
        lottoResult: LottoResult,
        expected: Money,
    ) {
        val actual = lottoResult.winningMoney
        val expected = expected
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `당첨금을 합산한다`() =
            listOf(
                Arguments.of(
                    LottoResult(
                        mapOf(
                            Rank.FIRST to 1,
                            Rank.SECOND to 1,
                            Rank.THIRD to 1,
                            Rank.FOURTH to 1,
                            Rank.FIFTH to 1,
                            Rank.MISS to 1,
                        ),
                    ),
                    Money(2_031_555_000),
                ),
                Arguments.of(
                    LottoResult(
                        mapOf(
                            Rank.FIRST to 100,
                            Rank.SECOND to 0,
                            Rank.THIRD to 0,
                            Rank.FOURTH to 0,
                            Rank.FIFTH to 0,
                            Rank.MISS to 0,
                        ),
                    ),
                    Money(200_000_000_000),
                ),
            )
    }
}
