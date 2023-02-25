package domain

@JvmInline
value class Money(val money: Int) {
    init {
        require(money % EACH_LOTTO_PRICE == 0 && money != 0) { ERROR_MINIMUM_MONEY }
    }

    fun makeCount(): Int {
        return money / EACH_LOTTO_PRICE
    }

    companion object {
        const val ERROR_MINIMUM_MONEY = "금액을 1000원 단위로 입력해주세요."
        private const val EACH_LOTTO_PRICE = 1000
    }
}
