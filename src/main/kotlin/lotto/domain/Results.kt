package lotto.domain

class Results(
    private val list: List<Result>,
) {
    val profitRate: Double = this.calculateProfitRate()
    val tally: Map<Result, Int> = this.makeTally()
    val classification: Classification = Classification.from(profitRate)

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

    private fun makeTally(): Map<Result, Int> {
        val tally = Result.entries.associateWith { 0 }.toMutableMap()
        list.forEach { result: Result ->
            tally[result] = tally.getValue(result) + 1
        }
        return tally.toMap()
    }

    private fun calculateProfitRate(): Double {
        val profit: Long =
            list.sumOf { result: Result ->
                result.prize.toLong()
            }
        return profit / (list.size * Lotto.PRICE).toDouble()
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
