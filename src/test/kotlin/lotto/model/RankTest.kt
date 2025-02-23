package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개 번호가 맞으면 1등을 반환한다`() {
        testRankByCountOfMatch(6, Rank.FIRST)
    }

    @Test
    fun `5개 번호가 맞으면 3등을 반환한다`() {
        testRankByCountOfMatch(5, Rank.THIRD)
    }

    @Test
    fun `4개 번호가 맞으면 4등을 반환한다`() {
        testRankByCountOfMatch(4, Rank.FOURTH)
    }

    @Test
    fun `3개 번호가 맞으면 5등을 반환한다`() {
        testRankByCountOfMatch(3, Rank.FIFTH)
    }

    @Test
    fun `2개 번호가 맞으면 MISS를 반환한다`() {
        testRankByCountOfMatch(2, Rank.MISS)
    }

    @Test
    fun `1개 번호가 맞으면 MISS를 반환한다`() {
        testRankByCountOfMatch(1, Rank.MISS)
    }

    @Test
    fun `0개 번호가 맞으면 MISS를 반환한다`() {
        testRankByCountOfMatch(0, Rank.MISS)
    }

    private fun testRankByCountOfMatch(
        countOfMatch: Int,
        expectedRank: Rank,
    ) {
        val matchBonus = false
        val rank = Rank.from(countOfMatch, matchBonus)

        assertEquals(expectedRank, rank)
    }

    @Test
    fun `로또 내에 보너스 번호가 있으면 2등을 반환한다`() {
        testRankByBonus(5, true, Rank.SECOND)
    }

    @Test
    fun `로또 내에 보너스 번호가 다르면 3등을 반환한다`() {
        testRankByBonus(5, false, Rank.THIRD)
    }

    @Test
    fun `3개 번호가 맞고 보너스 번호가 맞아도 5등을 반환한다`() {
        testRankByBonus(3, true, Rank.FIFTH)
    }

    @Test
    fun `3개 번호가 맞고 보너스 번호가 틀려도 5등을 반환한다`() {
        testRankByBonus(3, false, Rank.FIFTH)
    }

    private fun testRankByBonus(
        countOfMatch: Int,
        matchBonus: Boolean,
        expectedRank: Rank,
    ) {
        val rank = Rank.from(countOfMatch, matchBonus)

        assertEquals(expectedRank, rank)
    }
}
