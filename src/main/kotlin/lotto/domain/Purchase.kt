package lotto.domain

class Purchase(
    val amountOfPurchase: Int,
) {
    init {
        require(amountOfPurchase >= LOTTO_PRICE) { LOTTO_PRICE_ERROR_MESSAGE }
    }

    fun calculateAmountOfLottos(): Int {
        return amountOfPurchase / LOTTO_PRICE
    }

    fun getPrice(): Int {
        return if (amountOfPurchase % LOTTO_PRICE == 0) {
            amountOfPurchase
        } else {
            amountOfPurchase - (amountOfPurchase % LOTTO_PRICE)
        }
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val LOTTO_PRICE_ERROR_MESSAGE = "구입 금액은 1000원 이상이어야 합니다"
    }
}
