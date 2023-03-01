package lotto.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

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
                ),
                14000
            )
        Assertions.assertThat(winningResult.yield).isEqualTo(0.35)
    }

    @ParameterizedTest
    @MethodSource("gainCase")
    fun `수익률을 받아 1 이상이면 이득, 1 미만이면 손해로 판단한다`(winningResult: WinningResult, expected: Boolean) {
        Assertions.assertThat(winningResult.isGain).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun gainCase(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    WinningResult(
                        Rank.values().associateWith { 0 }.toMutableMap(),
                        5000
                    ),
                    false
                ),
                Arguments.of(
                    WinningResult(
                        Rank.values().associateWith { 1 }.toMutableMap(),
                        5000
                    ),
                    true
                )
            )
        }
    }
}
