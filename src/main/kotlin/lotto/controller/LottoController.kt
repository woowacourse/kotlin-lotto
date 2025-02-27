package lotto.controller

import lotto.contants.LottoRuleConstants
import lotto.model.*
import lotto.view.UserInterface

class LottoController(
    private val userInterface: UserInterface = UserInterface(),
) {
    fun run() {

        val cashier = meetCashier()
        val lottoCount = calculateLottoTicketAmount(cashier)
        calculateLottoTicketCount(cashier, lottoCount)
        val manualLottoCount = userInterface.getManualLottoCount()
        val manualLottoNumber = userInterface.getManualLottoNumber(manualLottoCount)
        val lottoTicketCountManager = LottoTicketCountManager(LottoTicketCount(lottoCount), LottoTicketCount(manualLottoCount))
        val autoLottoCount = lottoTicketCountManager.autoLottoCount
        // 수동 번호를 입력하세요.
        val lottoTicket = LottoMachine(LottoTicketCount(manualLottoCount), LottoTicketCount(autoLottoCount), manualLottoNumber).issueLottoTickets()
        userInterface.printLottoTickets(manualLottoCount, autoLottoCount, lottoTicket)
        val winningLotto = getWinningLotto()
        val lottoResult = winningLotto.getResult(lottoTicket)
        getResult(lottoResult)
    }

    private fun meetCashier() : LottoStoreCashier {
        val amount = userInterface.inputPurchaseAmount()
        val cashier = LottoStoreCashier(amount)
        return cashier
    }

    private fun calculateLottoTicketAmount(cashier: LottoStoreCashier): Int {
        val lottoCount = cashier.calculateCount()
        return lottoCount
    }

    private fun calculateLottoTicketCount(cashier: LottoStoreCashier, lottoCount: Int) {
        val customerAnswer = userInterface.printLottoCount(lottoCount)
        if (customerAnswer) {
            val change = cashier.calculateChange()
            userInterface.printChange(change)
        }
    }

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
