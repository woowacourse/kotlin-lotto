package model

class Lotto {
    val lottoNumbers = generateLottoNumbers()

    fun generateLottoNumbers(): List<Int> {
        val randomNumbers = makeRandomNumbers()

        return drawSixNumbers(randomNumbers)
    }

    private fun makeRandomNumbers(): List<Int> {
        return (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).map { it }.shuffled()
    }

    private fun drawSixNumbers(randomNumbers: List<Int>): List<Int> {
        return randomNumbers.subList(0, 6).sorted()
    }

    override fun toString(): String {
        return lottoNumbers.sorted().joinToString(", ", "[", "]")
    }

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
    }
}
