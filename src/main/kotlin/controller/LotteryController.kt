package controller

import WinningLottery
import model.Money
import model.Quantity
import model.lottery.Lotteries
import model.lottery.Lottery
import model.lottery.LotteryNumber
import model.lottery.LotterySeller
import model.winning.WinningResult
import view.InputView
import view.OutputView

class LotteryController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lotterySeller: LotterySeller,
) {
    fun start() {
        val purchaseAmount = Money.from(inputView.readPurchaseAmount())
        val lotteries = buyLotteries(purchaseAmount)

        val winningResult = calculateWinningResult(lotteries)
        calculateProfitRate(purchaseAmount, winningResult)
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
            inputView.guideManualLottery()
            return Lotteries(
                List(manualLotteryQuantity.count) {
                    Lottery.of(*inputView.readManualLottery().toIntArray())
                },
            )
        }
        return Lotteries(listOf())
    }

    private fun buyRandomLotteries(randomLotteryQuantity: Quantity): Lotteries =
        Lotteries(List(randomLotteryQuantity.count) { Lottery.fromRandom() })

    private fun calculateWinningResult(lotteries: Lotteries): WinningResult {
        val winningLottery = readWinningLottery()
        val winningResult = lotteries.evaluateWinning(winningLottery)
        outputView.showWinningResult(winningResult)
        return winningResult
    }

    private fun readWinningLottery(): WinningLottery =
        WinningLottery(
            Lottery.of(*inputView.readWinningLottery().toIntArray()),
            LotteryNumber.from(inputView.readBonusNumber()),
        )

    private fun calculateProfitRate(
        purchaseAmount: Money,
        winningResult: WinningResult,
    ) {
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)
        outputView.showProfitRate(profitRate, profitRate.decideProfitStatus())
    }
}
