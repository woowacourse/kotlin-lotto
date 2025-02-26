package lotto.model

class PrizeCalculator(
    val winningLotto: WinningLotto,
    val publishedLotto: List<Lotto>,
) {
    val rankCount: Map<Rank, Int>

    init {
        rankCount = makeResult()
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

    fun calculateEarningRate(): Double = calculateTotalPrize(rankCount).toDouble() / (LOTTO_PRIZE * publishedLotto.size).toDouble()

    private fun calculateTotalPrize(result: Map<Rank, Int>): Int = result.entries.sumOf { (rank, count) -> rank.winningMoney * count }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
