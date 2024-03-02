package model

import model.WinningResult.Companion.round
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.ExplicitLotteriesGenerationStrategy
import utils.ManualLotteriesGenerationStrategy

class WinningResultTest {
    @Test
    fun `4000원 투자로 1등, 2등, 3등, 꽝 한 장씩에 대한 결과 테스트`() {
        val winningLottery = Lottery.fromSet((1..6).toSet())
        val bonus = Bonus.fromInput("7", winningLottery)

        val manualInput =
            listOf(
                listOf("1", "2", "3", "7", "8", "9"),
                listOf("7", "8", "9", "10", "11", "12"),
            )
        val autoInput =
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(1, 2, 3, 4, 5, 7),
                setOf(1, 2, 3, 4, 5, 8),
                setOf(10, 11, 12, 13, 14, 15),
            )

        val manualLotteries = LotteryMachine.issueLotteries(ManualLotteriesGenerationStrategy(manualInput))
        val autoLotteries = LotteryMachine.issueLotteries(ExplicitLotteriesGenerationStrategy(autoInput))

        val purchaseInformation = PurchaseInformation(Amount(6000), ManualLotteryCount(manualInput.size))

        val ticket =
            LotteryMachine.issueTicket(manualLotteries, autoLotteries, purchaseInformation)

        val winningResult = WinningResult.of(ticket, winningLottery, bonus)

        val prizeMoney = Rank.FIRST.winningMoney + Rank.SECOND.winningMoney + Rank.THIRD.winningMoney + Rank.FIFTH.winningMoney
        val amount = purchaseInformation.amount.money

        val expectedROI = (prizeMoney / amount.toDouble()).round(2)

        assertThat(winningResult.stats[Rank.FIRST]).isEqualTo(1)
        assertThat(winningResult.stats[Rank.SECOND]).isEqualTo(1)
        assertThat(winningResult.stats[Rank.THIRD]).isEqualTo(1)
        assertThat(winningResult.stats[Rank.FIFTH]).isEqualTo(1)
        assertThat(winningResult.stats[Rank.MISS]).isEqualTo(2)

        assertThat(winningResult.roi).isEqualTo(expectedROI)
    }
}
