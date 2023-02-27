package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Lottery(vararg numbers: Int): Lottery {
    return Lottery(numbers.map { LotteryNumber.from(it) })
}

class LotteryResultTest {
    @Test
    fun `로또를 받아 등수를 카운트한다`() {
        val tickets = listOf(
            Lottery(1, 2, 3, 4, 5, 6),
            Lottery(1, 3, 4, 5, 6, 7),
            Lottery(1, 3, 4, 5, 6, 8),
            Lottery(1, 2, 3, 7, 8, 9),
            Lottery(1, 2, 3, 9, 10, 11)
        )
        val winningLottery = WinningLottery(Lottery(1, 2, 3, 4, 5, 6), LotteryNumber.from(7))

        val result = LotteryResult(tickets, winningLottery)

        assertThat(result.ranks[Rank.FIRST]).isEqualTo(1)
        assertThat(result.ranks[Rank.SECOND]).isEqualTo(1)
        assertThat(result.ranks[Rank.THIRD]).isEqualTo(1)
        assertThat(result.ranks[Rank.FOURTH]).isEqualTo(0)
        assertThat(result.ranks[Rank.FIFTH]).isEqualTo(2)
        assertThat(result.ranks[Rank.MISS]).isEqualTo(0)
    }

    @Test
    fun `수익률을 계산한다`() {
        val tickets = listOf(
            Lottery(1, 2, 3, 4, 5, 6),
            Lottery(1, 3, 4, 5, 6, 7),
            Lottery(1, 3, 4, 5, 6, 8),
            Lottery(1, 2, 3, 7, 8, 9),
            Lottery(1, 2, 3, 9, 10, 11)
        )
        val winningLottery = WinningLottery(Lottery(1, 2, 3, 4, 5, 6), LotteryNumber.from(7))
        val result = LotteryResult(tickets, winningLottery)

        val actual = result.getProfit(PurchaseAmount(50000))

        assertThat(actual).isEqualTo(40_630.20)
    }
}
