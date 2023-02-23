package controller

import domain.LottoPurchaseInfo
import domain.LottoSeller
import domain.LottoStatistics
import domain.PurchaseLottoMoney
import domain.Ticket
import domain.WinningLotto
import view.InputViewInterface
import view.OutputViewInterface

class LottoController(
    private val inputView: InputViewInterface,
    private val resultView: OutputViewInterface
) {
    private val lottoSeller: LottoSeller by lazy { LottoSeller() }

    fun run() {
        val purchaseLottoMoney = inputView.getMoney()
        val purchaseInfo = inputView.getManualCount(purchaseLottoMoney)
        val ticket = getManualAndAutoTicket(purchaseInfo)
        resultView.printTicket(purchaseInfo, ticket)
        val winningLotto = initializeWinningLotto()
        printResult(winningLotto, ticket, purchaseLottoMoney)
    }

    private fun getManualAndAutoTicket(purchaseInfo: LottoPurchaseInfo): Ticket {
        while (true) {
            val manuals = inputView.getManualNumbers(purchaseInfo)
            val ticket = lottoSeller.sellManualAndAuto(purchaseInfo, manuals) ?: continue
            println()
            return ticket
        }
    }

    private fun initializeWinningLotto(): WinningLotto {
        while (true) {
            val winningNumbers = inputView.getWinningNumbers()
            val bonusNumber = inputView.getBonusNumber()
            runCatching { WinningLotto(winningNumbers, bonusNumber) }
                .onSuccess {
                    println()
                    return it
                }
                .onFailure { println(it.message) }
        }
    }

    private fun printResult(winningLotto: WinningLotto, ticket: Ticket, purchaseLottoMoney: PurchaseLottoMoney) {
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.matchTicket(ticket)
        val profit = lottoStatistics.yield(result, purchaseLottoMoney)
        resultView.printResult(result, profit)
    }
}
