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
    private val inputView: LottoGameInputView,
    private val outputView: LottoGameOutputView,
    private val lottieGenerator: AutoLottieGenerator,
) {
    fun startLottoGame() {
        val buyingCost: Money = createBuyingCost()
        val lottie: List<Lotto> = buyLottie(cost = buyingCost)
        val winningLotto = createWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto.numbers)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, lottie)
        displayLottoResult(lottoGameResult, buyingCost)
    }

    private fun createBuyingCost(): Money =
        runCatching {
            Money(inputView.inputPurchaseExpense())
        }.onFailure {
            if (it is IllegalArgumentException) return createBuyingCost()
        }.getOrThrow()

    private fun buyLottie(cost: Money): List<Lotto> =
        runCatching {
            lottieGenerator.generate(cost)
                .also(outputView::showPurchasedLottie)
        }.onFailure {
            if (it is IllegalArgumentException) return buyLottie(cost)
        }.getOrThrow()

    private fun createWinningLotto(): Lotto =
        runCatching {
            val winningNumbers: List<Int> = inputView.inputWinningNumbers()
            Lotto(winningNumbers)
        }.onFailure {
            if (it is IllegalArgumentException) return createWinningLotto()
        }.getOrThrow()

    private fun createBonusLottoNumber(winningLottoNumbers: List<LottoNumber>): LottoNumber =
        runCatching {
            val bonusNumber = inputView.inputBonusNumber()
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
            outputView.showGameResult(rankResults, truncateDecimal(earningRate))
        }.onFailure {
            if (it is IllegalArgumentException) return displayLottoResult(gameResult, purchaseExpense)
        }
    }

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * SCALE) / SCALE

    companion object {
        private const val SCALE = 100
        private val LOTTO_PRICE = Money(1_000)
    }
}
