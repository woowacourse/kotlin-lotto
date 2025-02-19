package lotto.controller

import lotto.Lotto
import lotto.LottoFactory
import lotto.LottoResult
import lotto.Purchase
import lotto.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price = inputView.inputPurchasePrice()
        val amount = Purchase(price).calculateAmountOfLottos()
        outputView.printLottoAmount(amount)

        val lottos: LottoResult = LottoFactory().generateLottos(amount)
        outputView.printLottos(lottos)

        val winningNumbers: List<Int> = inputView.inputWinningNumber()
        val bonusNumber: String = inputView.inputBonusNumber()
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
    }
}
