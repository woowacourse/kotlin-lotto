package model

import util.Constant

class NumberGenerator {
    fun makeRandomNumbers(): List<Int> {
        return (Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE).map { it }.shuffled()
    }

    fun drawSixNumbers(randomNumbers: List<Int>): List<Int> {
        return randomNumbers.subList(0, Constant.LOTTO_SIZE).sorted()
    }
}
