package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `당첨번호 등수를 확인한다`() {
        // given
        val countOfMatch = 1
        val matchBonus = false

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(Rank.MISS)
    }
}
