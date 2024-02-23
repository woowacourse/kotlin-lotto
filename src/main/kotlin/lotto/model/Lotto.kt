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

    private fun List<Int>.isValidSize() = size == SIZE

    private fun List<Int>.isNotDuplicate() = distinct().size == SIZE

    fun compare(
        otherLotto: Lotto,
        bonusNumber: LottoNumber,
    ): LottoPrize {
        val matchingCount = otherLotto.numbers.intersect(numbers).size
        val isMatchingBonus = contains(bonusNumber)
        return LottoPrize.getLottoPrize(matchingCount, isMatchingBonus)
    }

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    override fun toString() = numbers.toString()

    companion object {
        const val SIZE = 6
        const val PRICE = 1000
    }
}
