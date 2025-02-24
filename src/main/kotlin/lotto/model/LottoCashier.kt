package lotto.model

class LottoCashier(
    private val amount: Int,
    private val manualQuantity: Int,
) {
    init {
        validateAmountMinimumRange()
        validateAmountUnit()
        validateManualLottoQuantity()
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

    private fun validateManualLottoQuantity() {
        require(amount - manualQuantity * LOTTO_EACH_AMOUNT >= 0) {
            "[ERROR] 낸 금액보다 많은 수동 로또를 살 수 없습니다. 금액: $amount, 수동 로또: ${manualQuantity}개"
        }
    }

    fun getPurchaseAutoQuantity(): Int = amount / LOTTO_EACH_AMOUNT - manualQuantity

    companion object {
        private const val LOTTO_MIN_AMOUNT = 0
        private const val LOTTO_EACH_AMOUNT = 1000
    }
}
