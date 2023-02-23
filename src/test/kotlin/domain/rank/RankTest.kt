package domain.rank

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankTest {
    @Test
    fun `6개 일치하고 보너스 번호는 일치하지 않을 때, 1등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(SIX_MATCH, MISMATCH_BONUS)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `6개 일치하고 보너스 번호도 일치할 때, 1등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(SIX_MATCH, MATCH_BONUS)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개 일치하고 보너스 번호도 일치할 때, 2등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(FIVE_MATCH, MATCH_BONUS)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개 일치하고 보너스 번호는 일치하지 않을 때, 3등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(FIVE_MATCH, MISMATCH_BONUS)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개 일치하고 보너스 번호는 일치하지 않을 때, 4등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(FOUR_MATCH, MISMATCH_BONUS)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `4개 일치하고 보너스 번호도 일치할 때, 4등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(FOUR_MATCH, MATCH_BONUS)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개 일치하고 보너스 번호는 일치하지 않을 때, 5등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(THREE_MATCH, MISMATCH_BONUS)).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `3개 일치하고 보너스 번호도 일치할 때, 5등을 반환한다`() {
        Assertions.assertThat(Rank.valueOf(THREE_MATCH, MATCH_BONUS)).isEqualTo(Rank.FIFTH)
    }

    @ParameterizedTest
    @ValueSource(ints = [ZERO_MATCH, ONE_MATCH, TWO_MATCH])
    fun `3개 미만으로 일치하고 보너스 번호는 일치하지 않을 때, 5등을 반환한다`(countOfMatch: Int) {
        Assertions.assertThat(Rank.valueOf(countOfMatch, MISMATCH_BONUS)).isEqualTo(Rank.MISS)
    }

    @ParameterizedTest
    @ValueSource(ints = [ZERO_MATCH, ONE_MATCH, TWO_MATCH])
    fun `3개 미만으로 일치하고 보너스 번호도 일치할 때, 5등을 반환한다`(countOfMatch: Int) {
        Assertions.assertThat(Rank.valueOf(countOfMatch, MATCH_BONUS)).isEqualTo(Rank.MISS)
    }

    companion object {
        private val SIX_MATCH = Rank.FIRST.countOfMatch
        private val FIVE_MATCH = Rank.SECOND.countOfMatch
        private val FOUR_MATCH = Rank.FOURTH.countOfMatch
        private val THREE_MATCH = Rank.FIFTH.countOfMatch
        private const val TWO_MATCH = 2
        private const val ONE_MATCH = 1
        private const val ZERO_MATCH = 0

        private const val MATCH_BONUS = true
        private const val MISMATCH_BONUS = false
    }
}
