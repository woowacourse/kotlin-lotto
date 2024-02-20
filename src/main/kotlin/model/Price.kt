package model

@JvmInline
value class Price(val value: Int) {
    init {
        require(value >= MIN_PRICE) { EXCEPTION_PRICE_RANGE }
    }

    companion object {
        private const val MIN_PRICE = 0
        private const val EXCEPTION_PRICE_RANGE = "Price는 $MIN_PRICE 이상 이여야한다."
    }
}
