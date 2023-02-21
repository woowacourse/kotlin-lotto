package controller

import domain.LottoPurchaseInfo
import domain.LottoSeller
import domain.LottoStatistics
import domain.Ticket
import domain.WinningLotto
import view.InputViewInterface
import view.ResultViewInterface

class LottoController(
    private val inputView: InputViewInterface,
    private val resultView: ResultViewInterface
) {
    private val lottoSeller: LottoSeller by lazy { LottoSeller() }

    fun run() {
        val purchaseLottoMoney = inputView.getMoney()
        val purchaseInfo = inputView.getManualCount(purchaseLottoMoney)
        val ticket = getManualAndAutoTicket(purchaseInfo)
        resultView.printTicket(purchaseInfo, ticket)
        val winningLotto = initializeWinningLotto()
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.matchTicket(ticket)
        val profit = lottoStatistics.yield(result, purchaseLottoMoney)
        resultView.printResult(result, profit)
    }

    private fun getManualAndAutoTicket(purchaseInfo: LottoPurchaseInfo): Ticket {
        while (true) {
            val manuals = inputView.getManualNumbers(purchaseInfo)
            kotlin.runCatching { lottoSeller.sellManualAndAuto(purchaseInfo, manuals) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    private fun initializeWinningLotto(): WinningLotto {
        while (true) {
            val winningNumbers = inputView.getWinningNumbers()
            val bonusNumber = inputView.getBonusNumber()
            runCatching { WinningLotto(winningNumbers, bonusNumber) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }
}
