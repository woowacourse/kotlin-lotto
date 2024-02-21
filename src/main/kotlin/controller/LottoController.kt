package controller

import model.*
import view.InputView
import view.OutputView

class LottoController {
    private lateinit var buyer: Buyer

    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        buyer = Buyer(purchaseAmount)
        val lottos = publishLottos()
        val winningLotto = drawWinningLotto()
    }

    private fun generateLottoNumbers(): List<Int> {
        val numberGenerator = NumberGenerator()
        val randomNumbers = numberGenerator.makeRandomNumbers()

        return numberGenerator.drawSixNumbers(randomNumbers)
    }

    private fun publishLottos() {
        val lottos = Lottos(List(buyer.numberOfLotto) { Lotto(generateLottoNumbers()) })
        buyer.buyLottos(lottos)
        displayPurchaseResult()
    }

    private fun displayPurchaseResult() {
        OutputView.outputNumberOfLotto(buyer.numberOfLotto)
        OutputView.outputLottos(buyer.lottos)
    }

    private fun drawWinningLotto(): WinningLotto {
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        return WinningLotto(winningNumbers, bonusNumber)
    }
}
