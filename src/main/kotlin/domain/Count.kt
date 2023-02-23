package domain

@JvmInline
value class Count(val value: Int) {

    init {
        require(value in MINIMUM_COUNT..MAXIMUM_COUNT) { ERROR_COUNT_RANGE }
    }

    companion object {
        private const val MINIMUM_COUNT = 0
        private const val MAXIMUM_COUNT = 100
        private const val ERROR_COUNT_RANGE = "이 프로그램 내에서 개수는 $MINIMUM_COUNT 이상 $MAXIMUM_COUNT 이하여야 합니다."
    }
}
