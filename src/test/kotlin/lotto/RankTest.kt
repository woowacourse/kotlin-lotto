package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RankTest {
    @ParameterizedTest
    @MethodSource("getSource")
    fun `맞춘 개수와 보너스 일치 여부에 따라 등수를 판단한다`(
        matchCount: Int,
        isMatchedBonus: Boolean,
        expected: Rank,
    ) {
        assertThat(Rank.valueOf(matchCount, isMatchedBonus))
            .isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun getSource(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(6, false, Rank.FIRST),
                Arguments.arguments(5, true, Rank.SECOND),
                Arguments.arguments(5, false, Rank.THIRD),
                Arguments.arguments(4, false, Rank.FOURTH),
                Arguments.arguments(3, false, Rank.FIFTH),
                Arguments.arguments(1, false, Rank.MISS),
            )
    }
}
