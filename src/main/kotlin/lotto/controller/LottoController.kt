package lotto.controller

import lotto.model.*
import lotto.view.UserInterface

class LottoController(
    private val userInterface: UserInterface = UserInterface(),
) {
    fun run(): Result<Unit> {
        return kotlin.runCatching {
            val possibleToLottoTicketCount = meetLottoStoreCashier()
            val lottoTickets = getLottoTickets(possibleToLottoTicketCount)
            val winningLotto = getWinningLotto()
            getResult(lottoTickets, winningLotto)
        }.map {
            Result.success(Unit)
        }.getOrElse {
            e ->  Result.failure(Exception("(로또 실행 중 오류 발생) : ${e.message}"))
        }

    }

    private fun meetLottoStoreCashier() : Int {
        val money = userInterface.inputPurchaseAmount()
        val lottoStoreCashier = LottoStoreCashier(money)
        val possibleToLottoTicketCount = lottoStoreCashier.calculatePossibleToBuyLottoTicketCount()
        val customerAnswer = userInterface.printLottoCount(possibleToLottoTicketCount)
        if (customerAnswer) {
            val change = lottoStoreCashier.calculateChange(possibleToLottoTicketCount)
            userInterface.printChange(change)
        }
        return possibleToLottoTicketCount
    }

    private fun getLottoTickets(possibleToLottoTicketCount: Int): List<LottoTicket> {
        val customerWantToBuyManualLottoTicketCount = userInterface.getManualLottoCount()
        val manualLottoNumbers = userInterface.getManualLottoNumbers(customerWantToBuyManualLottoTicketCount)
        val lottoTicketIssueManager = LottoTicketIssueManager(possibleToLottoTicketCount, customerWantToBuyManualLottoTicketCount, manualLottoNumbers)
        val lottoTickets = lottoTicketIssueManager.getLottoTickets(manualLottoNumbers)
        val autoLottoTicketCount = lottoTicketIssueManager.getCustomerWantToBuyManualLottoTicketCount()
        userInterface.printLottoTickets(customerWantToBuyManualLottoTicketCount, autoLottoTicketCount, lottoTickets)
        return lottoTickets
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = userInterface.getWinningNumbers()
        val bonusNumber = userInterface.getBonusNumber()
        val winningLottoTicket = LottoTicket(LottoIssueType.WINNING, winningNumbers)
        return WinningLotto(winningLottoTicket, bonusNumber)
    }

    private fun getResult(lottoTickets: List<LottoTicket>, winningLotto: WinningLotto) {
        val ranks = winningLotto.getRanks(lottoTickets)
        val lottoResult = LottoResult(ranks)
        val winningStatus = lottoResult.getWinningStatus()
        val profit = lottoResult.calculateProfit()
        userInterface.printResult(winningStatus)
        userInterface.printProfit(profit)
    }
}
