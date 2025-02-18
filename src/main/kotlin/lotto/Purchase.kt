package lotto

class Purchase(
    val amountOfPurchase: Int,
) {
    init {
        require(amountOfPurchase % LOTTO_PRICE == 0) { "로또는 1000원 단위로 구입해주세요" }
    }

    fun getAmountOfLottos(money: Int): Int {
        return money / LOTTO_PRICE
    }

    private companion object {
        const val LOTTO_PRICE = 1000
    }
}
