package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoWinningStats
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val purchaseQuantity = purchaseAmount.getPurchaseQuantity()
        outputView.printPurchaseQuantity(purchaseQuantity)
        val lottoMachine = LottoMachine()
        val purchaseDetail = lottoMachine.generateLottos(purchaseAmount)
        outputView.printLottos(purchaseDetail.lottos)
        val winningLotto = getWinningLotto()
        val lottoWinningStats = getLottoWInningStats(winningLotto, purchaseDetail.lottos)
        outputView.printLottoStats(lottoWinningStats)
        outputView.printLottoEarningRate(lottoWinningStats.calculateEarningRate())
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoWithoutBonus = inputView.readWinningLottoWithoutBonusNumber()
        return inputView.getWinningLottoWithBonusNumber(winningLottoWithoutBonus)
    }

    private fun getLottoWInningStats(
        winningLotto: WinningLotto,
        lottos: List<Lotto>,
    ): LottoWinningStats {
        val lottoCalculator = LottoCalculator(winningLotto, lottos)
        return lottoCalculator.calculateWinningStats()
    }
}
