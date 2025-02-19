package lotto.controller

import lotto.domain.LottoMachine
import lotto.domain.LottoResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lottoMachine = LottoMachine()
        val lottos = lottoMachine.buyLottos(purchaseAmount.toInt())
        outputView.printPurchasedLottos(lottos)
        val winningNumber = inputView.getWinningNumber().split(", ").map { it.toInt() }
        val bonusNumber = inputView.getBonusNumber().toInt()
        val lottoResult = LottoResult(winningNumber, bonusNumber)
        lottoResult.matchLotto(lottos)
        outputView.printWinningStats(lottoResult.winningStats)
        val prize = lottoResult.calculatePrize()
        outputView.printProfit(lottoResult.calculateProfit(prize, purchaseAmount.toInt()))
    }
}
