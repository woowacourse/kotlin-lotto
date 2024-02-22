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

        val winningLotto = readWinningLotto()
        val bonus = readBonus(winningLotto)

        val stats = getStats(userLottos, winningLotto, bonus)
        printStats(stats)

        val roi = getROI(amount, stats)
        printROI(roi)
    }

    private fun readAmount() = Amount(InputView.readAmount())

    private fun readBonus(winningLotto: Lotto) = Bonus(InputView.readBonus(), winningLotto)

    private fun readWinningLotto() = Lotto.fromInput(InputView.readWinningLotto())

    private fun purchaseLottos(amount: Amount): List<Lotto> {
        LottoStore.setStrategy(RandomLottoGenerationStrategy(amount))

        return LottoStore.makeLotto()
    }

    private fun printPurchasedLottos(lottos: List<Lotto>) = OutputView.printPurchasedLotto(lottos)

    private fun printStats(stats: Map<Rank, Int>) = OutputView.printStats(stats)

    private fun printROI(roi: Double) = OutputView.printProfit(roi)

    private fun getStats(
        userLottos: List<Lotto>,
        winningLotto: Lotto,
        bonus: Bonus,
    ) = WinningResult.getStats(userLottos, winningLotto, bonus)

    private fun getROI(
        amount: Amount,
        stats: Map<Rank, Int>,
    ) = WinningResult.calculateROI(amount, stats)
}
