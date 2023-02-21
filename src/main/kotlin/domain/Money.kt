package domain

@JvmInline
value class Money(val value: Int) {
    val count: Int
        get() = value / LottoStore.LOTTO_PRICE

    init {
        require(value in 0..MAXIMUM_AMOUNT) { ERROR_CREATE_COUNT.format(value) }
    }

    fun calculateCharge(countLotto: Int): Money = Money(value - LottoStore.LOTTO_PRICE * countLotto)

    companion object {
        private const val MAXIMUM_AMOUNT = 100000
        private const val ERROR_CREATE_COUNT = "돈은 0원 이상 100000원 이하로 보유할 수 있습니다.\n잘못된 값: %d"
    }
}
