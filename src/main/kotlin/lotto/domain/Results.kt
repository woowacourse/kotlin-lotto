package lotto.domain

class Results(
    private val value: List<Result>,
) {
    enum class Classification {
        PROFIT,
        LOSS,
        BREAKEVEN,
        ;

        companion object {
            fun from(profitRate: Double): Classification =
                when {
                    profitRate > 1.0 -> PROFIT
                    profitRate < 1.0 -> LOSS
                    else -> BREAKEVEN
                }
        }
    }

    fun getTally(): Map<Result, Int> {
        val tally = Result.entries.associateWith { 0 }.toMutableMap()
        value.forEach { result: Result ->
            tally[result] = tally.getValue(result) + 1
        }
        return tally.toMap()
    }

    fun getProfitRate(): Double {
        val profit: Long =
            value.sumOf { result: Result ->
                result.prize.toLong()
            }
        return profit / (value.size * Lotto.PRICE).toDouble()
    }

    companion object {
        fun from(
            winningLotto: WinningLotto,
            userLottos: List<Lotto>,
        ): Results =
            Results(
                userLottos.map { userLotto: Lotto ->
                    Result.from(winningLotto, userLotto)
                },
            )
    }
}
