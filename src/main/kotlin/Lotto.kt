class Lotto(val numbers: Set<Int>) {
    init {
        require(
            numbers.all { number ->
                number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
            }
        ) { NUMBER_RANGE_ERROR }
        require(numbers.size == NUMBER_COUNT)
    }

    fun getResult(winningNumbers: WinningNumbers): LottoResult {
        val matchCount = numbers.count { winningNumbers.catchNumbers.contains(it) }

        return when (matchCount) {
            6 -> LottoResult.FIRST
            5 -> numbers.decideSecondOrThird(winningNumbers.bonusNumber)
            4 -> LottoResult.FORTH
            3 -> LottoResult.FIFTH
            else -> LottoResult.MISS
        }
    }

    private fun Set<Int>.decideSecondOrThird(bonusNumber: Int): LottoResult =
        if (this.contains(bonusNumber)) {
            LottoResult.SECOND
        } else {
            LottoResult.THIRD
        }

    companion object {
        private const val NUMBER_COUNT = 6
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val NUMBER_RANGE_ERROR = "[ERROR] 번호는 1 이상 45 이하입니다."
    }
}
