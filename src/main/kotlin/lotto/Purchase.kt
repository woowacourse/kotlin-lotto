package lotto

class Purchase(
    val amountOfPurchase: String,
) {
    init {
        require(amountOfPurchase.all { it.isDigit() }) { "구입 금액은 숫자로 입력해주세요" }
        require(amountOfPurchase.toInt() % LOTTO_PRICE == 0) { "로또는 1000원 단위로 구입해주세요" }
    }

    fun calculateAmountOfLottos(money: String): Int {
        return money.toInt() / LOTTO_PRICE
    }

    private companion object {
        const val LOTTO_PRICE = 1000
    }
}
