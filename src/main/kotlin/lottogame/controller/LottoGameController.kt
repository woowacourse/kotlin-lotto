package lottogame.controller

import lottogame.model.BonusLottoNumber
import lottogame.model.Lotto
import lottogame.model.LottoCount
import lottogame.model.LottoGameResult
import lottogame.model.LottoNumber
import lottogame.model.Money
import lottogame.model.Rank
import lottogame.model.generator.GeneralLottoNumber
import lottogame.model.generator.LottoMachine
import lottogame.view.LottoGameInputView
import lottogame.view.LottoGameOutputView
import kotlin.math.floor

class LottoGameController(
    private val inputView: LottoGameInputView,
    private val outputView: LottoGameOutputView,
    private val lottoMachine: LottoMachine,
) {
    fun startLottoGame() {
        val buyingCost: Money = inputView.inputPurchaseExpense()
        val lottoCount: LottoCount = determineManualLottoCount(buyingCost)
        val buyingLottie: List<Lotto> = buyLottie(lottoCount, buyingCost)
        val winningLotto: Lotto = Lotto(inputView.inputWinningNumbers())
        val bonusLottoNumber = createBonusLottoNumber(winningLotto.numbers)
        val lottoGameResult = LottoGameResult.of(bonusLottoNumber, winningLotto, buyingLottie)
        displayLottoResult(lottoGameResult, buyingCost)
    }

    private fun determineManualLottoCount(cost: Money): LottoCount {
        return runCatching {
            LottoCount.of(
                count = inputView.inputManualLottoCount(),
                lottoPrice = LOTTO_PRICE,
                cost = cost,
            )
        }.onFailure {
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
