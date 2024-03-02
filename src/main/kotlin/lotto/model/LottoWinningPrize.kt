package lotto.model

class LottoWinningPrize(private val winningTable: WinningTable) {
    private fun calculateWinningPrize(): Int {
        val winningPrize = winningTable.winnings.map { it.key.winningMoney * it.value }.sum()
        return winningPrize
    }

    fun calculateWinningRate(ticketCount: Int): Float {
        val winningPrize = calculateWinningPrize()
        val purchaseAmount = ticketCount * LottoMachine.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
