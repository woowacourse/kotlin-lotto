package model

class LottoPurchase(
    purchasePrice: Int,
) {
    init {
        require(purchasePrice >= 0)
    }

    val lottoCount = purchasePrice / PRICE_OF_LOTTO_TICKET

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
