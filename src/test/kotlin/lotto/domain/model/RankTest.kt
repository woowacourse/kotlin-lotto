package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RankTest {
    @ParameterizedTest
    @MethodSource("countOfMatch, matchBonus, Rank")
    fun `일치 개수와 보너스 매치 여부에 따른 랭크 반환 테스트`(countOfMatch: Int, matchBonus: Boolean, rank: Rank) {
        val result = Rank.valueOf(countOfMatch, matchBonus)
        assertThat(result).isEqualTo(rank)
    }

    companion object {
        @JvmStatic
        fun `countOfMatch, matchBonus, Rank`(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(6, false, Rank.FIRST),
                Arguments.arguments(5, true, Rank.SECOND),
                Arguments.arguments(5, false, Rank.THIRD),
                Arguments.arguments(4, false, Rank.FOURTH),
                Arguments.arguments(3, false, Rank.FIFTH),
                Arguments.arguments(2, false, Rank.MISS),
                Arguments.arguments(1, false, Rank.MISS),
                Arguments.arguments(0, false, Rank.MISS)
            )
        }
    }
}
