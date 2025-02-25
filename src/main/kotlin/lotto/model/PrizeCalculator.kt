package lotto.model

class PrizeCalculator(
    val winningLotto: WinningLotto,
    val publishedLotto: List<Lotto>,
    val amount: Amount,
) {
    val result: Map<Rank, Int>

    init {
        result = makeResult()
    }

    private fun makeResult(): Map<Rank, Int> {
        val result: MutableMap<Rank, Int> = mutableMapOf()
        publishedLotto.forEach {
            val rank = winningLotto.findRank(it)
            result[rank] = result.getOrDefault(rank, 0) + 1
        }
        Rank.entries.forEach {
            result[it] = result.getOrDefault(it, 0)
        }
        return result
    }

    fun calculateEarningRate(): Double = calculateTotalPrize(result).toDouble() / amount.money.toDouble()

    private fun calculateTotalPrize(result: Map<Rank, Int>): Int = result.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}
