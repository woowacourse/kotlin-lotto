class WinningNumbers(
    val catchNumbers: Set<Int>,
    val bonusNumber: Int
) {
    init {
        require(
            catchNumbers.all { number ->
                number in MINIMUM_NUMBER..MAXIMUM_NUMBER
            }
        )
    }

    companion object{
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
    }
}
