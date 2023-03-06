package model.domain

import model.domain.Rank.* // ktlint-disable no-wildcard-imports
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `1등인지 확인한다`() {
        // given
        val countOfMatch = 6
        val matchBonus = false

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(FIRST)
    }

    @Test
    fun `2등인지 확인한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = true

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(SECOND)
    }

    @Test
    fun `3등인지 확인한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = false

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(THIRD)
    }

    @Test
    fun `4등인지 확인한다`() {
        // given
        val countOfMatch = 4
        val matchBonus = false

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(FOURTH)
    }

    @Test
    fun `5등인지 확인한다`() {
        // given
        val countOfMatch = 3
        val matchBonus = false

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(FIFTH)
    }

    @Test
    fun `꼴등인지 확인한다`() {
        // given
        val countOfMatch = 1
        val matchBonus = true

        val actual = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(actual).isEqualTo(MISS)
    }
}
