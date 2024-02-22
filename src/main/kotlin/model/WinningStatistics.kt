package model

class WinningStatistics(private val results: List<WinningStatistic>) {
    override fun toString(): String {
        return results.subList(0, results.size - 1).reversed ().joinToString("\n")
    }
}
