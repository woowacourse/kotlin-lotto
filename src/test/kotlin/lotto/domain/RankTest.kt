package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class RankTest {
    @MethodSource("rankNumbers")
    @ParameterizedTest
    fun `당첨 등수를 구한다`(countOfMatch: Int, matchBonus: Boolean, rank: Rank) {
        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(rank)
    }

    @Test
    fun `일치하는 번호의 개수는 0과 6 사이여야 한다`() {
        assertThrows<IllegalArgumentException> { Rank.valueOf(7, true) }
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
