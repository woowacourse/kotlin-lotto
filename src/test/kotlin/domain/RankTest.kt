package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RankTest {
    @ParameterizedTest(name = "{0}개 맞고 보너스볼 매치 {1}일 경우 {2}")
    @MethodSource("countOfMatchAndMatchBonusProvider")
    fun `당첨 번호와 보너스 볼 매치 여부로 당첨 등수 확인`(countOfMatch: Int, matchBonus: Boolean, expected: Rank) {
        val result = Rank.valueOf(countOfMatch, matchBonus)
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun countOfMatchAndMatchBonusProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(6, false, Rank.FIRST),
                Arguments.arguments(5, true, Rank.SECOND),
                Arguments.arguments(5, false, Rank.THIRD),
                Arguments.arguments(4, false, Rank.FOURTH),
                Arguments.arguments(3, false, Rank.FIFTH),
                Arguments.arguments(1, false, Rank.MISS),
                Arguments.arguments(0, false, Rank.MISS),
                Arguments.arguments(0, true, Rank.MISS)
            )
        }
    }
}
