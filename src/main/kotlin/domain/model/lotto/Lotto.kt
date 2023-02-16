package domain.model.lotto

import domain.model.LottoResult
import domain.model.WinningNumbers

class Lotto(val numbers: Set<Int>) {
    init {
        require(numbers.checkNumbersRange()) {
            NUMBER_RANGE_ERROR
        }
        require(numbers.size == NUMBER_COUNT) {
            NUMBER_COUNT_ERROR
        }
    }

    fun getLottoResult(winningNumbers: WinningNumbers): LottoResult {
        val matchCount = numbers.getMatchCount(winningNumbers)
        val hasBonusNumber = numbers.contains(winningNumbers.bonusNumber)

        return LottoResult.valueOf(matchCount, hasBonusNumber)
    }

    private fun Set<Int>.getMatchCount(winningNumbers: WinningNumbers) =
        this.count { number ->
            winningNumbers.catchNumbers.contains(number)
        }

    private fun Set<Int>.checkNumbersRange() =
        this.all { number ->
            number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
        }

    companion object {
        private const val NUMBER_COUNT = 6
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val NUMBER_RANGE_ERROR = "[ERROR] 번호는 1 이상 45 이하입니다."
        private const val NUMBER_COUNT_ERROR = "[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다."
    }
}
