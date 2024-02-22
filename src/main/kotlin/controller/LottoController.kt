package controller

import model.Amount
import model.Bonus
import model.Lotto
import model.LottoStore
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
    }

    private fun readAmount() = Amount(InputView.readAmount())

    private fun readBonus(winningNumbers: Lotto) = Bonus(InputView.readBonus(), winningNumbers)

    private fun readWinningNumbers() = Lotto.fromInput(InputView.readWinningNumbers())

    private fun purchaseLottos(amount: Amount): List<Lotto> {
        LottoStore.setStrategy(RandomLottoGenerationStrategy(amount))

        return LottoStore.makeLotto()
    }

    private fun printPurchasedLottos(lottos: List<Lotto>) = OutputView.printPurchasedLotto(lottos)
}
