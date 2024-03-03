package lotto.model

import lotto.util.LottoConstants

class NumberGenerator {
    fun makeRandomNumbers(): List<Int> {
        return (LottoConstants.START_RANGE..LottoConstants.END_RANGE).map { it }.shuffled()
    }

    fun drawSixNumbers(randomNumbers: List<Int>): List<Int> {
        return randomNumbers.subList(0, LottoConstants.SIZE).sorted()
    }
}
