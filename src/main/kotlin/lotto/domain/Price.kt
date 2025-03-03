package lotto.domain

class Price(
    val price: Int,
) {
    init {
        validatePurchaseAmount()
    }

    private fun validatePurchaseAmount() {
        require(price % LOTTO_PRICE == 0) { "로또는 ${LOTTO_PRICE}원 단위로 구입해주세요" }
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
