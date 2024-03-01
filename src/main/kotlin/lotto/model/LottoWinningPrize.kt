package lotto.model

class LottoWinningPrize(private val rankMap: RankMap) {
    private fun calculateWinningPrize(): Int {
        val winningPrize = rankMap.ranks.map { it.key.winningMoney * it.value }.sum()
        return winningPrize
    }

    fun calculateWinningRate(ticketCount: Int): Float {
        val winningPrize = calculateWinningPrize()
        val purchaseAmount = ticketCount * LottoMachine.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
