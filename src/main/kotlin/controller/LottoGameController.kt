package controller

import model.Lotto
import model.LottoGameResult
import model.LottoGenerator
import model.LottoNumber
import model.Money
import model.Rank
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
        val bonusLottoNumber = createBonusLottoNumber(winningLotto)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, lottie)
        displayLottoResult(lottoGameResult, purchaseExpense)
    }

    private fun displayLottoResult(
        lottoGameResult: LottoGameResult,
        purchaseExpense: Int,
    ) {
        val rankResults = lottoGameResult.results.filterNot { it.rank == Rank.MISS }
        val earningRate = lottoGameResult.calculateEarningRate(Money(purchaseExpense))
        lottoGameOutputView.showGameResult(rankResults, truncateDecimal(earningRate))
    }

    private fun createBonusLottoNumber(winningLotto: Lotto): LottoNumber {
        val bonusNumber = lottoGameInputView.inputBonusNumber()
        val bonusLottoNumber = LottoNumber(bonusNumber)
        check(bonusLottoNumber in winningLotto) {
            EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT
        }
        return bonusLottoNumber
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

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * SCALE) / SCALE

    companion object {
        private const val SCALE = 100
        private const val EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT = "보너스 번호는 우승 로또 번호와 중복될 수 없습니다."
    }
}
