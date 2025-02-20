package lotto.domain.service

import lotto.constants.LottoConstants
import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber

class LottoGenerator {
    fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoConstants.MINIMUM_LOTTO_NUMBER..LottoConstants.MAXIMUM_LOTTO_NUMBER)
                .shuffled()
                .take(LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
                .sorted()
                .map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }
}
