package lotto.domain

class Price(
    val price: Int,
) {
    init {
        validatePurchaseAmount()
    }

    private fun validatePurchaseAmount() {
        if (price % LOTTO_PRICE != 0) {
            val change = price % LOTTO_PRICE
            println("구입 금액이 1000원 단위가 아닙니다. 거스름돈 ${change}원을 반환합니다.")
        }
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
