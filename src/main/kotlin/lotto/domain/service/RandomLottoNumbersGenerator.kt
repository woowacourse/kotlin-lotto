package lotto.domain.service

import lotto.domain.model.LottoNumber

class RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): Set<LottoNumber> {
        val selectedNumbers = selectLottoNumbers().sorted()
        return selectedNumbers.map { LottoNumber.from(it) }.toSet()
    }

    private fun selectLottoNumbers(): List<Int> {
        val shuffledNumbers = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).shuffled()
        return shuffledNumbers.take(LOTTO_NUMBER_SIZE)
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_SIZE = 6
    }
}
