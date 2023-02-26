package lotto.domain

class Receipt(val purchase: PurchaseAmount, val manual: TicketCount) {
    val auto = TicketCount(purchase.count - manual.count)

    init {
        checkCount()
    }

    private fun checkCount() {
        require(manual.count >= 0) {
            "${MANUAL_QUANTITY_ERROR_MESSAGE}\n" +
                "오류값 : ${manual.count}"
        }
        require(manual.count <= purchase.count) {
            "${MANUAL_QUANTITY_OVERFLOW_ERROR_MESSAGE}\n" +
                "오류값 : ${manual.count}"
        }
    }

    companion object {
        private const val MANUAL_QUANTITY_ERROR_MESSAGE = "수동 로또 개수는 음수일 수 없습니다."
        private const val MANUAL_QUANTITY_OVERFLOW_ERROR_MESSAGE = "구입금액보다 수동 로또 개수가 더 많습니다."
    }
}
