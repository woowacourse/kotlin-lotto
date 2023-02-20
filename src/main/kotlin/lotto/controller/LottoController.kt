package lotto.controller

import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.Rank
import lotto.domain.WinningLottery
import lotto.domain.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryGenerator: LotteryGenerator = LotteryGenerator()
) {
    fun run() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val lotteries = lotteryGenerator.generateLotteries(purchaseAmount.amount)
        val winningLottery = WinningLottery(inputView.readLottery(), inputView.readBonusNumber())
        val winningResult = calculateResult(lotteries, winningLottery)
        println()
        outputView.printWinningStats(purchaseAmount, winningResult)

        if (!winningResult.isGain(winningResult.calculateYield(purchaseAmount.amount))) {
            outputView.printMessage(NOTICE_MESSAGE_NOT_GAIN)
        }
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

    companion object {
        private const val NOTICE_MESSAGE_NOT_GAIN = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    }
}
