package controller

import model.*
import utils.RandomLottoGenerationStrategy
import view.InputView
import view.OutputView

class LottoController {

    fun start() {
        val amount = readAmount()
        val userLottos = purchaseLottos(amount)
        printPurchasedLottos(userLottos)

        val winningNumbers = readWinningNumbers()
        val bonus = readBonus(winningNumbers)

        val stats = getStats(userLottos, winningNumbers, bonus)
        printStats(stats)

        val roi = getROI(amount, stats)
        printROI(roi)
    }

    private fun readAmount() = Amount(InputView.readAmount())

    private fun readBonus(winningNumbers: Lotto) = Bonus(InputView.readBonus(), winningNumbers)

    private fun readWinningNumbers() = Lotto.fromInput(InputView.readWinningNumbers())

    private fun purchaseLottos(amount: Amount): List<Lotto> {
        LottoStore.setStrategy(RandomLottoGenerationStrategy(amount))

        return LottoStore.makeLotto()
    }

    private fun printPurchasedLottos(lottos: List<Lotto>) = OutputView.printPurchasedLotto(lottos)

    private fun printStats(stats: Map<Rank, Int>) = OutputView.printStats(stats)

    private fun printROI(roi: Double) = OutputView.printProfit(roi)

    private fun getStats(userLottos: List<Lotto>, winningNumbers: Lotto, bonus: Bonus) =
        LottoResult.getStats(userLottos, winningNumbers, bonus)

    private fun getROI(amount: Amount, stats: Map<Rank, Int>) = LottoResult.calculateROI(amount, stats)
}
