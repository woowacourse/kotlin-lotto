package model

object WinningResult {
    private const val DECIMALS = 2
    private const val BASE_MULTIPLIER = 10

    fun getStats(
        userLottos: List<Lotto>,
        winningLotto: Lotto,
        bonus: Bonus,
    ): Map<Rank, Int> {
        val ranks = mutableMapOf<Rank, Int>()

        userLottos.forEach {
            val rank = Rank.valueOf(it.getCountOfMatch(winningLotto), it.hasBonus(bonus))
            ranks[rank] = (ranks[rank] ?: 0) + 1
        }
        return ranks
    }

    fun calculateROI(
        amount: Amount,
        winningResult: Map<Rank, Int>,
    ): Double {
        var prize = 0.0

        winningResult.forEach {
            prize += (it.key.winningMoney * it.value)
        }

        return (prize / amount.money).round(DECIMALS)
    }

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= BASE_MULTIPLIER }
        return kotlin.math.round(this * multiplier) / multiplier
    }
}
