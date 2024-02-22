package controller

import model.Lotto
import model.LottoGameResult
import model.LottoGenerator
import model.LottoNumber
import model.Money
import view.LottoGameInputView
import view.LottoGameOutputView
import kotlin.math.floor

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
    private val lottoGenerator: LottoGenerator,
) {
    fun startLottoGame() {
        val purchaseExpense: Int = getPurchaseExpense()
        val lottie: List<Lotto> = purchaseLottie(purchaseExpense)
        val winningLotto = getWinningLotto()
        val bonusLottoNumber = getBonusLottoNumber()
        val lottoGameResult = getLottoGameResult(bonusLottoNumber, winningLotto, lottie)
        displayLottoResult(lottoGameResult, purchaseExpense)
    }

    private fun displayLottoResult(
        lottoGameResult: LottoGameResult,
        purchaseExpense: Int,
    ) {
        val rankResults = lottoGameResult.results
        val earningRate = lottoGameResult.calculateEarningRate(Money(purchaseExpense))
        lottoGameOutputView.showGameResult(rankResults, truncateDecimal(earningRate))
    }

    private fun getLottoGameResult(
        bonusLottoNumber: LottoNumber,
        winningLotto: Lotto,
        lottie: List<Lotto>,
    ) = LottoGameResult(
        bonusNumber = bonusLottoNumber,
        winningLotto = winningLotto,
        purchasedLottie = lottie,
    )

    private fun getBonusLottoNumber(): LottoNumber {
        val bonusNumber = lottoGameInputView.inputBonusNumber()
        return LottoNumber(bonusNumber)
    }

    private fun getWinningLotto(): Lotto {
        val winningNumbers = lottoGameInputView.inputWinningNumbers()
        return Lotto(*(winningNumbers.toIntArray()))
    }

    private fun getPurchaseExpense(): Int {
        return lottoGameInputView.inputPurchaseExpense()
    }

    private fun purchaseLottie(purchaseExpense: Int): List<Lotto> {
        val lottie = lottoGenerator.generate(Money(purchaseExpense))
        lottoGameOutputView.showPurchasedLottie(lottie)
        return lottie
    }

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * 100) / 100
}
