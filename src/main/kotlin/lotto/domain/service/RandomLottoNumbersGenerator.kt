package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber

class RandomLottoNumbersGenerator {
    fun generateLottoNumbers(): Set<LottoNumber> = getShuffledNumbersAsSizeOfOneLotto().map { LottoNumber(it) }.toSet()

    private fun getShuffledNumbersAsSizeOfOneLotto(): List<Int> =
        (LottoNumber.LOTTO_RANGE)
            .shuffled()
            .take(Lotto.NUMBER_OF_LOTTO_NUMBERS)
}
