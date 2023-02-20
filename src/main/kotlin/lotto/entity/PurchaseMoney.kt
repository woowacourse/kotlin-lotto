package lotto.entity

class PurchaseMoney(value: Int) : Money(value) {
    init {
        require(value >= LOTTO_PRICE) { String.format(ERROR_MESSAGE_PURCHASE_LESS_THAN_LOTTO, value) }
    }

    fun calculateLottoCount(): Int = value / LOTTO_PRICE

    companion object {
        const val ERROR_MESSAGE_PURCHASE_LESS_THAN_LOTTO = "구입 금액이 로또 가격보다 적습니다. 입력된 구입 금액은 %d원 입니다."
    }
}
