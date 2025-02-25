package lotto.model

class LottoTicketCounter(purchase: String) {
    val ticketCount: Int = purchase.toInt() / PURCHASE_STANDARD

    init {
        require(purchase.isNotBlank()) { INVALID_BLANK_MESSAGE }
        require(purchase.toIntOrNull() != null) { NUMERIC_PURCHASE_MESSAGE }
        require(purchase.toInt() >= PURCHASE_STANDARD) { MINIMUM_PURCHASE_MESSAGE }
        require(purchase.toInt() % PURCHASE_STANDARD == REMAINDER) { PURCHASE_UNIT_MESSAGE }
    }

    companion object {
        private const val PURCHASE_STANDARD = 1000
        private const val REMAINDER = 0
        const val INVALID_BLANK_MESSAGE = "구입 금액은 공백일 수 없습니다."
        const val NUMERIC_PURCHASE_MESSAGE = "구입 금액은 숫자만 입력할 수 있습니다."
        const val MINIMUM_PURCHASE_MESSAGE = "구입 금액은 최소 1000원 이상 이어야 합니다."
        const val PURCHASE_UNIT_MESSAGE = "구입 금액은 1000원 단위로 나누어 떨어져야 합니다."
    }
}
