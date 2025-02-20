package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoWinningStats
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.domain.value.PurchaseQuantity
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val purchaseQuantity = getPurchaseQuantity()
        outputView.printPurchaseQuantity(purchaseQuantity)
        val lottos = getLottoByQuantity(purchaseQuantity)
        outputView.printLottos(lottos)
        val lottoWinningStats = getLottoWInningStats(lottos)
        outputView.printLottoStats(lottoWinningStats)
        outputView.printLottoEarningRate(lottoWinningStats.calculateEarningRate())
    }

    private fun getPurchaseQuantity(): PurchaseQuantity {
        val purchaseAmount = inputView.readPurchaseAmount()
        return PurchaseQuantity(purchaseAmount)
    }

    private fun getLottoByQuantity(purchaseQuantity: PurchaseQuantity): List<Lotto> {
        val lottoMachine = LottoMachine()
        return lottoMachine.generateLottos(purchaseQuantity)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoWithoutBonus = inputView.readWinningLottoWithoutBonusNumber()
        return inputView.getWinningLottoWithBonusNumber(winningLottoWithoutBonus)
    }

    private fun getLottoWInningStats(lottos: List<Lotto>): LottoWinningStats {
        val winningLotto = getWinningLotto()
        val lottoCalculator = LottoCalculator(winningLotto, lottos)
        return lottoCalculator.calculateWinningStats()
    }
}
