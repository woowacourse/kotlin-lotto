package utils

import kotlin.random.Random

object LottoRandomGenerator : Generator {
    private const val MIN_LOTTO_NUMBER = 1
    private const val MAX_LOTTO_NUMBER = 45
    private const val LOTTO_COUNT = 6

    override fun generate(): List<Int> {
        val lottoNumbers = mutableListOf<Int>()

        while (lottoNumbers.size < LOTTO_COUNT) {
            makeLottoNumbers(lottoNumbers)
        }

        return lottoNumbers
    }

    private fun makeLottoNumbers(lottoNumbers: MutableList<Int>) {
        val randomNumber = Random.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
        if (!lottoNumbers.contains(randomNumber)) {
            lottoNumbers.add(randomNumber)
        }
    }
}
