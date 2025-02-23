package lotto.model

class PrizeCalculator {
    fun calculateEarningRate(
        money: Int,
        result: Map<Rank, Int>,
    ): Double {
        return calculateTotalPrize(result).toDouble() / money.toDouble()
    }

    private fun calculateTotalPrize(result: Map<Rank, Int>): Int {
        return result.entries.sumOf { (rank, count) -> rank.winningMoney * count }
    }
}
