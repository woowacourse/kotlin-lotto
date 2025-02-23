package lotto.model

class LottoTicketCounter(purchase: String) {
    private val ticketCount: Int = purchase.toInt() / PURCHASE_STANDARD

    fun count(): Int {
        return ticketCount
    }

    init {
        require(purchase.isNotBlank()) { "구입 금액은 공백일 수 없습니다." }
        require(purchase.toIntOrNull() != null) { "구입 금액은 숫자만 입력할 수 있습니다." }
        require(purchase.toInt() >= PURCHASE_STANDARD) { "구입 금액은 최소 1000원 이상 이어야 합니다." }
        require(purchase.toInt() % PURCHASE_STANDARD == REMAINDER) { "구입 금액은 1000원 단위로 나누어 떨어져야 합니다." }
    }

    companion object {
        private const val PURCHASE_STANDARD = 1000
        private const val REMAINDER = 0
    }
}
