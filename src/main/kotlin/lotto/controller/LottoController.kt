package lotto.controller

import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.domain.service.LottoMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoMachine = LottoMachine()
        val lottoCalculator = LottoCalculator()

        val purchaseAmount = inputView.readPurchaseAmount()
        val lottos = lottoMachine.generate(purchaseAmount)
        outputView.printPurchaseDetail(purchaseAmount, lottos)

        val winningLotto = getWinningLotto()
        val lottoStats = lottoCalculator.calculate(winningLotto, lottos)
        val earningRate = lottoCalculator.calculateEarningRate(lottoStats, purchaseAmount)
        outputView.printLottoResult(lottoStats, earningRate)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()
        return WinningLotto(winningLottoNumbers, bonusNumber)
    }
}
