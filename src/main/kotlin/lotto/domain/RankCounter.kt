package lotto.domain

class RankCounter(
    numberOfEachRank: Map<Rank, Int> =
        Rank.values().associateWith { NUMBER_DEFAULT_VALUE }
) {
    private val _numberOfEachRank: MutableMap<Rank, Int> = numberOfEachRank.toMutableMap()
    val numberOfEachRank: Map<Rank, Int>
        get() = _numberOfEachRank.toMap()

    fun count(lotteries: List<Lottery>, winningLottery: WinningLottery) {
        lotteries.forEach {
            val rank = Rank.valueOf(
                it.countMatches(winningLottery.lottery),
                it.containBonusNumber(winningLottery.bonusNumber)
            )

            _numberOfEachRank[rank] =
                _numberOfEachRank.getOrDefault(rank, NUMBER_DEFAULT_VALUE) + INCREASE_VALUE
        }
    }

    fun calculateTotalPrize(): Long {
        val prize = numberOfEachRank.map { (rank, count) ->
            rank.calculatePrize(count)
        }.sum()
        return prize
    }

    companion object {
        private const val NUMBER_DEFAULT_VALUE = 0
        private const val INCREASE_VALUE = 1
    }
}
