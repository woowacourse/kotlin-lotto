package lotto.model

data class Lotto private constructor(
    val numbers: Set<LottoNumber>,
) {
    fun getRank(winningLotto: WinningLotto): Rank {
        val matchCount = getMatchCount(winningLotto.winningNumbers)
        val isMatchedBonus = containsNumber(winningLotto.bonusNumber)
        return Rank.findRank(matchCount, isMatchedBonus)
    }

    private fun getMatchCount(winningNumbers: Lotto): Int =
        numbers.count { number ->
            winningNumbers.numbers.contains(number)
        }

    private fun containsNumber(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        private const val ERROR_DUPLICATED_LOTTO_NUMBERS = "입력한 로또 번호 %s이 중복됩니다. 로또 번호는 중복될 수 없습니다."
        private const val ERROR_LOTTO_NUMBERS_SIZE = "입력한 로또 번호 개수는 %d개 입니다. 로또 번호는 6개입니다."
        const val LOTTO_NUMBERS_COUNT = 6
        const val LOTTO_PRICE = 1_000

        fun from(numbers: List<LottoNumber>): Lotto {
            require(numbers.size == LOTTO_NUMBERS_COUNT) { ERROR_LOTTO_NUMBERS_SIZE.format(numbers.size) }
            require(numbers.size == numbers.toSet().size) { ERROR_DUPLICATED_LOTTO_NUMBERS.format(numbers) }
            return Lotto(numbers.toSet())
        }

        fun from(vararg numbers: Int): Lotto {
            require(numbers.size == LOTTO_NUMBERS_COUNT) { ERROR_LOTTO_NUMBERS_SIZE.format(numbers.size) }
            require(numbers.size == numbers.toSet().size) { ERROR_DUPLICATED_LOTTO_NUMBERS.format(numbers) }
            return Lotto(numbers.map { number -> LottoNumber(number) }.toSet())
        }
    }
}
