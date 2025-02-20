package lotto.controller

import lotto.domain.model.LottoMachine
import lotto.domain.model.WinningLotto
import lotto.domain.value.PurchaseQuantity
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val purchaseQuantity = PurchaseQuantity(purchaseAmount)
        outputView.printPurchaseQuantity(purchaseQuantity)
        val lottoMachine = LottoMachine()
        val lottos = lottoMachine.generateLottos(purchaseQuantity)
        outputView.printLottos(lottos)
        val winningLotto = getWinningLotto()
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoWithoutBonus = inputView.readWinningLottoWithoutBonusNumber()
        return inputView.getWinningLottoWithBonusNumber(winningLottoWithoutBonus)
    }
}
