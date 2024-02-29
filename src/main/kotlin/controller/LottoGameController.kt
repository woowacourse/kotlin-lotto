package controller

import model.Count
import model.Lotto
import model.LottoGameResult
import model.LottoGenerator
import model.LottoNumber
import model.Money
import util.ExceptionHandler
import view.LottoGameInputView
import view.LottoGameOutputView

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
    private val exceptionHandler: ExceptionHandler,
    private val lottoGenerator: LottoGenerator,
) {
    fun startLottoGame() {
        val purchaseExpense: Money = getPurchaseExpense()
        var availableCount = Count.fromMoney(purchaseExpense)
        val manualCount = getManualCount()
//        val manualLotteries = lottoGameInputView.inputManualLotteries(manualCount.value)
//        availableCount -= manualCount
//        val autoLotteries = lottoGenerator.generate()
//        val lottie: List<Lotto> = purchaseLottie(purchaseExpense)
//        val winningLotto = getWinningLotto()
//        val bonusLottoNumber = createBonusLottoNumber(winningLotto)
//        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, lottie)
//        displayLottoResult(lottoGameResult, purchaseExpense)
    }

    private fun getPurchaseExpense(): Money = exceptionHandler.handleInputValue(lottoGameInputView::inputPurchaseExpense)

//    private fun purchaseLottie(purchaseExpense: Money): List<Lotto> =
//        exceptionHandler.handleInputValue {
//            val lotteries = lottoGenerator.generate(Count.fromMoney(purchaseExpense))
//            lotteries.also(lottoGameOutputView::showPurchasedLottie)
//        }

    private fun getManualCount(): Count = exceptionHandler.handleInputValue(lottoGameInputView::inputManualPurchaseCount)

    private fun getWinningLotto(): Lotto = exceptionHandler.handleInputValue(lottoGameInputView::inputWinningNumbers)

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
