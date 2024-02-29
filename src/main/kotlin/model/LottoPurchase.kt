package model

class LottoPurchase(private val priceOfLotto: Money) {
    fun calculateLottoCount(purchasePrice: Money): Int = (purchasePrice / priceOfLotto).toInt()
}
