package lotto.domain

class Receipt(val purchase: PurchaseAmount, val manual: TicketCount) {
    val auto: TicketCount by lazy { TicketCount(purchase.count - manual.count) }

    init {
        checkCount()
    }

    private fun checkCount() {
        require(manual.count <= purchase.count) {
            "${MANUAL_COUNT_OVERFLOW_ERROR_MESSAGE}\n" +
                "오류값 : ${manual.count}"
        }
    }

    companion object {
        private const val MANUAL_COUNT_OVERFLOW_ERROR_MESSAGE = "구입금액보다 수동 로또 개수가 더 많습니다."
    }
}
