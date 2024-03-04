package lottogame.controller

import lottogame.model.BonusLottoNumber
import lottogame.model.GeneralLottoNumber
import lottogame.model.Lotto
import lottogame.model.LottoCount
import lottogame.model.LottoGameResult
import lottogame.model.LottoNumber
import lottogame.model.LottoResult
import lottogame.model.Money
import lottogame.model.Rank
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

    private tailrec fun determineManualLottoCount(cost: Money): LottoCount {
        return runCatching {
            LottoCount(
                count = inputView.inputManualLottoCount(),
                lottoPrice = LOTTO_PRICE,
                cost = cost,
            )
        }.onFailure { if (it is IllegalArgumentException) return determineManualLottoCount(cost) }
            .getOrThrow()
    }

    private fun buyLottie(
        manualLottoCount: LottoCount,
        buyingCost: Money,
    ): List<Lotto> {
        val manualLottie = buyManualLottie(count = manualLottoCount)
        val restCost = buyingCost - LOTTO_PRICE * manualLottoCount.amount
        val autoLottie = lottoMachine.generateAutoLottie(restCost)
        outputView.showPurchaseLotto(manualLottie, autoLottie)
        return manualLottie + autoLottie
    }

    private fun buyManualLottie(count: LottoCount): List<Lotto> {
        val manualLottie = inputView.inputManualLottoNumbers(count.amount)
        val lottieResult = lottoMachine.generateManualLottie(manualLottie)
        lottieResult.forEach { validateLottoResults(it) { return buyManualLottie(count) } }
        return lottieResult.map { (it as LottoResult.Success).lotto }
    }

    private inline fun validateLottoResults(
        lottoResult: LottoResult,
        onFailure: () -> Unit,
    ) {
        when (lottoResult) {
            is LottoResult.Success -> {}
            is LottoResult.InvalidSort -> {
                println(lottoResult.message)
                onFailure()
            }

            is LottoResult.InvalidDuplicateNumber -> {
                println(lottoResult.message)
                onFailure()
            }

            is LottoResult.InvalidNumberSize -> {
                println(lottoResult.message)
                onFailure()
            }
        }
    }

    private tailrec fun createBonusLottoNumber(winningLottoNumbers: List<LottoNumber>): LottoNumber {
        val bonusNumber = inputView.inputBonusNumber()
        return runCatching { BonusLottoNumber(GeneralLottoNumber(bonusNumber), winningLottoNumbers) }
            .onFailure { if (it is IllegalArgumentException) return createBonusLottoNumber(winningLottoNumbers) }
            .getOrThrow()
    }

    private fun displayLottoResult(
        gameResult: LottoGameResult,
        purchaseExpense: Money,
    ) {
        val rankResults = gameResult.results.filterNot { it.rank == Rank.MISS }
        val earningRate = gameResult.calculateEarningRate(purchaseExpense)
        outputView.showGameResult(rankResults, truncateDecimal(earningRate))
    }

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * SCALE) / SCALE

    companion object {
        private const val SCALE = 100
        private val LOTTO_PRICE = Money(1_000)
    }
}
