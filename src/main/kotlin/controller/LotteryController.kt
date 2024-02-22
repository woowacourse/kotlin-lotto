package controller

import model.*
import view.InputView
import view.OutputView
import java.math.BigDecimal

class LotteryController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryResultEvaluator: LotteryResultEvaluator = LotteryResultEvaluator(),
    private val profit: Profit = Profit()
) {

    fun start() {
        val purchaseAmount = Money.from(inputView.readPurchaseAmount())
    private fun lotterySellingService(purchaseAmount: Money): Lotteries {
        val lotterySeller = LotterySeller(purchaseAmount)
        val lotteryCount = lotterySeller.getLotteryCount()

        val lotteries = getLotteries(lotteryCount)
        outputView.showPurchasedLotteries(lotteries)

        return lotteries
    }

        val winningResult = lotteryResultEvaluator.evaluate(lotteries, winningNumbers, bonusNumber)
        outputView.showWinningResult(winningResult)

        var totalPrize = Money(BigDecimal(0))
        for (result in winningResult.result) {
            totalPrize += (result.key.winningPrize * result.value)
        }

        val profitRate = profit.calculateRate(purchaseAmount, totalPrize)

        outputView.showProfitRate(profitRate, ProfitStatusDecider.decide(purchaseAmount, totalPrize))
    }

    private fun getProfitRate(
        purchaseAmount: Money,
        totalPrize: Money,
    ): ProfitRate = profit.calculateRate(purchaseAmount, totalPrize)
}
