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
}
