class PaymentMoney(val value: Int) {

    init {
        require(value > ONE_LOTTO_PRICE) { PAYMENT_MONEY_MINIMUM_ERROR_MESSAGE }
    }

    fun getTotalLottoCount(): Int {
        return this.value / ONE_LOTTO_PRICE
    }

    companion object {
        const val PAYMENT_MONEY_MINIMUM_ERROR_MESSAGE = "[ERROR] 구입 금액이 1000을 넘지 않습니다."
        const val ONE_LOTTO_PRICE = 1000
    }
}
