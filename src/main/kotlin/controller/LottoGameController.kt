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
        // 1
        val purchaseExpense: Int = lottoGameInputView.inputPurchaseExpense()
        // 2
        val autoLottie: List<Lotto> = lottoGenerator.generate(Money(purchaseExpense))
        lottoGameOutputView.showPurchasedLottie(autoLottie)
        // 3
        val winningNumbers: List<Int> = lottoGameInputView.inputWinningNumbers()
        val winningLotto = Lotto(*(winningNumbers.toIntArray()))
        // 4
        val bonusNumber: Int = lottoGameInputView.inputBonusNumber()
        val bonusLottoNumber = LottoNumber(bonusNumber)
        // 5
        val lottoGameResult =
            LottoGameResult(
                bonusNumber = bonusLottoNumber,
                winningLotto = winningLotto,
                purchasedLottie = autoLottie,
            )
        val rankResults = lottoGameResult.results
        val earningRate = lottoGameResult.calculateEarningRate(Money(purchaseExpense))
        lottoGameOutputView.showGameResult(rankResults, truncateDecimal(earningRate))
    }

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * 100) / 100
}
