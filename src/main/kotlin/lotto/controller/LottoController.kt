package lotto.controller

import lotto.domain.generator.ManualLottoNumbersGenerator
import lotto.domain.generator.RandomLottoNumbersGenerator
import lotto.domain.model.Lotto
import lotto.domain.model.LottoBundle
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoNumber
import lotto.domain.model.Order
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val purchaseAmount = readPurchaseAmount()
        val order = generateLottoOrder(purchaseAmount)

        val lottoBundle = purchaseLotto(order)
        printLottoBundle(order, lottoBundle)

        val winningNumbers = generateWinningNumbers()
        printWinningResults(lottoBundle, winningNumbers)
    }

    private fun readPurchaseAmount(): PurchaseAmount =
        retryWhenException {
            PurchaseAmount(inputView.readPurchaseAmount())
        }

    private fun generateLottoOrder(purchaseAmount: PurchaseAmount): Order =
        retryWhenException {
            val manualLottoAmount = inputView.readManualLottoAmount()
            Order(purchaseAmount, manualLottoAmount)
        }

    private fun purchaseLotto(order: Order): LottoBundle {
        val manualLottoBundle = purchaseManualLotto(order.manualLottoAmount)
        val autoLottoBundle = purchaseAutoLotto(order.autoLottoAmount)

        return LottoBundle.combine(manualLottoBundle, autoLottoBundle)
    }

    private fun purchaseManualLotto(purchaseLottoAmount: Int): LottoBundle? {
        val manualLottoNumbers = List(purchaseLottoAmount) { inputView.readManualLottoNumbers() }
        val generator = ManualLottoNumbersGenerator(manualLottoNumbers)
        return LottoMachine(generator).generateLottoBundle(purchaseLottoAmount)
    }

    private fun purchaseAutoLotto(purchaseLottoAmount: Int): LottoBundle? {
        val generator = RandomLottoNumbersGenerator()
        return LottoMachine(generator).generateLottoBundle(purchaseLottoAmount)
    }

    private fun printLottoBundle(
        order: Order,
        lottoBundle: LottoBundle,
    ) {
        outputView.printPurchaseLottoCount(order.manualLottoAmount, order.autoLottoAmount)
        lottoBundle.lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers.toList()) }
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
