package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.validator.BonusNumberValidator
import lotto.validator.PurchaseAmountValidator
import lotto.validator.WinningNumberValidator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottos = LottoMachine().buyLottos(purchaseAmount)
        outputView.printPurchasedLottos(lottos)
        val winningNumber = getWinningNumber()
        val bonusNumber = getBonusNumber(winningNumber)
        val lottoResult = LottoResult(winningNumber, bonusNumber)
        val winningStats = lottoResult.matchLotto(lottos)
        outputView.printWinningStats(winningStats)
        val prize = lottoResult.calculatePrize(winningStats)
        outputView.printProfit(lottoResult.calculateProfit(prize, purchaseAmount))
    }

    private fun getPurchaseAmount(): Int {
        val purchaseAmount = inputView.getPurchaseAmount()
        PurchaseAmountValidator(purchaseAmount)
        return parseToInt(purchaseAmount)
    }

    private fun parseToInt(input: String): Int {
        return input.toInt()
    }

    private fun getWinningNumber(): Lotto {
        val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { it.trim() }
        WinningNumberValidator(winningNumber)
        return Lotto(winningNumber.map { LottoNumber(parseToInt(it)) })
    }

    private fun getBonusNumber(winningNumber: Lotto): LottoNumber {
        val bonusNumber = inputView.getBonusNumber()
        BonusNumberValidator(bonusNumber, winningNumber)
        return LottoNumber(parseToInt(bonusNumber))
    }

    companion object {
        const val DELIMITERS = ","
    }
}
