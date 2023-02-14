class Lotto(numbers: List<Int>) {
    init {
        require(
            numbers.all { number ->
                number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
            }
        ) { NUMBER_RANGE_ERROR }
        require(numbers.size == NUMBER_COUNT)
    }

    companion object {
        private const val NUMBER_COUNT = 6
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val NUMBER_RANGE_ERROR = "[ERROR] 번호는 1 이상 45 이하입니다."
    }
}
