package model

class LottoPurchase(private val priceOfLotto: Money) {
    fun calculateLottoCount(purchasePrice: Money): Int {
        val count = (purchasePrice / priceOfLotto) ?: 0f
        return count.toInt()
    }
}
