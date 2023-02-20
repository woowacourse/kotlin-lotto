package domain.money

class Money(val value: Int) {
    init {
        require(value >= 0) { ERROR_MESSAGE_UNDER_ZERO }
    }

    fun divideByThousand(): Int {
        return value.div(1000)
    }

    companion object {
        private const val ERROR_MESSAGE_UNDER_ZERO = "[ERROR] 입력값은 0이상이어야 합니다."
    }
}