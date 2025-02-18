package model

import constants.LottoConstants

class LottoMachine(purchaseAmount: Int) {
    init {
        require(purchaseAmount % LottoConstants.LOTTO_PRICE == 0)
    }

    fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoConstants.LOTTO_RANGE).shuffled()
                .subList(0, LottoConstants.NUMBER_OF_LOTTO_NUMBERS).map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }
}
