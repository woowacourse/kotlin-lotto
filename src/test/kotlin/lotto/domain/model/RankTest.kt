package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class RankTest {

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `일치하는 번호가 6개라면 1등이다`(isMatchBonus: Boolean) {
        val result = Rank.valueOf(6, isMatchBonus)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하면 2등이다`() {
        val result = Rank.valueOf(5, true)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하지 않으면 3등이다`() {
        val result = Rank.valueOf(5, false)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `일치하는 번호가 4개라면 4등이다`(isMatchBonus: Boolean) {
        val result = Rank.valueOf(4, isMatchBonus)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `일치하는 번호가 3개라면 5등이다`(isMatchBonus: Boolean) {
        val result = Rank.valueOf(3, isMatchBonus)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @ParameterizedTest
    @MethodSource("countOfMatch, matchBonus")
    fun `일치하는 번호가 2~0개라면 MISS다`(countOfMatch: Int, matchBonus: Boolean) {
        val result = Rank.valueOf(countOfMatch, matchBonus)
        assertThat(result).isEqualTo(Rank.MISS)
    }

    companion object {
        @JvmStatic
        fun `countOfMatch, matchBonus`(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(2, true),
                Arguments.arguments(2, false),
                Arguments.arguments(1, true),
                Arguments.arguments(1, false),
                Arguments.arguments(0, true),
                Arguments.arguments(0, false)
            )
        }
    }
}
