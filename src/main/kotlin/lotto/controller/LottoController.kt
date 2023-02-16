package lotto.controller

import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.PurchaseAmount
import lotto.domain.Rank
import lotto.domain.WinningLottery
import lotto.domain.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {
    fun run() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val lotteries = createLotteries(purchaseAmount)
        val winningLottery = WinningLottery(inputView.readLottery(), inputView.readBonusNumber())
        val winningResult = calculateResult(lotteries, winningLottery)
        println()
        outputView.printWinningStats(purchaseAmount, winningResult)
    }

    private fun calculateResult(lotteries: List<Lottery>, winningLottery: WinningLottery): WinningResult {
        val winningResult = WinningResult()

        repeat(lotteries.size) {
            val countOfMatch = lotteries[it].countMatches(winningLottery)
            val matchBonus = lotteries[it].containBonusNumber(winningLottery.bonusNumber)
            winningResult.countRank(Rank.valueOf(countOfMatch, matchBonus))
        }

        return winningResult
    }

    private fun createLotteries(purchaseAmount: PurchaseAmount): List<Lottery> {
        val generator = LotteryGenerator()
        val lotteries = mutableListOf<Lottery>()

        repeat(purchaseAmount.getPurchaseQuantity()) {
            lotteries.add(generator.generateLottery())
        }

        repeat(lotteries.size) {
            val numbers = lotteries[it].numbers.sortedBy { it.number }
            outputView.printMessage(numbers.toString())
        }
        println()
        return lotteries
    }
}
