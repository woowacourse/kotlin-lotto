package lotto.model

class PrizeCalculator {
    fun calculateEarningRate(
        money: Int,
        result: Map<Rank, Int>,
    ): Double = calculateTotalPrize(result).toDouble() / money.toDouble()

    private fun calculateTotalPrize(result: Map<Rank, Int>): Int = result.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}
