package lotto.domain.model

data class Money(val amount: Long) {
    init {
        require(MINIMUM_AMOUNT < amount) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val MINIMUM_AMOUNT = 0
        private const val MINIMUM_VALUE_EXCEPTION_MESSAGE = "${MINIMUM_AMOUNT}보다 커야합니다."
    }
}
