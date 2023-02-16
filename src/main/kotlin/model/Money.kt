package model

class Money(money: Int) {

    init {
        require(money < MINIMUM_PRICE) { MINIMUM_PRICE_ERROR_MESSAGE }
        require(money % MINIMUM_PRICE == DIVISIBLE) { NOT_THOUSAND_PER_MONEY_ERROR_MESSAGE }
    }

    companion object {
        private const val MINIMUM_PRICE = 1000
        private const val DIVISIBLE = 0
        private const val MINIMUM_PRICE_ERROR_MESSAGE = "[ERROR] 최소 천원이상 입력해야합니다"
        private const val NOT_THOUSAND_PER_MONEY_ERROR_MESSAGE = "[ERROR] 천원 단위로 입력해야합니다"
    }
}
