package lotto.controller

import lotto.domain.LotteriesGenerator
import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.LotteryNumberGenerator
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLottery
import lotto.view.InputView
import lotto.view.OutputView

class Controller(
    private val generator: LotteryNumberGenerator,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        publishLotteries()
        val winningLottery = getWinningLottery()
    }

    private fun publishLotteries() {
        val money = PurchaseAmount(inputView.readPurchaseAmount())
        val lotteries = LotteriesGenerator().generate(generator, money.getPurchaseQuantity())
        outputView.printLotteries(lotteries)
        outputView.printInterval()
    }

    private fun getWinningLottery(): WinningLottery {
        val winningNumbers = Lottery(inputView.readWinningNumbers().map { LotteryNumber(it) })
        val bonusNumber = LotteryNumber(inputView.readBonusNumber())
        return WinningLottery(winningNumbers, bonusNumber)
    }
}
