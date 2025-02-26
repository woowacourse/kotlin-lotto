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

    private fun purchaseLotto(): Pair<LottoBundle?, LottoBundle?> {
        val purchaseAmount = PurchaseAmount(inputView.readPurchaseAmount())

        val manualLottoBundle = purchaseManualLotto(purchaseAmount)
        val autoLottoBundle = purchaseAutoLotto(purchaseAmount)

        return Pair(manualLottoBundle, autoLottoBundle)
    }

    private fun purchaseManualLotto(purchaseAmount: PurchaseAmount): LottoBundle? {
        val manualLottoAmount = inputView.readManualLottoAmount()
        purchaseAmount.reducePurchaseAmount(manualLottoAmount * LottoMachine.LOTTO_PRICE)

        val manualLottoNumbers = List(manualLottoAmount) { inputView.readManualLottoNumbers() }
        val generator = ManualLottoNumbersGenerator(manualLottoNumbers)
        return LottoMachine(generator).generateLottoBundle(manualLottoAmount)
    }

    private fun purchaseAutoLotto(purchaseAmount: PurchaseAmount): LottoBundle? {
        val autoLottoAmount = purchaseAmount.calculatePurchaseLottoCount(LottoMachine.LOTTO_PRICE)
        purchaseAmount.reducePurchaseAmount(autoLottoAmount * LottoMachine.LOTTO_PRICE)

        return LottoMachine().generateLottoBundle(autoLottoAmount)
    }

    private fun printLottoBundle(
        manualLottoBundle: LottoBundle?,
        autoLottoBundle: LottoBundle?,
    ) {
        outputView.printPurchaseLottoCount(
            manualLottoBundle?.lottos?.size ?: 0,
            autoLottoBundle?.lottos?.size ?: 0,
        )
        manualLottoBundle?.printLottoNumbers()
        autoLottoBundle?.printLottoNumbers()
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
