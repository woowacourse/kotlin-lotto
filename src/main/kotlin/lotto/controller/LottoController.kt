package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.ManualLottoPurchaseCount
import lotto.model.Money
import lotto.model.NumberGenerator
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    fun run() {
        playLotto().onFailure { e -> OutputView.outputErrorMessage(e) }
    }

    private fun playLotto() =
        runCatching {
            val purchaseAmount = InputView.inputPurchaseAmount()
            val money = Money.from(purchaseAmount)
            val purchaseSizeOfManualLotto = InputView.inputPurchaseSizeOfManualLotto()
            val manualLottoCount = ManualLottoPurchaseCount.from(purchaseSizeOfManualLotto, money.numberOfLotto)
            val userLotto = publishLottos(manualLottoCount, money)
            val winningLotto = drawWinningLotto()

            val winningStatistics = userLotto.makeWinningStatics(winningLotto)

            displayWinningStatistics(money.numberOfLotto, winningStatistics)
        }

    private fun publishLottos(
        numberOfManualLotto: ManualLottoPurchaseCount,
        money: Money,
    ): Lottos {
        val manual = InputView.inputManualLottos(numberOfManualLotto.count)
        val manualLottos = Lottos(manual.map { generateManualLotto(it) })
        val autoLottoCount = money.calculateAutoLottoCount(manualLottos.publishedLottos.size)
        val autoLottos = Lottos(List(autoLottoCount) { generateAutoLotto() })

        displayPurchaseResult(manualLottos, autoLottos)

        return manualLottos + autoLottos
    }

    private fun generateManualLotto(manual: List<Int>): Lotto {
        return Lotto(manual.map { LottoNumber(it) }.toSet())
    }

    private fun generateAutoLotto(): Lotto {
        return Lotto(generateLottoNumbers())
    }

    private fun generateLottoNumbers(): Set<LottoNumber> {
        return NumberGenerator().generateLottoNumbers().map { LottoNumber(it) }.toSet()
    }

    private fun displayPurchaseResult(
        manualLotto: Lottos,
        autoLotto: Lottos,
    ) {
        OutputView.outputShowLottos(manualLotto, autoLotto)
    }

    private fun drawWinningLotto(): WinningLotto {
        val inputWinningNumbers = InputView.inputWinningNumbers()
        val winningNumbers = inputWinningNumbers.map { LottoNumber(it.toIntOrNull() ?: 0) }
        val bonusNumber = LottoNumber(InputView.inputBonusNumber().toIntOrNull() ?: 0)
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun displayWinningStatistics(
        purchaseAmount: Int,
        winningStatistics: WinningStatistics,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)
        displayRateOfReturn(purchaseAmount, winningStatistics)
    }

    private fun displayRateOfReturn(
        purchaseAmount: Int,
        winningStatistics: WinningStatistics,
    ) {
        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}
