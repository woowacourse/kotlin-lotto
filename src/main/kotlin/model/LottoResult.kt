package model

object LottoResult {
    private const val DECIMALS = 2
    private const val PERCENTAGE_MULTIPLIER = 100
    private const val BASE_MULTIPLIER = 10

    fun getStats(
        userLottos: List<Lotto>,
        resultLotto: Lotto,
        resultBonus: Bonus,
    ): Map<Rank, Int> {
        val ranks = mutableMapOf<Rank, Int>()

        userLottos.forEach {
            val rank = Rank.valueOf(it.getCountOfMatch(resultLotto), it.hasBonus(resultBonus))
            ranks[rank] = (ranks[rank] ?: 0) + 1
        }
        return ranks
    }

    fun calculateROI(
        amount: Amount,
        lottoResult: Map<Rank, Int>,
    ): Double {
        var prize = 0.0

        lottoResult.forEach {
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
