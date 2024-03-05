package model.lottery

import model.Quantity
import model.winning.WinningRank
import model.winning.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteriesTest {
    @Test
    fun `로또 여러 장을 저장할 수 있다`() {
        val lotteries =
            Lotteries(
                listOf(
                    Lottery.of(listOf(1, 2, 3, 4, 5, 6)),
                    Lottery.of(listOf(1, 2, 3, 4, 5, 6)),
                ),
            )
        assertThat(lotteries.lotteries.size).isEqualTo(2)
    }

    @Test
    fun `로또 뭉치들을 합쳐서 하나의 로또 뭉치로 만들 수 있다`() {
        val lotteries =
            Lotteries(
                listOf(
                    Lottery.of(listOf(1, 3, 5, 7, 9, 11)),
                    Lottery.of(listOf(2, 4, 6, 8, 10, 12)),
                ),
            )
        val otherLotteries =
            Lotteries(
                listOf(
                    Lottery.of(listOf(2, 4, 6, 8, 10, 12)),
                    Lottery.of(listOf(3, 6, 9, 12, 15, 18)),
                ),
            )
        assertThat(lotteries + otherLotteries).isEqualTo(
            Lotteries(
                listOf(
                    Lottery.of(listOf(1, 3, 5, 7, 9, 11)),
                    Lottery.of(listOf(2, 4, 6, 8, 10, 12)),
                    Lottery.of(listOf(2, 4, 6, 8, 10, 12)),
                    Lottery.of(listOf(3, 6, 9, 12, 15, 18)),
                ),
            ),
        )
    }

    @Test
    fun `로또들에 대해 당첨 로또(당첨 번호 & 보너스 번호)를 비교하여 최종 당첨 결과를 구한다`() {
        val lotteries =
            Lotteries(
                listOf(
                    Lottery.of(listOf(1, 2, 3, 4, 5, 6)),
                    Lottery.of(listOf(4, 5, 6, 7, 8, 9)),
                    Lottery.of(listOf(4, 5, 6, 10, 11, 12)),
                ),
            )
        val winningLottery =
            WinningLottery(
                Lottery.of(listOf(4, 5, 6, 10, 11, 12)),
                LotteryNumber.of(7),
            )
        val expected =
            WinningResult(
                mapOf(
                    WinningRank.FIRST to Quantity(1),
                    WinningRank.SECOND to Quantity(0),
                    WinningRank.THIRD to Quantity(0),
                    WinningRank.FOURTH to Quantity(0),
                    WinningRank.FIFTH to Quantity(2),
                    WinningRank.NONE to Quantity(0),
                ),
            )

        val winningResult = lotteries.evaluateWinning(winningLottery)

        assertThat(winningResult).isEqualTo(expected)
    }
}
