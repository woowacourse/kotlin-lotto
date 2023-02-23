package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @Test
    fun `5개 번호가 일치하고, 보너스 번호를 포함하고 있다면 2등이다`() {
        // given
        val matchOfCount = 5
        val bonusMatch = true

        // when
        val actual = Rank.valueOf(matchOfCount, bonusMatch)

        // then
        val expected = Rank.SECOND
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource("0,true", "1,true", "2,true", "0,false", "1,false", "2,false")
    fun `일치하는 번호가 2개 이하면 보너스번호와 상관없이 MISS등수를 반환한다`(countOfMatch: Int, bonusMatch: Boolean) {
        // when
        val actual = Rank.valueOf(countOfMatch, bonusMatch)

        // then
        val expected = Rank.MISS
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("true", "false")
    fun `일치하는 번호가 3개면 보너스번호와 상관없이 FIFTH 등수를 반환한다`(bonusMatch: Boolean) {
        // given
        val countOfMatch = 3

        // when
        val actual = Rank.valueOf(countOfMatch, bonusMatch)

        // then
        val expected = Rank.FIFTH
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("true", "false")
    fun `일치하는 번호가 4개면 보너스번호와 상관없이 FOURTH 등수를 반환한다`(bonusMatch: Boolean) {
        // given
        val countOfMatch = 4

        // when
        val actual = Rank.valueOf(countOfMatch, bonusMatch)

        // then
        val expected = Rank.FOURTH
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스번호가 일치하지 않으면 THIRD 등수를 반환한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = false

        // when
        val actual = Rank.valueOf(countOfMatch, matchBonus)

        // then
        val expected = Rank.THIRD
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스번호가 일치하면 SECOND 등수를 반환한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = true

        // when
        val actual = Rank.valueOf(countOfMatch, matchBonus)

        // then
        val expected = Rank.SECOND
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("true", "false")
    fun `일치하는 번호가 6개면 보너스번호와 상관없이 FIRST 등수를 반환한다`(bonusMatch: Boolean) {
        // given
        val countOfMatch = 6

        // when
        val actual = Rank.valueOf(countOfMatch, bonusMatch)

        // then
        val expected = Rank.FIRST
        assertThat(actual).isEqualTo(expected)
    }
}
