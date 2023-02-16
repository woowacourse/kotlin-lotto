package lotto.domain

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == 6) { LOTTO_SIZE_ERROR }
        require(numbers.map { lottoNumber -> lottoNumber.number }.distinct().size == 6) { LOTTO_DUPLICATE_ERROR }
    }

    fun countMatchingNumbers(winningLotto: Lotto): Int {
        val numbers = numbers.map { lottoNumber -> lottoNumber.number }
        val winningNumbers = winningLotto.numbers.map { lottoNumber -> lottoNumber.number }
        return numbers.count { lottoNumber -> winningNumbers.contains(lottoNumber) }
    }

    fun checkMatchingBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.any { lottoNumber -> lottoNumber.number == bonusNumber.number }
    }

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 번호는 여섯 개여야 합니다."
        private const val LOTTO_DUPLICATE_ERROR = "로또 번호는 중복되면 안됩니다."
    }
}
