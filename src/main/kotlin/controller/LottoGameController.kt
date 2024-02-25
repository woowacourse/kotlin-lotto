package controller

import model.Lotto
import model.LottoGameManager
import model.LottoGameResult
import model.LottoNumber
import model.Money
import util.ExceptionHandler
import view.LottoGameInputView
import view.LottoGameOutputView

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
    private val exceptionHandler: ExceptionHandler,
    private val randomLottoGameManager: LottoGameManager,
) {
    fun startLottoGame() {
        val purchaseExpense: Int = getPurchaseExpense()
        val lottie: List<Lotto> = purchaseLottie(purchaseExpense)
        val winningLotto = getWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, lottie)
        displayLottoResult(lottoGameResult, purchaseExpense)
    }

    private fun getPurchaseExpense(): Int = exceptionHandler.handleInputValue(lottoGameInputView::inputPurchaseExpense)

    private fun purchaseLottie(purchaseExpense: Int): List<Lotto> =
        exceptionHandler.handleInputValue {
            val lotteries = randomLottoGameManager.generateLotteries(purchaseExpense)
            lotteries.also(lottoGameOutputView::showPurchasedLottie)
        }

    private fun getWinningLotto(): Lotto =
        exceptionHandler.handleInputValue {
            val winningNumbers = lottoGameInputView.inputWinningNumbers()
            randomLottoGameManager.generateWinningLotto(winningNumbers)
        }

    private fun createBonusLottoNumber(winningLotto: Lotto): LottoNumber =
        exceptionHandler.handleInputValue {
            val bonusNumber = lottoGameInputView.inputBonusNumber()
            randomLottoGameManager.generateBonusLottoNumber(bonusNumber, winningLotto)
        }

    private fun displayLottoResult(
        lottoGameResult: LottoGameResult,
        purchaseExpense: Int,
    ) {
        exceptionHandler.handleOutputValue {
            val rankResults = lottoGameResult.getWinningResult()
            val earningRate = lottoGameResult.calculateEarningRate(Money(purchaseExpense))
            lottoGameOutputView.showGameResult(rankResults, earningRate)
        }
    }
}
