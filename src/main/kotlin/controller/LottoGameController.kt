package controller

import model.AutoLottieGenerator
import model.BonusLottoNumber
import model.GeneralLottoNumber
import model.Lotto
import model.LottoGameResult
import model.LottoNumber
import model.Money
import model.Rank
import view.LottoGameInputView
import view.LottoGameOutputView
import kotlin.math.floor

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
    private val lottieGenerator: AutoLottieGenerator,
) {
    fun startLottoGame() {
        val purchaseExpense: Money = createPurchaseExpense()
        val lottie: List<Lotto> = purchaseLottie(purchaseExpense)
        val winningLotto = createWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto.numbers)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, lottie)
        displayLottoResult(lottoGameResult, purchaseExpense)
    }

    private fun createPurchaseExpense(): Money =
        runCatching {
            Money(lottoGameInputView.inputPurchaseExpense())
        }.onFailure {
            if (it is IllegalArgumentException) return createPurchaseExpense()
        }.getOrThrow()

    private fun purchaseLottie(expense: Money): List<Lotto> =
        runCatching {
            lottieGenerator.generate(expense)
                .also(lottoGameOutputView::showPurchasedLottie)
        }.onFailure {
            if (it is IllegalArgumentException) return purchaseLottie(expense)
        }.getOrThrow()

    private fun createWinningLotto(): Lotto =
        runCatching {
            val winningNumbers = lottoGameInputView.inputWinningNumbers()
            Lotto(*(winningNumbers.toIntArray()))
        }.onFailure {
            if (it is IllegalArgumentException) return createWinningLotto()
        }.getOrThrow()

    private fun createBonusLottoNumber(winningLottoNumbers: List<LottoNumber>): LottoNumber =
        runCatching {
            val bonusNumber = lottoGameInputView.inputBonusNumber()
            val bonusLottoNumber: LottoNumber =
                BonusLottoNumber.of(GeneralLottoNumber(bonusNumber), winningLottoNumbers)
            bonusLottoNumber
        }.onFailure {
            if (it is IllegalArgumentException) {
                return createBonusLottoNumber(winningLottoNumbers)
            }
        }.getOrThrow()

    private fun displayLottoResult(
        gameResult: LottoGameResult,
        purchaseExpense: Money,
    ) {
        runCatching {
            val rankResults = gameResult.results.filterNot { it.rank == Rank.MISS }
            val earningRate = gameResult.calculateEarningRate(purchaseExpense)
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
