package lotto.model

import lotto.constants.LottoPrize


class Lotto(numbers: List<Int>) {
    private val numbers: List<LottoNumber>

    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate()) {
            "올바른 로또 번호를 입력해 주세요."
        }
        this.numbers = numbers.sorted().map { LottoNumber(it) }
    }

    private fun List<Int>.isValidSize() = size == 6

    private fun List<Int>.isNotDuplicate() = distinct().size == 6


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

}
