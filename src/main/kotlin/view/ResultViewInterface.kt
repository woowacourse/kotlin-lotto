package view

import domain.Rank

interface ResultViewInterface {
    fun printResult(winningCountBy: Map<Rank, Int>, profit: String)

    fun printStatistics(winningCountBy: Map<Rank, Int>)
    fun printProfit(profit: String)
}
