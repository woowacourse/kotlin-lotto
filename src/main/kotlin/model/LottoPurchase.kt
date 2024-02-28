package model

class LottoPurchase(private val priceOfLotto: Int) {

    fun calculateLottoCount(purchasePrice: Int): Int {
        return purchasePrice / priceOfLotto
    }
}
