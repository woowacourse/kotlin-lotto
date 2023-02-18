package domain

class Money(val price: Int) {
    init {
        require(price >= 0) { INPUT_MONEY_NEGATIVE_ERROR_MESSAGE }
        require(price % 1000 == 0) { INPUT_MONEY_NOT_DIVIDE_ERROR_MESSAGE }
    }

    fun getLottoCount(): Int {
        return price / LOTTO_PRICE
    }

    companion object {
        const val INPUT_MONEY_NEGATIVE_ERROR_MESSAGE = "금액은 양수여야 합니다."
        const val INPUT_MONEY_NOT_DIVIDE_ERROR_MESSAGE = "금액은 1000원으로 나누어떨어져야 합니다."
        const val LOTTO_PRICE = 1000
    }
}
