package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber

class RandomLottoGenerator {
    fun generateLotto(): Lotto {
        val lottoNumbers = getShuffledNumbersAsSizeOfOneLotto().map { LottoNumber(it) }.toSet()
        return Lotto(lottoNumbers)
    }

    private fun getShuffledNumbersAsSizeOfOneLotto(): List<Int> =
        (LottoNumber.LOTTO_RANGE)
            .shuffled()
            .take(Lotto.NUMBER_OF_LOTTO_NUMBERS)
}
