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

        assertThat(counter.numberOfEachRank["FIRST"]).isEqualTo(1)
        assertThat(counter.numberOfEachRank["SECOND"]).isEqualTo(1)
        assertThat(counter.numberOfEachRank["THIRD"]).isEqualTo(1)
        assertThat(counter.numberOfEachRank["FOURTH"]).isEqualTo(0)
        assertThat(counter.numberOfEachRank["FIFTH"]).isEqualTo(2)
        assertThat(counter.numberOfEachRank["MISS"]).isEqualTo(0)
    }

    @Test
    fun `총 상금을 계산한다`() {
        val counter = RankCounter(
            mapOf(
                "FIRST" to 2,
                "SECOND" to 0,
                "THIRD" to 1,
                "FOURTH" to 0,
                "FIFTH" to 1
            )
        )

        val actual = counter.calculateTotalPrize()

        assertThat(actual).isEqualTo(4001505000)
    }
}
