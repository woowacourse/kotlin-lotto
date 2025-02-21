package lotto.controller

import lotto.domain.*
import lotto.generator.RandomGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottos = LottoMachine(RandomGenerator()).buyLottos(purchaseAmount.value)
        outputView.printPurchasedLottos(lottos)

        val winningNumber = getWinningNumber()
        val winningLotto = getWinningLotto(winningNumber)
        val lottoResult = LottoResult(winningLotto.winningNumber, winningLotto.bonusNumber)

        val winningStats = lottoResult.matchLotto(lottos)
        outputView.printWinningStats(winningStats)
        val prize = lottoResult.calculatePrize(winningStats)
        outputView.printProfit(lottoResult.calculateProfit(prize, purchaseAmount.value))
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
