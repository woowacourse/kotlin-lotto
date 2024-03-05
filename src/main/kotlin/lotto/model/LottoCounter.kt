package lotto.model

class LottoCounter(
    private val budget: Money,
) {
    fun getAutoCountOrNull(manualCount: Int): Int? {
        val manualPurchase = PRICE_OF_LOTTO * manualCount
        if (budget >= manualPurchase) {
            return ((budget - manualPurchase) / PRICE_OF_LOTTO)?.toInt()
        }
        return null
    }

    companion object {
        private val PRICE_OF_LOTTO = Money(1000)
    }
}
