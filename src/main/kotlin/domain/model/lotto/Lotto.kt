package domain.model.lotto

import domain.model.LottoResult
import domain.model.WinningNumbers

class Lotto private constructor(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == NUMBER_COUNT) {
            NUMBER_COUNT_ERROR
        }
    }

    fun getLottoResult(winningNumbers: WinningNumbers): LottoResult {
        val matchCount = winningNumbers.getMatchCount(numbers)
        val hasBonusNumber = winningNumbers.checkMatchBonus(numbers)

        return LottoResult.valueOf(matchCount, hasBonusNumber)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Lotto) {
            return this.numbers == other.numbers
        }
        return false
    }

    override fun hashCode(): Int {
        return numbers.hashCode()
    }

    companion object {
        private const val NUMBER_COUNT = 6
        private const val NUMBER_COUNT_ERROR = "[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다."

        fun create(numbers: List<Int>): Lotto =
            Lotto(
                numbers.map { number ->
                    LottoNumber.from(number)
                }.toSet()
            )
    }
}
