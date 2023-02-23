package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Lottery(vararg numbers: Int): Lottery {
    return Lottery(numbers.map { LotteryNumber.from(it) })
}

class RankCounterTest {
    @Test
    fun `로또를 받아 등수를 카운트한다`() {
        val counter = RankCounter()
        val lotteries = listOf(
            Lottery(1, 2, 3, 4, 5, 6),
            Lottery(1, 3, 4, 5, 6, 7),
            Lottery(1, 3, 4, 5, 6, 8),
            Lottery(1, 2, 3, 7, 8, 9),
            Lottery(1, 2, 3, 9, 10, 11)
        )
        val winningLottery = WinningLottery(Lottery(1, 2, 3, 4, 5, 6), LotteryNumber.from(7))

        counter.count(lotteries, winningLottery)

        assertThat(counter.numberOfEachRank[Rank.FIRST]).isEqualTo(1)
        assertThat(counter.numberOfEachRank[Rank.SECOND]).isEqualTo(1)
        assertThat(counter.numberOfEachRank[Rank.THIRD]).isEqualTo(1)
        assertThat(counter.numberOfEachRank[Rank.FOURTH]).isEqualTo(0)
        assertThat(counter.numberOfEachRank[Rank.FIFTH]).isEqualTo(2)
        assertThat(counter.numberOfEachRank[Rank.MISS]).isEqualTo(0)
    }

    @Test
    fun `총 상금을 계산한다`() {
        val counter = RankCounter(
            mapOf(
                Rank.FIRST to 2,
                Rank.SECOND to 0,
                Rank.THIRD to 1,
                Rank.FOURTH to 0,
                Rank.FIFTH to 1,
                Rank.MISS to 5
            )
        )

        val actual = counter.calculateTotalPrize()

        assertThat(actual).isEqualTo(4001505000)
    }
}
