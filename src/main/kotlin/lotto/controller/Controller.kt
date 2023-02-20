package lotto.controller

import lotto.domain.LotteriesGenerator
import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.LotteryNumber
import lotto.domain.ProfitCalculator
import lotto.domain.PurchaseAmount
import lotto.domain.RankCounter
import lotto.domain.WinningLottery
import lotto.view.InputView
import lotto.view.OutputView

class Controller(
    private val generator: LotteryGenerator,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val purchase = getPurchase()
        val lotteries = publishLotteries(purchase)
        outputView.printLotteries(lotteries)
        outputView.printInterval()

        val winningLottery = getWinningLottery()
        outputView.printInterval()

        val counter = getRankCounter(lotteries, winningLottery)
        val profit = getProfit(purchase, counter.calculateTotalPrize())
        outputView.printWinningResult(counter, profit)
    }

    private fun getPurchase(): PurchaseAmount {
        return PurchaseAmount(inputView.readPurchaseAmount())
    }

    private fun publishLotteries(purchase: PurchaseAmount): List<Lottery> {
        return LotteriesGenerator().generate(purchase.getPurchaseQuantity(), generator)
    }

    private fun getWinningLottery(): WinningLottery {
        val winningNumbers = Lottery(inputView.readWinningNumbers().map { LotteryNumber.from(it) })
        val bonusNumber = LotteryNumber.from(inputView.readBonusNumber())
        return WinningLottery(winningNumbers, bonusNumber)
    }

    private fun getRankCounter(lotteries: List<Lottery>, winningLottery: WinningLottery): RankCounter {
        val counter = RankCounter()
        counter.count(lotteries, winningLottery)
        return counter
    }

    private fun getProfit(purchase: PurchaseAmount, prize: Long): Double {
        return ProfitCalculator.calculate(purchase, prize)
    }
}
