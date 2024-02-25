package controller

import model.BonusLottoNumber
import model.GeneralLottoNumber
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
        val winningLotto = createWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, lottie)
        displayLottoResult(lottoGameResult, purchaseExpense)
    }

    private fun getPurchaseExpense(): Int =
        runCatching {
            lottoGameInputView.inputPurchaseExpense()
        }.onFailure {
            if (it is IllegalArgumentException) return getPurchaseExpense()
        }.getOrThrow()

    private fun purchaseLottie(purchaseExpense: Int): List<Lotto> =
        runCatching {
            lottoGenerator.generate(Money(purchaseExpense))
                .also(lottoGameOutputView::showPurchasedLottie)
        }.onFailure {
            if (it is IllegalArgumentException) return purchaseLottie(purchaseExpense)
        }.getOrThrow()

    private fun createWinningLotto(): Lotto =
        runCatching {
            val winningNumbers = lottoGameInputView.inputWinningNumbers()
            Lotto(*(winningNumbers.toIntArray()))
        }.onFailure {
            if (it is IllegalArgumentException) return createWinningLotto()
        }.getOrThrow()

    private fun createBonusLottoNumber(winningLotto: Lotto): LottoNumber =
        runCatching {
            val bonusNumber = lottoGameInputView.inputBonusNumber()
            val bonusLottoNumber: LottoNumber = BonusLottoNumber.of(GeneralLottoNumber(bonusNumber), winningLotto)
            bonusLottoNumber
        }.onFailure {
            if (it is IllegalArgumentException) {
                return createBonusLottoNumber(winningLotto)
            }
        }.getOrThrow()

    private fun displayLottoResult(
        gameResult: LottoGameResult,
        purchaseExpense: Int,
    ) {
        runCatching {
            val rankResults = gameResult.results.filterNot { it.rank == Rank.MISS }
            val earningRate = gameResult.calculateEarningRate(Money(purchaseExpense))
            lottoGameOutputView.showGameResult(rankResults, truncateDecimal(earningRate))
        }.onFailure {
            if (it is IllegalArgumentException) return displayLottoResult(gameResult, purchaseExpense)
        }
    }

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * SCALE) / SCALE

    companion object {
        private const val SCALE = 100
        private const val EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT = "보너스 번호는 우승 로또 번호와 중복될 수 없습니다."
    }
}
