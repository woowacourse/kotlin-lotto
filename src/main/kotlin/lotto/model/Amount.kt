package lotto.model

@JvmInline
value class Amount(
    val value: Int,
) {
    init {
        validateAmountMinimumRange()
        validateAmountUnit()
    }

    fun isAffordable(manualQuantity: Int): Boolean = value - manualQuantity * LOTTO_EACH_AMOUNT >= 0

    fun getAutoLottoQuantity(manualQuantity: Int): Int = value / LOTTO_EACH_AMOUNT - manualQuantity

    private fun validateAmountMinimumRange() {
        require(value > LOTTO_MIN_AMOUNT) {
            "[ERROR] ${LOTTO_MIN_AMOUNT}원 이상의 금액으로 입력해 주세요. 입력값: $value"
        }
    }

    private fun validateAmountUnit() {
        require(value % LOTTO_EACH_AMOUNT == 0) {
            "[ERROR] ${LOTTO_EACH_AMOUNT}원 단위의 금액으로 입력해 주세요. 입력값: $value"
        }
    }

    companion object {
        private const val LOTTO_MIN_AMOUNT = 0
        private const val LOTTO_EACH_AMOUNT = 1000
    }
}
