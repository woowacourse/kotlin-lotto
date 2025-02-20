package lotto.model

class Lotto(
    val numbers: List<Int>,
) {
    init {
        validateLottoNumbersCount(numbers)
        validateLottoNumbersRange(numbers)
    }

    private fun validateLottoNumbersCount(numbers: List<Int>) {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또는 ${LOTTO_NUMBER_SIZE}개의 번호만 가질 수 있습니다."
        }
    }

    private fun validateLottoNumbersRange(numbers: List<Int>) {
        numbers.forEach { number ->
            require(number in LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE) {
                "[ERROR] 로또 번호의 범위는 $LOTTO_NUMBER_MIN_RANGE 이상 $LOTTO_NUMBER_MAX_RANGE 이하여야 합니다."
            }
        }
    }

    fun getRank(
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Rank {
        val countOfMatch = countMatchWinningNumbers(winningNumbers)
        val matchBonus = isHaveBonusNumber(bonusNumber)

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }

    private fun countMatchWinningNumbers(winningNumbers: List<Int>): Int {
        validateLottoNumbersRange(winningNumbers)
        validateLottoNumbersCount(winningNumbers)

        return numbers.count { existNumber -> winningNumbers.contains(existNumber) }
    }

    private fun isHaveBonusNumber(bonusNumber: Int): Boolean {
        validateLottoNumbersRange(listOf(bonusNumber))

        return numbers.contains(bonusNumber)
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val LOTTO_NUMBER_MIN_RANGE = 1
        const val LOTTO_NUMBER_MAX_RANGE = 45
    }
}
