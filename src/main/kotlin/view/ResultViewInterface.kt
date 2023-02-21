package view

import domain.LottoPurchaseInfo
import domain.Rank
import domain.Ticket

interface ResultViewInterface {
    fun printResult(statisticsResult: Map<Rank, Int>, profit: String)
    fun printTicket(purchaseInfo: LottoPurchaseInfo, ticket: Ticket)
    fun printStatistics(statisticsResult: Map<Rank, Int>)
    fun printProfit(profit: String)
}
