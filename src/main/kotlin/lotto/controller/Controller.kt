package lotto.controller

import lotto.domain.LotteriesGenerator
import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.LotteryNumberGenerator
import lotto.domain.NumberOfRank
import lotto.domain.PurchaseAmount
import lotto.domain.Rank
import lotto.domain.WinningLottery
import lotto.domain.WinningResultCalculator
import lotto.view.InputView
import lotto.view.OutputView

class Controller(
    private val generator: LotteryNumberGenerator,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val purchase = getPurchase()
        val lotteries = publishLotteries(purchase)
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
        val lotteries = LotteriesGenerator().generate(generator, purchase.getPurchaseQuantity())
        outputView.printLotteries(lotteries)
        return lotteries
    }

    private fun getWinningLottery(): WinningLottery {
        val winningNumbers = Lottery(inputView.readWinningNumbers().map { LotteryNumber(it) })
        val bonusNumber = LotteryNumber(inputView.readBonusNumber())
        return WinningLottery(winningNumbers, bonusNumber)
    }

    private fun getRankCounter(lotteries: List<Lottery>, winningLottery: WinningLottery): NumberOfRank {
        val counter = NumberOfRank()
        lotteries.forEach {
            counter.increaseNumber(
                Rank.valueOf(
                    it.countMatches(winningLottery.lottery),
                    it.containBonusNumber(winningLottery.bonusNumber)
                )
            )
        }
        return counter
    }

    private fun getProfit(purchase: PurchaseAmount, prize: Long): Double {
        val calculator = WinningResultCalculator()
        return calculator.calculateProfit(purchase, prize)
    }
}
