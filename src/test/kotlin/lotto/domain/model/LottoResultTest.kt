package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoResultTest {
    @ParameterizedTest(name = "로또 등수가 {0}일 때, 수익률은 \"{1}\"이다")
    @MethodSource("provideLottoRanksAndReturns")
    fun `총 수익률을 계산할 수 있다`(
        lottoRanks: List<LottoRank>,
        expectedReturn: Double,
    ) {
        val totalReturn = LottoResult(lottoRanks).calculateTotalReturn(1000)
        assertThat(totalReturn.toDouble()).isEqualTo(expectedReturn)
    }

    companion object {
        @JvmStatic
        fun provideLottoRanksAndReturns(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(LottoRank.FIRST), 2000000.00),
                Arguments.of(listOf(LottoRank.SECOND), 30000.00),
                Arguments.of(listOf(LottoRank.THIRD), 1500.00),
                Arguments.of(listOf(LottoRank.FOURTH, LottoRank.FIFTH), 27.50),
                Arguments.of(listOf(LottoRank.MISS), 0.00),
                Arguments.of(listOf(LottoRank.THIRD, LottoRank.FIFTH, LottoRank.SECOND), 10501.66),
            )
    }
}
