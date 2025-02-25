package lotto.domain

class PurchaseAmount(
    val amountOfPurchase: Int,
) {
    init {
        require(amountOfPurchase.toInt() % LOTTO_PRICE == 0) { "로또는 1000원 단위로 구입해주세요" }
    }

    fun calculateAmountOfLottos(): Int {
        return amountOfPurchase / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
