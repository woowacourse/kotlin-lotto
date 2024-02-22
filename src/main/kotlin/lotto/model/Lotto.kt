package lotto.model

import lotto.constants.LottoPrize
import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER

class Lotto(numbers: List<Int>) {
    private val numbers: List<LottoNumber>

    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate()) {
            INVALID_LOTTO_NUMBER
        }
        this.numbers = numbers.sorted().map { LottoNumber(it) }
    }

    private fun List<Int>.isValidSize() = size == LOTTO_SIZE

    private fun List<Int>.isNotDuplicate() = distinct().size == LOTTO_SIZE

    fun compare(otherLotto: Lotto, bonusNumber: LottoNumber) =
        LottoPrize.getLottoPrize(otherLotto.numbers.intersect(numbers).size, contains(bonusNumber))

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    override fun toString(): String {
        return numbers.toString()
    }

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
