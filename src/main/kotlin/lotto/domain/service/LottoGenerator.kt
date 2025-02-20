package lotto.domain.service

import lotto.constants.LottoConstants
import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber

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
