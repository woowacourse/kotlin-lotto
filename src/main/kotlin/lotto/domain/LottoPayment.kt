package lotto.domain

class LottoPayment(private val amount: Int) {
    init {
        require(amount >= MINIMUM_INPUT_PRICE) { ERROR_UNDER_THOUSAND }
    }

    fun toInt() = amount

    companion object {
        const val MINIMUM_INPUT_PRICE = 1_000
        const val ERROR_UNDER_THOUSAND = "[ERROR] 최소 로또 구입 금액은 1,000원입니다."
    }
}
