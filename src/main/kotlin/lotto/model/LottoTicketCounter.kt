package lotto.model

class LottoTicketCounter(
    private val purchase: Double,
    private val manual: Int,
) {
    fun count(): Int = purchase.toInt() / PURCHASE_STANDARD

    fun autoCount(): Int = count() - manual

    init {
        require(purchase.toInt() >= PURCHASE_STANDARD) { ERROR_MINIMUM_PURCHASE_MESSAGE }
        require(count() >= manual) { ERROR_MANUAL_LOTTO_PURCHASE_MESSAGE }
    }

    companion object {
        private const val PURCHASE_STANDARD = 1000
        private const val ERROR_MINIMUM_PURCHASE_MESSAGE = "구입 금액은 최소 1000원 이상 이어야 합니다."
        private const val ERROR_MANUAL_LOTTO_PURCHASE_MESSAGE = "전체 발행 가능한 로또 개수보다 수동 로또 발행 개수가 더 많습니다."
    }
}
