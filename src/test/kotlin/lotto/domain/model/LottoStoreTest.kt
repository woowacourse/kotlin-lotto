package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoStoreTest {
    @ParameterizedTest
    @MethodSource("calculateTotalReturnTest")
    fun `총 수익률을 계산할 수 있다`(
        lottoRanks: Map<LottoRank, Int>,
        expectedReturn: Double,
    ) {
        val totalReturn = LottoStore(lottoRanks).calculateTotalReturn()
        assertThat(totalReturn.toDouble()).isEqualTo(expectedReturn)
    }

    companion object {
        @JvmStatic
        fun calculateTotalReturnTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(mapOf(LottoRank.SECOND to 1), 30000.00),
                Arguments.arguments(mapOf(LottoRank.MISS to 1), 0.00),
                Arguments.arguments(
                    mapOf(LottoRank.THIRD to 1, LottoRank.FIFTH to 1, LottoRank.SECOND to 1),
                    10_501.66,
                ),
            )
        }
    }
}
