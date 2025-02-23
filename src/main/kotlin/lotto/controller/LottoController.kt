package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.WinningLotto
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
        val lottoTickets = LottoMachine().buyLottoTickets(purchaseAmount)
        outputView.printPurchasedLottos(lottoTickets)
        val winningNumber = getWinningNumber()
        val bonusNumber = getBonusNumber(winningNumber)
        val lottoResult = LottoResult(WinningLotto(winningNumber, bonusNumber))
        lottoResult.calculateWinningStats(lottoTickets)
        outputView.printWinningStats(lottoResult.getWinningStats())
        lottoResult.calculatePrize()
        outputView.printProfit(lottoResult.getProfitRate())
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
        return Lotto(winningNumber.map { LottoNumber(it.toInt()) })
    }

    private fun getBonusNumber(winningNumber: Lotto): LottoNumber {
        val bonusNumber = inputView.getBonusNumber()
        BonusNumberValidator(bonusNumber, winningNumber)
        return LottoNumber(bonusNumber.toInt())
    }

    companion object {
        const val DELIMITERS = ","
    }
}
