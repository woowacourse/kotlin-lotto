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
        when (otherLotto.numbers.intersect(numbers).size) {
            6 -> LottoPrize.FIRST
            5 -> compareBonusNumber(bonusNumber)
            4 -> LottoPrize.FOURTH
            3 -> LottoPrize.FIFTH
            else -> LottoPrize.NOTHING
        }

    private fun compareBonusNumber(bonusNumber: LottoNumber): LottoPrize {
        if (numbers.contains(bonusNumber)) return LottoPrize.SECOND
        return LottoPrize.THIRD
    }

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    override fun toString(): String {
        return numbers.toString()
    }

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
