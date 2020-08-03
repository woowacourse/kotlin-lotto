package lotto.domain

private const val LOTTO_TICKET_PRICE = 1000

data class PurchaseAmount(val purchaseAmount: Int) {
    fun isUnderCountOfLottoTicket(size: Int): Boolean {
        return size < countOfLottoTicket()
    }

    private fun countOfLottoTicket(): Int {
        return purchaseAmount / LOTTO_TICKET_PRICE
    }

    fun calculateProfit(sum: Double): Double {
        return sum / purchaseAmount
    }
}
