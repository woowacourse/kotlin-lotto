package lottotest.domain.model

import lotto.domain.model.Rank
import lotto.domain.model.WinningStatistics
import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.ObjectQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinningStatisticsTest {
    @ParameterizedTest
    @MethodSource("normalWinningStatisticsData")
    fun `인스턴스에게 당첨 통계를 요청하면 MISS를 제외한 전체 0개 당첨을 포함한 정렬된 전체 정보를 반환한다`(
        lottoPaymentMoney: LottoPaymentMoney,
        rawWinRankMap: Map<Rank, Int>,
        rawExpectedRank: List<Pair<Rank, Int>>,
    ) {
        // Given
        val winRankMap = rawWinRankMap.mapValues { (_, value) -> ObjectQuantity(value) }
        val expectedRank: List<Pair<Rank, ObjectQuantity>> =
            rawExpectedRank.map { (rank, value) -> rank to ObjectQuantity(value) }

        // When
        val winningStatistics = WinningStatistics(lottoPaymentMoney, winRankMap)
        val actualRank: List<Pair<Rank, ObjectQuantity>> = winningStatistics.getFullRanksWithoutMiss()

        // Then
        assertThat(actualRank).isEqualTo(expectedRank)
    }

    @ParameterizedTest
    @MethodSource("normalWinningStatisticsData")
    fun `인스턴스에게 전체 당첨 금액을 요청하면 당첨 금액 인스턴스를 반환한다`(
        lottoPaymentMoney: LottoPaymentMoney,
        rawWinRankMap: Map<Rank, Int>,
        rawExpectedRank: List<Pair<Rank, Int>>,
    ) {
        // Given
        val winRankMap = rawWinRankMap.mapValues { (_, value) -> ObjectQuantity(value) }
        val expectedPrizeMoney: Long = rawWinRankMap.map { (rank, value) -> rank.winningMoney.toLong() * value }.sum()

        // When
        val winningStatistics = WinningStatistics(lottoPaymentMoney, winRankMap)

        // Then
        assertThat(winningStatistics.getTotalPrizeMoney().money).isEqualTo(expectedPrizeMoney)
    }

    companion object {
        @JvmStatic
        fun normalWinningStatisticsData() =
            Stream.of(
                Arguments.of(
                    LottoPaymentMoney(35000),
                    mapOf(Rank.THIRD to 2, Rank.FOURTH to 4),
                    listOf(Rank.FIFTH to 0, Rank.FOURTH to 4, Rank.THIRD to 2, Rank.SECOND to 0, Rank.FIRST to 0),
                ),
            )
    }
}
