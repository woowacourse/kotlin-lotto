package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank
import lotto.domain.model.WinningNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoRankCalculatorTest {
    @MethodSource("lottoRankCalculatorTest")
    @ParameterizedTest
    fun `로또 랭크를 구할 수 있다`(
        lotto: Lotto,
        actual: LottoRank,
    ) {
        val winningNumbers = WinningNumbers(listOf(1, 2, 3, 4, 5, 6), 8)
        val lottoRankCalculator = LottoRankCalculator()
        assertThat(lottoRankCalculator.calculate(listOf(lotto), winningNumbers).lottoRanks).isEqualTo(listOf(actual))
    }

    companion object {
        @JvmStatic
        fun lottoRankCalculatorTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoRank.FIRST),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 8, 6)), LottoRank.SECOND),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 7, 5, 6)), LottoRank.THIRD),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 8, 12)), LottoRank.FOURTH),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 22, 8, 18)), LottoRank.FIFTH),
                Arguments.arguments(Lotto(listOf(7, 8, 9, 10, 11, 12)), LottoRank.MISS),
            )
        }
    }
}
