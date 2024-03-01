package lottogame.model

@JvmInline
value class Count(val amount: Int) {
    init {
        require(amount > 0) { EXCEPTION_COUNT_AMOUNT }
    }

    companion object {
        private const val EXCEPTION_COUNT_AMOUNT = "count 는 0보다 커야함"

        fun createOrNull(count: Int): Count? {
            if (count < 0) return null
            return Count(count)
        }
    }
}
