package controller

import model.Count
import model.Lotto
import model.LottoGameResult
import model.LottoNumber
import model.Money
import model.NumbersGenerator
import util.ExceptionHandler
import view.LottoGameInputView
import view.LottoGameOutputView

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
        val manualLotteries = lottoGameInputView.inputManualLotteryNumber(manualCount.value).map { Lotto(*it.toIntArray()) }
        availableCount -= manualCount
        val autoLotteries =
            List(availableCount.value) {
                Lotto(*numbersGenerator.generate(6).toIntArray())
            }
        lottoGameOutputView.showLottoCount((availableCount - manualCount).value, manualCount.value)
        lottoGameOutputView.showPurchasedLotteries(autoLotteries)
        val entireLotteries = manualLotteries + autoLotteries
        val winningLotto = getWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, entireLotteries)
        displayLottoResult(lottoGameResult, purchaseExpense)
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

    private fun displayLottoResult(
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
