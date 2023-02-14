package lotto.domain

import lotto.constants.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class YieldCalculatorTest {

    @MethodSource("yieldTestNumbers")
    @ParameterizedTest
    fun `수익률을 계산한다`(lottoCount: Int, ranks: List<Rank>, yield: Double) {
        val calculator = YieldCalculator()
        assertThat(calculator.calculateYield(lottoCount, ranks)).isEqualTo(yield)
    }

    companion object {
        @JvmStatic
        fun yieldTestNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(14, listOf(Rank.FIFTH), 0.35),
                Arguments.of(10, listOf(Rank.FIFTH, Rank.SECOND), 3000.5)
            )
        }
    }
}