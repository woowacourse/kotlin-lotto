package lotto.model

class LottoTicketCounter(
    private val purchase: Double,
) {
    fun count(): Int = purchase.toInt() / PURCHASE_STANDARD

    init {
        require(purchase.toInt() >= PURCHASE_STANDARD) { ERROR_MINIMUM_PURCHASE_MESSAGE }
    }

    companion object {
        private const val PURCHASE_STANDARD = 1000
        private const val ERROR_MINIMUM_PURCHASE_MESSAGE = "구입 금액은 최소 1000원 이상 이어야 합니다."
    }
}
