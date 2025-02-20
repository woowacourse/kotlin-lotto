package lotto.domain.service

import lotto.domain.model.BonusNumber
import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
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
        winningNumbersData: List<Int>,
        lottoNumbersData: List<Int>,
        bonusNumberData: Int,
        actual: LottoRank,
    ) {
        val winningNumbers = WinningNumbers(winningNumbersData.map { LottoNumber(it) })
        val lotto = Lotto(lottoNumbersData.map { LottoNumber(it) })
        val bonusNumber = BonusNumber(winningNumbers, LottoNumber(bonusNumberData))
        val matchCount = LottoRankCalculator().calculate(lotto, winningNumbers, bonusNumber = bonusNumber)
        assertThat(matchCount).isEqualTo(actual)
    }

    companion object {
        @JvmStatic
        fun lottoRankCalculatorTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 4, 5, 6), 8, LottoRank.FIRST),
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 4, 8, 6), 8, LottoRank.SECOND),
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 7, 5, 6), 8, LottoRank.THIRD),
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 4, 8, 12), 8, LottoRank.FOURTH),
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 22, 8, 18), 8, LottoRank.FIFTH),
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(7, 8, 9, 10, 11, 12), 8, LottoRank.MISS),
            )
        }
    }
}
