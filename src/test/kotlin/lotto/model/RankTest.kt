package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개의 당첨번호와 보너스 번호가 일치하면 1등이다`() {
        val rank = Rank.valueOf(6, true)
        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `6개의 당첨번호와 보너스 번호가 일치하지 않으면 1등이다`() {
        val rank = Rank.valueOf(6, false)
        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개의 당첨번호와 보너스 번호가 일치하면 2등이다`() {
        val rank = Rank.valueOf(5, true)
        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개의 당첨번호가 일치하고 보너스 번호가 일치하지 않으면 3등이다`() {
        val rank = Rank.valueOf(5, false)
        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개의 당첨번호와 보너스 번호가 일치하면 4등이다`() {
        val rank = Rank.valueOf(4, true)
        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `4개의 당첨번호와 보너스 번호가 일치하지 않으면 4등이다`() {
        val rank = Rank.valueOf(4, false)
        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개의 당첨번호와 보너스 번호가 일치하면 5등이다`() {
        val rank = Rank.valueOf(3, true)
        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `3개의 당첨번호와 보너스 번호가 일치하지 않으면 5등이다`() {
        val rank = Rank.valueOf(3, false)
        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `2개의 당첨번호와 보너스 번호가 일치하면 꽝이다`() {
        val rank = Rank.valueOf(2, true)
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    @Test
    fun `2개의 당첨번호와 보너스 번호가 일치하지 않으면 꽝이다`() {
        val rank = Rank.valueOf(2, false)
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    @Test
    fun `1개의 당첨번호와 보너스 번호가 일치하면 꽝이다`() {
        val rank = Rank.valueOf(1, true)
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    @Test
    fun `1개의 당첨번호와 보너스 번호가 일치하지 않으면 꽝이다`() {
        val rank = Rank.valueOf(1, false)
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    @Test
    fun `0개의 당첨번호와 보너스 번호가 일치하면 꽝이다`() {
        val rank = Rank.valueOf(0, true)
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    @Test
    fun `0개의 당첨번호와 보너스 번호가 일치하지 않으면 꽝이다`() {
        val rank = Rank.valueOf(0, false)
        assertThat(rank).isEqualTo(Rank.MISS)
    }
}
