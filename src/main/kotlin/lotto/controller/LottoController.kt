package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoBundle
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoNumber
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val lottoBundle = purchaseLotto()
        printLottoBundle(lottoBundle)

        val winningNumbers = generateWinningNumbers()
        printWinningResults(lottoBundle, winningNumbers)
    }

    private fun purchaseLotto(): LottoBundle {
        val purchasePrice = PurchaseAmount(inputView.readPurchaseAmount())
        val purchaseLottoCount = purchasePrice.calculatePurchaseLottoCount(LottoMachine.LOTTO_PRICE)
        val lottoBundle = LottoMachine().generateLottoBundle(purchaseLottoCount)

        return lottoBundle
    }

    private fun printLottoBundle(lottoBundle: LottoBundle) {
        outputView.printPurchaseLottoCount(lottoBundle.lottos.size)
        lottoBundle.lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers.toList()) }
    }

    private fun generateWinningNumbers(): WinningNumbers {
        val winningLotto = Lotto(inputView.readWinningNumbers())
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun printWinningResults(
        lottoBundle: LottoBundle,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottoBundle)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn(LottoMachine.LOTTO_PRICE))
    }
}
