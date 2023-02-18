package view

import domain.Rank

interface ResultViewInterface {
    fun printResult(statisticsResult: Map<Rank, Int>, profit: String)

    fun printStatistics(statisticsResult: Map<Rank, Int>)
    fun printProfit(profit: String)
}
