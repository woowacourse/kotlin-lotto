package lotto.model

import lotto.constants.LottoPrize

class Lotto(numbers: Set<Int>) {
    private val numbers: Set<LottoNumber>

    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate()) {
            INVALID_LOTTO_NUMBER
        }
        this.numbers = numbers.sorted().map { LottoNumber(it) }.toSet()
    }

    private fun Set<Int>.isValidSize() = size == LOTTO_SIZE

    private fun Set<Int>.isNotDuplicate() = distinct().size == LOTTO_SIZE

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
        private const val LOTTO_SIZE = 6
        private const val INVALID_LOTTO_NUMBER = "올바른 로또 번호를 입력해 주세요."
    }
}
