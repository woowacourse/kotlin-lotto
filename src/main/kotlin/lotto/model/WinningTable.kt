package lotto.model

class WinningTable(winnings: Map<Rank, Int>) {
    var winnings = winnings
        private set

    init {
        val winningTable: MutableMap<Rank, Int> = Rank.entries.associateWith { 0 }.toMutableMap()
        Rank.entries.forEach {
            winningTable[it] = winnings[it] ?: 0
        }
        this.winnings = winningTable
    }

    fun calculateWinningRate(purchasePrice: Int): Float {
        val winningPrize = winnings.map { it.key.winningMoney * it.value }.sum()
        return winningPrize.toFloat() / purchasePrice
    }
}
