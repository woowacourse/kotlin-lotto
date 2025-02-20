package lotto.controller

import lotto.domain.LottoMachine
import lotto.domain.LottoResult
import lotto.validator.InputValidator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val validator = InputValidator()

    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottoMachine = LottoMachine()
        val lottos = lottoMachine.buyLottos(purchaseAmount)

        outputView.printPurchasedLottos(lottos)

        val winningNumber = getWinningNumber()
        val bonusNumber = getBonusNumber(winningNumber)

        val lottoResult = LottoResult(winningNumber, bonusNumber)
        lottoResult.matchLotto(lottos)

        outputView.printWinningStats(lottoResult.winningStats)

        val prize = lottoResult.calculatePrize()
        outputView.printProfit(lottoResult.calculateProfit(prize, purchaseAmount))
    }

    private fun getPurchaseAmount(): Int {
        val purchaseAmount = inputView.getPurchaseAmount()
        validator.validatePurchaseAmount(purchaseAmount)
        return parseToInt(purchaseAmount)
    }

    private fun parseToInt(input: String): Int {
        return input.toInt()
    }

    private fun getWinningNumber(): List<Int> {
        val winningNumber = inputView.getWinningNumber().split(", ").map { it.trim() }
        validator.validateWinningNumber(winningNumber)
        return winningNumber.map { parseToInt(it) }
    }

    private fun getBonusNumber(winningNumber: List<Int>): Int {
        val bonusNumber = inputView.getBonusNumber()
        validator.validateBonusNumber(bonusNumber, winningNumber)
        return parseToInt(bonusNumber)
    }
}
