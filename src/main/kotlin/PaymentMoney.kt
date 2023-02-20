class PaymentMoney(val value: Int) {
    init {
        require(value > MINIMUM_MONEY) { PAYMENT_MONEY_MINIMUM_ERROR_MESSAGE }
    }

    companion object {
        const val PAYMENT_MONEY_MINIMUM_ERROR_MESSAGE = "[ERROR] 구입 금액이 1000을 넘지 않습니다."
        const val MINIMUM_MONEY = 1000
    }
}
