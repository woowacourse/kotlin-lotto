package lotto.model

import lotto.constants.LottoPrize


class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate() && numbers.isInRange())
    }

    fun compare(otherLotto: Lotto, bonusNumber: Int) =
        when (otherLotto.numbers.intersect(numbers).size) {
            6 -> LottoPrize.FIRST
            5 -> compareBonusNumber(bonusNumber)
            4 -> LottoPrize.FOURTH
            3 -> LottoPrize.FIFTH
            else -> LottoPrize.NOTHING
        }

    private fun compareBonusNumber(bonusNumber: Int): LottoPrize {
        if (numbers.contains(bonusNumber)) return LottoPrize.SECOND
        return LottoPrize.THIRD
    }

    private fun List<Int>.isValidSize() = size == 6

    private fun List<Int>.isNotDuplicate() = distinct().size == 6

    private fun List<Int>.isInRange() = all { number -> number in 1..45 }
}
