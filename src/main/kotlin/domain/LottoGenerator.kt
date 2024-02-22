package domain

import domain.model.Lotto
import domain.model.LottoNumber

object LottoGenerator {
    private const val MINIMUM_LOTTO_NUMBER = 1
    private const val MAXIMUM_LOTTO_NUMBER = 45
    private const val LOTTO_SIZE = 6

    fun makeLotto(randomNumber: List<Int>): Lotto {
        val randomNumbers = randomNumber.take(LOTTO_SIZE).sorted()
        val lottoNumbers = randomNumbers.map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }

    fun makeRandomNumber() = (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).shuffled()
}
