package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankTest {
    @Test
    fun `6개가 일치하면 1등 순위가 반환된다`() {
        val result = Rank.valueOf(6, false)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개가 일치하고 보너스 번호가 일치하면 2등 순위가 반환된다`() {
        val result = Rank.valueOf(5, true)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개가 일치하고 보너스 번호가 일치하지 않으면 3등 순위가 반환된다`() {
        val result = Rank.valueOf(5, false)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `4개가 일치하면 4등 순위가 반환된다`(isMatchBonus: Boolean) {
        val result = Rank.valueOf(4, isMatchBonus)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `3개가 일치하면 5등 순위가 반환된다`(isMatchBonus: Boolean) {
        val result = Rank.valueOf(3, isMatchBonus)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `2개 이하로 일치하면 MISS가 반환된다`() {
        val result = Rank.valueOf(2, false)
        assertThat(result).isEqualTo(Rank.MISS)
    }
}
