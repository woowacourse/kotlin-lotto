package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RankTest {
    @ParameterizedTest
    @MethodSource("getStatistics")
    fun `당첨번호와 보너스볼 매치 갯수에 따라 당첨 등수 확인 `(
        countOfMatch: Int,
        matchBonus: Boolean,
        rank: Rank,
    ) {
        val result = Rank.fromMatch(countOfMatch, matchBonus)
        assertThat(result).isEqualTo(rank)
    }

    private fun getStatistics() =
        listOf(
            Arguments.of(6, false, Rank.FIRST),
            Arguments.of(5, true, Rank.SECOND),
            Arguments.of(5, false, Rank.THIRD),
            Arguments.of(4, false, Rank.FOURTH),
            Arguments.of(3, false, Rank.FIFTH),
            Arguments.of(2, false, Rank.MISS),
        )
}
