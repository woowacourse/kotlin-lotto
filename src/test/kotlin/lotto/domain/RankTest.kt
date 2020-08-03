package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RankTest {
    @ParameterizedTest
    @MethodSource("createCountOfSameNumberAndRank")
    internal fun `같은 숫자의 개수에 맞는 순위`(countOfSameNumber: Int, rank: Rank) {
        assertThat(Rank.of(countOfSameNumber)).isEqualTo(rank)
    }

    companion object {
        @JvmStatic
        fun createCountOfSameNumberAndRank() = listOf(
            Arguments.of(0, Rank.NONE),
            Arguments.of(3, Rank.FOURTH),
            Arguments.of(4, Rank.THIRD),
            Arguments.of(5, Rank.SECOND),
            Arguments.of(6, Rank.FIRST)
        )
    }
}
