package lotto.entity

class PurchaseMoney(val value: Int) {
    init {
        require(value >= LottoPrice.DEFAULT_LOTTO_PRICE) { ERROR_MESSAGE_GREATER_THAN_LOTTO_PRICE }
    }

    fun getPurchaseLottoCount(): Int = value / LottoPrice.DEFAULT_LOTTO_PRICE

    companion object {
        private const val ERROR_MESSAGE_GREATER_THAN_LOTTO_PRICE = "구매 금액은 로또 1장의 가격보다 큰 숫자여야 합니다"
    }
}
