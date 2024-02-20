package model

import util.Constant

class Lotto {
    val lottoNumbers = generateLottoNumbers()

    fun generateLottoNumbers(): List<Int> {
        val randomNumbers = makeRandomNumbers()

        return drawSixNumbers(randomNumbers)
    }

    private fun makeRandomNumbers(): List<Int> {
        return (Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE).map { it }.shuffled()
    }

    private fun drawSixNumbers(randomNumbers: List<Int>): List<Int> {
        return randomNumbers.subList(0, Constant.LOTTO_SIZE).sorted()
    }

    override fun toString(): String {
        return lottoNumbers.sorted().joinToString(", ", "[", "]")
    }
}
