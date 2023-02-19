package lotto.entity

import lotto.model.Rank

class Lotto(val numbers: List<LottoNumber>) {

    init {
        require(numbers.size == LOTTO_COUNT) { ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX }
        require(numbers.intersect(numbers.toSet()).size == LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
    }

    fun determineLottoResult(winLotto: WinLotto): Rank {
        val countOfMatch = numbers.intersect(winLotto.winNumber.numbers.toSet()).size
        val matchBonus = numbers.contains(winLotto.bonus)

        return Rank.valueOf(countOfMatch, matchBonus)
    }

    companion object {
        const val LOTTO_MINIMUM_RANGE = 0
        const val LOTTO_COUNT = 6
        private const val ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX = "로또 번호는 6개여야 합니다"
        private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "로또 번호는 서로 중복될 수 없습니다"
    }
}
