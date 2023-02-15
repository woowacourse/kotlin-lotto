package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningResultTest {

    @Test
    fun `일치 등수의 개수를 기록한다`() {
        val winningResult = WinningResult(mutableListOf(1, 0, 0, 0, 0, 0))
        assertThat(winningResult.countMatchRanks).isEqualTo(mutableListOf(1, 0, 0, 0, 0, 0))
    }

    @Test
    fun `등수를 받아 카운트한다`() {
        val winningResult = WinningResult(mutableListOf(1, 0, 0, 0, 0, 0))
        winningResult.countRank(Rank.SECOND)
        assertThat(winningResult.countMatchRanks).isEqualTo(listOf(1, 1, 0, 0, 0, 0))
    }

    @Test
    fun `지불 금액을 받아 수익률을 계산해 알려준다`() {
        val winningResult = WinningResult(mutableListOf(0, 0, 0, 0, 1, 13))
        assertThat(winningResult.calculateYield(14000)).isEqualTo(0.35)
    }

    @ParameterizedTest
    @CsvSource("0.1, false", "1.0, true")
    fun `수익률을 받아 1 이상이면 이득, 1 미만이면 손해로 판단한다`(yield: Double, expected: Boolean) {
        val winningResult = WinningResult()
        assertThat(winningResult.isGain(yield)).isEqualTo(expected)
    }
}
