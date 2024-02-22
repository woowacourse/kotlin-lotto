package lotto.model

class LottoResult(private val resultMap: Map<Rank, Int>) {
    override fun toString(): String {
        var result = ""

        Rank.entries.reversed().forEach { rank ->
            val count = resultMap[rank] ?: EMPTY_COUNT
            result +=
                when (rank) {
                    Rank.MISS -> ""
                    Rank.SECOND -> WINNING_BONUS_MESSAGE.format(rank.countOfMatch, rank.winningMoney, count)
                    else -> WINNING_MESSAGE.format(rank.countOfMatch, rank.winningMoney, count)
                }
        }
        return result
    }

    fun getProfitRate(): Double {
        val totalProfit =
            resultMap.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = resultMap.entries.sumOf { (_, count) -> count }
        return (totalProfit.toDouble() / (totalCount * PURCHASE_UNIT)) * RATE
    }

    companion object {
        private const val PURCHASE_UNIT = 1_000
        private const val RATE = 100
        private const val EMPTY_COUNT = 0

        private const val WINNING_BONUS_MESSAGE = "%d개 일치, 보너스 볼 일치(%d)- %d개\n"
        private const val WINNING_MESSAGE = "%d개 일치 (%d)- %d개\n"
    }
}
