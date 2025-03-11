package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개 번호가 맞으면 1등을 반환한다`() {
        // given
        val countOfMatch = 6
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.FIRST, rank)
    }

    @Test
    fun `5개 번호가 맞으면 3등을 반환한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.THIRD, rank)
    }

    @Test
    fun `4개 번호가 맞으면 4등을 반환한다`() {
        // given
        val countOfMatch = 4
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.FOURTH, rank)
    }

    @Test
    fun `3개 번호가 맞으면 5등을 반환한다`() {
        // given
        val countOfMatch = 3
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.FIFTH, rank)
    }

    @Test
    fun `2개 번호가 맞으면 MISS를 반환한다`() {
        // given
        val countOfMatch = 2
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.MISS, rank)
    }

    @Test
    fun `1개 번호가 맞으면 MISS를 반환한다`() {
        // given
        val countOfMatch = 1
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.MISS, rank)
    }

    @Test
    fun `0개 번호가 맞으면 MISS를 반환한다`() {
        // given
        val countOfMatch = 0
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.MISS, rank)
    }

    @Test
    fun `로또 내에 보너스 번호가 있으면 2등을 반환한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = true

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.SECOND, rank)
    }

    @Test
    fun `로또 내에 보너스 번호가 다르면 3등을 반환한다`() {
        // given
        val countOfMatch = 5
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.THIRD, rank)
    }

    @Test
    fun `3개 번호가 맞고 보너스 번호가 맞아도 5등을 반환한다`() {
        // given
        val countOfMatch = 3
        val matchBonus = true

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.FIFTH, rank)
    }

    @Test
    fun `3개 번호가 맞고 보너스 번호가 틀려도 5등을 반환한다`() {
        // given
        val countOfMatch = 3
        val matchBonus = false

        // when
        val rank = Rank.from(countOfMatch, matchBonus)

        // then
        assertEquals(Rank.FIFTH, rank)
    }
}
