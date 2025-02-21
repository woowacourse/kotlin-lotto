package lotto.controller

import lotto.domain.*
import lotto.generator.RandomGenerator
import lotto.util.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottos = getAndPrintPurchasedLottos(purchaseAmount)
        val lottoCalculator = getWinningInfoAndCalculator()
        val winningStats = getAndPrintWinningStats(lottoCalculator, lottos)
        val prize = lottoCalculator.calculatePrize(winningStats)
        outputView.printProfit(lottoCalculator.calculateProfit(prize, purchaseAmount.value))
    }

    private fun getWinningInfoAndCalculator(): LottoCalculator {
        val winningNumber = getWinningNumber()
        val winningLotto = getWinningLotto(winningNumber)
        return LottoCalculator(winningLotto.winningNumber, winningLotto.bonusNumber)
    }

    private fun getAndPrintPurchasedLottos(purchaseAmount: PurchaseAmount): List<Lotto> {
        val lottos = LottoMachine().buyLottos(purchaseAmount.value)
        outputView.printPurchasedLottos(lottos)
        return lottos
    }

    private fun getAndPrintWinningStats(lottoCalculator: LottoCalculator, lottos: List<Lotto>): Map<Rank, Int> {
        val winningStats = lottoCalculator.matchLottos(lottos)
        outputView.printWinningStats(winningStats.filter { it.key != Rank.NONE })
        return winningStats
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val purchaseAmount = inputView.getPurchaseAmount()
        validateNumeric(purchaseAmount)
        return PurchaseAmount(purchaseAmount.toInt())
    }

    private fun getWinningNumber(): Lotto {
        val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { it.trim() }
        validateWinningNumber(winningNumber)
        return Lotto(winningNumber.map { LottoNumber(it.toInt()) })
    }

    private fun getWinningLotto(winningNumber: Lotto): WinningLotto {
        val bonusNumber = inputView.getBonusNumber()
        validateNumeric(bonusNumber)
        return WinningLotto(winningNumber, LottoNumber(bonusNumber.toInt()))
    }

    private fun validateNumeric(input: String) {
        input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
    }

    private fun validateWinningNumber(winningNumber: List<String>) {
        winningNumber.forEach {
            validateNumeric(it)
        }
    }

    companion object {
        private const val DELIMITERS = ","
        private const val ERROR_NOT_NUMBER = "[ERROR] 양수만 입력 가능합니다."
    }
}
