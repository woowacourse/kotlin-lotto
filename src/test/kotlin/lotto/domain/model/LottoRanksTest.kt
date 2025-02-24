package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoRanksTest {
    @ParameterizedTest
    @MethodSource("calculateTotalReturnTest")
    fun `총 수익률을 계산할 수 있다`(
        lottoRanks: List<LottoRank>,
        expectedReturn: Double,
    ) {
        val totalReturn = LottoRanks(lottoRanks).calculateTotalReturn(1000)
        assertThat(totalReturn.toDouble()).isEqualTo(expectedReturn)
    }

    companion object {
        @JvmStatic
        fun calculateTotalReturnTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(listOf(LottoRank.FIRST), 2_000_000.00),
                Arguments.arguments(listOf(LottoRank.SECOND), 30_000.00),
                Arguments.arguments(listOf(LottoRank.THIRD), 1_500.00),
                Arguments.arguments(listOf(LottoRank.FOURTH, LottoRank.FIFTH), 27.50),
                Arguments.arguments(listOf(LottoRank.MISS), 0.00),
                Arguments.arguments(
                    listOf(LottoRank.THIRD, LottoRank.FIFTH, LottoRank.SECOND),
                    10_501.66,
                ),
            )
        }
    }
}
