package lotto.controller

import lotto.domain.model.LottoMachine
import lotto.domain.model.PurchaseDetail
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoMachine = LottoMachine()
        val purchaseDetail = getPurchaseDetail(lottoMachine)
        outputView.printPurchaseDetail(purchaseDetail)

        val winningLotto = getWinningLotto()
        val lottoCalculator = LottoCalculator()
        val lottoResult = lottoCalculator.calculateWinningStats(winningLotto, purchaseDetail)
        outputView.printLottoResult(lottoResult)
    }

    private fun getPurchaseDetail(lottoMachine: LottoMachine): PurchaseDetail {
        val purchaseAmount = inputView.readPurchaseAmount()
        return lottoMachine.generateLottos(purchaseAmount)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()
        return WinningLotto(winningLottoNumbers, bonusNumber)
    }
}
