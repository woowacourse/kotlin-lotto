package lotto.domain

class RankCounter(
    numberOfEachRank: Map<String, Int> =
        Rank.values().associate { it.name to NUMBER_DEFAULT_VALUE }
) {
    private val _numberOfEachRank: MutableMap<String, Int> = numberOfEachRank.toMutableMap()
    val numberOfEachRank: Map<String, Int>
        get() = _numberOfEachRank.toMap()

    fun count(lotteries: List<Lottery>, winningLottery: WinningLottery) {
        lotteries.forEach {
            val rank = Rank.valueOf(
                it.countMatches(winningLottery.lottery),
                it.containBonusNumber(winningLottery.bonusNumber)
            )

            _numberOfEachRank[rank.name] =
                _numberOfEachRank.getOrDefault(rank.name, NUMBER_DEFAULT_VALUE) + INCREASE_VALUE
        }
    }

    fun calculateTotalPrize(): Long {
        val prize = numberOfEachRank.map { (name, count) ->
            Rank.valueOf(name).calculatePrize(count)
        }.sum()
        return prize
    }

    companion object {
        private const val NUMBER_DEFAULT_VALUE = 0
        private const val INCREASE_VALUE = 1
    }
}
