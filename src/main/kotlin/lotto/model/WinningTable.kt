package lotto.model

class WinningTable(var winnings: Map<Rank, Int>) {
    init {
        val rankMap: MutableMap<Rank, Int> = Rank.entries.associateWith { 0 }.toMutableMap()
        Rank.entries.forEach {
            rankMap[it] = winnings[it] ?: 0
        }
        winnings = rankMap
    }

    fun calculateWinningRate(ticketCount: Int): Float {
        val winningPrize = winnings.map { it.key.winningMoney * it.value }.sum()
        val purchaseAmount = ticketCount * LottoMachine.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
