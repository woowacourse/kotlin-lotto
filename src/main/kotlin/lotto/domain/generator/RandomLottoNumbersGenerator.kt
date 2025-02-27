package lotto.domain.generator

import lotto.domain.model.LottoNumber

class RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): Set<LottoNumber> {
        val selectedNumbers = selectLottoNumbers().sorted()
        return selectedNumbers.map { LottoNumber.from(it) }.toSet()
    }

    private fun selectLottoNumbers(): List<Int> {
        val shuffledNumbers = (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER).shuffled()
        return shuffledNumbers.take(LOTTO_NUMBER_SIZE)
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
    }
}
