package lotto.model

class LottoCashier(
    val amount: Int,
) {
    init {
        validateAmountMinimumRange()
        validateAmountUnit()
    }

    private fun validateAmountMinimumRange() {
        require(amount > LOTTO_MIN_AMOUNT) {
            "[ERROR] ${LOTTO_MIN_AMOUNT}원 이상의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    private fun validateAmountUnit() {
        require(amount % LOTTO_EACH_AMOUNT == 0) {
            "[ERROR] ${LOTTO_EACH_AMOUNT}원 단위의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    fun getPurchaseQuantity(): Int = amount / LOTTO_EACH_AMOUNT

    companion object {
        private const val LOTTO_MIN_AMOUNT = 0
        private const val LOTTO_EACH_AMOUNT = 1000
    }
}
