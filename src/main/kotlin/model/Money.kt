package model

class Money(value: Int) {

    init {
        require(value >= MINIMUM_PRICE) { ERROR_UNDER_MINIMUM_PRICE }
        require(value % MINIMUM_PRICE == DIVISIBLE) { ERROR_CHECK_PRICE_UNIT }
    }

    companion object {
        const val ERROR_UNDER_MINIMUM_PRICE = "[ERROR] 최소 천원이상 입력해야합니다"
        const val ERROR_CHECK_PRICE_UNIT = "[ERROR] 천원 단위로 입력해야합니다"
        private const val MINIMUM_PRICE = 1000
        private const val DIVISIBLE = 0
    }
}
