package domain

import constants.LottoConstants
import domain.value.LottoNumber

class LottoGenerator {
    fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoConstants.LOTTO_RANGE)
                .shuffled()
                .take(LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
                .sorted()
                .map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }
}
