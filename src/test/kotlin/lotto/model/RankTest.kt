package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `로또 당첨 등수 일치 확인`() {
        val countOfMatch = 5
        val bonusMatched = true

        val actual = Rank.SECOND
        val expected = Rank.getRank(countOfMatch, bonusMatched)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 당첨 등수 불일치 확인`() {
        val countOfMatch = 5
        val bonusMatched = false

        val actual = Rank.SECOND
        val expected = Rank.getRank(countOfMatch, bonusMatched)

        assertThat(actual).isNotEqualTo(expected)
    }
}
