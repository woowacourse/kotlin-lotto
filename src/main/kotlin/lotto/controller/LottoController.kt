package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoBundle
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoNumber
import lotto.domain.model.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lottoMachine: LottoMachine = LottoMachine(),
) {
    fun run() {
        val lottoBundle = purchaseLotto()
        val winningLotto = Lotto(inputView.readWinningNumbers())
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        val winningNumbers = WinningNumbers(winningLotto, bonusNumber)

        printWinningResults(lottoBundle, winningNumbers)
    }

    private fun purchaseLotto(): LottoBundle {
        val purchasePrice = inputView.readPurchaseAmount()
        val lottoBundle = lottoMachine.generateLottoBundle(purchasePrice)
        outputView.printPurchaseLottoCount(lottoBundle.lottos.size)
        lottoBundle.lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers.toList()) }
        return lottoBundle
    }

    private fun printWinningResults(
        lottoBundle: LottoBundle,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottoBundle)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn(lottoMachine.lottoPrice))
    }
}
