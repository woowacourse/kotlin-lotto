package lotto.model

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

    fun getMatchingCount(otherLotto: Lotto) = otherLotto.numbers.intersect(numbers).size

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    override fun toString() = numbers.toString()

    companion object {
        const val SIZE = 6
        const val PRICE = 1000
    }
}
