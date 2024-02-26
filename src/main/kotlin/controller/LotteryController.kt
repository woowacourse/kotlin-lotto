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
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryMachine: LotteryMachine = LotteryMachine(),
    private val lotteryResultEvaluator: LotteryResultEvaluator = LotteryResultEvaluator(),
    private val profit: Profit = Profit(),
) {
    fun start() {
        val purchaseAmount = Money.from(inputView.readPurchaseAmount())
        val lotteries = buyLotteries(purchaseAmount)

        val winningResult = calculateWinningResult(lotteries)
        calculateProfitRate(winningResult, purchaseAmount)
    }

    private fun buyLotteries(purchaseAmount: Money): Lotteries {
        val lotteriesCount = LotterySeller(purchaseAmount).getLotteryCount()
        val manualLotteryQuantity = Quantity.from(inputView.readManualLotteryQuantity())
        val lotteries = buyManualLotteries(manualLotteryQuantity) + buyRandomLotteries(lotteriesCount, manualLotteryQuantity)

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

    private fun buyRandomLotteries(
        lotteriesCount: Int,
        manualLotteryQuantity: Quantity,
    ): Lotteries =
        Lotteries(
            List(lotteriesCount - manualLotteryQuantity.count) {
                lotteryMachine.generateRandomLottery()
            },
        )

    private fun calculateWinningResult(lotteries: Lotteries): WinningResult {
        val winningLottery = readWinningLottery()
        val winningResult = lotteryResultEvaluator.evaluate(lotteries, winningLottery)
        outputView.showWinningResult(winningResult)
        return winningResult
    }

    private fun readWinningLottery(): WinningLottery =
        WinningLottery(
            Lottery.fromInput(inputView.readWinningNumbers()),
            LotteryNumber.bonusNumber(inputView.readBonusNumber()),
        )

    private fun calculateProfitRate(
        winningResult: WinningResult,
        purchaseAmount: Money,
    ) {
        val totalPrize = LotteryPrizeCalculator().calculate(winningResult)
        val profitRate = profit.calculateRate(purchaseAmount, totalPrize)
        outputView.showProfitRate(profitRate, ProfitStatusDecider.decide(purchaseAmount, totalPrize))
    }
}
