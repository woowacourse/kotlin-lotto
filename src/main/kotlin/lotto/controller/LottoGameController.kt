package lotto.controller

import lotto.model.Count
import lotto.model.Lotto
import lotto.model.LottoGameResult
import lotto.model.LottoNumber
import lotto.model.Money
import lotto.model.NumbersGenerator
import lotto.util.ExceptionHandler
import lotto.view.LottoGameInputView
import lotto.view.LottoGameOutputView

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
    private val exceptionHandler: ExceptionHandler,
    private val numbersGenerator: NumbersGenerator,
) {
    fun startLottoGame() {
        val purchaseExpense: Money = getPurchaseExpense()
        var availableCount = Count.fromMoney(purchaseExpense)
        val manualCount = getManualCount(availableCount.value)
        val manualLotteries = getManualLotteries(manualCount)
        availableCount -= manualCount

        val autoLotteries = getAutoLotteries(availableCount)
        showPurchaseResult(availableCount, manualCount, autoLotteries)

        val winningLotto = getWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, manualLotteries + autoLotteries)
        showLottoGameResult(lottoGameResult, purchaseExpense)
    }

    private fun showPurchaseResult(
        availableCount: Count,
        manualCount: Count,
        autoLotteries: List<Lotto>,
    ) {
        lottoGameOutputView.showLottoCount(availableCount.value, manualCount.value)
        lottoGameOutputView.showPurchasedLotteries(autoLotteries)
    }

    private fun getAutoLotteries(availableCount: Count) = List(availableCount.value) { Lotto(*numbersGenerator.generate().toIntArray()) }

    private fun getManualLotteries(manualCount: Count) =
        exceptionHandler.handleInputValue {
            lottoGameInputView.inputManualLotteryNumber(manualCount.value).map { Lotto(*it.toIntArray()) }
        }

    private fun getPurchaseExpense(): Money = Money(exceptionHandler.handleInputValue(lottoGameInputView::inputPurchaseExpense))

    private fun getManualCount(entireCount: Int): Count =
        Count.ofManual(
            exceptionHandler.handleInputValue(lottoGameInputView::inputManualPurchaseCount),
            entireCount,
        )

    private fun getWinningLotto(): Lotto = Lotto(*exceptionHandler.handleInputValue(lottoGameInputView::inputWinningNumbers).toIntArray())

    private fun createBonusLottoNumber(winningLotto: Lotto): LottoNumber =
        exceptionHandler.handleInputValue {
            val bonusNumber = lottoGameInputView.inputBonusNumber()
            LottoNumber(bonusNumber, winningLotto)
        }

    private fun showLottoGameResult(
        lottoGameResult: LottoGameResult,
        purchaseExpense: Money,
    ) {
        exceptionHandler.handleOutputValue {
            val rankResults = lottoGameResult.getWinningResult()
            val earningRate = lottoGameResult.calculateEarningRate(purchaseExpense)
            lottoGameOutputView.showGameResult(rankResults, earningRate)
        }
    }
}
