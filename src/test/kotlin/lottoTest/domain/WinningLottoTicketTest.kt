package lottoTest.domain

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import lotto.domain.WinningLottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinningLottoTicketTest {
    @ParameterizedTest
    @MethodSource("t1Provider")
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t1(
        manyLotto: List<Lotto>,
        scoreMap: ScoreRankMap,
    ) {
        val winningLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.of(7)
        val result = WinningLottoTicket(winningLotto, bonus).findLottoRanks(manyLotto)
        assertThat(result).isEqualTo(scoreMap)
    }

    @ParameterizedTest
    @MethodSource("t2Provider")
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2(
        winningLotto: Lotto,
        rank: Rank,
    ) {
        val lotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.of(7)
        val result = WinningLottoTicket(winningLotto, bonus).findLottoRank(lotto)
        assertThat(result).isEqualTo(rank)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3() {
        val lotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(5)
        val result = WinningLottoTicket(winningLotto, bonus).findLottoRank(lotto)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    companion object {
        @JvmStatic
        private fun t1Provider(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        Lotto.of(11, 12, 13, 14, 15, 16),
                        Lotto.of(11, 12, 13, 14, 15, 16),
                        Lotto.of(11, 12, 13, 14, 15, 16),
                    ),
                    ScoreRankMap.of(
                        mapOf(Rank.MISS to 3),
                    ),
                ),
                Arguments.of(
                    listOf(
                        Lotto.of(1, 2, 11, 10, 5, 6),
                        Lotto.of(11, 12, 13, 14, 15, 16),
                        Lotto.of(11, 12, 13, 14, 15, 16),
                    ),
                    ScoreRankMap.of(
                        mapOf(
                            Rank.MISS to 2,
                            Rank.FOURTH to 1,
                        ),
                    ),
                ),
                Arguments.of(
                    listOf(
                        Lotto.of(1, 2, 11, 10, 5, 6),
                        Lotto.of(1, 2, 3, 4, 5, 6),
                        Lotto.of(1, 2, 11, 10, 8, 9),
                    ),
                    ScoreRankMap.of(
                        mapOf(
                            Rank.MISS to 1,
                            Rank.FOURTH to 1,
                            Rank.FIRST to 1,
                        ),
                    ),
                ),
            )

        @JvmStatic
        private fun t2Provider(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Lotto.of(1, 2, 3, 4, 5, 6), Rank.FIRST),
                Arguments.of(Lotto.of(1, 2, 3, 4, 5, 8), Rank.THIRD),
                Arguments.of(Lotto.of(1, 2, 3, 4, 5, 7), Rank.SECOND),
                Arguments.of(Lotto.of(1, 2, 3, 4, 8, 9), Rank.FOURTH),
                Arguments.of(Lotto.of(1, 2, 3, 10, 8, 9), Rank.FIFTH),
                Arguments.of(Lotto.of(1, 2, 11, 10, 8, 9), Rank.MISS),
            )
    }
}
