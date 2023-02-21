package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class YieldCalculatorTest {

    @ParameterizedTest
    @CsvSource(value = ["14:FIFTH:0.35", "10:FIFTH,SECOND:3000.5"], delimiter = ':')
    fun `수익률을 계산한다`(lottoCount: Int, ranks: String, expected: Double) {
        val ranks = makeRanks(ranks)
        val actual = YieldCalculator.calculateYield(lottoCount, ranks)
        assertThat(actual).isEqualTo(expected)
    }

    private fun makeRanks(ranks: String): List<Rank> {
        return ranks.split(",").map {
            Rank.valueOf(it)
        }
    }
}
