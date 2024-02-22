package domain.model

data class Money(val amount: Long) {
    init {
        require(amount >= ZERO) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
    }

    companion object {
        const val ZERO = 0
        const val MINIMUM_VALUE_EXCEPTION_MESSAGE = " ${ZERO}원 이상이어야 합니다."
    }
}
