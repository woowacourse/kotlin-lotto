package lotto

import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoStatisticsTest {
    @ParameterizedTest
    @MethodSource("getRankStatistics")
    fun `당첨 로또 금액에 대한 수익률을 계산한다`(
        rankStatistics: Map<Rank, Int>,
        rateOfReturn: Double,
    ) {
        val purchaseAmount = LottoPurchaseAmount(3_000)
        val lottoStatistics = LottoStatistics(rankStatistics, purchaseAmount)
        val actual = lottoStatistics.rateOfReturn
        assertThat(actual).isEqualTo(rateOfReturn)
    }

    companion object {
        @JvmStatic
        fun getRankStatistics(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(
                    mapOf(Rank.FIRST to 1, Rank.SECOND to 1, Rank.THIRD to 1),
                    677166.66,
                ),
                Arguments.arguments(
                    emptyMap<Rank, Int>(),
                    0.0,
                ),
                Arguments.arguments(
                    mapOf(Rank.FIFTH to 3),
                    5.0,
                ),
            )
    }
}
