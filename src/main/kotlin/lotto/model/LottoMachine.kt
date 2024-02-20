package lotto.model

class LottoMachine(val price: String) {
    init {
        require(price.toIntOrNull()?.let { it >= MIN_PRICE } == true) { PRICE_ERROR_MESSAGE }
    }

    fun getLottoTicket(): Int = price.toInt() / MIN_PRICE

    companion object {
        const val MIN_PRICE = 1_000

        const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
    }
}
