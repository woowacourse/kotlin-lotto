package lotto.domain

class Results(
    private val value: List<Result>,
) {
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
            userLottos: Lottos,
        ): Results =
            Results(
                userLottos.value.map { userLotto: Lotto ->
                    Result.from(winningLotto, userLotto)
                },
            )
    }
}
