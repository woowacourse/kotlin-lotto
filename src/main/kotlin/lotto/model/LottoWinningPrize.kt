package lotto.model

class LottoWinningPrize(private val rankMap: Map<Rank, Int>) {
    private fun calculateWinningPrize(): Int {
        val winningPrize = rankMap.map { it.key.winningMoney * it.value }.sum()
        return winningPrize
    }

    fun calculateWinningRate(ticketCount: Int): Float {
        val winningPrize = calculateWinningPrize()
        val purchaseAmount = ticketCount * AutoLottoMachine.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
