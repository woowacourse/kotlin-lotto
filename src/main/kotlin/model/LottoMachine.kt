package model

import constants.LottoConstants

class LottoMachine {
    fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoConstants.LOTTO_RANGE).shuffled()
                .subList(0, LottoConstants.NUMBER_OF_LOTTO_NUMBERS).map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }
}
