package view

import domain.Rank
import domain.Ticket

interface ResultViewInterface {
    fun printResult(winningCountBy: Map<Rank, Int>, profit: String)
    fun printStatistics(winningCountBy: Map<Rank, Int>)
    fun printProfit(profit: String)
    fun printCount(count: Int)
    fun printTicket(manualTicket: Ticket, autoTicket: Ticket)
}
