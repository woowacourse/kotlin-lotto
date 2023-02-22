package lotto.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningResultTest {

    @Test
    fun `지불 금액을 받아 수익률을 계산해 알려준다`() {
        val winningResult: WinningResult =
            WinningResult(
                mapOf(
                    Pair(Rank.FIRST, 0),
                    Pair(Rank.SECOND, 0),
                    Pair(Rank.THIRD, 0),
                    Pair(Rank.FOURTH, 0),
                    Pair(Rank.FIFTH, 1)
                )
            )
        Assertions.assertThat(winningResult.calculateYield(14000)).isEqualTo(0.35)
    }

    @ParameterizedTest
    @CsvSource("0.1, false", "1.0, true")
    fun `수익률을 받아 1 이상이면 이득, 1 미만이면 손해로 판단한다`(yield: Double, expected: Boolean) {
        val winningResult: WinningResult = WinningResult(
            Rank.values().associateWith { 0 }.toMutableMap()
        )
        Assertions.assertThat(winningResult.isGain(yield)).isEqualTo(expected)
    }
}
