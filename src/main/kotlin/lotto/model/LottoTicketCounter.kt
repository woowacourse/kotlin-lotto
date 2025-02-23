package lotto.model

class LottoTicketCounter(
    private val purchase: String,
) {
    fun count(): Int = purchase.toInt() / PURCHASE_STANDARD

    init {
        require(purchase.toInt() >= PURCHASE_STANDARD) { ERROR_MINIMUM_PURCHASE_MESSAGE }
        require(purchase.toInt() % PURCHASE_STANDARD == REMAINDER) { ERROR_PURCHASE_DIVIDE_MESSAGE }
    }

    companion object {
        private const val PURCHASE_STANDARD = 1000
        private const val REMAINDER = 0
        private const val ERROR_MINIMUM_PURCHASE_MESSAGE = "구입 금액은 최소 1000원 이상 이어야 합니다."
        private const val ERROR_PURCHASE_DIVIDE_MESSAGE = "구입 금액은 1000원 단위로 나누어 떨어져야 합니다."
    }
}
