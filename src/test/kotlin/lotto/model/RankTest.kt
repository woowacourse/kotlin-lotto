package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class RankTest {
    @ParameterizedTest
    @MethodSource("countOfMatchCasesForRankTest")
    fun `당첨 번호와 중복되는 개수에 따라 알맞은 랭크가 2등을 제외하고 반환된다`(
        countOfMatch: Int,
        expectedRank: Rank,
    ) {
        val matchBonus = false
        val rankByCountOfMatch = Rank.fromMatchResult(countOfMatch, matchBonus)

        Assertions.assertEquals(expectedRank, rankByCountOfMatch)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    fun `보너스 번호의 여부에 따라 2등 또는 3등이 반환된다`(matchBonus: Boolean) {
        val expectedRank = if (matchBonus) Rank.SECOND else Rank.THIRD

        val countOfMatch = 5
        val actualRank = Rank.fromMatchResult(countOfMatch, matchBonus)

        Assertions.assertEquals(expectedRank, actualRank)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    fun `3개가 맞은 상태에서 보너스 번호가 맞으면 5등이 반환된다`(matchBonus: Boolean) {
        val expectedRank = Rank.FIFTH

        val countOfMatch = 3
        val actualRank = Rank.fromMatchResult(countOfMatch, matchBonus)

        Assertions.assertEquals(expectedRank, actualRank)
    }

    companion object {
        @JvmStatic
        fun countOfMatchCasesForRankTest(): Stream<Arguments> =
            Stream.of(
                Arguments.of(6, Rank.FIRST),
                Arguments.of(5, Rank.THIRD),
                Arguments.of(4, Rank.FOURTH),
                Arguments.of(3, Rank.FIFTH),
                Arguments.of(2, Rank.MISS),
                Arguments.of(1, Rank.MISS),
                Arguments.of(0, Rank.MISS),
            )
    }
}
