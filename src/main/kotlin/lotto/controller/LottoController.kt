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
        val combinedLottoBundle = LottoBundle.combine(manualLottoBundle, autoLottoBundle)
        printLottoBundle(manualLottoBundle, autoLottoBundle)

        val winningNumbers = generateWinningNumbers()
        printWinningResults(combinedLottoBundle, winningNumbers)
    }

    private fun purchaseLotto(): Pair<LottoBundle?, LottoBundle?> {
        val purchaseAmount = readPurchaseAmount()

        val manualLottoBundle = purchaseManualLotto(purchaseAmount)
        val autoLottoBundle = purchaseAutoLotto(purchaseAmount)

        return Pair(manualLottoBundle, autoLottoBundle)
    }

    private fun readPurchaseAmount(): PurchaseAmount =
        retryWhenException {
            PurchaseAmount(inputView.readPurchaseAmount())
        }

    private fun purchaseManualLotto(purchaseAmount: PurchaseAmount): LottoBundle? {
        val manualLottoAmount = inputView.readManualLottoAmount()
        reducePurchaseAmount(purchaseAmount, manualLottoAmount)

        val manualLottoNumbers = List(manualLottoAmount) { inputView.readManualLottoNumbers() }
        val generator = ManualLottoNumbersGenerator(manualLottoNumbers)
        return LottoMachine(generator).generateLottoBundle(manualLottoAmount)
    }

    private fun purchaseAutoLotto(purchaseAmount: PurchaseAmount): LottoBundle? {
        val autoLottoAmount = purchaseAmount.calculatePurchaseLottoCount(LottoMachine.LOTTO_PRICE)
        reducePurchaseAmount(purchaseAmount, autoLottoAmount)

        return LottoMachine().generateLottoBundle(autoLottoAmount)
    }

    private fun reducePurchaseAmount(
        purchaseAmount: PurchaseAmount,
        reduceAmount: Int,
    ) = retryWhenException {
        purchaseAmount.reducePurchaseAmount(reduceAmount * LottoMachine.LOTTO_PRICE)
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
        val winningLotto = readWinningLotto()
        while (true) {
            val bonusNumber = readBonusNumber()

            val winningNumbers = makeWinningNumbers(winningLotto, bonusNumber)
            if (winningNumbers != null) return winningNumbers
        }
    }

    private fun readWinningLotto() =
        retryWhenException {
            Lotto(inputView.readWinningNumbers())
        }

    private fun readBonusNumber() =
        retryWhenException {
            LottoNumber.from(inputView.readBonusNumber())
        }

    private fun makeWinningNumbers(
        winningLotto: Lotto,
        bonusNumber: LottoNumber,
    ): WinningNumbers? {
        return runCatching {
            WinningNumbers(winningLotto, bonusNumber)
        }.onFailure {
            outputView.printErrorMessage(it.message)
        }.getOrNull()
    }

    private fun printWinningResults(
        lottoBundle: LottoBundle,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottoBundle)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn(LottoMachine.LOTTO_PRICE))
    }

    private fun <T> retryWhenException(action: () -> T): T {
        while (true) {
            runCatching {
                return action()
            }.onFailure { e ->
                outputView.printErrorMessage(e.message)
            }
        }
    }
}
