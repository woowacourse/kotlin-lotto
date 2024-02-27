package controller

import WinningLottery
import model.Money
import model.Quantity
import model.WinningResult
import model.lottery.Lotteries
import model.lottery.Lottery
import model.lottery.LotteryMachine
import model.lottery.LotteryNumber
import model.lottery.LotteryPrizeCalculator
import model.lottery.LotteryResultEvaluator
import model.lottery.LotterySeller
import model.profit.Profit
import model.profit.ProfitStatusDecider
import view.InputView
import view.OutputView

class LotteryController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lotterySeller: LotterySeller,
    private val lotteryMachine: LotteryMachine,
    private val lotteryResultEvaluator: LotteryResultEvaluator,
    private val profit: Profit,
    private val profitStatusDecider: ProfitStatusDecider,
) {
    fun start() {
        val purchaseAmount = Money.from(inputView.readPurchaseAmount())
        val lotteries = buyLotteries(purchaseAmount)

        val winningResult = calculateWinningResult(lotteries)
        calculateProfitRate(winningResult, purchaseAmount)
    }

    private fun buyLotteries(purchaseAmount: Money): Lotteries {
        val lotteryQuantity = lotterySeller.getLotteryQuantity(purchaseAmount)
        val manualLotteryQuantity = Quantity.from(inputView.readManualLotteryQuantity())
        val randomLotteryQuantity = lotteryQuantity - manualLotteryQuantity

        val lotteries = buyManualLotteries(manualLotteryQuantity) + buyRandomLotteries(randomLotteryQuantity)
        outputView.showLotteriesType(manualLotteryQuantity, randomLotteryQuantity)
        outputView.showPurchasedLotteries(lotteries)
        return lotteries
    }

    private fun buyManualLotteries(manualLotteryQuantity: Quantity): Lotteries {
        if (manualLotteryQuantity.count > 0) {
            return Lotteries(
                inputView.readManualLottery(manualLotteryQuantity).map { lotteryMachine.generateManualLottery(it) },
            )
        }
        return Lotteries(listOf())
    }

    private fun buyRandomLotteries(randomLotteryQuantity: Quantity): Lotteries =
        Lotteries(List(randomLotteryQuantity.count) { lotteryMachine.generateRandomLottery() })

    private fun calculateWinningResult(lotteries: Lotteries): WinningResult {
        val winningLottery = readWinningLottery()
        val winningResult = lotteryResultEvaluator.evaluate(lotteries, winningLottery)
        outputView.showWinningResult(winningResult)
        return winningResult
    }

    private fun readWinningLottery(): WinningLottery =
        WinningLottery(
            Lottery.fromInput(inputView.readWinningNumbers()),
            LotteryNumber.from(inputView.readBonusNumber()),
        )

    private fun calculateProfitRate(
        winningResult: WinningResult,
        purchaseAmount: Money,
    ) {
        val totalPrize = LotteryPrizeCalculator().calculate(winningResult)
        val profitRate = profit.calculateRate(purchaseAmount, totalPrize)
        outputView.showProfitRate(profitRate, profitStatusDecider.decide(purchaseAmount, totalPrize))
    }
}
