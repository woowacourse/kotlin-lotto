package lotto.controller

import lotto.model.LottoIssueType
import lotto.model.LottoResult
import lotto.model.LottoStoreCashier
import lotto.model.LottoTicket
import lotto.model.LottoTicketIssueManager
import lotto.model.WinningLotto
import lotto.view.ViewFlow

class LottoController(
    private val viewFlow: ViewFlow = ViewFlow(),
) {
    fun run() {
        val possibleToLottoTicketCount = meetLottoStoreCashier()
        val lottoTickets = getLottoTickets(possibleToLottoTicketCount)
        val winningLotto = getWinningLotto()
        getResult(lottoTickets, winningLotto)
    }

    private fun meetLottoStoreCashier(): Int {
        val money = viewFlow.inputPurchaseAmount()
        val lottoStoreCashier = LottoStoreCashier(money)
        val possibleToLottoTicketCount = lottoStoreCashier.calculatePossibleToBuyLottoTicketCount()
        val customerAnswer = viewFlow.printLottoCount(possibleToLottoTicketCount)
        if (customerAnswer) {
            val change = lottoStoreCashier.calculateChange(possibleToLottoTicketCount)
            viewFlow.printChange(change)
        }
        return possibleToLottoTicketCount
    }

    private fun getLottoTickets(possibleToLottoTicketCount: Int): List<LottoTicket> {
        val customerWantToBuyManualLottoTicketCount = viewFlow.getManualLottoCount()
        val manualLottoNumbers =
            if (customerWantToBuyManualLottoTicketCount != 0) {
                viewFlow.getManualLottoNumbers(customerWantToBuyManualLottoTicketCount)
            } else {
                emptyList()
            }
        val lottoTicketIssueManager =
            LottoTicketIssueManager(
                possibleToLottoTicketCount,
                customerWantToBuyManualLottoTicketCount,
                manualLottoNumbers,
            )
        val lottoTickets = lottoTicketIssueManager.getLottoTickets(manualLottoNumbers)
        val autoLottoTicketCount = lottoTicketIssueManager.getAutoLottoTicketCount()
        viewFlow.printLottoTickets(customerWantToBuyManualLottoTicketCount, autoLottoTicketCount, lottoTickets)
        return lottoTickets
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = viewFlow.getWinningNumbers()
        val bonusNumber = viewFlow.getBonusNumber()
        val winningLottoTicket = LottoTicket(LottoIssueType.WINNING, winningNumbers)
        return WinningLotto(winningLottoTicket, bonusNumber)
    }

    private fun getResult(
        lottoTickets: List<LottoTicket>,
        winningLotto: WinningLotto,
    ) {
        val ranks = winningLotto.getRanks(lottoTickets)
        val lottoResult = LottoResult(ranks)
        val winningStatus = lottoResult.getWinningStatus()
        val profit = lottoResult.calculateProfit()
        viewFlow.printResult(winningStatus)
        viewFlow.printProfit(profit)
    }
}
