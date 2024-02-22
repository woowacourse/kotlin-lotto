package lotto.model

import lotto.constants.LottoPrize


class Lotto private constructor(val numbers: List<LottoNumber>) {
    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate())
    }

    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toList())

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

    private fun List<LottoNumber>.isValidSize() = size == 6

    private fun List<LottoNumber>.isNotDuplicate() = distinct().size == 6
}
