package domain

data class Amount(val amount: Int) {

    init {
        require(amount in MINIMUM_AMOUNT..MAXIMUM_AMOUNT) { ERROR_CREATE_COUNT.format(amount) }
    }

    companion object {
        private const val MINIMUM_AMOUNT = 1000
        private const val MAXIMUM_AMOUNT = 100000
        private const val ERROR_CREATE_COUNT = "구매 할 수 있는 금액은 1000원 이상 100000원 이하입니다.\n잘못된 값: %d"
    }
}
