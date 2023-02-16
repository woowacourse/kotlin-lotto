package lotto.domain

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == 6) { LOTTO_SIZE_ERROR }
        require(numbers.map { it.number }.distinct().size == 6) { LOTTO_DUPLICATE_ERROR }
    }

    fun countMatchingNumbers(winningLotto: Lotto): Int {
        val numbers = numbers.map { it.number }
        val winningNumbers = winningLotto.numbers.map { it.number }
        return numbers.count { winningNumbers.contains(it) }
    }

    fun checkMatchingBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.any { it.number == bonusNumber.number }
    }

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 번호는 여섯 개여야 합니다."
        private const val LOTTO_RANGE_ERROR = "로또 번호는 1과 45 사이여야 합니다."
        private const val LOTTO_DUPLICATE_ERROR = "로또 번호는 중복되면 안됩니다."
    }
}