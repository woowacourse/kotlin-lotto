package lotto.domain

class Purchase(
    val amountOfPurchase: String,
) {
    init {
        require(amountOfPurchase.all { it.isDigit() }) { PRICE_NUMBER_ERROR_MESSAGE }
        require(amountOfPurchase.toInt() % LOTTO_PRICE == 0) { LOTTO_UNIT_ERROR_MESSAGE }
    }

    fun calculateAmountOfLottos(): Int {
        return amountOfPurchase.toInt() / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE = 1000
        private const val PRICE_NUMBER_ERROR_MESSAGE = "구입 금액은 숫자로 입력해주세요"
        private const val LOTTO_UNIT_ERROR_MESSAGE = "로또는 1000원 단위로 구입해주세요"
    }
}
