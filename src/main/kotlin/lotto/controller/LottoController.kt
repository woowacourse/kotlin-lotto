package lotto.controller

import lotto.Lotto
import lotto.LottoMachine
import lotto.LottoPurchaseAmount
import lotto.Lottos
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoMachine: LottoMachine,
) {
    fun run() {
        val purchaseMoney = getPurchaseMoney()
        val lottoCount = getLottoCount(purchaseMoney)
        val lottos = getLottos(lottoCount)
    }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        try {
            outputView.printPurchaseAmountGuide()
            val money = inputView.readLottoPurchaseAmount()
            LottoPurchaseAmount(money)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message ?: "")
            getPurchaseMoney()
        }

    private fun getLottoCount(purchaseMoney: LottoPurchaseAmount): Int {
        val lottoCount = purchaseMoney.getLottoCount()
        outputView.printLottoCount(lottoCount)
        return lottoCount
    }

    private fun getLottos(lottoCount: Int): Lottos {
        val lottoBundle = mutableListOf<Lotto>()
        repeat(lottoCount) { lottoBundle.add(lottoMachine.createLotto()) }
        return Lottos(lottoBundle.toList())
    }
}
