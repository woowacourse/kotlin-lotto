package lotto.entity

import lotto.model.Rank

class Lotto(val numbers: Set<LottoNumber>) {

    init {
        require(numbers.size == LOTTO_COUNT) { ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX }
        require(numbers.intersect(numbers).size == LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
    }

    fun determineRank(countOfMatch: Int, matchBonus: Boolean): Rank {
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    fun determineCountOfMatch(winLotto: Lotto): Int {
        return numbers.intersect(winLotto.numbers).size
    }

    fun determineMatchBonus(bonus: LottoNumber): Boolean {
        return numbers.contains(bonus)
    }

    companion object {
        const val LOTTO_MINIMUM_RANGE = 0
        const val LOTTO_COUNT = 6
        private const val ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX = "로또 번호는 6개여야 합니다"
        private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "로또 번호는 서로 중복될 수 없습니다"
    }
}
