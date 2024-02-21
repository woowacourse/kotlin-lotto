package controller

import model.*
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val buyer = Buyer(purchaseAmount)
        val lottoNumbers = generateLottoNumbers()
        val lottos = Lottos(List(buyer.numberOfLotto) { Lotto(lottoNumbers) })
        displayPurchaseResult(buyer, lottos)
        val winningLotto = publishWinningLotto()
    }

    private fun generateLottoNumbers(): List<Int> {
        val numberGenerator = NumberGenerator()
        val randomNumbers = numberGenerator.makeRandomNumbers()

        return numberGenerator.drawSixNumbers(randomNumbers)
    }

    private fun displayPurchaseResult(buyer: Buyer, lottos: Lottos) {
        OutputView.outputNumberOfLotto(buyer.numberOfLotto)
        OutputView.outputLottos(lottos)
    }

    private fun publishWinningLotto(): WinningLotto {
        val winning = Winning(InputView.inputWinningNumbers())
        val bonusNumber = InputView.inputBonusNumber(winning)
        return WinningLotto(winning, bonusNumber)
    }
}
