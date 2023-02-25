package lotto.controller

import lotto.domain.Lotteries
import lotto.domain.Lottery
import lotto.domain.LotteryMachine
import lotto.domain.LotteryNumber
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLottery
import lotto.domain.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val machine: LotteryMachine = LotteryMachine()
) {
    fun run() {
        val amount: PurchaseAmount = getPurchaseAmount()
        val autoLotteries: Lotteries = getLotteries(amount.autoNumber)
        val manualLotteries: Lotteries = getLotteries(amount.manualNumber)
        val lotteries: Lotteries = autoLotteries.plus(manualLotteries)
        outputView.printPurchaseLotteries(amount, lotteries)

        val winningLottery: WinningLottery = getWinningLottery()
        val result: WinningResult = machine.createWinningResult(winningLottery, lotteries, amount)
        outputView.printWinningStats(result)
    }

    private fun getWinningLottery(): WinningLottery {
        while (true) {
            val lottery: Lottery? = inputView.readLottery()
            val bonusNumber: LotteryNumber? = inputView.readLotteryNumber()
            if (lottery != null && bonusNumber != null) return WinningLottery(lottery, bonusNumber)
        }
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        while (true) {
            val amount: PurchaseAmount? = inputView.readPurchaseAmount()
            if (amount != null) return amount
        }
    }

    private fun getLotteries(count: Int): Lotteries {
        while (true) {
            val lotteries: Lotteries? = inputView.readLotteries(count)
            if (lotteries != null) return lotteries
        }
    }
}
