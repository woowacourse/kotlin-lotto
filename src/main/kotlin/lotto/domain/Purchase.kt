package lotto.domain

class Purchase(
    val amountOfPurchase: Int,
) {
    init {
        require(amountOfPurchase % LOTTO_PRICE == 0) { "로또는 1000원 단위로 구입할 수 있습니다." }
    }

    fun calculateAmountOfLottos(): Int {
        return amountOfPurchase / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
