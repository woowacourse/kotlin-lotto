package model

class Count(money: Money, manualLottoCount: Int) {
    val autoLottoCount = (money.value / ONE_TICKET_PRICE) - manualLottoCount

    init {
        require(manualLottoCount >= MINIMUM_COUNT) { ERROR_MINUS_VALUE }
        require(autoLottoCount > MINIMUM_COUNT) { ERROR_PURCHASE_LIMIT }
    }

    companion object {
        private const val MINIMUM_COUNT = 0
        private const val ONE_TICKET_PRICE = 1000
        const val ERROR_MINUS_VALUE = "[ERROR] 음수값을 입력할 수 없습니다"
        const val ERROR_PURCHASE_LIMIT = "[ERROR] 구입금액보다 더 많이 구입할 수 없습니다"
    }
}
