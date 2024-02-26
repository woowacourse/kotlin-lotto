package model.lottery

import model.Money
import model.PrizeCount
import model.WinningRank
import model.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteryPrizeCalculatorTest {
    @Test
    fun `로또의 총 당첨금을 계산한다`() {
        val lotteryPrizeCalculator = LotteryPrizeCalculator()
        val winningResult =
            WinningResult(
                mapOf(
                    WinningRank.FIRST to PrizeCount(0),
                    WinningRank.SECOND to PrizeCount(1),
                    WinningRank.THIRD to PrizeCount(0),
                    WinningRank.FOURTH to PrizeCount(1),
                    WinningRank.FIFTH to PrizeCount(1),
                    WinningRank.NONE to PrizeCount(0),
                ),
            )
        val lotteriesPrize = lotteryPrizeCalculator.calculate(winningResult)
        assertThat(lotteriesPrize).isEqualTo(Money.wons(30_055_000))
    }
}
