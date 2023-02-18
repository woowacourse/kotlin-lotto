package lotto.entity

import lotto.model.Rank

class Lotto(val numbers: List<Int>) {

    init {
        require(numbers.size == LOTTO_COUNT) { ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX }
        require(numbers.all { (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).contains(it) }) { ERROR_MESSAGE_LOTTO_RANGE_1_TO_45 }
        require(numbers.intersect(numbers.toSet()).size == LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
    }

    fun determineLottoResult(winLotto: WinLotto): Rank {
        val countOfMatch = numbers.intersect(winLotto.winNumber.numbers.toSet()).size
        val matchBonus = numbers.contains(winLotto.bonus.value)

        return Rank.valueOf(countOfMatch, matchBonus)
    }

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val LOTTO_MINIMUM_RANGE = 0
        const val LOTTO_COUNT = 6
        private const val ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX = "로또 번호는 6개여야 합니다"
        private const val ERROR_MESSAGE_LOTTO_RANGE_1_TO_45 = "로또 번호는 1에서 45 사이의 숫자여야 합니다"
        private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "로또 번호는 서로 중복될 수 없습니다"
    }
}
