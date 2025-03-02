package lotto.model

class LottoQuantity(
    private val amount: Int,
    private val passiveLottoQuantity: Int,
) {
    init {
        validateAmountMinimumRange()
    }

    fun getActiveLottoQuantity(): Int = amount / LOTTO_EACH_AMOUNT - passiveLottoQuantity

    private fun validateAmountMinimumRange() {
        require(amount >= LOTTO_EACH_AMOUNT) {
            "[ERROR] ${LOTTO_EACH_AMOUNT}원 이상의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    companion object {
        private const val LOTTO_EACH_AMOUNT = 1000
    }
}
