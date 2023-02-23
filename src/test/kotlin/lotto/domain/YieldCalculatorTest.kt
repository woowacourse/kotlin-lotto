package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class YieldCalculatorTest {

    @MethodSource("yieldTestNumbers")
    @ParameterizedTest
    fun `수익률을 계산한다`(lottoCount: Int, ranks: List<Rank>, yield: Double) {
        assertThat(YieldCalculator.calculateYield(ranks)).isEqualTo(yield)
    }

    companion object {
        @JvmStatic
        fun yieldTestNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(4, listOf(Rank.FIFTH, Rank.MISS, Rank.MISS, Rank.MISS), 1.25),
                Arguments.of(3, listOf(Rank.FIFTH, Rank.SECOND, Rank.MISS), 10001.66)
            )
        }
    }
}
