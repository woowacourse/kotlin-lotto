package lotto.model

class WinningTable(winnings: Map<Rank, RankCount>) {
    var winnings = winnings
        private set

    init {
        val winningTable: MutableMap<Rank, RankCount> =
            Rank.entries.associateWith { RankCount(0) }.toMutableMap()
        Rank.entries.forEach {
            winningTable[it] = winnings[it] ?: RankCount(0)
        }
        this.winnings = winningTable
    }

    fun calculateWinningRate(purchasePrice: Int): Float {
        val winningPrize = winnings.map { it.key.winningMoney * it.value.num }.sum()
        return winningPrize.toFloat() / purchasePrice
    }
}
