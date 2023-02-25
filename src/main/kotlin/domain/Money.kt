package domain

class Money(val price: Int) {

    init {
        require(price >= MIN_PRICE) { MONEY_ERROR_MESSAGE }
        require(price % LOTTO_PRICE == MIN_PRICE) { MONEY_ERROR_MESSAGE }
    }

    fun lottoCount(): Int {
        return price / LOTTO_PRICE
    }

    companion object {
        private const val MIN_PRICE = 0
        private const val LOTTO_PRICE = 1000

        const val MONEY_ERROR_MESSAGE = "금액은 1000원으로 나누어떨어지는 양수여야 합니다."
    }
}
