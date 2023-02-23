package domain

@JvmInline
value class Money(val money: Int) {
    init {
        require(money % 1000 == 0 && money != 0) { ERROR_MINIMUM_MONEY }
    }

    fun divide(divisor: Int): Int {
        return money / divisor
    }

    companion object {
        const val ERROR_MINIMUM_MONEY = "금액을 1000원 단위로 입력해주세요."
    }
}
