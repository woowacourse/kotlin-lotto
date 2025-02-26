package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoBundle
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoNumber
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.WinningNumbers
import lotto.domain.service.ManualLottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val (manualLottoBundle, autoLottoBundle) = purchaseLotto()
        printLottoBundle(manualLottoBundle, autoLottoBundle)

        val winningNumbers = generateWinningNumbers()

        val combinedLottoBundle = LottoBundle.combine(manualLottoBundle, autoLottoBundle)
        printWinningResults(combinedLottoBundle, winningNumbers)
    }

    private fun purchaseLotto(): Pair<LottoBundle, LottoBundle> {
        val purchasePrice = PurchaseAmount(inputView.readPurchaseAmount())
        val totalPurchaseAmount = purchasePrice.calculatePurchaseLottoCount(LottoMachine.LOTTO_PRICE)

        val manualLottoAmount = inputView.readManualLottoAmount()
        val manualLottoBundle = purchaseManualLotto(totalPurchaseAmount, manualLottoAmount)

        val autoLottoBundle = purchaseAutoLotto(totalPurchaseAmount - manualLottoAmount)

        return Pair(manualLottoBundle, autoLottoBundle)
    }

    private fun purchaseManualLotto(
        totalPurchaseAmount: Int,
        purchaseAmount: Int,
    ): LottoBundle {
        if (totalPurchaseAmount < purchaseAmount) throw IllegalArgumentException()

        val manualLottoNumbers = List(purchaseAmount) { inputView.readManualLottoNumbers() }
        val generator = ManualLottoNumbersGenerator(manualLottoNumbers)
        return LottoMachine(generator).generateLottoBundle(purchaseAmount)
    }

    private fun purchaseAutoLotto(purchaseAmount: Int): LottoBundle {
        return LottoMachine().generateLottoBundle(purchaseAmount)
    }

    private fun printLottoBundle(
        manualLottoBundle: LottoBundle,
        autoLottoBundle: LottoBundle,
    ) {
        outputView.printPurchaseLottoCount(manualLottoBundle.lottos.size, autoLottoBundle.lottos.size)
        manualLottoBundle.printLottoNumbers()
        autoLottoBundle.printLottoNumbers()
    }

    private fun LottoBundle.printLottoNumbers() {
        this.lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers.toList()) }
    }

    private fun generateWinningNumbers(): WinningNumbers {
        val winningLotto = Lotto(inputView.readWinningNumbers())
        val bonusNumber = LottoNumber.from(inputView.readBonusNumber())
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
