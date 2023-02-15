package lotto.domain

import lotto.constants.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class RankTest {
    @MethodSource("rankNumbers")
    @ParameterizedTest
    fun `당첨 등수를 구한다`(countOfMatch: Int, matchBonus: Boolean, rank: Rank){
        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(rank)
    }

    companion object {
        @JvmStatic
        fun rankNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, true, Rank.FOURTH),
                Arguments.of(1, true, Rank.MISS),
                )
        }
    }
}