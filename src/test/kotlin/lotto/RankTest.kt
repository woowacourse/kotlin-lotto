package lotto

import lotto.model.Rank
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

    @Test
    fun `Ordinal로 로또 등수 반환 일치 확인`() {
        val actual = Rank.THIRD
        val expected = Rank.getRankByOrdinal(2)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ordinal로 로또 등수 반환 불일치 확인`() {
        val actual = Rank.FIFTH
        val expected = Rank.getRankByOrdinal(2)

        assertThat(actual).isNotEqualTo(expected)
    }
}
