package view

interface ResultViewInterface {
    fun printResult(statisticsResult: List<Int>, profit: String)

    fun printStatistics(statisticsResult: List<Int>)
    fun printProfit(profit: String)
}
