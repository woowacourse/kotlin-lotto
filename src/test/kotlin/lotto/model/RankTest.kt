package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `3개의 당첨번호와 보너스 번호가 일치하면 5등이다`() {
        val rank = Rank.valueOf(3, true)
        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `6개의 당첨번호와 보너스 번호가 일치하면 1등이다`() {
        val rank = Rank.valueOf(6, true)
        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개의 당첨번호가 일치하고 보너스 번호가 일치하지 않으면 3등이다`() {
        val rank = Rank.valueOf(5, false)
        assertThat(rank).isEqualTo(Rank.THIRD)
    }
}
