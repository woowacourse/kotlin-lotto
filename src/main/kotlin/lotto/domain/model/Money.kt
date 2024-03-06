package lotto.domain.model

data class Money(val amount: Long) {
    init {
        require(MINIMUM_AMOUNT <= amount) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
    }

    fun calculateQuantity(pricePerUnit: Int): Int = (amount / pricePerUnit).toInt()

    companion object {
        private const val MINIMUM_AMOUNT = 0
        private const val MINIMUM_VALUE_EXCEPTION_MESSAGE = "${MINIMUM_AMOUNT}원 이상이어야 합니다."
    }
}
