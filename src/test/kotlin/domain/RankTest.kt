package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `일치하는 개수가 6개이고 보너스번호가 같아도 1등`() {
        val result = Rank.valueOf(6, true)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `일치하는 개수가 6개이고 보너스번호가 달라도 1등`() {
        val result = Rank.valueOf(6, true)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `일치하는 개수가 5개고 보너스번호가 같으면 2등`() {
        val result = Rank.valueOf(5, true)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `일치하는 개수가 5개고 보너스번호가 다르면 3등`() {
        val result = Rank.valueOf(5, false)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `일치하는 개수가 4개고 보너스번호가 같아도 4등`() {
        val result = Rank.valueOf(4, true)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `일치하는 개수가 4개고 보너스번호가 달라도 4등`() {
        val result = Rank.valueOf(4, false)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `일치하는 개수가 3개고 보너스번호가 같아도 5등`() {
        val result = Rank.valueOf(3, true)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `일치하는 개수가 3개고 보너스번호가 달라도 5등`() {
        val result = Rank.valueOf(3, false)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }
}
