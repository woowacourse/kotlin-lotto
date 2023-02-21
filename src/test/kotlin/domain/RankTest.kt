package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankTest {

    @ValueSource(booleans = [false, true])
    @ParameterizedTest
    fun `보너스 번호 일치 여부와 관계 없이 모든 번호를 맞추면 1등이다`(isBonusMatch: Boolean) {
        // given
        val countMatch: Int = 6

        // when
        val actual = Rank.valueOf(countMatch, isBonusMatch)

        // then
        assertThat(actual).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개의 번호와 보너스 번호를 맞추면 2등이다`() {
        // given
        val countOfMatch: Int = 5
        val matchBonus: Boolean = true

        // when
        val actual = Rank.valueOf(countOfMatch, matchBonus)

        // then
        assertThat(actual).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개의 번호를 맞추고 보너스 번호를 못맞추면 3등이다`() {
        // given
        val countOfMatch: Int = 5
        val matchBonus: Boolean = false

        // when
        val actual = Rank.valueOf(countOfMatch, matchBonus)

        // then
        assertThat(actual).isEqualTo(Rank.THIRD)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    fun `4개의 번호를 맞추면 보너스 번호 일치여부와 관계없이 4등이다`(isBonusMatch: Boolean) {
        // given
        val countOfMatch: Int = 4

        // when
        val actual = Rank.valueOf(countOfMatch, isBonusMatch)

        // then
        assertThat(actual).isEqualTo(Rank.FOURTH)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    fun `3개의 번호를 맞추면 보너스 번호 일치여부와 관계없이 5등이다`(isBonusMatch: Boolean) {
        // given
        val countOfMatch: Int = 3

        // when
        val actual = Rank.valueOf(countOfMatch, isBonusMatch)

        // then
        assertThat(actual).isEqualTo(Rank.FIFTH)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    fun `2개의 번호를 맞추면 보너스 번호 일치여부와 상관 없이 MISS 이다`(isBonusMatch: Boolean) {
        // given
        val countOfMatch: Int = 2

        // when
        val actual = Rank.valueOf(countOfMatch, isBonusMatch)

        // then
        assertThat(actual).isEqualTo(Rank.MISS)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    fun `1개의 번호를 맞추면 보너스 번호 일치여부와 관계없이 MISS이다`(isBonusMatch: Boolean) {
        // given
        val countOfMatch: Int = 1

        // when
        val actual = Rank.valueOf(countOfMatch, isBonusMatch)

        // then
        assertThat(actual).isEqualTo(Rank.MISS)
    }

    @Test
    fun `0개의 번호를 맞추면 보너스 번호 일치여부와 관계없이 MISS이다`() {
        // given
        val countOfMatch: Int = 0

        // when
        val actual = Rank.valueOf(countOfMatch, false)

        // then
        assertThat(actual).isEqualTo(Rank.MISS)
    }
}
