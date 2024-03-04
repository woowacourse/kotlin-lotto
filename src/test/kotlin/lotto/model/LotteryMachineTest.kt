package lotto.model

import lotto.model.lottery.Lottery
import lotto.model.lottery.strategy.ExplicitLotteriesGenerationStrategy
import lotto.model.lottery.strategy.ManualLotteriesGenerationStrategy
import lotto.model.lottery.strategy.RandomLotteriesGenerationStrategy
import lotto.model.puchaseinformation.PurchaseInformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LotteryMachineTest {
    @Test
    fun `수동 로또가 구매한 갯수만큼 발행에 성공`() {
        val manualInput =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18),
            )

        val manualLotteries = LotteryMachine.issueLotteries(ManualLotteriesGenerationStrategy(manualInput))

        assertThat(manualLotteries.size).isEqualTo(manualInput.size)
    }

    @ParameterizedTest
    @CsvSource("10000, 4", "3000, 1", "6500, 5", "6100, 6")
    fun `자동 로또가 구매한 갯수만큼 발행에 성공`(
        money: Int,
        count: Int,
    ) {
        val purchaseInformation = PurchaseInformation(money, count)

        val autoLotteries = LotteryMachine.issueLotteries(RandomLotteriesGenerationStrategy(purchaseInformation))

        val amount = purchaseInformation.amount
        val manualLotteryCount = purchaseInformation.manualLotteryCount

        val totalLotteryCount = amount / 1000

        val expectedAutoLotteryCount = totalLotteryCount - manualLotteryCount
        assertThat(autoLotteries.size).isEqualTo(expectedAutoLotteryCount)
    }

    @Test
    fun `전략 패턴을 이용하여 자동 로또 값을 지정해서 테스트`() {
        val explicitNumbers =
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(7, 8, 9, 10, 11, 12),
            )
        val explicitLotteries = explicitNumbers.map { Lottery.fromSet(it) }.toList()

        val autoLotteries = LotteryMachine.issueLotteries(ExplicitLotteriesGenerationStrategy(explicitNumbers))

        autoLotteries.forEachIndexed { index, userLottery ->
            assertThat(userLottery.getCountOfMatch(explicitLotteries[index])).isEqualTo(6)
        }
    }
}
