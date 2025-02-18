package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class RankTest {
    @MethodSource("lottoRankTest")
    @ParameterizedTest
    fun `당첨 등수를 계산할 수 있다`(
        matchCount: Int,
        matchBonus: Boolean,
        rank: Rank,
    ) {
        assertThat(Rank.calculate(matchCount, matchBonus)).isEqualTo(rank)
    }

    companion object {
        @JvmStatic
        fun lottoRankTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(0, false, Rank.MISS),
                Arguments.arguments(3, false, Rank.FIFTH),
                Arguments.arguments(4, false, Rank.FOURTH),
                Arguments.arguments(5, false, Rank.THIRD),
                Arguments.arguments(5, true, Rank.SECOND),
                Arguments.arguments(6, false, Rank.FIRST),
            )
        }
    }
}
