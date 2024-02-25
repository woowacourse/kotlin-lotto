package controller

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
    private val lottoGenerator: LottoGenerator,
    private val exceptionHandler: ExceptionHandler,
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
            lottoGenerator.generate(Money(purchaseExpense)).also(lottoGameOutputView::showPurchasedLottie)
        }

    private fun getWinningLotto(): Lotto =
        exceptionHandler.handleInputValue {
            val winningNumbers = lottoGameInputView.inputWinningNumbers()
            Lotto(*(winningNumbers.toIntArray()))
        }

    private fun createBonusLottoNumber(winningLotto: Lotto): LottoNumber =
        exceptionHandler.handleInputValue {
            val bonusNumber = lottoGameInputView.inputBonusNumber()
            LottoNumber(bonusNumber, winningLotto)
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
