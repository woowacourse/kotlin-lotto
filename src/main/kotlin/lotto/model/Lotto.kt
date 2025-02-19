package lotto.model

class Lotto(
    private val numbers: List<Int>,
) {
    init {
        validateLottoNumbersCount(numbers)
        validateLottoNumbersRange(numbers)
    }

    private fun validateLottoNumbersCount(numbers: List<Int>) {
        require(numbers.size == 6) { "[ERROR] 로또는 6개의 번호만 가질 수 있습니다." }
    }

    private fun validateLottoNumbersRange(numbers: List<Int>) {
        numbers.forEach { number ->
            require(number in 1..45) { "[ERROR] 로또 번호의 범위는 1 이상 45 이하여야 합니다." }
        }
    }

    fun compareWinningNumbers(winningNumbers: List<Int>): Int {
        validateLottoNumbersRange(winningNumbers)
        validateLottoNumbersCount(winningNumbers)

        return numbers.count { existNumber -> winningNumbers.contains(existNumber) }
    }

    fun isHaveBonusNumber(bonusNumber: Int): Boolean {
        validateLottoNumbersRange(listOf(bonusNumber))

        return numbers.contains(bonusNumber)
    }
}
