package lotto.domain

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
        require(numbers.distinct().size == LOTTO_SIZE) { LOTTO_DUPLICATE_ERROR }
    }

    fun countMatchingNumbers(winningLotto: Lotto): Int {
        return numbers.count { lottoNumber -> winningLotto.numbers.contains(lottoNumber) }
    }

    fun checkMatchingBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.any { lottoNumber -> lottoNumber == bonusNumber }
    }

    companion object {
        const val LOTTO_SIZE = 6
        private const val LOTTO_SIZE_ERROR = "로또 번호는 여섯 개여야 합니다."
        private const val LOTTO_DUPLICATE_ERROR = "로또 번호는 중복되면 안됩니다."
    }
}
