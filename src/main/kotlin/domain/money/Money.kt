package domain.money

class Money(val value: Int) {
    init {
        require(value >= 0) { ERROR_MESSAGE_UNDER_ZERO }
    }

    fun divideByThousand(): Int {
        return value.div(1000)
    }

    fun getChangeByPurchasedLottos(lottoCount: Int): Money {
        check(value >= lottoCount * LOTTO_PRICE_FOR_WON) { ERROR_MESSAGE_LACK_OF_MONEY }
        return Money(value - lottoCount * LOTTO_PRICE_FOR_WON)
    }

    companion object {
        private const val ERROR_MESSAGE_UNDER_ZERO = "[ERROR] 입력값은 0이상이어야 합니다."
        private const val ERROR_MESSAGE_LACK_OF_MONEY = "[ERROR] 금액이 사려는 로또 금액보다 작습니다."
        private const val LOTTO_PRICE_FOR_WON = 1000
    }
}
