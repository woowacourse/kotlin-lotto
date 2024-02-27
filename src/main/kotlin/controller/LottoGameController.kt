package controller

import model.BonusLottoNumber
import model.GeneralLottoNumber
import model.Lotto
import model.LottoCount
import model.LottoGameResult
import model.LottoMachine
import model.LottoNumber
import model.Money
import model.Rank
import view.LottoGameInputView
import view.LottoGameOutputView
import kotlin.math.floor

class LottoGameController(
    private val inputView: LottoGameInputView,
    private val outputView: LottoGameOutputView,
    private val lottoMachine: LottoMachine,
) {
    fun startLottoGame() {
        val buyingCost: Money = createBuyingCost()
        val lottoCount: LottoCount = determineManualLottoCount(buyingCost)
        val buyingLottie: List<Lotto> = buyLottie(lottoCount, buyingCost)
        val winningLotto: Lotto = createWinningLotto()
        val bonusLottoNumber = createBonusLottoNumber(winningLotto.numbers)
        val lottoGameResult = LottoGameResult(bonusLottoNumber, winningLotto, buyingLottie)
        displayLottoResult(lottoGameResult, buyingCost)
    }

    private fun createBuyingCost(): Money =
        runCatching {
            Money(inputView.inputPurchaseExpense())
        }.onFailure {
            if (it is IllegalArgumentException) return createBuyingCost()
        }.getOrThrow()

    private fun determineManualLottoCount(cost: Money): LottoCount {
        return runCatching {
            LottoCount.of(
                count = inputView.inputManualLottoCount(),
                lottoPrice = LOTTO_PRICE,
                cost = cost,
            )
        }.onFailure {
            if (it is IllegalStateException) return determineManualLottoCount(cost)
            if (it is IllegalArgumentException) return determineManualLottoCount(cost)
        }.getOrThrow()
    }

    private fun buyLottie(
        manualLottoCount: LottoCount,
        buyingCost: Money,
    ): List<Lotto> {
        val manualLottie = buyManualLottie(count = manualLottoCount)
        val restCost = buyingCost - LOTTO_PRICE * manualLottoCount.amount
        val autoLottie = buyAutoLottie(restCost)
        outputView.showPurchaseLotto(manualLottie, autoLottie)
        return manualLottie + autoLottie
    }

    private fun buyManualLottie(count: LottoCount): List<Lotto> =
        runCatching {
            val manualLottie = inputView.inputManualLottoNumbers(count.amount)
            lottoMachine.generateManualLottie(manualLottie)
        }.onFailure {
            if (it is IllegalArgumentException) return buyManualLottie(count)
        }.getOrThrow()

    private fun buyAutoLottie(cost: Money): List<Lotto> =
        runCatching {
            lottoMachine.generateAutoLottie(cost)
        }.onFailure {
            if (it is IllegalArgumentException) return buyAutoLottie(cost)
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
            BonusLottoNumber.of(GeneralLottoNumber(bonusNumber), winningLottoNumbers)
        }.onFailure {
            if (it is IllegalArgumentException) return createBonusLottoNumber(winningLottoNumbers)
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
