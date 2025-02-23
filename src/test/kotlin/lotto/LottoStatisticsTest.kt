package lotto

import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class LottoStatisticsTest {
    private lateinit var purchaseAmount: LottoPurchaseAmount

    @BeforeEach
    fun setUp() {
        purchaseAmount = LottoPurchaseAmount(3_000)
    }

    @ParameterizedTest
    @MethodSource("getRankStatistics")
    fun `당첨 로또 금액에 대한 수익률을 계산한다`(
        rankStatistics: Map<Rank, Int>,
        rateOfReturn: Double,
    ) {
        val lottoStatistics = LottoStatistics(rankStatistics, purchaseAmount)
        val actual = lottoStatistics.getRateOfReturn()
        assertThat(actual).isEqualTo(rateOfReturn)
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 0.9999999])
    fun `수익률이 1 미만 일 경우 수익률 손해를 파악하는 메서드는 true를 반환한다`(rateOfReturn: Double) {
        val lottoStatistics = LottoStatistics(emptyMap(), purchaseAmount)
        val expected = true

        val actual = lottoStatistics.getIsLossMoney(rateOfReturn)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(doubles = [1.0, 100.0])
    fun `수익률이 1 이상 일 경우 수익률 손해를 파악하는 메서드는 false를 반환한다`(rateOfReturn: Double) {
        val lottoStatistics = LottoStatistics(emptyMap(), purchaseAmount)
        val expected = false

        val actual = lottoStatistics.getIsLossMoney(rateOfReturn)

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun getRankStatistics(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(
                    mapOf(Rank.FIRST to 1, Rank.SECOND to 1, Rank.THIRD to 1),
                    677166.6666666666,
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
