package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `번호를 맞춘 개 수에 따라 알맞은 등수를 반환한다`() {
        // given
        val countOfMatch: Int = 5
        val matchBonus: Boolean = true

        // when
        val actual = Rank.valueOf(countOfMatch, matchBonus)

        // then
        assertThat(actual).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `MISS를 제외한 모든 Rank를 반환한다`() {
        // given
        val expected = listOf<Rank>(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH)

        // when
        val actual = Rank.validValues()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
