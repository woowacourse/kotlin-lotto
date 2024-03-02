package lotto.model

class WinningTable(winnings: Map<Rank, Int>) {
    var winnings = winnings
        private set

    init {
        val rankMap: MutableMap<Rank, Int> = Rank.entries.associateWith { 0 }.toMutableMap()
        Rank.entries.forEach {
            rankMap[it] = winnings[it] ?: 0
        }
        this.winnings = rankMap
    }

    fun calculateWinningRate(ticketCount: Int): Float {
        val winningPrize = winnings.map { it.key.winningMoney * it.value }.sum()
        val purchaseAmount = ticketCount * LottoCountCalculator.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
