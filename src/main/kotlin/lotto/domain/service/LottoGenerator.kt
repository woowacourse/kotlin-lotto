package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber

class LottoGenerator {
    fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoNumber.LOTTO_RANGE)
                .shuffled()
                .take(Lotto.NUMBER_OF_LOTTO_NUMBERS)
                .map { LottoNumber(it) }
                .toSet()
        return Lotto(lottoNumbers)
    }
}
