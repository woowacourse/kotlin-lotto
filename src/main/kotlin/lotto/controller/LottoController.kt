package lotto.controller

import lotto.Lotto
import lotto.LottoMachine
import lotto.LottoNumber
import lotto.LottoPurchaseAmount
import lotto.Lottos
import lotto.WinningLotto
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

        lottos.lottoBundle.forEach { lotto -> outputView.printLottoNumbers(lotto) }

        val winningLottoNumbers = getWinningLottoNumbers()
        val winningLotto = getWinningLotto(winningLottoNumbers)
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

    private fun getWinningLottoNumbers(): Lotto =
        try {
            outputView.printWinningLottoNumbersGuide()
            val numbers = inputView.readWinningLottoNumbers()
            Lotto(numbers.map { number -> LottoNumber(number) }.toList())
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message ?: "")
            getWinningLottoNumbers()
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

    private fun getWinningLotto(winningLottoNumbers: Lotto): WinningLotto =
        try {
            val bonusNumber = getBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message ?: "")
            getWinningLotto(winningLottoNumbers)
        }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        return bonusNumber
    }
}
