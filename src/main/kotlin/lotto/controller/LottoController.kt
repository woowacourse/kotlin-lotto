package lotto.controller

import lotto.contants.Constants
import lotto.model.LottoTicket
import lotto.model.LottoMachine
import lotto.model.WinningLotto
import lotto.model.LottoResult
import lotto.view.UserInterface

class LottoController(
    private val userInterface: UserInterface = UserInterface(),
) {
    fun run() {
        val lottoTickets = generateLottoTickets()
        val winningLotto = getWinningLotto()
        val lottoResult = winningLotto.getResult(lottoTickets)
        getResult(lottoResult)
    }

    private fun generateLottoTickets(): List<LottoTicket> {
        val amount = userInterface.inputPurchaseAmount()
        val count = calculatePurchaseCount(amount)
        val lottoTickets = LottoMachine().purchase(count)
        userInterface.printLottoTickets(lottoTickets)
        return lottoTickets
    }

    private fun calculatePurchaseCount(amount: Int) = amount / Constants.LOTTO_AMOUNT

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = userInterface.getWinningNumbers()
        val bonusNumber = userInterface.getBonusNumber()
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun getResult(lottoResult: LottoResult) {
        val winningStatus = lottoResult.getWinningStatus()
        val profit = lottoResult.calculateProfit()
        userInterface.printResult(winningStatus)
        userInterface.printProfit(profit)
    }
}
