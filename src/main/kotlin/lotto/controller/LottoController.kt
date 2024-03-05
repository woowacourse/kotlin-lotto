package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumberGenerator
import lotto.model.Lottos
import lotto.model.ManualLottoPurchaseCount
import lotto.model.Money
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

            displayWinningStatistics(money.purchaseAmount, winningStatistics)
        }

    private fun publishLottos(
        numberOfManualLotto: ManualLottoPurchaseCount,
        money: Money,
    ): Lottos {
        val generator = LottoNumberGenerator()
        val manual =
            InputView.inputManualLottos(numberOfManualLotto.count)
                .map { manualNumbers ->
                    generator.generateManual(manualNumbers)
                }
        val manualLottos = Lottos(manual.map { Lotto(it) })
        val autoLottoCount = money.calculateAutoLottoCount(manualLottos.publishedLottos.size)
        val autoLottos = Lottos(List(autoLottoCount) { Lotto(generator.generateAuto()) })

        OutputView.outputShowLottos(manualLottos, autoLottos)
        return manualLottos + autoLottos
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
