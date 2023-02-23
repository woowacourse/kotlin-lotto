package domain

@JvmInline
value class Count(val value: Int) {
    init {
        require(value >= MINIMUM_NON_NEGATIVE_NUMBER) { COUNT_NEGATIVE_ERROR_MESSAGE }
    }

    companion object {
        const val COUNT_NEGATIVE_ERROR_MESSAGE = "[ERROR] 갯수는 음수가 될 수 없습니다."
        const val MINIMUM_NON_NEGATIVE_NUMBER = 0
    }
}
