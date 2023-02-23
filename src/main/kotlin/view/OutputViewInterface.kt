package view

import domain.LottoPurchaseInfo
import domain.LottoResult
import domain.Ticket

interface OutputViewInterface {
    fun printMessage(message: String)
    fun printResult(statisticsResult: LottoResult, profit: String)
    fun printTicket(purchaseInfo: LottoPurchaseInfo, ticket: Ticket)
    fun printStatistics(statisticsResult: LottoResult)
    fun printProfit(profit: String)
}
