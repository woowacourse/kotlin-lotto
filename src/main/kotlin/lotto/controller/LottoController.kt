package lotto.controller

import lotto.contants.Constants
import lotto.model.*
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    fun run() {
        val lottoTickets = generateLottoTickets()
        val winningLotto = getWinningLotto()
        val lottoResult = winningLotto.getResult(lottoTickets)
        getResult(lottoResult)
    }

    private fun generateLottoTickets(): List<LottoTicket> {
        val amount = InputView.inputPurchaseAmount()
        val count = calculatePurchaseCount(amount)
        val lottoTickets = LottoMachine().purchase(count)
        OutputView.printLottoTickets(lottoTickets)
        return lottoTickets
    }

    private fun calculatePurchaseCount(amount: Int) = amount / Constants.LOTTO_AMOUNT

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber()
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun getResult(lottoResult: LottoResult) {
        val profit = lottoResult.calculateProfit()
        val winningStatus = lottoResult.getWinningStatus()
        OutputView.printResult(winningStatus)
        OutputView.printProfit(profit)
    }
}
