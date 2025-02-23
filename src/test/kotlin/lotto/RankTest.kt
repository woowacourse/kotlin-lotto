package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RankTest {
    @ParameterizedTest(name = "{0}개의 번호를 맞추고 보너스 번호 일치 여부가 {1}라면 등수는 {2}다")
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
                Arguments.arguments(2, false, Rank.MISS),
                Arguments.arguments(1, false, Rank.MISS),
                Arguments.arguments(0, false, Rank.MISS),
            )
    }
}
