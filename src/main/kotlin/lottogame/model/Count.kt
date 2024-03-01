package lottogame.model

@JvmInline
value class Count(val amount: Int) {
    init {
        require(amount >= 0) { EXCEPTION_COUNT_AMOUNT }
    }

    companion object {
        private const val MIN_COUNT = 0
        private const val EXCEPTION_COUNT_AMOUNT = "count 는 0보다 커야함"

        fun createOrNull(count: Int): Count? {
            if (count < MIN_COUNT) return null
            return Count(count)
        }
    }
}
