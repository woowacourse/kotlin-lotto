package lotto.model

class LottoQuantity(
    val amount: Int,
    val passiveLottoQuantity: Int,
) {
    init {
        validateAmountMinimumRange()
        validatePassiveQuantity()
    }

    private fun getTotalLottoQuantity(): Int = amount / LOTTO_EACH_AMOUNT

    fun getActiveLottoQuantity(): Int = getTotalLottoQuantity() - passiveLottoQuantity

    private fun validateAmountMinimumRange() {
        require(amount >= LOTTO_EACH_AMOUNT) {
            "[ERROR] ${LOTTO_EACH_AMOUNT}원 이상의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    private fun validatePassiveQuantity() {
        require(getTotalLottoQuantity() >= passiveLottoQuantity) {
            "[ERROR] 수동 로또의 수는 전체 구매 로또의 수보다 클 수 없습니다."
        }
    }

    companion object {
        private const val LOTTO_EACH_AMOUNT = 1000
    }
}
